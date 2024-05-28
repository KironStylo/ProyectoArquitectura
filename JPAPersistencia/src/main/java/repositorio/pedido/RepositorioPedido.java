package repositorio.pedido;

import java.util.List;

import entities.Pedido;
import repositorio.Repositorio;

public interface RepositorioPedido extends Repositorio<Pedido> {
	List<Pedido> findByUserId(Long id);
}
