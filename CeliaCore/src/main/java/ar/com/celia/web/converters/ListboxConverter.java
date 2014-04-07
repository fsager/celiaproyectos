package ar.com.celia.web.converters;

import org.zkoss.zkplus.databind.TypeConverter;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;


public class ListboxConverter implements TypeConverter {
	public Object coerceToBean(java.lang.Object val,org.zkoss.zk.ui.Component comp) {
		Listbox listbox=(Listbox)comp;
		if(listbox.getSelectedItem()!=null)
			return listbox.getSelectedItem().getValue();
		else
			return null;
	}
 
	public Object coerceToUi(java.lang.Object val, org.zkoss.zk.ui.Component comp) {
		Listbox listbox=(Listbox)comp;
		java.util.List<Listitem> listitems=listbox.getItems();
		for(Listitem item:listitems)
		{
			
			if(val!=null && item.getValue()!=null && item.getValue().equals(val))
			{
				item.setSelected(true);
				return item;
			}
		}
		
		if(listitems.size()>0)
			return listitems.get(0);
		else
			return null;
	}
}