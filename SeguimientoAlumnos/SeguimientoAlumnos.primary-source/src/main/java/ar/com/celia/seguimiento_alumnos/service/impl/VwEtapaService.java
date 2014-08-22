package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwEtapa;
import ar.com.celia.seguimiento_alumnos.service.VwEtapaDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class VwEtapaService implements VwEtapaDefinition {

	protected VwEtapaDefinition biz;
	
	public VwEtapaService() {

	}
	
	public void setBusinessObject (VwEtapaDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(VwEtapa p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public VwEtapa get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public VwEtapa get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<VwEtapa> getAll(VwEtapa p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(VwEtapa p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(VwEtapa p_domain) throws Exception {
		biz.update(p_domain);
	}
}