package ar.com.celia.seguimiento_alumnos.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import ar.com.celia.common.persistence.util.DAOObject;
import ar.com.celia.seguimiento_alumnos.domain.CelAlertaEnviada;
import ar.com.celia.seguimiento_alumnos.persistence.CelAlertaEnviadaDao;


/**
 * Home object for domain model class CelAlertaEnviada.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.CelAlertaEnviada
 * @author Hibernate Tools
 */
public class CelAlertaEnviadaHome extends DAOObject implements CelAlertaEnviadaDao {

    private static final Log log = LogFactory.getLog(CelAlertaEnviadaHome.class);
    
    public void insert(CelAlertaEnviada transientInstance) throws Exception {
        log.debug("persisting CelAlertaEnviada instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(CelAlertaEnviada transientInstance) throws Exception {
        log.debug("persisting CelAlertaEnviada instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(CelAlertaEnviada persistentInstance) throws Exception {
        log.debug("deleting CelAlertaEnviada instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public CelAlertaEnviada get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting CelAlertaEnviada instance with id: " + p_Id);
        try {
            CelAlertaEnviada instance = (CelAlertaEnviada) getHibernateTemplate()
                    .get(CelAlertaEnviada.class, p_Id);
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
    
    public CelAlertaEnviada get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
        log.debug("getting CelAlertaEnviada instance with id: " + p_Id);
        try {
            Criteria cri = getSession().createCriteria(CelAlertaEnviada.class);
            cri.add(Restrictions.idEq(p_Id));
            
        	if(falseLazy!=null)
        	{
        		for(int i=0;i<falseLazy.length;i++)
            	{
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            CelAlertaEnviada instance =(CelAlertaEnviada)cri.uniqueResult();
            
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
    
    public List<CelAlertaEnviada> getAll(CelAlertaEnviada p_example, String[] falseLazy) throws Exception {
        log.debug("finding CelAlertaEnviada instance by example");
        try {
            Criteria cri = getSession().createCriteria(CelAlertaEnviada.class);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            addDependenciesFilters(p_example, cri);
        	if(falseLazy != null) {
        		for(int i=0; i<falseLazy.length; i++) {
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	            
            //cri.addOrder(Order.asc(DEFINIR));
            List<CelAlertaEnviada> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
