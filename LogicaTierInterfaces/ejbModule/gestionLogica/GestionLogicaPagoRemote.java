package gestionLogica;

import java.util.Date;
import java.util.List;

import entities.ItemCarrito;
import jakarta.ejb.Remote;

@Remote
public interface GestionLogicaPagoRemote {
	public boolean procesarPago(String username, String direccion,List<ItemCarrito> carritoItems, Date fechaPedido, double tarifa);
}
