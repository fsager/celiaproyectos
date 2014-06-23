package ar.com.celia.seguimiento_alumnos.renderers;

import java.text.DecimalFormat;

import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import ar.com.celia.seguimiento_alumnos.domain.VwMaterias;

public class Materias implements ListitemRenderer{

	private DecimalFormat df = new DecimalFormat("#.##");
	
	@Override
	public void render(Listitem item, Object data, int index) throws Exception {
		
		VwMaterias tpsVencidos=(VwMaterias)data;
		if(index==0)
		{
			setHeader(item.getListbox());	
		}
		
		Listcell cellCurso=new Listcell();
		cellCurso.setParent(item);
		
		Label lbCurso=new Label(tpsVencidos.getFullname());
		lbCurso.setParent(cellCurso);
		
	}
	
	private void setHeader(Listbox lb)
	{
		Listhead lh=new Listhead();
		lh.setParent(lb);
		
		Listheader lhr=new Listheader("Materia");
		lhr.setParent(lh);
		
	}

}
