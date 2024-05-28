package controller;

import java.util.Map;

import entities.Producto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import jakarta.faces.context.FacesContext;
import services.ProductService;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class ShopProductBean {

	private Long productId;
	private Producto producto;
	private ProductService productService;

	public ShopProductBean() {
		producto = new Producto();
		productService = new ProductService();
	}

	@PostConstruct
	public void init() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String productIdString = params.get("id");
		if (productIdString != null && !productIdString.isEmpty()) {
			productService = new ProductService();
			productId = Long.parseLong(productIdString);
			producto = productService.obtenerProducto(productId);
		}
	}

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

	public String regresarHome() {
		return "/shop/store.xhtml?faces-redirect=true";
	}
}
