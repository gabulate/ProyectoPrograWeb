package Controller;

import java.io.IOException;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gabri
 */
public class beanIngreso {
    String usuario = "";
    String clave = "";

    String mensaje = "";

    public void Ingresar() throws IOException {    
        
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
