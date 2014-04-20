package ar.com.celia.seguimiento_alumnos.persistence.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import ar.com.celia.seguimiento_alumnos.domain.*;
import ar.com.celia.seguimiento_alumnos.persistence.CelGrupoUsuarioDao;
import ar.com.celia.common.persistence.util.DAOObject;
import org.hibernate.criterion.Order;
import org.hibernate.FetchMode;


/**
 * Home object for domain model class CelGrupoUsuario.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.CelGrupoUsuario
 * @author Hibernate Tools
 */
public class CelGrupoUsuarioHome extends DAOObject implements CelGrupoUsuarioDao {

    private static final Log log = LogFactory.getLog(CelGrupoUsuarioHome.class);
    
    public void insert(CelGrupoUsuario transientInstance) throws Exception {
        log.debug("persisting CelGrupoUsuario instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(CelGrupoUsuario transientInstance) throws Exception {
        log.debug("persisting CelGrupoUsuario instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(CelGrupoUsuario persistentInstance) throws Exception {
        log.debug("deleting CelGrupoUsuario instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public CelGrupoUsuario get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting CelGrupoUsuario instance with id: " + p_Id);
        try {
            CelGrupoUsuario instance = (CelGrupoUsuario) getHibernateTemplate()
                    .get(CelGrupoUsuario.class, p_Id);
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
    
    public CelGrupoUsuario get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
        log.debug("getting CelGrupoUsuario instance with id: " + p_Id);
        try {
            Criteria cri = getSession().createCriteria(CelGrupoUsuario.class);
            cri.add(Restrictions.idEq(p_Id));
            
        	if(falseLazy!=null)
        	{
        		for(int i=0;i<falseLazy.length;i++)
            	{
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            CelGrupoUsuario instance =(CelGrupoUsuario)cri.uniqueResult();
            
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
    
    public List<CelGrupoUsuario> getAll(CelGrupoUsuario p_example, String[] falseLazy) throws Exception {
        log.debug("finding CelGrupoUsuario instance by example");
        try {
            Criteria cri = getSession().createCriteria(CelGrupoUsuario.class);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            addDependenciesFilters(p_example, cri);
        	if(falseLazy != null) {
        		for(int i=0; i<falseLazy.length; i++) {
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	            
            cri.addOrder(Order.asc("gruId"));
            List<CelGrupoUsuario> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
