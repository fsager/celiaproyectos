package ar.com.celia.core.persistence.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ar.com.celia.core.domain.CeliaError;
import ar.com.celia.core.persistence.CeliaErrorDao;
import ar.com.celia.core.persistence.impl.util.DAOObject;

/**
 * Home object for domain model class CeliaError.
 * @see ar.com.celia.CeliaError.persistence.impl.CeliaError
 * @author Hibernate Tools
 */
public class CeliaErrorHome extends DAOObject implements CeliaErrorDao {

    private static final Log log = LogFactory.getLog(CeliaErrorHome.class);
    
    public void insert(CeliaError transientInstance) throws Exception {
        log.debug("persisting CeliaError instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(CeliaError transientInstance) throws Exception {
        log.debug("persisting CeliaError instance");
        try {
            updateObject(transientInstance);
            getSession().flush();
            
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(CeliaError persistentInstance) throws Exception {
        log.debug("deleting CeliaError instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public CeliaError get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting CeliaError instance with id: " + p_Id);
        try {
            CeliaError instance = (CeliaError) getHibernateTemplate()
                    .get(CeliaError.class, p_Id);
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
    
    
    public List getAll(CeliaError p_example) throws Exception {
        log.debug("finding CeliaError instance by example");
        try {
            Criteria cri = getSession().createCriteria(CeliaError.class);
            
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            cri.addOrder(Order.asc("errId"));
            List results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
    
    public List getAllErrorSinEnviar(CeliaError p_example) throws Exception {
        log.debug("finding CeliaError instance by getAllErrorSinEnviar");
        try {
            Criteria cri = getSession().createCriteria(CeliaError.class);
            
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            cri.add(Restrictions.isNull("errFechaEnvio"));
            
            
            cri.addOrder(Order.asc("errNombreProyecto"));
            cri.addOrder(Order.desc("errFechaError"));
            cri.addOrder(Order.desc("errId"));
            List results = cri.list();
            log.debug("find by getAllErrorSinEnviar successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by getAllErrorSinEnviar failed", re);
            throw re;
        }
    }
    
    public void deleteAllEnviados(CeliaError p_example) throws Exception {
        log.debug("deleting deleteAllEnviados");
        try {

        	Calendar cal=Calendar.getInstance();
        	cal.add(Calendar.MONTH,-1);
        	
        	String sqlDelete="delete from CELIA_ERROR where ERR_FECHA_ENVIO < ? and ERR_FECHA_ENVIO is not null";
        	
        	SQLQuery sql = getSession().createSQLQuery(sqlDelete);
            sql.setDate(0,cal.getTime());
            int deleted=sql.executeUpdate();
            
            log.debug("deleting deleteAllEnviados successful, size:" + deleted);
            
        }
        catch (RuntimeException re) {
            log.error("deleting deleteAllEnviados failed", re);
            throw re;
        }
    }
    
    public CeliaError getByErrHashId(String errHashId) throws Exception{
    	log.debug("getting CeliaError instance with hashId: " + errHashId);
        
    	try {
    		
    		//Criteria cri = getSession().createCriteria(CeliaError.class);
    		//cri.add(Restrictions.eq("errHashId", errHashId));
    		
           //log.debug("getting CeliaError instance with hashId successful");
    		
            //CeliaError result = (CeliaError) cri.uniqueResult();
            //return (CeliaError) getAll(new CeliaError()).get(0);
            
    		//return result;
    		
    		String query=  "select * from celia_error" +
    					   "	where err_hash_id = ?";

    		return  (CeliaError) getSession().createSQLQuery(query)
								 	.addEntity(CeliaError.class)
								 	.setString(0, errHashId)
								 	.uniqueResult();
            
        }catch (RuntimeException re) {
            log.error("getting CeliaError instance with hashId failed", re);
            throw re;
        }
    }
}