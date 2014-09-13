package ar.com.celia.seguimiento_alumnos.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import ar.com.celia.core.business.ContextManagerCore;
import ar.com.celia.seguimiento_alumnos.business.Utils;
import ar.com.celia.seguimiento_alumnos.domain.CelDominio;
import ar.com.celia.seguimiento_alumnos.domain.CelIndicador;
import ar.com.celia.seguimiento_alumnos.domain.CelInteraccionCaso;
import ar.com.celia.seguimiento_alumnos.domain.CelInteraccionCasoDetalle;
import ar.com.celia.seguimiento_alumnos.domain.CelPropiedad;
import ar.com.celia.seguimiento_alumnos.domain.VwAlumnosActivos;
import ar.com.celia.seguimiento_alumnos.domain.VwIndicadoresAlumnos;
import ar.com.celia.seguimiento_alumnos.service.CelDominioDefinition;
import ar.com.celia.seguimiento_alumnos.service.CelIndicadorDefinition;
import ar.com.celia.seguimiento_alumnos.service.CelInteraccionCasoDefinition;
import ar.com.celia.seguimiento_alumnos.service.CelInteraccionCasoDetalleDefinition;
import ar.com.celia.seguimiento_alumnos.service.CelPropiedadDefinition;

public class TabContactoActualController extends GenericForwardComposer {
	
	private Listbox lbIndicadores;
	private CelDominioDefinition celDominioService=(CelDominioDefinition)ContextManagerCore.getBizObject("celDominioService");
	private CelInteraccionCasoDefinition celInteraccionCasoService=(CelInteraccionCasoDefinition)ContextManagerCore.getBizObject("celInteraccionCasoService");
	private CelInteraccionCasoDetalleDefinition celInteraccionCasoDetalleService=(CelInteraccionCasoDetalleDefinition)ContextManagerCore.getBizObject("celInteraccionCasoDetalleService");
	private CelIndicadorDefinition celIndicadorService=(CelIndicadorDefinition)ContextManagerCore.getBizObject("celIndicadorService");
	private String dominioRespuestas="INDICADOR_CASO_RESPUESTA";
	private VwAlumnosActivos alumno=null;
	private Textbox tbxObservacionesGenerales;
	private String userPrincipal;
	private CelPropiedadDefinition celPropiedadService=(CelPropiedadDefinition)ContextManagerCore.getBizObject("celPropiedadService");
	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute("controller", this, false);
		userPrincipal=execution.getUserPrincipal().toString();
	}
	
	
	public void onCreate$wdsTabContactoActual(Event evt) throws Exception {
	
		alumno=(VwAlumnosActivos)arg.get("alumno");
		if(alumno!=null)
		{
			java.util.Set <VwIndicadoresAlumnos> indicadoresSet=alumno.getIndicadoresAlumnos();
			generarGrilla(indicadoresSet);
		}
	}
	
	
	public void generarGrilla(java.util.Set <VwIndicadoresAlumnos> indicadoresSet) throws Exception {
		for(VwIndicadoresAlumnos indicador: indicadoresSet) {
			if(indicador.getValorIndicador() >= 2) {
				//Se crea la fila
				Listitem row=new Listitem();
				
				//Celda de Indicadores
				Listcell celdaIndicador=new Listcell();
				Label lblindicador= new Label();
				lblindicador.setMultiline(true);
				lblindicador.setPre(true);
				lblindicador.setHeight("3");
				lblindicador.setMaxlength(10);
				lblindicador.setValue(indicador.getNombreIndicador());
				lblindicador.setTooltiptext(indicador.getDescIndicador());
				row.setAttribute("indicador", indicador);
				
				//Celda de Imagen Indicador
				Listcell celdaImagen=new Listcell();
				Image imgIndicador=new Image();
				
				CelPropiedad pro=celPropiedadService.get("IND_"+indicador.getIdIndicador()+"_ROJO");
				Long valorRojo=null;
				if(pro!=null)
				{
					valorRojo=new Long(pro.getProValor());
				}
				
    			String srcImage=Utils.getImageIndicador(valorRojo,indicador);
    			imgIndicador.setSrc(srcImage);
    			imgIndicador.setWidth("29px");
    			imgIndicador.setHeight("29px");
				
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
				lstCeldaCampoObservaciones.setMaxlength(2000);
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
	
	
	public void cargarComboDominio(Listbox listbox, String dominio) throws Exception {
		List <CelDominio> listDominosRespuestas=celDominioService.getDominio(dominio, null);
		
		for (int i = 0; i < listDominosRespuestas.size(); i++) {
			Listitem li= new Listitem();
			li.setValue(listDominosRespuestas.get(i));
			li.setLabel(listDominosRespuestas.get(i).getDomTexto());
			
			li.setParent(listbox);
		}
	}
	
	
	/*
	 * Se guarda la interacción si se cargó al menos un indicador o bien las observaciones generales, sin indicadores.
	 */
	public boolean guardarInteraccion() throws Exception {
		List<CelInteraccionCasoDetalle> lstDetallesPendientes = new ArrayList<CelInteraccionCasoDetalle>();
		List<Listitem> lstItems = lbIndicadores.getItems();

		for (Listitem li : lstItems) {
			Textbox txtObservaciones = (Textbox) li.getAttribute("lstCeldaCampoObservaciones");
			Listbox lbRespuestas = (Listbox) li.getAttribute("lstCeldaListaRespuesta");
			
			//Si hay una respuesta tipificada cargada para el indicador, la guardamos en la lista de indicadores a almacenar.
			if(lbRespuestas.getSelectedItem() != null) {
				VwIndicadoresAlumnos vwIndicador = (VwIndicadoresAlumnos) li.getAttribute("indicador");
				CelIndicador celIndicador = celIndicadorService.get(vwIndicador.getIdIndicador());
				CelInteraccionCasoDetalle cicd = new CelInteraccionCasoDetalle();
				cicd.setAudFechaIns(new Date());
				cicd.setAudFechaUpd(new Date());
				cicd.setAudUsrIns(userPrincipal);
				cicd.setAudUsrUpd(userPrincipal);
				cicd.setIcdObservaciones(txtObservaciones.getValue());
				cicd.setCelIndicador(celIndicador);
				CelDominio dom = (CelDominio) lbRespuestas.getSelectedItem().getValue();
				cicd.setIcdRtaTipo(dom.getDomClave());
				lstDetallesPendientes.add(cicd);
			}
		}

		//Si no hay información cargada para al menos un indicador ni se registran observaciones generales, no se permite insertar.
		if (lstDetallesPendientes.isEmpty() && tbxObservacionesGenerales.getValue().trim().isEmpty()) {
			Clients.showNotification("Para registrar el contacto debe cargar algún campo", "error", null, "middle_center", 3000);
			return false;
		}
 
		
		//Guardamos el caso y los detalles asociados al mismo.
		CelInteraccionCaso cic = new CelInteraccionCaso();
		cic.setAluId(alumno.getId());
		cic.setAudFechaIns(new Date());
		cic.setAudFechaUpd(new Date());
		cic.setAudUsrIns(userPrincipal);
		cic.setAudUsrUpd(userPrincipal);
		cic.setCasObservacionesGrales(tbxObservacionesGenerales.getValue());
		celInteraccionCasoService.insert(cic);
		
		for (CelInteraccionCasoDetalle cicd : lstDetallesPendientes) {
			cicd.setCelInteraccionCaso(cic);
			celInteraccionCasoDetalleService.insert(cicd);
		}
		
		Clients.showNotification("La información del contacto se guardó con éxito", "info", null, "middle_center", 3000);
		
		return true;
	}


}
