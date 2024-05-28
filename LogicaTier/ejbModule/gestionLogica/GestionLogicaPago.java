package gestionLogica;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import entities.DetallePedido;
import entities.ItemCarrito;
import entities.Pedido;
import entities.Producto;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Usuario;
import gestionDatos.GestionDatosPedidoRemote;
import gestionDatos.GestionDatosProductoRemote;
import gestionDatos.GestionDatosUsuarioRemote;
import jakarta.ejb.Stateless;

@Stateless
public class GestionLogicaPago implements GestionLogicaPagoRemote {


    public GestionLogicaPago() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean procesarPago(String username, String direccionEnvio, List<ItemCarrito> carritoItems, Date fechaPedido, double tarifa) { 
		boolean proceso = false;
		try {
			Hashtable<String, String> environment = new Hashtable<String, String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			environment.put(Context.PROVIDER_URL, "http-remoting://localhost:8480");

			InitialContext context = new InitialContext(environment);

			GestionDatosUsuarioRemote gu = (GestionDatosUsuarioRemote) context
					.lookup("ejb:DatosEar/DatosTier/GestionDatosUsuario!gestionDatos.GestionDatosUsuarioRemote");
			
			GestionDatosProductoRemote gu1 = (GestionDatosProductoRemote)context.lookup(
					"ejb:DatosEar/DatosTier/GestionDatosProducto!gestionDatos.GestionDatosProductoRemote");
			
			GestionDatosPedidoRemote gu2 = (GestionDatosPedidoRemote)context.lookup(
					"ejb:DatosEar/DatosTier/GestionDatosPedido!gestionDatos.GestionDatosPedidoRemote");
			
			Usuario usuario = gu.encontrarPorUsuario(username);
			
			Pedido pedido = new Pedido();
			
			pedido.setDireccion(direccionEnvio);
			pedido.setFechaPedido(new Date());
			
			List<DetallePedido> detalles = new ArrayList<DetallePedido>();
			
			double subtotal = 0;
			
			for(ItemCarrito item: carritoItems) {
				Producto producto = gu1.buscarProducto(item.getProducto().getId());
				if(item.getCantidad() > producto.getCantidad()) {
					return proceso;
				}else {
					DetallePedido detallePedido = new DetallePedido();
					detallePedido.setProducto(item.getProducto());
					detallePedido.setCantidad(item.getCantidad());
					detallePedido.setPedido(pedido);
					detalles.add(detallePedido);
					subtotal += item.getCantidad()*item.getProducto().getPrecio();
				}
			}
			
			for(ItemCarrito item: carritoItems) {
				Producto producto = gu1.buscarProducto(item.getProducto().getId());
				producto.setCantidad(producto.getCantidad()-item.getCantidad());
				gu1.actualizarProducto(producto);
			}
			
			pedido.setDetalles(detalles);
			pedido.setTotal(subtotal+tarifa);
			pedido.setUsuario(usuario);
			
			gu2.crearPedido(pedido);
			proceso = true;
			
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proceso;
	}

}
