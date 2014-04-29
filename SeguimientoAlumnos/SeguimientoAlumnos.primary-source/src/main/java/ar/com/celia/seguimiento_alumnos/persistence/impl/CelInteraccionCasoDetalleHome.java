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
import ar.com.celia.seguimiento_alumnos.domain.CelInteraccionCasoDetalle;
import ar.com.celia.seguimiento_alumnos.persistence.CelInteraccionCasoDetalleDao;


/**
 * Home object for domain model class CelInteraccionCasoDetalle.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.CelInteraccionCasoDetalle
 * @author Hibernate Tools
 */
public class CelInteraccionCasoDetalleHome extends DAOObject implements CelInteraccionCasoDetalleDao {

    private static final Log log = LogFactory.getLog(CelInteraccionCasoDetalleHome.class);
    
    public void insert(CelInteraccionCasoDetalle transientInstance) throws Exception {
        log.debug("persisting CelInteraccionCasoDetalle instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(CelInteraccionCasoDetalle transientInstance) throws Exception {
        log.debug("persisting CelInteraccionCasoDetalle instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(CelInteraccionCasoDetalle persistentInstance) throws Exception {
        log.debug("deleting CelInteraccionCasoDetalle instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public CelInteraccionCasoDetalle get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting CelInteraccionCasoDetalle instance with id: " + p_Id);
        try {
            CelInteraccionCasoDetalle instance = (CelInteraccionCasoDetalle) getHibernateTemplate()
                    .get(CelInteraccionCasoDetalle.class, p_Id);
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
    
    public CelInteraccionCasoDetalle get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
        log.debug("getting CelInteraccionCasoDetalle instance with id: " + p_Id);
        try {
            Criteria cri = getSession().createCriteria(CelInteraccionCasoDetalle.class);
            cri.add(Restrictions.idEq(p_Id));
            
        	if(falseLazy!=null)
        	{
        		for(int i=0;i<falseLazy.length;i++)
            	{
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            CelInteraccionCasoDetalle instance =(CelInteraccionCasoDetalle)cri.uniqueResult();
            
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
    
    public List<CelInteraccionCasoDetalle> getAll(CelInteraccionCasoDetalle p_example, String[] falseLazy) throws Exception {
        log.debug("finding CelInteraccionCasoDetalle instance by example");
        try {
            Criteria cri = getSession().createCriteria(CelInteraccionCasoDetalle.class);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            addDependenciesFilters(p_example, cri);
        	if(falseLazy != null) {
        		for(int i=0; i<falseLazy.length; i++) {
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	            
            cri.addOrder(Order.asc("icdId"));
            @SuppressWarnings("unchecked")
			List<CelInteraccionCasoDetalle> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
    
    
    public List<CelInteraccionCasoDetalle> getDetalleInteraccionesPorAlumno(Long alu_id) {
        log.debug("finding CelInteraccionCasoDetalle instance by getDetalleInteraccionesPorAlumno");
        try {
            Criteria cri = getSession().createCriteria(CelInteraccionCasoDetalle.class, "celInteraccionCasoDetalle");
            cri.createAlias("celInteraccionCasoDetalle.celInteraccionCaso", "cic");
            cri.add(Restrictions.eq("cic.aluId", alu_id));
            cri.setFetchMode("celInteraccionCaso", FetchMode.JOIN);
            cri.setFetchMode("celIndicador", FetchMode.JOIN);
            cri.addOrder(Order.desc("celInteraccionCasoDetalle.celInteraccionCaso.casId"));
            cri.addOrder(Order.asc("celIndicador"));
            @SuppressWarnings("unchecked")
			List<CelInteraccionCasoDetalle> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    	
    }
}
