package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelGrupo;
import ar.com.celia.seguimiento_alumnos.service.CelGrupoDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class CelGrupoService implements CelGrupoDefinition {

	protected CelGrupoDefinition biz;
	
	public CelGrupoService() {

	}
	
	public void setBusinessObject (CelGrupoDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(CelGrupo p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public CelGrupo get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public CelGrupo get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<CelGrupo> getAll(CelGrupo p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(CelGrupo p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(CelGrupo p_domain) throws Exception {
		biz.update(p_domain);
	}
}