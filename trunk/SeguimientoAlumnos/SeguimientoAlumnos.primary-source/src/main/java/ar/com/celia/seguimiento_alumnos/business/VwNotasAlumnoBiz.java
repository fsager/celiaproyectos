package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwNotasAlumno;
import ar.com.celia.seguimiento_alumnos.persistence.VwNotasAlumnoDao;
import ar.com.celia.seguimiento_alumnos.service.VwNotasAlumnoDefinition;

public class VwNotasAlumnoBiz implements VwNotasAlumnoDefinition {
	VwNotasAlumnoDao dao;
	
	public void setDao (VwNotasAlumnoDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(VwNotasAlumno p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public VwNotasAlumno get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public VwNotasAlumno get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<VwNotasAlumno> getAll(VwNotasAlumno p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(VwNotasAlumno p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(VwNotasAlumno p_domain) throws Exception {
		dao.update(p_domain);
	}

}