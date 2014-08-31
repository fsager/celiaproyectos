package ar.com.celia.seguimiento_alumnos.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.com.celia.seguimiento_alumnos.domain.*;
import ar.com.celia.seguimiento_alumnos.persistence.VwListadoNotasTpAlumnoDao;
import ar.com.celia.common.persistence.util.DAOObject;

import org.hibernate.criterion.Order;
import org.hibernate.FetchMode;


/**
 * Home object for domain model class VwListadoNotasTpAlumno.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.VwListadoNotasTpAlumno
 * @author Hibernate Tools
 */
public class VwListadoNotasTpAlumnoHome extends DAOObject implements VwListadoNotasTpAlumnoDao {

    private static final Log log = LogFactory.getLog(VwListadoNotasTpAlumnoHome.class);
    
    public void insert(VwListadoNotasTpAlumno transientInstance) throws Exception {
        log.debug("persisting VwListadoNotasTpAlumno instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(VwListadoNotasTpAlumno transientInstance) throws Exception {
        log.debug("persisting VwListadoNotasTpAlumno instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(VwListadoNotasTpAlumno persistentInstance) throws Exception {
        log.debug("deleting VwListadoNotasTpAlumno instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VwListadoNotasTpAlumno get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting VwListadoNotasTpAlumno instance with id: " + p_Id);
        try {
            VwListadoNotasTpAlumno instance = (VwListadoNotasTpAlumno) getHibernateTemplate()
                    .get(VwListadoNotasTpAlumno.class, p_Id);
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
    
    public VwListadoNotasTpAlumno get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
        log.debug("getting VwListadoNotasTpAlumno instance with id: " + p_Id);
        try {
            Criteria cri = getSession().createCriteria(VwListadoNotasTpAlumno.class);
            cri.add(Restrictions.idEq(p_Id));
            
        	if(falseLazy!=null)
        	{
        		for(int i=0;i<falseLazy.length;i++)
            	{
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            VwListadoNotasTpAlumno instance =(VwListadoNotasTpAlumno)cri.uniqueResult();
            
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
    
    public List<VwListadoNotasTpAlumno> getAll(VwListadoNotasTpAlumno p_example, String[] falseLazy) throws Exception {
        log.debug("finding VwListadoNotasTpAlumno instance by example");
        try {
            Criteria cri = getSession().createCriteria(VwListadoNotasTpAlumno.class);
            cri.add(Example.create(p_example).enableLike(MatchMode.ANYWHERE).ignoreCase());
            
            addDependenciesFilters(p_example, cri);
        	if(falseLazy != null) {
        		for(int i=0; i<falseLazy.length; i++) {
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	            
//            cri.addOrder(Order.asc(DEFINIR));
            List<VwListadoNotasTpAlumno> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
