package ar.com.celia.seguimiento_alumnos.notificaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Date;

import javax.servlet.ServletContext;

import ar.com.celia.seguimiento_alumnos.domain.CelAlertaEnviada;
import ar.com.celia.seguimiento_alumnos.service.CelAlertaEnviadaDefinition;
import ar.com.celia.seguimiento_alumnos.service.CelPropiedadDefinition;



public class Notificaciones {
	
	protected ServletContext servletContext = null;
	protected SteMailPoolService steMailService;
	protected CelPropiedadDefinition celPropiedadService;
	protected CelAlertaEnviadaDefinition celAlertaEnviadaService;
	
	protected String from;
	protected String replyTo;
	protected String cc;
	protected String prueba;
	
	protected static final String OBJ_TIPO_EXAMEN="mdl_quiz";
	protected static final String OBJ_TIPO_TP="mdl_assignment";
	
	protected static final String ALERTA_NUEVO_EXAMEN="ALU_NUEVO_QUIZ";
	protected static final String ALERTA_EXAMEN_POR_VENCER="ALU_QUIZ_POR_VENCER";
	protected static final String ALERTA_EXAMEN_VENCIDO="ALU_QUIZ_VENCIDO";
	protected static final String ALERTA_NUEVO_TP="ALU_NUEVO_TP";
	protected static final String ALERTA_TP_POR_VENCER="ALU_TP_POR_VENCER";
	protected static final String ALERTA_TP_VENCIDO="ALU_TP_VENCIDO";	
	protected static final String ALERTA_ALU_LIBRE="ALU_LIBRE";
	
	protected static final String ALERTA_DOC_EXAMEN_NOTA_PENDIENTE="DOC_EXAMEN_NOTA_PENDIENTE";
	protected static final String ALERTA_DOC_TP_NOTA_PENDIENTE="DOC_TP_NOTA_PENDIENTE";
	protected static final String ALERTA_DOC_EXAMEN_PENDIENTE_CARGA="DOC_EXAMEN_PENDIENTE_CARGA";
	protected static final String ALERTA_DOC_TP_PENDIENTE_CARGA="DOC_TPS_PENDIENTE_CARGA";
		
	
	public void init() throws Exception
	{
		from=celPropiedadService.get("mail_from").getProValor();
		replyTo=celPropiedadService.get("mail_reply_to").getProValor();
		//cc=celPropiedadService.get("mail_cc").getProValor();		
		prueba=celPropiedadService.get("entorno_pruebas").getProValor();
	}
	
	
	public void enviarMail(String mail,String subject,String templateWithValues,boolean registrarEnvio,String obj_tipo,Long obj_id,Long usr_id,String alerta) throws Exception
	{
		if(prueba==null ||prueba.equals("true")){
			steMailService.enviarMail(mail+"algoparaquenollegue", from, replyTo, cc, null, subject, templateWithValues);
		}
		else
		{
			steMailService.enviarMail(mail, from, replyTo, cc, null, subject, templateWithValues);
		}
		
		if(registrarEnvio)
			registrarEnvio(obj_tipo,obj_id,usr_id,alerta);
		
	}
	
	public void registrarEnvio(String obj_tipo,long obj_id,long usr_id,String alerta) throws Exception
	{	
		CelAlertaEnviada celAlerta=new CelAlertaEnviada();
		celAlerta.setAleAlerta(alerta);
		celAlerta.setAleObjId(obj_id);
		celAlerta.setAleObjTipo(obj_tipo);
		celAlerta.setAudFechaIns(new Date());
		celAlerta.setUsrId(usr_id);

		celAlertaEnviadaService.insert(celAlerta);
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

	public CelPropiedadDefinition getCelPropiedadService() {
		return celPropiedadService;
	}

	public void setCelPropiedadService(CelPropiedadDefinition celPropiedadService) {
		this.celPropiedadService = celPropiedadService;
	}
	
	public CelAlertaEnviadaDefinition getCelAlertaEnviadaService() {
		return celAlertaEnviadaService;
	}
	
	public void setCelAlertaEnviadaService(
			CelAlertaEnviadaDefinition celAlertaEnviadaService) {
		this.celAlertaEnviadaService = celAlertaEnviadaService;
	}
}
