/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.mbsesion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FlowEvent;
import sv.ues.dao.LotesDao;
import sv.ues.dao.MantoLoteDao;
import sv.ues.dominio.BitacoraLab;
import sv.ues.dominio.Lote;
import sv.ues.dominio.Mantenimiento;
import sv.ues.dominio.Muestra;
import sv.ues.dominio.Preservante;

/**
 *
 * @author Flor Rivas
 */
@ManagedBean(name = "MbLote")
@ViewScoped
public class MbLote implements Serializable {

    private Lote lote;
    private Mantenimiento manto;
    private Muestra muestra;
    private Integer cod_preservante;//
    private Integer cod_vector;
    private Integer correlativo=0;//Solo para mostrar al usuario
    private Lote loteSeleccionado;//Para ser usado en consultar lotes
    private Integer correlativo_muestra;
    private Lote modLote;
    private Mantenimiento modMantoLote;
    private Integer modCodPreservante;

   

    public MbLote() {
        lote = new Lote();
        manto = new Mantenimiento();
        muestra = new Muestra();
        loteSeleccionado = new Lote();
        correlativo_muestra=0;
        modLote = new Lote();
        modMantoLote = new Mantenimiento();

    }

    //Este metodo controla el flujo del wizard de registro
    public String flujoResgistrar(FlowEvent event) throws Exception {
        if (event.getOldStep().equals("general") && event.getNewStep().equals("confirmar")) {
            if (falla_validar_ingreso_lote())
            {
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "El lote ya existe"));
                return event.getOldStep();
            } else {
                return event.getNewStep();
            }
        }
        return event.getNewStep();
    }
    
