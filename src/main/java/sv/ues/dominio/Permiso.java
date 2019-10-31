package sv.ues.dominio;
// Generated 10-28-2019 09:05:07 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Permiso generated by hbm2java
 */
public class Permiso  implements java.io.Serializable {


     private int idPermiso;
     private String nomPermiso;
     private boolean estadoPermiso;
     private Set menus = new HashSet(0);
     private Set rols = new HashSet(0);

    public Permiso() {
    }

	
    public Permiso(int idPermiso, String nomPermiso, boolean estadoPermiso) {
        this.idPermiso = idPermiso;
        this.nomPermiso = nomPermiso;
        this.estadoPermiso = estadoPermiso;
    }
    public Permiso(int idPermiso, String nomPermiso, boolean estadoPermiso, Set menus, Set rols) {
       this.idPermiso = idPermiso;
       this.nomPermiso = nomPermiso;
       this.estadoPermiso = estadoPermiso;
       this.menus = menus;
       this.rols = rols;
    }
   
    public int getIdPermiso() {
        return this.idPermiso;
    }
    
    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }
    public String getNomPermiso() {
        return this.nomPermiso;
    }
    
    public void setNomPermiso(String nomPermiso) {
        this.nomPermiso = nomPermiso;
    }
    public boolean isEstadoPermiso() {
        return this.estadoPermiso;
    }
    
    public void setEstadoPermiso(boolean estadoPermiso) {
        this.estadoPermiso = estadoPermiso;
    }
    public Set getMenus() {
        return this.menus;
    }
    
    public void setMenus(Set menus) {
        this.menus = menus;
    }
    public Set getRols() {
        return this.rols;
    }
    
    public void setRols(Set rols) {
        this.rols = rols;
    }




}


