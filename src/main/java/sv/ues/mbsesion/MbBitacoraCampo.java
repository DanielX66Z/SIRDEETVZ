/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.mbsesion;

/**
 *
 * @author Daniel
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.behavior.FacesBehavior;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FlowEvent;
import sv.ues.dao.BitacoraCampoDao;
import sv.ues.dao.DepartamentoDao;
import sv.ues.dao.InvVectorDao;
import sv.ues.dao.InvestigacionDao;
import sv.ues.dao.MunicipioDao;
import sv.ues.dao.TipoMuestraDao;
import sv.ues.dominio.BitacoraCampo;
import sv.ues.dominio.Departamento;
import sv.ues.dominio.InvVector;
import sv.ues.dominio.Investigacion;
import sv.ues.dominio.Muestra;
import sv.ues.dominio.Municipio;
import sv.ues.dominio.TipoMuestra;
import sv.ues.dominio.Ubicacion;

@ManagedBean
@ViewScoped
public class MbBitacoraCampo implements Serializable {

    private BitacoraCampo bitacoraCampo = new BitacoraCampo();
    private List<Muestra> lsMuestras = new ArrayList<Muestra>();
    private List<InvVector> lsInvVector=new ArrayList<InvVector>();
    private Muestra muestra = new Muestra();
    private List<TipoMuestra> lsTipoMuestras = new ArrayList<TipoMuestra>();

    public MbBitacoraCampo() {
        bitacoraCampo = new BitacoraCampo();
    }

    public BitacoraCampo getBitacoraCampo() {
        return bitacoraCampo;
    }

    public void setBitacoraCampo(BitacoraCampo bitacoraCampo) {
        this.bitacoraCampo = bitacoraCampo;
    }

    public List<Muestra> getLsMuestras() {
        return lsMuestras;
    }

    public void setLsMuestras(List<Muestra> lsMuestras) {
        this.lsMuestras = lsMuestras;
    }

    public Muestra getMuestra() {
        return muestra;
    }

    public void setMuestra(Muestra muestra) {
        this.muestra = muestra;
    }

    public List<TipoMuestra> getLsTipoMuestras() {
        TipoMuestraDao tipoMuestraDao = new TipoMuestraDao();
        return lsTipoMuestras = tipoMuestraDao.getTiposMuestras();
    }

    public void setLsTipoMuestras(List<TipoMuestra> lsTipoMuestras) {
        this.lsTipoMuestras = lsTipoMuestras;
    }

    public List<InvVector> getLsInvVector() {
        InvVectorDao invDao=new InvVectorDao();
        return lsInvVector=invDao.getListInvVector();
    }

    public void setLsInvVector(List<InvVector> lsInvVector) {
        this.lsInvVector = lsInvVector;
    }

    
    
    public void agregarMuestra() {
        try {
            //validamos campos
            if (muestra.getCodigoMuestra().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ingrese codigo de muestra"));
            } else {
                if (muestra.getEdadMuestra() == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ingrese edad de la muestra"));
                } else {
                    if (muestra.getFechaMuestra() == null) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ingrese fecha de muestra"));
                    } else {
                        if (muestra.getCodigoMuestra().isEmpty()) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ingrese codigo de muestra"));
                        } else {
                            if (muestra.getTelefonoMuestra().isEmpty()) {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ingrese telefono"));
                            } else {
                                if (muestra.getNomJefeFam().isEmpty()) {
                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ingrese nombre jefe de familia"));
                                }
                                else{
                                     if (muestra.getTipoMuestra()==null) {
                                          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione tipo de muestra"));
                                     }else{
                                         lsMuestras.add(muestra);
                                         FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Exito", "Datos ingresados correctamente"));
                                     }
                                            
                                }
                            }
                        }
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al agregar elemento en bean mbbitacoracampo");
        }

    }
    
    public void registrar() throws Exception{
       //  try {
            bitacoraCampo.setMuestras(lsMuestras);
             BitacoraCampoDao bitacoraCampoDao=new BitacoraCampoDao();
            bitacoraCampoDao.registrar(bitacoraCampo);
            bitacoraCampo=new BitacoraCampo();
            lsMuestras.clear();
            muestra=new Muestra();
            PrimeFaces.current().ajax().update("F01");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Infomracion","Datos registrados correctamente"));
       /* } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "Error al guardar"));
        }*/
    }

}
