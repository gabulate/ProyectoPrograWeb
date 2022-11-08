package Controller;

import DAO.SNMPExceptions;
import Model.DetallePlanilla;
import Model.DetallePlanillaDB;
import Model.Planilla;
import Model.PlanillaDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gabri
 */
public class beanListaPlanilla {

    private LinkedList<Planilla> listaPlanillas = new LinkedList<>();

    private LinkedList<DetallePlanilla> ListaDetallePlanilla = new LinkedList<>();

    private String mensaje = "";

    //Pasa a la página de detalle con la lista de detalles de la planilla seleccionada
    public void MostrarDetalle(Planilla planilla) throws SNMPExceptions, SQLException, IOException {
        this.setListaDetallePlanilla(new DetallePlanillaDB().getFromIdPlanilla(planilla.getID()));

        FacesContext.getCurrentInstance().getExternalContext().redirect("VerPlanilla.xhtml");
    }

    //Trae la lista de planillas de la base de datos
    public void MostrarLista() throws SNMPExceptions, SQLException {

        this.setListaPlanillas(new PlanillaDB().moTodo());
    }

    public LinkedList<Planilla> getListaPlanillas() {
        try {
            MostrarLista();
        } catch (Exception e) {
            mensaje = "Ha ocurrido un error al conectar con la base de datos";
        }

        return listaPlanillas;
    }

    public void setListaPlanillas(LinkedList<Planilla> listaPlanillas) {
        this.listaPlanillas = listaPlanillas;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LinkedList<DetallePlanilla> getListaDetallePlanilla() {
        return ListaDetallePlanilla;
    }

    public void setListaDetallePlanilla(LinkedList<DetallePlanilla> ListaDetallePlanilla) {
        this.ListaDetallePlanilla = ListaDetallePlanilla;
    }
}
