package Model;

/**
 *
 * @author Gabri
 */
public class Rebajo {
    int ID;
    int IdDetallePlanilla;
    String Detalle;
    float Total;

    public Rebajo(int ID, int IdDetallePlanilla, String Detalle, float Total) {
        this.ID = ID;
        this.IdDetallePlanilla = IdDetallePlanilla;
        this.Detalle = Detalle;
        this.Total = Total;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdDetallePlanilla() {
        return IdDetallePlanilla;
    }

    public void setIdDetallePlanilla(int IdDetallePlanilla) {
        this.IdDetallePlanilla = IdDetallePlanilla;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String Detalle) {
        this.Detalle = Detalle;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }
}
