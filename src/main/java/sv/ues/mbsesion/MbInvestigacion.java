/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.mbsesion;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.PrimeFaces;
import sv.ues.dao.InvestigacionDao;
import sv.ues.dominio.Investigacion;
import sv.ues.dominio.Laboratorio;
import sv.ues.dominio.PerfilInv;

/**
 *
 * @author PC
 */
@ManagedBean
@ViewScoped
public class MbInvestigacion implements Serializable {

    private Investigacion investigacion;
    private List<Laboratorio> itemLaboratorio;

    private Laboratorio lab;

    public Laboratorio getLab() {
        return lab;
    }

    public void setLab(Laboratorio lab) {
        this.lab = lab;
    }

    public MbInvestigacion() {
        investigacion = new Investigacion();
        investigacion.setPerfilInvs(new PerfilInv());
    }

    public Investigacion getInvestigacion() {
        return investigacion;
    }

    public void setInvestigacion(Investigacion investigacion) {
        this.investigacion = investigacion;
    }

    public List<Laboratorio> getItemLaboratorio() {
        InvestigacionDao investigacionDao = new InvestigacionDao();

        this.itemLaboratorio = new ArrayList();
        itemLaboratorio = investigacionDao.getLaboratorio();

        return itemLaboratorio;
    }

    public void setItemLaboratorio(List<Laboratorio> itemLaboratorio) {
        this.itemLaboratorio = itemLaboratorio;
    }

    public void registrar() {
        try {
            investigacion.setEstadoInvest(Boolean.TRUE);
          //  investigacion.setFechaRegistro(new Date());
            InvestigacionDao investigacionDao = new InvestigacionDao();
            investigacionDao.registrar(investigacion);
            investigacion=new Investigacion();
            PrimeFaces.current().ajax().update("F01");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Infomracion","Datos registrados correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "Error al guardar"));
        }
    }

}
