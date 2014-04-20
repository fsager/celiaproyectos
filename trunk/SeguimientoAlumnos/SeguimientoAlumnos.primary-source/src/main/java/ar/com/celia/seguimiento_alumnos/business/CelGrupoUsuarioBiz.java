package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelGrupoUsuario;
import ar.com.celia.seguimiento_alumnos.persistence.CelGrupoUsuarioDao;
import ar.com.celia.seguimiento_alumnos.service.CelGrupoUsuarioDefinition;

public class CelGrupoUsuarioBiz implements CelGrupoUsuarioDefinition {
	CelGrupoUsuarioDao dao;
	
	public void setDao (CelGrupoUsuarioDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(CelGrupoUsuario p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public CelGrupoUsuario get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public CelGrupoUsuario get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<CelGrupoUsuario> getAll(CelGrupoUsuario p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(CelGrupoUsuario p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(CelGrupoUsuario p_domain) throws Exception {
		dao.update(p_domain);
	}

}