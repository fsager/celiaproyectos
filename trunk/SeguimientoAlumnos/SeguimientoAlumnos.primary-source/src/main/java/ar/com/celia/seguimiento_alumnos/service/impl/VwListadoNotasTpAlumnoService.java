package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwListadoNotasTpAlumno;
import ar.com.celia.seguimiento_alumnos.service.VwListadoNotasTpAlumnoDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class VwListadoNotasTpAlumnoService implements VwListadoNotasTpAlumnoDefinition {

	protected VwListadoNotasTpAlumnoDefinition biz;
	
	public VwListadoNotasTpAlumnoService() {

	}
	
	public void setBusinessObject (VwListadoNotasTpAlumnoDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(VwListadoNotasTpAlumno p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public VwListadoNotasTpAlumno get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public VwListadoNotasTpAlumno get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<VwListadoNotasTpAlumno> getAll(VwListadoNotasTpAlumno p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(VwListadoNotasTpAlumno p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(VwListadoNotasTpAlumno p_domain) throws Exception {
		biz.update(p_domain);
	}
}