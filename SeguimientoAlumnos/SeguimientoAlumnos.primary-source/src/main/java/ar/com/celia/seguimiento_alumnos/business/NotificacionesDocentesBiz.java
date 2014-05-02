package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

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

}