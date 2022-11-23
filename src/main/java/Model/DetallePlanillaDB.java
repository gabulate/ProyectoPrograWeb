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
public class DetallePlanillaDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public DetallePlanillaDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public void Insertar(DetallePlanilla detalle) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            strSQL = String.format("INSERT INTO DetallePlanilla VALUES (%d, %d, %f, %f, %f, %f)", 
                    detalle.IdEmpleado, detalle.IdPlanilla, detalle.HorasTrabajadas, detalle.SalarioBruto,
                    detalle.HorasExtra, detalle.SalarioNeto);

            // Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public DetallePlanilla getUltima() throws SNMPExceptions, SQLException {
        String select = "SELECT TOP 1 *  FROM DetallePlanilla ORDER BY ID desc";

        DetallePlanilla detallePlanilla = null;

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            //se llama el array con los proyectos
            while (rsPA.next()) {

                int ID = rsPA.getInt("ID");
                int IdEmpleado = rsPA.getInt("IdEmpleado");
                int IdPlanilla = rsPA.getInt("IdPlanilla");

                float HorasTrabajadas = rsPA.getFloat("HorasTrabajadas");
                float SalarioBruto = rsPA.getFloat("SalarioBruto");
                float HorasExtra = rsPA.getFloat("HorasExtra");
                float SalarioNeto = rsPA.getFloat("SalarioNeto");

                //se construye el objeto.
                detallePlanilla = new DetallePlanilla(ID, IdEmpleado, IdPlanilla,
                        HorasTrabajadas, SalarioBruto, HorasExtra, SalarioNeto);
            }

            rsPA.close(); //se cierra el ResultSet.

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }

        return detallePlanilla;
    }

    public LinkedList<DetallePlanilla> getFromIdPlanilla(int IdPlanilla) throws SNMPExceptions, SQLException {
        String select = "SELECT * FROM DetallePlanilla WHERE IdPlanilla = " + IdPlanilla;

        LinkedList<DetallePlanilla> lista = new LinkedList<>();

        try {
            // Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            // se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            // se llama el array con los proyectos
            while (rsPA.next()) {

                int ID = rsPA.getInt("ID");
                int IdEmpleado = rsPA.getInt("IdEmpleado");
                //int IdPlanilla = rsPA.getInt("IdPlanilla");

                float HorasTrabajadas = rsPA.getFloat("HorasTrabajadas");
                float SalarioBruto = rsPA.getFloat("SalarioBruto");
                float HorasExtra = rsPA.getFloat("HorasExtra");
                float SalarioNeto = rsPA.getFloat("SalarioNeto");

                // se construye el objeto.
                DetallePlanilla detalleplanilla = new DetallePlanilla(ID, IdEmpleado, IdPlanilla,
                        HorasTrabajadas, SalarioBruto, HorasExtra, SalarioNeto);

                lista.add(detalleplanilla);
            }

            rsPA.close(); // se cierra el ResultSet.

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return lista;
    }

    public void Actualizar(DetallePlanilla detalle) throws SNMPExceptions {
        String strSQL = "";

        try {

            strSQL = String.format("UPDATE DetallePlanilla SET IdEmpleado = %d, IdPlanilla = %d,"
                    + " HorasTrabajadas = %f, SalarioBruto = %f, HorasExtra = %f, SalarioNeto = %f"
                    + "WHERE ID = %d",
                    detalle.IdEmpleado, detalle.IdPlanilla, detalle.HorasTrabajadas, detalle.SalarioBruto,
                    detalle.HorasExtra, detalle.SalarioNeto, detalle.ID);

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
}
