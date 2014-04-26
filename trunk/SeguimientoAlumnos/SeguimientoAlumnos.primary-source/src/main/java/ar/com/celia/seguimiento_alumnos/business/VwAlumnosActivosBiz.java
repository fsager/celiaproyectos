package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwAlumnosActivos;
import ar.com.celia.seguimiento_alumnos.persistence.VwAlumnosActivosDao;
import ar.com.celia.seguimiento_alumnos.service.VwAlumnosActivosDefinition;

public class VwAlumnosActivosBiz implements VwAlumnosActivosDefinition {
	VwAlumnosActivosDao dao;
	
	public void setDao (VwAlumnosActivosDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(VwAlumnosActivos p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public VwAlumnosActivos get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public VwAlumnosActivos get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<VwAlumnosActivos> getAll(VwAlumnosActivos p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(VwAlumnosActivos p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(VwAlumnosActivos p_domain) throws Exception {
		dao.update(p_domain);
	}

	public List<VwAlumnosActivos> p_alumnos_activos_con_indicadores(String list_indicadores, String matricula, String apellido, String nombre) throws Exception {
		return dao.p_alumnos_activos_con_indicadores(list_indicadores, matricula, apellido, nombre);
    }
}