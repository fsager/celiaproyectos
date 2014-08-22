package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwPeriodo;
import ar.com.celia.seguimiento_alumnos.service.VwPeriodoDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class VwPeriodoService implements VwPeriodoDefinition {

	protected VwPeriodoDefinition biz;
	
	public VwPeriodoService() {

	}
	
	public void setBusinessObject (VwPeriodoDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(VwPeriodo p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public VwPeriodo get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public VwPeriodo get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<VwPeriodo> getAll(VwPeriodo p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(VwPeriodo p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(VwPeriodo p_domain) throws Exception {
		biz.update(p_domain);
	}
}