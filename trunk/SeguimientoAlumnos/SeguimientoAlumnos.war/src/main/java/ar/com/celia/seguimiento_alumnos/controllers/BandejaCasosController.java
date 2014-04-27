package ar.com.celia.seguimiento_alumnos.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkmax.zul.Chosenbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

import ar.com.celia.core.business.ContextManagerCore;
import ar.com.celia.seguimiento_alumnos.domain.CelIndicador;
import ar.com.celia.seguimiento_alumnos.domain.VwAlumnosActivos;
import ar.com.celia.seguimiento_alumnos.domain.VwIndicadoresAlumnos;
import ar.com.celia.seguimiento_alumnos.service.CelIndicadorDefinition;
import ar.com.celia.seguimiento_alumnos.service.CelInteraccionCasoDefinition;
import ar.com.celia.seguimiento_alumnos.service.VwAlumnosActivosDefinition;

public class BandejaCasosController extends GenericForwardComposer {

	private VwAlumnosActivosDefinition vwAlumnosActivosService=(VwAlumnosActivosDefinition)ContextManagerCore.getBizObject("vwAlumnosActivosService");
	private CelInteraccionCasoDefinition celInteraccionCasoService=(CelInteraccionCasoDefinition)ContextManagerCore.getBizObject("celInteraccionCasoService");
	private CelIndicadorDefinition celIndicadorService=(CelIndicadorDefinition)ContextManagerCore.getBizObject("celIndicadorService");

	
	private Window wndIndex;
	private Listbox lstBandejaCasos;
	private Textbox txtNombre;
	private Textbox txtApellido;
	private Textbox txtMatricula;
	private Chosenbox chosenIndicadores;
	
    public BandejaCasosController()
    {

    }
    
    public void onCreate$wndIndex(CreateEvent event) throws Exception {    	
        cargarIndicadores();
        cargarBandeja();       
    }
    
    public void cargarIndicadores() throws Exception 
    {
    	CelIndicador celIndicadorExample=new CelIndicador();
    	String[] lazyFalse=null;
    	List<CelIndicador> indicadores=celIndicadorService.getAll(celIndicadorExample, lazyFalse);    	
		Collections.sort(indicadores,new CelIndicador());    	
        ListModel<CelIndicador> model =new ListModelList<CelIndicador>(indicadores);
        chosenIndicadores.setModel(model);
        
        cargarHeader(indicadores);
    }
    
    public void cargarHeader(List<CelIndicador> indicadores)
    {
    	Listhead head=new Listhead();
    	head.setParent(lstBandejaCasos);
    	
    	Listheader header=new Listheader("Datos personales");
    	header.setWidth("400px");
    	header.setParent(head);
    	
    	for(CelIndicador indicador:indicadores)
    	{
    		header=new Listheader(indicador.getIndNombre());
        	header.setParent(head);
    	}
    	
    }
    
    
    
    public void onClick$btnFiltrar(Event event) throws Exception {
    	cargarBandeja();
    }
    
    public void onOK$gridFiltros(Event event) throws Exception {
    	cargarBandeja();
    }

    public String getSelectedIndicadores()
    {
    	Set<CelIndicador> indicadoresSeleccionados=chosenIndicadores.getSelectedObjects();
    	String selected="";
    	for(CelIndicador ind:indicadoresSeleccionados){
    		selected+=","+ind.getIndId();
    	}
    	
    	if(selected.length()>0)
    		selected=selected.substring(1);
    	
    	return selected;
    }
    
    public void cargarBandeja() throws Exception{
    	lstBandejaCasos.getItems().clear();
    	String selectedIndicadores= getSelectedIndicadores();
    	
        List<VwAlumnosActivos> alumnos=vwAlumnosActivosService.p_alumnos_activos_con_indicadores(selectedIndicadores, txtMatricula.getValue(),txtApellido.getValue(),txtNombre.getValue());

    	for(VwAlumnosActivos alumno:alumnos)
    	{
    		java.util.Set <VwIndicadoresAlumnos> indicadoresSet=alumno.getIndicadoresAlumnos();
    		List<VwIndicadoresAlumnos> myListToOrder = new ArrayList(indicadoresSet);
    		Collections.sort(myListToOrder,new CelIndicador());
    		
    		if(!indicadoresSet.isEmpty())
    		{
    			Listitem row=new Listitem();
    			crearPanelDatosPersonales(row,alumno);
        		    			
        		for(VwIndicadoresAlumnos indicador:myListToOrder){
                	
        			Listcell celdaIndicadores=new Listcell();
        			Image imgIndicador=new Image();
        			imgIndicador.setTooltiptext(indicador.getDescIndicador());
        			imgIndicador.setParent(celdaIndicadores);
        			
        			if(indicador.getValorIndicador()==1)
        				imgIndicador.setSrc("/img/green.jpg");
        			else if(indicador.getValorIndicador()==2)
        				imgIndicador.setSrc("/img/red.jpg");
        			else 
        				imgIndicador.setSrc("/img/yellow.jpg");
        			
        			celdaIndicadores.setParent(row);
            		
            	}        		
        		row.setValue(alumno);
        		row.setParent(lstBandejaCasos);
    		}
    		
    	}
    } 
    
