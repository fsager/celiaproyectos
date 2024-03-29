package ar.com.celia.seguimiento_alumnos.domain;

// Generated 20/04/2014 02:51:20 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CelPropiedad generated by hbm2java
 */
@Entity
@Table(name = "cel_propiedad", catalog = "seguimiento_alumnos")
public class CelPropiedad implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3847948694560548140L;
	private String proClave;
	private String proTipo;
	private String proValor;
	private String proDescripcion;

	public CelPropiedad() {

	}

	public CelPropiedad(String proClave, String proTipo, String proValor,
			String proDescripcion) {
		this.proClave = proClave;
		this.proTipo = proTipo;
		this.proValor = proValor;
		this.proDescripcion = proDescripcion;
	}

	@Id
	@Column(name = "PRO_CLAVE", nullable = false, length = 250)
	public String getProClave() {
		return this.proClave;
	}

	public void setProClave(String proClave) {
		this.proClave = proClave;
	}

	@Column(name = "PRO_TIPO", nullable = false, length = 100)
	public String getProTipo() {
		return this.proTipo;
	}

	public void setProTipo(String proTipo) {
		this.proTipo = proTipo;
	}

	@Column(name = "PRO_VALOR", nullable = false, length = 4000)
	public String getProValor() {
		return this.proValor;
	}

	public void setProValor(String proValor) {
		this.proValor = proValor;
	}

	@Column(name = "PRO_DESCRIPCION", nullable = false, length = 4000)
	public String getProDescripcion() {
		return this.proDescripcion;
	}

	public void setProDescripcion(String proDescripcion) {
		this.proDescripcion = proDescripcion;
	}

}