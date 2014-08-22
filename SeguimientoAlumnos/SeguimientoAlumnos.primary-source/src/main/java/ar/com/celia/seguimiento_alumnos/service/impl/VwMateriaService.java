package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwMateria;
import ar.com.celia.seguimiento_alumnos.service.VwMateriaDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class VwMateriaService implements VwMateriaDefinition {

	protected VwMateriaDefinition biz;
	
	public VwMateriaService() {

	}
	
	public void setBusinessObject (VwMateriaDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(VwMateria p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public VwMateria get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public VwMateria get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<VwMateria> getAll(VwMateria p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(VwMateria p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(VwMateria p_domain) throws Exception {
		biz.update(p_domain);
	}
}