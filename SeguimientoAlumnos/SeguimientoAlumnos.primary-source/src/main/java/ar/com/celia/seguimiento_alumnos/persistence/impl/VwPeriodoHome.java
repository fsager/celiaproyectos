package ar.com.celia.seguimiento_alumnos.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ar.com.celia.common.persistence.util.DAOObject;
import ar.com.celia.seguimiento_alumnos.domain.VwPeriodo;
import ar.com.celia.seguimiento_alumnos.persistence.VwPeriodoDao;


/**
 * Home object for domain model class VwPeriodo.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.VwPeriodo
 * @author Hibernate Tools
 */
public class VwPeriodoHome extends DAOObject implements VwPeriodoDao {

    private static final Log log = LogFactory.getLog(VwPeriodoHome.class);
    
    public void insert(VwPeriodo transientInstance) throws Exception {
        log.debug("persisting VwPeriodo instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(VwPeriodo transientInstance) throws Exception {
        log.debug("persisting VwPeriodo instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(VwPeriodo persistentInstance) throws Exception {
        log.debug("deleting VwPeriodo instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VwPeriodo get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting VwPeriodo instance with id: " + p_Id);
        try {
            VwPeriodo instance = (VwPeriodo) getHibernateTemplate()
                    .get(VwPeriodo.class, p_Id);
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
    
    public VwPeriodo get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
        log.debug("getting VwPeriodo instance with id: " + p_Id);
        try {
            Criteria cri = getSession().createCriteria(VwPeriodo.class);
            cri.add(Restrictions.idEq(p_Id));
            
        	if(falseLazy!=null)
        	{
        		for(int i=0;i<falseLazy.length;i++)
            	{
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            VwPeriodo instance =(VwPeriodo)cri.uniqueResult();
            
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
    
    public List<VwPeriodo> getAll(VwPeriodo p_example, String[] falseLazy) throws Exception {
        log.debug("finding VwPeriodo instance by example");
        try {
            Criteria cri = getSession().createCriteria(VwPeriodo.class);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            addDependenciesFilters(p_example, cri);
        	if(falseLazy != null) {
        		for(int i=0; i<falseLazy.length; i++) {
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
            cri.addOrder(Order.asc("perOrden"));
            cri.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
            @SuppressWarnings("unchecked")
			List<VwPeriodo> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
