package gestionLogica;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Producto;
import gestionDatos.GestionDatosProductoRemote;
import jakarta.ejb.Stateless;

/**
 * Session Bean implementation class GestionLogicaProducto
 */
@Stateless
public class GestionLogicaProducto implements GestionLogicaProductoRemote {

    /**
     * Default constructor. 
     */
    public GestionLogicaProducto() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void crearProducto(Producto producto) {
		try {
			Hashtable<String, String> environment = new Hashtable<String,String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8480");

			InitialContext context = new InitialContext(environment);
			
			GestionDatosProductoRemote gu = (GestionDatosProductoRemote)context.lookup(
					"ejb:DatosEar/DatosTier/GestionDatosProducto!gestionDatos.GestionDatosProductoRemote");
			
			gu.crearProducto(producto);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void actualizarProducto(Producto producto) {
		try {
			Hashtable<String, String> environment = new Hashtable<String,String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8480");

			InitialContext context = new InitialContext(environment);
			
			System.out.println("Producto a actualizar" +  producto.getNombre());
			
			GestionDatosProductoRemote gu = (GestionDatosProductoRemote)context.lookup(
					"ejb:DatosEar/DatosTier/GestionDatosProducto!gestionDatos.GestionDatosProductoRemote");
			
			gu.actualizarProducto(producto);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarProducto(Producto producto) {
		try {
			Hashtable<String, String> environment = new Hashtable<String,String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8480");

			InitialContext context = new InitialContext(environment);
			
			GestionDatosProductoRemote gu = (GestionDatosProductoRemote)context.lookup(
					"ejb:DatosEar/DatosTier/GestionDatosProducto!gestionDatos.GestionDatosProductoRemote");
			
			gu.eliminarProducto(producto);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public Producto buscarProducto(Long id) {
		Producto producto = new Producto();
		try {
			Hashtable<String, String> environment = new Hashtable<String,String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8480");

			InitialContext context = new InitialContext(environment);
			
			GestionDatosProductoRemote gu = (GestionDatosProductoRemote)context.lookup(
					"ejb:DatosEar/DatosTier/GestionDatosProducto!gestionDatos.GestionDatosProductoRemote");
			
			producto = gu.buscarProducto(id);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return producto;
	}

	@Override
	public List<Producto> buscarProductos() {
		List<Producto> productos = new ArrayList<>();
		try {
			Hashtable<String, String> environment = new Hashtable<String,String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8480");

			InitialContext context = new InitialContext(environment);
			
			GestionDatosProductoRemote gu = (GestionDatosProductoRemote)context.lookup(
					"ejb:DatosEar/DatosTier/GestionDatosProducto!gestionDatos.GestionDatosProductoRemote");
			
			productos = gu.buscarProductos();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productos;
	}

	@Override
	public List<Producto> buscarProductosActivos() {
		List <Producto> productosActivos = new ArrayList<Producto>();
		try {
			Hashtable<String, String> environment = new Hashtable<String,String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8480");

			InitialContext context = new InitialContext(environment);
			
			GestionDatosProductoRemote gu = (GestionDatosProductoRemote)context.lookup(
					"ejb:DatosEar/DatosTier/GestionDatosProducto!gestionDatos.GestionDatosProductoRemote");
			
			productosActivos = gu.buscarProductosActivos();
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return productosActivos;
	}
	
	

}
