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

    public MbLote() {
        lote = new Lote();
        manto = new Mantenimiento();
        muestra = new Muestra();

    }

    //Este metodo controla el flujo del wizard de registro
    public String flujoResgistrar(FlowEvent event) throws Exception {
        if (event.getOldStep().equals("general") && event.getNewStep().equals("confirmar")) {
            if (falla_validar_ingreso_lote())//Aca tienes que poner "validar_ingreso_lote()"
            {
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "El lote ya existe"));
                return event.getOldStep();
            } else {
                return event.getNewStep();
            }
        }
        return event.getNewStep();
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

    public void registrar_lote() {
        LotesDao lDao = new LotesDao();
        MantoLoteDao mDao = new MantoLoteDao();
        lote.setIdVector(cod_vector);
        Preservante prs = new Preservante();

        prs.setIdPreservante(cod_preservante);
        manto.setPreservante(prs);
        manto.setCompletadoManto(false);
        manto.setFechaManto(lote.getFechaCreacion());
        manto.setFechaProxManto(lote.getFechaModificacion());
        
        Lote nlote = lDao.registrar_nuevo_lote(lote);//Se registra lote y se obtiene dicho objeto
        manto.setLote(nlote);//Crea fk en Mantenimiento
        mDao.registrar_nuevo_manto(manto);//Guarda el nuevo mantenimiento
        
        lote = new Lote();//reinicia valores para no mostrar en vista
        manto = new Mantenimiento();
        PrimeFaces.current().ajax().update("F01:registro");
        PrimeFaces.current().ajax().update("F01");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Lote registrado con éxito"));
    }

    private boolean falla_validar_ingreso_lote() {
        lote.setFechaCreacion(new Date());
        if (lote.getNombreLote().trim().compareTo("") == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Escriba un nombre de lote"));
            return true;
        } else {
            if (existe_este_lote()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Lote ya existe"));
                return true;
            } else {
                if (lote.getNumMuestras() == null || lote.getNumMuestras() == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Numero de muestras no puede ser cero o vacío"));
                    return true;
                } else {
                    if (cod_preservante == null) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Seleccione un preservante"));
                        return true;
                    } else {
                        if (cod_vector == null) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Seleccione un vector para la muestra"));
                            return true;
                        } else {
                            if (lote.getFechaCreacion() == null || lote.getFechaModificacion() == null) {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Seleccione la fecha"));
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean existe_este_lote() {
        LotesDao lotesDao = new LotesDao();
        try {
            return lotesDao.existe_lote(lote.getNombreLote());
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
}
