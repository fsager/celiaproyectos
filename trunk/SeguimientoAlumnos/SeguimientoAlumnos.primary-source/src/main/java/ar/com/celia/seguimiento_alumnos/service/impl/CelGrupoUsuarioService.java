package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelGrupoUsuario;
import ar.com.celia.seguimiento_alumnos.service.CelGrupoUsuarioDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class CelGrupoUsuarioService implements CelGrupoUsuarioDefinition {

	protected CelGrupoUsuarioDefinition biz;
	
	public CelGrupoUsuarioService() {

	}
	
	public void setBusinessObject (CelGrupoUsuarioDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(CelGrupoUsuario p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public CelGrupoUsuario get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public CelGrupoUsuario get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<CelGrupoUsuario> getAll(CelGrupoUsuario p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(CelGrupoUsuario p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(CelGrupoUsuario p_domain) throws Exception {
		biz.update(p_domain);
	}
}