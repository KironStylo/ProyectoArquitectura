package repositorio.pedido;

import entities.Pedido;
import jakarta.enterprise.inject.Model;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Model
public class RepositorioPedidoImpl implements RepositorioPedido {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(Pedido obj) {
        entityManager.persist(obj);
    }

    @Override
    public void delete(Pedido obj) {
        entityManager.remove(entityManager.contains(obj) ? obj : entityManager.merge(obj));
    }

    @Override
    public void update(Pedido obj) {
        entityManager.merge(obj);
    }

    @Override
    public Pedido findById(Long id) {
        return entityManager.find(Pedido.class, id);
    }

    @Override
    public List<Pedido> findAll() {
        return entityManager.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
    }
    
    @Override
    public List<Pedido> findByUserId(Long userId) {
        return entityManager.createQuery("SELECT p FROM Pedido p WHERE p.usuario.id = ?1", Pedido.class)
                            .setParameter(1, userId)
                            .getResultList();
    }
}
