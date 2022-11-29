package Model.Entidades;

/**
 *
 * @author Gabri
 */
public class TramoRenta {
    int ID;
    float ExentoHasta;
    float Tarifa;

    public TramoRenta(int ID, float ExcentoHasta, float Tarifa) {
        this.ID = ID;
        this.ExentoHasta = ExcentoHasta;
        this.Tarifa = Tarifa;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getExentoHasta() {
        return ExentoHasta;
    }

    public void setExentoHasta(float ExentoHasta) {
        this.ExentoHasta = ExentoHasta;
    }

    public float getTarifa() {
        return Tarifa;
    }

    public void setTarifa(float Tarifa) {
        this.Tarifa = Tarifa;
    }
    
    
}
