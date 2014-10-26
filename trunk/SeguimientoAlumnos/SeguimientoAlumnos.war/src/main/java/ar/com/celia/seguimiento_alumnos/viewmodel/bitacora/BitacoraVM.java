package ar.com.celia.seguimiento_alumnos.viewmodel.bitacora;

import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import ar.com.celia.core.business.ContextManagerCore;
import ar.com.celia.seguimiento_alumnos.domain.VwAlumnosActivos;
import ar.com.celia.seguimiento_alumnos.service.VwAlumnosActivosDefinition;

public class BitacoraVM {

	private VwAlumnosActivosDefinition vwAlumnosActivosService = (VwAlumnosActivosDefinition) ContextManagerCore.getBizObject("vwAlumnosActivosService");
	private List<VwAlumnosActivos> alumnosActivos; 
	private VwAlumnosActivos alumnoSeleccionado;
	
	String apellidoAlumnoFiltro = "";
	String nombreAlumnoFiltro = "";
	
	
	@Init
	public void init() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) throws Exception {
		Selectors.wireComponents(view, this, false);
	
		filtrarAlumnos();
	}
	
	
	@Command
	@NotifyChange("alumnosActivos")
	public void filtrarAlumnos() throws Exception {
		VwAlumnosActivos alumnoActivoExample = new VwAlumnosActivos();
		alumnoActivoExample.setLastname("%"+apellidoAlumnoFiltro.trim()+"%");
		alumnoActivoExample.setFirstname("%"+nombreAlumnoFiltro.trim()+"%");
		
		alumnosActivos = vwAlumnosActivosService.getAll(alumnoActivoExample, null);
	}
	
	
	@Command
	public void nuevaInteraccion() throws Exception {
		if (alumnoSeleccionado == null) {
			Clients.showNotification("Debe seleccionar un alumno", "error", null, "middle_center", 2000);
			return;
		}
		
	    java.util.Properties params = new java.util.Properties();
        params.put("alumno", alumnoSeleccionado);
		Window win = (Window) Executions.createComponents("/celia/bitacora/bitacora_nueva_interaccion.zul", null, params);
		win.doModal();
	}
	
	
	@Command
	public void verBitacora() throws Exception {
		if (alumnoSeleccionado == null) {
			Clients.showNotification("Debe seleccionar un alumno", "error", null, "middle_center", 2000);
			return;
		}
		
	    java.util.Properties params = new java.util.Properties();
        params.put("alumno", alumnoSeleccionado);
		Window win = (Window) Executions.createComponents("/celia/bitacora/ver_bitacora.zul", null, params);
		win.setTitle("Bitácora de "+alumnoSeleccionado.getNombreCompleto());
		win.setWidth("700px");
		win.setClosable(true);
		win.doModal();
	}
	
	
	public List<VwAlumnosActivos> getAlumnosActivos() {
		return alumnosActivos;
	}


	public void setAlumnosActivos(List<VwAlumnosActivos> alumnosActivos) {
		this.alumnosActivos = alumnosActivos;
	}

	
	public String getApellidoAlumnoFiltro() {
		return apellidoAlumnoFiltro;
	}


	public void setApellidoAlumnoFiltro(String apellidoAlumnoFiltro) {
		this.apellidoAlumnoFiltro = apellidoAlumnoFiltro;
	}


	public String getNombreAlumnoFiltro() {
		return nombreAlumnoFiltro;
	}


	public void setNombreAlumnoFiltro(String nombreAlumnoFiltro) {
		this.nombreAlumnoFiltro = nombreAlumnoFiltro;
	}


	public VwAlumnosActivos getAlumnoSeleccionado() {
		return alumnoSeleccionado;
	}


	public void setAlumnoSeleccionado(VwAlumnosActivos alumnoSeleccionado) {
		this.alumnoSeleccionado = alumnoSeleccionado;
	}

}