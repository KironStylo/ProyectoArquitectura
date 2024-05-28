package gestionDatos;

import java.util.List;

import entities.Producto;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import repositorio.producto.RepositorioProducto;

/**
 * Session Bean implementation class GestionDatosProducto
 */
@Stateless
public class GestionDatosProducto implements GestionDatosProductoRemote {
	
	@Inject
	RepositorioProducto repositorioProducto;
	
    public GestionDatosProducto() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void crearProducto(Producto producto) {
		repositorioProducto.save(producto);
	}
	
	@Override
	public void actualizarProducto(Producto producto) {
		repositorioProducto.update(producto);
	}
	
	@Override
	public void eliminarProducto(Producto producto) {
		repositorioProducto.delete(producto);
	}
	
	@Override
	public Producto buscarProducto(Long id) {
		return repositorioProducto.findById(id);
	}
	
	@Override
	public List<Producto> buscarProductos() {
		return repositorioProducto.findAll();
	}

	@Override
	public List<Producto> buscarProductosActivos() {
		return repositorioProducto.findActiveProducts();
	}
}
