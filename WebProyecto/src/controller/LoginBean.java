package controller;

import java.io.Serializable;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.faces.context.FacesContext;
import services.UsuarioService;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String selectedRole;
	private boolean authenticated;

	private UsuarioService usuarioService;

	public LoginBean() {
		usuarioService = new UsuarioService();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(String selectedRole) {
		this.selectedRole = selectedRole;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public String login() {
		boolean autenticado = usuarioService.autenticarUsuario(selectedRole, username, password);
		System.out.println("Autenticado: " + autenticado);

		if (autenticado) {
			authenticated = true;
			if ("Usuario".equals(selectedRole)) {
				return "shop/store?faces-redirect=true"; // Redirige a la página de la tienda
			} else if ("Administrador".equals(selectedRole)) {
				System.out.println("Redirigiendo a administrador");
				return "admin/home?faces-redirect=true"; // Redirige a la página de administrador
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Login incorrecto", "Usuario o contraseña incorrectos"));
		}
		return null;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		System.out.println("Cerrando sesión");
		return "/login.xhtml";
	}

	public String crearUsuario() {
		System.out.println("Redirigiendo a crear usuario");
		return "user/crearUsuario?faces-redirect=true";
	}
	
	public String redirectToVerPedidos() {
	    return "verPedidos.xhtml?faces-redirect=true&username=" + username;
	}
	
	public String redirectToPagar() {
		System.out.println("Redirigiendo a pagar");
	    return "pago.xhtml?faces-redirect=true";
	}
	
	public String redirectToEditar() {
	    return "/user/editarDireccion.xhtml?faces-redirect=true";
	}
	
    public String redirectToWebID() {
        return "/user/ingresarWebId.xhtml?faces-redirect=true";
    }
	
	

}
