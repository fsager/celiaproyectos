package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwMateria;
import ar.com.celia.seguimiento_alumnos.persistence.VwMateriaDao;
import ar.com.celia.seguimiento_alumnos.service.VwMateriaDefinition;

public class VwMateriaBiz implements VwMateriaDefinition {
	VwMateriaDao dao;
	
	public void setDao (VwMateriaDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(VwMateria p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public VwMateria get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public VwMateria get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<VwMateria> getAll(VwMateria p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(VwMateria p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(VwMateria p_domain) throws Exception {
		dao.update(p_domain);
	}

}