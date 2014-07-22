package ar.com.celia.seguimiento_alumnos.domain;

// Generated 06/04/2014 19:30:09 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * VwAlumnosActivos generated by hbm2java
 */
@Entity
@Table(name = "vw_alerta_alumnos_nuevos_examenes", schema = "seguimiento_alumnos")
public class VwAlertasExamenes implements java.io.Serializable {

	private Long quizid;
	private Long userid;
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

	@Column(name = "userid", nullable = false)
	public Long getUserid() {
		return userid;
	}

	
	public void setUserid(Long userid) {
		this.userid = userid;
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

}