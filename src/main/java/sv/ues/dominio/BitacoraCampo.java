package sv.ues.dominio;
// Generated 10-28-2019 09:05:07 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BitacoraCampo generated by hbm2java
 */
public class BitacoraCampo  implements java.io.Serializable {


     private int idBitCampo;
     private BitacoraCampo bitacoraCampo;
     private InvVector invVector;
     private Date fechaCampo;
     private Set bitacoraCampos = new HashSet(0);
     private Set encuestas = new HashSet(0);
     private Set muestras = new HashSet(0);
     private Set ubicacions = new HashSet(0);

    public BitacoraCampo() {
    }

	
    public BitacoraCampo(int idBitCampo, InvVector invVector, Date fechaCampo) {
        this.idBitCampo = idBitCampo;
        this.invVector = invVector;
        this.fechaCampo = fechaCampo;
    }
    public BitacoraCampo(int idBitCampo, BitacoraCampo bitacoraCampo, InvVector invVector, Date fechaCampo, Set bitacoraCampos, Set encuestas, Set muestras, Set ubicacions) {
       this.idBitCampo = idBitCampo;
       this.bitacoraCampo = bitacoraCampo;
       this.invVector = invVector;
       this.fechaCampo = fechaCampo;
       this.bitacoraCampos = bitacoraCampos;
       this.encuestas = encuestas;
       this.muestras = muestras;
       this.ubicacions = ubicacions;
    }
   
    public int getIdBitCampo() {
        return this.idBitCampo;
    }
    
    public void setIdBitCampo(int idBitCampo) {
        this.idBitCampo = idBitCampo;
    }
    public BitacoraCampo getBitacoraCampo() {
        return this.bitacoraCampo;
    }
    
    public void setBitacoraCampo(BitacoraCampo bitacoraCampo) {
        this.bitacoraCampo = bitacoraCampo;
    }
    public InvVector getInvVector() {
        return this.invVector;
    }
    
    public void setInvVector(InvVector invVector) {
        this.invVector = invVector;
    }
    public Date getFechaCampo() {
        return this.fechaCampo;
    }
    
    public void setFechaCampo(Date fechaCampo) {
        this.fechaCampo = fechaCampo;
    }
    public Set getBitacoraCampos() {
        return this.bitacoraCampos;
    }
    
    public void setBitacoraCampos(Set bitacoraCampos) {
        this.bitacoraCampos = bitacoraCampos;
    }
    public Set getEncuestas() {
        return this.encuestas;
    }
    
    public void setEncuestas(Set encuestas) {
        this.encuestas = encuestas;
    }
    public Set getMuestras() {
        return this.muestras;
    }
    
    public void setMuestras(Set muestras) {
        this.muestras = muestras;
    }
    public Set getUbicacions() {
        return this.ubicacions;
    }
    
    public void setUbicacions(Set ubicacions) {
        this.ubicacions = ubicacions;
    }




}


