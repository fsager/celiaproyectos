package ar.com.celia.seguimiento_alumnos.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import ar.com.celia.seguimiento_alumnos.domain.*;
import ar.com.celia.seguimiento_alumnos.persistence.CelInteraccionCasoDao;
import ar.com.celia.common.persistence.util.DAOObject;

import org.hibernate.criterion.Order;
import org.hibernate.FetchMode;


/**
 * Home object for domain model class CelInteraccionCaso.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.CelInteraccionCaso
 * @author Hibernate Tools
 */
public class CelInteraccionCasoHome extends DAOObject implements CelInteraccionCasoDao {

    private static final Log log = LogFactory.getLog(CelInteraccionCasoHome.class);
    
    public void insert(CelInteraccionCaso transientInstance) throws Exception {
        log.debug("persisting CelInteraccionCaso instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(CelInteraccionCaso transientInstance) throws Exception {
        log.debug("persisting CelInteraccionCaso instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(CelInteraccionCaso persistentInstance) throws Exception {
        log.debug("deleting CelInteraccionCaso instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public CelInteraccionCaso get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting CelInteraccionCaso instance with id: " + p_Id);
        try {
            CelInteraccionCaso instance = (CelInteraccionCaso) getHibernateTemplate()
                    .get(CelInteraccionCaso.class, p_Id);
            if (instance==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public CelInteraccionCaso get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
        log.debug("getting CelInteraccionCaso instance with id: " + p_Id);
        try {
            Criteria cri = getSession().createCriteria(CelInteraccionCaso.class);
            cri.add(Restrictions.idEq(p_Id));
            
        	if(falseLazy!=null)
        	{
        		for(int i=0;i<falseLazy.length;i++)
            	{
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            CelInteraccionCaso instance =(CelInteraccionCaso)cri.uniqueResult();
            
            if (instance==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public List<CelInteraccionCaso> getAll(CelInteraccionCaso p_example, String[] falseLazy) throws Exception {
        log.debug("finding CelInteraccionCaso instance by example");
        try {
            Criteria cri = getSession().createCriteria(CelInteraccionCaso.class);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            addDependenciesFilters(p_example, cri);
        	if(falseLazy != null) {
        		for(int i=0; i<falseLazy.length; i++) {
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	            
            cri.addOrder(Order.asc("casId"));
            List<CelInteraccionCaso> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
    
    public Long insertInteraccionCaso(CelInteraccionCaso transientInstance) throws Exception {
    	
	    Session session = this.getSession();
	    try{
	    	
		    Transaction tran=session.beginTransaction();
		    session.save(transientInstance);  
		    session.flush(); // I forgot this from the previous post  
		    Long idTransacionCaso = transientInstance.getCasId(); // returns the id Hibernate assigned for your car
		    tran.commit();
		    
		    return idTransacionCaso;
	    
    	}catch(Exception e)
    	{
    		throw e; 
    	}finally{
    		//if(session!=null)session.close();
    	}
    }
    
    public int getCantidadContactos(Long aluId) throws Exception {
    	 try {
    	   	
	    	 Criteria cri = getSession().createCriteria(CelInteraccionCaso.class);
	         cri.add(Restrictions.eq("aluId", aluId));
	         List<CelInteraccionCaso> results = cri.list();
	         
	         if(results!=null && !results.isEmpty()){
	        	 return results.size(); 
	         }
	         else
	         {
	        	 return 0;
	         }
    	 }
         catch (RuntimeException re) {
             log.error("find by example failed", re);
             throw re;
         }
    }
}
