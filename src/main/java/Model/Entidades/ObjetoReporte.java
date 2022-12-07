package Model.Entidades;

/**
 *
 * @author Gabri
 */
public class ObjetoReporte {
    Empleado empleado;
    DetallePlanilla detalle;

    public ObjetoReporte(Empleado empleado, DetallePlanilla detalle) {
        this.empleado = empleado;
        this.detalle = detalle;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public DetallePlanilla getDetalle() {
        return detalle;
    }

    public void setDetalle(DetallePlanilla detalle) {
        this.detalle = detalle;
    }
    
    
}
