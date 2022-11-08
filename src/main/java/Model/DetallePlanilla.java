package Model;

/**
 *
 * @author Gabri
 */
public class DetallePlanilla {
    int ID;
    int IdEmpleado;
    int IdPlanilla;
    
    String Detalle;
    float HorasTrabajadas;
    float SalarioBruto;
    float HorasExtra;
    float SalarioNeto;

    public DetallePlanilla(int ID, int IdEmpleado, int IdPlanilla, String Detalle, float HorasTrabajadas, float SalarioBruto, float HorasExtra, float SalarioNeto) {
        this.ID = ID;
        this.IdEmpleado = IdEmpleado;
        this.IdPlanilla = IdPlanilla;
        this.Detalle = Detalle;
        this.HorasTrabajadas = HorasTrabajadas;
        this.SalarioBruto = SalarioBruto;
        this.HorasExtra = HorasExtra;
        this.SalarioNeto = SalarioNeto;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    public int getIdPlanilla() {
        return IdPlanilla;
    }

    public void setIdPlanilla(int IdPlanilla) {
        this.IdPlanilla = IdPlanilla;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String Detalle) {
        this.Detalle = Detalle;
    }

    public float getHorasTrabajadas() {
        return HorasTrabajadas;
    }

    public void setHorasTrabajadas(float HorasTrabajadas) {
        this.HorasTrabajadas = HorasTrabajadas;
    }

    public float getSalarioBruto() {
        return SalarioBruto;
    }

    public void setSalarioBruto(float SalarioBruto) {
        this.SalarioBruto = SalarioBruto;
    }

    public float getHorasExtra() {
        return HorasExtra;
    }

    public void setHorasExtra(float HorasExtra) {
        this.HorasExtra = HorasExtra;
    }

    public float getSalarioNeto() {
        return SalarioNeto;
    }

    public void setSalarioNeto(float SalarioNeto) {
        this.SalarioNeto = SalarioNeto;
    }
    
    
}
