/**
 * 
 */
package ar.com.celia.seguimiento_alumnos.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author gdelasilva
 * La clase representa una materia(curso en moodle).
 */
@Entity
@Table(name = "vw_trabajos_practicos", schema = "seguimiento_alumnos")
public class VwTrabajoPractico extends VwEvaluacion {

	private static final long serialVersionUID = -5573846571573168627L;
	
//	@Column(name = "descripcion")
//	public String getTpDescripcion() {
//		return tpDescripcion;
//	}
//	public void setTpDescripcion(String tpDescripcion) {
//		this.tpDescripcion = tpDescripcion;
//	}

//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "timedue")
//	public Date getTpTimeDue() {
//		return tpTimeDue;
//	}
//	public void setTpTimeDue(Date tpTimeDue) {
//		this.tpTimeDue = tpTimeDue;
//	}
//	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "timeavailable")
//	public Date getTpTimeAvailable() {
//		return tpTimeAvailable;
//	}
//	public void setTpTimeAvailable(Date tpTimeAvailable) {
//		this.tpTimeAvailable = tpTimeAvailable;
//	}
	@Override
	@Transient
	public String getTituloCorto(){
		return getTitulo().toLowerCase().trim()
				.replaceAll("trabajo práctico", "TP")
				.replaceAll("trabajo practico", "TP").toUpperCase();
	}
}
