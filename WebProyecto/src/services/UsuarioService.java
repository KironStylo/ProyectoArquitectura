package services;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Usuario;
import gestionLogica.GestionLogicaUsuarioRemote;

public class UsuarioService {
	private static final String JNDI_NAME = "ejb:LogicaEAR/LogicaTier/GestionLogicaUsuario!gestionLogica.GestionLogicaUsuarioRemote";
	private static final String PROVIDER_URL = "http-remoting://localhost:8280";

	public boolean autenticarUsuario(String role, String username, String password) {
		try {
			Hashtable<String, String> environment = new Hashtable<>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, PROVIDER_URL);

			InitialContext context = new InitialContext(environment);
			GestionLogicaUsuarioRemote gestionLogicaUsuarioRemote = (GestionLogicaUsuarioRemote) context
					.lookup(JNDI_NAME);

			return gestionLogicaUsuarioRemote.autenticarUsuario(role, username, password);
		} catch (NamingException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean crearUsuario(String role, String username, String password) {
		try {
			Hashtable<String, String> environment = new Hashtable<>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, PROVIDER_URL);

			InitialContext context = new InitialContext(environment);
			GestionLogicaUsuarioRemote gestionLogicaUsuarioRemote = (GestionLogicaUsuarioRemote) context
					.lookup(JNDI_NAME);
			
			Usuario usuario = new Usuario(username, role, password);

			return gestionLogicaUsuarioRemote.crearUsuario(usuario);
		} catch (NamingException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Long obtenerIdPorUsername(String username){
		try {
			Hashtable<String, String> environment = new Hashtable<>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, PROVIDER_URL);

			InitialContext context = new InitialContext(environment);
			GestionLogicaUsuarioRemote gestionLogicaUsuarioRemote = (GestionLogicaUsuarioRemote) context
					.lookup(JNDI_NAME);
			
			return gestionLogicaUsuarioRemote.encontrarPorUsuario(username).getId();
			
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
