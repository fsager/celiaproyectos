package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwEtapa;
import ar.com.celia.seguimiento_alumnos.persistence.VwEtapaDao;
import ar.com.celia.seguimiento_alumnos.service.VwEtapaDefinition;

public class VwEtapaBiz implements VwEtapaDefinition {
	VwEtapaDao dao;
	
	public void setDao (VwEtapaDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(VwEtapa p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public VwEtapa get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public VwEtapa get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<VwEtapa> getAll(VwEtapa p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(VwEtapa p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(VwEtapa p_domain) throws Exception {
		dao.update(p_domain);
	}

}