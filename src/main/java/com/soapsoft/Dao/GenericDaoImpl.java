package com.soapsoft.Dao;

import com.soapsoft.Util.HibernateUtil;
import com.soapsoft.Util.LlaveValor;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;

/**
 * @author hdcardenas dropimax@gmail.com
 * @param <T>
 * @param <E>
 */
public class GenericDaoImpl<T, E extends Serializable> implements GenericDao<T, E>  {

    private final Class<T> clazz;
    protected Session session;
    protected Transaction tx;

    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        this.clazz = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass())
                            .getActualTypeArguments()[0];
        
        this.session = HibernateUtil.getSessionFactory().openSession();
        ThreadLocalSessionContext.bind(session);
    }
    
    @Override
    public E create(T entity) {
        E id = null;
        try {
            startOperation();
            id = (E) session.save(entity);
            Commit();
        } catch (HibernateException e) {
            Rollback();
            throw e;
        }       
        return  id;
    }
    
    @Override
    public void delete(T entity) {
        try {
            startOperation();
            session.delete(entity);
            Commit();
        } catch (HibernateException e) {
            Rollback();
            throw e;
        }      
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void update(T entity) {
        try {
            startOperation();
            session.merge(entity);
            Commit();
        } catch (HibernateException e) {
            Rollback();
            throw e;
        } 
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        List <T> list;
        try {
            startOperation();
            list = session.createCriteria(clazz).list();
        } catch (HibernateException e) {
            list = null ;
            throw e;
        } 
        return list;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findList(int pageNo, int pageSize) {
        List <T> list;
        try {
            startOperation();
            list = session.createCriteria(clazz)
                          .setFirstResult((pageNo - 1) * pageSize)
                          .setMaxResults(pageSize)
                          .list();
        } catch (HibernateException e) {
            list = null ;
            throw e;
        }  
        return list;
    }
    
    @Override
    public int getCountOfAll() {
        Long count;
        try {
            startOperation();
            count = (Long) HibernateUtil.getSession()
                                           .createCriteria(clazz)
                                           .setProjection(Projections.rowCount())
                                           .uniqueResult();
        } catch (HibernateException e) {
            count = null;
            throw e;
        } 
        if (null == count) {
            return 0;
        } else {
            return count.intValue();
        }
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T findById(Serializable id) {
        T obj;
        try {
            startOperation();
            obj = (T) session.get(clazz, id);
        } catch (HibernateException e) {
            obj = null;
            throw e;
        }        
        return  obj;
    }
    
    private void Rollback(){
        if(tx != null){
             tx.rollback();
        }
    }
    
    private void Commit(){
       if( tx != null ){
           tx.commit(); 
       }  
    } 
    
    public void Close(){
      if(session != null){
          session.flush();
      }
    }
    
    protected void startOperation() throws HibernateException {
        if(session == null){
           session = HibernateUtil.getSession();
        }
        
        if(tx == null ){            
           tx = session.beginTransaction();
        }else{
          if(!tx.isActive()){
             tx = session.beginTransaction();
          } 
        }
    }
    
    protected List<Map<String,Object>> Query(String sql, ArrayList<LlaveValor> parametros){
         
          startOperation();
          
          Query query = session.createSQLQuery(sql);
          
          for (LlaveValor parametro : parametros) {
              query.setParameter(parametro.getKey(), parametro.getValue());
          }
          
          query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
          List<Map<String,Object>> resultado = query.list();
          
          return resultado;
 
      }
    
     protected List<T> Query(String sql, ArrayList<LlaveValor> parametros,Class<T> clase){
         
          startOperation();
          List <T> list ;
          
          Query query = session.createSQLQuery(sql);
          
          for (LlaveValor parametro : parametros) {
              query.setParameter(parametro.getKey(), parametro.getValue());
          }
          
          query.setResultTransformer(Transformers.aliasToBean(clase));
          
          list = query.list() ;
          
          return list;
 
      }
    
    
    
}