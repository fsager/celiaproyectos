package ar.com.celia.seguimiento_alumnos.notificaciones;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.ServletContext;

import ar.com.celia.core.business.ContextManagerCore;
import ar.com.celia.seguimiento_alumnos.domain.VwDocentesNoIngresanAMoodle;
import ar.com.celia.seguimiento_alumnos.service.CelPropiedadDefinition;
import ar.com.celia.seguimiento_alumnos.service.NotificacionesDocentesDefinition;



public class NotificacionesDocentes {
	
	private ServletContext servletContext = null;
	private SteMailPoolService steMailService;
	private NotificacionesDocentesDefinition notificacionesDocentesService;
	private CelPropiedadDefinition celPropiedadService;
	
	public void notificarNoIngresoAMoodle(ServletContext servletContext) throws Exception
	{
		this.servletContext=servletContext;
		List<VwDocentesNoIngresanAMoodle> docentesQueNoIngresan=notificacionesDocentesService.getDocentesNoIngresanAMoodle();
		
		BufferedReader bufferedReaderTemplate;
		InputStream istreamTemplateMail = this.servletContext.getResourceAsStream("/template_notificaciones/docentes/noIngresaAMoodle.html");
		bufferedReaderTemplate = new BufferedReader(new InputStreamReader(istreamTemplateMail));
		
		String textoMail = "";
		
		String aux = bufferedReaderTemplate.readLine();
		
		while (aux != null) {
			textoMail += aux;
			aux = bufferedReaderTemplate.readLine();
		}
		bufferedReaderTemplate.close();
		
		
		String from=celPropiedadService.get("mail_from").getProValor();
		String replyTo=celPropiedadService.get("mail_reply_to").getProValor();
		String cc=celPropiedadService.get("mail_cc").getProValor();
		String subject=celPropiedadService.get("mail_doc_no_ingreso_subject").getProValor();
		
		
		for(VwDocentesNoIngresanAMoodle docenteQueNoIngresa:docentesQueNoIngresan)
		{
			String templateWithValues=remplazarValoresTemplate(docenteQueNoIngresa,textoMail);
			//TODO corregir mail
			steMailService.enviarMail(docenteQueNoIngresa.getEmail()+"algoparaquenosenevie", from, replyTo, cc, null, subject, templateWithValues);
			
		}
	}
	
	public static String remplazarValoresTemplate(Object obj,String template) throws Exception
	{
		Field[] atributos=obj.getClass().getDeclaredFields();
		String templateWithValues=template;
		for(Field atributo: atributos)
		{
			atributo.setAccessible(true);
				
			
			templateWithValues=templateWithValues.replaceAll("##"+atributo.getName()+"##", atributo.get(obj).toString());
		}
		
		return templateWithValues;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public SteMailPoolService getSteMailService() {
		return steMailService;
	}

	public void setSteMailService(SteMailPoolService steMailService) {
		this.steMailService = steMailService;
	}

	public NotificacionesDocentesDefinition getNotificacionesDocentesService() {
		return notificacionesDocentesService;
	}

	public void setNotificacionesDocentesService(
			NotificacionesDocentesDefinition notificacionesDocentesService) {
		this.notificacionesDocentesService = notificacionesDocentesService;
	}

	public CelPropiedadDefinition getCelPropiedadService() {
		return celPropiedadService;
	}

	public void setCelPropiedadService(CelPropiedadDefinition celPropiedadService) {
		this.celPropiedadService = celPropiedadService;
	}
	
	

}
