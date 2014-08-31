package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwListadoNotasExamenAlumno;
import ar.com.celia.seguimiento_alumnos.service.VwListadoNotasExamenAlumnoDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class VwListadoNotasExamenAlumnoService implements VwListadoNotasExamenAlumnoDefinition {

	protected VwListadoNotasExamenAlumnoDefinition biz;
	
	public VwListadoNotasExamenAlumnoService() {

	}
	
	public void setBusinessObject (VwListadoNotasExamenAlumnoDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(VwListadoNotasExamenAlumno p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public VwListadoNotasExamenAlumno get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public VwListadoNotasExamenAlumno get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<VwListadoNotasExamenAlumno> getAll(VwListadoNotasExamenAlumno p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(VwListadoNotasExamenAlumno p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(VwListadoNotasExamenAlumno p_domain) throws Exception {
		biz.update(p_domain);
	}
}