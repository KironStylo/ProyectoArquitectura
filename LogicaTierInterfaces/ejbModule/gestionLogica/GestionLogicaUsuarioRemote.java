package gestionLogica;

import entities.Usuario;
import jakarta.ejb.Remote;

@Remote
public interface GestionLogicaUsuarioRemote {
	boolean crearUsuario(Usuario usuario);
	boolean autenticarUsuario(String rol, String username, String password);
	Usuario obtenerUsuario(Long id);
	Usuario encontrarPorUsuario(String username);
}
