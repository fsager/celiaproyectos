package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwAlertasExamenes;
import ar.com.celia.seguimiento_alumnos.domain.VwAlertasTps;
import ar.com.celia.seguimiento_alumnos.domain.VwDocentesNoIngresanAMoodle;
import ar.com.celia.seguimiento_alumnos.persistence.NotificacionesDocentesDao;
import ar.com.celia.seguimiento_alumnos.service.NotificacionesDocentesDefinition;

public class NotificacionesDocentesBiz implements NotificacionesDocentesDefinition {
	NotificacionesDocentesDao dao;
	
	public NotificacionesDocentesDao getDao() {
		return dao;
	}
	
	public void setDao(NotificacionesDocentesDao dao) {
		this.dao = dao;
	}
	
	public List<VwDocentesNoIngresanAMoodle> getDocentesNoIngresanAMoodle() throws Exception{
		return dao.getDocentesNoIngresanAMoodle();
	}
	
    public List<VwAlertasExamenes> getNotasPendientes() throws Exception{
    	return dao.getNotasPendientes();
    }
    
    public List<VwAlertasTps> getTpNotasPendientes() throws Exception{
    	return dao.getTpNotasPendientes();
    }
    
    public List<VwAlertasTps> getTpPendientesDeSubir() throws Exception{
    	return dao.getTpPendientesDeSubir();
    }
    
    public List<VwAlertasExamenes> getExamenPendientesDeSubir() throws Exception{
    	return dao.getExamenPendientesDeSubir();
    }

}