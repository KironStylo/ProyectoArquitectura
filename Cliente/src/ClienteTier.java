import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.DetallePedido;
import entities.ItemCarrito;
import entities.Pedido;
import entities.Producto;
import entities.Usuario;
import gestionLogica.GestionLogicaPagoRemote;
import gestionLogica.GestionLogicaPedidoRemote;
import gestionLogica.GestionLogicaProductoRemote;
import gestionLogica.GestionLogicaUsuarioRemote;

public class ClienteTier {

	public static void main(String[] args) {
		ClienteTier c = new ClienteTier();
		c.crearProductos();
		c.crearUsuario();
	}
	
	public void encontrarPorUsuario() {
		try {
			Hashtable<String, String> environment = new Hashtable<String, String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8280");

			InitialContext context = new InitialContext(environment);
			
			GestionLogicaUsuarioRemote gu = (GestionLogicaUsuarioRemote) context
					.lookup("ejb:LogicaEAR/LogicaTier/GestionLogicaUsuario!gestionLogica.GestionLogicaUsuarioRemote");
			
			Usuario usuario = gu.encontrarPorUsuario("Daniel");
			
			System.out.println("El usuario Daniel tiene ID" + usuario.getId() + " y rol " + usuario.getRol() );
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void obtenerPedidosPorUsuario() {
		try {
			Hashtable<String, String> environment = new Hashtable<String, String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8280");
			InitialContext context = new InitialContext(environment);


			GestionLogicaPedidoRemote gu2 = (GestionLogicaPedidoRemote) context
					.lookup("ejb:LogicaEAR/LogicaTier/GestionLogicaPedido!gestionLogica.GestionLogicaPedidoRemote");
			
			List<Pedido> pedidos = new ArrayList<Pedido>();
			
			pedidos = gu2.obtenerPedidosPorUsuario(1L);
			
			for(Pedido  p: pedidos) {
				System.out.println("Información pedido" + p.getDireccion());
				for(DetallePedido  d: p.getDetalles()) {
					System.out.println("Producto: " + d.getProducto().getNombre());
					System.out.println("Cantidad " + d.getCantidad());
				}
			}
			
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void crearPedido() {
		try {
			Hashtable<String, String> environment = new Hashtable<String, String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8280");

			InitialContext context = new InitialContext(environment);
			
			GestionLogicaUsuarioRemote gu = (GestionLogicaUsuarioRemote) context
					.lookup("ejb:LogicaEAR/LogicaTier/GestionLogicaUsuario!gestionLogica.GestionLogicaUsuarioRemote");
			
			GestionLogicaProductoRemote gu1 = (GestionLogicaProductoRemote) context
					.lookup("ejb:LogicaEAR/LogicaTier/GestionLogicaProducto!gestionLogica.GestionLogicaProductoRemote");
			
			GestionLogicaPedidoRemote gu2 = (GestionLogicaPedidoRemote) context
					.lookup("ejb:LogicaEAR/LogicaTier/GestionLogicaPedido!gestionLogica.GestionLogicaPedidoRemote");
			
			
			Usuario usuario = gu.obtenerUsuario(1L);
			Pedido pedido = new Pedido();
			pedido.setDireccion("Dirección A");
			pedido.setFechaPedido(new Date());
			pedido.setTotal(1800);
			pedido.setUsuario(usuario);
			List<DetallePedido> detalles = new ArrayList<DetallePedido>();
			DetallePedido detalle = new DetallePedido();
			detalle.setCantidad(1);
			detalle.setProducto(gu1.buscarProducto(1L));
			detalle.setPedido(pedido);
			detalles.add(detalle);

			
			DetallePedido detalle2 = new DetallePedido();
			detalle2.setCantidad(14);
			detalle2.setProducto(gu1.buscarProducto(2L));
			detalle2.setPedido(pedido);
			detalles.add(detalle2);
			
			pedido.setDetalles(detalles);
			
			gu2.crearPedido(pedido);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void hacerPago() {
		try {
			Hashtable<String, String> environment = new Hashtable<String, String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8280");

			InitialContext context = new InitialContext(environment);

			GestionLogicaPagoRemote gu = (GestionLogicaPagoRemote) context
					.lookup("ejb:LogicaEAR/LogicaTier/GestionLogicaPago!gestionLogica.GestionLogicaPagoRemote");
			
			GestionLogicaProductoRemote gu1 = (GestionLogicaProductoRemote) context
					.lookup("ejb:LogicaEAR/LogicaTier/GestionLogicaProducto!gestionLogica.GestionLogicaProductoRemote");
			
			List<ItemCarrito> carritoItems = new ArrayList<ItemCarrito>();
			ItemCarrito itemCarrito = new ItemCarrito();
			Producto producto = gu1.buscarProducto(1L);
			itemCarrito.setCantidad(5);
			itemCarrito.setProducto(producto);
			
			carritoItems.add(itemCarrito);
			
			boolean funciono =  gu.procesarPago("Daniel", "Direccion A", carritoItems, new Date(), 12000);
			
			System.out.println("Funciono: " + funciono);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void crearUsuario() {
		try {
			Hashtable<String, String> environment = new Hashtable<String, String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8280");

			InitialContext context = new InitialContext(environment);

			GestionLogicaUsuarioRemote gu = (GestionLogicaUsuarioRemote) context
					.lookup("ejb:LogicaEAR/LogicaTier/GestionLogicaUsuario!gestionLogica.GestionLogicaUsuarioRemote");

			Usuario usuario = new Usuario();
			usuario.setNombreUsuario("Admin");
			usuario.setContraseña("Admin");
			usuario.setRol("Administrador");

			boolean creado = gu.crearUsuario(usuario);

			if(creado) {
				System.out.println("El usuario fue creado");
			}else {
				System.out.println("El usuario no fue creado ya existe en la base de datos");
			}

			System.out.println(gu.autenticarUsuario("Administrador", "Admin", "Admin"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void encontrarProductos() {
		
		List<Producto> productos = new  ArrayList<Producto>();
		try {
			Hashtable<String, String> environment = new Hashtable<String, String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8280");

			InitialContext context = new InitialContext(environment);

			GestionLogicaProductoRemote gu = (GestionLogicaProductoRemote) context
					.lookup("ejb:LogicaEAR/LogicaTier/GestionLogicaProducto!gestionLogica.GestionLogicaProductoRemote");
			
			productos = gu.buscarProductosActivos();
			
			for(Producto p : productos) {
				System.out.println("Producto nombre y activo" + p.getNombre() + ", " + p.isActivo());
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void crearProductos() {
		try {
			Hashtable<String, String> environment = new Hashtable<String, String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8280");

			InitialContext context = new InitialContext(environment);

			GestionLogicaProductoRemote gu = (GestionLogicaProductoRemote) context
					.lookup("ejb:LogicaEAR/LogicaTier/GestionLogicaProducto!gestionLogica.GestionLogicaProductoRemote");

			List<Producto> listaProductos = new ArrayList<>();

	        Producto producto1 = new Producto("Asus ZenBook 14 UX434",
	                "Portátil ultradelgado con pantalla táctil NanoEdge de 14 pulgadas, procesador Intel Core i7, 16 GB de RAM, SSD de 512 GB, tarjeta gráfica NVIDIA GeForce MX250, y sistema operativo Windows 10 Pro.",
	                "Portátiles", 1499.99, 10, "https://pngimg.com/uploads/laptop/laptop_PNG5930.png", true);
	        Producto producto2 = new Producto("Asus ROG Strix G15",
	                "Laptop gaming con pantalla FHD de 15.6 pulgadas, procesador Intel Core i7, 16 GB de RAM DDR4, SSD de 1 TB, tarjeta gráfica NVIDIA GeForce RTX 2070, y teclado RGB.",
	                "Portátiles", 1899.99, 8, "https://pngimg.com/uploads/laptop/laptop_PNG5930.png", true);
	        Producto producto3 = new Producto("Asus VivoBook 15",
	                "Portátil para uso diario con pantalla Full HD de 15.6 pulgadas, procesador AMD Ryzen 5, 8 GB de RAM DDR4, SSD de 256 GB, gráficos AMD Radeon, y diseño compacto y ligero.",
	                "Portátiles", 699.99, 15, "https://pngimg.com/uploads/laptop/laptop_PNG5930.png", true);

	        Producto celular1 = new Producto("Samsung Galaxy S21",
	                "Smartphone con pantalla AMOLED de 6.2 pulgadas, procesador Exynos 2100, 8 GB de RAM, 128 GB de almacenamiento interno, cámara triple de 12 MP, y batería de 4000 mAh.",
	                "Celulares", 799.99, 20, "https://d3gqasl9vmjfd8.cloudfront.net/aaa55709-15d9-486e-9097-381575296ed3.jpg", true);
	        Producto celular2 = new Producto("iPhone 13 Pro",
	                "Smartphone con pantalla Super Retina XDR de 6.1 pulgadas, procesador A15 Bionic, 6 GB de RAM, 128 GB de almacenamiento interno, cámara triple de 12 MP, y batería de 3095 mAh.",
	                "Celulares", 999.99, 15, "https://d3gqasl9vmjfd8.cloudfront.net/aaa55709-15d9-486e-9097-381575296ed3.jpg", true);
	        Producto celular3 = new Producto("Google Pixel 6",
	                "Smartphone con pantalla OLED de 6.4 pulgadas, procesador Google Tensor, 8 GB de RAM, 128 GB de almacenamiento interno, cámara dual de 50 MP, y batería de 4614 mAh.",
	                "Celulares", 699.99, 18, "https://d3gqasl9vmjfd8.cloudfront.net/aaa55709-15d9-486e-9097-381575296ed3.jpg", true);
	        Producto celular4 = new Producto("OnePlus 9 Pro",
	                "Smartphone con pantalla Fluid AMOLED de 6.7 pulgadas, procesador Snapdragon 888, 12 GB de RAM, 256 GB de almacenamiento interno, cámara cuádruple de 48 MP, y batería de 4500 mAh.",
	                "Celulares", 969.99, 12, "https://d3gqasl9vmjfd8.cloudfront.net/aaa55709-15d9-486e-9097-381575296ed3.jpg", true);

	        listaProductos.add(producto1);
	        listaProductos.add(producto2);
	        listaProductos.add(producto3);
	        listaProductos.add(celular1);
	        listaProductos.add(celular2);
	        listaProductos.add(celular3);
	        listaProductos.add(celular4);

			// Iteramos sobre la lista de productos y ejecutamos la función
			// gu.crearProducto(producto) para cada uno
			for (Producto producto : listaProductos) {
				gu.crearProducto(producto); // Suponiendo que 'gu' es una instancia válida de la clase que contiene el
											// método crearProducto
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
