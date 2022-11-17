package Model;

import DAO.SNMPExceptions;
import java.sql.SQLException;

/**
 *
 * @author Gabri
 */
public class Usuario {
    int ID;
    String Nombre;
    int IdRol;
    
    String Rol;

    public Usuario(int ID, String Nombre, int IdRol) throws SNMPExceptions, SQLException {
        this.ID = ID;
        this.Nombre = Nombre;
        this.IdRol = IdRol;
        
        this.Rol = new RolDB().getByID(IdRol).Nombre;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int IdRol) {
        this.IdRol = IdRol;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }
    
    
}
