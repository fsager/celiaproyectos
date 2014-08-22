/**
 * 
 */
package ar.com.celia.seguimiento_alumnos.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * @author gdelasilva
 * La clase representa una materia(curso en moodle).
 */
@Entity
@Table(name = "vw_materias_activas", schema = "seguimiento_alumnos")
public class VwMateria implements Serializable {

	private static final long serialVersionUID = -6790639075901438941L;
	
	private Long matId;
	private String materia;
	private String matNombreCorto;
	private Integer matOrden;
	private Boolean matVisible;
	private VwEtapa vwEtapa;
	private Set<VwTrabajoPractico> trabajosPracticos = new HashSet<VwTrabajoPractico>();
	private Set<VwExamen> examenes= new HashSet<VwExamen>();
	
	@Id
	@Column(name = "mat_id", nullable = false)
	public Long getMatId() {
		return matId;
	}
	public void setMatId(Long matId) {
		this.matId = matId;
	}
	
	@Column(name = "mat_nombre_completo")
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	
	@Column(name = "mat_nombre_corto")
	public String getMatNombreCorto() {
		return matNombreCorto;
	}
	public void setMatNombreCorto(String matNombreCorto) {
		this.matNombreCorto = matNombreCorto;
	}
	
	@Column(name = "orden")
	public Integer getMatOrden() {
		return matOrden;
	}
	public void setMatOrden(Integer matOrden) {
		this.matOrden = matOrden;
	}
	
	@Column(name = "visible")
	public Boolean getMatVisible() {
		return matVisible;
	}
	public void setMatVisible(Boolean matVisible) {
		this.matVisible = matVisible;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="etp_cat_id")
	public VwEtapa getVwEtapa() {
		return vwEtapa;
	}
	public void setVwEtapa(VwEtapa vwEtapa) {
		this.vwEtapa = vwEtapa;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="vwMateria")
	@OrderBy("fechaHoraInicio ASC")
	public Set<VwTrabajoPractico> getTrabajosPracticos() {
		return trabajosPracticos;
	}
	public void setTrabajosPracticos(Set<VwTrabajoPractico> trabajosPracticos) {
		this.trabajosPracticos = trabajosPracticos;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="vwMateria")
	@OrderBy("fechaHoraInicio ASC")
	public Set<VwExamen> getExamenes() {
		return examenes;
	}
	public void setExamenes(Set<VwExamen> examenes) {
		this.examenes = examenes;
	}
	
}
