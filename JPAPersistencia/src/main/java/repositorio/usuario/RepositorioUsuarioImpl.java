package repositorio.usuario;

import java.util.List;

import entities.Usuario;
import jakarta.enterprise.inject.Model;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Model
public class RepositorioUsuarioImpl implements RepositorioUsuario {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Usuario usuario) {
		entityManager.persist(usuario);
	}

	@Override
	public void update(Usuario usuario) {
		entityManager.merge(usuario);
	}

	@Override
	public void delete(Usuario usuario) {
		entityManager.remove(usuario);
	}

	@Override
	public Usuario findById(Long id) {
		return entityManager.find(Usuario.class, id);
	}

	@Override
	public List<Usuario> findAll() {
		return entityManager.createQuery("SELECT p FROM Usuario p", Usuario.class).getResultList();
	}

	@Override
	public boolean findUsuarioByRolNombreUsuarioAndContrasena(String rol, String nombreUsuario, String contrasena) {
		try {
			Usuario usuario = entityManager
					.createQuery(
							"SELECT u FROM Usuario u WHERE u.rol = ?1 AND u.nombreUsuario = ?2 AND u.contraseña = ?3",
							Usuario.class)
					.setParameter(1, rol).setParameter(2, nombreUsuario).setParameter(3, contrasena).getSingleResult();

			// Si se encuentra un usuario, se considera como éxito y devuelve true
			return usuario != null;
		} catch (NoResultException e) {
			// Si no se encuentra ningún resultado, se maneja la excepción y se devuelve
			// false
			return false;
		}
	}

	public boolean usuarioExiste(String nombreUsuario) {
		TypedQuery<Usuario> query = entityManager
				.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario", Usuario.class);
		query.setParameter("nombreUsuario", nombreUsuario);
		List<Usuario> usuarios = query.getResultList();

		return !usuarios.isEmpty();
	}

    public Usuario findByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :username", Usuario.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Usuario no encontrado
        }
    }

}
