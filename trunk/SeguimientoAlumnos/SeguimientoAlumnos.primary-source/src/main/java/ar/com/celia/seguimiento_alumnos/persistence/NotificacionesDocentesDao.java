package ar.com.celia.seguimiento_alumnos.persistence;
import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwDocentesNoIngresanAMoodle;

/**
 * @created 30-Oct-2006 10:42:50 AM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public interface NotificacionesDocentesDao {

	 public List<VwDocentesNoIngresanAMoodle> getDocentesNoIngresanAMoodle() throws Exception;

}
