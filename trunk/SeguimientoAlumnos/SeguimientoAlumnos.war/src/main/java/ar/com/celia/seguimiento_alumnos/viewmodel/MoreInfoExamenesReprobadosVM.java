package ar.com.celia.seguimiento_alumnos.viewmodel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listgroup;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import ar.com.celia.core.business.ContextManagerCore;
import ar.com.celia.seguimiento_alumnos.domain.CelIndicador;
import ar.com.celia.seguimiento_alumnos.domain.VwAlumnosActivos;
import ar.com.celia.seguimiento_alumnos.domain.VwExameneesReprobados;
import ar.com.celia.seguimiento_alumnos.domain.VwTpsVencidos;
import ar.com.celia.seguimiento_alumnos.service.CelIndicadorDefinition;

public class MoreInfoExamenesReprobadosVM {
	
	private CelIndicadorDefinition celIndicadorService = (CelIndicadorDefinition) ContextManagerCore.getBizObject("celIndicadorService");

	private DecimalFormat df = new DecimalFormat("#.##");
	
	@Wire
	private Window wndMoreInfoExamenesReprobados;
	
	@Wire
	private Listbox lbExamenesReprobados;
	
	
	@Init
	public void init() {
		try {

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("alumno") VwAlumnosActivos alumno, @ExecutionArgParam("celIndicador") CelIndicador celIndicador) throws Exception {
		Selectors.wireComponents(view, this, false);

		wndMoreInfoExamenesReprobados.setTitle(alumno.getLastname()+", "+alumno.getFirstname());

		List<VwExameneesReprobados> lstExamenesReprobados = celIndicadorService.callMoreDetail(alumno.getId(),celIndicador);
		
		/*
		 * Cargamos los Exámenes Reprobados en un HashMap agrupados por materia, para mostrarlos agrupados en el listbox.
		 */
		HashMap<String, List<VwExameneesReprobados>> mapExamenesReprobados = new HashMap<String, List<VwExameneesReprobados>>();
		for (VwExameneesReprobados vwExamenesReprobados : lstExamenesReprobados) {
			if (mapExamenesReprobados.get(vwExamenesReprobados.getCourseFullname()) == null) {
				mapExamenesReprobados.put(vwExamenesReprobados.getCourseFullname(), new ArrayList<VwExameneesReprobados>());
			}
			List<VwExameneesReprobados> lstExamenesReprobadosDeMateria = mapExamenesReprobados.get(vwExamenesReprobados.getCourseFullname());
			lstExamenesReprobadosDeMateria.add(vwExamenesReprobados);
		}
		
		Iterator<String> itMapTPsVencidos = mapExamenesReprobados.keySet().iterator();
		while (itMapTPsVencidos.hasNext()) {
			String nombreMateria = itMapTPsVencidos.next();

			List<VwExameneesReprobados> lstExamenes = mapExamenesReprobados.get(nombreMateria);
			
			Listgroup lgroupMateria = new Listgroup(nombreMateria+" ("+lstExamenes.size()+")");
			lgroupMateria.setOpen(false);
			lgroupMateria.setParent(lbExamenesReprobados);
			
			for (VwExameneesReprobados vwExamenesReprobados : lstExamenes) {
				Listitem liTpMateria = new Listitem();
				
				Listcell lcNombreMateria = new Listcell(vwExamenesReprobados.getQuizName());
				lcNombreMateria.setParent(liTpMateria);
				
				String notaExamen = " - ";
				if (vwExamenesReprobados.getQuizGrade() != null) {
					notaExamen = df.format(vwExamenesReprobados.getQuizGrade());
				}
				Listcell lcNotaExamen = new Listcell(notaExamen);
				lcNotaExamen.setParent(liTpMateria);
				
				liTpMateria.setParent(lbExamenesReprobados);
			}
		}
	}

}