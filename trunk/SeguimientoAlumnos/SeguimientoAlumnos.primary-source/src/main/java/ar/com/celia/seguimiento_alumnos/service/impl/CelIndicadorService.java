package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelIndicador;
import ar.com.celia.seguimiento_alumnos.service.CelIndicadorDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class CelIndicadorService implements CelIndicadorDefinition {

	protected CelIndicadorDefinition biz;
	
	public CelIndicadorService() {

	}
	
	public void setBusinessObject (CelIndicadorDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(CelIndicador p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public CelIndicador get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public CelIndicador get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<CelIndicador> getAll(CelIndicador p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(CelIndicador p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(CelIndicador p_domain) throws Exception {
		biz.update(p_domain);
	}
}