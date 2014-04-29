package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelInteraccionCasoDetalle;
import ar.com.celia.seguimiento_alumnos.service.CelInteraccionCasoDetalleDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class CelInteraccionCasoDetalleService implements CelInteraccionCasoDetalleDefinition {

	protected CelInteraccionCasoDetalleDefinition biz;
	
	public CelInteraccionCasoDetalleService() {

	}
	
	public void setBusinessObject (CelInteraccionCasoDetalleDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(CelInteraccionCasoDetalle p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public CelInteraccionCasoDetalle get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public CelInteraccionCasoDetalle get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<CelInteraccionCasoDetalle> getAll(CelInteraccionCasoDetalle p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(CelInteraccionCasoDetalle p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(CelInteraccionCasoDetalle p_domain) throws Exception {
		biz.update(p_domain);
	}
	
    public List<CelInteraccionCasoDetalle> getDetalleInteraccionesPorAlumno(Long alu_id) throws Exception {
    	return biz.getDetalleInteraccionesPorAlumno(alu_id);
    }

}