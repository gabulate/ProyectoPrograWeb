package Model;

/**
 *
 * @author Gabri
 */
public class TipoPlanilla {
    int ID = 0;
    String Nombre;
    int dias;

    public TipoPlanilla(int ID, String Nombre, int dias) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.dias = dias;
    }

    public TipoPlanilla() {
    }

    @Override
    public String toString() {
        return Nombre;
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

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
}
