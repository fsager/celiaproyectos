package ar.com.celia.seguimiento_alumnos.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwAlertasAlumnoLibrePorTP;
import ar.com.celia.seguimiento_alumnos.domain.VwAlertasExamenes;
import ar.com.celia.seguimiento_alumnos.domain.VwAlertasTps;
import ar.com.celia.seguimiento_alumnos.persistence.NotificacionesAlumnosDao;
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
    	List<VwAlertasTps> vwAlertasTps = dao.getTrabajosPracticosNuevos();
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH24:mm:ss");
    	Date fechaActual = new Date();
    	List<VwAlertasTps> res = new ArrayList<VwAlertasTps>();
    	for (VwAlertasTps vwAlertasTp : vwAlertasTps)
			if(sdf.parse(vwAlertasTp.getFechaVencimiento()).after(fechaActual))
				res.add(vwAlertasTp);
		
    	return res;
    }
    
    public List<VwAlertasTps> getTrabajosPracticosPorVencer() throws Exception{
    	return dao.getTrabajosPracticosPorVencer();
    }
    
    public List<VwAlertasTps> getTrabajosPracticosVencidos() throws Exception{
    	return dao.getTrabajosPracticosVencidos();
    }
    
    public List<VwAlertasAlumnoLibrePorTP> getTrabajosPracticosPorQuedarLibre() throws Exception{
    	return dao.getTrabajosPracticosPorQuedarLibre();
    }
    
    public List<VwAlertasExamenes> getExamenesNuevos() throws Exception{
    	List<VwAlertasExamenes> vwAlertasExamenes = dao.getExamenesNuevos();
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH24:mm:ss");
    	Date fechaActual = new Date();
    	List<VwAlertasExamenes> res = new ArrayList<VwAlertasExamenes>();
    	for (VwAlertasExamenes vwExamenen : vwAlertasExamenes)
			if(sdf.parse(vwExamenen.getFechaVencimiento()).after(fechaActual))
				res.add(vwExamenen);
		
    	return res;    }
    
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