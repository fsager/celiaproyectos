package ar.com.celia.seguimiento_alumnos.viewmodel;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkmax.zul.Filedownload;
import org.zkoss.zul.Listbox;

import ar.com.celia.seguimiento_alumnos.comparator.VwNotasAlumnoComparator;
import ar.com.celia.seguimiento_alumnos.domain.VwEtapa;
import ar.com.celia.seguimiento_alumnos.domain.VwEvaluacion;
import ar.com.celia.seguimiento_alumnos.domain.VwMateria;
import ar.com.celia.seguimiento_alumnos.domain.VwNotasAlumno;
import ar.com.celia.seguimiento_alumnos.domain.VwPeriodo;
import ar.com.celia.seguimiento_alumnos.exporter.AuditoriaAlumnosXLSExporter;
import ar.com.celia.seguimiento_alumnos.service.VwEtapaDefinition;
import ar.com.celia.seguimiento_alumnos.service.VwMateriaDefinition;
import ar.com.celia.seguimiento_alumnos.service.VwNotasAlumnoDefinition;
import ar.com.celia.seguimiento_alumnos.service.VwPeriodoDefinition;
import ar.com.celia.seguimiento_alumnos.zkrenderer.NotasAlumnoListRenderer;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AuditoriaAlumnosVM {

	@WireVariable
	private VwPeriodoDefinition vwPeriodoService;
	@WireVariable
	private VwEtapaDefinition vwEtapaService;
	@WireVariable
	private VwMateriaDefinition vwMateriaService;
	@WireVariable
	private VwNotasAlumnoDefinition vwNotasAlumnoService;
	
	@Wire
	private Listbox lbNotas;
	private NotasAlumnoListRenderer listitemRenderer; 
		
	private List<VwPeriodo> periodos;
	private List<VwMateria> materias;
	private Map<VwNotasAlumno, List<VwNotasAlumno>> alumnoNotas;
		
	private String nombreAlumno;
	private String apellidoAlumno;
	private VwPeriodo periodoSelected;
	private VwEtapa etapaSelected;
	
	private Boolean filtrarHabilitado;
	
	@Init
	public void init() {
		try {
			materias = new ArrayList<VwMateria>();
			
			periodos = vwPeriodoService.getAll(new VwPeriodo(), new String[]{"vwEtapas"});			
			
			if(!(filtrarHabilitado = !periodos.isEmpty() && !periodos.get(0).getVwEtapas().isEmpty())) return;
			
			periodoSelected = periodos.get(0);
			etapaSelected = periodoSelected.getVwEtapas().get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) throws Exception{
        Selectors.wireComponents(view, this, false);
        listitemRenderer = new NotasAlumnoListRenderer(materias,lbNotas);
        filtrar();
    }
	
	@Command
	@NotifyChange({"etapas","etapaSelected","filtrarHabilitado"})
	public void actualizarEtapas(){
		etapaSelected = periodoSelected.getVwEtapas().get(0);
		filtrarHabilitado = !periodos.isEmpty() && etapaSelected != null;
	}
	
	@Command
	@NotifyChange("alumnoNotas")
	public void filtrar() throws Exception{
		if(!filtrarHabilitado)return;
		materias.clear();
		VwMateria vwMateriaExample = new VwMateria();
		vwMateriaExample.setVwEtapa(etapaSelected);
		materias.addAll(vwMateriaService.getAll(vwMateriaExample, new String[]{"trabajosPracticos","examenes"}));

		listitemRenderer.actualizarListboxHeader();
		actualizarNotasAlumnos();
	}
	
	@Command
	public void exportar() throws Exception{
		AuditoriaAlumnosXLSExporter alumnosXLSExporter = new AuditoriaAlumnosXLSExporter();
		
		File export = alumnosXLSExporter.export(
				System.currentTimeMillis()+".xls", 
				periodoSelected, 
				etapaSelected, 
				materias, 
				alumnoNotas);
		Filedownload.saveResumable(export, "application/vnd.ms-excel",export.getName());
	}
	
	private void actualizarNotasAlumnos() throws Exception {
		lbNotas.getItems().clear();
		VwNotasAlumno vwNotasAlumnoExample = new VwNotasAlumno();
		if(nombreAlumno != null && !nombreAlumno.trim().isEmpty())
			vwNotasAlumnoExample.setFirstname(nombreAlumno.toLowerCase());
		if(apellidoAlumno!= null && !apellidoAlumno.trim().isEmpty())
			vwNotasAlumnoExample.setLastname(apellidoAlumno.toLowerCase());
		vwNotasAlumnoExample.setEtpCatId(etapaSelected.getEtpId());
		vwNotasAlumnoExample.setPerCatId(etapaSelected.getVwPeriodo().getPerId());
		
		List<VwNotasAlumno> notasAlumnos = vwNotasAlumnoService.getAll(vwNotasAlumnoExample, null);
		
		alumnoNotas = new TreeMap<>(new VwNotasAlumnoComparator());
		
		for (VwNotasAlumno vwNotasAlumno : notasAlumnos) {
			if(!alumnoNotas.containsKey(vwNotasAlumno))
				alumnoNotas.put(vwNotasAlumno, new ArrayList<VwNotasAlumno>(lbNotas.getListhead().getChildren().size()));
			alumnoNotas.get(vwNotasAlumno).add(vwNotasAlumno);
		}
	}

	public List<VwEvaluacion> getAllEvaluaciones(){
		List<VwEvaluacion> res = new LinkedList<VwEvaluacion>();

		for (VwMateria vwMateria : materias) {
			res.addAll(vwMateria.getTrabajosPracticos());
			res.addAll(vwMateria.getExamenes());
		}
		
		return res; 
	}
	
	public NotasAlumnoListRenderer getListitemRenderer() {
		return listitemRenderer;
	}

	public void setListitemRenderer(NotasAlumnoListRenderer listitemRenderer) {
		this.listitemRenderer = listitemRenderer;
	}

	public String getNombreAlumno() {
		return nombreAlumno;
	}

	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}

	public String getApellidoAlumno() {
		return apellidoAlumno;
	}

	public void setApellidoAlumno(String apellidoAlumno) {
		this.apellidoAlumno = apellidoAlumno;
	}

	public List<VwPeriodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<VwPeriodo> periodos) {
		this.periodos = periodos;
	}

	public VwPeriodo getPeriodoSelected() {
		return periodoSelected;
	}

	public void setPeriodoSelected(VwPeriodo periodoSelected) {
		this.periodoSelected = periodoSelected;
	}

	public VwEtapa getEtapaSelected() {
		return etapaSelected;
	}

	public void setEtapaSelected(VwEtapa etapaSelected) {
		this.etapaSelected = etapaSelected;
	}

	public List<VwMateria> getMaterias() {
		return materias;
	}

	public void setMaterias(List<VwMateria> materias) {
		this.materias = materias;
	}

	public Map<VwNotasAlumno, List<VwNotasAlumno>> getAlumnoNotas() {
		return alumnoNotas;
	}

	public void setAlumnoNotas(Map<VwNotasAlumno, List<VwNotasAlumno>> alumnoNotas) {
		this.alumnoNotas = alumnoNotas;
	}

	public Boolean getFiltrarHabilitado() {
		return filtrarHabilitado;
	}

	public void setFiltrarHabilitado(Boolean filtrarHabilitado) {
		this.filtrarHabilitado = filtrarHabilitado;
	}
	
}
