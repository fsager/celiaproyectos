/**
 * 
 */
package ar.com.celia.seguimiento_alumnos.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * @author gdelasilva
 * La clase representa un Periodo de clases.
 */
@Entity
@Table(name = "vw_periodos_activos", schema = "seguimiento_alumnos")
public class VwPeriodo implements Serializable {
	
	private static final long serialVersionUID = 1362006470281474719L;
	
	private Long perId;
	private String periodo;
	private Long perOrden;
	private Boolean perVisible;
	private List<VwEtapa> vwEtapas = new ArrayList<VwEtapa>(); 
	
	@Id
	@Column(name = "per_cat_id", nullable = false)
	public Long getPerId() {
		return perId;
	}
	public void setPerId(Long perId) {
		this.perId = perId;
	}
	
	@Column(name = "periodo")
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	@Column(name = "per_orden")
	public Long getPerOrden() {
		return perOrden;
	}
	public void setPerOrden(Long perOrden) {
		this.perOrden = perOrden;
	}
	
	@Column(name = "per_visible")
	public Boolean getPerVisible() {
		return perVisible;
	}
	public void setPerVisible(Boolean perVisible) {
		this.perVisible = perVisible;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="vwPeriodo")
	@OrderBy("etpOrden DESC")
	public List<VwEtapa> getVwEtapas() {
		return vwEtapas;
	}
	public void setVwEtapas(List<VwEtapa> vwEtapas) {
		this.vwEtapas = vwEtapas;
	}
}
