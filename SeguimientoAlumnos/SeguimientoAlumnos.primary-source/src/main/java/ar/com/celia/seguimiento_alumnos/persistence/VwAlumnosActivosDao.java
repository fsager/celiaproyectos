package ar.com.celia.seguimiento_alumnos.persistence;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;

import ar.com.celia.seguimiento_alumnos.domain.VwAlumnosActivos;
import ar.com.celia.seguimiento_alumnos.domain.VwMaterias;

/**
 * @created 30-Oct-2006 10:42:50 AM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public interface VwAlumnosActivosDao {

	/**
	 * Permite eliminar un objeto del dominio
	 * 
	 * @param p_domain    Objeto de dominio que se desea eliminar
	 */
	public abstract void delete(VwAlumnosActivos p_domain) throws Exception;

	/**
	 * Permite obtener un objeto de dominio por su clave primaria.
	 * 
	 * @param p_Id    Identificador �nico del objeto que se desea obtener
	 */
	public abstract VwAlumnosActivos get(java.io.Serializable p_Id) throws Exception;
	
	public abstract VwAlumnosActivos get(java.io.Serializable p_Id, String[] falseLazy) throws Exception;

	/**
	 * Este m�todo retornar� una lista de objetos de dominio que coincidieron con las
	 * condiciones del objeto de ejemplo enviado por par�metro.
	 * 
	 * @param p_example    Objeto de dominio que se utilizar� como ejemplo en el
	 * filtro al momento de realizar el query.
	 */
	public abstract java.util.List<VwAlumnosActivos> getAll(VwAlumnosActivos p_example, String[] falseLazy) throws Exception;

	/**
	 * Insertar un registro de dominio.
	 * 
	 * @param p_domain    p_domain
	 */
	public abstract void insert(VwAlumnosActivos p_domain) throws Exception;

	/**
	 * Actualizar un registro de dominio.
	 * 
	 * @param p_domain    p_domain
	 */
	public abstract void update(VwAlumnosActivos p_domain) throws Exception;
	
	public List<VwAlumnosActivos> p_alumnos_activos_con_indicadores(String list_indicadores, String matricula, String apellido, String nombre) throws Exception;
	
	 public List<VwMaterias> getMateriasByAlumno(VwMaterias p_example) throws Exception;

}
