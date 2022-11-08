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
public class DetallePlanillaDB {
    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public DetallePlanillaDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public void Insertar(DetallePlanilla detallePlanilla) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {

            /*
             * strSQL
             * =
             * "INSERT INTO VOTO_CANDIDATO(Tip_Identificacion,Num_Identificacion,Nom_Persona,Nom_Apellido1,"
             * + "Nom_Apellido2,Num_Candidato) VALUES "
             * + "(" + "'" + planilla.getTip_Identificacion() + "'" + ","
             * + "'" + planilla.getNum_Identificacion() + "'" + ","
             * + "'" + planilla.getNomPersona() + "'" + ","
             * + "'" + planilla.getNom_Apellido1() + "'" + ","
             * + "'" + planilla.getNom_Apellido2() + "'" + ","
             * + "'" + planilla.getNum_Candidato() + "'" + ")";
             */
            // Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
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

                String Detalle = rsPA.getString("Detalle");
                float HorasTrabajadas = rsPA.getFloat("HorasTrabajadas");
                float SalarioBruto = rsPA.getFloat("SalarioBruto");
                float HorasExtra = rsPA.getFloat("HorasExtra");
                float SalarioNeto = rsPA.getFloat("SalarioNeto");

                // se construye el objeto.
                DetallePlanilla detalleplanilla = new DetallePlanilla(ID, IdEmpleado, IdPlanilla, Detalle,
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
}
