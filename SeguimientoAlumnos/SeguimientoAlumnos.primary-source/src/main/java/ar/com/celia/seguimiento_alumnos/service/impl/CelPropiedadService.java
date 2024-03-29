package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelPropiedad;
import ar.com.celia.seguimiento_alumnos.service.CelPropiedadDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class CelPropiedadService implements CelPropiedadDefinition {

	protected CelPropiedadDefinition biz;
	
	public CelPropiedadService() {

	}
	
	public void setBusinessObject (CelPropiedadDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(CelPropiedad p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public CelPropiedad get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public CelPropiedad get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<CelPropiedad> getAll(CelPropiedad p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(CelPropiedad p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(CelPropiedad p_domain) throws Exception {
		biz.update(p_domain);
	}
}