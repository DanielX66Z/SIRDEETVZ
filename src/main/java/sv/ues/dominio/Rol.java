package sv.ues.dominio;
// Generated 10-28-2019 09:05:07 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Rol generated by hbm2java
 */
public class Rol  implements java.io.Serializable {


     private int idRol;
     private String nomRol;
     private String descripcion;
     private boolean estadoRol;
     private Set permisos = new HashSet(0);
     private Set usuarios = new HashSet(0);

    public Rol() {
    }

	
    public Rol(int idRol, String nomRol, boolean estadoRol) {
        this.idRol = idRol;
        this.nomRol = nomRol;
        this.estadoRol = estadoRol;
    }
    public Rol(int idRol, String nomRol, String descripcion, boolean estadoRol, Set permisos, Set usuarios) {
       this.idRol = idRol;
       this.nomRol = nomRol;
       this.descripcion = descripcion;
       this.estadoRol = estadoRol;
       this.permisos = permisos;
       this.usuarios = usuarios;
    }
   
    public int getIdRol() {
        return this.idRol;
    }
    
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    public String getNomRol() {
        return this.nomRol;
    }
    
    public void setNomRol(String nomRol) {
        this.nomRol = nomRol;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public boolean isEstadoRol() {
        return this.estadoRol;
    }
    
    public void setEstadoRol(boolean estadoRol) {
        this.estadoRol = estadoRol;
    }
    public Set getPermisos() {
        return this.permisos;
    }
    
    public void setPermisos(Set permisos) {
        this.permisos = permisos;
    }
    public Set getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }




}


