package ar.com.celia.common.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Detail;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Window;

import ar.com.celia.core.business.ContextManagerCore;
import ar.com.celia.core.mbean.MBeanServerReader;
import ar.com.celia.core.mbean.VersionInfo;


public class ControllerVersion extends GenericForwardComposer{
	
	
	private Window wndMain;
	private Rows applicaciones;
	private MBeanServerReader mbeanVersionInfo;
	
	public void onCreate$wndMain(Event evt) throws Exception
	{
		
		java.util.List<VersionInfo> versiones=mbeanVersionInfo.getVersionBeans();
		String parentAplicacion=null;
		Row masterRow=null;
		Detail detalle=null;
		Rows rows=null;
		for(VersionInfo version: versiones)
		{

			if(parentAplicacion==null || !parentAplicacion.equals(version.getParentAplicaciont()))
			{
				parentAplicacion=version.getParentAplicaciont();
				//Maestro
				masterRow=new Row();
				masterRow.setParent(applicaciones);
				Label label=new Label(version.getParentAplicaciont());
				label.setParent(masterRow);
				detalle=new Detail();
				detalle.setParent(masterRow);
				detalle.setOpen(true);

				Grid grid=new Grid();
				Columns columns=new Columns();
				columns.setParent(grid);
				
				Column column1=new Column();
				column1.setParent(columns);
				Column column2=new Column("Componente");
				column2.setParent(columns);
				Column column3=new Column("Versión");
				column3.setParent(columns);

				grid.setParent(detalle);
				rows=new Rows();
				rows.setParent(grid);
				
				addDetail(rows,detalle,version);
			}
			else
			{
				addDetail(rows,detalle,version);
			}
		}		
	}
	
	private void addDetail(Rows rows,Detail detalle,VersionInfo version)
	{
		Row row=new Row();
		row.setParent(rows);
		Label lblEmpty=new Label("");
		lblEmpty.setParent(row);
		Label lblAplicacion=new Label(version.getAplicacion());
		lblAplicacion.setParent(row);
		Label lblVersion=new Label(version.getVersion());
		lblVersion.setParent(row);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);

	mbeanVersionInfo=(MBeanServerReader)arg.get("mbeanVersionInfo");

	}

}

