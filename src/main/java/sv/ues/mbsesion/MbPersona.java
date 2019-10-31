      package sv.ues.mbsesion;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.activation.DataHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.primefaces.PrimeFaces;
import org.springframework.security.crypto.bcrypt.BCrypt;
import sv.ues.dao.DepartamentoDao;
import sv.ues.dao.MunicipioDao;
import sv.ues.dao.PersonaDao;
import sv.ues.dao.RolesDao;
import sv.ues.dominio.Departamento;
import sv.ues.dominio.Municipio;
import sv.ues.dominio.Persona;
import sv.ues.dominio.Rol;
import sv.ues.dominio.Usuario;
import javax.activation.DataSource;
import sv.ues.dao.UsuarioDao;
import sv.ues.utils.EnvioCorreos;

/**
 *
 * @author Miguel Martinez
 */


@ManagedBean(name="MbPersona")
@ViewScoped
public class MbPersona  implements Serializable
{
    
    private Persona persona;
    private Persona personaSeleccionada;
    
    private List<SelectItem> items_municipio;
    private List<SelectItem> items_departamento;
    
    private Municipio municipio;
    private Departamento departamento;
    private String codigodepartamento = "";
    private String codigomunicipio = "";
    
    private String listarolesconsulta = "";
    
    List<Rol> listaroles;


    public MbPersona()
    {
        persona = new Persona();
        municipio = new Municipio();
        departamento = new Departamento();
    }
    
    public void registrar2() throws Exception
    {
        Usuario usuario = new Usuario();
       
        usuario.setActivo(false);
        usuario.setIdUsuario(155);
        Rol rol = new Rol();
        Rol rol2 = new Rol();
        Set rols = new HashSet();
        Set usuarios = new HashSet();
        usuarios.add(usuario);
        
        rol.setIdRol(6);
        rol.setUsuarios(usuarios);
        rols.add(rol);
        rol2.setIdRol(5);
        rols.add(rol2);

        usuario.setRols(rols);

        PersonaDao personaDao = new PersonaDao();
        personaDao.registrar(persona,usuario);

        
    }
    
    public void registrar() throws Exception
    {
        String clave = "";
        String hash = "";
        
        Date fecha = new Date();
        Usuario usuario = new Usuario();
        
        usuario.setNomUsuario(persona.getPrimerNombre().substring(0,1).toLowerCase()+persona.getSegundoNombre().substring(0,1).toLowerCase()+persona.getPrimerApellido());
        usuario.setFechaRegistro(fecha);
        usuario.setFechaUltimaModificacion(fecha);
        usuario.setActivo(false);
        clave = generarPassword(8);
        usuario.setClave(BCrypt.hashpw(clave, BCrypt.gensalt()));
        hash = usuario.getClave()+""+System.nanoTime();
        usuario.setHash(hash);
        
        

        PersonaDao personaDao = new PersonaDao();
        persona.setMunicipio(municipio);
        persona.setFechaRegistro(fecha);
        persona.setFechaUltimaModificacion(fecha);
        
        Rol rol = new Rol();
        rol.setIdRol(6);
        Set rolset = new HashSet(0);
        rolset.add(rol);
        usuario.setRols(rolset);
        
        personaDao.registrar(persona,usuario);
        
        UsuarioDao usuarioDao = new UsuarioDao();
        usuario = usuarioDao.obtener_usuario_hash(hash);
        //StringUtils.capitalize(persona.getPrimerNombre())
        //persona.getPrimerApellido().substring(0, 1).toUpperCase() + persona.getPrimerApellido().substring(1).toLowerCase();
        
        EnvioCorreos correos = new EnvioCorreos(persona.getCorreoPersona(), "Registro SIRDEETV", asunto_email(usuario.getNomUsuario(),clave,persona.getPrimerNombre().substring(0, 1).toUpperCase() + persona.getPrimerNombre().substring(1).toLowerCase(),persona.getPrimerApellido().substring(0, 1).toUpperCase() + persona.getPrimerApellido().substring(1).toLowerCase()));
        correos.start();
        
        persona = new Persona();
        municipio = new Municipio();
        departamento = new Departamento();
        usuario = new Usuario();
        
        
        
        this.items_departamento = new ArrayList();
        this.items_municipio = new ArrayList();
        codigodepartamento = "";
        codigomunicipio = "";

        PrimeFaces.current().ajax().update("F01");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informacion","Persona registrada con exito")); 
    }
    
