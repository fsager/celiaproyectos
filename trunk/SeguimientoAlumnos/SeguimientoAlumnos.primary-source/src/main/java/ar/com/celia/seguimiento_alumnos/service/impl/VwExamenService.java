package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwExamen;
import ar.com.celia.seguimiento_alumnos.service.VwExamenDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class VwExamenService implements VwExamenDefinition {

	protected VwExamenDefinition biz;
	
	public VwExamenService() {

	}
	
	public void setBusinessObject (VwExamenDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(VwExamen p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public VwExamen get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public VwExamen get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<VwExamen> getAll(VwExamen p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(VwExamen p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(VwExamen p_domain) throws Exception {
		biz.update(p_domain);
	}
}