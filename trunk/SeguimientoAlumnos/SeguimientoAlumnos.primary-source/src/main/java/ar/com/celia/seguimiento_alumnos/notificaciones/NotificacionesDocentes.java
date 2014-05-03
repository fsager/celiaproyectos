package ar.com.celia.seguimiento_alumnos.notificaciones;

import java.io.BufferedReader;
import java.io.IOException;
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
	
	private String from;
	private String replyTo;
	private String cc;
	private String prueba;
	
	public void init() throws Exception
	{
		from=celPropiedadService.get("mail_from").getProValor();
		replyTo=celPropiedadService.get("mail_reply_to").getProValor();
		cc=celPropiedadService.get("mail_cc").getProValor();		
		prueba=celPropiedadService.get("entorno_pruebas").getProValor();
	}
	
	public void notificarNoIngresoAMoodle() throws Exception
	{
		init();
		List<VwDocentesNoIngresanAMoodle> docentesQueNoIngresan=notificacionesDocentesService.getDocentesNoIngresanAMoodle();
		String textoMail =  readTemplate(this.servletContext,"/template_notificaciones/docentes/noIngresaAMoodle.html");
		String subject=celPropiedadService.get("mail_doc_no_ingreso_subject").getProValor();
		
		
		for(VwDocentesNoIngresanAMoodle docenteQueNoIngresa:docentesQueNoIngresan)
		{
			String templateWithValues=remplazarValoresTemplate(docenteQueNoIngresa,textoMail);
			enviarMail(docenteQueNoIngresa.getEmail(),subject,templateWithValues);
		}
	}
	
	public void notificarCargaDeNotas() throws Exception
	{	
		init();
		//TODO llamar al metodo que implenta la busqueda en la base de datos 
		//List<VwDocentesNoIngresanAMoodle> docentesQueNoIngresan=notificacionesDocentesService.getDocentesNoIngresanAMoodle();
		String textoMail =  readTemplate(this.servletContext,"/template_notificaciones/docentes/cargaDeNotas.html");
		
		//TODO llamar a la propiedad con el asunto del mail
		//String subject=celPropiedadService.get("mail_doc_no_ingreso_subject").getProValor();
		
		//TODO iterar resultados
//		for(VwDocentesNoIngresanAMoodle docenteQueNoIngresa:docentesQueNoIngresan)
//		{
//			String templateWithValues=remplazarValoresTemplate(docenteQueNoIngresa,textoMail);
//			enviarMail(docenteQueNoIngresa.getEmail(),subject,templateWithValues);
//		}
	}
	
	public void notificarTrabajosPracticosPendientesDeCorreccion() throws Exception
	{
		init();
		//TODO llamar al metodo que implenta la busqueda en la base de datos 
		//List<VwDocentesNoIngresanAMoodle> docentesQueNoIngresan=notificacionesDocentesService.getDocentesNoIngresanAMoodle();
		String textoMail =  readTemplate(this.servletContext,"/template_notificaciones/docentes/trabajosPracticosPendientesDeCorreccion.html");
		
		//TODO llamar a la propiedad con el asunto del mail
		//String subject=celPropiedadService.get("mail_doc_no_ingreso_subject").getProValor();
		
		//TODO iterar resultados
//		for(VwDocentesNoIngresanAMoodle docenteQueNoIngresa:docentesQueNoIngresan)
//		{
//			String templateWithValues=remplazarValoresTemplate(docenteQueNoIngresa,textoMail);
//			enviarMail(docenteQueNoIngresa.getEmail(),subject,templateWithValues);
//		}
	}
	
	
	public void notificarTrabajosPracticosExamenesPendienteDeSubida() throws Exception
	{
		init();
		//TODO llamar al metodo que implenta la busqueda en la base de datos 
		//List<VwDocentesNoIngresanAMoodle> docentesQueNoIngresan=notificacionesDocentesService.getDocentesNoIngresanAMoodle();
		String textoMail =  readTemplate(this.servletContext,"/template_notificaciones/docentes/trabajosPracticosExamenesPendienteDeSubida.html");
		
		//TODO llamar a la propiedad con el asunto del mail
		//String subject=celPropiedadService.get("mail_doc_no_ingreso_subject").getProValor();
		
		//TODO iterar resultados
//		for(VwDocentesNoIngresanAMoodle docenteQueNoIngresa:docentesQueNoIngresan)
//		{
//			String templateWithValues=remplazarValoresTemplate(docenteQueNoIngresa,textoMail);
//			enviarMail(docenteQueNoIngresa.getEmail(),subject,templateWithValues);
//		}
	}
	
	public void enviarMail(String mail,String subject,String templateWithValues)
	{
		if(prueba==null ||prueba.equals("true")){
			steMailService.enviarMail(mail+"algoparaquenollegue", from, replyTo, cc, null, subject, templateWithValues);
		}
		else
		{
			steMailService.enviarMail(mail, from, replyTo, cc, null, subject, templateWithValues);
		}
	}
	
	public static String readTemplate(ServletContext servletContext,String ubicacion) throws IOException
	{
		BufferedReader bufferedReaderTemplate;
		InputStream istreamTemplateMail = servletContext.getResourceAsStream(ubicacion);
		bufferedReaderTemplate = new BufferedReader(new InputStreamReader(istreamTemplateMail));
		
		String textoMail = "";
		
		String aux = bufferedReaderTemplate.readLine();
		
		while (aux != null) {
			textoMail += aux;
			aux = bufferedReaderTemplate.readLine();
		}
		bufferedReaderTemplate.close();
		
		return textoMail;
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
