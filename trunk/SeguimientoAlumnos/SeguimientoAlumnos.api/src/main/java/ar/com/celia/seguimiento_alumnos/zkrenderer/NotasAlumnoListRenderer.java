package ar.com.celia.seguimiento_alumnos.zkrenderer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Auxhead;
import org.zkoss.zul.Auxheader;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import ar.com.celia.seguimiento_alumnos.domain.VwEvaluacion;
import ar.com.celia.seguimiento_alumnos.domain.VwExamen;
import ar.com.celia.seguimiento_alumnos.domain.VwMateria;
import ar.com.celia.seguimiento_alumnos.domain.VwNotasAlumno;
import ar.com.celia.seguimiento_alumnos.domain.VwTrabajoPractico;

public class NotasAlumnoListRenderer implements ListitemRenderer<Entry<VwNotasAlumno, List<VwNotasAlumno>>> {

	private List<VwMateria> materias;
	private Listbox lbNotas;
	
	public NotasAlumnoListRenderer(List<VwMateria> materias, Listbox lbNotas) {
		super();
		this.materias = materias;
		this.lbNotas = lbNotas;
	}

	@Override
	public void render(Listitem liNotasAlumno, Entry<VwNotasAlumno, List<VwNotasAlumno>> vwNotasAlumnoEntry, int index) throws Exception {
		
		Listbox lbNotas = liNotasAlumno.getListbox();
		
		VwNotasAlumno vwNotasAlumno = vwNotasAlumnoEntry.getKey();
		
		new Listcell(vwNotasAlumno.getLastname()).setParent(liNotasAlumno);
		new Listcell(vwNotasAlumno.getFirstname()).setParent(liNotasAlumno);
		List<Component> comp = lbNotas.getListhead().getChildren();
		
		evaluaciones:
		for (int i=2; i<comp.size();i++) {//empieza en dos para evitar los dos primeros headers que corresponden con el apellido y nombre
			Component component = comp.get(i);
			
			VwEvaluacion evaluacion = (VwEvaluacion) component.getAttribute("evaluacion");
			
			Listcell listcellNota = new Listcell("-");
			listcellNota.setParent(liNotasAlumno);
			
			if(evaluacion==null) continue evaluaciones;
			
			Iterator<VwNotasAlumno> alumnoEvalNotaIterator = new LinkedList<>(vwNotasAlumnoEntry.getValue()).iterator();
			
			//notasAlumno:
			while(alumnoEvalNotaIterator.hasNext()){
				VwNotasAlumno vwNotasAlumnoEvalNota = alumnoEvalNotaIterator.next();
				if(vwNotasAlumnoEvalNota.getTipoEvaluacion().equals(evaluacion.getTipoEvaluacion()) 
				&& vwNotasAlumnoEvalNota.getEvalId().equals(evaluacion.getId())){
					listcellNota.setLabel(vwNotasAlumnoEvalNota.getNotaDivididoDiez()+"");
					listcellNota.setTooltiptext(evaluacion.getTitulo()+" - "+listcellNota.getLabel());
					alumnoEvalNotaIterator.remove();
					continue evaluaciones;
				}
			}
			listcellNota.setTooltiptext(evaluacion.getTitulo()+" - Sin Nota/Ausente");
		}
	}
	
