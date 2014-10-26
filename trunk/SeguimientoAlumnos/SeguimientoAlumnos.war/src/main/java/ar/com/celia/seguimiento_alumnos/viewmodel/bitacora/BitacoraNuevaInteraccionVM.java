package ar.com.celia.seguimiento_alumnos.viewmodel.bitacora;

import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import ar.com.celia.seguimiento_alumnos.domain.CelBitacoraAlumnos;
import ar.com.celia.seguimiento_alumnos.domain.CelDominio;
import ar.com.celia.seguimiento_alumnos.domain.CelUsuario;
import ar.com.celia.seguimiento_alumnos.domain.VwAlumnosActivos;
import ar.com.celia.seguimiento_alumnos.service.CelBitacoraAlumnosDefinition;
import ar.com.celia.seguimiento_alumnos.service.CelDominioDefinition;

public class BitacoraNuevaInteraccionVM {

	private CelDominio origenContactoSelected;
	private CelDominio motivoContactoSelected;
	private List<CelDominio> lstOrigenesContacto;
	private List<CelDominio> lstMotivosContacto;
	private CelBitacoraAlumnos celBitacoraAlumnos;
	private VwAlumnosActivos alumno;
	private String userPrincipal;
	private CelUsuario celUsuario;
	
	
	@Wire
	Window wndBitacoraNuevaInteraccion;
	
	@WireVariable
	private CelDominioDefinition celDominioService;
	
	@WireVariable
	private CelBitacoraAlumnosDefinition celBitacoraAlumnosService;
		
	@Init
	public void init() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("alumno") VwAlumnosActivos alumnoParam) throws Exception {
		Selectors.wireComponents(view, this, false);

		userPrincipal = Executions.getCurrent().getUserPrincipal().toString();
		alumno = alumnoParam;
		
		celBitacoraAlumnos = new CelBitacoraAlumnos();
		
		lstOrigenesContacto = celDominioService.getDominio("bitacora.origen.contacto");
		lstMotivosContacto = celDominioService.getDominio("bitacora.motivo.contacto");
	}
	
	
	@Command
	public void guardarInteraccion() throws Exception {
		Messagebox.show("¿Desea guardar la interacción actual?",
				"Confirmación", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(Event evt) throws Exception {
						if (Messagebox.ON_OK.equals(evt.getName())) {
							
							celBitacoraAlumnos.setAudFechaIns(new Date());
							celBitacoraAlumnos.setAudFechaUpd(new Date());
							celBitacoraAlumnos.setAudUsrIns(userPrincipal);
							celBitacoraAlumnos.setAudUsrUpd(userPrincipal);
							celBitacoraAlumnos.setUsrId(alumno.getId());
							
							if (origenContactoSelected == null) {
								Clients.showNotification("Seleccione un tipo de origen para el contacto actual.", "error", null, "middle_center", 3000);
								return;
							}
							
							if (motivoContactoSelected == null) {
								Clients.showNotification("Seleccione un tipo de motivo para el contacto actual.", "error", null, "middle_center", 3000);
								return;
							}
							
							if (celBitacoraAlumnos.getBtcDescripcionContacto() == null) {
								Clients.showNotification("Ingrese una descripción.", "error", null, "middle_center", 3000);
								return;
							}
							
							celBitacoraAlumnos.setBtcOrigenContacto(origenContactoSelected.getDomClave());
							celBitacoraAlumnos.setBtcMotivoContacto(motivoContactoSelected.getDomClave());
							
							celBitacoraAlumnosService.insert(celBitacoraAlumnos);
							
							Clients.showNotification("La información se ha guardado exitosamente.", "info", null, "middle_center", 3000);
							wndBitacoraNuevaInteraccion.detach();
						}
					}
				});
	}


	public CelDominio getOrigenContactoSelected() {
		return origenContactoSelected;
	}


	public void setOrigenContactoSelected(CelDominio origenContactoSelected) {
		this.origenContactoSelected = origenContactoSelected;
	}


	public CelDominio getMotivoContactoSelected() {
		return motivoContactoSelected;
	}


	public void setMotivoContactoSelected(CelDominio motivoContactoSelected) {
		this.motivoContactoSelected = motivoContactoSelected;
	}


	public List<CelDominio> getLstOrigenesContacto() {
		return lstOrigenesContacto;
	}


	public void setLstOrigenesContacto(List<CelDominio> lstOrigenesContacto) {
		this.lstOrigenesContacto = lstOrigenesContacto;
	}


	public List<CelDominio> getLstMotivosContacto() {
		return lstMotivosContacto;
	}


	public void setLstMotivosContacto(List<CelDominio> lstMotivosContacto) {
		this.lstMotivosContacto = lstMotivosContacto;
	}


	public VwAlumnosActivos getAlumno() {
		return alumno;
	}


	public void setAlumno(VwAlumnosActivos alumno) {
		this.alumno = alumno;
	}


	public CelUsuario getCelUsuario() {
		return celUsuario;
	}


	public void setCelUsuario(CelUsuario celUsuario) {
		this.celUsuario = celUsuario;
	}


	public CelBitacoraAlumnos getCelBitacoraAlumnos() {
		return celBitacoraAlumnos;
	}


	public void setCelBitacoraAlumnos(CelBitacoraAlumnos celBitacoraAlumnos) {
		this.celBitacoraAlumnos = celBitacoraAlumnos;
	}
	
}