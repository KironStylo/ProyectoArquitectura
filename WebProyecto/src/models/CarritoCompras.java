package models;

import java.util.ArrayList;
import java.util.List;

import entities.ItemCarrito;
import entities.Producto;

public class CarritoCompras {
    private List<ItemCarrito> items;

    public CarritoCompras() {
        this.items = new ArrayList<>();
    }

    public void agregarItem(Producto producto, int cantidad) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getId().equals(producto.getId())) {
                item.setCantidad(item.getCantidad() + cantidad);
                return;
            }
        }
        items.add(new ItemCarrito(producto, cantidad));
    }

    public void incrementarCantidad(Producto producto) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getId().equals(producto.getId())) {
                item.setCantidad(item.getCantidad() + 1);
                return;
            }
        }
    }

    public void decrementarCantidad(Producto producto) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getId().equals(producto.getId())) {
                item.setCantidad(item.getCantidad() - 1);
                if (item.getCantidad() <= 0) {
                    items.remove(item);
                }
                return;
            }
        }
    }

    public double obtenerTotal() {
        double total = 0;
        for (ItemCarrito item : items) {
            double precioUnitario = item.getProducto().getPrecio();
            total += precioUnitario * item.getCantidad();
        }
        return total;
    }
    
    public int obtenerCantidadProductos() {
    	int cantidadTotal  = 0;
    	for(ItemCarrito item: items) {
    		cantidadTotal += item.getCantidad();
    	}
    	return cantidadTotal;
    }

    public int obtenerCantidadDeProducto(Producto producto) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getId().equals(producto.getId())) {
                return item.getCantidad();
            }
        }
        return 0; // Si el producto no está en el carrito, devuelve 0
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public void setItems(List<ItemCarrito> items) {
        this.items = items;
    }
}
