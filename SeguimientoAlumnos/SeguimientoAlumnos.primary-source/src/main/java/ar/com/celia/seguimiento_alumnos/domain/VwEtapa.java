/**
 * 
 */
package ar.com.celia.seguimiento_alumnos.domain;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

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
 * La clase representa una Etapa a la que puede pertenecer una materia.
 */
@Entity
@Table(name = "vw_etapas_activas", schema = "seguimiento_alumnos")
public class VwEtapa implements Serializable {

	private static final long serialVersionUID = 3765679167742364836L;
	
	private Long etpId;
	private String etapa;
	private Long etpOrden;
	private Boolean etpVisible;
	private VwPeriodo vwPeriodo;
	private Set<VwMateria> vwMaterias = new TreeSet<VwMateria>(); 
	
	@Id
	@Column(name = "etp_cat_id", nullable = false)
	public Long getEtpId() {
		return etpId;
	}
	public void setEtpId(Long etpId) {
		this.etpId = etpId;
	}
	
	@Column(name = "etapa")
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	@Column(name = "etp_orden")
	public Long getEtpOrden() {
		return etpOrden;
	}
	public void setEtpOrden(Long etpOrden) {
		this.etpOrden = etpOrden;
	}
	
	@Column(name = "etp_visible")
	public Boolean getEtpVisible() {
		return etpVisible;
	}
	public void setEtpVisible(Boolean etpVisible) {
		this.etpVisible = etpVisible;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="per_cat_id", insertable=false, updatable=false)
	public VwPeriodo getVwPeriodo() {
		return vwPeriodo;
	}
	public void setVwPeriodo(VwPeriodo vwPeriodo) {
		this.vwPeriodo = vwPeriodo;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="vwEtapa")
	@OrderBy("matOrden ASC")
	public Set<VwMateria> getVwMaterias() {
		return vwMaterias;
	}
	public void setVwMaterias(Set<VwMateria> vwMaterias) {
		this.vwMaterias = vwMaterias;
	}
	
	
	
}
