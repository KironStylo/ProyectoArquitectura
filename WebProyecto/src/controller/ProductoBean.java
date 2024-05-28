package controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import jakarta.faces.context.FacesContext;

import java.util.Map;

import entities.Producto;
import services.ProductService;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class ProductoBean {

    private Long productId;
    private Producto producto;
    private ProductService productService;
    
    
    public ProductoBean() {
        producto = new Producto(); // Inicializamos un nuevo producto
        productService = new ProductService();
    }

    @PostConstruct
    public void init() {
        productService = new ProductService();
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String productIdString = params.get("id");
        if (productIdString != null && !productIdString.isEmpty()) {
            productId = Long.parseLong(productIdString);
            producto = productService.obtenerProducto(productId);
            System.out.println("Imprimir el id" + producto.getId());
        }
    }
    
    public String guardarProducto() {
    	System.out.println("Producto" +  producto.getNombre());
        productService.crearProducto(producto);
        return "/admin/home?faces-redirect=true"; // Redireccionar a la página principal después de guardar
    }
    // Getters y Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
	public String guardarCambios() {
		ProductService productService = new ProductService();
		System.out.println(producto.getPrecio());
		productService.actualizarProducto(producto);
		return "guardarCambios";
	}
	
	public String regresarHome() {
		return "/admin/home?faces-redirect=true";
	}
}