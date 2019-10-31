/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.dao;

import sv.ues.utils.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sv.ues.dominio.Departamento;

/**
 *
 * @author Miguel
 */
public class DepartamentoDao 
{
    private HibernateUtil hibernateUtil = new HibernateUtil();
    private SessionFactory sessionFactory = hibernateUtil. getSessionFactory();
    private Session sesion;
    private Transaction tx;
    
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
    
    public List<Departamento> obtener_todos_los_departamentos() throws Exception
    {
        try{
        iniciaOperacion();
        CriteriaQuery criteria = sesion.getCriteriaBuilder().createQuery(Departamento.class);
        criteria.from(Departamento.class);
        List<Departamento> departamentos = sesion.createQuery(criteria).getResultList();
        return departamentos;
        }catch(HibernateException e){
            throw e;
        }finally{
        sesion.close();
        
        }
        
        /*iniciaOperacion();
        Criteria criteria = sesion.createCriteria(Departamento.class);
        List<Departamento> departamentos =(List<Departamento>) criteria.list();
        sesion.close();
	return departamentos;*/
    }
    
}
