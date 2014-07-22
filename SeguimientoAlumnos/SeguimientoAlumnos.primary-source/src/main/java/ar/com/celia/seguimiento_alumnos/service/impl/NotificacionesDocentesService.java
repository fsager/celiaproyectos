package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwAlertasExamenes;
import ar.com.celia.seguimiento_alumnos.domain.VwAlertasTps;
import ar.com.celia.seguimiento_alumnos.domain.VwDocentesNoIngresanAMoodle;
import ar.com.celia.seguimiento_alumnos.service.NotificacionesDocentesDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class NotificacionesDocentesService implements NotificacionesDocentesDefinition {

	protected NotificacionesDocentesDefinition biz;
	
	public NotificacionesDocentesService() {

	}
	
	public void setBusinessObject (NotificacionesDocentesDefinition p_biz)	{
		biz = p_biz;
	}

	public List<VwDocentesNoIngresanAMoodle> getDocentesNoIngresanAMoodle() throws Exception{
		return biz.getDocentesNoIngresanAMoodle();
	}
	
    public List<VwAlertasExamenes> getNotasPendientes() throws Exception{
    	return biz.getNotasPendientes();
    }
    
    public List<VwAlertasTps> getTpNotasPendientes() throws Exception{
    	return biz.getTpNotasPendientes();
    }
    
    public List<VwAlertasTps> getTpPendientesDeSubir() throws Exception{
    	return biz.getTpPendientesDeSubir();
    }
    
    public List<VwAlertasExamenes> getExamenPendientesDeSubir() throws Exception{
    	return biz.getExamenPendientesDeSubir();
    }
}