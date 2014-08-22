/**
 * 
 */
package ar.com.celia.seguimiento_alumnos.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author gdelasilva
 * La clase representa una evaluacion impartida durante el curso de una materia(un curso en moodle).
 */
@MappedSuperclass
@IdClass(VwEvaluacionPk.class)
public class VwEvaluacion implements Serializable {

	private static final long serialVersionUID = 227152227418003101L;
	
	private Long id;
	private String tipoEvaluacion;
	private String titulo;
	private Date fechaHoraInicio;
	private Date fechaHoraFin;
	private VwMateria vwMateria;
	
	@Id
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Id
	@Column(name = "tipo_evaluacion", nullable = false)
	public String getTipoEvaluacion() {
		return tipoEvaluacion;
	}
	public void setTipoEvaluacion(String tipoEvaluacion) {
		this.tipoEvaluacion = tipoEvaluacion;
	}
	
	@Column(name = "titulo")
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_hora_inicio")
	public Date getFechaHoraInicio() {
		return fechaHoraInicio;
	}
	public void setFechaHoraInicio(Date fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_hora_fin")
	public Date getFechaHoraFin() {
		return fechaHoraFin;
	}
	public void setFechaHoraFin(Date fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mat_id" , insertable=false, updatable=false)
	public VwMateria getVwMateria() {
		return vwMateria;
	}
	
	public void setVwMateria(VwMateria vwMateria) {
		this.vwMateria = vwMateria;
	}
	

	@Transient
	public String getTituloCorto(){
		return titulo;
	}
}

class VwEvaluacionPk implements Serializable{

	private static final long serialVersionUID = -5743176869051961905L;
	
	private Long id;
	private String tipoEvaluacion;
	
	@Id
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Id
	@Column(name = "tipo_evaluacion", nullable = false)
	public String getTipoEvaluacion() {
		return tipoEvaluacion;
	}
	public void setTipoEvaluacion(String tipoEvaluacion) {
		this.tipoEvaluacion = tipoEvaluacion;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((tipoEvaluacion == null) ? 0 : tipoEvaluacion.hashCode());
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
		VwEvaluacionPk other = (VwEvaluacionPk) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tipoEvaluacion == null) {
			if (other.tipoEvaluacion != null)
				return false;
		} else if (!tipoEvaluacion.equals(other.tipoEvaluacion))
			return false;
		return true;
	}
	
}