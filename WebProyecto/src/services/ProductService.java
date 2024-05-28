package services;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Producto;
import gestionLogica.GestionLogicaProductoRemote;

public class ProductService {

	public List<Producto> getAllProducts() {
		List<Producto> productos = new ArrayList<>();
		System.out.println("Llamando al servicio de productos");
		try {
			Hashtable<String, String> environment = new Hashtable<String, String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8280");

			InitialContext context = new InitialContext(environment);

			GestionLogicaProductoRemote gu = (GestionLogicaProductoRemote) context
					.lookup("ejb:LogicaEAR/LogicaTier/GestionLogicaProducto!gestionLogica.GestionLogicaProductoRemote");
			productos = gu.buscarProductos();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return productos;
	}

	public void actualizarProducto(Producto producto) {
		try {
			Hashtable<String, String> environment = new Hashtable<String, String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8280");

			InitialContext context = new InitialContext(environment);

			GestionLogicaProductoRemote gu = (GestionLogicaProductoRemote) context
					.lookup("ejb:LogicaEAR/LogicaTier/GestionLogicaProducto!gestionLogica.GestionLogicaProductoRemote");

			System.out.println("Actualizando producto con ID " + producto.getId());
			gu.actualizarProducto(producto);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Producto obtenerProducto(Long id) {
		Producto producto = new Producto();
		try {
			Hashtable<String, String> environment = new Hashtable<String, String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8280");

			InitialContext context = new InitialContext(environment);

			GestionLogicaProductoRemote gu = (GestionLogicaProductoRemote) context
					.lookup("ejb:LogicaEAR/LogicaTier/GestionLogicaProducto!gestionLogica.GestionLogicaProductoRemote");

			System.out.println("Obteniendo el proucto");
			producto = gu.buscarProducto(id);

		} catch (NamingException e) {
			e.printStackTrace();
		}

		return producto;
	}

	public void crearProducto(Producto producto) {
		try {
			Hashtable<String, String> environment = new Hashtable<String, String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8280");

			InitialContext context = new InitialContext(environment);

			GestionLogicaProductoRemote gu = (GestionLogicaProductoRemote) context
					.lookup("ejb:LogicaEAR/LogicaTier/GestionLogicaProducto!gestionLogica.GestionLogicaProductoRemote");

			System.out.println("Obteniendo el proucto");
			gu.crearProducto(producto);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public List<Producto> obtenerProductosActivos() {
		List<Producto> productos = new ArrayList<>();
		System.out.println("Llamando al servicio de productos");
		try {
			Hashtable<String, String> environment = new Hashtable<String, String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8280");

			InitialContext context = new InitialContext(environment);

			GestionLogicaProductoRemote gu = (GestionLogicaProductoRemote) context
					.lookup("ejb:LogicaEAR/LogicaTier/GestionLogicaProducto!gestionLogica.GestionLogicaProductoRemote");
			productos = gu.buscarProductosActivos();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return productos;
	}

	public List<Producto> obtenerProductosPorCategorias(List<String> categorias) {
		List<Producto> todosProductos = obtenerProductosActivos();
		return todosProductos.stream().filter(producto -> categorias.contains(producto.getCategoria()))
				.collect(Collectors.toList());
	}

}
