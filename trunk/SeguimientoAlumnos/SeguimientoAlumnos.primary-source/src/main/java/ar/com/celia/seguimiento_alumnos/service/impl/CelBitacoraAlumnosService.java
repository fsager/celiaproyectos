package ar.com.celia.seguimiento_alumnos.service.impl;

import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelBitacoraAlumnos;
import ar.com.celia.seguimiento_alumnos.service.CelBitacoraAlumnosDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class CelBitacoraAlumnosService implements CelBitacoraAlumnosDefinition {

	protected CelBitacoraAlumnosDefinition biz;
	
	public CelBitacoraAlumnosService() {

	}
	
	public void setBusinessObject (CelBitacoraAlumnosDefinition p_biz)	{
		biz = p_biz;
	}

	public void delete(CelBitacoraAlumnos p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public CelBitacoraAlumnos get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public CelBitacoraAlumnos get(java.io.Serializable p_Id, String[] falseLazy) throws Exception {
		return biz.get(p_Id,falseLazy);
	}	

	public List<CelBitacoraAlumnos> getAll(CelBitacoraAlumnos p_example, String[] falseLazy) throws Exception {
		return biz.getAll(p_example,falseLazy);
	}
	
	public void insert(CelBitacoraAlumnos p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(CelBitacoraAlumnos p_domain) throws Exception {
		biz.update(p_domain);
	}
	
    public List<CelBitacoraAlumnos> getBitacoraPorUsrId(Long usrId) throws Exception {
    	return biz.getBitacoraPorUsrId(usrId);
    }
}