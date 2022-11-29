package Model.ClasesDB;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Entidades.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Gabri
 */
public class UsuarioDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public UsuarioDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

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

    public Usuario getByID(int ID) throws SNMPExceptions {
        String select = "SELECT * FROM Usuario WHERE ID = " + ID;

        Usuario usuario = null;

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            while (rsPA.next()) {
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

    public LinkedList<Usuario> moTodo() throws SNMPExceptions {
        String select = "SELECT * FROM Usuario order by ID desc";

        LinkedList<Usuario> lista = new LinkedList<>();

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            //se llama el array con los proyectos
            while (rsPA.next()) {

                int ID = rsPA.getInt("ID");
                String Nombre = rsPA.getString("Nombre");
                int IdRol = rsPA.getInt("IdRol");

                Usuario usuario = new Usuario(ID, Nombre, IdRol);
                lista.add(usuario);
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

    public void Actualizar(Usuario usuario) throws SNMPExceptions {
        String strSQL = "";

        try {

            strSQL = String.format("UPDATE Usuario SET Nombre = '%s', IdRol = %d WHERE ID = %d",
                    usuario.getNombre(), usuario.getIdRol(), usuario.getID());

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public boolean Insertar(Usuario usuario, String contrasenna) throws SNMPExceptions {
        String select = String.format("EXECUTE CrearUsuario @Usuario = '%s', @IdRol = %d, @Contrasenna = '%s'",
                usuario.getNombre(), usuario.getIdRol(), contrasenna);

        try {
            AccesoDatos accesoDatos = new AccesoDatos();

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            while (rsPA.next()) {
                return rsPA.getBoolean("Aprobado");
            }

            rsPA.close(); //se cierra el ResultSet.
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }

        return false;
    }

    //Utiliza el stored procedure de la base de datos, retorna true si se cambió exitosamente
    public boolean CambiarContra(int ID, String vieja, String nueva) throws SNMPExceptions {
        String select = String.format("EXECUTE CambiarContrasenna @ID = %d, "
                + "@ContrasennaVieja = '%s', @ContrasennaNueva = '%s'",
                ID, vieja, nueva);

        try {
            AccesoDatos accesoDatos = new AccesoDatos();

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            while (rsPA.next()) {
                return rsPA.getBoolean("Aprobado");
            }

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
