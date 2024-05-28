package Response;

public class CotizacionResponse {
    private double distancia;
    private double costoTotal;
    private double costoPerProducto;
    private double costoPerKilometro;
    private String direccionEnvio;
    private String direccionEntrega;
    private int numeroProductos;

    // Getters and Setters
    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public double getCostoPerProducto() {
        return costoPerProducto;
    }

    public void setCostoPerProducto(double costoPerProducto) {
        this.costoPerProducto = costoPerProducto;
    }

    public double getCostoPerKilometro() {
        return costoPerKilometro;
    }

    public void setCostoPerKilometro(double costoPerKilometro) {
        this.costoPerKilometro = costoPerKilometro;
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

    public int getNumeroProductos() {
        return numeroProductos;
    }

    public void setNumeroProductos(int numeroProductos) {
        this.numeroProductos = numeroProductos;
    }
}
