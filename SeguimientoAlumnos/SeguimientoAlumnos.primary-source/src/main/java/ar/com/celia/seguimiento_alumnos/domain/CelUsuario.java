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
 * CelUsuario generated by hbm2java
 */
@Entity
@Table(name = "cel_usuario", catalog = "seguimiento_alumnos")
public class CelUsuario implements java.io.Serializable {

	private Long usrId;
	private String usrUsuario;
	private String usrClave;
	private String usrNombre;
	private String usrApellido;
	private String usrMail;
	private Integer usrActivo;
	private Date audFechaIns;
	private Date audFechaUpd;
	private String audUsrIns;
	private String audUsrUpd;
	private Set celGrupoUsuarios = new HashSet(0);

	public CelUsuario() {
	}

	public CelUsuario(Date audFechaIns, Date audFechaUpd) {
		this.audFechaIns = audFechaIns;
		this.audFechaUpd = audFechaUpd;
	}

	public CelUsuario(String usrUsuario, String usrClave, String usrNombre,
			String usrApellido, String usrMail, Integer usrActivo,
			Date audFechaIns, Date audFechaUpd, String audUsrIns,
			String audUsrUpd, Set celGrupoUsuarios) {
		this.usrUsuario = usrUsuario;
		this.usrClave = usrClave;
		this.usrNombre = usrNombre;
		this.usrApellido = usrApellido;
		this.usrMail = usrMail;
		this.usrActivo = usrActivo;
		this.audFechaIns = audFechaIns;
		this.audFechaUpd = audFechaUpd;
		this.audUsrIns = audUsrIns;
		this.audUsrUpd = audUsrUpd;
		this.celGrupoUsuarios = celGrupoUsuarios;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USR_ID", unique = true, nullable = false)
	public Long getUsrId() {
		return this.usrId;
	}

	public void setUsrId(Long usrId) {
		this.usrId = usrId;
	}

	@Column(name = "USR_USUARIO", length = 50)
	public String getUsrUsuario() {
		return this.usrUsuario;
	}

	public void setUsrUsuario(String usrUsuario) {
		this.usrUsuario = usrUsuario;
	}

	@Column(name = "USR_CLAVE", length = 100)
	public String getUsrClave() {
		return this.usrClave;
	}

	public void setUsrClave(String usrClave) {
		this.usrClave = usrClave;
	}

	@Column(name = "USR_NOMBRE", length = 250)
	public String getUsrNombre() {
		return this.usrNombre;
	}

	public void setUsrNombre(String usrNombre) {
		this.usrNombre = usrNombre;
	}

	@Column(name = "USR_APELLIDO", length = 250)
	public String getUsrApellido() {
		return this.usrApellido;
	}

	public void setUsrApellido(String usrApellido) {
		this.usrApellido = usrApellido;
	}

	@Column(name = "USR_MAIL", length = 250)
	public String getUsrMail() {
		return this.usrMail;
	}

	public void setUsrMail(String usrMail) {
		this.usrMail = usrMail;
	}

	@Column(name = "USR_ACTIVO")
	public Integer getUsrActivo() {
		return this.usrActivo;
	}

	public void setUsrActivo(Integer usrActivo) {
		this.usrActivo = usrActivo;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "celUsuario")
	public Set getCelGrupoUsuarios() {
		return this.celGrupoUsuarios;
	}

	public void setCelGrupoUsuarios(Set celGrupoUsuarios) {
		this.celGrupoUsuarios = celGrupoUsuarios;
	}

}