package gestionLogica;

import java.util.List;

import entities.Pedido;
import jakarta.ejb.Remote;

@Remote
public interface GestionLogicaPedidoRemote {
	public Pedido obtenerPedido(Long id);
	public List<Pedido> obtenerPedidos();
	public void crearPedido(Pedido pedido);
	public List<Pedido> obtenerPedidosPorUsuario(Long id);
}
