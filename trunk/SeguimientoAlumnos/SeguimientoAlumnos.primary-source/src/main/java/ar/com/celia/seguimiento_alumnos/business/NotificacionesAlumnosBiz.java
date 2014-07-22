package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwAlertasExamenes;
import ar.com.celia.seguimiento_alumnos.domain.VwAlertasTps;
import ar.com.celia.seguimiento_alumnos.persistence.NotificacionesAlumnosDao;
import ar.com.celia.seguimiento_alumnos.persistence.NotificacionesDocentesDao;
import ar.com.celia.seguimiento_alumnos.service.NotificacionesAlumnosDefinition;

public class NotificacionesAlumnosBiz implements NotificacionesAlumnosDefinition {
	NotificacionesAlumnosDao dao;
	
	public NotificacionesAlumnosDao getDao() {
		return dao;
	}
	
	public void setDao(NotificacionesAlumnosDao dao) {
		this.dao = dao;
	}
	
    public List<VwAlertasTps> getTrabajosPracticosNuevos() throws Exception{
    	return dao.getTrabajosPracticosNuevos();
    }
    
    public List<VwAlertasTps> getTrabajosPracticosPorVencer() throws Exception{
    	return dao.getTrabajosPracticosPorVencer();
    }
    
    public List<VwAlertasTps> getTrabajosPracticosVencidos() throws Exception{
    	return dao.getTrabajosPracticosVencidos();
    }
    
    public List<VwAlertasExamenes> getExamenesNuevos() throws Exception{
    	return dao.getExamenesNuevos();
    }
    
    public List<VwAlertasExamenes> getExamenesPorVencer() throws Exception{
    	return dao.getExamenesPorVencer();
    }
    
    public List<VwAlertasExamenes> getExamenesVencidos() throws Exception{
    	return dao.getExamenesVencidos();
    }
    
    public List<VwAlertasExamenes> getLibres() throws Exception{
    	return dao.getLibres();
    }

}