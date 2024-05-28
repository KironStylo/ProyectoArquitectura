package services;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.ItemCarrito;
import gestionLogica.GestionLogicaPagoRemote;

public class PagoService {
	
	public boolean hacerPago(String username, String direccion,List<ItemCarrito> carritoItems, Date fechaPedido, double tarifa) {
		boolean valido = false;
		try {
			System.out.println("Vamos a procesar el pago");
			Hashtable<String, String> environment = new Hashtable<String, String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8280");

			InitialContext context = new InitialContext(environment);

			GestionLogicaPagoRemote gu = (GestionLogicaPagoRemote) context
					.lookup("ejb:LogicaEAR/LogicaTier/GestionLogicaPago!gestionLogica.GestionLogicaPagoRemote");
			
			valido = gu.procesarPago(username, direccion, carritoItems, fechaPedido, tarifa);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valido;
	}
}
