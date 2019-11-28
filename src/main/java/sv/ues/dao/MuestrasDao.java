/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sv.ues.dominio.Muestra;
import sv.ues.utils.HibernateUtil;



/**
 *
 * @author Flor Diaz
 */
public class MuestrasDao {
    private HibernateUtil hibernateUtil = new HibernateUtil();
    private SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
    private Session sesion;
    private Transaction tx;

    private void iniciaOperacion() throws HibernateException 
    {
        sesion = null;
        tx=null;
        try 
        {
            sesion = sessionFactory.openSession();
            tx = sesion.beginTransaction();
        }
        catch(Throwable t)
        {
            System.err.println("Exception while getting session.. ");
            t.printStackTrace();
        }

    }

    private void manejaExcepcion(HibernateException he) throws HibernateException
    {
        tx.rollback();
        throw new HibernateException("Ocurrio un error en la capa DAO", he);
    }    
    
    /**
     * Devuelve una lista de las muestras pertenecientes a ID de lote
     * @param idLote
     * @return Lista de Muestras
     */
    public List<Muestra> muestra_por_lote(Integer idLote) {
         try {
            iniciaOperacion();
            CriteriaBuilder cb = sesion.getCriteriaBuilder();
            CriteriaQuery<Muestra> query = cb.createQuery(Muestra.class);
            Root<Muestra> root = query.from(Muestra.class);
            query.select(root).where(cb.equal(root.get("lote"), idLote));
            Query<Muestra> q = sesion.createQuery(query);
            return q.getResultList();
        } catch (HibernateException e) {
            throw e;
        } finally {
            tx.commit();
            sesion.close();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
