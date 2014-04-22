package ar.com.celia.seguimiento_alumnos.controllers;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

import ar.com.celia.seguimiento_alumnos.domain.VwAlumnosActivos;
import ar.com.celia.seguimiento_alumnos.domain.VwIndicadoresAlumnos;

public class TabContactoActualController extends GenericForwardComposer {
	
	private Listbox lbIndicadores;
	
	public void onCreate$wdsDetalleCaso(Event evt) throws Exception {
	
		VwAlumnosActivos alumno=(VwAlumnosActivos)arg.get("alumno");
		if(alumno!=null)
		{
			java.util.Set <VwIndicadoresAlumnos> indicadoresSet=alumno.getIndicadoresAlumnos();
			generarGrilla(indicadoresSet);
		}
	}
	
	public void generarGrilla(java.util.Set <VwIndicadoresAlumnos> indicadoresSet){
		
		for(VwIndicadoresAlumnos indicador: indicadoresSet)
		{
			//Celda de Indicadores
			Listitem row=new Listitem();
			
			Listcell celdaIndicador=new Listcell();
			Label lblindicador= new Label();
			lblindicador.setMultiline(true);
			lblindicador.setPre(true);
			lblindicador.setHeight("3");
			lblindicador.setMaxlength(10);
			lblindicador.setValue(indicador.getDescIndicador()+": "+indicador.getValorIndicador());
						
			Listcell celdaImagen=new Listcell();
			Image imgIndicador=new Image();
			if(indicador.getValorIndicador()==1)imgIndicador.setSrc("/img/green.jpg");
			else if(indicador.getValorIndicador()==2)imgIndicador.setSrc("/img/red.jpg");
			else imgIndicador.setSrc("/img/yellow.jpg");
			
			Listcell celdaRespuesta=new Listcell();
			Label lblRespuesta= new Label();
			lblRespuesta.setValue("Respuesta");
			
			Listcell celdaListaRespuesta=new Listcell();
			Listbox lstCeldaListaRespuesta=new Listbox();
			lstCeldaListaRespuesta.setMold("select");
			
			Listcell celdaObservaciones=new Listcell();
			Label lblObservaciones= new Label();
			lblObservaciones.setValue("Respuesta");
			
			Listcell celdaCampoObservaciones=new Listcell();
			Textbox lstCeldaCampoObservaciones=new Textbox();
			lstCeldaCampoObservaciones.setRows(2);
			
			lblindicador.setParent(celdaIndicador);
			imgIndicador.setParent(celdaImagen);
			lblRespuesta.setParent(celdaRespuesta);
			lstCeldaListaRespuesta.setParent(celdaListaRespuesta);
			lblObservaciones.setParent(celdaObservaciones);
			lstCeldaCampoObservaciones.setParent(celdaCampoObservaciones);
			celdaIndicador.setParent(row);
			celdaImagen.setParent(row);
			celdaRespuesta.setParent(row);
			celdaListaRespuesta.setParent(row);
			celdaObservaciones.setParent(row);
			celdaCampoObservaciones.setParent(row);
			row.setParent(lbIndicadores);
			
		}
	}

}
