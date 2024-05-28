package gestionDatos;

import entities.Usuario;
import jakarta.ejb.Remote;

@Remote
public interface GestionDatosUsuarioRemote {
	boolean crearUsuario(Usuario usuario);
	boolean autenticar(String rol, String username, String contrasena);
	Usuario buscarUsuario(Long id);
	Usuario encontrarPorUsuario(String username);
}
