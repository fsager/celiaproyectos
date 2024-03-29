package ar.com.celia.seguimiento_alumnos.persistence;
import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelDominio;

/**
 * @created 30-Oct-2006 10:42:50 AM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public interface CelDominioDao {

	/**
	 * Permite eliminar un objeto del dominio
	 * 
	 * @param p_domain    Objeto de dominio que se desea eliminar
	 */
	public abstract void delete(CelDominio p_domain) throws Exception;

	/**
	 * Permite obtener un objeto de dominio por su clave primaria.
	 * 
	 * @param p_Id    Identificador �nico del objeto que se desea obtener
	 */
	public abstract CelDominio get(java.io.Serializable p_Id) throws Exception;
	
	public abstract CelDominio get(java.io.Serializable p_Id, String[] falseLazy) throws Exception;

	/**
	 * Este m�todo retornar� una lista de objetos de dominio que coincidieron con las
	 * condiciones del objeto de ejemplo enviado por par�metro.
	 * 
	 * @param p_example    Objeto de dominio que se utilizar� como ejemplo en el
	 * filtro al momento de realizar el query.
	 */
	public abstract java.util.List<CelDominio> getAll(CelDominio p_example, String[] falseLazy) throws Exception;

	/**
	 * Insertar un registro de dominio.
	 * 
	 * @param p_domain    p_domain
	 */
	public abstract void insert(CelDominio p_domain) throws Exception;

	/**
	 * Actualizar un registro de dominio.
	 * 
	 * @param p_domain    p_domain
	 */
	public abstract void update(CelDominio p_domain) throws Exception;
	
	/**
	 * Buscar dominios en funci�n de valor seteado en el campo DOM_DOMINIO.
	 * 
	 * @param p_domain    p_domain
	 */
	public abstract List<CelDominio> getDominio(String p_example) throws Exception;

}
