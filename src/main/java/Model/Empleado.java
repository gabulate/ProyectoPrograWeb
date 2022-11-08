package Model;

/**
 *
 * @author Gabri
 */
public class Empleado {
    int ID;
    String Nombre;
    int IdTipoJornada;
    float Salario;

    public Empleado(int ID, String Nombre, int IdTipoJornada, float Salario) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.IdTipoJornada = IdTipoJornada;
        this.Salario = Salario;
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

    public int getIdTipoJornada() {
        return IdTipoJornada;
    }

    public void setIdTipoJornada(int IdTipoJornada) {
        this.IdTipoJornada = IdTipoJornada;
    }

    public float getSalario() {
        return Salario;
    }

    public void setSalario(float Salario) {
        this.Salario = Salario;
    }
    
    
}
