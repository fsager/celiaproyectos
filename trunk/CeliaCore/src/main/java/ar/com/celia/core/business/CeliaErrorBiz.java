package ar.com.celia.core.business;

import java.util.List;

import ar.com.celia.core.domain.CeliaError;
import ar.com.celia.core.persistence.CeliaErrorDao;
import ar.com.celia.core.service.CeliaErrorDefinition;

public class CeliaErrorBiz implements CeliaErrorDefinition {
	CeliaErrorDao dao;
	
	public void setDao (CeliaErrorDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(CeliaError p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public CeliaError get(java.io.Serializable p_Id) throws Exception {
		return dao.get(p_Id);
	}

	public List getAll(CeliaError p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(CeliaError p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(CeliaError p_domain) throws Exception {
		dao.update(p_domain);
	}


	public List getAllErrorSinEnviar(CeliaError p_example) throws Exception{
		return dao.getAllErrorSinEnviar(p_example);
	}


	public void deleteAllEnviados(CeliaError p_example) throws Exception{
		dao.deleteAllEnviados(p_example);
	}

	public CeliaError getByErrHashId(String errHashId) throws Exception{
		return dao.getByErrHashId(errHashId);
	}
}