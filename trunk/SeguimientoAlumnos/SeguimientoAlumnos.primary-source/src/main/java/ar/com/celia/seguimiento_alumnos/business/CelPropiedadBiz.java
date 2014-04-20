package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelPropiedad;
import ar.com.celia.seguimiento_alumnos.persistence.CelPropiedadDao;
import ar.com.celia.seguimiento_alumnos.service.CelPropiedadDefinition;

public class CelPropiedadBiz implements CelPropiedadDefinition {
	CelPropiedadDao dao;
	
	public void setDao (CelPropiedadDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(CelPropiedad p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public CelPropiedad get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public CelPropiedad get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<CelPropiedad> getAll(CelPropiedad p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(CelPropiedad p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(CelPropiedad p_domain) throws Exception {
		dao.update(p_domain);
	}

}