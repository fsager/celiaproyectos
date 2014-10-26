package ar.com.celia.seguimiento_alumnos.controllers;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
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
	private Tabpanel tbpBitacora;
	private Tab tbHistorico;
	private Window wndDetalleCaso;
	private BandejaCasosController bandejaCasosController=null;
	private VwAlumnosActivos alumno=null;
	private TabContactoActualController tabContactoActualController=null;
	
	public void onCreate$wndDetalleCaso(Event evt) throws Exception {
		
		alumno = (VwAlumnosActivos)arg.get("alumno");
		
		if(alumno != null) {
			lblMatricula.setValue(alumno.getMatricula());
			lblEmail.setValue(alumno.getEmail());
			lblNombreUsuario.setValue(alumno.getLastname()+", "+alumno.getFirstname());
			lblTelefono.setValue(alumno.getPhone1()+" - "+alumno.getPhone2());
			String contactos=(String)arg.get("contactos");
			lblContatos.setValue(contactos);
			cargarTabContactoActual(alumno);
			bandejaCasosController=(BandejaCasosController)arg.get("bandejaController");
		}

	}
	
	
	public void cargarTabContactoActual(VwAlumnosActivos alumno) throws Exception {
			java.util.Properties params = new java.util.Properties();
			params.put("alumno", alumno);
			Window win = (Window) Executions.createComponents(
					"/celia/tab_Contacto_actual.zul", tbpContactoActual, params);
			
			tabContactoActualController = (TabContactoActualController)win.getAttribute("wdsTabContactoActual$composer");
	}

	
	public void cargarTabBitacora(VwAlumnosActivos alumno) throws Exception {
		tbpBitacora.getChildren().clear();
		java.util.Properties params = new java.util.Properties();
		
		params.put("alumno", alumno);
		Window win = (Window) Executions.createComponents(
				"/celia/bitacora/ver_bitacora.zul", tbpBitacora, params);
	}
	
	
	public void cargarTabHistorico(VwAlumnosActivos alumno) throws Exception {
		tbpHistorico.getChildren().clear();	
		java.util.Properties params = new java.util.Properties();
			
			params.put("alumno", alumno);
			Window win = (Window) Executions.createComponents(
					"/celia/historico_casos_alumno.zul", tbpHistorico, params);
	}
	
	
	public void onSelect$tbHistorico(Event ev) throws Exception {
		cargarTabHistorico(alumno);
	}
	
	public void onSelect$tbBitacora(Event ev) throws Exception {
		cargarTabBitacora(alumno);
	}
	
	
	public void onClick$btnGuardar(Event evt) throws Exception {
		Messagebox.show("¿Desea guardar la interacción actual?",
				"Confirmación", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(Event evt) throws Exception {
						if (Messagebox.ON_OK.equals(evt.getName())) {
							boolean ok = tabContactoActualController.guardarInteraccion();
							if(ok) {
								bandejaCasosController.cargarBandeja();
								wndDetalleCaso.detach();
							}
						}
					}
				});
	}
	
	public void onClick$btnCancelar(Event evt) throws Exception {
		wndDetalleCaso.detach();
	}
}
