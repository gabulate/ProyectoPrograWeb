package Controller;

import DAO.SNMPExceptions;
import Model.Beneficio;
import Model.BeneficioDB;
import Model.Bonus;
import Model.BonusDB;
import Model.Deduccion;
import Model.DeduccionDB;
import Model.DetallePlanilla;
import Model.DetallePlanillaDB;
import Model.Empleado;
import Model.EmpleadoDB;
import Model.Planilla;
import Model.PlanillaDB;
import Model.Rebajo;
import Model.RebajoDB;
import Model.TipoJornada;
import Model.TipoJornadaDB;
import Model.TipoPlanilla;
import Model.TipoPlanillaDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gabri
 */
public class beanPlanillas {

    //Propiedades para mostrar las planillas////////////
    private LinkedList<Planilla> listaPlanillas = new LinkedList<>();
    private LinkedList<DetallePlanilla> ListaDetallePlanilla;
    ////////////////////////////////////////////////////

    //Propiedades para crear una planilla nueva/////////
    private Planilla planilla = null;

    private Date FechaInicio = new Date();
    private Date FechaFinal = new Date();
    private Date FechaPago = new Date();

    private String jornada = "";
    private LinkedList<TipoJornada> listaJornadas;
    private LinkedList<Empleado> ListaEmpleados = new LinkedList<>();

    private String tipo = "";
    private LinkedList<TipoPlanilla> listaTipos;
    ///////////////////////////////////////////////////
    private String mensaje = "";

    //Genera la planilla y la guarda en la base de datos
    public void Generar() throws SNMPExceptions, SQLException, IOException {
        int idJornada = 0;

        //Obtiene el id de la jornada seleccionada
        for (int i = 0; i < listaJornadas.size(); i++) {
            if (listaJornadas.get(i).getNombre().equals(jornada)) {
                idJornada = listaJornadas.get(i).getID();
            }
        }

        int idTipo = 0;

        //Obtiene el id del tipo de planilla seleccionado
        for (int i = 0; i < listaTipos.size(); i++) {
            if (listaTipos.get(i).getNombre().equals(tipo)) {
                idTipo = listaTipos.get(i).getID();
            }
        }

        //Se tiene que convertir de util.date a sql.date
        planilla = new Planilla(0, idJornada,
                new java.sql.Date(FechaInicio.getTime()),
                new java.sql.Date(FechaFinal.getTime()),
                new java.sql.Date(FechaPago.getTime()),
                idTipo);

        new PlanillaDB().Insertar(planilla);
        CrearDetallesPlanilla();
    }

    private void CrearDetallesPlanilla() throws SNMPExceptions, SQLException, IOException{
        planilla = new PlanillaDB().getUltima();
        ListaEmpleados = new EmpleadoDB().getByJornada(planilla.getIdTipoJornada());

        //Elimina los usuarios marcados como desactivados
        for (Iterator<Empleado> it = ListaEmpleados.iterator(); it.hasNext();) {
            Empleado e = it.next();
            if (!e.isActivo()) {
                ListaEmpleados.remove(e);
            }
        }

        //Obtiene Los objetos de tipo jornada y tipo Planilla///////////////////////////
        int idJornada = 0;

        //Obtiene el id de la jornada seleccionada
        for (int i = 0; i < listaJornadas.size(); i++) {
            if (listaJornadas.get(i).getNombre().equals(jornada)) {
                idJornada = listaJornadas.get(i).getID();
            }
        }

        int idTipo = 0;
        //Obtiene el id del tipo de planilla seleccionado
        for (int i = 0; i < listaTipos.size(); i++) {
            if (listaTipos.get(i).getNombre().equals(tipo)) {
                idTipo = listaTipos.get(i).getID();
            }
        }

        TipoJornada tipoJornada = new TipoJornadaDB().getByID(idJornada);
        TipoPlanilla tipoPlanilla = new TipoPlanillaDB().getByID(idTipo);

        //Crea los Objetos de DetallePlanilla///////////////////////////////////////////
        for (Empleado e : ListaEmpleados) {
            int IdEmpleado = e.getID();
            int IdPlanilla = planilla.getID();

            float HorasTrabajadas = tipoJornada.getHoras() * tipoPlanilla.getDias();
            float SalarioBruto = (e.getSalario() * tipoPlanilla.getDias()) / 30;
            float HorasExtra = 0;
            
            //Inserta el detalle planilla sin calcular el salario bruto todavía
            new DetallePlanillaDB().Insertar(new DetallePlanilla(0, IdEmpleado, IdPlanilla, HorasTrabajadas, SalarioBruto, HorasExtra, 0));
            
            //Obtiene el detalleplanilla recién creado
            DetallePlanilla detalle = new DetallePlanillaDB().getUltima();
            
            //Obtiene las listas de deducciones y beneficios
            LinkedList<Deduccion> deducciones = new DeduccionDB().getFromIdEmpleado(e.getID());
            LinkedList<Beneficio> beneficios = new BeneficioDB().getFromIdEmpleado(e.getID());

            
            float SalarioNeto = SalarioBruto;
            
            //Calculo del salario neto del empleado///////////////////////////////////////////
            
            for(Deduccion d : deducciones){
                float total = 0;
                //Si el rebajo es una cifra fija se le resta directamente, si no
                //Se calcula el porcentaje por rebajar
                if(d.isFijo()){
                    total = d.getCantidad();
                    SalarioNeto -= d.getCantidad();
                    
                } else{
                    total = (SalarioBruto * d.getCantidad()) / 100;
                    SalarioNeto -= (SalarioBruto * d.getCantidad()) / 100;
                }
                
                //Crea un objeto rebajo y lo guarda en la base de datos
                Rebajo r = new Rebajo(0, detalle.getID(), d.getDetalle(), total);
                new RebajoDB().Insertar(r);
            }
            
            //Lo mismo pero con los beneficios se suma 
            for(Beneficio b : beneficios){
                float total = 0;
                if(b.isFijo()){
                    total = b.getCantidad();
                    SalarioNeto += b.getCantidad();
                    
                } else{
                    total = (SalarioBruto * b.getCantidad()) / 100;
                    SalarioNeto += (SalarioBruto * b.getCantidad()) / 100;
                }
                
                
                Bonus bon = new Bonus(0, detalle.getID(), b.getDetalle(), total);
                new BonusDB().Insertar(bon);
            }

            //Actualiza el detallePlanilla con el salario neto
            detalle.setSalarioNeto(SalarioNeto);
            new DetallePlanillaDB().Actualizar(detalle);
        }//Fin de foreach de empleados
        
        //Finalmente, ya creada la planilla, cada objeto detallePlanilla y
        //todos los objetos de rebajos y bonus, se vuelve a la página de lista planillas
        FacesContext.getCurrentInstance().getExternalContext().redirect("ListaPlanillas.xhtml");
    }

