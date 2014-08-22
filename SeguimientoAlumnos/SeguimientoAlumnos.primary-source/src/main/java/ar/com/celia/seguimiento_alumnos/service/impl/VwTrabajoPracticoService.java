package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwTrabajoPractico;
import ar.com.celia.seguimiento_alumnos.service.VwTrabajoPracticoDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class VwTrabajoPracticoService implements VwTrabajoPracticoDefinition {

	protected VwTrabajoPracticoDefinition biz;
	
	public VwTrabajoPracticoService() {

	}
	
	public void setBusinessObject (VwTrabajoPracticoDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(VwTrabajoPractico p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public VwTrabajoPractico get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public VwTrabajoPractico get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<VwTrabajoPractico> getAll(VwTrabajoPractico p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(VwTrabajoPractico p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(VwTrabajoPractico p_domain) throws Exception {
		biz.update(p_domain);
	}
}