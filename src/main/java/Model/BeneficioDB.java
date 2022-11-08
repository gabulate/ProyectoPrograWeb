package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Gabri
 */
public class BeneficioDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public BeneficioDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public LinkedList<Beneficio> getFromIdEmpleado(int IdEmpleado) throws SNMPExceptions, SQLException {
        String select = "SELECT * FROM Beneficio WHERE IdEmpleado = " + IdEmpleado;

        LinkedList<Beneficio> lista = new LinkedList<>();

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            //se llama el array con los proyectos
            while (rsPA.next()) {

                int ID = rsPA.getInt("ID");
                String Detalle = rsPA.getString("Detalle");
                float Cantidad = rsPA.getFloat("Cantidad");
                boolean Fijo = rsPA.getBoolean("Fijo");

                //se construye el objeto.
                Beneficio beneficio = new Beneficio(ID, IdEmpleado, Detalle, Cantidad, Fijo);

                lista.add(beneficio);
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
