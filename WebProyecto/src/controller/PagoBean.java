package controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.faces.context.FacesContext;
import models.Producto;
import Request.CotizacionRequest;
import Request.MensajeRequest;
import Response.CotizacionResponse;
import entities.ItemCarrito;
import services.CotizacionService;
import services.MensajeriaService;
import services.PagoService;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class PagoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String numeroTarjeta;
    private String nombreTitular;
    private Date fechaExpiracion;
    private String codigoSeguridad;
    private String direccionEnvio;
    private String direccionEntrega;
    private String correoElectronico;
    private double tarifaEnvio;
    private PagoService pagoService;
    private MensajeriaService mensajeriaService;
    private CotizacionService cotizacionService;
    private boolean success;
    
    
    @PostConstruct
    public void init() {
    	System.out.println("Iniciando servicios");
    	pagoService = new PagoService();
    	mensajeriaService = new MensajeriaService();
    	cotizacionService = new CotizacionService();    	
    }

    // Getters and Setters
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public double getTarifaEnvio() {
        return tarifaEnvio;
    }

    public void setTarifaEnvio(double tarifaEnvio) {
        this.tarifaEnvio = tarifaEnvio;
    }
    
    public boolean isSuccess() {
        return success;
    }

    public void getSuccess(boolean success) {
        this.success = success;
    }

    public String procesarPago(String username, List<ItemCarrito> items, double total) {
    	
    	System.out.println("Procesando pago");
        FacesContext context = FacesContext.getCurrentInstance();

        if (numeroTarjeta == null || numeroTarjeta.isEmpty() ||
                nombreTitular == null || nombreTitular.isEmpty() ||
                fechaExpiracion == null ||
                codigoSeguridad == null || codigoSeguridad.isEmpty() ||
                direccionEntrega == null || direccionEntrega.isEmpty() ||
                correoElectronico == null || correoElectronico.isEmpty() ||
                tarifaEnvio == 0.0) {

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Por favor complete todos los campos y asegúrese de que la tarifa de envío sea diferente de 0."));
            context.validationFailed(); // Indicate that validation has failed
        } else {
        	boolean valido = pagoService.hacerPago(username, this.direccionEnvio, items, new Date(), tarifaEnvio);
        	if(valido) {
        		System.out.println("Se realizo el pedido con exito");
                MensajeRequest mensajeRequest = new MensajeRequest();
                mensajeRequest.setPara(correoElectronico);
                mensajeRequest.setUsername(username);
                
                List<Producto> productos = new ArrayList<Producto>();
                for(ItemCarrito item: items) {
                	Producto producto = new Producto();
                	producto.setNombre(item.getProducto().getNombre());
                	producto.setPrecio(item.getProducto().getPrecio());
                	producto.setCantidad(item.getCantidad());
                	productos.add(producto);
                }
                
                mensajeRequest.setProductos(productos);
                mensajeRequest.setTarifaEnvio(tarifaEnvio);
                LocalDateTime ahora = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String fechaFormateada = ahora.format(formatter);
                mensajeRequest.setFechaPedido(fechaFormateada);
                mensajeRequest.setTotalPago(tarifaEnvio+total);
                mensajeRequest.setDireccionEnvio(this.direccionEntrega);
                
               success = mensajeriaService.enviarMensaje(mensajeRequest);
               
               System.out.println("El envio fue exitoso" + success);
                
                return "pagoRealizado.xhtml?faces-redirect=true";
        	}
        	else {
        		System.out.println("El pedido no se realizo con exito");
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "No se pudo realizar la transacción"));
        	}
        }
        return null;
    }
    public void calcularTarifaEnvio(int cantidad) {
        try {
        	System.out.println("Calculando tarifa de envio");
        	System.out.println("Pagando " + cantidad + " de productos");
            CotizacionRequest request = new CotizacionRequest();
            request.setDireccionEnvio("carrera 7 N 45 - 40");
            request.setDireccionEntrega(this.direccionEntrega);
            request.setNumProductos(cantidad);

            CotizacionResponse response = cotizacionService.obtenerCotizacion(request);

            this.tarifaEnvio = response.getCostoTotal();
            this.direccionEnvio = response.getDireccionEnvio();
            
            System.out.println("Tarifa de envio" + this.tarifaEnvio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
