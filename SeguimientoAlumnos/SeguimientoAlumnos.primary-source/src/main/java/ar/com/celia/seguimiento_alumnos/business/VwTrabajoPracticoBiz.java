package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwTrabajoPractico;
import ar.com.celia.seguimiento_alumnos.persistence.VwTrabajoPracticoDao;
import ar.com.celia.seguimiento_alumnos.service.VwTrabajoPracticoDefinition;

public class VwTrabajoPracticoBiz implements VwTrabajoPracticoDefinition {
	VwTrabajoPracticoDao dao;
	
	public void setDao (VwTrabajoPracticoDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(VwTrabajoPractico p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public VwTrabajoPractico get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public VwTrabajoPractico get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<VwTrabajoPractico> getAll(VwTrabajoPractico p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(VwTrabajoPractico p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(VwTrabajoPractico p_domain) throws Exception {
		dao.update(p_domain);
	}

}