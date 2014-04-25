package ar.com.celia.seguimiento_alumnos.business;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelDominio;
import ar.com.celia.seguimiento_alumnos.persistence.CelDominioDao;
import ar.com.celia.seguimiento_alumnos.service.CelDominioDefinition;

public class CelDominioBiz implements CelDominioDefinition {
	CelDominioDao dao;
	
	public void setDao (CelDominioDao p_dao) {
		dao = p_dao;
	}
	
	public void delete(CelDominio p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public CelDominio get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public CelDominio get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return dao.get(p_Id,falseLazy);
	}	
	

	public List<CelDominio> getAll(CelDominio p_example, String[] falseLazy) throws Exception {
		return dao.getAll(p_example,falseLazy);
	}

	public void insert(CelDominio p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(CelDominio p_domain) throws Exception {
		dao.update(p_domain);
	}
	
	public List<CelDominio> getDominio(String p_example, String[] falseLazy) throws Exception{
		return dao.getDominio(p_example, falseLazy);
	}

}