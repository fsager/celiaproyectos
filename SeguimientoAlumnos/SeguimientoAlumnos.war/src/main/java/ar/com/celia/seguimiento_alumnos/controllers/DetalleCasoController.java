package ar.com.celia.seguimiento_alumnos.controllers;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Window;

import ar.com.celia.seguimiento_alumnos.domain.VwAlumnosActivos;

public class DetalleCasoController extends GenericForwardComposer {
	
	private Label lblLegajo;
	private Label lblContacto;
	private Label lblTelefono;
	private Label lblCarrera;
	private Label lblNombreUsuario;
	private Tabpanel tbpContactoActual;
	private Window wdsDetalleCaso;
	
	private TabContactoActualController tabContactoActualController=null;
	
	public void onCreate$wdsDetalleCaso(Event evt) throws Exception {
		
		VwAlumnosActivos alumno=(VwAlumnosActivos)arg.get("alumno");
		
		if(alumno!= null)
		{
			lblNombreUsuario.setValue(alumno.getLastname()+", "+alumno.getFirstname());
			lblLegajo.setValue(alumno.getIdnumber());
			lblLegajo.setValue(alumno.getPhone1()+" - "+alumno.getPhone2());
			lblLegajo.setValue(alumno.getIdnumber());
			cargarTabContactoActual(alumno);
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
	
	
	public void onClick$btnGuardar(Event evt) throws Exception {
		
		tabContactoActualController.guardarInteraccion();
		
	}
	
	public void onClick$btnCancelar(Event evt) throws Exception {
		wdsDetalleCaso.detach();
	}
}
