package ar.com.celia.seguimiento_alumnos.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;

import ar.com.celia.common.persistence.util.DAOObject;
import ar.com.celia.seguimiento_alumnos.domain.VwAlertasExamenes;
import ar.com.celia.seguimiento_alumnos.domain.VwAlertasTps;
import ar.com.celia.seguimiento_alumnos.persistence.NotificacionesAlumnosDao;


/**
 * Home object for domain model class NotificacionesDocentes.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.NotificacionesDocentes
 * @author Hibernate Tools
 */
public class NotificacionesAlumnosHome extends DAOObject implements NotificacionesAlumnosDao {

    private static final Log log = LogFactory.getLog(NotificacionesAlumnosHome.class);
    
    public List<VwAlertasTps> getTrabajosPracticosNuevos() throws Exception {
        log.debug("finding getTrabajosPracticosNuevos instance by example");
        try {
            
            String select="select * from vw_alerta_alumnos_nuevos_tp";
            SQLQuery sQLQuery=getSession().createSQLQuery(select).addEntity(VwAlertasTps.class);
            
            List<VwAlertasTps> results = sQLQuery.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
    
    public List<VwAlertasTps> getTrabajosPracticosPorVencer() throws Exception {
        log.debug("finding getTrabajosPracticosPorVencer instance by example");
        try {
            String select="select * from vw_alerta_alumnos_tp_por_vencer";
            SQLQuery sQLQuery=getSession().createSQLQuery(select).addEntity(VwAlertasTps.class);
            
            
            List<VwAlertasTps> results = sQLQuery.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
    
    public List<VwAlertasTps> getTrabajosPracticosVencidos() throws Exception {
        log.debug("finding getTrabajosPracticosVencidos instance by example");
        try {
            String select="select * from vw_alerta_tpsvencidos";
            SQLQuery sQLQuery=getSession().createSQLQuery(select).addEntity(VwAlertasTps.class);
            
            
            List<VwAlertasTps> results = sQLQuery.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
    
    public List<VwAlertasExamenes> getExamenesNuevos() throws Exception {
        log.debug("finding getExamenesNuevos instance by example");
        try {
            String select="select * from vw_alerta_alumnos_nuevos_examenes";
            SQLQuery sQLQuery=getSession().createSQLQuery(select).addEntity(VwAlertasExamenes.class);
            
            List<VwAlertasExamenes> results = sQLQuery.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
    
    public List<VwAlertasExamenes> getExamenesPorVencer() throws Exception {
        log.debug("finding getExamenesPorVencer instance by example");
        try {
            String select="select * from vw_alerta_alumnos_examenes_por_vencer";
            SQLQuery sQLQuery=getSession().createSQLQuery(select).addEntity(VwAlertasExamenes.class);
            
            List<VwAlertasExamenes> results = sQLQuery.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
    
    public List<VwAlertasExamenes> getExamenesVencidos() throws Exception {
        log.debug("finding getExamenesVencidos instance by example");
        try {
            String select="select * from vw_alerta_alumnos_examenes_vencidos";
            SQLQuery sQLQuery=getSession().createSQLQuery(select).addEntity(VwAlertasExamenes.class);
            
            List<VwAlertasExamenes> results = sQLQuery.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
    
    public List<VwAlertasExamenes> getLibres() throws Exception {
        log.debug("finding NotificacionesDocentes instance by example");
        try {
            Criteria cri = getSession().createCriteria(VwAlertasExamenes.class);        	            
            List<VwAlertasExamenes> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
