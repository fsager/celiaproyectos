/**
 * 
 */
package ar.com.celia.seguimiento_alumnos.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author gdelasilva
 * Nota de un alumno en una evaluación.
 */
@Entity
@Table(name = "vw_listado_notas_examen_alumno", schema = "seguimiento_alumnos"	)
@IdClass(VwListadoNotasExamenAlumnoPk.class)
public class VwListadoNotasExamenAlumno extends VwListadoNotasAlumno implements Serializable {

	private static final long serialVersionUID = 6830633785760982839L;
		
	private Long perCatId;
	private Long etpCatId;
	private Long matId;
	private Long userId;
	private Long evalId;
	private String tipoEvaluacion;
	
	private String lastname;
	private String firstname;
	private Double nota;
	
	@Id
	@Column(name = "evalid", nullable = false)
	public Long getEvalId() {
		return evalId;
	}
	public void setEvalId(Long evalId) {
		this.evalId = evalId;
	}
	
	@Id
	@Column(name = "per_cat_id", nullable = false)
	public Long getPerCatId() {
		return perCatId;
	}
	public void setPerCatId(Long perCatId) {
		this.perCatId = perCatId;
	}
	
	@Id
	@Column(name = "etp_cat_id", nullable = false)
	public Long getEtpCatId() {
		return etpCatId;
	}
	public void setEtpCatId(Long etpCatId) {
		this.etpCatId = etpCatId;
	}
	
	@Id
	@Column(name = "mat_id", nullable = false)
	public Long getMatId() {
		return matId;
	}
	public void setMatId(Long matId) {
		this.matId = matId;
	}
	
	@Id
	@Column(name = "userid", nullable = false)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Id
	@Column(name = "tipo_evaluacion")
	public String getTipoEvaluacion() {
		return tipoEvaluacion;
	}
	public void setTipoEvaluacion(String tipoEvaluacion) {
		this.tipoEvaluacion = tipoEvaluacion;
	}
	
	@Column(name = "lastname")
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	@Column(name = "firstname")
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	@Column(name = "nota", precision=24, scale=5)
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	
	@Transient
	public Double getNotaDivididoDiez(){
		return this.nota < 0D? 0D:this.nota/10D;
	}
	
	@Transient
	public Boolean isSinNota(){
		return this.nota == -1D;
	}
	
	@Transient
	public Boolean isAusente(){
		return this.nota==null;
	}
	
}

class VwListadoNotasExamenAlumnoPk implements Serializable{

	private static final long serialVersionUID = -5743176869051961905L;
	
	private Long perCatId;
	private Long etpCatId;
	private Long matId;
	private Long userId;
	private Long evalId;
	private String tipoEvaluacion;
	
	@Id
	@Column(name = "evalid", nullable = false)
	public Long getEvalId() {
		return evalId;
	}
	public void setEvalId(Long evalId) {
		this.evalId = evalId;
	}
	
	@Id
	@Column(name = "per_cat_id", nullable = false)
	public Long getPerCatId() {
		return perCatId;
	}
	public void setPerCatId(Long perCatId) {
		this.perCatId = perCatId;
	}
	
	@Id
	@Column(name = "etp_cat_id", nullable = false)
	public Long getEtpCatId() {
		return etpCatId;
	}
	public void setEtpCatId(Long etpCatId) {
		this.etpCatId = etpCatId;
	}
	
	@Id
	@Column(name = "mat_id", nullable = false)
	public Long getMatId() {
		return matId;
	}
	public void setMatId(Long matId) {
		this.matId = matId;
	}
	
	@Id
	@Column(name = "userid", nullable = false)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Id
	@Column(name = "tipo_evaluacion", nullable = false)
	public String getTipoEvaluacion() {
		return tipoEvaluacion;
	}
	public void setTipoEvaluacion(String tipoEvaluacion) {
		this.tipoEvaluacion = tipoEvaluacion;
	}
}