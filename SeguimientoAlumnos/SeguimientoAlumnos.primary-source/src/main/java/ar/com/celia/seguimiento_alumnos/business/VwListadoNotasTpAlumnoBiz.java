package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwListadoNotasTpAlumno;
import ar.com.celia.seguimiento_alumnos.persistence.VwListadoNotasTpAlumnoDao;
import ar.com.celia.seguimiento_alumnos.service.VwListadoNotasTpAlumnoDefinition;

public class VwListadoNotasTpAlumnoBiz implements VwListadoNotasTpAlumnoDefinition {
	VwListadoNotasTpAlumnoDao dao;
	
	public void setDao (VwListadoNotasTpAlumnoDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(VwListadoNotasTpAlumno p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public VwListadoNotasTpAlumno get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public VwListadoNotasTpAlumno get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<VwListadoNotasTpAlumno> getAll(VwListadoNotasTpAlumno p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(VwListadoNotasTpAlumno p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(VwListadoNotasTpAlumno p_domain) throws Exception {
		dao.update(p_domain);
	}

}