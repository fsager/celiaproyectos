package ar.com.celia.seguimiento_alumnos.controllers;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Window;

import ar.com.celia.seguimiento_alumnos.domain.VwAlumnosActivos;

public class DetalleCasoController extends GenericForwardComposer {
	
	private Label lblContatos;
	private Label lblTelefono;
	private Label lblMatricula;
	private Label lblNombreUsuario;
	private Label lblEmail;
	private Tabpanel tbpContactoActual;
	private Tabpanel tbpHistorico;
	private Tab tbHistorico;
	private Window wdsDetalleCaso;
	private BandejaCasosController bandejaCasosController=null;
	private VwAlumnosActivos alumno=null;
	private TabContactoActualController tabContactoActualController=null;
	
	public void onCreate$wdsDetalleCaso(Event evt) throws Exception {
		
		alumno=(VwAlumnosActivos)arg.get("alumno");
		
		if(alumno!= null)
		{
			lblMatricula.setValue(alumno.getMatricula());
			lblEmail.setValue(alumno.getEmail());
			lblNombreUsuario.setValue(alumno.getLastname()+", "+alumno.getFirstname());
			lblTelefono.setValue(alumno.getPhone1()+" - "+alumno.getPhone2());
			String contactos=(String)arg.get("contactos");
			lblContatos.setValue(contactos);
			cargarTabContactoActual(alumno);
			bandejaCasosController=(BandejaCasosController)arg.get("bandejaCotroller");
		}

	}
	
	
	public void cargarTabContactoActual(VwAlumnosActivos alumno) throws Exception
	{
		
		if (alumno != null) 
		{
			java.util.Properties params = new java.util.Properties();
			
			
			params.put("alumno", alumno);
			Window win = (Window) Executions.createComponents(
					"/celia/tab_Contacto_actual.zul", tbpContactoActual,params);
			
			tabContactoActualController=(TabContactoActualController)win.getAttribute("wdsTabContactoActual$composer");
			
		}

	}
	
	public void onSelect$tbHistorico(Event ev) throws Exception {
		cargarTabHistorcio(alumno);
	}
	
	public void cargarTabHistorcio(VwAlumnosActivos alumno) throws Exception
	{
		
		if (alumno != null) 
		{
			java.util.Properties params = new java.util.Properties();
			
			
			params.put("alumno", alumno);
			Window win = (Window) Executions.createComponents(
					"/celia/tab_historico.zul", tbpHistorico,params);
			
			tabContactoActualController=(TabContactoActualController)win.getAttribute("wdsTabContactoActual$composer");
			
		}

	}
	
	public void onClick$btnGuardar(Event evt) throws Exception {
		
		tabContactoActualController.guardarInteraccion();
		bandejaCasosController.cargarBandeja();
		wdsDetalleCaso.detach();
		
	}
	
	public void onClick$btnCancelar(Event evt) throws Exception {
		wdsDetalleCaso.detach();
	}
}
