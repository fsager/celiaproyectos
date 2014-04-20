package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelUsuario;
import ar.com.celia.seguimiento_alumnos.service.CelUsuarioDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class CelUsuarioService implements CelUsuarioDefinition {

	protected CelUsuarioDefinition biz;
	
	public CelUsuarioService() {

	}
	
	public void setBusinessObject (CelUsuarioDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(CelUsuario p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public CelUsuario get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public CelUsuario get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<CelUsuario> getAll(CelUsuario p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(CelUsuario p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(CelUsuario p_domain) throws Exception {
		biz.update(p_domain);
	}
}