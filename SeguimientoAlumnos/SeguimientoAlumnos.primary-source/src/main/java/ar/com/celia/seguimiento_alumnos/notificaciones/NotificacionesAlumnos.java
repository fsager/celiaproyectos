package ar.com.celia.seguimiento_alumnos.notificaciones;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwAlertasAlumnoLibrePorTP;
import ar.com.celia.seguimiento_alumnos.domain.VwAlertasExamenes;
import ar.com.celia.seguimiento_alumnos.domain.VwAlertasTps;
import ar.com.celia.seguimiento_alumnos.service.NotificacionesAlumnosDefinition;



public class NotificacionesAlumnos extends Notificaciones{
	

	private NotificacionesAlumnosDefinition notificacionesAlumnosService;
	
	
	public void notificarNuevoTrabajoPractico() throws Exception {
		init();
		List<VwAlertasTps> tps=notificacionesAlumnosService.getTrabajosPracticosNuevos();
		String textoMail =  readTemplate(this.servletContext,"/template_notificaciones/alumnos/nuevosTps.html");
		String subject=celPropiedadService.get("mail_alu_nuevos_tp_subject").getProValor();
		
		for(VwAlertasTps nuevoTps:tps) {
			String templateWithValues=remplazarValoresTemplate(nuevoTps,textoMail);
			enviarMail(nuevoTps.getEmail(),subject,templateWithValues,true,Notificaciones.OBJ_TIPO_TP,nuevoTps.getAssignmentId(),nuevoTps.getUserid(),Notificaciones.ALERTA_NUEVO_TP);
			
			if(frenar)
				break;
		}
	}
	
	public void notificarNuevoExamen() throws Exception
	{
		init();
		List<VwAlertasExamenes> examenes=notificacionesAlumnosService.getExamenesNuevos();
		String textoMail =  readTemplate(this.servletContext,"/template_notificaciones/alumnos/nuevosExamenes.html");
		String subject=celPropiedadService.get("mail_alu_nuevos_examenes_subject").getProValor();
		
		for(VwAlertasExamenes nuevoExamen:examenes)
		{
			String templateWithValues=remplazarValoresTemplate(nuevoExamen,textoMail);
			enviarMail(nuevoExamen.getEmail(),subject,templateWithValues,true,Notificaciones.OBJ_TIPO_EXAMEN,nuevoExamen.getQuizid(),nuevoExamen.getUserid(),Notificaciones.ALERTA_NUEVO_EXAMEN);
			if(frenar)
				break;
		}
	}
	
	public void notificarTrabajoPracticoPorVencer() throws Exception
	{
		init();
		List<VwAlertasTps> tps=notificacionesAlumnosService.getTrabajosPracticosPorVencer();
		String textoMail =  readTemplate(this.servletContext,"/template_notificaciones/alumnos/tpsPorVencer.html");
		String subject=celPropiedadService.get("mail_alu_tp_por_vencer_subject").getProValor();
		
		
		for(VwAlertasTps nuevoTps:tps)
		{
			String templateWithValues=remplazarValoresTemplate(nuevoTps,textoMail);
			enviarMail(nuevoTps.getEmail(),subject,templateWithValues,true,Notificaciones.OBJ_TIPO_TP,nuevoTps.getAssignmentId(),nuevoTps.getUserid(),Notificaciones.ALERTA_TP_POR_VENCER);
			if(frenar)
				break;
		}
	}
	
	public void notificarExamenPorVencer() throws Exception {
		init();
		List<VwAlertasExamenes> examenes=notificacionesAlumnosService.getExamenesPorVencer();
		String textoMail =  readTemplate(this.servletContext,"/template_notificaciones/alumnos/examenesPorVencer.html");
		String subject=celPropiedadService.get("mail_alu_examenes_por_vecer_subject").getProValor();
		
		
		for(VwAlertasExamenes nuevoExamen:examenes)
		{
			String templateWithValues=remplazarValoresTemplate(nuevoExamen,textoMail);
			enviarMail(nuevoExamen.getEmail(),subject,templateWithValues,true,Notificaciones.OBJ_TIPO_EXAMEN,nuevoExamen.getQuizid(),nuevoExamen.getUserid(),Notificaciones.ALERTA_EXAMEN_POR_VENCER);
			if(frenar)
				break;
		}
	}
	
