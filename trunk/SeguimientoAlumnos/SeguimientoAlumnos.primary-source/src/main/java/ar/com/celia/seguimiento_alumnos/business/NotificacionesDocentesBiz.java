package ar.com.celia.seguimiento_alumnos.business;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;

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
    	List<VwAlertasTps> vwAlertasTps = dao.getTpPendientesDeSubir();
    	List<VwAlertasTps> res = new ArrayList<VwAlertasTps>();
    	for (VwAlertasTps vwAlertasTp : vwAlertasTps) {
			if(vwAlertasTp.getAssignmentName() != null &&
					!"Trabajo Práctico".equalsIgnoreCase(Jsoup.parse(vwAlertasTp.getAssignmentName()).text().trim()))
				res.add(vwAlertasTp);
		}
    	return res;
    }
    
    public List<VwAlertasExamenes> getExamenPendientesDeSubir() throws Exception{
    	List<VwAlertasExamenes> vwAlertasExamenes =dao.getExamenPendientesDeSubir();
    	List<VwAlertasExamenes> res = new ArrayList<VwAlertasExamenes>();
    	for (VwAlertasExamenes vwAlertasExamen : vwAlertasExamenes) {
			if(vwAlertasExamen.getQuizName() != null &&
					!"Examen".equalsIgnoreCase(Jsoup.parse(vwAlertasExamen.getQuizName()).text().trim()))
				res.add(vwAlertasExamen);
		}
    	return res;
    }

}