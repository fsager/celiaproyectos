package ar.com.celia.seguimiento_alumnos.viewmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Group;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

import ar.com.celia.core.business.ContextManagerCore;
import ar.com.celia.seguimiento_alumnos.domain.CelDominio;
import ar.com.celia.seguimiento_alumnos.domain.CelInteraccionCaso;
import ar.com.celia.seguimiento_alumnos.domain.CelInteraccionCasoDetalle;
import ar.com.celia.seguimiento_alumnos.domain.VwAlumnosActivos;
import ar.com.celia.seguimiento_alumnos.service.CelDominioDefinition;
import ar.com.celia.seguimiento_alumnos.service.CelInteraccionCasoDefinition;
import ar.com.celia.seguimiento_alumnos.service.CelInteraccionCasoDetalleDefinition;

public class HistoricoCasosAlumnoVM {

	private CelInteraccionCasoDetalleDefinition celInteraccionCasoDetalleService = (CelInteraccionCasoDetalleDefinition) ContextManagerCore.getBizObject("celInteraccionCasoDetalleService");
	
	private CelInteraccionCasoDefinition celInteraccionCasoService = (CelInteraccionCasoDefinition) ContextManagerCore.getBizObject("celInteraccionCasoService");
	
	private LinkedHashMap<Long, CelInteraccionCaso> hashMapCasos = new LinkedHashMap<Long, CelInteraccionCaso>();
	private LinkedHashMap<Long, List<CelInteraccionCasoDetalle>> hashMapInteraccionCasos = new LinkedHashMap<Long, List<CelInteraccionCasoDetalle>>();
	
	@Wire
	private Grid gridHistoricoCasos;
	
	private CelDominioDefinition celDominioService = (CelDominioDefinition) ContextManagerCore.getBizObject("celDominioService");
	
	private HashMap<String, CelDominio> hashRespuestasDominio = new HashMap<String, CelDominio>();
	
	
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

		List<CelDominio> lstRespuestasDominio = celDominioService.getDominio("INDICADOR_CASO_RESPUESTA");

		for (CelDominio domRespuestasDominio : lstRespuestasDominio) {
			hashRespuestasDominio.put(domRespuestasDominio.getDomClave(), domRespuestasDominio);	
		}
		cargarLbHistorico(alumnoParam.getId());
	}

	

	private void cargarLbHistorico(Long aluId) throws Exception {
		List<CelInteraccionCaso> lstInteraccionesAlumno = celInteraccionCasoService.getInteraccionesPorAlumno(aluId, null);

		List<CelInteraccionCasoDetalle> lstInteraccionCasoDetalle = celInteraccionCasoDetalleService.getDetalleInteraccionesPorAlumno(aluId);
		
		cargarHashMapDetalles(lstInteraccionesAlumno, lstInteraccionCasoDetalle);
		Rows rows = new Rows();
		rows.setParent(gridHistoricoCasos);
		
		Iterator<Entry<Long, List<CelInteraccionCasoDetalle>>> it = hashMapInteraccionCasos.entrySet().iterator();
			
		while (it.hasNext()) {
			Entry<Long, List<CelInteraccionCasoDetalle>> entry = it.next();
			Long casId = entry.getKey();
			CelInteraccionCaso celInteraccionCaso = hashMapCasos.get(casId);
			
			String title = celInteraccionCaso.getAudFechaIns().toString().concat(" - ").concat(celInteraccionCaso.getCasObservacionesGrales());
			Group group = new Group(title);
			group.setParent(rows);
			
			List<CelInteraccionCasoDetalle> lstDetalles = entry.getValue();
			for (CelInteraccionCasoDetalle casoDetalle : lstDetalles) {
				Row row = new Row();
				Label lblIndicador = new Label(casoDetalle.getCelIndicador().getIndNombre());
				lblIndicador.setParent(row);
				CelDominio domRespuesta = hashRespuestasDominio.get(casoDetalle.getIcdRtaTipo()); 
				Label lblRespuesta = new Label(domRespuesta.getDomTexto());
				lblRespuesta.setParent(row);
				
				Label lblObservaciones = new Label(casoDetalle.getIcdObservaciones());
				lblObservaciones.setParent(row);
				
				row.setParent(rows);
			}
		}
	}
	
	
	private void cargarHashMapDetalles(List<CelInteraccionCaso> lstInteraccionesAlumno, List<CelInteraccionCasoDetalle> lstInteraccionCasoDetalle) {
		hashMapInteraccionCasos = new LinkedHashMap<Long, List<CelInteraccionCasoDetalle>>();
		hashMapCasos = new LinkedHashMap<Long, CelInteraccionCaso>();
		
		//Creamos la lista vacía de detalles para cada caso en el HashMap, luego se la completará.
		for (CelInteraccionCaso celInteraccionCaso : lstInteraccionesAlumno) {
			Long key = celInteraccionCaso.getCasId();
			List<CelInteraccionCasoDetalle> lstDetalles = new ArrayList<CelInteraccionCasoDetalle>();
			hashMapInteraccionCasos.put(key, lstDetalles);
			hashMapCasos.put(key, celInteraccionCaso);
		}
		
		for (CelInteraccionCasoDetalle casoDetalle : lstInteraccionCasoDetalle) {
			Long key = casoDetalle.getCelInteraccionCaso().getCasId();
			List<CelInteraccionCasoDetalle> lstDetalles = hashMapInteraccionCasos.get(key);
			lstDetalles.add(casoDetalle);
		}
	}
}