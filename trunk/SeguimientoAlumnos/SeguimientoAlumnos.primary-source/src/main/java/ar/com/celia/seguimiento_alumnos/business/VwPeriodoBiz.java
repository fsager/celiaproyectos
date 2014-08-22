package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwPeriodo;
import ar.com.celia.seguimiento_alumnos.persistence.VwPeriodoDao;
import ar.com.celia.seguimiento_alumnos.service.VwPeriodoDefinition;

public class VwPeriodoBiz implements VwPeriodoDefinition {
	VwPeriodoDao dao;
	
	public void setDao (VwPeriodoDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(VwPeriodo p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public VwPeriodo get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public VwPeriodo get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<VwPeriodo> getAll(VwPeriodo p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(VwPeriodo p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(VwPeriodo p_domain) throws Exception {
		dao.update(p_domain);
	}

}