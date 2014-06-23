package ar.com.celia.seguimiento_alumnos.service;
import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.CelIndicador;

/**
 * @created 30-Oct-2006 10:42:50 AM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public interface CelIndicadorDefinition {

	/**
	 * Permite eliminar un objeto del dominio
	 * 
	 * @param p_domain    Objeto de dominio que se desea eliminar
	 */
	public abstract void delete(CelIndicador p_domain) throws Exception;

	/**
	 * Permite obtener un objeto de dominio por su clave primaria.
	 * 
	 * @param p_Id    Identificador �nico del objeto que se desea obtener
	 */
	public abstract CelIndicador get(java.io.Serializable p_Id) throws Exception;

	public abstract CelIndicador get(java.io.Serializable p_Id, String[] falseLazy) throws Exception;
	
	/**
	 * Este m�todo retornar� una lista de objetos de dominio que coincidieron con las
	 * condiciones del objeto de ejemplo enviado por par�metro.
	 * 
	 * @param p_example    Objeto de dominio que se utilizar� como ejemplo en el
	 * filtro al momento de realizar el query.
	 */
	public abstract java.util.List<CelIndicador> getAll(CelIndicador p_example, String[] falseLazy) throws Exception;

	/**
	 * Insertar un registro de dominio.
	 * 
	 * @param p_domain    p_domain
	 */
	public abstract void insert(CelIndicador p_domain) throws Exception;

	/**
	 * Actualizar un registro de dominio.
	 * 
	 * @param p_domain    p_domain
	 */
	public abstract void update(CelIndicador p_domain) throws Exception;
	
	public List callMoreDetail(Long aluId,CelIndicador indicador) throws Exception;

}