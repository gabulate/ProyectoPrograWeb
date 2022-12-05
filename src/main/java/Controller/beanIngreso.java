package Controller;

import DAO.SNMPExceptions;
import Model.Entidades.Usuario;
import Model.ClasesDB.UsuarioDB;
import java.io.IOException;
import java.sql.SQLException;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gabri
 */
public class beanIngreso {

    String nombre = "";
    String clave = "";

    String mensaje = "";

    static Usuario usuario = null;

    public void Ingresar() throws IOException, SNMPExceptions, SQLException {
        usuario = null;
        if (new UsuarioDB().VerificarUsuario(nombre, clave)) {
            usuario = new UsuarioDB().getByNombre(nombre);
            beanUsuarios.usuario = null;
            FacesContext.getCurrentInstance().getExternalContext().redirect("ListaPlanillas.xhtml");
        } else {
            mensaje = "Credenciales incorrectas.";
        }
    }

    public Usuario getUsuario() {
        
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
