package repositorio.producto;

import entities.Producto;
import jakarta.enterprise.inject.Model;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Model
public class RepositorioProductoImpl implements RepositorioProducto {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Producto producto) {
        entityManager.persist(producto);
    }

    @Override
    public void update(Producto producto) {
        entityManager.merge(producto);
    }

    @Override
    public void delete(Producto producto) {
        producto.setActivo(false);
        entityManager.merge(producto);
    }

    @Override
    public Producto findById(Long id) {
        return entityManager.find(Producto.class, id);
    }

    @Override
    public List<Producto> findAll() {
        return entityManager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }

    @Override
    public List<Producto> findActiveProducts() {
        return entityManager.createQuery("SELECT p FROM Producto p WHERE p.activo = true and p.cantidad > 0", Producto.class).getResultList();
    }
    
    @Override
    public List<Producto> findByQuantityGreaterThan(Long productId, int cantidadMinima) {
        return entityManager.createQuery("SELECT p FROM Producto p WHERE p.id = ?1 AND p.cantidad > ?2", Producto.class)
                            .setParameter(1, productId)
                            .setParameter(2, cantidadMinima)
                            .getResultList();
    }

}
