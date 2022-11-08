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
public class EmpleadoDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public EmpleadoDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public void Insertar(Empleado empleado) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {

            /*strSQL
                    = "INSERT INTO VOTO_CANDIDATO(Tip_Identificacion,Num_Identificacion,Nom_Persona,Nom_Apellido1,"
                    + "Nom_Apellido2,Num_Candidato) VALUES "
                    + "(" + "'" + planilla.getTip_Identificacion() + "'" + ","
                    + "'" + planilla.getNum_Identificacion() + "'" + ","
                    + "'" + planilla.getNomPersona() + "'" + ","
                    + "'" + planilla.getNom_Apellido1() + "'" + ","
                    + "'" + planilla.getNom_Apellido2() + "'" + ","
                    + "'" + planilla.getNum_Candidato() + "'" + ")";*/
            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public LinkedList<Empleado> moTodo() throws SNMPExceptions, SQLException {
        String select = "SELECT * FROM Empleado";

        LinkedList<Empleado> lista = new LinkedList<>();

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            //se llama el array con los proyectos
            while (rsPA.next()) {

                int ID = rsPA.getInt("ID");
                String Nombre = rsPA.getString("Nombre");
                int IdTipoJornada = rsPA.getInt("IdTipoJornada");
                float Salario = rsPA.getFloat("Salario");

                //se construye el objeto.
                Empleado empleado = new Empleado(ID, Nombre, IdTipoJornada, Salario);

                lista.add(empleado);
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
