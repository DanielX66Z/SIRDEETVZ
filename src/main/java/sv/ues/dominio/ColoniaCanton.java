package sv.ues.dominio;
// Generated 10-28-2019 09:05:07 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * ColoniaCanton generated by hbm2java
 */
public class ColoniaCanton  implements java.io.Serializable {


     private int idColCan;
     private Municipio municipio;
     private String nomUbicacion;
     private Set cacerios = new HashSet(0);

    public ColoniaCanton() {
    }

	
    public ColoniaCanton(int idColCan, Municipio municipio, String nomUbicacion) {
        this.idColCan = idColCan;
        this.municipio = municipio;
        this.nomUbicacion = nomUbicacion;
    }
    public ColoniaCanton(int idColCan, Municipio municipio, String nomUbicacion, Set cacerios) {
       this.idColCan = idColCan;
       this.municipio = municipio;
       this.nomUbicacion = nomUbicacion;
       this.cacerios = cacerios;
    }
   
    public int getIdColCan() {
        return this.idColCan;
    }
    
    public void setIdColCan(int idColCan) {
        this.idColCan = idColCan;
    }
    public Municipio getMunicipio() {
        return this.municipio;
    }
    
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    public String getNomUbicacion() {
        return this.nomUbicacion;
    }
    
    public void setNomUbicacion(String nomUbicacion) {
        this.nomUbicacion = nomUbicacion;
    }
    public Set getCacerios() {
        return this.cacerios;
    }
    
    public void setCacerios(Set cacerios) {
        this.cacerios = cacerios;
    }




}


