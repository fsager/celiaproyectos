package ar.com.celia.seguimiento_alumnos.domain;

// Generated 20/04/2014 02:51:20 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CelGrupoUsuario generated by hbm2java
 */
@Entity
@Table(name = "cel_grupo_usuario", catalog = "seguimiento_alumnos")
public class CelGrupoUsuario implements java.io.Serializable {

	private Long gruId;
	private CelUsuario celUsuario;
	private CelGrupo celGrupo;

	public CelGrupoUsuario() {
	}

	public CelGrupoUsuario(CelUsuario celUsuario, CelGrupo celGrupo) {
		this.celUsuario = celUsuario;
		this.celGrupo = celGrupo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GRU_ID", unique = true, nullable = false)
	public Long getGruId() {
		return this.gruId;
	}

	public void setGruId(Long gruId) {
		this.gruId = gruId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USR_ID")
	public CelUsuario getCelUsuario() {
		return this.celUsuario;
	}

	public void setCelUsuario(CelUsuario celUsuario) {
		this.celUsuario = celUsuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GRP_ID")
	public CelGrupo getCelGrupo() {
		return this.celGrupo;
	}

	public void setCelGrupo(CelGrupo celGrupo) {
		this.celGrupo = celGrupo;
	}

}
