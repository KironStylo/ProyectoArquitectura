package gestionDatos;

import java.util.List;

import entities.Pedido;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import repositorio.pedido.RepositorioPedido;

/**
 * Session Bean implementation class GestionDatosPedido
 */
@Stateless
public class GestionDatosPedido implements GestionDatosPedidoRemote {

	
	@Inject
	RepositorioPedido repositorioPedido;
	
    public GestionDatosPedido() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void crearPedido(Pedido pedido) {
		repositorioPedido.save(pedido);
	}

	@Override
	public void actualizarPedido(Pedido pedido) {
		repositorioPedido.update(pedido);
	}

	@Override
	public void eliminarPedido(Pedido pedido) {
		repositorioPedido.delete(pedido);
	}

	@Override
	public Pedido buscarPedido(Long id) {
		return repositorioPedido.findById(id);
	}

	@Override
	public List<Pedido> buscarPedidos() {
		return repositorioPedido.findAll();
	}

	@Override
	public List<Pedido> buscarPedidosPorUsuario(Long id) {
		return repositorioPedido.findByUserId(id);
	}

}
