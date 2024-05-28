package entities;

import java.io.Serializable;

public class ItemCarrito implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Producto producto;
    private int cantidad;
    
    
    public ItemCarrito() {
    	
    }
    
	public ItemCarrito(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
  
}
