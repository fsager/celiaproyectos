package ar.com.celia.seguimiento_alumnos.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.com.celia.seguimiento_alumnos.domain.*;
import ar.com.celia.seguimiento_alumnos.persistence.VwListadoNotasExamenAlumnoDao;
import ar.com.celia.common.persistence.util.DAOObject;

import org.hibernate.criterion.Order;
import org.hibernate.FetchMode;


/**
 * Home object for domain model class VwListadoNotasExamenAlumno.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.VwListadoNotasExamenAlumno
 * @author Hibernate Tools
 */
public class VwListadoNotasExamenAlumnoHome extends DAOObject implements VwListadoNotasExamenAlumnoDao {

    private static final Log log = LogFactory.getLog(VwListadoNotasExamenAlumnoHome.class);
    
    public void insert(VwListadoNotasExamenAlumno transientInstance) throws Exception {
        log.debug("persisting VwListadoNotasExamenAlumno instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(VwListadoNotasExamenAlumno transientInstance) throws Exception {
        log.debug("persisting VwListadoNotasExamenAlumno instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(VwListadoNotasExamenAlumno persistentInstance) throws Exception {
        log.debug("deleting VwListadoNotasExamenAlumno instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VwListadoNotasExamenAlumno get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting VwListadoNotasExamenAlumno instance with id: " + p_Id);
        try {
            VwListadoNotasExamenAlumno instance = (VwListadoNotasExamenAlumno) getHibernateTemplate()
                    .get(VwListadoNotasExamenAlumno.class, p_Id);
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
    
    public VwListadoNotasExamenAlumno get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
        log.debug("getting VwListadoNotasExamenAlumno instance with id: " + p_Id);
        try {
            Criteria cri = getSession().createCriteria(VwListadoNotasExamenAlumno.class);
            cri.add(Restrictions.idEq(p_Id));
            
        	if(falseLazy!=null)
        	{
        		for(int i=0;i<falseLazy.length;i++)
            	{
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            VwListadoNotasExamenAlumno instance =(VwListadoNotasExamenAlumno)cri.uniqueResult();
            
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
    
    public List<VwListadoNotasExamenAlumno> getAll(VwListadoNotasExamenAlumno p_example, String[] falseLazy) throws Exception {
        log.debug("finding VwListadoNotasExamenAlumno instance by example");
        try {
            Criteria cri = getSession().createCriteria(VwListadoNotasExamenAlumno.class);
            cri.add(Example.create(p_example).enableLike(MatchMode.ANYWHERE).ignoreCase());
            
            addDependenciesFilters(p_example, cri);
        	if(falseLazy != null) {
        		for(int i=0; i<falseLazy.length; i++) {
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	            
//            cri.addOrder(Order.asc(DEFINIR));
            List<VwListadoNotasExamenAlumno> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
