package services;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Pedido;
import gestionLogica.GestionLogicaPedidoRemote;

public class PedidoService {
	
	public List<Pedido> obtenerPedidosPorUsuario(Long id) {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		try {
			Hashtable<String, String> environment = new Hashtable<String, String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8280");
			InitialContext context = new InitialContext(environment);


			GestionLogicaPedidoRemote gu2 = (GestionLogicaPedidoRemote) context
					.lookup("ejb:LogicaEAR/LogicaTier/GestionLogicaPedido!gestionLogica.GestionLogicaPedidoRemote");
			
			pedidos = gu2.obtenerPedidosPorUsuario(id);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pedidos;
	}
	
	public Pedido obtenerPedido(Long id) {
		Pedido pedido = new Pedido();
		try {
			Hashtable<String, String> environment = new Hashtable<String, String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8280");
			InitialContext context = new InitialContext(environment);


			GestionLogicaPedidoRemote gu2 = (GestionLogicaPedidoRemote) context
					.lookup("ejb:LogicaEAR/LogicaTier/GestionLogicaPedido!gestionLogica.GestionLogicaPedidoRemote");
			
			pedido = gu2.obtenerPedido(id);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pedido;
	}

}
