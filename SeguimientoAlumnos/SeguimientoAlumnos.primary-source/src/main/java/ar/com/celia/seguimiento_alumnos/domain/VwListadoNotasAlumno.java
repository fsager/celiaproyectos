/**
 * 
 */
package ar.com.celia.seguimiento_alumnos.domain;

import java.io.Serializable;

/**
 * @author gdelasilva
 * Nota de un alumno en una evaluación (examen o trabajo práctico).
 */
public abstract class VwListadoNotasAlumno implements Serializable {

	private static final long serialVersionUID = 6830633785760982839L;
		
	public abstract Long getEvalId();
	
	public abstract void setEvalId(Long evalId);

	public abstract Long getPerCatId();

	public abstract void setPerCatId(Long perCatId);
	
	public abstract Long getEtpCatId();

	public abstract void setEtpCatId(Long etpCatId);
	
	public abstract Long getMatId();

	public abstract void setMatId(Long matId);
	
	public abstract Long getUserId();
	
	public abstract void setUserId(Long userId);
	
	public abstract String getTipoEvaluacion();

	public abstract void setTipoEvaluacion(String tipoEvaluacion);
	
	public abstract String getLastname();

	public abstract void setLastname(String lastname);
	
	public abstract String getFirstname();

	public abstract void setFirstname(String firstname);

	public abstract Double getNota();

	public abstract void setNota(Double nota);
	
	public abstract Double getNotaDivididoDiez();
	
	public abstract Boolean isSinNota();
	
	public abstract Boolean isAusente();
	
}