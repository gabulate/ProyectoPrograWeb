package Model;

import java.sql.Date;

/**
 *
 * @author Gabri
 */
public class Planilla {
    int ID;
    int IdTipoJornada;
    Date FechaInicio;
    Date FechaFinal;
    Date FechaPago;

    public Planilla(int ID, int IdTipoJornada, Date FechaInicio, Date FechaFinal, Date FechaPago) {
        this.ID = ID;
        this.IdTipoJornada = IdTipoJornada;
        this.FechaInicio = FechaInicio;
        this.FechaFinal = FechaFinal;
        this.FechaPago = FechaPago;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdTipoJornada() {
        return IdTipoJornada;
    }

    public void setIdTipoJornada(int IdTipoJornada) {
        this.IdTipoJornada = IdTipoJornada;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public Date getFechaFinal() {
        return FechaFinal;
    }

    public void setFechaFinal(Date FechaFinal) {
        this.FechaFinal = FechaFinal;
    }

    public Date getFechaPago() {
        return FechaPago;
    }

    public void setFechaPago(Date FechaPago) {
        this.FechaPago = FechaPago;
    }
}
