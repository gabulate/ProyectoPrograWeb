package Controller;

import DAO.SNMPExceptions;
import Model.Planilla;
import Model.PlanillaDB;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Gabri
 */
public class beanListaPlanilla {

    private LinkedList<Planilla> listaPlanillas = new LinkedList<>();

    public void MostrarLista() throws SNMPExceptions, SQLException {
        //Trae la lista de planillas de la base de datos
        this.setListaPlanillas(new PlanillaDB().moTodo());
    }

    public LinkedList<Planilla> getListaPlanillas() {
        return listaPlanillas;
    }

    public void setListaPlanillas(LinkedList<Planilla> listaPlanillas) {
        this.listaPlanillas = listaPlanillas;
    }
}
