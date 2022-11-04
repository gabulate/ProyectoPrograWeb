/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        String select = "SELECT Num_Candidato,COUNT(Con_Voto) as Cantidad FROM VOTO_CANDIDATO group by Num_Candidato";

        LinkedList<Planilla> listaVoto = new LinkedList<Planilla>();

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();
            
            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            
            //se llama el array con los proyectos
            while (rsPA.next()) {

                /*int numCand = rsPA.getInt("Num_Candidato");

                int cantidad = rsPA.getInt("Cantidad");

                //se construye el objeto.
                Voto perVoto = new Voto(numCand, cantidad);

                listaVoto.add(perVoto);*/
            }
            
            rsPA.close();//se cierra el ResultSeat.

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return listaVoto;
    }
}
