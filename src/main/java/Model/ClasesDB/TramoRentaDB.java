package Model.ClasesDB;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Entidades.TramoRenta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Gabri
 */
public class TramoRentaDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public TramoRentaDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public LinkedList<TramoRenta> moTodo() throws SNMPExceptions, SQLException {
        String select = "SELECT * FROM TramoRenta ORDER BY ExentoHasta asc";

        LinkedList<TramoRenta> lista = new LinkedList<>();

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            //se llama el array con los proyectos
            while (rsPA.next()) {

                int ID = rsPA.getInt("ID");
                float ExentoHasta = rsPA.getFloat("ExentoHasta");
                float Tarifa = rsPA.getFloat("Tarifa");

                //se construye el objeto.
                TramoRenta tramo = new TramoRenta(ID, ExentoHasta, Tarifa);

                lista.add(tramo);
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
