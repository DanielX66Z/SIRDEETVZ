/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.mbsesion;

/**
 *
 * @author PC
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.event.FlowEvent;
import sv.ues.dao.DepartamentoDao;
import sv.ues.dao.MunicipioDao;
import sv.ues.dominio.BitacoraCampo;
import sv.ues.dominio.Departamento;
import sv.ues.dominio.Muestra;
import sv.ues.dominio.Municipio;
import sv.ues.dominio.Ubicacion;

@ManagedBean
@ViewScoped
public class MbBitacoraCampo implements Serializable{
    
    private BitacoraCampo bitacoraCampo=new BitacoraCampo();
    private Ubicacion ubicacion=new Ubicacion();
    private Muestra muestra=new Muestra();
    private List<Departamento> lsdepartamentos = new ArrayList();
    private List<Municipio> lsmunicipios = new ArrayList();
    private List<SelectItem> items_departamento;
    private List<SelectItem> items_municipio;
    private boolean skip;
    private String codigodepartamento = "";
    private String codigomunicipio = "";
    
    public BitacoraCampo getBitacoraCampo() {
        return bitacoraCampo;
    }

    public void setBitacoraCampo(BitacoraCampo bitacoraCampo) {
        this.bitacoraCampo = bitacoraCampo;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    
    
    
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
    
     public List<SelectItem> getItems_departamento() throws Exception {
        //this.items_departamento_domicilio_aspirante = new ArrayList<>();
        this.items_departamento = new ArrayList();
        DepartamentoDao departamentos = new DepartamentoDao();
        List<Departamento> lista_departamentos = departamentos.obtener_todos_los_departamentos();
        this.items_departamento.clear();
        for(Departamento dpto:lista_departamentos)
        {
           SelectItem item = new SelectItem(dpto.getCodDepto(),dpto.getNomDepto());
           this.items_departamento.add(item);
        }
        return items_departamento;
    }
     
     
     public List<SelectItem> getItems_municipio() {
        //this.items_municipio = new ArrayList<>();
      /*  this.items_municipio = new ArrayList();
        MunicipioDao municipios = new MunicipioDao();
        List<Municipio> lista_municipios_por_dpto = municipios.obtener_municipios_por_id_del_departamento(this.codigodepartamento);
        this.items_municipio.clear();
        for(Municipio muni:lista_municipios_por_dpto)
        {
            SelectItem item = new SelectItem(muni.getCodMunicipio(),muni.getNomMunicipio());
            this.items_municipio.add(item);
        }*/
        return items_municipio;
    }
    
    public String getCodigodepartamento() {
        return codigodepartamento;
    }

    public void setCodigodepartamento(String codigodepartamento) {
        this.codigodepartamento = codigodepartamento;
    }  

    public String getCodigomunicipio() {
        return codigomunicipio;
    }

    public void setCodigomunicipio(String codigomunicipio) {
        this.codigomunicipio = codigomunicipio;
    }

    public Muestra getMuestra() {
        return muestra;
    }

    public void setMuestra(Muestra muestra) {
        this.muestra = muestra;
    }
    
    
    
    
}
