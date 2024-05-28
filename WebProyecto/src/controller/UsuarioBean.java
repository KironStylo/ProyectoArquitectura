package controller;

import java.io.Serializable;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import jakarta.faces.context.FacesContext;
import services.UsuarioService;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private String nombreUsuario;
    private String contraseña;
    private String confirmarContraseña;
    private String direccion;
    
    private UsuarioService usuarioService;
    
    public UsuarioBean() {
    	usuarioService = new UsuarioService();
	}
    

    // Getters y Setters

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getConfirmarContraseña() {
        return confirmarContraseña;
    }

    public void setConfirmarContraseña(String confirmarContraseña) {
        this.confirmarContraseña = confirmarContraseña;
    }

    // Método para crear un nuevo usuario
    public String crearUsuario() {
    	
    	System.out.println("Se va a crear un usuario");
    
    	
        // Validar que las contraseñas coincidan
        if (!contraseña.equals(confirmarContraseña)) {
            FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden"));
            return null;
        }

        // Validar que la contraseña tenga al menos 8 caracteres y al menos una mayúscula
        if (contraseña.length() < 8 || !contraseña.matches(".*[A-Z].*")) {
        	System.out.println("No cumple condciones de las contraseñas");
            FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La contraseña debe tener al menos 8 caracteres y contener al menos una mayúscula"));
            return null;
        }

        // Intentar crear el usuario
        boolean creado = usuarioService.crearUsuario("Usuario", nombreUsuario, contraseña);

        if (creado) {
            // Usuario creado con éxito, puedes redirigir a alguna página de éxito
            return "exito";
        } else {
            // El usuario ya existe en la base de datos
            FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El usuario ya existe"));
            return null;
        }
    }


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}

