package ar.com.celia.seguimiento_alumnos.service;
import ar.com.celia.seguimiento_alumnos.domain.VwIndicadoresAlumnos;

/**
 * @created 30-Oct-2006 10:42:50 AM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public interface VwIndicadoresAlumnosDefinition {

	/**
	 * Permite eliminar un objeto del dominio
	 * 
	 * @param p_domain    Objeto de dominio que se desea eliminar
	 */
	public abstract void delete(VwIndicadoresAlumnos p_domain) throws Exception;

	/**
	 * Permite obtener un objeto de dominio por su clave primaria.
	 * 
	 * @param p_Id    Identificador �nico del objeto que se desea obtener
	 */
	public abstract VwIndicadoresAlumnos get(java.io.Serializable p_Id) throws Exception;

	public abstract VwIndicadoresAlumnos get(java.io.Serializable p_Id, String[] falseLazy) throws Exception;
	
	/**
	 * Este m�todo retornar� una lista de objetos de dominio que coincidieron con las
	 * condiciones del objeto de ejemplo enviado por par�metro.
	 * 
	 * @param p_example    Objeto de dominio que se utilizar� como ejemplo en el
	 * filtro al momento de realizar el query.
	 */
	public abstract java.util.List<VwIndicadoresAlumnos> getAll(VwIndicadoresAlumnos p_example, String[] falseLazy) throws Exception;

	/**
	 * Insertar un registro de dominio.
	 * 
	 * @param p_domain    p_domain
	 */
	public abstract void insert(VwIndicadoresAlumnos p_domain) throws Exception;

	/**
	 * Actualizar un registro de dominio.
	 * 
	 * @param p_domain    p_domain
	 */
	public abstract void update(VwIndicadoresAlumnos p_domain) throws Exception;

}