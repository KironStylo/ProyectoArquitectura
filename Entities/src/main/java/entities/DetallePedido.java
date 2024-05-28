package entities;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
public class DetallePedido implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Nombre de la columna en la tabla
    private Long id;
    
    @Column(name = "cantidad") // Nombre de la columna en la tabla
    private int cantidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id") // Nombre de la columna en la tabla
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id") // Nombre de la columna en la tabla
    private Producto producto;

    public DetallePedido() {}

    public DetallePedido(int cantidad) {
        this.cantidad = cantidad;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}

