package ar.com.celia.seguimiento_alumnos.viewmodel.bitacora;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;

public class BitacoraNuevaInteraccionVM {

	
	
	
	@Init
	public void init() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) throws Exception {
		Selectors.wireComponents(view, this, false);

	}
	
	
	@Command
	public void guardarInteraccion() throws Exception {

	}
	
}