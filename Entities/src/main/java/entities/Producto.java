package entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Nombre de la columna en la tabla
    private Long id;
    
    @Column(name = "nombre") // Nombre de la columna en la tabla
    private String nombre;
    
    @Column(name = "descripcion") // Nombre de la columna en la tabla
    private String descripcion;
    
    @Column(name = "categoria") // Nombre de la columna en la tabla
    private String categoria;
    
    @Column(name = "precio") // Nombre de la columna en la tabla
    private double precio;
    
    @Column(name = "cantidad") // Nombre de la columna en la tabla
    private int cantidad;
    
    @Column(name = "imagen_url") // Nombre de la columna en la tabla
    private String imagenUrl;
    
    @Column(name = "activo")
    private boolean activo = true;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DetallePedido> detalles;
    
    public Producto() {
    	
    }
    
    public Producto(String nombre, String descripcion, String categoria, double precio, int cantidad, String imagenUrl, boolean activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagenUrl = imagenUrl;
        this.activo =  activo;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public List<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}

