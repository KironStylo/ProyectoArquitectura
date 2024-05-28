package gestionLogica;

import entities.Producto;

import java.util.List;
import jakarta.ejb.Remote;

@Remote
public interface GestionLogicaProductoRemote {
	public void crearProducto(Producto producto);
	public void actualizarProducto(Producto producto);
	public void eliminarProducto(Producto producto);
	public Producto buscarProducto(Long id);
	public List<Producto> buscarProductos();
	public List<Producto> buscarProductosActivos();
}
