package Model.ClasesDB;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Entidades.Beneficio;
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

    public void InsertarOActualizar(Beneficio ben)
            throws SNMPExceptions, SQLException {
        String select = "SELECT * FROM Beneficio WHERE ID = " + ben.getID();

        try {
            AccesoDatos accesoDatos = new AccesoDatos();

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            while (rsPA.next()) {
                //Si entra aquí es porque sí existe un objeto con ese ID
                Actualizar(ben);
                rsPA.close(); //se cierra el ResultSet.
                return;
            }

            //Si llega aquí es porque el objeto no existe y se crea
            Insertar(ben);
            rsPA.close(); //se cierra el ResultSet.
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public void Insertar(Beneficio ben) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            strSQL = String.format("INSERT INTO Beneficio VALUES (%d, '%s', %f, '%s')",
                    ben.getIdEmpleado(), ben.getDetalle(), ben.getCantidad(), ben.isFijo());
            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public void Actualizar(Beneficio ben) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {

            strSQL = String.format("UPDATE Beneficio SET IdEmpleado = %d, Detalle = '%s', Cantidad = %f, Fijo = '%s' WHERE ID = %d",
                    ben.getIdEmpleado(), ben.getDetalle(), ben.getCantidad(), ben.isFijo(), ben.getID());

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public void Eliminar(Beneficio ben) throws SNMPExceptions {
        String strSQL = "SELECT * FROM Beneficio WHERE ID = " + ben.getID();

        try {
            AccesoDatos accesoDatos = new AccesoDatos();

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);

            while (rsPA.next()) {
                //Si entra aquí es porque sí existe un objeto con ese ID
                strSQL = "DELETE FROM Beneficio WHERE ID = " + ben.getID();
                accesoDatos.ejecutaSQL(strSQL);
            }
            
            rsPA.close(); //se cierra el ResultSet.
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
}
