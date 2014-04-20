package ar.com.celia.seguimiento_alumnos.persistence.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import ar.com.celia.seguimiento_alumnos.domain.*;
import ar.com.celia.seguimiento_alumnos.persistence.CelIndicadorUsuarioEstadoDao;
import ar.com.celia.common.persistence.util.DAOObject;
import org.hibernate.criterion.Order;
import org.hibernate.FetchMode;


/**
 * Home object for domain model class CelIndicadorUsuarioEstado.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.CelIndicadorUsuarioEstado
 * @author Hibernate Tools
 */
public class CelIndicadorUsuarioEstadoHome extends DAOObject implements CelIndicadorUsuarioEstadoDao {

    private static final Log log = LogFactory.getLog(CelIndicadorUsuarioEstadoHome.class);
    
    public void insert(CelIndicadorUsuarioEstado transientInstance) throws Exception {
        log.debug("persisting CelIndicadorUsuarioEstado instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(CelIndicadorUsuarioEstado transientInstance) throws Exception {
        log.debug("persisting CelIndicadorUsuarioEstado instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(CelIndicadorUsuarioEstado persistentInstance) throws Exception {
        log.debug("deleting CelIndicadorUsuarioEstado instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public CelIndicadorUsuarioEstado get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting CelIndicadorUsuarioEstado instance with id: " + p_Id);
        try {
            CelIndicadorUsuarioEstado instance = (CelIndicadorUsuarioEstado) getHibernateTemplate()
                    .get(CelIndicadorUsuarioEstado.class, p_Id);
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
    
    public CelIndicadorUsuarioEstado get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
        log.debug("getting CelIndicadorUsuarioEstado instance with id: " + p_Id);
        try {
            Criteria cri = getSession().createCriteria(CelIndicadorUsuarioEstado.class);
            cri.add(Restrictions.idEq(p_Id));
            
        	if(falseLazy!=null)
        	{
        		for(int i=0;i<falseLazy.length;i++)
            	{
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            CelIndicadorUsuarioEstado instance =(CelIndicadorUsuarioEstado)cri.uniqueResult();
            
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
    
    public List<CelIndicadorUsuarioEstado> getAll(CelIndicadorUsuarioEstado p_example, String[] falseLazy) throws Exception {
        log.debug("finding CelIndicadorUsuarioEstado instance by example");
        try {
            Criteria cri = getSession().createCriteria(CelIndicadorUsuarioEstado.class);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            addDependenciesFilters(p_example, cri);
        	if(falseLazy != null) {
        		for(int i=0; i<falseLazy.length; i++) {
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	            
            cri.addOrder(Order.asc("iueId"));
            List<CelIndicadorUsuarioEstado> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
