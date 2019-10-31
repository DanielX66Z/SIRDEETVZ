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
import sv.ues.dao.InvVectorDao;
import sv.ues.dao.PerfilInvDao;
import sv.ues.dao.VectorDao;
import sv.ues.dominio.InvVector;
import sv.ues.dominio.PerfilInv;
import sv.ues.dominio.Vector;

/**
 *
 * @author PC
 */
@ManagedBean
@ViewScoped
public class MbInvVector implements Serializable{
    
    private InvVector invVector;
    private List<PerfilInv> lsPerfil;
    private List<Vector> lsVector;
    
    public MbInvVector(){
        invVector=new InvVector();
       
    }    
    public List<PerfilInv> getLsPerfil() {
        PerfilInvDao perfilDao=new PerfilInvDao();
        this.lsPerfil=perfilDao.getPerfilInvestigacionByActivo();
        return lsPerfil;
    }

    public void setLsPerfil(List<PerfilInv> lsPerfil) {
        this.lsPerfil = lsPerfil;
    }

    public List<Vector> getLsVector() {
        VectorDao vectorDao=new VectorDao();
       
        return lsVector= vectorDao.getVectores();
    }

    public void setLsVector(List<Vector> lsVector) {
        this.lsVector = lsVector;
    }
    
    
    
    public InvVector getInvVector() {
        return invVector;
    }

    public void setInvVector(InvVector invVector) {
        this.invVector = invVector;
    }
    
    
    
    public void registrarVector(){
        //llamaremos el dao para guardar el vector
        InvVectorDao invVectorDao=new InvVectorDao();
        invVectorDao.guardar(invVector);
        invVector=new InvVector();
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "info","datos guardados"));
    }
    
}
