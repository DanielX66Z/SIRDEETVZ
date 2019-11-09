package sv.ues.dominio;
// Generated 10-28-2019 09:05:07 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Investigacion generated by hbm2java
 */
public class Investigacion  implements java.io.Serializable {


     private Integer codInvest;
     private Investigacion investigacion;
     private Laboratorio laboratorio;
     private String nomInvest;
     private Boolean estadoInvest;
     private Set actividads = new HashSet(0);
     private Set investigadors = new HashSet(0);
     private Set investigacions = new HashSet(0);
     private Set invVectors = new HashSet(0);
     private PerfilInv perfilInvs ;

    public Investigacion() {
    }

	
    public Investigacion(Integer codInvest, Laboratorio laboratorio, String nomInvest) {
        this.codInvest = codInvest;
        this.laboratorio = laboratorio;
        this.nomInvest = nomInvest;
    }
    public Investigacion(Integer codInvest, Investigacion investigacion, Laboratorio laboratorio, String nomInvest, Boolean estadoInvest, Set actividads, Set investigadors, Set investigacions, Set invVectors, PerfilInv perfilInvs) {
       this.codInvest = codInvest;
       this.investigacion = investigacion;
       this.laboratorio = laboratorio;
       this.nomInvest = nomInvest;
       this.estadoInvest = estadoInvest;
       this.actividads = actividads;
       this.investigadors = investigadors;
       this.investigacions = investigacions;
       this.invVectors = invVectors;
       this.perfilInvs = perfilInvs;
    }
   
    public Integer getCodInvest() {
        return this.codInvest;
    }
    
    public void setCodInvest(Integer codInvest) {
        this.codInvest = codInvest;
    }
    public Investigacion getInvestigacion() {
        return this.investigacion;
    }
    
    public void setInvestigacion(Investigacion investigacion) {
        this.investigacion = investigacion;
    }
    public Laboratorio getLaboratorio() {
        return this.laboratorio;
    }
    
    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }
    public String getNomInvest() {
        return this.nomInvest;
    }
    
    public void setNomInvest(String nomInvest) {
        this.nomInvest = nomInvest;
    }
    public Boolean getEstadoInvest() {
        return this.estadoInvest;
    }
    
    public void setEstadoInvest(Boolean estadoInvest) {
        this.estadoInvest = estadoInvest;
    }
    public Set getActividads() {
        return this.actividads;
    }
    
    public void setActividads(Set actividads) {
        this.actividads = actividads;
    }
    public Set getInvestigadors() {
        return this.investigadors;
    }
    
    public void setInvestigadors(Set investigadors) {
        this.investigadors = investigadors;
    }
    public Set getInvestigacions() {
        return this.investigacions;
    }
    
    public void setInvestigacions(Set investigacions) {
        this.investigacions = investigacions;
    }
    public Set getInvVectors() {
        return this.invVectors;
    }
    
    public void setInvVectors(Set invVectors) {
        this.invVectors = invVectors;
    }
    public PerfilInv getPerfilInvs() {
        return this.perfilInvs;
    }
    
    public void setPerfilInvs(PerfilInv perfilInvs) {
        this.perfilInvs = perfilInvs;
    }


    @Override
    public String toString() {
        return "Investigacion [codInvest=" + codInvest + " ]";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.codInvest;
        hash = 37 * hash + (this.investigacion != null ? this.investigacion.hashCode() : 0);
        hash = 37 * hash + (this.laboratorio != null ? this.laboratorio.hashCode() : 0);
        hash = 37 * hash + (this.nomInvest != null ? this.nomInvest.hashCode() : 0);
        hash = 37 * hash + (this.estadoInvest != null ? this.estadoInvest.hashCode() : 0);
        hash = 37 * hash + (this.actividads != null ? this.actividads.hashCode() : 0);
        hash = 37 * hash + (this.investigadors != null ? this.investigadors.hashCode() : 0);
        hash = 37 * hash + (this.investigacions != null ? this.investigacions.hashCode() : 0);
        hash = 37 * hash + (this.invVectors != null ? this.invVectors.hashCode() : 0);
        hash = 37 * hash + (this.perfilInvs != null ? this.perfilInvs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
          // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Investigacion)) {
            return false;
        }
        Investigacion other = (Investigacion) object;
        if ((this.codInvest == null && other.codInvest != null) || (this.codInvest != null && !this.codInvest.equals(other.codInvest))) {
            return false;
        }
        return true;
    }


    
    
}


