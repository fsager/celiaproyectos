package ar.com.celia.seguimiento_alumnos.persistence.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import ar.com.celia.seguimiento_alumnos.domain.*;
import ar.com.celia.seguimiento_alumnos.persistence.CelPropiedadDao;
import ar.com.celia.common.persistence.util.DAOObject;
import org.hibernate.criterion.Order;
import org.hibernate.FetchMode;


/**
 * Home object for domain model class CelPropiedad.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.CelPropiedad
 * @author Hibernate Tools
 */
public class CelPropiedadHome extends DAOObject implements CelPropiedadDao {

    private static final Log log = LogFactory.getLog(CelPropiedadHome.class);
    
    public void insert(CelPropiedad transientInstance) throws Exception {
        log.debug("persisting CelPropiedad instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(CelPropiedad transientInstance) throws Exception {
        log.debug("persisting CelPropiedad instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(CelPropiedad persistentInstance) throws Exception {
        log.debug("deleting CelPropiedad instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public CelPropiedad get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting CelPropiedad instance with id: " + p_Id);
        try {
            CelPropiedad instance = (CelPropiedad) getHibernateTemplate()
                    .get(CelPropiedad.class, p_Id);
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
    
    public CelPropiedad get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
        log.debug("getting CelPropiedad instance with id: " + p_Id);
        try {
            Criteria cri = getSession().createCriteria(CelPropiedad.class);
            cri.add(Restrictions.idEq(p_Id));
            
        	if(falseLazy!=null)
        	{
        		for(int i=0;i<falseLazy.length;i++)
            	{
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            CelPropiedad instance =(CelPropiedad)cri.uniqueResult();
            
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
    
    public List<CelPropiedad> getAll(CelPropiedad p_example, String[] falseLazy) throws Exception {
        log.debug("finding CelPropiedad instance by example");
        try {
            Criteria cri = getSession().createCriteria(CelPropiedad.class);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            addDependenciesFilters(p_example, cri);
        	if(falseLazy != null) {
        		for(int i=0; i<falseLazy.length; i++) {
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	            
            cri.addOrder(Order.asc("proClave"));
            List<CelPropiedad> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