    //Cuando se obtiene la jornada trae a los empleados con la jornada elegida
    public LinkedList<TipoJornada> getListaJornadas() throws SNMPExceptions, SNMPExceptions, SQLException {
        listaJornadas = new TipoJornadaDB().moTodo();

        int idJornada = 0;

        //Obtiene el id de la jornada seleccionada
        for (int i = 0; i < listaJornadas.size(); i++) {
            if (listaJornadas.get(i).getNombre().equals(jornada)) {
                idJornada = listaJornadas.get(i).getID();
            }
        }

        //Obtiene la lista de empleados con la jornada seleccionada
        ListaEmpleados = new EmpleadoDB().getByJornada(idJornada);

        //Elimina los usuarios marcados como desactivados
        for (Iterator<Empleado> it = ListaEmpleados.iterator(); it.hasNext();) {
            Empleado e = it.next();
            if (!e.isActivo()) {
                ListaEmpleados.remove(e);
            }
        }

        return listaJornadas;
    }

    //Pasa a la página de creación de planilla
    public void AbrirPaginaCreacion() throws IOException {
        try {

            mensaje = "";
        } catch (Exception e) {
            mensaje = "Ha ocurrido un error al conectar con la base de datos";
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("CrearPlanilla.xhtml");
    }

    //Pasa a la página de detalle con la lista de detalles de la planilla seleccionada
    public void MostrarDetalle(Planilla planilla) throws SNMPExceptions, SQLException, IOException {
        ListaDetallePlanilla = new DetallePlanillaDB().getFromIdPlanilla(planilla.getID());

        FacesContext.getCurrentInstance().getExternalContext().redirect("VerPlanilla.xhtml");
    }

    //Trae la lista de planillas de la base de datos
    public void MostrarLista() throws SNMPExceptions, SQLException {

        this.setListaPlanillas(new PlanillaDB().moTodo());
    }

    public LinkedList<Planilla> getListaPlanillas() {
        try {
            MostrarLista();
        } catch (Exception e) {
            mensaje = "Ha ocurrido un error al conectar con la base de datos";
        }

        return listaPlanillas;
    }

    public void setListaPlanillas(LinkedList<Planilla> listaPlanillas) {
        this.listaPlanillas = listaPlanillas;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LinkedList<DetallePlanilla> getListaDetallePlanilla() {
        System.out.print(ListaDetallePlanilla.size());
        return ListaDetallePlanilla;
    }

    public void setListaDetallePlanilla(LinkedList<DetallePlanilla> ListaDetallePlanilla) {
        this.ListaDetallePlanilla = ListaDetallePlanilla;
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

    public String getJornada() {
        return jornada;
    }

    public void setListaJornadas(LinkedList<TipoJornada> listaJornadas) {
        this.listaJornadas = listaJornadas;
    }

    public LinkedList<Empleado> getListaEmpleados() {
        return ListaEmpleados;
    }

    public void setListaEmpleados(LinkedList<Empleado> ListaEmpleados) {
        this.ListaEmpleados = ListaEmpleados;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LinkedList<TipoPlanilla> getListaTipos() throws SNMPExceptions, SNMPExceptions, SQLException {
        listaTipos = new TipoPlanillaDB().moTodo();
        return listaTipos;
    }

    public void setListaTipos(LinkedList<TipoPlanilla> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public void setJornada(String jornada) throws SNMPExceptions, SNMPExceptions {
        this.jornada = jornada;
    }
}
