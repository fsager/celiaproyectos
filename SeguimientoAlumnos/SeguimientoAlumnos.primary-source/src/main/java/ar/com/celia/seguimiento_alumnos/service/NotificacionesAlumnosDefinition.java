package ar.com.celia.seguimiento_alumnos.service;
import java.util.List;

import ar.com.celia.seguimiento_alumnos.domain.VwAlertasAlumnoLibrePorTP;
import ar.com.celia.seguimiento_alumnos.domain.VwAlertasExamenes;
import ar.com.celia.seguimiento_alumnos.domain.VwAlertasTps;
import ar.com.celia.seguimiento_alumnos.domain.VwDocentesNoIngresanAMoodle;

/**
 * @created 30-Oct-2006 10:42:50 AM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public interface NotificacionesAlumnosDefinition {

    public List<VwAlertasTps> getTrabajosPracticosNuevos() throws Exception;
    
    public List<VwAlertasTps> getTrabajosPracticosPorVencer() throws Exception;
    
    public List<VwAlertasTps> getTrabajosPracticosVencidos() throws Exception;
    
    public List<VwAlertasAlumnoLibrePorTP> getTrabajosPracticosPorQuedarLibre() throws Exception;
    
    public List<VwAlertasExamenes> getExamenesNuevos() throws Exception;
    
    public List<VwAlertasExamenes> getExamenesPorVencer() throws Exception;
    
    public List<VwAlertasExamenes> getExamenesVencidos() throws Exception;
    
    public List<VwAlertasExamenes> getLibres() throws Exception;

}