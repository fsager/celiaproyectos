package ar.com.celia.seguimiento_alumnos.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ar.com.celia.common.persistence.util.DAOObject;
import ar.com.celia.seguimiento_alumnos.domain.VwEtapa;
import ar.com.celia.seguimiento_alumnos.persistence.VwEtapaDao;


/**
 * Home object for domain model class VwEtapa.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.VwEtapa
 * @author Hibernate Tools
 */
public class VwEtapaHome extends DAOObject implements VwEtapaDao {

    private static final Log log = LogFactory.getLog(VwEtapaHome.class);
    
    public void insert(VwEtapa transientInstance) throws Exception {
        log.debug("persisting VwEtapa instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(VwEtapa transientInstance) throws Exception {
        log.debug("persisting VwEtapa instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(VwEtapa persistentInstance) throws Exception {
        log.debug("deleting VwEtapa instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VwEtapa get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting VwEtapa instance with id: " + p_Id);
        try {
            VwEtapa instance = (VwEtapa) getHibernateTemplate()
                    .get(VwEtapa.class, p_Id);
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
    
    public VwEtapa get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
        log.debug("getting VwEtapa instance with id: " + p_Id);
        try {
            Criteria cri = getSession().createCriteria(VwEtapa.class);
            cri.add(Restrictions.idEq(p_Id));
            
        	if(falseLazy!=null)
        	{
        		for(int i=0;i<falseLazy.length;i++)
            	{
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            VwEtapa instance =(VwEtapa)cri.uniqueResult();
            
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
    
    public List<VwEtapa> getAll(VwEtapa p_example, String[] falseLazy) throws Exception {
        log.debug("finding VwEtapa instance by example");
        try {
            Criteria cri = getSession().createCriteria(VwEtapa.class);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            addDependenciesFilters(p_example, cri);
        	if(falseLazy != null) {
        		for(int i=0; i<falseLazy.length; i++) {
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	            
            cri.addOrder(Order.asc("etpId"));
            @SuppressWarnings("unchecked")
			List<VwEtapa> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
