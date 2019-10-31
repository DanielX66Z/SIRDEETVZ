package sv.ues.mbsesion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import sv.ues.dao.RolesDao;
import sv.ues.dominio.Rol;

/**
 *
 * @author daniel
 */
@ManagedBean(name="MbRol")
@ViewScoped
public class MbRol  implements Serializable{
    
    private Rol roles;
    private List<Rol> lsroles=new ArrayList();
    private Rol selectedRol;

    public MbRol()
    {
        roles=new Rol();
        roles.setEstadoRol(true);
    }
    
    public void registrar() throws Exception
    {
        if(validarCampos() == true)
        {
            RolesDao rolesDao=new RolesDao(); 
            rolesDao.registrar(roles);
            roles = new Rol();
            roles.setEstadoRol(true);
            PrimeFaces.current().ajax().update("F01:registro");
            //PrimeFaces.current().ajax().update("rolesResgistrados");
            PrimeFaces.current().ajax().update("F01");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informacion","Rol registrado con exito")); 
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void modificar(Rol rol)
    {
        selectedRol=rol;
    }
    
    public void modificarDialog() throws Exception
    {
        if(validarCamposModificar() == true)
        {
            RolesDao rolesDao=new RolesDao();
            rolesDao.actualizar_rol(selectedRol);
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('dialogoModificar').hide();");
            PrimeFaces.current().ajax().update("registros");
            selectedRol = new Rol();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informacion","Rol modificado exitosamente"));
        }
    }
    
    public void setSelected(Rol roles)
    {
        selectedRol=roles;
    }
    
    public void eliminarDialog()
    {
       if(selectedRol!=null)
       {
            RolesDao rolesDao=new RolesDao();
            rolesDao.eliminar_rol(selectedRol);
            selectedRol = new Rol();
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('dialogoEliminar').hide();");
            PrimeFaces.current().ajax().update("registros");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informacion","Eliminacion exitosa"));
       }
    }
    
    public boolean validarCampos() throws Exception
    {    
        /*EN ESTAS VALIDACIONES SOLO PONER EL TRIM NO FUNCIONA
        ASI QUE SE TRABAJA CON LA LONGITUD*/
        if(roles.getNomRol().trim().length() == 0 || roles.getDescripcion().trim().length() == 0)
        {
            if(roles.getNomRol().trim().length() == 0)//VALIDAR QUE SE DIGITE EL NOMBRE DEL ROL
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","Debe digitar el nombre"));
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('bui').hide()");
            }
            if(roles.getDescripcion().trim().length() == 0)//VALIDAR QUE SE DIGITE LA DESCRIPCION DEL ROL
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","Debe digitar la descripcion"));
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('bui').hide()");
            }
        }
        else
        {
            if(roles.getNomRol().trim().length() < 10 || roles.getNomRol().trim().length() > 25 || roles.getDescripcion().trim().length() < 10 || roles.getDescripcion().trim().length() > 500)
            {
                if(roles.getNomRol().trim().length() < 10)
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","El nombre del rol debe tener al menos 10 caracteres"));
                    PrimeFaces current = PrimeFaces.current();
                    current.executeScript("PF('bui').hide()");
                }
                if(roles.getNomRol().trim().length() > 25)
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","El nombre del rol debe tener un maximo de 25 caracteres"));
                    PrimeFaces current = PrimeFaces.current();
                    current.executeScript("PF('bui').hide()");
                }
                if(roles.getDescripcion().trim().length() < 10)
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","La descripcion del rol debe tener al menos 10 caracteres"));
                    PrimeFaces current = PrimeFaces.current();
                    current.executeScript("PF('bui').hide()");
                }
                if(roles.getDescripcion().trim().length() > 500)
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","La descripcion del rol debe tener un maximo de 500 caracteres"));
                    PrimeFaces current = PrimeFaces.current();
                    current.executeScript("PF('bui').hide()");
                }
            }
            else
            {
                RolesDao rolesDao=new RolesDao();  
               
                if(rolesDao.validar_rol(roles.getNomRol())==true)
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","El rol ya existe")); 
                    PrimeFaces current = PrimeFaces.current();
                    current.executeScript("PF('bui').hide()");
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }
    return false;
    }
    
        public boolean validarCamposModificar() throws Exception
    {   
                if(selectedRol.getNomRol().trim().length() == 0 || selectedRol.getDescripcion().trim().length() == 0)
                {
                    if(selectedRol.getNomRol().trim().length() == 0)//VALIDAR QUE SE DIGITE EL NOMBRE DEL ROL
                    {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","Debe digitar el nombre"));
                    }
                    if(selectedRol.getDescripcion().trim().length() == 0)//VALIDAR QUE SE DIGITE LA DESCRIPCION DEL ROL
                    {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","Debe digitar la descripcion"));
                    }
                }
                else
                {
                    if(selectedRol.getNomRol().trim().length() < 10 || selectedRol.getNomRol().trim().length() > 25 || selectedRol.getDescripcion().trim().length() < 10 || selectedRol.getDescripcion().trim().length() > 500)
                    {
                        if(selectedRol.getNomRol().trim().length() < 10)
                        {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","El nombre del rol debe tener al menos 10 caracteres"));
                        }
                        if(selectedRol.getNomRol().trim().length() > 25)
                        {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","El nombre del rol debe tener un maximo de 25 caracteres"));
                        }
                        if(selectedRol.getDescripcion().trim().length() < 10)
                        {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","La descripcion del rol debe tener al menos 10 caracteres"));
                        }
                        if(selectedRol.getDescripcion().trim().length() > 500)
                        {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","La descripcion del rol debe tener un maximo de 500 caracteres"));
                        }
                    }
                    else
                    {
                        RolesDao rolesDao=new RolesDao();
                        System.out.println(selectedRol.getNomRol().trim().toLowerCase() + "   " + selectedRol.getIdRol());
                        if(rolesDao.validar_rol_modificar(selectedRol.getNomRol().trim().toLowerCase(),selectedRol.getIdRol()) == true)
                        {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","El rol ya existe")); 
                            return false;
                        }
                        else
                        {
                            return true;
                        }
                    }
                }
            
        return false;
    }
    
    public List<Rol> getLsroles() 
    {
        return lsroles;
    }

    public void setLsroles(List<Rol> lsroles) 
    {
        this.lsroles = lsroles;
    }

    public Rol getSelectedRol() 
    {
        return selectedRol;
    }

    public void setSelectedRol(Rol selectedRol) 
    {
        this.selectedRol = selectedRol;
    }
    
    public Rol getRoles() 
    {
        return roles;
    }
    
    public void setRoles(Rol roles) 
    {
        this.roles = roles;
    }
    
    public List<Rol> lista_rol()
    {
        RolesDao rolesDao = new RolesDao();
        try
        {
            return rolesDao.obtener_roles();
        }
        catch(Exception x)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,x.toString(),x.toString()));
            return null;
        }
    }
}
