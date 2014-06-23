package ar.com.celia.seguimiento_alumnos.renderers;

import java.text.DecimalFormat;

import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import ar.com.celia.seguimiento_alumnos.domain.VwTpsVencidos;

public class MoreInfoTpVencidos implements ListitemRenderer{

	private DecimalFormat df = new DecimalFormat("#.##");
	@Override
	public void render(Listitem item, Object data, int index) throws Exception {
		
		VwTpsVencidos tpsVencidos=(VwTpsVencidos)data;
		if(index==0)
		{
			setHeader(item.getListbox());	
		}
		
		Listcell cellCurso=new Listcell();
		cellCurso.setParent(item);
		
		Label lbCurso=new Label(tpsVencidos.getCourseFullname());
		lbCurso.setParent(cellCurso);
		
		Listcell cellExamen=new Listcell();
		cellExamen.setParent(item);
		
		Label lbAssignment=new Label(tpsVencidos.getAssignmentName());
		lbAssignment.setParent(cellExamen);
		
	}
	
	private void setHeader(Listbox lb)
	{
		Listhead lh=new Listhead();
		lh.setParent(lb);
		
		Listheader lhr=new Listheader("Materia");
		lhr.setParent(lh);
		
		Listheader lhr2=new Listheader("Trabajo pr�ctico");
		lhr2.setWidth("120px");
		lhr2.setParent(lh);
	}

}
