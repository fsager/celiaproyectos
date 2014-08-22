package ar.com.celia.seguimiento_alumnos.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.com.celia.common.persistence.util.DAOObject;
import ar.com.celia.seguimiento_alumnos.domain.VwNotasAlumno;
import ar.com.celia.seguimiento_alumnos.persistence.VwNotasAlumnoDao;


/**
 * Home object for domain model class VwNotasAlumno.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.VwNotasAlumno
 * @author Hibernate Tools
 */
public class VwNotasAlumnoHome extends DAOObject implements VwNotasAlumnoDao {

    private static final Log log = LogFactory.getLog(VwNotasAlumnoHome.class);
    
    public void insert(VwNotasAlumno transientInstance) throws Exception {
        log.debug("persisting VwNotasAlumno instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(VwNotasAlumno transientInstance) throws Exception {
        log.debug("persisting VwNotasAlumno instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(VwNotasAlumno persistentInstance) throws Exception {
        log.debug("deleting VwNotasAlumno instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VwNotasAlumno get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting VwNotasAlumno instance with id: " + p_Id);
        try {
            VwNotasAlumno instance = (VwNotasAlumno) getHibernateTemplate()
                    .get(VwNotasAlumno.class, p_Id);
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
    
    public VwNotasAlumno get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
        log.debug("getting VwNotasAlumno instance with id: " + p_Id);
        try {
            Criteria cri = getSession().createCriteria(VwNotasAlumno.class);
            cri.add(Restrictions.idEq(p_Id));
            
        	if(falseLazy!=null)
        	{
        		for(int i=0;i<falseLazy.length;i++)
            	{
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            VwNotasAlumno instance =(VwNotasAlumno)cri.uniqueResult();
            
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
    
    public List<VwNotasAlumno> getAll(VwNotasAlumno p_example, String[] falseLazy) throws Exception {
        log.debug("finding VwNotasAlumno instance by example");
        try {
            Criteria cri = getSession().createCriteria(VwNotasAlumno.class);
            cri.add(Example.create(p_example).enableLike(MatchMode.ANYWHERE).ignoreCase());
            
            addDependenciesFilters(p_example, cri);
        	
            if(falseLazy != null)
        		for(int i=0; i<falseLazy.length; i++)
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
        	            
//            cri.addOrder(Order.asc(DEFINIR));
            @SuppressWarnings("unchecked")
			List<VwNotasAlumno> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
