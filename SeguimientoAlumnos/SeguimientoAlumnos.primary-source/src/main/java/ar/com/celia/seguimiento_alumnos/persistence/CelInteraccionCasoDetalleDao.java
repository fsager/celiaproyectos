package ar.com.celia.seguimiento_alumnos.persistence;
import ar.com.celia.seguimiento_alumnos.domain.CelInteraccionCasoDetalle;

/**
 * @created 30-Oct-2006 10:42:50 AM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public interface CelInteraccionCasoDetalleDao {

	/**
	 * Permite eliminar un objeto del dominio
	 * 
	 * @param p_domain    Objeto de dominio que se desea eliminar
	 */
	public abstract void delete(CelInteraccionCasoDetalle p_domain) throws Exception;

	/**
	 * Permite obtener un objeto de dominio por su clave primaria.
	 * 
	 * @param p_Id    Identificador único del objeto que se desea obtener
	 */
	public abstract CelInteraccionCasoDetalle get(java.io.Serializable p_Id) throws Exception;
	
	public abstract CelInteraccionCasoDetalle get(java.io.Serializable p_Id, String[] falseLazy) throws Exception;

	/**
	 * Este método retornará una lista de objetos de dominio que coincidieron con las
	 * condiciones del objeto de ejemplo enviado por parámetro.
	 * 
	 * @param p_example    Objeto de dominio que se utilizará como ejemplo en el
	 * filtro al momento de realizar el query.
	 */
	public abstract java.util.List<CelInteraccionCasoDetalle> getAll(CelInteraccionCasoDetalle p_example, String[] falseLazy) throws Exception;

	/**
	 * Insertar un registro de dominio.
	 * 
	 * @param p_domain    p_domain
	 */
	public abstract void insert(CelInteraccionCasoDetalle p_domain) throws Exception;

	/**
	 * Actualizar un registro de dominio.
	 * 
	 * @param p_domain    p_domain
	 */
	public abstract void update(CelInteraccionCasoDetalle p_domain) throws Exception;

}
