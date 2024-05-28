package controller;

import java.io.Serializable;
import java.util.ArrayList;

import entities.ItemCarrito;
import entities.Producto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import models.CarritoCompras;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class ShoppingCartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private CarritoCompras carrito;

	@PostConstruct
	public void init() {
		carrito = new CarritoCompras();
	}

	// Métodos para agregar, eliminar, actualizar y obtener elementos del carrito
	public void agregarAlCarrito(Producto producto, int cantidad) {
		System.out.println("Agregando un producto al carrito");
		carrito.agregarItem(producto, cantidad);
	}

	// Método para calcular el total del carrito
	public double calcularTotal() {
		return carrito.obtenerTotal();
	}
	
	public int obtenerCantidad() {
		return carrito.obtenerCantidadProductos();
	}

	public void incrementarCantidad(Producto producto) {
		carrito.incrementarCantidad(producto);
	}
	
	public void limpiarCarrito() {
		carrito.setItems(new  ArrayList<ItemCarrito>());
	}

	public void decrementarCantidad(Producto producto) {
		carrito.decrementarCantidad(producto);
	}

	public CarritoCompras getCarrito() {
		return carrito;
	}

	public void setCarrito(CarritoCompras carrito) {
		this.carrito = carrito;
	}

}
