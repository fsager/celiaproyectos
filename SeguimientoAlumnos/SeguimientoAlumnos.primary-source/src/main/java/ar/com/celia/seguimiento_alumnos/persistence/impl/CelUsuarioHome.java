package ar.com.celia.seguimiento_alumnos.persistence.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import ar.com.celia.seguimiento_alumnos.domain.*;
import ar.com.celia.seguimiento_alumnos.persistence.CelUsuarioDao;
import ar.com.celia.common.persistence.util.DAOObject;
import org.hibernate.criterion.Order;
import org.hibernate.FetchMode;


/**
 * Home object for domain model class CelUsuario.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.CelUsuario
 * @author Hibernate Tools
 */
public class CelUsuarioHome extends DAOObject implements CelUsuarioDao {

    private static final Log log = LogFactory.getLog(CelUsuarioHome.class);
    
    public void insert(CelUsuario transientInstance) throws Exception {
        log.debug("persisting CelUsuario instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(CelUsuario transientInstance) throws Exception {
        log.debug("persisting CelUsuario instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(CelUsuario persistentInstance) throws Exception {
        log.debug("deleting CelUsuario instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public CelUsuario get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting CelUsuario instance with id: " + p_Id);
        try {
            CelUsuario instance = (CelUsuario) getHibernateTemplate()
                    .get(CelUsuario.class, p_Id);
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
    
    public CelUsuario get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
        log.debug("getting CelUsuario instance with id: " + p_Id);
        try {
            Criteria cri = getSession().createCriteria(CelUsuario.class);
            cri.add(Restrictions.idEq(p_Id));
            
        	if(falseLazy!=null)
        	{
        		for(int i=0;i<falseLazy.length;i++)
            	{
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            CelUsuario instance =(CelUsuario)cri.uniqueResult();
            
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
    
    public List<CelUsuario> getAll(CelUsuario p_example, String[] falseLazy) throws Exception {
        log.debug("finding CelUsuario instance by example");
        try {
            Criteria cri = getSession().createCriteria(CelUsuario.class);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            addDependenciesFilters(p_example, cri);
        	if(falseLazy != null) {
        		for(int i=0; i<falseLazy.length; i++) {
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	            
            cri.addOrder(Order.asc("usrId"));
            List<CelUsuario> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
