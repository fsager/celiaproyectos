package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelDominio;
import ar.com.celia.seguimiento_alumnos.service.CelDominioDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class CelDominioService implements CelDominioDefinition {

	protected CelDominioDefinition biz;
	
	public CelDominioService() {

	}
	
	public void setBusinessObject (CelDominioDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(CelDominio p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public CelDominio get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public CelDominio get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<CelDominio> getAll(CelDominio p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(CelDominio p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(CelDominio p_domain) throws Exception {
		biz.update(p_domain);
	}
	
	public List<CelDominio> getDominio(String p_example) throws Exception{
		return biz.getDominio(p_example);
	}
}