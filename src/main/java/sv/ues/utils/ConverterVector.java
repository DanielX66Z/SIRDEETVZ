/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import sv.ues.dao.VectorDao;
import sv.ues.dominio.Laboratorio;
import sv.ues.dominio.Vector;

/**
 *
 * @author PC
 */
@ManagedBean
public class ConverterVector implements Converter{

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        try{
            
            VectorDao vectorDao=new VectorDao();
            if(value == null || value.length() == 0 || JsfUtil.isDummySelectItem(uic, value)){
                return null;
            }
            return vectorDao.findByVectorById(Integer.parseInt(value));
        }catch(Throwable ex){
            return null;
        }
    }

   String getStringKey(java.lang.Integer value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Vector) {
            Vector o = (Vector) object;
            return getStringKey(o.getCodVector());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Vector.class.getName()});
            return null;
        }
    }
}
