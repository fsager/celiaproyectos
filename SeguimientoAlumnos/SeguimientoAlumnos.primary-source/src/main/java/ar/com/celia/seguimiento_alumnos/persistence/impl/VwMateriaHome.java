package ar.com.celia.seguimiento_alumnos.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import ar.com.celia.seguimiento_alumnos.domain.*;
import ar.com.celia.seguimiento_alumnos.persistence.VwMateriaDao;
import ar.com.celia.common.persistence.util.DAOObject;

import org.hibernate.criterion.Order;
import org.hibernate.FetchMode;


/**
 * Home object for domain model class VwMateria.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.VwMateria
 * @author Hibernate Tools
 */
public class VwMateriaHome extends DAOObject implements VwMateriaDao {

    private static final Log log = LogFactory.getLog(VwMateriaHome.class);
    
    public void insert(VwMateria transientInstance) throws Exception {
        log.debug("persisting VwMateria instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(VwMateria transientInstance) throws Exception {
        log.debug("persisting VwMateria instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(VwMateria persistentInstance) throws Exception {
        log.debug("deleting VwMateria instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VwMateria get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting VwMateria instance with id: " + p_Id);
        try {
            VwMateria instance = (VwMateria) getHibernateTemplate()
                    .get(VwMateria.class, p_Id);
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
    
    public VwMateria get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
        log.debug("getting VwMateria instance with id: " + p_Id);
        try {
            Criteria cri = getSession().createCriteria(VwMateria.class);
            cri.add(Restrictions.idEq(p_Id));
            
        	if(falseLazy!=null)
        	{
        		for(int i=0;i<falseLazy.length;i++)
            	{
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            VwMateria instance =(VwMateria)cri.uniqueResult();
            
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
    
    public List<VwMateria> getAll(VwMateria p_example, String[] falseLazy) throws Exception {
        log.debug("finding VwMateria instance by example");
        try {
            Criteria cri = getSession().createCriteria(VwMateria.class);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            addDependenciesFilters(p_example, cri);
        	if(falseLazy != null) {
        		for(int i=0; i<falseLazy.length; i++) {
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            cri.addOrder(Order.asc("matOrden"));
            cri.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
            @SuppressWarnings("unchecked")
			List<VwMateria> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
