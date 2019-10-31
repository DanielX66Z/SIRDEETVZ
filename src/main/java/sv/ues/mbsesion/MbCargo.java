package sv.ues.mbsesion;

/*import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import sv.ues.dao.RolesDao;
import sv.ues.dominio.Cargo;
import sv.ues.dominio.Rol;*/

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import sv.ues.dao.CargoDao;
import sv.ues.dao.PersonaDao;
import sv.ues.dao.RolesDao;
import sv.ues.dominio.Cargo;
import sv.ues.dominio.Rol;

/**
 *
 * @author Miguel
 */

/*@ManagedBean(name="MbCargo")
@ViewScoped
public class MbCargo  implements Serializable
{
    
    private Cargo cargo;

    public MbCargo()
    {
        cargo = new Cargo();
    }

    public void registrar()
    {
        if(cargo.getCargo().trim().length() > 0)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informacion","Ingresa a registrar Cargo"));
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Informacion","NO Ingresa a registrar Cargo"));
        }
        //PrimeFaces.current().ajax().update("creacion:cargo:mensaje");
    }
       
    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}*/

@ManagedBean(name = "MbCargo")
@ViewScoped
public class MbCargo implements Serializable {

    private Cargo cargo;
    private List<Cargo> lscargo;
    private Cargo selectedCargo;

    public Cargo getSelectedCargo() {
        return selectedCargo;
    }

    public void setSelectedCargo(Cargo selectedCargo) {
        this.selectedCargo = selectedCargo;
    }

    public List<Cargo> getLscargo() {
        return lscargo;
    }

    public void setLscargo(List<Cargo> lscargo) {
        this.lscargo = lscargo;
    }

    public void selectCargo(Cargo cargo) {
        this.selectedCargo = cargo;
    }

    public void modificar(Cargo cargo) {
        selectedCargo = cargo;
    }

    public MbCargo() {
        cargo = new Cargo();
    }

    public void registrar() throws ParseException, Exception {
        try {
            if (cargo.getCargo().trim().length() > 0) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:MM:ss");
                String x = sdf.format(date);
                cargo.setFechaCreacion(sdf.parse(x));
                cargo.setActivo(Boolean.TRUE);
                CargoDao cargoDao = new CargoDao();
                cargoDao.registrar(cargo);
                cargo=new Cargo();
                PrimeFaces.current().ajax().update("F01");    
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Registro de cargo exitoso"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "Error al guardar"));
            }
            //PrimeFaces.current().ajax().update("creacion:cargo:mensaje");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "Error al guardar"));
        }
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public void validarCargo() {
        try {
            CargoDao cargoDao = new CargoDao();
            Cargo cargox = cargoDao.validarExistCargo(cargo.getCargo());
            if (cargox == null) {

            } else {
                this.cargo.setCargo("");
                PrimeFaces.current().ajax().update("F01:cargo");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Cargo ya existe"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "en capa dao de cargo"));
        }
    }

 

    public List<Cargo> listCargos() throws Exception {
        CargoDao cargoDao = new CargoDao();
        return cargoDao.obtenerCargo();
    }

    public void modificarDialog() throws Exception {

        CargoDao cargoDao = new CargoDao();
        
        cargoDao.actualizarCargo(selectedCargo);
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('dialogoModificar').hide();");
        PrimeFaces.current().ajax().update("F01");
        selectedCargo = new Cargo();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Cargo modificado exitosamente"));

    }

    public void eliminarDialog() {
        if (selectedCargo != null) {
            CargoDao cargoDao = new CargoDao();
            cargoDao.desactivarCargo(selectedCargo);

            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('dialogoEliminar').hide();");
             PrimeFaces.current().ajax().update("F01");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "Error al desactivar puesto"));

        }
    }
    
    

}