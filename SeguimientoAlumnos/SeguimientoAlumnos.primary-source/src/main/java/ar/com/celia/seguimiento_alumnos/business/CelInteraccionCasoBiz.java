package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelInteraccionCaso;
import ar.com.celia.seguimiento_alumnos.persistence.CelInteraccionCasoDao;
import ar.com.celia.seguimiento_alumnos.service.CelInteraccionCasoDefinition;

public class CelInteraccionCasoBiz implements CelInteraccionCasoDefinition {
	CelInteraccionCasoDao dao;
	
	public void setDao (CelInteraccionCasoDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(CelInteraccionCaso p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public CelInteraccionCaso get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public CelInteraccionCaso get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<CelInteraccionCaso> getAll(CelInteraccionCaso p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(CelInteraccionCaso p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(CelInteraccionCaso p_domain) throws Exception {
		dao.update(p_domain);
	}
	
	public Long insertInteraccionCaso(CelInteraccionCaso transientInstance) throws Exception{
		return dao.insertInteraccionCaso(transientInstance);
	}
	
	public int getCantidadContactos(Long aluId) throws Exception{
		return dao.getCantidadContactos(aluId);
	}

}