package Model;

/**
 *
 * @author Gabri
 */
public class Usuario {
    int ID;
    String Nombre;
    int IdRol;
    String Contrasenna;

    public Usuario(int ID, String Nombre, int IdRol, String Contrasenna) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.IdRol = IdRol;
        this.Contrasenna = Contrasenna;
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

    public String getContrasenna() {
        return Contrasenna;
    }

    public void setContrasenna(String Contrasenna) {
        this.Contrasenna = Contrasenna;
    }
    
    
}
