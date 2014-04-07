package ar.com.celia.core.mbean;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(objectName = "bean:name=VersionInfo", description = "Información sobre la version del componente")
public class VersionInfo {

	private String parentAplicaciont;
	private String aplicacion;
	private String version;
	
	public VersionInfo()
	{
	}

	
	public VersionInfo(String version)
	{
		this.version=version;
	}

	@ManagedAttribute
	public String getVersion() {
		return version;
	}

	public String getParentAplicaciont() {
		return parentAplicaciont;
	}
	
	public void setParentAplicaciont(String parentAplicaciont) {
		this.parentAplicaciont = parentAplicaciont;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
}