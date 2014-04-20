package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelGrupo;
import ar.com.celia.seguimiento_alumnos.persistence.CelGrupoDao;
import ar.com.celia.seguimiento_alumnos.service.CelGrupoDefinition;

public class CelGrupoBiz implements CelGrupoDefinition {
	CelGrupoDao dao;
	
	public void setDao (CelGrupoDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(CelGrupo p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public CelGrupo get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public CelGrupo get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<CelGrupo> getAll(CelGrupo p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(CelGrupo p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(CelGrupo p_domain) throws Exception {
		dao.update(p_domain);
	}

}