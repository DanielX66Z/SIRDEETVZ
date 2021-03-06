package sv.ues.dominio;
// Generated 21-nov-2019 13:01:51 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Mantenimiento generated by hbm2java
 */
public class Mantenimiento  implements java.io.Serializable {


     private int idManto;
     private Lote lote;
     private Preservante preservante;
     private boolean completadoManto;
     private Date fechaManto;
     private Date fechaProxManto;
     private int numeroMantenimiento;

    public Mantenimiento() {
    }

    
    public Mantenimiento(int idManto, Lote lote, boolean completadoManto) {
        this.idManto = idManto;
        this.lote = lote;
        this.completadoManto = completadoManto;
    }
    public Mantenimiento(int idManto, Lote lote, Preservante preservante, boolean completadoManto, Date fechaManto, Date fechaProxManto,int numeroMantenimiento) {
       this.idManto = idManto;
       this.lote = lote;
       this.preservante = preservante;
       this.completadoManto = completadoManto;
       this.fechaManto = fechaManto;
       this.fechaProxManto = fechaProxManto;
       this.numeroMantenimiento= numeroMantenimiento;
    }
   
    public int getIdManto() {
        return this.idManto;
    }
    
    public void setIdManto(int idManto) {
        this.idManto = idManto;
    }
    public Lote getLote() {
        return this.lote;
    }
    
    public void setLote(Lote lote) {
        this.lote = lote;
    }
    public Preservante getPreservante() {
        return this.preservante;
    }
    
    public void setPreservante(Preservante preservante) {
        this.preservante = preservante;
    }
    public boolean isCompletadoManto() {
        return this.completadoManto;
    }
    
    public void setCompletadoManto(boolean completadoManto) {
        this.completadoManto = completadoManto;
    }
    public Date getFechaManto() {
        return this.fechaManto;
    }
    
    public void setFechaManto(Date fechaManto) {
        this.fechaManto = fechaManto;
    }
    public Date getFechaProxManto() {
        return this.fechaProxManto;
    }
    
    public void setFechaProxManto(Date fechaProxManto) {
        this.fechaProxManto = fechaProxManto;
    }

    public int getNumeroMantenimiento() {
        return numeroMantenimiento;
    }

    public void setNumeroMantenimiento(int numeroMantenimiento) {
        this.numeroMantenimiento = numeroMantenimiento;
    }
    




}


