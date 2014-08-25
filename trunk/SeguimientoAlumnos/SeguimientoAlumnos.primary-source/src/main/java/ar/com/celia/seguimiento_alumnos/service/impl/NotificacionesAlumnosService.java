package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwAlertasAlumnoLibrePorTP;
import ar.com.celia.seguimiento_alumnos.domain.VwAlertasExamenes;
import ar.com.celia.seguimiento_alumnos.domain.VwAlertasTps;
import ar.com.celia.seguimiento_alumnos.service.NotificacionesAlumnosDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class NotificacionesAlumnosService implements NotificacionesAlumnosDefinition {

	protected NotificacionesAlumnosDefinition biz;
	
	public NotificacionesAlumnosService() {

	}
	
	public void setBusinessObject (NotificacionesAlumnosDefinition p_biz)	{
		biz = p_biz;
	}

    public List<VwAlertasTps> getTrabajosPracticosNuevos() throws Exception{
    	return biz.getTrabajosPracticosNuevos();
    }
    
    public List<VwAlertasTps> getTrabajosPracticosPorVencer() throws Exception{
    	return biz.getTrabajosPracticosPorVencer();
    }
    
    public List<VwAlertasTps> getTrabajosPracticosVencidos() throws Exception{
    	return biz.getTrabajosPracticosVencidos();
    }
    
    public List<VwAlertasAlumnoLibrePorTP> getTrabajosPracticosPorQuedarLibre() throws Exception{
    	return biz.getTrabajosPracticosPorQuedarLibre();
    }
    
    public List<VwAlertasExamenes> getExamenesNuevos() throws Exception{
    	return biz.getExamenesNuevos();
    }
    
    public List<VwAlertasExamenes> getExamenesPorVencer() throws Exception{
    	return biz.getExamenesPorVencer();
    }
    
    public List<VwAlertasExamenes> getExamenesVencidos() throws Exception{
    	return biz.getExamenesVencidos();
    }
    
    public List<VwAlertasExamenes> getLibres() throws Exception{
    	return biz.getLibres();
    }
}