	public void actualizarListboxHeader() {
		if(lbNotas.getListhead() != null)lbNotas.getListhead().detach();
		Iterator<Component> i = lbNotas.getChildren().iterator();
		while(i.hasNext()){
			try{
				Component auxHeader = i.next();
				if(auxHeader instanceof Auxhead){
					i.remove();
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
				i.next();
			}
		}
		
		Auxhead auxheadMaterias = new Auxhead();
		auxheadMaterias.setParent(lbNotas);
		Auxhead auxheadTipoEvaluacion = new Auxhead();
		auxheadTipoEvaluacion.setParent(lbNotas);
		
		Listhead listheadEvaluaciones = new Listhead();
		listheadEvaluaciones.setParent(lbNotas);
				
		createHeaderDatosAlumno(auxheadMaterias);
		createHeaderNombreApellido(listheadEvaluaciones);
		
		for (VwMateria vwMateria : materias) {
			createHeaderMateria(auxheadMaterias, vwMateria);
			createHeaderTipoEvaluaciones(auxheadTipoEvaluacion, vwMateria);
			
			for (VwTrabajoPractico vwTrabajoPractico : vwMateria.getTrabajosPracticos())
				createHeaderTrabajoPractico(listheadEvaluaciones, vwTrabajoPractico);
			
			for (VwExamen vwExamen : vwMateria.getExamenes())
				createHeaderExamen(listheadEvaluaciones, vwExamen);
		}
		
	}

	private void createHeaderExamen(Listhead listheadEvaluaciones, VwExamen vwExamen) {
		Listheader listheaderExamen = new Listheader(vwExamen.getTituloCorto());
		listheaderExamen.setTooltiptext(vwExamen.getTitulo());
		listheaderExamen.setAttribute("evaluacion", vwExamen);
		listheaderExamen.setWidth("50px");
		listheaderExamen.setAlign("center");
		listheaderExamen.setParent(listheadEvaluaciones);
	}

	private void createHeaderTrabajoPractico(Listhead listheadEvaluaciones, VwTrabajoPractico vwTrabajoPractico) {
		Listheader listheaderTrabajoPractico = new Listheader(vwTrabajoPractico.getTituloCorto());
		listheaderTrabajoPractico.setTooltiptext(vwTrabajoPractico.getTitulo());
		listheaderTrabajoPractico.setAttribute("evaluacion", vwTrabajoPractico);
		listheaderTrabajoPractico.setWidth("50px");
		listheaderTrabajoPractico.setAlign("center");
		listheaderTrabajoPractico.setParent(listheadEvaluaciones);
	}

	private void createHeaderTipoEvaluaciones(Auxhead auxheadTipoEvaluacion, VwMateria vwMateria) {
		Auxheader auxheaderTrabajosPracticos = new Auxheader("Trabajos Prácticos");
		int trabajosPractivosColSpan = vwMateria.getTrabajosPracticos().size();
		auxheaderTrabajosPracticos.setColspan(trabajosPractivosColSpan > 0? trabajosPractivosColSpan : 1);
		auxheaderTrabajosPracticos.setAlign("center");
		auxheaderTrabajosPracticos.setParent(auxheadTipoEvaluacion);
		Auxheader auxheaderExamenes = new Auxheader("Exámenes");
		int examenesColSpan = vwMateria.getExamenes().size();
		auxheaderExamenes.setColspan(examenesColSpan > 0? examenesColSpan : 1);
		auxheaderExamenes.setAlign("center");
		auxheaderExamenes.setParent(auxheadTipoEvaluacion);
	}

	private void createHeaderMateria(Auxhead auxheadMaterias, VwMateria vwMateria) {
		Auxheader auxheaderMateria = new Auxheader(vwMateria.getMateria());
		auxheaderMateria.setAttribute("materia", vwMateria);
		int materiasColSpan = vwMateria.getTrabajosPracticos().size() + vwMateria.getExamenes().size();
		auxheaderMateria.setColspan(materiasColSpan > 0 ? materiasColSpan : 1);
		auxheaderMateria.setAlign("center");
		auxheaderMateria.setStyle("font-size:16px;");
		auxheaderMateria.setImage("/img/chefs-hat-icon.png");
		auxheaderMateria.setParent(auxheadMaterias);
	}

	private void createHeaderNombreApellido(Listhead listheadEvaluaciones) {
		Listheader listheaderApellido = new Listheader("Apellido");
		listheaderApellido.setParent(listheadEvaluaciones);
		listheaderApellido.setWidth("115px");
		Listheader listheaderNombre = new Listheader("Nombre");
		listheaderNombre.setWidth("115px");
		listheaderNombre.setParent(listheadEvaluaciones);
	}

	private void createHeaderDatosAlumno(Auxhead auxheadMaterias) {
		Auxheader auxheaderDatosPersonales = new Auxheader("Datos del Alumno");
		auxheaderDatosPersonales.setColspan(2);
		auxheaderDatosPersonales.setRowspan(2);
		auxheaderDatosPersonales.setAlign("center");
		auxheaderDatosPersonales.setStyle("font-size:18px;");
		auxheaderDatosPersonales.setImage("/img/chef.png"); 
		auxheaderDatosPersonales.setWidth("230px");
		auxheaderDatosPersonales.setParent(auxheadMaterias);
	}

}
