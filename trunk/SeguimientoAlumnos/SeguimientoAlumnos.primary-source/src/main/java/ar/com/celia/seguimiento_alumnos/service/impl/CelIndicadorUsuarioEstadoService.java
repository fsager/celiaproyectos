package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelIndicadorUsuarioEstado;
import ar.com.celia.seguimiento_alumnos.service.CelIndicadorUsuarioEstadoDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class CelIndicadorUsuarioEstadoService implements CelIndicadorUsuarioEstadoDefinition {

	protected CelIndicadorUsuarioEstadoDefinition biz;
	
	public CelIndicadorUsuarioEstadoService() {

	}
	
	public void setBusinessObject (CelIndicadorUsuarioEstadoDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(CelIndicadorUsuarioEstado p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public CelIndicadorUsuarioEstado get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public CelIndicadorUsuarioEstado get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<CelIndicadorUsuarioEstado> getAll(CelIndicadorUsuarioEstado p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(CelIndicadorUsuarioEstado p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(CelIndicadorUsuarioEstado p_domain) throws Exception {
		biz.update(p_domain);
	}
}