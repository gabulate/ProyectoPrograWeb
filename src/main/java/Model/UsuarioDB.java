package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gabri
 */
public class UsuarioDB {

    public boolean VerificarUsuario(String Usuario, String Contrasenna) throws SNMPExceptions {
        String select = String.format("EXECUTE VerificarLogin @Usuario = '%s', @Contrasenna = '%s'", Usuario, Contrasenna);

        try {
            AccesoDatos accesoDatos = new AccesoDatos();

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            while (rsPA.next()) {
                return rsPA.getBoolean("Aprobado");
            }

            //se construye el objeto.
            rsPA.close(); //se cierra el ResultSet.
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }

        return false;
    }

    public Usuario getByNombre(String nombre) throws SNMPExceptions, SQLException {
        String select = "SELECT * FROM Usuario WHERE Nombre = '" + nombre + "'";

        Usuario usuario = null;

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            while (rsPA.next()) {
                int ID = rsPA.getInt("ID");
                String Nombre = rsPA.getString("Nombre");
                int IdRol = rsPA.getInt("IdRol");

                usuario = new Usuario(ID, Nombre, IdRol);
            }

            //se construye el objeto.
            rsPA.close(); //se cierra el ResultSet.

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return usuario;
    }
}
