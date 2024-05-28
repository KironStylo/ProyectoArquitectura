package repositorio.usuario;

import entities.Usuario;
import repositorio.Repositorio;

public interface RepositorioUsuario extends Repositorio<Usuario> {
	boolean findUsuarioByRolNombreUsuarioAndContrasena(String rol, String nombreUsuario, String contrasena);
	Usuario findByUsername(String username);
	boolean usuarioExiste(String nombreUsuario);
}
