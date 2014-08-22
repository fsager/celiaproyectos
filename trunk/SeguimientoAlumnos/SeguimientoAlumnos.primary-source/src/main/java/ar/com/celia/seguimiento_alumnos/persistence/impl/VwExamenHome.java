package ar.com.celia.seguimiento_alumnos.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import ar.com.celia.seguimiento_alumnos.domain.*;
import ar.com.celia.seguimiento_alumnos.persistence.VwExamenDao;
import ar.com.celia.common.persistence.util.DAOObject;

import org.hibernate.criterion.Order;
import org.hibernate.FetchMode;


/**
 * Home object for domain model class VwExamen.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.VwExamen
 * @author Hibernate Tools
 */
public class VwExamenHome extends DAOObject implements VwExamenDao {

    private static final Log log = LogFactory.getLog(VwExamenHome.class);
    
    public void insert(VwExamen transientInstance) throws Exception {
        log.debug("persisting VwExamen instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(VwExamen transientInstance) throws Exception {
        log.debug("persisting VwExamen instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(VwExamen persistentInstance) throws Exception {
        log.debug("deleting VwExamen instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VwExamen get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting VwExamen instance with id: " + p_Id);
        try {
            VwExamen instance = (VwExamen) getHibernateTemplate()
                    .get(VwExamen.class, p_Id);
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
    
    public VwExamen get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
        log.debug("getting VwExamen instance with id: " + p_Id);
        try {
            Criteria cri = getSession().createCriteria(VwExamen.class);
            cri.add(Restrictions.idEq(p_Id));
            
        	if(falseLazy!=null)
        	{
        		for(int i=0;i<falseLazy.length;i++)
            	{
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            VwExamen instance =(VwExamen)cri.uniqueResult();
            
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
    
    public List<VwExamen> getAll(VwExamen p_example, String[] falseLazy) throws Exception {
        log.debug("finding VwExamen instance by example");
        try {
            Criteria cri = getSession().createCriteria(VwExamen.class);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            addDependenciesFilters(p_example, cri);
        	if(falseLazy != null) {
        		for(int i=0; i<falseLazy.length; i++) {
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	            
            cri.addOrder(Order.asc("exId"));
            @SuppressWarnings("unchecked")
			List<VwExamen> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
