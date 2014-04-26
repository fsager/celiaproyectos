package ar.com.celia.seguimiento_alumnos.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ar.com.celia.common.persistence.util.DAOObject;
import ar.com.celia.seguimiento_alumnos.domain.VwAlumnosActivos;
import ar.com.celia.seguimiento_alumnos.persistence.VwAlumnosActivosDao;


/**
 * Home object for domain model class VwAlumnosActivos.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.VwAlumnosActivos
 * @author Hibernate Tools
 */
public class VwAlumnosActivosHome extends DAOObject implements VwAlumnosActivosDao {

    private static final Log log = LogFactory.getLog(VwAlumnosActivosHome.class);
    
    public void insert(VwAlumnosActivos transientInstance) throws Exception {
        log.debug("persisting VwAlumnosActivos instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(VwAlumnosActivos transientInstance) throws Exception {
        log.debug("persisting VwAlumnosActivos instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(VwAlumnosActivos persistentInstance) throws Exception {
        log.debug("deleting VwAlumnosActivos instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VwAlumnosActivos get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting VwAlumnosActivos instance with id: " + p_Id);
        try {
            VwAlumnosActivos instance = (VwAlumnosActivos) getHibernateTemplate()
                    .get(VwAlumnosActivos.class, p_Id);
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
    
    public VwAlumnosActivos get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
        log.debug("getting VwAlumnosActivos instance with id: " + p_Id);
        try {
            Criteria cri = getSession().createCriteria(VwAlumnosActivos.class);
            cri.add(Restrictions.idEq(p_Id));
            
        	if(falseLazy!=null)
        	{
        		for(int i=0;i<falseLazy.length;i++)
            	{
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            VwAlumnosActivos instance =(VwAlumnosActivos)cri.uniqueResult();
            
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
    
    public List<VwAlumnosActivos> getAll(VwAlumnosActivos p_example, String[] falseLazy) throws Exception {
        log.debug("finding VwAlumnosActivos instance by example");
        try {
        	
        	Query query = getSession().createSQLQuery(
        			"CALL p_alumnos_activos_con_indicadores()")
        			.addEntity("alu",VwAlumnosActivos.class)
        			.addJoin("ind", "alu.indicadoresAlumnos")
        			.addEntity("alu",VwAlumnosActivos.class)
        			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        	
        			//.setParameter("stockCode", "7277");
        		 
        	return query.list();
        		
        /*	session.createSQLQuery("select personid, name, code, description from person_books")  
        	       .addEntity("person", Person.class)
        	       .addJoin("ind", "person.indicadoresAlumnos")
        	       .list();
        		
        	
        	
            Criteria cri = getSession().createCriteria(VwAlumnosActivos.class);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            addDependenciesFilters(p_example, cri);
        	if(falseLazy != null) {
        		for(int i=0; i<falseLazy.length; i++) {
        			cri.setFetchMode(falseLazy[i], FetchMode.JOIN);
            	}
        	}
        	
            cri.addOrder(Order.asc("lastname"));
            cri.addOrder(Order.asc("firstname"));
            List<VwAlumnosActivos> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;*/
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
