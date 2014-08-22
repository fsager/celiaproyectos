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
@Table(name = "vw_examenes", schema = "seguimiento_alumnos")
public class VwExamen extends VwEvaluacion {

	private static final long serialVersionUID = 1789117162824499287L;
		
		
//	@Column(name = "descripcion")
//	public String getExDescripcion() {
//		return exDescripcion;
//	}
//	public void setExDescripcion(String exDescripcion) {
//		this.exDescripcion = exDescripcion;
//	}	

//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "timeopen")
//	public Date getExTimeOpen() {
//		return exTimeOpen;
//	}
//	public void setExTimeOpen(Date exTimeOpen) {
//		this.exTimeOpen = exTimeOpen;
//	}
//	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "timeclose")
//	public Date getExTimeClose() {
//		return exTimeClose;
//	}
//	public void setExTimeClose(Date exTimeClose) {
//		this.exTimeClose = exTimeClose;
//	}
//	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "timecreated")
//	public Date getExTimeCreated() {
//		return exTimeCreated;
//	}
//	
//	public void setExTimeCreated(Date exTimeCreated) {
//		this.exTimeCreated = exTimeCreated;
//	}
	
	@Override
	@Transient
	public String getTituloCorto(){
		return getTitulo().toLowerCase().trim()
				
				.replaceAll("recuperatorio primer ex�men", "1� R")
				.replaceAll("recuperatorio primer examen", "1� R")
				.replaceAll("recuperatorio segundo examen", "2� R")
				.replaceAll("recuperatorio segundo ex�men", "2� R")
				.replaceAll("recuperatorio tercer ex�men", "3� R")
				.replaceAll("recuperatorio tercer examen", "3� R")
				.replaceAll("recuperatorio cuarto ex�men", "4� R")
				.replaceAll("recuperatorio cuarto examen", "4� R")
				.replaceAll("recuperatorio quinto ex�men", "5� R")
				.replaceAll("recuperatorio quinto examen", "5� R")
				.replaceAll("recuperatorio sexto ex�men", "6� R")
				.replaceAll("recuperatorio sexto examen", "6� R")
				.replaceAll("recuperatorio septimo ex�men", "7� R")
				.replaceAll("recuperatorio septimo examen", "7� R")
				.replaceAll("recuperatorio s�ptimo ex�men", "7� R")
				.replaceAll("recuperatorio s�ptimo examen", "7� R")
				.replaceAll("recuperatorio octavo ex�men", "8� R")
				.replaceAll("recuperatorio octavo examen", "8� R")
				.replaceAll("recuperatorio noveno ex�men", "9� R")
				.replaceAll("recuperatorio noveno examen", "9� R")
				
				.replaceAll("primer ex�men", "1� E")
				.replaceAll("primer examen", "1� E")
				.replaceAll("segundo examen", "2� E")
				.replaceAll("segundo ex�men", "2� E")
				.replaceAll("tercer ex�men", "3� E")
				.replaceAll("tercer examen", "3� E")
				.replaceAll("cuarto ex�men", "4� E")
				.replaceAll("cuarto examen", "4� E")
				.replaceAll("quinto ex�men", "5� E")
				.replaceAll("quinto examen", "5� E")
				.replaceAll("sexto ex�men", "6� E")
				.replaceAll("sexto examen", "6� E")
				.replaceAll("septimo ex�men", "7� E")
				.replaceAll("septimo examen", "7� E")
				.replaceAll("s�ptimo ex�men", "7� E")
				.replaceAll("s�ptimo examen", "7� E")
				.replaceAll("octavo ex�men", "8� E")
				.replaceAll("octavo examen", "8� E")
				.replaceAll("noveno ex�men", "9� E")
				.replaceAll("noveno examen", "9� E")
				
				.replaceAll("ex�men final", "F")
				.replaceAll("examen final", "F").toUpperCase()
				;
	}
}
