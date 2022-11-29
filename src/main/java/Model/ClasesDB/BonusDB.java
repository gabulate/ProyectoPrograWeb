package Model.ClasesDB;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Entidades.Bonus;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Gabri
 */
public class BonusDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public BonusDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public void Insertar(Bonus bonus) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            strSQL = String.format("INSERT INTO Bonus VALUES ('%s', %f, %d)",
                    bonus.getDetalle(), bonus.getTotal(), bonus.getIdDetallePlanilla());
            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public LinkedList<Bonus> getFromIdDetallePlanilla(int IdDetallePlanilla) throws SNMPExceptions, SQLException {
        String select = "SELECT * FROM Bonus WHERE IdDetallePlanilla = " + IdDetallePlanilla;

        LinkedList<Bonus> lista = new LinkedList<>();

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
                Bonus bonus = new Bonus(ID, IdDetallePlanilla, Detalle, Total);

                lista.add(bonus);
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
