package controller;


import java.util.List;

import entities.DetallePedido;
import entities.Pedido;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import services.PedidoService;

@SuppressWarnings("deprecation")
@jakarta.faces.bean.ManagedBean
@jakarta.faces.bean.RequestScoped
public class DetallePedidoBean {

    private Long idPedido;
    private Pedido pedido;
    private PedidoService pedidoService;
    private List<DetallePedido> detalles;
    
    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String idParam = externalContext.getRequestParameterMap().get("idPedido");
        if (idParam != null) {
        	pedidoService = new PedidoService();
        	System.out.println("El id del pedido es " + idParam);
            idPedido = Long.parseLong(idParam);
        	System.out.println("Obteniendo el pedido");
            pedido = pedidoService.obtenerPedido(idPedido);
            setDetalles(pedido.getDetalles());
            System.out.println("Url de imagen"+detalles.get(0).getProducto().getImagenUrl());
        } else {
        	pedido = new Pedido();
        }
    }

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}
    
}

