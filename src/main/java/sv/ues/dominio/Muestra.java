package sv.ues.dominio;
// Generated 21-nov-2019 13:01:51 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Muestra generated by hbm2java
 */
public class Muestra  implements java.io.Serializable {


     private String codigoMuestra;
     private BitacoraCampo bitacoraCampo;
     private Lote lote;
     private TipoMuestra tipoMuestra;
     private String nomJefeFam;
     private int edadMuestra;
     private Date fechaTrabajo;
     private Date fechaMuestra;
     private String numeroMuestra;
     private String generoMuestra;
     private Integer secuencia;
     private Boolean parasito;
     private String telefonoMuestra;
     private String motivoMuestra;
     private Set analisisLabs = new HashSet(0);

    public Muestra() {
    }

    
    public Muestra(String codigoMuestra, BitacoraCampo bitacoraCampo, TipoMuestra tipoMuestra, String nomJefeFam, int edadMuestra, Date fechaTrabajo, Date fechaMuestra, String numeroMuestra) {
        this.codigoMuestra = codigoMuestra;
        this.bitacoraCampo = bitacoraCampo;
        this.tipoMuestra = tipoMuestra;
        this.nomJefeFam = nomJefeFam;
        this.edadMuestra = edadMuestra;
        this.fechaTrabajo = fechaTrabajo;
        this.fechaMuestra = fechaMuestra;
        this.numeroMuestra = numeroMuestra;
    }
    public Muestra(String codigoMuestra, BitacoraCampo bitacoraCampo, Lote lote, TipoMuestra tipoMuestra, String nomJefeFam, int edadMuestra, Date fechaTrabajo, Date fechaMuestra, String numeroMuestra, String generoMuestra, Integer secuencia, Boolean parasito, String telefonoMuestra, String motivoMuestra, Set analisisLabs) {
       this.codigoMuestra = codigoMuestra;
       this.bitacoraCampo = bitacoraCampo;
       this.lote = lote;
       this.tipoMuestra = tipoMuestra;
       this.nomJefeFam = nomJefeFam;
       this.edadMuestra = edadMuestra;
       this.fechaTrabajo = fechaTrabajo;
       this.fechaMuestra = fechaMuestra;
       this.numeroMuestra = numeroMuestra;
       this.generoMuestra = generoMuestra;
       this.secuencia = secuencia;
       this.parasito = parasito;
       this.telefonoMuestra = telefonoMuestra;
       this.motivoMuestra = motivoMuestra;
       this.analisisLabs = analisisLabs;
    }
   
    public String getCodigoMuestra() {
        return this.codigoMuestra;
    }
    
    public void setCodigoMuestra(String codigoMuestra) {
        this.codigoMuestra = codigoMuestra;
    }
    public BitacoraCampo getBitacoraCampo() {
        return this.bitacoraCampo;
    }
    
    public void setBitacoraCampo(BitacoraCampo bitacoraCampo) {
        this.bitacoraCampo = bitacoraCampo;
    }
    public Lote getLote() {
        return this.lote;
    }
    
    public void setLote(Lote lote) {
        this.lote = lote;
    }
    public TipoMuestra getTipoMuestra() {
        return this.tipoMuestra;
    }
    
    public void setTipoMuestra(TipoMuestra tipoMuestra) {
        this.tipoMuestra = tipoMuestra;
    }
    public String getNomJefeFam() {
        return this.nomJefeFam;
    }
    
    public void setNomJefeFam(String nomJefeFam) {
        this.nomJefeFam = nomJefeFam;
    }
    public int getEdadMuestra() {
        return this.edadMuestra;
    }
    
    public void setEdadMuestra(int edadMuestra) {
        this.edadMuestra = edadMuestra;
    }
    public Date getFechaTrabajo() {
        return this.fechaTrabajo;
    }
    
    public void setFechaTrabajo(Date fechaTrabajo) {
        this.fechaTrabajo = fechaTrabajo;
    }
    public Date getFechaMuestra() {
        return this.fechaMuestra;
    }
    
    public void setFechaMuestra(Date fechaMuestra) {
        this.fechaMuestra = fechaMuestra;
    }
    public String getNumeroMuestra() {
        return this.numeroMuestra;
    }
    
    public void setNumeroMuestra(String numeroMuestra) {
        this.numeroMuestra = numeroMuestra;
    }
    public String getGeneroMuestra() {
        return this.generoMuestra;
    }
    
    public void setGeneroMuestra(String generoMuestra) {
        this.generoMuestra = generoMuestra;
    }
    public Integer getSecuencia() {
        return this.secuencia;
    }
    
    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }
    
    public Boolean getParasito() {
        return this.parasito;
    }
    
    public void setParasito(Boolean parasito) {
        this.parasito = parasito;
    }
    public String getTelefonoMuestra() {
        return this.telefonoMuestra;
    }
    
    public void setTelefonoMuestra(String telefonoMuestra) {
        this.telefonoMuestra = telefonoMuestra;
    }
    public String getMotivoMuestra() {
        return this.motivoMuestra;
    }
    
    public void setMotivoMuestra(String motivoMuestra) {
        this.motivoMuestra = motivoMuestra;
    }
    public Set getAnalisisLabs() {
        return this.analisisLabs;
    }
    
    public void setAnalisisLabs(Set analisisLabs) {
        this.analisisLabs = analisisLabs;
    }




}


