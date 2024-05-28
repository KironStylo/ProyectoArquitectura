package gestionLogica;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Usuario;
import gestionDatos.GestionDatosUsuarioRemote;
import jakarta.ejb.Stateless;

/**
 * Session Bean implementation class GestionLogicaUsuario
 */
@Stateless
public class GestionLogicaUsuario implements GestionLogicaUsuarioRemote{

    /**
     * Default constructor. 
     */
    public GestionLogicaUsuario() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean crearUsuario(Usuario usuario) {
		boolean creado = false;
		try {
			Hashtable<String, String> environment = new Hashtable<String,String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8480");

			InitialContext context = new InitialContext(environment);
			
			GestionDatosUsuarioRemote gu = (GestionDatosUsuarioRemote)context.lookup(
					"ejb:DatosEar/DatosTier/GestionDatosUsuario!gestionDatos.GestionDatosUsuarioRemote");
			creado = gu.crearUsuario(usuario);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return creado;
	}

	@Override
	public boolean autenticarUsuario(String rol, String username, String password) {
		boolean autenticado = false;
		try {
			Hashtable<String, String> environment = new Hashtable<String,String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8480");

			InitialContext context = new InitialContext(environment);
			
			GestionDatosUsuarioRemote gu = (GestionDatosUsuarioRemote)context.lookup(
					"ejb:DatosEar/DatosTier/GestionDatosUsuario!gestionDatos.GestionDatosUsuarioRemote");
			System.out.println("Autenticando el usuario");
			System.out.println(rol + "" + username + ""  + password);
			autenticado  = gu.autenticar(rol, username, password);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return autenticado;
	}

	@Override
	public Usuario obtenerUsuario(Long id) {
		Usuario usuario = new Usuario();
		try {
			Hashtable<String, String> environment = new Hashtable<String,String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8480");

			InitialContext context = new InitialContext(environment);
			
			GestionDatosUsuarioRemote gu = (GestionDatosUsuarioRemote)context.lookup(
					"ejb:DatosEar/DatosTier/GestionDatosUsuario!gestionDatos.GestionDatosUsuarioRemote");
			usuario  = gu.buscarUsuario(id);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	@Override
	public Usuario encontrarPorUsuario(String username) {
		Usuario usuario = new Usuario();
		try {
			Hashtable<String, String> environment = new Hashtable<String,String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8480");

			InitialContext context = new InitialContext(environment);
			
			GestionDatosUsuarioRemote gu = (GestionDatosUsuarioRemote)context.lookup(
					"ejb:DatosEar/DatosTier/GestionDatosUsuario!gestionDatos.GestionDatosUsuarioRemote");
			usuario  = gu.encontrarPorUsuario(username);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return usuario;
	}
}
