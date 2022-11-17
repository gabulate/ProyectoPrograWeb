package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gabri
 */
public class RolDB {
    public Rol getByID(int id) throws SNMPExceptions, SQLException {
        String select = "SELECT * FROM Rol WHERE ID =" + id;

        Rol rol = null;

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            while (rsPA.next()) {
                int ID = rsPA.getInt("ID");
                String Nombre = rsPA.getString("Nombre");

                rol = new Rol(ID, Nombre);
            }

            //se construye el objeto.
            rsPA.close(); //se cierra el ResultSet.

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return rol;
    }
}
