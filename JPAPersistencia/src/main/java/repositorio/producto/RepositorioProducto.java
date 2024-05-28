package repositorio.producto;

import java.util.List;

import entities.Producto;
import repositorio.Repositorio;

public interface RepositorioProducto extends Repositorio<Producto> {
	public List<Producto> findActiveProducts();
	public List<Producto>findByQuantityGreaterThan(Long productId, int cantidadMinima);
}