    public void crearPanelDatosPersonales(Listitem row,VwAlumnosActivos alumno) throws Exception
    {
		Listcell celdaDatosPersonales=new Listcell();
		celdaDatosPersonales.setStyle("width:400px;");
		
		Hbox hBoxDatosPersonales= new Hbox();
		hBoxDatosPersonales.setSclass("resultForm");
		hBoxDatosPersonales.setStyle("line-height:0px;align:stretch;width:390px;");
		hBoxDatosPersonales.setParent(celdaDatosPersonales);

		Div nombreMatricula=new Div();
		nombreMatricula.setZclass("none");
		nombreMatricula.setStyle("width:150px;padding:10px;");
		nombreMatricula.setParent(hBoxDatosPersonales);
		
		Div divNombre=new Div();
		divNombre.setZclass("none");
		divNombre.setParent(nombreMatricula);
		
		String nombre=alumno.getLastname()+", "+alumno.getFirstname();
		Label lblnombre= new Label();
		lblnombre.setValue(nombre);
		lblnombre.setStyle("font-weight:bold;");
		lblnombre.setParent(divNombre);
		
		Div divNMatricula=new Div();
		divNMatricula.setZclass("none");
		divNMatricula.setStyle("padding-top:10px;");		
		divNMatricula.setParent(nombreMatricula);
		
		Label lblMatricula= new Label();
		lblMatricula.setValue("Matrícula: "+alumno.getMatricula());
		lblMatricula.setStyle("font-weight:bold;");
		lblMatricula.setParent(divNMatricula);
				
		
		Vbox vBoxDatos=new Vbox();
		vBoxDatos.setParent(hBoxDatosPersonales);
		
		Hbox hTelefono1=new Hbox();
		hTelefono1.setParent(vBoxDatos);
		
		String telefono1="-";
		if(alumno.getPhone1()!=null && !alumno.getPhone1().equals(""))
			telefono1=alumno.getPhone1();
		
		Label lblTelefono1=new Label("Teléfono: "+telefono1);
		lblTelefono1.setParent(hTelefono1);
		
		String telefono2="-";
		if(alumno.getPhone2()!=null && !alumno.getPhone2().equals(""))
			telefono1=alumno.getPhone2();
		
		Label lbltelefono2=new Label("Teléfono: "+telefono2);
		lbltelefono2.setParent(hTelefono1);
		
		Hbox hTelefono2=new Hbox();
		hTelefono2.setParent(vBoxDatos);
		
		Label lblEmail=new Label();
		String email="-";
		if(alumno.getEmail()!=null)email=alumno.getEmail();
		lblEmail.setValue("E-mail: "+email);
		lblEmail.setParent(vBoxDatos);

		Label lblContactos=new Label();
		lblContactos.setValue("Contactos: "+getContactosAlumno(alumno.getId()));
		lblContactos.setParent(vBoxDatos);
		
		Button btnNuevoContacto=new Button();
		btnNuevoContacto.setLabel("Nuevo Contacto");
		btnNuevoContacto.setAttribute("alumno", alumno);
		btnNuevoContacto.setSclass("tn btn-xs btn-info");
		btnNuevoContacto.addEventListener("onClick", new EventListener() {
		    public void onEvent(Event event) throws Exception {
		    	
		        abrirVentanDetalle((VwAlumnosActivos)event.getTarget().getAttribute("alumno"));
		    }
		});
		btnNuevoContacto.setParent(vBoxDatos);
		
		
		celdaDatosPersonales.setParent(row);
    }
    
   
    public void abrirVentanDetalle(VwAlumnosActivos alumno) throws Exception
    {
	    java.util.Properties params = new java.util.Properties();
        params.put("alumno", alumno);
        params.put("contactos", getContactosAlumno(alumno.getId()));
		Window win = (Window) Executions.createComponents("/celia/detalle_caso.zul", null,params);
		win.doModal();
    }
    
   
    public String getContactosAlumno(Long aluId) throws Exception{
    	String contactos="0";
    	int cantidadContactos=celInteraccionCasoService.getCantidadContactos(aluId);
    	contactos=String.valueOf(cantidadContactos);
    	return contactos;
    }

}
