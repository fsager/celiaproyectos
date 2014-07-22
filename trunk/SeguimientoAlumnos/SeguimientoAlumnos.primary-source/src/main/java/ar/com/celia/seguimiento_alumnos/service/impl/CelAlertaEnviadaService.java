package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelAlertaEnviada;
import ar.com.celia.seguimiento_alumnos.service.CelAlertaEnviadaDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class CelAlertaEnviadaService implements CelAlertaEnviadaDefinition {

	protected CelAlertaEnviadaDefinition biz;
	
	public CelAlertaEnviadaService() {

	}
	
	public void setBusinessObject (CelAlertaEnviadaDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(CelAlertaEnviada p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public CelAlertaEnviada get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public CelAlertaEnviada get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<CelAlertaEnviada> getAll(CelAlertaEnviada p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(CelAlertaEnviada p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(CelAlertaEnviada p_domain) throws Exception {
		biz.update(p_domain);
	}
}