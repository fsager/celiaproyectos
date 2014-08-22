package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwNotasAlumno;
import ar.com.celia.seguimiento_alumnos.service.VwNotasAlumnoDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class VwNotasAlumnoService implements VwNotasAlumnoDefinition {

	protected VwNotasAlumnoDefinition biz;
	
	public VwNotasAlumnoService() {

	}
	
	public void setBusinessObject (VwNotasAlumnoDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(VwNotasAlumno p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public VwNotasAlumno get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public VwNotasAlumno get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<VwNotasAlumno> getAll(VwNotasAlumno p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(VwNotasAlumno p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(VwNotasAlumno p_domain) throws Exception {
		biz.update(p_domain);
	}
}