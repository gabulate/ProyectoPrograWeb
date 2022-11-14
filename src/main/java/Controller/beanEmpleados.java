package Controller;

import DAO.SNMPExceptions;
import Model.Beneficio;
import Model.BeneficioDB;
import Model.Deduccion;
import Model.DeduccionDB;
import Model.Empleado;
import Model.EmpleadoDB;
import Model.TipoJornada;
import Model.TipoJornadaDB;
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

    private String jornada = "";
    private LinkedList<TipoJornada> listaJornadas;

    private LinkedList<Deduccion> listaDeducciones;
    private LinkedList<Beneficio> listaBeneficios;

    private String mensaje = "";

    //Pasa el empleado seleccionado y abre la página de editar
    public void Editar(Empleado empleado) throws IOException {
        this.empleado = empleado;

        try {
            MostrarDeduccionesYBeneficios();
            mensaje = "";
        } catch (Exception e) {
            mensaje = "Ha ocurrido un error al conectar con la base de datos";
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("EditarEmpleado.xhtml");
    }

    //Guarda los cambios realizados en el empleado y sus deducciones y beneficios
    public void Guardar() throws SNMPExceptions, SQLException {
        new EmpleadoDB().Actualizar(empleado);
    }

    //Vuelve a la página de la lista de empleados
    public void Cancelar() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("AdministrarEmpleados.xhtml");
    }

    public void MostrarListaEmpleados() throws SNMPExceptions, SQLException {
        this.setListaEmpleados(new EmpleadoDB().moTodo());
    }

    public LinkedList<Empleado> getListaEmpleados() {
        try {
            MostrarListaEmpleados();
            mensaje = "";
        } catch (Exception e) {
            mensaje = "Ha ocurrido un error al conectar con la base de datos";
        }

        return listaEmpleados;
    }

    public void AnadirEmpleado() throws SNMPExceptions, SQLException{
        new EmpleadoDB().Insertar(new Empleado(0, "Empleado Nuevo", 1, 0, true));
        MostrarListaEmpleados();
    }
    
    public void AnadirDeduccion() {
        this.listaDeducciones.add(new Deduccion(0, empleado.getID(), "Nuevo cobro", 0, false));
    }

    public void AnadirBeneficio() {
        this.listaBeneficios.add(new Beneficio(0, empleado.getID(), "Nuevo beneficio", 0, false));
    }

    public void MostrarDeduccionesYBeneficios() throws SNMPExceptions, SQLException {
        this.setListaDeducciones(new DeduccionDB().getFromIdEmpleado(this.empleado.getID()));
        this.setListaBeneficios(new BeneficioDB().getFromIdEmpleado(this.empleado.getID()));
    }

    public LinkedList<Deduccion> getListaDeducciones() {

        return listaDeducciones;
    }

    public LinkedList<Beneficio> getListaBeneficios() {
        return listaBeneficios;
    }

    public void setListaBeneficios(LinkedList<Beneficio> listaBeneficios) {
        this.listaBeneficios = listaBeneficios;
    }

    public void setListaDeducciones(LinkedList<Deduccion> listaDeducciones) {
        this.listaDeducciones = listaDeducciones;
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

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public LinkedList<TipoJornada> getListaJornadas() throws SNMPExceptions, SQLException {
        listaJornadas = new TipoJornadaDB().moTodo();

        return listaJornadas;
    }

    public void setListaJornadas(LinkedList<TipoJornada> listaJornadas) {
        this.listaJornadas = listaJornadas;
    }

}
