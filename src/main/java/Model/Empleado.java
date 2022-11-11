package Model;

import DAO.SNMPExceptions;
import java.sql.SQLException;

/**
 *
 * @author Gabri
 */
public class Empleado {
    int ID;
    String Nombre;
    int IdTipoJornada;
    float Salario;
    
    String jornada;

    public Empleado(int ID, String Nombre, int IdTipoJornada, float Salario) throws SNMPExceptions, SQLException {
        this.ID = ID;
        this.Nombre = Nombre;
        this.IdTipoJornada = IdTipoJornada;
        this.Salario = Salario;
        
        jornada = new TipoJornadaDB().getByID(IdTipoJornada).Nombre;
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

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }
    
    
}
