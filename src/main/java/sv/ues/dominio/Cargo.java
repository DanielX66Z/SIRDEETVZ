package sv.ues.dominio;
// Generated 10-28-2019 09:05:07 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Cargo generated by hbm2java
 */
public class Cargo  implements java.io.Serializable {


     private int idCargo;
     private String cargo;
     private String descripcion;
     private Date fechaCreacion;
     private Date fechaModifica;
     private Boolean activo;
     private Set usuarios = new HashSet(0);

    public Cargo() {
    }

	
    public Cargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public Date getFechaModifica() {
        return fechaModifica;
    }

    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }
    
    
    
    public Cargo(int idCargo, String cargo, String descripcion, Date fechaCreacion, Boolean activo, Set usuarios) {
       this.idCargo = idCargo;
       this.cargo = cargo;
       this.descripcion = descripcion;
       this.fechaCreacion = fechaCreacion;
       this.activo = activo;
       this.usuarios = usuarios;
    }
   
    public int getIdCargo() {
        return this.idCargo;
    }
    
    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }
    public String getCargo() {
        return this.cargo;
    }
    
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public Boolean getActivo() {
        return this.activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    public Set getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }




}