public String flujoModificar(FlowEvent event) throws Exception {
        if (event.getOldStep().equals("general") && event.getNewStep().equals("confirmar")) {
            if (falla_validar_Modificar_lote()) {
                return event.getOldStep();
            } else {
                return event.getNewStep();
            }
        }
        return event.getNewStep();
    }
    private boolean falla_validar_Modificar_lote() {
        modLote.setFechaModificacion(new Date());
            if (existe_otro_lote_asi(modLote)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "Lote ya existe"));
                return true;
            } else {
                if (modLote.getNumMuestras() == null || modLote.getNumMuestras() == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "Numero de muestras no puede ser cero o vacio"));
                    return true;
                } else {
                    if (modCodPreservante == null) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "Seleccione un preservante"));
                        return true;
                    } else {
                        if (modLote.getIdVector() == null) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "Seleccione un vector para la muestra"));
                            return true;
                        } else {
                            if (modMantoLote.getFechaProxManto()==null) {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "Seleccione la fecha"));
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
    }
    public void modificar_lote(){
        LotesDao lDao = new LotesDao();
        MantoLoteDao mDao = new MantoLoteDao();
        
        Preservante prs = new Preservante();

        prs.setIdPreservante(modCodPreservante);
        modMantoLote.setPreservante(prs);
        modLote.setNombreLote(modLote.getNombreLote().toUpperCase());
        lDao.modificar_lote(modLote);//modifica lote
        manto.setLote(modLote);//Crea fk en Mantenimiento
        mDao.modificar_manto(modMantoLote);
        
        setModLote(new Lote());
        setModMantoLote(new Mantenimiento());
        setModCodPreservante(0); 
        setCorrelativo_muestra(0);
        
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('dialogoModificar').hide();");
        PrimeFaces.current().ajax().update("lotesRegistrados");
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Lote modificado con Exito"));
    }
    

    public Lote getModLote() {
        return modLote;
    }

    public void setModLote(Lote modLote) {
        this.modLote = modLote;
        /* se recupera el manto ultimo dado al lote*/
        MantoLoteDao mlDao = new MantoLoteDao();
        Mantenimiento m = new Mantenimiento();
        m = mlDao.ultimo_manto_de_lote(modLote.getIdLote());
        if(m!=null){
            setModMantoLote(m);
            setModCodPreservante(getModMantoLote().getPreservante().getIdPreservante());
        }
    }

    public Mantenimiento getModMantoLote() {
        return modMantoLote;
    }

    public void setModMantoLote(Mantenimiento modMantoLote) {
        this.modMantoLote = modMantoLote;
    }

    public Integer getModCodPreservante() {
        return modCodPreservante;
    }

    public void setModCodPreservante(Integer modCodPreservante) {
        this.modCodPreservante = modCodPreservante;
    }
    

    public Integer getCorrelativo_muestra() {
        return correlativo_muestra = correlativo_muestra +1;
    }

    public void setCorrelativo_muestra(Integer correlativo_muestra) {
        this.correlativo_muestra = correlativo_muestra;
    }
    

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Mantenimiento getManto() {
        return manto;
    }

    public void setManto(Mantenimiento manto) {
        this.manto = manto;
    }

    public Muestra getMuestra() {
        return muestra;
    }

    public void setMuestra(Muestra muestra) {
        this.muestra = muestra;
    }

    public Integer getCod_preservante() {
        return cod_preservante;
    }

    public void setCod_preservante(Integer cod_preservante) {
        this.cod_preservante = cod_preservante;
    }

    public Integer getCod_vector() {
        return cod_vector;
    }

    public void setCod_vector(Integer cod_vector) {
        this.cod_vector = cod_vector;
    }

    public Integer getCorrelativo() {
        return correlativo=correlativo+1;
    }

    public void setCorrelativo(Integer correlativo) {
        this.correlativo = correlativo;
    }

    public Lote getLoteSeleccionado() {
        return loteSeleccionado;
    }

    public void setLoteSeleccionado(Lote loteSeleccionado) {
        this.loteSeleccionado = loteSeleccionado;
        //correlativo_muestra=0;
        setCorrelativo_muestra(0);//pone correlativo a 0, 
    }

    public void registrar_lote() {
        LotesDao lDao = new LotesDao();
        MantoLoteDao mDao = new MantoLoteDao();
        lote.setIdVector(cod_vector);
        lote.setNombreLote(lote.getNombreLote().toUpperCase());
        Preservante prs = new Preservante();

        prs.setIdPreservante(cod_preservante);
        manto.setPreservante(prs);
        manto.setCompletadoManto(true);
        manto.setFechaManto(lote.getFechaCreacion());
        manto.setFechaProxManto(lote.getFechaModificacion());
        
        Lote nlote = lDao.registrar_nuevo_lote(lote);//Se registra lote y se obtiene dicho objeto
        manto.setLote(nlote);//Crea fk en Mantenimiento
        mDao.registrar_nuevo_manto(manto);//Guarda el nuevo mantenimiento
        
        resetVariables();
        PrimeFaces.current().ajax().update("F01:registro");
        PrimeFaces.current().ajax().update("F01");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Lote registrado con Exito"));
    }

    private boolean falla_validar_ingreso_lote() {
        lote.setFechaCreacion(new Date());
            if (existe_este_lote()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "Lote ya existe"));
                return true;
            } else {
                if (lote.getNumMuestras() == null || lote.getNumMuestras() == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "Numero de muestras no puede ser cero o vacio"));
                    return true;
                } else {
                    if (cod_preservante == null) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "Seleccione un preservante"));
                        return true;
                    } else {
                        if (cod_vector == null) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "Seleccione un vector para la muestra"));
                            return true;
                        } else {
                            if (lote.getFechaCreacion() == null || lote.getFechaModificacion() == null) {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "Seleccione la fecha"));
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
        
    }
    
    private boolean existe_este_lote() {
        LotesDao lotesDao = new LotesDao();
        try {
            return lotesDao.existe_lote(lote.getNombreLote().toUpperCase());
        } catch (Exception x) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, x.toString(), x.toString()));
            return true;
        }
    }

    public List<Lote> lista_lote() {
        LotesDao lotesDao = new LotesDao();
        try {
            return lotesDao.obtener_lotes();
        } catch (Exception x) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, x.toString(), x.toString()));
            return null;
        }
    }
    
    public List<Lote> lista_lote_activos_inactivos(Integer estado) {
        LotesDao lotesDao = new LotesDao();
        try {
            return lotesDao.obtener_lotes_activos_inactivos(estado);
        } catch (Exception x) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, x.toString(), x.toString()));
            return null;
        }
    }
    
    public Lote lote_por_id(Integer idLote){
         LotesDao lotesDao = new LotesDao();
        try {
            return lotesDao.lote_por_id(idLote);
        } catch (Exception x) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, x.toString(), x.toString()));
            return null;
        }
    }
    
    public void resetVariables(){
        lote = new Lote();
        manto = new Mantenimiento();
        setCod_preservante(null);
        setCod_vector(null);
    }
    public List<Lote> lista_lote_activos_falta_muestras(Integer estado) {
        LotesDao lotesDao = new LotesDao();
        try {
            return lotesDao.obtener_lotes_activos_falta_muestra(estado);
        } catch (Exception x) {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, x.toString(), x.toString()));
            return null;
        }
    }
    private boolean existe_otro_lote_asi(Lote l){
    LotesDao lDao = new LotesDao();
    return lDao.existe_otro_lote_asi(l);
}
}
