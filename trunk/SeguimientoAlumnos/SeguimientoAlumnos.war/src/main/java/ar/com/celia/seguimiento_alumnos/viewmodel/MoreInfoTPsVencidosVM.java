package ar.com.celia.seguimiento_alumnos.viewmodel;

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
import org.zkoss.zul.Listgroup;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import ar.com.celia.core.business.ContextManagerCore;
import ar.com.celia.seguimiento_alumnos.domain.CelIndicador;
import ar.com.celia.seguimiento_alumnos.domain.VwAlumnosActivos;
import ar.com.celia.seguimiento_alumnos.domain.VwTpsVencidos;
import ar.com.celia.seguimiento_alumnos.service.CelIndicadorDefinition;

public class MoreInfoTPsVencidosVM {
	
	private CelIndicadorDefinition celIndicadorService = (CelIndicadorDefinition) ContextManagerCore.getBizObject("celIndicadorService");

	@Wire
	private Window wndMoreInfoTpsVencidos;
	
	@Wire
	private Listbox lbTPsVencidos;
	
	
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

		wndMoreInfoTpsVencidos.setTitle(alumno.getLastname()+", "+alumno.getFirstname());
		
		List<VwTpsVencidos> lstTPsVencidos = celIndicadorService.callMoreDetail(alumno.getId(),celIndicador);
		
		/*
		 * Cargamos los Tps vencidos en un HashMap agrupados por materia, para mostrarlos agrupados en el listbox.
		 */
		HashMap<String, List<VwTpsVencidos>> mapTPsVencidos = new HashMap<String, List<VwTpsVencidos>>();
		for (VwTpsVencidos vwTpsVencidos : lstTPsVencidos) {
			if (mapTPsVencidos.get(vwTpsVencidos.getCourseFullname()) == null) {
				mapTPsVencidos.put(vwTpsVencidos.getCourseFullname(), new ArrayList<VwTpsVencidos>());
			}
			List<VwTpsVencidos> lstTpsDeMateria = mapTPsVencidos.get(vwTpsVencidos.getCourseFullname());
			lstTpsDeMateria.add(vwTpsVencidos);
		}
		
		Iterator<String> itMapTPsVencidos = mapTPsVencidos.keySet().iterator();
		while (itMapTPsVencidos.hasNext()) {
			String nombreMateria = itMapTPsVencidos.next();

			List<VwTpsVencidos> lstTps = mapTPsVencidos.get(nombreMateria);
			
			Listgroup lgroupMateria = new Listgroup(nombreMateria+" ("+lstTps.size()+")");
			lgroupMateria.setOpen(false);
			lgroupMateria.setParent(lbTPsVencidos);
			
			for (VwTpsVencidos vwTPsVencidos : lstTps) {
				Listitem liTpMateria = new Listitem(vwTPsVencidos.getAssignmentName());
				liTpMateria.setParent(lbTPsVencidos);
			}
		}
	}

}