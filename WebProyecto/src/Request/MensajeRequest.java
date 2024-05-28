package Request;

import java.util.List;

import models.Producto;

public class MensajeRequest {
    private String para;
    private String username;
    private String direccionEnvio;
    private List<Producto> productos;
    private double tarifaEnvio;
    private double totalPago;
    private String fechaPedido;
	public String getPara() {
		return para;
	}
	public void setPara(String para) {
		this.para = para;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDireccionEnvio() {
		return direccionEnvio;
	}
	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	public double getTarifaEnvio() {
		return tarifaEnvio;
	}
	public void setTarifaEnvio(double tarifaEnvio) {
		this.tarifaEnvio = tarifaEnvio;
	}
	public double getTotalPago() {
		return totalPago;
	}
	public void setTotalPago(double totalPago) {
		this.totalPago = totalPago;
	}
	public String getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(String fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
}
