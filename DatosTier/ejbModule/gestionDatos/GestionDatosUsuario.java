package gestionDatos;

import entities.Usuario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import repositorio.usuario.RepositorioUsuario;

/**
 * Session Bean implementation class GestionDatosUsuario
 */
@Stateless
public class GestionDatosUsuario implements GestionDatosUsuarioRemote {

	@Inject
	RepositorioUsuario repositorioUsuario;
	
    public GestionDatosUsuario() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean crearUsuario(Usuario usuario) {	
		if(repositorioUsuario.usuarioExiste(usuario.getNombreUsuario())) {
			return false;
		}
		repositorioUsuario.save(usuario);
		return true;
	}

	@Override
	public boolean autenticar(String rol, String username, String contrasena) {
		return repositorioUsuario.findUsuarioByRolNombreUsuarioAndContrasena(rol, username, contrasena);
	}

	@Override
	public Usuario encontrarPorUsuario(String username) {
		return repositorioUsuario.findByUsername(username);
	}

	@Override
	public Usuario buscarUsuario(Long id) {
		return repositorioUsuario.findById(id);
	}
	

}
