package controller;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import java.io.Serializable;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import services.PodService;

@SuppressWarnings("deprecation")
@ManagedBean(name = "webIDBean")
@SessionScoped
public class WebIDBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String webID;
    private String podUrl;
    private String nuevaDireccion;

    // Getter y setter para webID, podUrl y nuevaDireccion

    public String getWebID() {
        return webID;
    }

    public void setWebID(String webID) {
        this.webID = webID;
    }

    public String getPodUrl() {
        return podUrl;
    }

    public void setPodUrl(String podUrl) {
        this.podUrl = podUrl;
    }

    public String getNuevaDireccion() {
        return nuevaDireccion;
    }

    public void setNuevaDireccion(String nuevaDireccion) {
        this.nuevaDireccion = nuevaDireccion;
    }

    public void ingresar() {
        try {
            PodService podService = new PodService();
            podUrl = podService.obtenerPodUrl(webID);

            // Crear la dirección en el POD
            podService.crearDireccionPod(podUrl, nuevaDireccion);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "WebID ingresado y dirección creada", "El WebID se ha ingresado correctamente. URL obtenida: " + podUrl));

            // Extraer el nombre de usuario del WebID
            String[] webIDParts = webID.split("/");
            String username = webIDParts[webIDParts.length - 1];

            // Redirigir a la página de creación de usuario
            FacesContext.getCurrentInstance().getExternalContext().redirect("crearUsuario.xhtml?username=" + username);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "No se pudo obtener la URL del POD o crear la nueva dirección: " + e.getMessage()));
        }
    }
}
