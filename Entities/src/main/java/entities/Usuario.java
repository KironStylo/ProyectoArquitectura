package entities;


import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Nombre de la columna en la tabla
    private Long id;
    
    @Column(name = "nombre_usuario") // Nombre de la columna en la tabla
    private String nombreUsuario;
    
    @Column(name = "rol") // Nombre de la columna en la tabla
    private String rol;
    
    @Column(name = "contrasena") // Nombre de la columna en la tabla
    private String contraseña;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pedido> pedidos;
    
    public Usuario() {
    	
    }

    public Usuario(String nombreUsuario, String rol, String contraseña) {
        this.nombreUsuario = nombreUsuario;
        this.rol = rol;
        this.contraseña = contraseña;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

    
}

