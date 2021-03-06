package sv.ues.dominio;
// Generated 10-28-2019 09:05:07 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Vector generated by hbm2java
 */
public class Vector  implements java.io.Serializable {


     private Integer codVector;
     private String nomVector;
     private String descVector;
     private Familia familia;
     private String nomCientifico;
     private Set invVectors = new HashSet(0);

    public Vector() {
    }

	
    public Vector(Integer codVector, String nomVector) {
        this.codVector = codVector;
        this.nomVector = nomVector;
    }
    public Vector(Integer codVector, String nomVector, String descVector, Set invVectors) {
       this.codVector = codVector;
       this.nomVector = nomVector;
       this.descVector = descVector;
       this.invVectors = invVectors;
    }
    
    public Vector(int codVector, Familia familia, String nomVector, String descVector, String nomCientifico, Set invVectors) {
       this.codVector = codVector;
       this.familia = familia;
       this.nomVector = nomVector;
       this.descVector = descVector;
       this.nomCientifico = nomCientifico;
       this.invVectors = invVectors;
    }
    
    public Integer getCodVector() {
        return this.codVector;
    }
    
    public void setCodVector(Integer codVector) {
        this.codVector = codVector;
    }
    public String getNomVector() {
        return this.nomVector;
    }
    
    public void setNomVector(String nomVector) {
        this.nomVector = nomVector;
    }
    public String getDescVector() {
        return this.descVector;
    }
    
    public void setDescVector(String descVector) {
        this.descVector = descVector;
    }
    public Set getInvVectors() {
        return this.invVectors;
    }
    
    public void setInvVectors(Set invVectors) {
        this.invVectors = invVectors;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public String getNomCientifico() {
        return nomCientifico;
    }

    public void setNomCientifico(String nomCientifico) {
        this.nomCientifico = nomCientifico;
    }
    
    @Override
    public String toString() {
        return "Vector[codVector=" + codVector + "]";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.codVector;
        hash = 79 * hash + (this.nomVector != null ? this.nomVector.hashCode() : 0);
        hash = 79 * hash + (this.descVector != null ? this.descVector.hashCode() : 0);
        hash = 79 * hash + (this.invVectors != null ? this.invVectors.hashCode() : 0);
        return hash;
    }

     @Override
    public boolean equals(Object object) {
          // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vector)) {
            return false;
        }
        Vector other = (Vector) object;
        if ((this.codVector == null && other.codVector != null) || (this.codVector != null && !this.codVector.equals(other.codVector))) {
            return false;
        }
        return true;
    }


    

}


