/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.mbsesion;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FlowEvent;
import sv.ues.dao.MuestrasDao;
import sv.ues.dao.TipoMuestraDao;
import sv.ues.dominio.BitacoraCampo;
import sv.ues.dominio.Lote;
import sv.ues.dominio.Muestra;
import sv.ues.dominio.TipoMuestra;

/**
 *
 * @author Flor Diaz
 */
@ManagedBean (name = "mbMuestrasLotes")
@ViewScoped
public class MbMuestrasLotes implements Serializable{
    
    private Muestra muestraLote;
     private Integer cod_lote;
     private Integer cod_tipomuestra;
    private Integer cod_estadio;
     private Integer cod_lote2;


    /**
     * Creates a new instance of MbMuestrasLotes
     */
    public MbMuestrasLotes() {
        muestraLote = new Muestra();        
    }

    public Integer getCod_lote2() {
        return cod_lote2;
    }

    public void setCod_lote2(Integer cod_lote2) {
        this.cod_lote2 = cod_lote2;
    }
    

    public Integer getCod_estadio() {
        return cod_estadio;
    }

    public void setCod_estadio(Integer cod_estadio) {
        this.cod_estadio = cod_estadio;
    }

    public Integer getCod_tipomuestra() {
        return cod_tipomuestra;
    }

    public void setCod_tipomuestra(Integer cod_tipomuestra) {
        this.cod_tipomuestra = cod_tipomuestra;
    }
    

    public Integer getCod_lote() {
        return cod_lote;
    }

    public void setCod_lote(Integer cod_lote) {
        this.cod_lote = cod_lote;
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
    
    private boolean falla_validar_ingreso_muestra() {
        if (cod_lote == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Seleccione el lote correspondiente"));
            return true;
        } else {
            if (muestraLote.getCodigoMuestra().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Digite el codigo de orden"));
                return true;
            } else {
                if (cod_tipomuestra == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Seleccione tipo de muestra"));
                    return true;
                } else {
                    if (muestraLote.getNomCientifico().equals("")) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese el nombre cientifico"));
                        return true;
                    } else {
                        if (muestraLote.getFamiliaMuestra().equals("")) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ingrese familia de muestra"));
                            return true;
                        } else {
                            if (cod_estadio == null) {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Seleccione el estadio"));
                                return true;
                            } else {
                                if (muestraLote.getGeneroMuestra() == "") {
                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Seleccione genero"));
                                    return true;
                                } else {
                                    if (muestraLote.getParasito() == null) {
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Seleccione si tiene o  no parasitos"));
                                        return true;
                                    } else {
                                        return false;/*Pasa todas las validaciones*/
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
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
    //Metodo que permite obtener el listado de tipo de muestras
    public List<TipoMuestra> listaTipoMuestras(){
        TipoMuestraDao tipoMuestraDao= new TipoMuestraDao();
        return 
                tipoMuestraDao.getTiposMuestras();
    }
    
    public String estadio_muestra(Integer estadio) {
        String est = "";
        try {
            switch (estadio) {
                case 1:
                    est = "Huevo";
                    break;
                case 2:
                    est = "1 Fase ninfal";
                    break;
                case 3:
                    est = "2 Fase ninfal";
                    break;
                case 4:
                    est = "3 Fase ninfal";
                    break;
                case 5:
                    est = "4 Fase ninfal";
                    break;
                case 6:
                    est = "5 Fase ninfal";
                    break;
                case 7:
                    est = "Fase adulta";
                    break;
                default:
                    est = "No determinada";
                // code block
            }
        } catch (Exception e) {
            est = "No determinada";
        }
        return est;
    }
    /**
     * Devuelve Tipo muestra segun el ID dado
     * @param idTipoMuestra Integer, ID dado
     * @return TipoMuestra
     */
    public TipoMuestra tipo_muestra_por_id(Integer idTipoMuestra){
        TipoMuestraDao tipoMuestraDao= new TipoMuestraDao();
        return tipoMuestraDao.getTipoMuestraById(idTipoMuestra);
    }
    
    public void registrar_nueva_muestra_lote() {
        MuestrasDao mDao = new MuestrasDao();

        /**
         * Creando instancias de objetos para FK en Muestras
         */
        Lote lo = new Lote();
        TipoMuestra tm = new TipoMuestra();
        BitacoraCampo bc = new BitacoraCampo();

        /**
         * "Setiando" los ID a cada instancia de objetos para ser usados como FK
         * en muestras
         */
        lo.setIdLote(cod_lote);
        tm.setIdTipoMues(cod_tipomuestra);
        bc.setIdBitCampo(3);/*La tabla bitacora_campo, solo tiene un registro con ID 3; Debe modificarse para soportar nulos o buscar otra alternativa, por el momento es obligatorio*/

        /**
         * "Setiando" las FK del Muestras
         */
        muestraLote.setLote(lo);
        muestraLote.setTipoMuestra(tm);
        muestraLote.setBitacoraCampo(bc);

        /**
         * LLenando con basura los campos obligatorios y que no estan en la
         * vista.
         */
        muestraLote.setNomJefeFam("JefeFamilia");
        muestraLote.setFechaTrabajo(new Date());
        muestraLote.setFechaMuestra(new Date());
        muestraLote.setNumeroMuestra("0");/*Este campo seria "innecesario" porque el numero de muestras esta indicado en Lote, a menos que este sea como un correlativo */

        /**
         * Guardando nuevo registro
         */
        mDao.registrar_nueva_muestra(muestraLote);
        
        PrimeFaces.current().ajax().update("F01:registro");
        PrimeFaces.current().ajax().update("F01");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Lote registrado con éxito"));
    }
}
