package sv.ues.dominio;
// Generated 10-28-2019 09:05:07 PM by Hibernate Tools 4.3.1



/**
 * Ubicacion generated by hbm2java
 */
public class Ubicacion  implements java.io.Serializable {


     private int idUbicacion;
     private BitacoraCampo bitacoraCampo;
     private Cacerio cacerio;
     private String latitud;
     private String longitud;
     private String codDepto;
     private String codMun;
     private String codCanton;
     private String altitud;
     private Integer poblacion;

    public Ubicacion() {
    }

	
    public Ubicacion(int idUbicacion, Cacerio cacerio, String latitud, String longitud, String altitud) {
        this.idUbicacion = idUbicacion;
        this.cacerio = cacerio;
        this.latitud = latitud;
        this.longitud = longitud;
        this.altitud = altitud;
    }
    public Ubicacion(int idUbicacion, BitacoraCampo bitacoraCampo, Cacerio cacerio, String latitud, String longitud, String altitud, Integer poblacion) {
       this.idUbicacion = idUbicacion;
       this.bitacoraCampo = bitacoraCampo;
       this.cacerio = cacerio;
       this.latitud = latitud;
       this.longitud = longitud;
       this.altitud = altitud;
       this.poblacion = poblacion;
    }
   
    public int getIdUbicacion() {
        return this.idUbicacion;
    }
    
    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }
    public BitacoraCampo getBitacoraCampo() {
        return this.bitacoraCampo;
    }
    
    public void setBitacoraCampo(BitacoraCampo bitacoraCampo) {
        this.bitacoraCampo = bitacoraCampo;
    }
    public Cacerio getCacerio() {
        return this.cacerio;
    }
    
    public void setCacerio(Cacerio cacerio) {
        this.cacerio = cacerio;
    }
    public String getLatitud() {
        return this.latitud;
    }
    
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }
    public String getLongitud() {
        return this.longitud;
    }
    
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    public String getAltitud() {
        return this.altitud;
    }
    
    public void setAltitud(String altitud) {
        this.altitud = altitud;
    }
    public Integer getPoblacion() {
        return this.poblacion;
    }
    
    public void setPoblacion(Integer poblacion) {
        this.poblacion = poblacion;
    }

    public String getCodDepto() {
        return codDepto;
    }

    public void setCodDepto(String codDepto) {
        this.codDepto = codDepto;
    }

    public String getCodMun() {
        return codMun;
    }

    public void setCodMun(String codMun) {
        this.codMun = codMun;
    }

    public String getCodCanton() {
        return codCanton;
    }

    public void setCodCanton(String codCanton) {
        this.codCanton = codCanton;
    }




}


