package Controller;

import DAO.SNMPExceptions;
import Model.Empleado;
import Model.EmpleadoDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gabri
 */
public class beanEmpleados {
    private LinkedList<Empleado> listaEmpleados = new LinkedList<>();

    private Empleado empleado;

    private String mensaje = "";
    
    public void Editar(Empleado empleado) throws IOException{
        this.empleado = empleado;

        FacesContext.getCurrentInstance().getExternalContext().redirect("EditarEmpleado.xhtml");
    }
    
    public void MostrarLista() throws SNMPExceptions, SQLException {

        this.setListaEmpleados(new EmpleadoDB().moTodo());
    }

    public LinkedList<Empleado> getListaEmpleados() {
        try {
            MostrarLista();
        } catch (Exception e) {
            mensaje = "Ha ocurrido un error al conectar con la base de datos";
        }

        return listaEmpleados;
    }

    public void setListaEmpleados(LinkedList<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}