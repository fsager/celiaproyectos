package ar.com.celia.seguimiento_alumnos.domain;

// Generated 20/04/2014 02:51:20 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CelGrupo generated by hbm2java
 */
@Entity
@Table(name = "cel_grupo", catalog = "seguimiento_alumnos")
public class CelGrupo implements java.io.Serializable {

	private Long grpId;
	private String grpNombre;
	private String grpDescripcion;
	private Date audFechaIns;
	private Date audFechaUpd;
	private String audUsrIns;
	private String audUsrUpd;
	//private Set celGrupoUsuarios = new HashSet(0);

	public CelGrupo() {
	}

	public CelGrupo(Date audFechaIns, Date audFechaUpd) {
		this.audFechaIns = audFechaIns;
		this.audFechaUpd = audFechaUpd;
	}

	public CelGrupo(String grpNombre, String grpDescripcion, Date audFechaIns,
			Date audFechaUpd, String audUsrIns, String audUsrUpd,
			Set celGrupoUsuarios) {
		this.grpNombre = grpNombre;
		this.grpDescripcion = grpDescripcion;
		this.audFechaIns = audFechaIns;
		this.audFechaUpd = audFechaUpd;
		this.audUsrIns = audUsrIns;
		this.audUsrUpd = audUsrUpd;
		//this.celGrupoUsuarios = celGrupoUsuarios;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GRP_ID", unique = true, nullable = false)
	public Long getGrpId() {
		return this.grpId;
	}

	public void setGrpId(Long grpId) {
		this.grpId = grpId;
	}

	@Column(name = "GRP_NOMBRE", length = 50)
	public String getGrpNombre() {
		return this.grpNombre;
	}

	public void setGrpNombre(String grpNombre) {
		this.grpNombre = grpNombre;
	}

	@Column(name = "GRP_DESCRIPCION", length = 4000)
	public String getGrpDescripcion() {
		return this.grpDescripcion;
	}

	public void setGrpDescripcion(String grpDescripcion) {
		this.grpDescripcion = grpDescripcion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AUD_FECHA_INS", nullable = false, length = 19)
	public Date getAudFechaIns() {
		return this.audFechaIns;
	}

	public void setAudFechaIns(Date audFechaIns) {
		this.audFechaIns = audFechaIns;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AUD_FECHA_UPD", nullable = false, length = 19)
	public Date getAudFechaUpd() {
		return this.audFechaUpd;
	}

	public void setAudFechaUpd(Date audFechaUpd) {
		this.audFechaUpd = audFechaUpd;
	}

	@Column(name = "AUD_USR_INS", length = 250)
	public String getAudUsrIns() {
		return this.audUsrIns;
	}

	public void setAudUsrIns(String audUsrIns) {
		this.audUsrIns = audUsrIns;
	}

	@Column(name = "AUD_USR_UPD", length = 250)
	public String getAudUsrUpd() {
		return this.audUsrUpd;
	}

	public void setAudUsrUpd(String audUsrUpd) {
		this.audUsrUpd = audUsrUpd;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "celGrupo")
//	public Set getCelGrupoUsuarios() {
//		return this.celGrupoUsuarios;
//	}
//
//	public void setCelGrupoUsuarios(Set celGrupoUsuarios) {
//		this.celGrupoUsuarios = celGrupoUsuarios;
//	}

}
