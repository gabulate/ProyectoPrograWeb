/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import com.sun.mail.imap.protocol.ID;
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

    public void InsertarPlanilla(Planilla planilla) throws SNMPExceptions, SQLException {
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

    public LinkedList<Planilla> moTodo() throws SNMPExceptions, SQLException {
        String select = "SELECT * FROM Planilla";

        LinkedList<Planilla> listaPlanilla = new LinkedList<Planilla>();

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

                //se construye el objeto.
                Planilla planilla = new Planilla(ID, IdJornada, FechaInicio, FechaFinal, FechaPago);

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
}
