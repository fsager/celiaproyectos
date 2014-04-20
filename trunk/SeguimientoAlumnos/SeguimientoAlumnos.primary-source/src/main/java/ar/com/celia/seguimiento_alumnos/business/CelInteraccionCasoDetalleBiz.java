package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelInteraccionCasoDetalle;
import ar.com.celia.seguimiento_alumnos.persistence.CelInteraccionCasoDetalleDao;
import ar.com.celia.seguimiento_alumnos.service.CelInteraccionCasoDetalleDefinition;

public class CelInteraccionCasoDetalleBiz implements CelInteraccionCasoDetalleDefinition {
	CelInteraccionCasoDetalleDao dao;
	
	public void setDao (CelInteraccionCasoDetalleDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(CelInteraccionCasoDetalle p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public CelInteraccionCasoDetalle get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public CelInteraccionCasoDetalle get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<CelInteraccionCasoDetalle> getAll(CelInteraccionCasoDetalle p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(CelInteraccionCasoDetalle p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(CelInteraccionCasoDetalle p_domain) throws Exception {
		dao.update(p_domain);
	}

}