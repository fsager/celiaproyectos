package ar.com.celia.seguimiento_alumnos.viewmodel.bitacora;

import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import ar.com.celia.seguimiento_alumnos.domain.CelBitacoraAlumnos;
import ar.com.celia.seguimiento_alumnos.domain.CelDominio;
import ar.com.celia.seguimiento_alumnos.domain.CelUsuario;
import ar.com.celia.seguimiento_alumnos.domain.VwAlumnosActivos;
import ar.com.celia.seguimiento_alumnos.service.CelBitacoraAlumnosDefinition;
import ar.com.celia.seguimiento_alumnos.service.CelDominioDefinition;

public class VerBitacoraVM {

	private List<CelDominio> lstOrigenesContacto;
	private List<CelDominio> lstMotivosContacto;
	private VwAlumnosActivos alumno;
	private CelUsuario celUsuario;
	
	@Wire
	Window wndVerBitacora;
	
	@Wire
	Listbox lbInteraccionesBitacora;
	
	@WireVariable
	private CelDominioDefinition celDominioService;
	
	@WireVariable
	private CelBitacoraAlumnosDefinition celBitacoraAlumnosService;
		
	private List<CelBitacoraAlumnos> lstInteraccionesBitacoraAlumno;
	
	private HashMap<String, String> hashMapOrigenesContacto = new HashMap<String, String>();
	private HashMap<String, String> hashMapMotivosContacto = new HashMap<String, String>();
	
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

		alumno = alumnoParam;
	
		lstInteraccionesBitacoraAlumno = celBitacoraAlumnosService.getBitacoraPorUsrId(alumno.getId());
		
		lstOrigenesContacto = celDominioService.getDominio("bitacora.origen.contacto");
		lstMotivosContacto = celDominioService.getDominio("bitacora.motivo.contacto");
		
		for (CelDominio celOrigenesContacto : lstOrigenesContacto) {
			hashMapOrigenesContacto.put(celOrigenesContacto.getDomClave(), celOrigenesContacto.getDomTexto());
		}
		
		for (CelDominio celMotivosContacto : lstMotivosContacto) {
			hashMapMotivosContacto.put(celMotivosContacto.getDomClave(), celMotivosContacto.getDomTexto());
		}
		
	}
	
	
	@Command
	public void cerrar() throws Exception {
		wndVerBitacora.detach();
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

	public List<CelBitacoraAlumnos> getLstInteraccionesBitacoraAlumno() {
		return lstInteraccionesBitacoraAlumno;
	}


	public void setLstInteraccionesBitacoraAlumno(
			List<CelBitacoraAlumnos> lstInteraccionesBitacoraAlumno) {
		this.lstInteraccionesBitacoraAlumno = lstInteraccionesBitacoraAlumno;
	}


	public HashMap<String, String> getHashMapOrigenesContacto() {
		return hashMapOrigenesContacto;
	}


	public void setHashMapOrigenesContacto(
			HashMap<String, String> hashMapOrigenesContacto) {
		this.hashMapOrigenesContacto = hashMapOrigenesContacto;
	}


	public HashMap<String, String> getHashMapMotivosContacto() {
		return hashMapMotivosContacto;
	}


	public void setHashMapMotivosContacto(
			HashMap<String, String> hashMapMotivosContacto) {
		this.hashMapMotivosContacto = hashMapMotivosContacto;
	}
	
}