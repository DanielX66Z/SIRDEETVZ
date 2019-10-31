package sv.ues.dominio;
// Generated 10-28-2019 09:05:07 PM by Hibernate Tools 4.3.1



/**
 * Detalle generated by hbm2java
 */
public class Detalle  implements java.io.Serializable {


     private int idDetalle;
     private Respuesta respuesta;
     private String opcionRepuesta;

    public Detalle() {
    }

	
    public Detalle(int idDetalle, Respuesta respuesta) {
        this.idDetalle = idDetalle;
        this.respuesta = respuesta;
    }
    public Detalle(int idDetalle, Respuesta respuesta, String opcionRepuesta) {
       this.idDetalle = idDetalle;
       this.respuesta = respuesta;
       this.opcionRepuesta = opcionRepuesta;
    }
   
    public int getIdDetalle() {
        return this.idDetalle;
    }
    
    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }
    public Respuesta getRespuesta() {
        return this.respuesta;
    }
    
    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }
    public String getOpcionRepuesta() {
        return this.opcionRepuesta;
    }
    
    public void setOpcionRepuesta(String opcionRepuesta) {
        this.opcionRepuesta = opcionRepuesta;
    }




}


