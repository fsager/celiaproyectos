package ar.com.celia.seguimiento_alumnos.persistence;
import ar.com.celia.seguimiento_alumnos.domain.VwListadoNotasExamenAlumno;

/**
 * @created 30-Oct-2006 10:42:50 AM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public interface VwListadoNotasExamenAlumnoDao {

	/**
	 * Permite eliminar un objeto del dominio
	 * 
	 * @param p_domain    Objeto de dominio que se desea eliminar
	 */
	public abstract void delete(VwListadoNotasExamenAlumno p_domain) throws Exception;

	/**
	 * Permite obtener un objeto de dominio por su clave primaria.
	 * 
	 * @param p_Id    Identificador �nico del objeto que se desea obtener
	 */
	public abstract VwListadoNotasExamenAlumno get(java.io.Serializable p_Id) throws Exception;
	
	public abstract VwListadoNotasExamenAlumno get(java.io.Serializable p_Id, String[] falseLazy) throws Exception;

	/**
	 * Este m�todo retornar� una lista de objetos de dominio que coincidieron con las
	 * condiciones del objeto de ejemplo enviado por par�metro.
	 * 
	 * @param p_example    Objeto de dominio que se utilizar� como ejemplo en el
	 * filtro al momento de realizar el query.
	 */
	public abstract java.util.List<VwListadoNotasExamenAlumno> getAll(VwListadoNotasExamenAlumno p_example, String[] falseLazy) throws Exception;

	/**
	 * Insertar un registro de dominio.
	 * 
	 * @param p_domain    p_domain
	 */
	public abstract void insert(VwListadoNotasExamenAlumno p_domain) throws Exception;

	/**
	 * Actualizar un registro de dominio.
	 * 
	 * @param p_domain    p_domain
	 */
	public abstract void update(VwListadoNotasExamenAlumno p_domain) throws Exception;

}