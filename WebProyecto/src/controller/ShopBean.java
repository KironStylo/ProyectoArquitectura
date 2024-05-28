package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import entities.Producto;
import entities.DetallePedido;
import entities.Pedido;
import services.ProductService;
import services.RecomendacionService;
import services.UsuarioService;
import services.PedidoService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@SuppressWarnings("deprecation")
@ManagedBean
@RequestScoped
public class ShopBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Producto> productos;
    private ProductService productoService;
    private RecomendacionService recommendationService;
    private UsuarioService usuarioService;
    private PedidoService pedidoService;

    public ShopBean() {
        productoService = new ProductService();
        recommendationService = new RecomendacionService();
        usuarioService = new UsuarioService();
        pedidoService = new PedidoService();
        productos = productoService.obtenerProductosActivos();
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public String verProducto(Long productId) {
        System.out.println("Ver producto");
        return "/shop/verProducto?faces-redirect=true&id=" + productId;
    }

    public void verRecomendados() {
        try {
            LoginBean loginBean = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
            if (loginBean != null && loginBean.getUsername() != null) {
            	System.out.println("El nombre de usuario es " + loginBean.getUsername());
                String username = loginBean.getUsername();
                Long usuarioId = usuarioService.obtenerIdPorUsername(username);

                List<Pedido> pedidos = pedidoService.obtenerPedidosPorUsuario(usuarioId);
                List<DetallePedido> historialDeCompras = obtenerDetallesDePedidos(pedidos);
                System.out.println("Se van a ver las recomendaciones");
                if (!historialDeCompras.isEmpty()) {
                	System.out.println("Se van a obtener la recomendaciones");
                    String jsonResponse = recommendationService.getRecommendations(historialDeCompras);
                    List<String> categoriasRecomendadas = parseRecommendations(jsonResponse);
                    System.out.println("Las recomendaciones son " + jsonResponse);
                    productos = productoService.obtenerProductosPorCategorias(categoriasRecomendadas);
                    System.out.println("Productos recomendados");
                } else {
                    productos = productoService.obtenerProductosActivos();
                }

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Recomendaciones obtenidas", "Se han obtenido las recomendaciones correctamente."));
            } else {
                productos = productoService.obtenerProductosActivos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario no autenticado", "Por favor, inicie sesión para ver recomendaciones personalizadas."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron obtener las recomendaciones: " + e.getMessage()));
        }
    }

    private List<DetallePedido> obtenerDetallesDePedidos(List<Pedido> pedidos) {
        List<DetallePedido> detalles = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            detalles.addAll(pedido.getDetalles());
        }
        return detalles;
    }

    private List<String> parseRecommendations(String jsonResponse) {
        List<String> categorias = new ArrayList<>();
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonArray categoriesArray = jsonObject.getAsJsonArray("recommended_categories");
        for (int i = 0; i < categoriesArray.size(); i++) {
            categorias.add(categoriesArray.get(i).getAsString());
        }
        return categorias;
    }
}