	public void notificarTrabajoPracticoVencidos() throws Exception
	{
		init();
		List<VwAlertasTps> tps=notificacionesAlumnosService.getTrabajosPracticosVencidos();
		String textoMail =  readTemplate(this.servletContext,"/template_notificaciones/alumnos/tpsNoRealizado.html");
		String subject=celPropiedadService.get("mail_alu_tp_vencidos_subject").getProValor();
		
		
		for(VwAlertasTps nuevoTps:tps) {
			String templateWithValues=remplazarValoresTemplate(nuevoTps,textoMail);
			enviarMail(nuevoTps.getEmail(),subject,templateWithValues,true,Notificaciones.OBJ_TIPO_TP,nuevoTps.getAssignmentId(),nuevoTps.getUserid(),Notificaciones.ALERTA_TP_VENCIDO);
			if(frenar)
				break;
		}
	}
	
	public void notificarExamenVencidos() throws Exception
	{
		init();
		List<VwAlertasExamenes> examenes=notificacionesAlumnosService.getExamenesVencidos();
		String textoMail =  readTemplate(this.servletContext,"/template_notificaciones/alumnos/examenesNoRealizados.html");
		String subject=celPropiedadService.get("mail_alu_examenes_vencidos_subject").getProValor();
		
		
		for(VwAlertasExamenes nuevoExamen:examenes)
		{
			String templateWithValues=remplazarValoresTemplate(nuevoExamen,textoMail);
			enviarMail(nuevoExamen.getEmail(),subject,templateWithValues,true,Notificaciones.OBJ_TIPO_EXAMEN,nuevoExamen.getQuizid(),nuevoExamen.getUserid(),Notificaciones.ALERTA_EXAMEN_VENCIDO);
			if(frenar)
				break;
		}
	}
	
	public void notificarLibres() throws Exception
	{
		init();
		List<VwAlertasExamenes> examenes=notificacionesAlumnosService.getLibres();
		String textoMail =  readTemplate(this.servletContext,"/template_notificaciones/alumnos/alumnoLibre.html");
		String subject=celPropiedadService.get("mail_alu_libres_subject").getProValor();
		
		for(VwAlertasExamenes nuevoExamen:examenes)
		{
			String templateWithValues=remplazarValoresTemplate(nuevoExamen,textoMail);
			//TODO
			//enviarMail(nuevoExamen.getEmail(),subject,templateWithValues,true,Notificaciones.OBJ_TIPO_MATERIA,nuevoExamen.getQuizid(),nuevoExamen.getUserid(),Notificaciones.ALERTA_NUEVO_EXAMEN);
			if(frenar)
				break;
		}
	}
	
	public void notificarAlumnosPorQuedarLibresPorTP() throws Exception
	{
		init();
		List<VwAlertasAlumnoLibrePorTP> alertasAlumnoLibrePorTPs=notificacionesAlumnosService.getTrabajosPracticosPorQuedarLibre();
		String textoMail =  readTemplate(this.servletContext,"/template_notificaciones/alumnos/librePorTps.html");
		String subject=celPropiedadService.get("mail_alu_por_quedar_libres_subject").getProValor();
		
		int cant = 0;
		for(VwAlertasAlumnoLibrePorTP alumnoLibrePorTP:alertasAlumnoLibrePorTPs) {
			if(cant == 10)break;
			String templateWithValues=remplazarValoresTemplate(alumnoLibrePorTP,textoMail);
			
			enviarMail("gonza.delasilva@gmail.com", subject, templateWithValues, true, Notificaciones.OBJ_TIPO_TP, alumnoLibrePorTP.getMatId(), alumnoLibrePorTP.getUserId(), ALERTA_ALU_POR_QUEDAR_LIBRE);
			cant++;
			if(frenar)
				break;
		}
	}
	
	public void test() throws Exception
	{
		super.frenar=true;
//		notificarNuevoTrabajoPractico();
//		notificarNuevoExamen();
//		notificarTrabajoPracticoPorVencer();
//		notificarExamenPorVencer();
//		notificarTrabajoPracticoVencidos();
//		notificarExamenVencidos();
		notificarAlumnosPorQuedarLibresPorTP();
	}
	
	public void setNotificacionesAlumnosService(
			NotificacionesAlumnosDefinition notificacionesAlumnosService) {
		this.notificacionesAlumnosService = notificacionesAlumnosService;
	}
	
	public NotificacionesAlumnosDefinition getNotificacionesAlumnosService() {
		return notificacionesAlumnosService;
	}
	
}
