package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwListadoNotasExamenAlumno;
import ar.com.celia.seguimiento_alumnos.persistence.VwListadoNotasExamenAlumnoDao;
import ar.com.celia.seguimiento_alumnos.service.VwListadoNotasExamenAlumnoDefinition;

public class VwListadoNotasExamenAlumnoBiz implements VwListadoNotasExamenAlumnoDefinition {
	VwListadoNotasExamenAlumnoDao dao;
	
	public void setDao (VwListadoNotasExamenAlumnoDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(VwListadoNotasExamenAlumno p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public VwListadoNotasExamenAlumno get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public VwListadoNotasExamenAlumno get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<VwListadoNotasExamenAlumno> getAll(VwListadoNotasExamenAlumno p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(VwListadoNotasExamenAlumno p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(VwListadoNotasExamenAlumno p_domain) throws Exception {
		dao.update(p_domain);
	}

}