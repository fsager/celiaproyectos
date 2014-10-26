package ar.com.celia.seguimiento_alumnos.persistence.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import ar.com.celia.seguimiento_alumnos.domain.*;
import ar.com.celia.seguimiento_alumnos.persistence.CelBitacoraAlumnosDao;
import ar.com.celia.common.persistence.util.DAOObject;
import org.hibernate.criterion.Order;
import org.hibernate.FetchMode;


/**
 * Home object for domain model class CelBitacoraAlumnos.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.CelBitacoraAlumnos
 * @author Hibernate Tools
 */
public class CelBitacoraAlumnosHome extends DAOObject implements CelBitacoraAlumnosDao {

    private static final Log log = LogFactory.getLog(CelBitacoraAlumnosHome.class);
    
    public void insert(CelBitacoraAlumnos transientInstance) throws Exception {
        log.debug("persisting CelBitacoraAlumnos instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(CelBitacoraAlumnos transientInstance) throws Exception {
        log.debug("persisting CelBitacoraAlumnos instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(CelBitacoraAlumnos persistentInstance) throws Exception {
        log.debug("deleting CelBitacoraAlumnos instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public CelBitacoraAlumnos get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting CelBitacoraAlumnos instance with id: " + p_Id);
        try {
            CelBitacoraAlumnos instance = (CelBitacoraAlumnos) getHibernateTemplate()
                    .get(CelBitacoraAlumnos.class, p_Id);
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
    
    public CelBitacoraAlumnos get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
        log.debug("getting CelBitacoraAlumnos instance with id: " + p_Id);
        try {
            Criteria cri = getSession().createCriteria(CelBitacoraAlumnos.class);
            cri.add(Restrictions.idEq(p_Id));
            
        	if(falseLazy!=null)
        	{
        		for(int i=0;i<falseLazy.length;i++)
            	{
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            CelBitacoraAlumnos instance =(CelBitacoraAlumnos)cri.uniqueResult();
            
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
    
    public List<CelBitacoraAlumnos> getAll(CelBitacoraAlumnos p_example, String[] falseLazy) throws Exception {
        log.debug("finding CelBitacoraAlumnos instance by example");
        try {
            Criteria cri = getSession().createCriteria(CelBitacoraAlumnos.class);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            addDependenciesFilters(p_example, cri);
        	if(falseLazy != null) {
        		for(int i=0; i<falseLazy.length; i++) {
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	            
            cri.addOrder(Order.asc("btcId"));
            List<CelBitacoraAlumnos> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
    
    
    public List<CelBitacoraAlumnos> getBitacoraPorUsrId(Long usrId) throws Exception {
        log.debug("finding CelBitacoraAlumnos instance by getBitacoraPorUsrId");
        try {
            Criteria cri = getSession().createCriteria(CelBitacoraAlumnos.class);
            cri.add(Restrictions.eq("usrId", usrId));
            
            cri.addOrder(Order.asc("audFechaIns"));
            List<CelBitacoraAlumnos> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}