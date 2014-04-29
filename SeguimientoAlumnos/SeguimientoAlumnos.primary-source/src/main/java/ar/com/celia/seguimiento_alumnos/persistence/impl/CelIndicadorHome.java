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
import ar.com.celia.seguimiento_alumnos.domain.CelIndicador;
import ar.com.celia.seguimiento_alumnos.persistence.CelIndicadorDao;


/**
 * Home object for domain model class CelIndicador.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.CelIndicador
 * @author Hibernate Tools
 */
public class CelIndicadorHome extends DAOObject implements CelIndicadorDao {

    private static final Log log = LogFactory.getLog(CelIndicadorHome.class);
    
    public void insert(CelIndicador transientInstance) throws Exception {
        log.debug("persisting CelIndicador instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(CelIndicador transientInstance) throws Exception {
        log.debug("persisting CelIndicador instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(CelIndicador persistentInstance) throws Exception {
        log.debug("deleting CelIndicador instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public CelIndicador get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting CelIndicador instance with id: " + p_Id);
        try {
            CelIndicador instance = (CelIndicador) getHibernateTemplate()
                    .get(CelIndicador.class, p_Id);
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
    
    public CelIndicador get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
        log.debug("getting CelIndicador instance with id: " + p_Id);
        try {
            Criteria cri = getSession().createCriteria(CelIndicador.class);
            cri.add(Restrictions.idEq(p_Id));
            
        	if(falseLazy!=null)
        	{
        		for(int i=0;i<falseLazy.length;i++)
            	{
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            CelIndicador instance =(CelIndicador)cri.uniqueResult();
            
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
    
    public List<CelIndicador> getAll(CelIndicador p_example, String[] falseLazy) throws Exception {
        log.debug("finding CelIndicador instance by example");
        try {
            Criteria cri = getSession().createCriteria(CelIndicador.class);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            addDependenciesFilters(p_example, cri);
        	if(falseLazy != null) {
        		for(int i=0; i<falseLazy.length; i++) {
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	            
            cri.addOrder(Order.asc("indId"));
            List<CelIndicador> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
