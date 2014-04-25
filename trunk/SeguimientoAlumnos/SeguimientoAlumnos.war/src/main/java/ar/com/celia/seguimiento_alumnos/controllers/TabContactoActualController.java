package ar.com.celia.seguimiento_alumnos.controllers;

import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import ar.com.celia.core.business.ContextManagerCore;
import ar.com.celia.seguimiento_alumnos.domain.CelDominio;
import ar.com.celia.seguimiento_alumnos.domain.CelInteraccionCaso;
import ar.com.celia.seguimiento_alumnos.domain.CelInteraccionCasoDetalle;
import ar.com.celia.seguimiento_alumnos.domain.VwAlumnosActivos;
import ar.com.celia.seguimiento_alumnos.domain.VwIndicadoresAlumnos;
import ar.com.celia.seguimiento_alumnos.service.CelDominioDefinition;
import ar.com.celia.seguimiento_alumnos.service.CelInteraccionCasoDefinition;
import ar.com.celia.seguimiento_alumnos.service.CelInteraccionCasoDetalleDefinition;

public class TabContactoActualController extends GenericForwardComposer {
	
	private Listbox lbIndicadores;
	private CelDominioDefinition celDominioService=(CelDominioDefinition)ContextManagerCore.getBizObject("celDominioService");
	private CelInteraccionCasoDefinition celInteraccionCasoService=(CelInteraccionCasoDefinition)ContextManagerCore.getBizObject("celInteraccionCasoService");
	private CelInteraccionCasoDetalleDefinition celInteraccionCasoDetalleService=(CelInteraccionCasoDetalleDefinition)ContextManagerCore.getBizObject("celInteraccionCasoDetalleService");
	private String dominioRespuestas="INDICADOR_CASO_RESPUESTA";
	private VwAlumnosActivos alumno=null;
	private Textbox tbxObservacionesGenerales;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute("controller", this, false);
	}
	
	public void onCreate$wdsTabContactoActual(Event evt) throws Exception {
	
		alumno=(VwAlumnosActivos)arg.get("alumno");
		if(alumno!=null)
		{
			java.util.Set <VwIndicadoresAlumnos> indicadoresSet=alumno.getIndicadoresAlumnos();
			generarGrilla(indicadoresSet);
		}
	}
	
	public void generarGrilla(java.util.Set <VwIndicadoresAlumnos> indicadoresSet) throws Exception
	{
				
		for(VwIndicadoresAlumnos indicador: indicadoresSet)
		{
			if(indicador.getValorIndicador()==2)
			{
				//Se crea la fila
				Listitem row=new Listitem();
				
				//Celda de Indicadores
				Listcell celdaIndicador=new Listcell();
				Label lblindicador= new Label();
				lblindicador.setMultiline(true);
				lblindicador.setPre(true);
				lblindicador.setHeight("3");
				lblindicador.setMaxlength(10);
				lblindicador.setValue(indicador.getDescIndicador());
				
				//Celda de Imagen Indicador
				Listcell celdaImagen=new Listcell();
				Image imgIndicador=new Image();
				if(indicador.getValorIndicador()==1)imgIndicador.setSrc("/img/green.jpg");
				else if(indicador.getValorIndicador()==2)imgIndicador.setSrc("/img/red.jpg");
				else imgIndicador.setSrc("/img/yellow.jpg");
				
				//Celda label respuesta
				Listcell celdaRespuesta=new Listcell();
				Label lblRespuesta= new Label();
				lblRespuesta.setValue("Respuesta:");
				
				//Celda de Combo respuestas
				Listcell celdaListaRespuesta=new Listcell();
				Listbox lstCeldaListaRespuesta=new Listbox();
				lstCeldaListaRespuesta.setMold("select");
				cargarComboDominio(lstCeldaListaRespuesta,dominioRespuestas);
				row.setAttribute("lstCeldaListaRespuesta", lstCeldaListaRespuesta);
				
				//Celda label observaciones
				Listcell celdaObservaciones=new Listcell();
				Label lblObservaciones= new Label();
				lblObservaciones.setValue("Observaciones:");
				
				//Celda campo observaciones
				Listcell celdaCampoObservaciones=new Listcell();
				Textbox lstCeldaCampoObservaciones=new Textbox();
				lstCeldaCampoObservaciones.setRows(2);
				lstCeldaCampoObservaciones.setWidth("100%");
				row.setAttribute("lstCeldaCampoObservaciones", lstCeldaCampoObservaciones);
				
				//Se agregan cada componente a la celda
				lblindicador.setParent(celdaIndicador);
				imgIndicador.setParent(celdaImagen);
				lblRespuesta.setParent(celdaRespuesta);
				lstCeldaListaRespuesta.setParent(celdaListaRespuesta);
				lblObservaciones.setParent(celdaObservaciones);
				lstCeldaCampoObservaciones.setParent(celdaCampoObservaciones);
				
				//Se agregan cada celda a fila
				celdaIndicador.setParent(row);
				celdaImagen.setParent(row);
				celdaRespuesta.setParent(row);
				celdaListaRespuesta.setParent(row);
				celdaObservaciones.setParent(row);
				celdaCampoObservaciones.setParent(row);
				
				//Se agregan la fila a listbox
				row.setParent(lbIndicadores);
			}
			
		}
	}
	
	public void cargarComboDominio(Listbox listbox, String dominio) throws Exception
	{
		List <CelDominio> listDominosRespuestas=celDominioService.getDominio(dominio, null);
		
		for (int i = 0; i < listDominosRespuestas.size(); i++) 
		{
			Listitem li= new Listitem();
			li.setValue(listDominosRespuestas.get(i));
			li.setLabel(listDominosRespuestas.get(i).getDomTexto());
			
			li.setParent(listbox);
		}
		
	}
	
	//¿que pasa si no hay cambios se guarda la interaccion en blanco o se le muestra un cartel que le recuerde que modifique algo y sino se sale?
	public void guardarInteraccion() throws Exception
	{
		alert("Entro  guardar la interaccion");
		
		List<Listitem> filas=lbIndicadores.getItems();
		
		if(!filas.isEmpty())
		{
//			CelInteraccionCaso cic=new CelInteraccionCaso();
//			cic.setAluId(alumno.getId());
			Date fecha=new Date();
//			cic.setAudFechaIns(fecha);
//			cic.setAudFechaUpd(fecha);
//			cic.setAudUsrIns("Usuario_logueado");
//			cic.setAudUsrUpd("Usuario_logueado");
//			cic.setCasObservacionesGrales(tbxObservacionesGenerales.getText());
			
//			celInteraccionCasoService.insert(cic);
			for (int i = 0; i < filas.size(); i++) 
			{
				Listitem fila=filas.get(i);
				Textbox txtObservaciones=(Textbox)fila.getAttribute("lstCeldaCampoObservaciones");
				Listbox ltbrespuestas=(Listbox)fila.getAttribute("lstCeldaListaRespuesta");
				
				if((txtObservaciones!=null && txtObservaciones.getValue().compareTo("")!=0)
				||(ltbrespuestas!=null && ltbrespuestas.getSelectedItem()!=null && ltbrespuestas.getSelectedItem().getValue()!=null)){
					CelInteraccionCasoDetalle cicd=new CelInteraccionCasoDetalle();
					cicd.setAudFechaIns(fecha);
					cicd.setAudFechaUpd(fecha);
					cicd.setAudUsrIns("Usuario_logueado");
					cicd.setAudUsrUpd("Usuario_logueado");
					
					cicd.setIcdObservaciones(txtObservaciones.getValue());
					
					CelDominio dom=(CelDominio)ltbrespuestas.getSelectedItem().getValue();
					cicd.setIcdRtaTipo(dom.getDomClave());
				}
				else
				{
					alert("No se carga cambios en la fila");
				}
			}
		}
		
		
	}

}
