package ar.com.celia.core.service.impl;

import java.util.List;

import ar.com.celia.core.domain.CeliaError;
import ar.com.celia.core.service.CeliaErrorDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class CeliaErrorService implements CeliaErrorDefinition {

	protected CeliaErrorDefinition biz;
	
	public CeliaErrorService(){

	}
	
	public void setBusinessObject (CeliaErrorDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(CeliaError p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public CeliaError get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(CeliaError p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(CeliaError p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(CeliaError p_domain) throws Exception {
		biz.update(p_domain);
	}

	public List getAllErrorSinEnviar(CeliaError p_example) throws Exception{
		return biz.getAllErrorSinEnviar(p_example);
	}


	public void deleteAllEnviados(CeliaError p_example) throws Exception{
		biz.deleteAllEnviados(p_example);
	}

	public CeliaError getByErrHashId(String errHashId) throws Exception{
		return biz.getByErrHashId(errHashId);
	}
}