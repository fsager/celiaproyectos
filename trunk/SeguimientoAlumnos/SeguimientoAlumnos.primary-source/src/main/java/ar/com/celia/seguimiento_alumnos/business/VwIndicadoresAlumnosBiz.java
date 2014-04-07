package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwIndicadoresAlumnos;
import ar.com.celia.seguimiento_alumnos.persistence.VwIndicadoresAlumnosDao;
import ar.com.celia.seguimiento_alumnos.service.VwIndicadoresAlumnosDefinition;

public class VwIndicadoresAlumnosBiz implements VwIndicadoresAlumnosDefinition {
	VwIndicadoresAlumnosDao dao;
	
	public void setDao (VwIndicadoresAlumnosDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(VwIndicadoresAlumnos p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public VwIndicadoresAlumnos get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public VwIndicadoresAlumnos get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<VwIndicadoresAlumnos> getAll(VwIndicadoresAlumnos p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(VwIndicadoresAlumnos p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(VwIndicadoresAlumnos p_domain) throws Exception {
		dao.update(p_domain);
	}

}