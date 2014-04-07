package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwAlumnosActivos;
import ar.com.celia.seguimiento_alumnos.service.VwAlumnosActivosDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class VwAlumnosActivosService implements VwAlumnosActivosDefinition {

	protected VwAlumnosActivosDefinition biz;
	
	public VwAlumnosActivosService() {

	}
	
	public void setBusinessObject (VwAlumnosActivosDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(VwAlumnosActivos p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public VwAlumnosActivos get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public VwAlumnosActivos get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<VwAlumnosActivos> getAll(VwAlumnosActivos p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(VwAlumnosActivos p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(VwAlumnosActivos p_domain) throws Exception {
		biz.update(p_domain);
	}
}