package controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import jakarta.faces.context.FacesContext;
import services.PedidoService;
import services.UsuarioService;
import entities.Pedido;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class UsuarioPedidoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private List<Pedido> pedidos;
    private Long idPedidoSeleccionado; // Campo para el ID del pedido seleccionado

    private UsuarioService usuarioService;
    private PedidoService pedidoService;

    @PostConstruct
    public void init() {
        usuarioService = new UsuarioService();
        pedidoService = new PedidoService();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        username = facesContext.getExternalContext().getRequestParameterMap().get("username");
        if (username != null && !username.isEmpty()) {
            cargarPedidos();
        }
    }

    public void cargarPedidos() {
        Long userId = usuarioService.obtenerIdPorUsername(username);
        if (userId != null) {
            pedidos = pedidoService.obtenerPedidosPorUsuario(userId);
        } else {
            pedidos = new ArrayList<>();
        }
    }

    public String redirigirADetalle() {
    	System.out.println("Redirigiendo a pedido con ID" + idPedidoSeleccionado);
        return "verDetallePedidos?faces-redirect=true&idPedido=" + idPedidoSeleccionado;
    }

    // Getters y Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Long getIdPedidoSeleccionado() {
        return idPedidoSeleccionado;
    }

    public void setIdPedidoSeleccionado(Long idPedidoSeleccionado) {
        this.idPedidoSeleccionado = idPedidoSeleccionado;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void setPedidoService(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
}
