package Model.ClasesDB;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Entidades.Planilla;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Gabri
 */
public class PlanillaDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public PlanillaDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public void Insertar(Planilla planilla) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            strSQL = String.format("INSERT INTO Planilla VALUES (%d, '%s', '%s', '%s', %d)",
                    planilla.getIdTipoJornada(), planilla.getFechaInicio().toString(), planilla.getFechaFinal().toString(),
                    planilla.getFechaPago().toString(), planilla.getIdTipoPlanilla());

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public Planilla getUltima() throws SNMPExceptions, SQLException {
        String select = "SELECT TOP 1 *  FROM Planilla ORDER BY ID desc";

        Planilla planilla = null;

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            //se llama el array con los proyectos
            while (rsPA.next()) {

                int ID = rsPA.getInt("ID");
                int IdJornada = rsPA.getInt("IdTipoJornada");

                Date FechaInicio = rsPA.getDate("FechaInicio");
                Date FechaFinal = rsPA.getDate("FechaFinal");
                Date FechaPago = rsPA.getDate("FechaPago");
                int IdTipoPlanilla = rsPA.getInt("IdTipoPlanilla");

                //se construye el objeto.
                planilla = new Planilla(ID, IdJornada, FechaInicio, FechaFinal, FechaPago, IdTipoPlanilla);
            }

            rsPA.close(); //se cierra el ResultSet.

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }

        return planilla;
    }

    public LinkedList<Planilla> moTodo() throws SNMPExceptions, SQLException {
        String select = "SELECT Planilla.ID, IdTipoJornada, FechaInicio, FechaFinal, FechaPago, IdTipoPlanilla,\n"
                + "TipoJornada.Nombre as 'Jornada', TipoPlanilla.Nombre as 'Tipo' FROM Planilla, TipoJornada, TipoPlanilla \n"
                + "WHERE (IdTipoJornada = TipoJornada.ID) and (TipoPlanilla.ID = IdTipoPlanilla)\n"
                + "order by Planilla.ID desc";

        LinkedList<Planilla> listaPlanilla = new LinkedList<>();

        try {
            //Se intancia la clase de acceso a datos
            accesoDatos = new AccesoDatos();

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            //se llama el array con los proyectos
            while (rsPA.next()) {

                int ID = rsPA.getInt("ID");
                int IdJornada = rsPA.getInt("IdTipoJornada");

                Date FechaInicio = rsPA.getDate("FechaInicio");
                Date FechaFinal = rsPA.getDate("FechaFinal");
                Date FechaPago = rsPA.getDate("FechaPago");
                int IdTipoPlanilla = rsPA.getInt("IdTipoPlanilla");

                String jornada = rsPA.getString("Jornada");
                String tipo = rsPA.getString("Tipo");

                //se construye el objeto.
                Planilla planilla = new Planilla(ID, IdJornada, FechaInicio, FechaFinal, FechaPago, IdTipoPlanilla, jornada, tipo);

                listaPlanilla.add(planilla);
            }

            rsPA.close(); //se cierra el ResultSet.

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }

        return listaPlanilla;
    }

    public Planilla getById(int idPlanilla) throws SNMPExceptions {
        String select = "SELECT * FROM Planilla WHERE ID = " + idPlanilla;

        Planilla planilla = null;

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            //se llama el array con los proyectos
            while (rsPA.next()) {

                int ID = rsPA.getInt("ID");
                int IdJornada = rsPA.getInt("IdTipoJornada");

                Date FechaInicio = rsPA.getDate("FechaInicio");
                Date FechaFinal = rsPA.getDate("FechaFinal");
                Date FechaPago = rsPA.getDate("FechaPago");
                int IdTipoPlanilla = rsPA.getInt("IdTipoPlanilla");

                //se construye el objeto.
                planilla = new Planilla(ID, IdJornada, FechaInicio, FechaFinal, FechaPago, IdTipoPlanilla);
            }

            rsPA.close(); //se cierra el ResultSet.

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }

        return planilla;
    }
}
