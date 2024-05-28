package gestionDatos;

import java.util.List;

import entities.Producto;
import jakarta.ejb.Remote;

@Remote
public interface GestionDatosProductoRemote {
	public void crearProducto(Producto producto);
	public void actualizarProducto(Producto producto);
	public void eliminarProducto(Producto producto);
	public Producto buscarProducto(Long id);
	public List<Producto> buscarProductosActivos();
	public List<Producto> buscarProductos();
}
