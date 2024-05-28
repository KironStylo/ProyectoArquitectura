package controller;
import java.io.Serializable;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import entities.Producto;
import services.ProductService;

@SuppressWarnings("deprecation")
@ManagedBean
@RequestScoped
public class ProductosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Producto> products;
	
	@PostConstruct
    public void init() {
        ProductService productService = new ProductService();
        products = productService.getAllProducts();
    }

	// Método para editar un producto
	public String editarProducto(Producto producto) {
		return "/admin/editarProducto?faces-redirect=true&id="+producto.getId();
	}

	// Método para ver un producto
	public String verProducto(Producto producto) {
		return "/admin/verProducto?faces-redirect=true&id="+producto.getId();
	}
	
	// Método para redirigir a la página de crear producto
	public String irACrearProducto() {
		return "/admin/crearProducto?faces-redirect=true";
	}

	public List<Producto> getProducts() {
		return products;
	}

	public void setProducts(List<Producto> products) {
		this.products = products;
	}
	
    public String cambiarEstadoProducto(Producto producto) {
        ProductService productService = new ProductService();
        producto.setActivo(!producto.isActivo());
        productService.actualizarProducto(producto);
        return null;
    }
}
