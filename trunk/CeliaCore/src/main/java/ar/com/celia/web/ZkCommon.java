package ar.com.celia.web;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;

public class ZkCommon {
	public static void setColumnsMenuFor(Listbox listbox, String id, Component padre)
	{
		Menupopup men=new Menupopup();
		men.setId(id);
			Listhead lh =(Listhead)listbox.getListhead();
			java.util.List<Listheader> lhChilds = lh.getChildren();
			
			for (int i=0;i<lhChilds.size();i++)
			{
				final Listheader lHeader =(Listheader)lhChilds.get(i);
				final Menuitem mi=new Menuitem();
				mi.setLabel(lHeader.getLabel());
				mi.setChecked(lHeader.isVisible());
				mi.setCheckmark(true); 
				mi.setParent(men);
				mi.addEventListener ("onClick", new EventListener(){
						public boolean isAsap() {
							return true;
						}
						public void onEvent(Event event) {
							mi.setChecked(!mi.isChecked());
							lHeader.setVisible(mi.isChecked()); 
						}
					});
			}
		men.setParent (padre);
	}
}

