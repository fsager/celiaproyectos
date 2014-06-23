package ar.com.celia.seguimiento_alumnos.renderers;

import java.text.DecimalFormat;

import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import ar.com.celia.seguimiento_alumnos.domain.VwExameneesReprobados;

public class MoreInfoExamenesReprobados implements ListitemRenderer{

	private DecimalFormat df = new DecimalFormat("#.##");
	@Override
	public void render(Listitem item, Object data, int index) throws Exception {
		
		VwExameneesReprobados exameneesReprobados=(VwExameneesReprobados)data;
		if(index==0)
		{
			setHeader(item.getListbox());	
		}
		
		Listcell cellCurso=new Listcell();
		cellCurso.setParent(item);
		
		Label lbCurso=new Label(exameneesReprobados.getCourseFullname());
		lbCurso.setParent(cellCurso);
		
		Listcell cellExamen=new Listcell();
		cellExamen.setParent(item);
		
		Label lbExamen=new Label(exameneesReprobados.getQuizName());
		lbExamen.setParent(cellExamen);
		
		Listcell cellNota=new Listcell();
		cellNota.setParent(item);
		
		String nota="-";
		if(exameneesReprobados.getQuizGrade()!=null)
		{
			
			nota=df.format(exameneesReprobados.getQuizGrade());
		}
		
		Label lbNota=new Label(nota);
		lbNota.setParent(cellNota);
		
	}
	
	private void setHeader(Listbox lb)
	{
		Listhead lh=new Listhead();
		lh.setParent(lb);
		
		Listheader lhr=new Listheader("Materia");
		lhr.setParent(lh);
		
		Listheader lhr2=new Listheader("Examen");
		lhr2.setWidth("100px");
		lhr2.setParent(lh);
		
		Listheader lhr3=new Listheader("Nota");
		lhr3.setWidth("50px");
		lhr3.setParent(lh);
	}

}
