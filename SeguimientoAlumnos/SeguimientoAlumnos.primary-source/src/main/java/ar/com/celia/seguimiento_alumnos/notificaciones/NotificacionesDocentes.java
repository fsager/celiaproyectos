package ar.com.celia.seguimiento_alumnos.notificaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.ServletContext;

import ar.com.celia.core.business.ContextManagerCore;
import ar.com.celia.seguimiento_alumnos.domain.VwAlertasExamenes;
import ar.com.celia.seguimiento_alumnos.domain.VwAlertasTps;
import ar.com.celia.seguimiento_alumnos.domain.VwDocentesNoIngresanAMoodle;
import ar.com.celia.seguimiento_alumnos.service.CelPropiedadDefinition;
import ar.com.celia.seguimiento_alumnos.service.NotificacionesDocentesDefinition;



public class NotificacionesDocentes extends Notificaciones{
	
	private NotificacionesDocentesDefinition notificacionesDocentesService;
	
	public void notificarNoIngresoAMoodle() throws Exception
	{
		init();
		List<VwDocentesNoIngresanAMoodle> docentesQueNoIngresan=notificacionesDocentesService.getDocentesNoIngresanAMoodle();
		String textoMail =  readTemplate(this.servletContext,"/template_notificaciones/docentes/noIngresaAMoodle.html");
		String subject=celPropiedadService.get("mail_doc_no_ingreso_subject").getProValor();
		
		
		for(VwDocentesNoIngresanAMoodle docenteQueNoIngresa:docentesQueNoIngresan)
		{
			String templateWithValues=remplazarValoresTemplate(docenteQueNoIngresa,textoMail);
			enviarMail(docenteQueNoIngresa.getEmail(),subject,templateWithValues,false,null,null,null,null);
			
			if(frenar)
				break;
		}
	}
	
	public void notificarCargaDeNotas() throws Exception
	{	
		init();
		List<VwAlertasExamenes> notasPendientes=notificacionesDocentesService.getNotasPendientes();
		String textoMail =  readTemplate(this.servletContext,"/template_notificaciones/docentes/cargaDeNotas.html");
		

		String subject=celPropiedadService.get("mail_doc_carga_nota_subject").getProValor();
		

		for(VwAlertasExamenes notaPendiente:notasPendientes)
		{
			String templateWithValues=remplazarValoresTemplate(notaPendiente,textoMail);
			enviarMail(notaPendiente.getEmail(),subject,templateWithValues,false,Notificaciones.OBJ_TIPO_EXAMEN,notaPendiente.getQuizid(),notaPendiente.getUserid(),Notificaciones.ALERTA_DOC_EXAMEN_NOTA_PENDIENTE);
			
			if(frenar)
				break;
		}
	}
	
	public void notificarTrabajosPracticosPendientesDeCorreccion() throws Exception
	{
		init();
		List<VwAlertasTps> tpNotasPendientes=notificacionesDocentesService.getTpNotasPendientes();
		String textoMail =  readTemplate(this.servletContext,"/template_notificaciones/docentes/trabajosPracticosPendientesDeCorreccion.html");
		

		String subject=celPropiedadService.get("mail_doc_tp_carga_nota_subject").getProValor();
		

		for(VwAlertasTps notaPendiente:tpNotasPendientes)
		{
			String templateWithValues=remplazarValoresTemplate(notaPendiente,textoMail);
			enviarMail(notaPendiente.getEmail(),subject,templateWithValues,false,Notificaciones.OBJ_TIPO_TP,notaPendiente.getAssignmentId(),notaPendiente.getUserid(),Notificaciones.ALERTA_DOC_TP_NOTA_PENDIENTE);
			if(frenar)
				break;
		}
	}
		
	public void notificarTrabajosPracticosExamenesPendienteDeSubida() throws Exception
	{
		notificarTrabajosPracticosPendientesDeSubir();
		notificarExamenesPendientesDeSubir();
	}
	
	public void notificarTrabajosPracticosPendientesDeSubir() throws Exception
	{
		init();
		List<VwAlertasTps> tpsPendientes=notificacionesDocentesService.getTpPendientesDeSubir();
		String textoMail =  readTemplate(this.servletContext,"/template_notificaciones/docentes/trabajosPracticosPendienteDeSubida.html");
		

		String subject=celPropiedadService.get("mail_doc_tp_pendiente_subida").getProValor();
		

		for(VwAlertasTps tp:tpsPendientes)
		{
			String templateWithValues=remplazarValoresTemplate(tp,textoMail);
			enviarMail(tp.getEmail(),subject,templateWithValues,false,Notificaciones.OBJ_TIPO_TP,tp.getAssignmentId(),tp.getUserid(),Notificaciones.ALERTA_DOC_TP_PENDIENTE_CARGA);
			if(frenar)
				break;
		}
	}
	
	public void notificarExamenesPendientesDeSubir() throws Exception
	{
		init();
		List<VwAlertasExamenes> examenPendientes=notificacionesDocentesService.getExamenPendientesDeSubir();
		String textoMail =  readTemplate(this.servletContext,"/template_notificaciones/docentes/examenesPendienteDeSubida.html");
		

		String subject=celPropiedadService.get("mail_doc_examen_pendiente_subida").getProValor();
		

		for(VwAlertasExamenes examen:examenPendientes)
		{
			String templateWithValues=remplazarValoresTemplate(examen,textoMail);
			enviarMail(examen.getEmail(),subject,templateWithValues,false,Notificaciones.OBJ_TIPO_EXAMEN,examen.getQuizid(),examen.getUserid(),Notificaciones.ALERTA_DOC_EXAMEN_PENDIENTE_CARGA);
			if(frenar)
				break;
		}
	}

	
	public void test() throws Exception
	{
		super.frenar=true;
//		notificarNoIngresoAMoodle();
//		notificarCargaDeNotas();
//		notificarTrabajosPracticosPendientesDeCorreccion();
//		notificarTrabajosPracticosExamenesPendienteDeSubida();
	}
	
	public NotificacionesDocentesDefinition getNotificacionesDocentesService() {
		return notificacionesDocentesService;
	}

	public void setNotificacionesDocentesService(
			NotificacionesDocentesDefinition notificacionesDocentesService) {
		this.notificacionesDocentesService = notificacionesDocentesService;
	}	
	
}
