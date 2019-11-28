/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.mbsesion;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import sv.ues.dao.MuestrasDao;
import sv.ues.dominio.Muestra;

/**
 *
 * @author Flor Diaz
 */
@ManagedBean (name = "mbMuestrasLotes")
@ViewScoped
public class MbMuestrasLotes implements Serializable{
    
    private Muestra muestraLote;

    /**
     * Creates a new instance of MbMuestrasLotes
     */
    public MbMuestrasLotes() {
        muestraLote = new Muestra();
    }

    public Muestra getMuestraLote() {
        return muestraLote;
    }

    public void setMuestraLote(Muestra muestraLote) {
        this.muestraLote = muestraLote;
    }
    
    public String flujoResgistrar(FlowEvent event) throws Exception {
        if (event.getOldStep().equals("general") && event.getNewStep().equals("confirmar")) {
            if (falla_validar_ingreso_muestra())
            {
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "El lote ya existe"));
                return event.getOldStep();
            } else {
                return event.getNewStep();
            }
        }
        return event.getNewStep();
    }
    
    private boolean falla_validar_ingreso_muestra(){
        //aca van todas las validaciones
        /*if (muestraLote.getNomCientifico().trim().compareTo("")==0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Escriba un nombre cientifico"));
            return true;
        } else {
            //Aca irian las demas
            return false;
        }*/
        return false;//
    }
    
    public List<Muestra> muestras_por_lote(Integer idLote){
        MuestrasDao muDao = new MuestrasDao();
        try {
            return muDao.muestra_por_lote(idLote);
        } catch (Exception x) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, x.toString(), x.toString()));
            return null;
        }
    }
}
