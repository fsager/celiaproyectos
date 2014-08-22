package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwExamen;
import ar.com.celia.seguimiento_alumnos.persistence.VwExamenDao;
import ar.com.celia.seguimiento_alumnos.service.VwExamenDefinition;

public class VwExamenBiz implements VwExamenDefinition {
	VwExamenDao dao;
	
	public void setDao (VwExamenDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(VwExamen p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public VwExamen get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public VwExamen get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<VwExamen> getAll(VwExamen p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(VwExamen p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(VwExamen p_domain) throws Exception {
		dao.update(p_domain);
	}

}