package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelUsuario;
import ar.com.celia.seguimiento_alumnos.persistence.CelUsuarioDao;
import ar.com.celia.seguimiento_alumnos.service.CelUsuarioDefinition;

public class CelUsuarioBiz implements CelUsuarioDefinition {
	CelUsuarioDao dao;
	
	public void setDao (CelUsuarioDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(CelUsuario p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public CelUsuario get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public CelUsuario get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<CelUsuario> getAll(CelUsuario p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(CelUsuario p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(CelUsuario p_domain) throws Exception {
		dao.update(p_domain);
	}

}