package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Gabri
 */
public class RebajoDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public RebajoDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }
    
    public void Insertar(Rebajo rebajo) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            strSQL = String.format("INSERT INTO Rebajo VALUES (%d, '%s', %f)",
                    rebajo.IdDetallePlanilla, rebajo.Detalle, rebajo.Total);
            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public LinkedList<Rebajo> getFromIdDetallePlanilla(int IdDetallePlanilla) throws SNMPExceptions, SQLException {
        String select = "SELECT * FROM Rebajo WHERE IdDetallePlanilla = " + IdDetallePlanilla;

        LinkedList<Rebajo> lista = new LinkedList<>();

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            //se llama el array con los proyectos
            while (rsPA.next()) {
                int ID = rsPA.getInt("ID");
                String Detalle = rsPA.getString("Detalle");
                float Total = rsPA.getFloat("Total");

                //se construye el objeto.
                Rebajo rebajo = new Rebajo(ID, IdDetallePlanilla, Detalle, Total);

                lista.add(rebajo);
            }

            rsPA.close(); //se cierra el ResultSet.

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return lista;
    }
}
