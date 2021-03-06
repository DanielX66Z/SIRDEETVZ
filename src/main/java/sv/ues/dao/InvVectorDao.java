/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.dao;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sv.ues.dominio.InvVector;
import sv.ues.dominio.TipoMuestra;

import sv.ues.utils.HibernateUtil;

/**
 *
 * @author PC
 */
public class InvVectorDao {
     private HibernateUtil hibernateUtil = new HibernateUtil();
    private SessionFactory sessionFactory = hibernateUtil. getSessionFactory();
    private Session sesion;
    private Transaction tx;
    
     private static final Logger LOGGER = Logger.getLogger(InvestigacionDao.class.getName());
    
    private void iniciaOperacion() throws HibernateException 
    {
	sesion = sessionFactory.openSession() ;
	tx = sesion.beginTransaction() ;
    }
    
    private void manejaExcepcion(HibernateException he) throws HibernateException 
    {
	tx.rollback();
	throw new HibernateException( "Ocurrió un error en la capa DAO" , he);
    }
    
    public void guardar(InvVector invVector){
       try {
            iniciaOperacion();
            sesion.save(invVector);
            sesion.flush();
            tx.commit();

        } catch (HibernateException he) {
            tx.rollback();
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
        
    }
    
    
    public InvVector getInvVectorById(int id){
        try
        {
            iniciaOperacion();
            CriteriaBuilder builder = sesion.getCriteriaBuilder();
            
            CriteriaQuery<InvVector> query = builder.createQuery(InvVector.class);
            Root<InvVector> root = query.from(InvVector.class);
            query.select(root).where(builder.equal(root.get("idInvVector"), id));
            Query<InvVector> q =sesion.createQuery(query);
            return q.getSingleResult();
        }
        catch(Exception x)
        {
            return null;
        }
        finally 
        {
            sesion.close();
        }
    }
    
    public List<InvVector> getListInvVector() {

        try {
            iniciaOperacion();
            CriteriaQuery criteria = sesion.getCriteriaBuilder().createQuery(InvVector.class);//Roles  .class);
            criteria.from(InvVector.class);
            List<InvVector> lsTipo = sesion.createQuery(criteria).getResultList();
            //sesion.close();
            return lsTipo;
        } catch (HibernateException e) {
            throw e;
        } finally {
            sesion.close();

        }
    }
}
