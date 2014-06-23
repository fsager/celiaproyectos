package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelIndicador;
import ar.com.celia.seguimiento_alumnos.persistence.CelIndicadorDao;
import ar.com.celia.seguimiento_alumnos.service.CelIndicadorDefinition;

public class CelIndicadorBiz implements CelIndicadorDefinition {
	CelIndicadorDao dao;
	
	public void setDao (CelIndicadorDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(CelIndicador p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public CelIndicador get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public CelIndicador get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<CelIndicador> getAll(CelIndicador p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(CelIndicador p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(CelIndicador p_domain) throws Exception {
		dao.update(p_domain);
	}
	
	public List callMoreDetail(Long aluId,CelIndicador indicador) throws Exception
	{
		return dao.callMoreDetail(aluId,indicador);
	}

}