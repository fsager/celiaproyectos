package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelAlertaEnviada;
import ar.com.celia.seguimiento_alumnos.persistence.CelAlertaEnviadaDao;
import ar.com.celia.seguimiento_alumnos.service.CelAlertaEnviadaDefinition;

public class CelAlertaEnviadaBiz implements CelAlertaEnviadaDefinition {
	CelAlertaEnviadaDao dao;
	
	public void setDao (CelAlertaEnviadaDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(CelAlertaEnviada p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public CelAlertaEnviada get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public CelAlertaEnviada get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<CelAlertaEnviada> getAll(CelAlertaEnviada p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(CelAlertaEnviada p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(CelAlertaEnviada p_domain) throws Exception {
		dao.update(p_domain);
	}

}