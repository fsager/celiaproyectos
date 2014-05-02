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
import ar.com.celia.seguimiento_alumnos.domain.VwDocentesNoIngresanAMoodle;
import ar.com.celia.seguimiento_alumnos.notificaciones.NotificacionesDocentes;
import ar.com.celia.seguimiento_alumnos.persistence.NotificacionesDocentesDao;


/**
 * Home object for domain model class NotificacionesDocentes.
 * @see ar.com.celia.seguimiento_alumnos.persistence.impl.NotificacionesDocentes
 * @author Hibernate Tools
 */
public class NotificacionesDocentesHome extends DAOObject implements NotificacionesDocentesDao {

    private static final Log log = LogFactory.getLog(NotificacionesDocentesHome.class);
    
    public List<VwDocentesNoIngresanAMoodle> getDocentesNoIngresanAMoodle() throws Exception {
        log.debug("finding NotificacionesDocentes instance by example");
        try {
            Criteria cri = getSession().createCriteria(VwDocentesNoIngresanAMoodle.class);        	            
            List<VwDocentesNoIngresanAMoodle> results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
