package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelIndicadorUsuarioEstado;
import ar.com.celia.seguimiento_alumnos.persistence.CelIndicadorUsuarioEstadoDao;
import ar.com.celia.seguimiento_alumnos.service.CelIndicadorUsuarioEstadoDefinition;

public class CelIndicadorUsuarioEstadoBiz implements CelIndicadorUsuarioEstadoDefinition {
	CelIndicadorUsuarioEstadoDao dao;
	
	public void setDao (CelIndicadorUsuarioEstadoDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(CelIndicadorUsuarioEstado p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public CelIndicadorUsuarioEstado get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public CelIndicadorUsuarioEstado get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<CelIndicadorUsuarioEstado> getAll(CelIndicadorUsuarioEstado p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(CelIndicadorUsuarioEstado p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(CelIndicadorUsuarioEstado p_domain) throws Exception {
		dao.update(p_domain);
	}

}