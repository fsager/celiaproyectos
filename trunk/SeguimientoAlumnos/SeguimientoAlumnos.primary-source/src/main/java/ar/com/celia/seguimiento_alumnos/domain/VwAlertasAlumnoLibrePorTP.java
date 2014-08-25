/**
 * 
 */
package ar.com.celia.seguimiento_alumnos.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author gdelasilva
 * Nota de un alumno en una evaluación.
 */
@Entity
@Table(name = "vw_alerta_alumno_libre_por_tp", schema = "seguimiento_alumnos"	)
@IdClass(VwAlertasAlumnoLibrePorTPPk.class)
public class VwAlertasAlumnoLibrePorTP implements Serializable {

	private static final long serialVersionUID = 6830633785760982839L;
		
	private Long perCatId;
	private Long matId;
	private VwMateria vwMateria;
	private Long userId;
	private String email;
	private Integer cantTp;
	private Integer cantAusente;
	private Integer cantReprobado;
	
	@Id
	@Column(name = "per_cat_id", nullable = false)
	public Long getPerCatId() {
		return perCatId;
	}
	public void setPerCatId(Long perCatId) {
		this.perCatId = perCatId;
	}
	
	@Id
	@Column(name = "mat_id", nullable = false)
	public Long getMatId() {
		return matId;
	}
	public void setMatId(Long matId) {
		this.matId = matId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mat_id" , insertable=false, updatable=false)
	public VwMateria getVwMateria() {
		return vwMateria;
	}
	
	public void setVwMateria(VwMateria vwMateria) {
		this.vwMateria = vwMateria;
	}
	
	@Id
	@Column(name = "userid", nullable = false)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "cant_tp")
	public Integer getCantTp() {
		return cantTp;
	}
	public void setCantTp(Integer cantTp) {
		this.cantTp = cantTp;
	}
	
	@Column(name = "cant_ausente")
	public Integer getCantAusente() {
		return cantAusente;
	}
	public void setCantAusente(Integer cantAusente) {
		this.cantAusente = cantAusente;
	}
	
	@Column(name = "cant_reprobado")
	public Integer getCantReprobado() {
		return cantReprobado;
	}
	public void setCantReprobado(Integer cantReprobado) {
		this.cantReprobado = cantReprobado;
	}
	
	@Transient
	public String getNombreMateria(){
		return vwMateria.getMateria();
	}
}

class VwAlertasAlumnoLibrePorTPPk implements Serializable{

	private static final long serialVersionUID = -5743176869051961905L;
	
	private Long perCatId;
	private Long matId;
	private Long userId;
		
	@Id
	@Column(name = "per_cat_id", nullable = false)
	public Long getPerCatId() {
		return perCatId;
	}
	public void setPerCatId(Long perCatId) {
		this.perCatId = perCatId;
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
}