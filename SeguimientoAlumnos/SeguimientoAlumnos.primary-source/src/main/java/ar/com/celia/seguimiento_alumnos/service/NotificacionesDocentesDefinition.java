package ar.com.celia.seguimiento_alumnos.service;
import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwAlertasExamenes;
import ar.com.celia.seguimiento_alumnos.domain.VwAlertasTps;
import ar.com.celia.seguimiento_alumnos.domain.VwDocentesNoIngresanAMoodle;

/**
 * @created 30-Oct-2006 10:42:50 AM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public interface NotificacionesDocentesDefinition {

	public List<VwDocentesNoIngresanAMoodle> getDocentesNoIngresanAMoodle() throws Exception;
    public List<VwAlertasExamenes> getNotasPendientes() throws Exception;
    
    public List<VwAlertasTps> getTpNotasPendientes() throws Exception;
    
    public List<VwAlertasTps> getTpPendientesDeSubir() throws Exception;
    
 public List<VwAlertasExamenes> getExamenPendientesDeSubir() throws Exception;

}