package ar.com.celia.seguimiento_alumnos.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.GroupsModel;
import org.zkoss.zul.GroupsModelArray;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.SimpleGroupsModel;

import ar.com.celia.core.business.ContextManagerCore;
import ar.com.celia.seguimiento_alumnos.domain.CelInteraccionCaso;
import ar.com.celia.seguimiento_alumnos.domain.VwAlumnosActivos;
import ar.com.celia.seguimiento_alumnos.domain.VwIndicadoresAlumnos;
import ar.com.celia.seguimiento_alumnos.service.CelInteraccionCasoDefinition;
import ar.com.celia.seguimiento_alumnos.service.CelInteraccionCasoDetalleDefinition;

public class TabHistoricoController extends GenericForwardComposer {
	
	private VwAlumnosActivos alumno=null;
	private Listbox lsbHistorial;
	private CelInteraccionCasoDefinition celInteraccionCasoService=(CelInteraccionCasoDefinition)ContextManagerCore.getBizObject("celInteraccionCasoService");
	private CelInteraccionCasoDetalleDefinition celInteraccionCasoDetalleService=(CelInteraccionCasoDetalleDefinition)ContextManagerCore.getBizObject("celInteraccionCasoDetalleService");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute("controller", this, false);
	}
	
	public void onCreate$wdsTabHistorico(Event evt) throws Exception {
	
		alumno=(VwAlumnosActivos)arg.get("alumno");
		if(alumno!=null)
		{
			java.util.Set <VwIndicadoresAlumnos> indicadoresSet=alumno.getIndicadoresAlumnos();
			generarGrilla(indicadoresSet);
		}
	}

	public void generarGrilla(java.util.Set <VwIndicadoresAlumnos> indicadoresSet) throws Exception
	{
		String[][] datas = new String[][] {
	            new String[] { //group 1
	                // Today
	                "RE: Bandbox Autocomplete Problem",
	                "RE: It's not possible to navigate a listbox' ite",
	                "RE: FileUpload"
	            },
	            new String[] { //group 2
	                // Yesterday
	                "RE: Opening more than one new browser window",
	                "RE: SelectedItemConverter Question"
	            },
	            new String[] { //group 3
	                "RE: Times_Series Chart help",
	                "RE: SelectedItemConverter Question"
	            }            
	        };
	        GroupsModel model = new SimpleGroupsModel(datas,
	            new String[]{"Date: Today", "Date: Yesterday", "Date: Last Week"});
	            //the 2nd argument is a list of group head
//	        CelInteraccionCaso celInteraccionCaso= new CelInteraccionCaso();
//	        celInteraccionCaso.setAluId(alumno.getId());
//	        celInteraccionCasoService.getInteraccionesPorAlumno(celInteraccionCaso, new String[]{"CelInteraccionCasoDetalle"});
	        
//	        GroupsModel model2= new GroupsModelArray<CelInteraccionCaso, celInteraccionCasoGroupModel.FoodGroupInfo, Object, Object>(data, new CelInteraccionCasoComparator());
	        
	        lsbHistorial.setModel(model);
	}
}