    public void modificar() throws Exception
    {
        Date fecha = new Date();
        
        PersonaDao personaDao = new PersonaDao();
        Municipio municipiomodificar = new Municipio();
        municipiomodificar.setCodMunicipio(codigomunicipio);
        personaSeleccionada.setMunicipio(municipiomodificar);
        personaSeleccionada.setFechaUltimaModificacion(fecha);
        
        personaDao.modificar(personaSeleccionada);
        
        //PROBABLEMENTE SE DEBA ENVAR CORREO DE PERSONA MODIFICADO
        //EnvioCorreos correos = new EnvioCorreos(persona.getCorreoPersona(), "Registro SIRDEETV", asunto_email(usuario.getNomUsuario(),clave,persona.getPrimerNombre().substring(0, 1).toUpperCase() + persona.getPrimerNombre().substring(1).toLowerCase(),persona.getPrimerApellido().substring(0, 1).toUpperCase() + persona.getPrimerApellido().substring(1).toLowerCase()));
        //correos.start();

        
        persona = new Persona();
        municipio = new Municipio();
        departamento = new Departamento();
        this.items_departamento = new ArrayList();
        this.items_municipio = new ArrayList();
        codigodepartamento = "";
        codigomunicipio = "";

        PrimeFaces.current().ajax().update("registros");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informacion","Persona modificada con exito")); 
        PrimeFaces.current().executeScript("PF('dialogoModificar').hide();");
    }
    
    public void centrarDialogo() throws Exception
    {
        PrimeFaces.current().executeScript("PF('dialogoModificar').initPosition();");
    }

