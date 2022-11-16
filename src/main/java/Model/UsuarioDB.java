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
}
