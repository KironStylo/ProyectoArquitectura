package gestionLogica;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Pedido;
import gestionDatos.GestionDatosPedidoRemote;
import jakarta.ejb.Stateless;

/**
 * Session Bean implementation class GestionLogicaPedido
 */
@Stateless
public class GestionLogicaPedido implements GestionLogicaPedidoRemote {

    /**
     * Default constructor. 
     */
    public GestionLogicaPedido() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void crearPedido(Pedido pedido) {
		try {
			Hashtable<String, String> environment = new Hashtable<String,String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8480");

			InitialContext context = new InitialContext(environment);
			
			GestionDatosPedidoRemote gu = (GestionDatosPedidoRemote)context.lookup(
					"ejb:DatosEar/DatosTier/GestionDatosPedido!gestionDatos.GestionDatosPedidoRemote");
			
			gu.crearPedido(pedido);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Pedido obtenerPedido(Long id) {
		Pedido pedido = new Pedido();
		try {
			Hashtable<String, String> environment = new Hashtable<String,String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8480");

			InitialContext context = new InitialContext(environment);
			
			GestionDatosPedidoRemote gu = (GestionDatosPedidoRemote)context.lookup(
					"ejb:DatosEar/DatosTier/GestionDatosPedido!gestionDatos.GestionDatosPedidoRemote");
			
			pedido = gu.buscarPedido(id);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}	
		return pedido;
	}

	@Override
	public List<Pedido> obtenerPedidos() {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		try {
			Hashtable<String, String> environment = new Hashtable<String,String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8480");

			InitialContext context = new InitialContext(environment);
			
			GestionDatosPedidoRemote gu = (GestionDatosPedidoRemote)context.lookup(
					"ejb:DatosEar/DatosTier/GestionDatosPedido!gestionDatos.GestionDatosPedidoRemote");
			
			pedidos = gu.buscarPedidos();
			
		} catch (NamingException e) {
			e.printStackTrace();
		}	
		return pedidos;
	}

	@Override
	public List<Pedido> obtenerPedidosPorUsuario(Long id) {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		try {
			Hashtable<String, String> environment = new Hashtable<String,String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8480");

			InitialContext context = new InitialContext(environment);
			
			GestionDatosPedidoRemote gu = (GestionDatosPedidoRemote)context.lookup(
					"ejb:DatosEar/DatosTier/GestionDatosPedido!gestionDatos.GestionDatosPedidoRemote");
			
			pedidos = gu.buscarPedidosPorUsuario(id);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}	
		return pedidos;
	}

}
