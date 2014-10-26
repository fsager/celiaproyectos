package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelBitacoraAlumnos;
import ar.com.celia.seguimiento_alumnos.persistence.CelBitacoraAlumnosDao;
import ar.com.celia.seguimiento_alumnos.service.CelBitacoraAlumnosDefinition;

public class CelBitacoraAlumnosBiz implements CelBitacoraAlumnosDefinition {
	CelBitacoraAlumnosDao dao;
	
	public void setDao (CelBitacoraAlumnosDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(CelBitacoraAlumnos p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public CelBitacoraAlumnos get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public CelBitacoraAlumnos get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<CelBitacoraAlumnos> getAll(CelBitacoraAlumnos p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(CelBitacoraAlumnos p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(CelBitacoraAlumnos p_domain) throws Exception {
		dao.update(p_domain);
	}

    public List<CelBitacoraAlumnos> getBitacoraPorUsrId(Long usrId) throws Exception {
    	return dao.getBitacoraPorUsrId(usrId);
    }
}