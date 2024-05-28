package gestionDatos;

import java.util.List;

import entities.Pedido;
import jakarta.ejb.Remote;

@Remote
public interface GestionDatosPedidoRemote {
	public void crearPedido(Pedido pedido);
	public void actualizarPedido(Pedido pedido);
	public void eliminarPedido(Pedido pedido);
	public Pedido buscarPedido(Long id);
	public List<Pedido> buscarPedidos();
	public List<Pedido> buscarPedidosPorUsuario(Long id);
}