    public void validarPersonaDui() throws Exception
    {
        PersonaDao personaDao = new PersonaDao();
        
        if(validarDui(persona.getDui())== true)
        {
            if(personaDao.obtener_persona_dui(persona.getDui())!=null)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","DUI: " + persona.getDui() + " duplicado")); 
                persona.setDui("");
                PrimeFaces.current().ajax().update("F01:persona_dui");
            }
        }
        else
        {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","DUI: " + persona.getDui() + " invalido")); 
                persona.setDui("");
                PrimeFaces.current().ajax().update("F01:persona_dui");
        } 
    }
    
    public void validarPersonaModificarDui() throws Exception
    {
        PersonaDao personaDao = new PersonaDao();
        if(validarDui(personaSeleccionada.getDui())== true)
        {
            if(personaDao.validar_persona_modificar_dui(personaSeleccionada.getDui(), personaSeleccionada.getIdPersona()) != false)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","DUI: " + personaSeleccionada.getDui() + " duplicado")); 
                personaSeleccionada.setDui("");
                PrimeFaces.current().ajax().update("formulario_modificar:persona_dui");
            }
        }
        else
        {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","DUI: " + personaSeleccionada.getDui() + " invalido")); 
                personaSeleccionada.setDui("");
                PrimeFaces.current().ajax().update("F01:persona_dui");
        } 
        
    }
    
    public void validarPersonaEmail() throws Exception
    {
        PersonaDao personaDao = new PersonaDao();
        if(personaDao.obtener_persona_email(persona.getCorreoPersona())!=null)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","Email: " + persona.getCorreoPersona() + " duplicado")); 
            persona.setCorreoPersona("");
            PrimeFaces.current().ajax().update("F01:persona_email");
        }
    }
    
    
    public void validarPersonaModificarEmail() throws Exception
    {
        PersonaDao personaDao = new PersonaDao();
        if(personaDao.validar_persona_modificar_email(personaSeleccionada.getCorreoPersona(),personaSeleccionada.getIdPersona())==true)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","Email: " + personaSeleccionada.getCorreoPersona() + " duplicado")); 
            personaSeleccionada.setCorreoPersona("");
            PrimeFaces.current().ajax().update("formulario_modificar:persona_email");
        }
    }
    
    public void validarPersonaNit() throws Exception
    {
        PersonaDao personaDao = new PersonaDao();
        
        if(personaDao.obtener_persona_nit(persona.getNit())!=null)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","NIT: " + persona.getNit() + " duplicado")); 
            persona.setNit("");
            PrimeFaces.current().ajax().update("F01:persona_nit");
        }
    }
    
    public void validarPersonaModificarNit() throws Exception
    {
        PersonaDao personaDao = new PersonaDao();
        
        if(personaDao.validar_persona_modificar_nit(personaSeleccionada.getNit(), personaSeleccionada.getIdPersona()) != false)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","NIT: " + personaSeleccionada.getNit() + " duplicado")); 
            personaSeleccionada.setNit("");
            PrimeFaces.current().ajax().update("formulario_modificar:persona_nit");
        }
    }
    
    public List<Persona> lista_personas()
    {
        PersonaDao personaDao = new PersonaDao();
        try
        {
            return personaDao.obtener_personas();
        }
        catch(Exception x)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,x.toString(),x.toString()));
            return null;
        }
    }
    
    

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<SelectItem> getItems_municipio() throws Exception {
        
        this.items_municipio = new ArrayList();
        MunicipioDao municipios = new MunicipioDao();
        Departamento departamento_seleccionado = new Departamento();
        departamento_seleccionado.setCodDepto(codigodepartamento);
        List<Municipio> lista_municipios_por_dpto = municipios.obtener_municipios_por_id_del_departamento(departamento_seleccionado);
        this.items_municipio.clear();
        for(Municipio muni:lista_municipios_por_dpto)
        {
            SelectItem item = new SelectItem(muni.getCodMunicipio(),muni.getNomMunicipio());
            this.items_municipio.add(item);
        }
        return items_municipio;
    }

    public void setItems_municipio(List<SelectItem> items_municipio) {
        this.items_municipio = items_municipio;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<SelectItem> getItems_departamento() throws Exception {
        //this.codigomunicipio = "";
        //this.items_municipio = new ArrayList();
        this.items_departamento = new ArrayList();
        DepartamentoDao departamentos = new DepartamentoDao();
        List<Departamento> lista_departamentos = departamentos.obtener_todos_los_departamentos();
        this.items_departamento.clear();
        for(Departamento dep:lista_departamentos)
        {
            SelectItem item = new SelectItem(dep.getCodDepto(),dep.getNomDepto());
            this.items_departamento.add(item);
        }
        return items_departamento;
    }

    public void setItems_departamento(List<SelectItem> items_departamento) {
        this.items_departamento = items_departamento;
    }

    public String getCodigodepartamento() {
        return codigodepartamento;
    }

    public void setCodigodepartamento(String codigodepartamento) {
        this.codigodepartamento = codigodepartamento;
    }

    public String getCodigomunicipio() {
        return codigomunicipio;
    }
    
    public void setCodigomunicipio(String codigomunicipio) {
        this.codigomunicipio = codigomunicipio;
    }

    public void resetCodigoMunicipio() {
        codigomunicipio = "";
    }
    
    public void asignarMunicipio() throws Exception 
    {
        
        MunicipioDao municipioDao = new MunicipioDao();
        municipio = municipioDao.obtener_municipio(codigomunicipio);
        if(municipio!=null)
        {
            codigomunicipio = municipio.getCodMunicipio();
        }
        else
        {
            codigomunicipio= ""; 
        }
        
        //municipio.setCodMunicipio(codigomunicipio);
    }

    
 static String generarPassword(int n) 
    { 
        String AlphaNumericString = "ABCDEFGHJKMNPRSTUVWXYZ2356789abcdefghjkmnprstuvxyz$#%*+-&.-_"; 

        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++)
        { 

            int index = (int)(AlphaNumericString.length()* Math.random()); 
  
            sb.append(AlphaNumericString.charAt(index)); 
        } 
        return sb.toString(); 
    }
 
 private String asunto_email(String user, String password, String nombre, String apellido)
 {
     return "<h1>SIRDEETV</h1>\n" +
            "\n" +
            "<p>Bienvenid@ "+nombre+" " +apellido+", al Sistema Informático para Registro de Datos Epidemiológicos Ligados a Enfermedades Transmitidas por Vectores y su Evaluacion con Modelos Animales</p>\n" +
            "\n" +
            "<h4>Credenciales</h4>\n" +
            "<spam>Usuario: <strong>"+user+"</strong></spam><br/>\n" +
            "<spam>Contraseña: <strong>"+password+"</strong></spam>\n" +
            "\n" +
            "<br/><br/>\n" +
            "\n" +
            "<spam>- Se recomienda ingresar a SIRDEETV y cambiar la contraseña generada por defecto.</spam><br/>\n" +
            "<spam>- Si en algún momento olvida sus credenciales, contacte con el administrador de SIRDEETV.</spam>";
 }
 
 /*public void enviar_email(String destinatario, String asunto, String cuerpo) throws AddressException, MessagingException
 {
     try
     {
        String remitente = "mm09255@ues.edu.sv";
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", "miguel751991");    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        //message.setDataHandler(new DataHandler(new HTMLDataSource(cuerpo)));
        Address address = new InternetAddress(destinatario);
        try {
           message.setFrom(new InternetAddress(remitente));
           message.addRecipient(Message.RecipientType.TO, address);   //Se podrían añadir varios de la misma manera
           message.setSubject(asunto);
           //message.setText(cuerpo);
           message.setContent(cuerpo,"text/html");
           Transport transport = session.getTransport("smtp");
           transport.connect("smtp.gmail.com", remitente, "miguel751991");
           transport.sendMessage(message, message.getAllRecipients());
           transport.close();
        }
        catch (MessagingException me) {
           me.printStackTrace();   //Si se produce un error
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","Sin conexion, el email no ha sido enviado")); 
        }
     }
     catch(Exception x)
     {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia","Sin conexion, el email no ha sido enviado")); 
     }
    
 }*/

    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
        this.codigomunicipio = personaSeleccionada.getMunicipio().getCodMunicipio();
        this.codigodepartamento = personaSeleccionada.getMunicipio().getDepartamento().getCodDepto();
        listarolesconsulta = "";
        try
        {
            /*Usuario usuario = new Usuario();
            Rol rol = new Rol();
            
            Set roles = new HashSet(0);
            
            roles.add(rol);
            roles.add(rol);
            roles.add(rol);
            roles.add(rol);
            roles.add(rol);
            
            usuario.setRols(roles);*/
            
            this.personaSeleccionada.getUsuario().getRols().toArray();
            listaroles = new ArrayList<Rol>(this.personaSeleccionada.getUsuario().getRols());
            
            for(int i = 0 ;i<listaroles.size();i++)
            {
                listarolesconsulta = listarolesconsulta + listaroles.get(i).getNomRol();
                if(i+1!=listaroles.size())
                {
                    listarolesconsulta = listarolesconsulta +", ";
                }
            }
        }
        catch(Exception x)
        {
            System.out.println("\n\n\nEXcepcion: " + x.toString());
        }
        
    }

    public String getListarolesconsulta() {
        return listarolesconsulta;
    }

    public void setListarolesconsulta(String listarolesconsulta) {
        this.listarolesconsulta = listarolesconsulta;
    }

    public List<Rol> getListaroles() {
        return listaroles;
    }

    public void setListaroles(List<Rol> listaroles) {
        this.listaroles = listaroles;
    }
    
    
    
    
    
    
    public static boolean validarDui(String dui) {
        //String mensaje = "";
        boolean mensaje = false;
        if (dui.length() < 9) {
            return false;//"Caracteres incompletos en el DUI";
        }
        if (dui.length() > 10) {
            return false;//"Demasiados caracteres en el DUI";
        }

        String strDigitos = dui.substring(0, 8);
        int verificador = 0;
        char verifi=Character.MIN_VALUE;
        if (dui.contains("-")) {
            verifi=dui.substring(9, 10).charAt(0);
            if(Character.isDigit(verifi)){
                verificador = Integer.parseInt(dui.substring(9, 10));
            }
            else{
                return false;//"El DUI no puede tener letras";
            }
        } else {
            verifi=dui.substring(8, 9).charAt(0);
            if(Character.isDigit(verifi)){
                verificador = Integer.parseInt(dui.substring(8, 9));
            }
            else{
                return false;//"El DUI no puede tener letras";
            }
        }
        char[] charDui = strDigitos.toCharArray();
        int j = 9;
        int digito = 0, suma = 0, multiplicacion = 0, validaDigitos;

        for (int i = 0; i < charDui.length; i++) {
            if (Character.isDigit(charDui[i])) {
                digito = Character.getNumericValue(charDui[i]);
                multiplicacion = digito * j;
                suma += multiplicacion;
                j--;
            } else {
                return false;//"El DUI contiene caracteres incorrectos";
            }
        }
        int mod = suma % 10;
        if(mod==0 && verificador==0){
            //mensaje="OK";
            mensaje = true;
        }else{
            if ((10 - mod) == verificador) {
                //mensaje = "OK";
                mensaje = true;
            } else {
                //mensaje = "DUI no válido, favor verificar";
                mensaje = false;
            }
        }


        return mensaje;
    }
    
    
    
    
    
    
    
}
