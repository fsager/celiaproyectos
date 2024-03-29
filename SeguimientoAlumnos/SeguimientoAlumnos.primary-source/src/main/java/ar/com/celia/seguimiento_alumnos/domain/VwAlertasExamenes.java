package ar.com.celia.seguimiento_alumnos.domain;

// Generated 06/04/2014 19:30:09 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * VwAlumnosActivos generated by hbm2java
 */
@Entity
@Table(name = "vw_alerta_alumnos_nuevos_examenes", schema = "seguimiento_alumnos")
@IdClass(VwAlertasExamenesPk.class)
public class VwAlertasExamenes implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7496446728054189321L;
	private Long quizid;
	private Long userid;
	private String lastname;
	private String firstname;
	private String email;
	private String courseFullname;
	private String courseShortname;
	private String courseCategory;
	private String quizName;
	private String fechaInicio;
	private String fechaVencimiento;

	public VwAlertasExamenes() {
	}

	@Id
	@Column(name = "quiz_id", nullable = false)
	public Long getQuizid() {
		return quizid;
	}
	
	public void setQuizid(Long quizid) {
		this.quizid = quizid;
	}

	@Id
	@Column(name = "userid", nullable = false)
	public Long getUserid() {
		return userid;
	}
	
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	@Column(name = "lastname", nullable = false)
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "firstname", nullable = false)
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "course_fullname", nullable = false)
	public String getCourseFullname() {
		return courseFullname;
	}

	public void setCourseFullname(String courseFullname) {
		this.courseFullname = courseFullname;
	}
	
	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}
	
	
	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "course_shortname", nullable = false)
	public String getCourseShortname() {
		return courseShortname;
	}

	public void setCourseShortname(String courseShortname) {
		this.courseShortname = courseShortname;
	}

	@Column(name = "course_category", nullable = false)
	public String getCourseCategory() {
		return courseCategory;
	}

	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}

	@Column(name = "quiz_name", nullable = false)
	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	
	@Column(name = "fecha_inicio", nullable = true)
	public String getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	@Column(name = "fecha_vencimiento", nullable = true)
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	@Override
	public String toString() {
		return "VwAlertasExamenes [quizid=" + quizid + ", userid=" + userid
				+ ", lastname=" + lastname + ", firstname=" + firstname
				+ ", email=" + email + ", courseFullname=" + courseFullname
				+ ", courseShortname=" + courseShortname + ", courseCategory="
				+ courseCategory + ", quizName=" + quizName + ", fechaInicio="
				+ fechaInicio + ", fechaVencimiento=" + fechaVencimiento + "]";
	}
	
}

class VwAlertasExamenesPk implements Serializable{

	private static final long serialVersionUID = -5848980863267591309L;
	private Long quizid;
	private Long userid;
	
	@Id
	@Column(name = "quiz_id", nullable = false)
	public Long getQuizid() {
		return quizid;
	}
	
	public void setQuizid(Long quizid) {
		this.quizid = quizid;
	}

	@Id
	@Column(name = "userid", nullable = false)
	public Long getUserid() {
		return userid;
	}
	
	public void setUserid(Long userid) {
		this.userid = userid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((quizid == null) ? 0 : quizid.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VwAlertasExamenesPk other = (VwAlertasExamenesPk) obj;
		if (quizid == null) {
			if (other.quizid != null)
				return false;
		} else if (!quizid.equals(other.quizid))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}
	
	
}
