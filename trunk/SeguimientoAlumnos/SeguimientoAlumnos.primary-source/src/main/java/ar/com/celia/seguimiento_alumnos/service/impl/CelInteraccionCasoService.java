package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelInteraccionCaso;
import ar.com.celia.seguimiento_alumnos.service.CelInteraccionCasoDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class CelInteraccionCasoService implements CelInteraccionCasoDefinition {

	protected CelInteraccionCasoDefinition biz;
	
	public CelInteraccionCasoService() {

	}
	
	public void setBusinessObject (CelInteraccionCasoDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(CelInteraccionCaso p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public CelInteraccionCaso get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public CelInteraccionCaso get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<CelInteraccionCaso> getAll(CelInteraccionCaso p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(CelInteraccionCaso p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(CelInteraccionCaso p_domain) throws Exception {
		biz.update(p_domain);
	}
	
	public Long insertInteraccionCaso(CelInteraccionCaso transientInstance) throws Exception{
		return biz.insertInteraccionCaso(transientInstance);
	}
	
	public int getCantidadContactos(Long aluId) throws Exception {
		return biz.getCantidadContactos(aluId) ;
	}
}