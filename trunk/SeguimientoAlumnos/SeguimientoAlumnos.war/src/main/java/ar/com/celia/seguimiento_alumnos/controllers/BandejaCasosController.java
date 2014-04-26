package ar.com.celia.seguimiento_alumnos.controllers;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.acegi.ShowWindowEventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Space;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

import com.sun.xml.bind.v2.runtime.reflect.Lister;

import ar.com.celia.core.business.ContextManagerCore;
import ar.com.celia.seguimiento_alumnos.domain.*;
import ar.com.celia.seguimiento_alumnos.persistence.VwIndicadoresAlumnosDao;
import ar.com.celia.seguimiento_alumnos.service.CelInteraccionCasoDefinition;
import ar.com.celia.seguimiento_alumnos.service.VwAlumnosActivosDefinition;
import ar.com.celia.seguimiento_alumnos.service.impl.*;

public class BandejaCasosController extends GenericForwardComposer {
	
	private Window wndIndex;
	private VwAlumnosActivosDefinition vwAlumnosActivosService=(VwAlumnosActivosDefinition)ContextManagerCore.getBizObject("vwAlumnosActivosService");
	CelInteraccionCasoDefinition celInteraccionCasoService=(CelInteraccionCasoDefinition)ContextManagerCore.getBizObject("celInteraccionCasoService");
	private Button btnFiltrar;
	private Listbox lstBandejaCasos;
	
	
    public BandejaCasosController()
    {

    }
    
    public void onCreate$wndIndex(CreateEvent event) throws Exception {
    	
    	java.util.List indicadores=new java.util.ArrayList();
        indicadores.add("Todos");
        indicadores.add("Ingresó al Moodle");
        indicadores.add("Rindió examen");
        indicadores.add("Prensentó trabajo práctico");
        indicadores.add("Falta mucho");
         
        org.zkoss.zul.ListModel model =new org.zkoss.zul.ListModelList(indicadores);
        
        java.util.List roles=new java.util.ArrayList();
        roles.add("Alumnos");
        roles.add("Docentes");
         
        org.zkoss.zul.ListModel modelRoles =new org.zkoss.zul.ListModelList(roles);
        
        
        VwAlumnosActivos vwAlumnosActivos=new VwAlumnosActivos();
    	String[] lazyFalse={"indicadoresAlumnos"};
        java.util.List alumnos=vwAlumnosActivosService.getAll(vwAlumnosActivos,lazyFalse);
        
        System.out.println("alumnos.size(): "+alumnos.size());
        
        if(alumnos.size()>0){
        	VwAlumnosActivos alumno=(VwAlumnosActivos)alumnos.get(0);
        	System.out.println("alumno: "+alumno.getId());
        	java.util.Set <VwIndicadoresAlumnos> indicadoresSet=alumno.getIndicadoresAlumnos();
        	System.out.println("indicadores.size(): "+indicadores.size());
        	
        	for(VwIndicadoresAlumnos indicador:indicadoresSet){
        	
        		System.out.println("indicador.getCodigoIndicador(): "+indicador.getCodigoIndicador());
        		System.out.println("indicador.getValorIndicador(): "+indicador.getValorIndicador());
        	}
        	
        }
        if(alumnos.size()>0){
        	VwAlumnosActivos alumno=(VwAlumnosActivos)alumnos.get(1);
        	System.out.println("alumno: "+alumno.getId());
        	java.util.Set <VwIndicadoresAlumnos> indicadoresSet=alumno.getIndicadoresAlumnos();
        	System.out.println("indicadores.size(): "+indicadores.size());
        	
        	for(VwIndicadoresAlumnos indicador:indicadoresSet){
        	
        		System.out.println("indicador.getCodigoIndicador(): "+indicador.getCodigoIndicador());
        		System.out.println("indicador.getValorIndicador(): "+indicador.getValorIndicador());
        	}
        	
        }
    }
    
    
    public void onClick$btnFiltrar(Event event) throws Exception {
    	
    	System.out.println("Entro a filtrar");
    	filtrar();
    	cargarBandeja();
    }
    
    public void filtrar(){
    	
    }
    
    public void cargarBandeja() throws Exception{
    	VwAlumnosActivos vwAlumnosActivos=new VwAlumnosActivos();
    	String[] lazyFalse={"indicadoresAlumnos"};
        java.util.List alumnos=vwAlumnosActivosService.getAll(vwAlumnosActivos,lazyFalse);
        
        if(!alumnos.isEmpty())
        {	
        	//for(int i=0; i<alumnos.size();i++)
        	for(int i=0; i<10;i++)
        	{
        		VwAlumnosActivos alumno=(VwAlumnosActivos)alumnos.get(i);
        		java.util.Set <VwIndicadoresAlumnos> indicadoresSet=alumno.getIndicadoresAlumnos();

        		if(!indicadoresSet.isEmpty() /*&& poseeIndicadoresIrregulares(alumno)*/)
        		{
	        		Listitem row=new Listitem();
	        		
	        		//Celda de datos Personales
	        		Listcell celdaDatosPersonales=new Listcell();
	        		celdaDatosPersonales.setStyle("width:50%;");
	        		
	        		Hbox hBoxDatosPersonales= new Hbox();
	        		hBoxDatosPersonales.setSclass("resultForm");
	        		hBoxDatosPersonales.setStyle("line-height:0px;align:stretch;width:90%;");

	        		//creacion de objetos Imagen y Nombre
	        		//agrupo verticalmente nombre e imagen
	        		Vbox vBoxImageName=new Vbox();
	        		vBoxImageName.setStyle("line-height:0px;align:stretch;width:150px;");
	        		Image img=new Image();
	        		img.setSrc("/img/imgPerfil.jpg");
	        		String nombre=alumno.getLastname()+", "+alumno.getFirstname();
	        		Label lblnombre= new Label();
	        		lblnombre.setValue(nombre);
	        		lblnombre.setStyle("font-weight:bold;");
	        		
	        		//agrego la imagen y el nombre al vbox correspondiente
	        		img.setParent(vBoxImageName);
	        		lblnombre.setParent(vBoxImageName);
	        		vBoxImageName.setParent(hBoxDatosPersonales);
	        		
	        		//creacion de objetos para los demas datos personales
	        		//agrupo verticalmente el resto de los datos personales
	        		Vbox vBoxDatos=new Vbox();
	        		vBoxDatos.setStyle("line-height:0px;align:stretch;width:250px;");
	        		Label lblcarrera=new Label();
	        		String carrera="";
	        		carrera=getCarreraAlumno();
	        		lblcarrera.setValue("carrera: "+carrera);
	        		lblcarrera.setStyle("font-weight:bold;");
	        		
	        		Div divTelefono=new Div();
	        		Label lbltelefono1=new Label();
	        		
	        		String telefono="-";
	        		if(alumno.getPhone1()!=null)telefono=alumno.getPhone1();
	        		
	        		Label lbltelefono2=new Label();
	        		String telefono2="-";
	        		if(alumno.getPhone2()!=null)telefono2=alumno.getPhone2();
	        		lbltelefono1.setValue("Telefono1: "+telefono);
	        		lbltelefono1.setStyle("font-weight:bold;margin-right:20px;");
	        		lbltelefono2.setValue("Telefono2: "+telefono2);
	        		lbltelefono2.setStyle("font-weight:bold;");
	        		
	        		lbltelefono1.setParent(divTelefono);
	        		lbltelefono2.setParent(divTelefono);
	        		
	        		Label lblEmail=new Label();
	        		String email="-";
	        		if(alumno.getEmail()!=null)email=alumno.getEmail();
	        		lblEmail.setValue("E-mail: "+email);
	        		lblEmail.setStyle("font-weight:bold;");
	        		
	        		Div divContacto= new Div();
	        		Label lblcontactos=new Label();
	        		String contactos="";
	        		contactos=getContactosAlumno(alumno.getId());
	        		lblcontactos.setValue("Contacto: "+contactos);
	        		lblcontactos.setStyle("font-weight:bold;margin-right:20px;");
	        		
	        		Button btnNuevoContacto=new Button();
	        		btnNuevoContacto.setLabel("Nuevo Contacto");
	        		btnNuevoContacto.setAttribute("alumno", alumno);
	        		btnNuevoContacto.setSclass("tn btn-xs btn-info");
	        		btnNuevoContacto.addEventListener("onClick", new EventListener() {
	        		    public void onEvent(Event event) throws Exception {
	        		    	
	        		        abrirVentanDetalle((VwAlumnosActivos)event.getTarget().getAttribute("alumno"));
	        		    }
	        		});
	        		
	        		
	        		//agrego los objetos de datos personales a sus padres
	        		
	        		lblcarrera.setParent(vBoxDatos);
	        		divTelefono.setParent(vBoxDatos);
	        		lblEmail.setParent(vBoxDatos);
	        		lblcontactos.setParent(divContacto);
	        		btnNuevoContacto.setParent(divContacto);
	        		divContacto.setParent(vBoxDatos);
	        		vBoxDatos.setParent(hBoxDatosPersonales);
	        		hBoxDatosPersonales.setParent(celdaDatosPersonales);
	        		
	        		//Celda de Indicadores
	        		Listcell celdaIndicadores=new Listcell();
	        		celdaIndicadores.setStyle("width:50%;");
	        		Hbox hBoxIndicador=new Hbox();

	        		//hBoxIndicador.setSclass("resultForm");
	        		for(VwIndicadoresAlumnos indicador:indicadoresSet){
	                	
	        			Vbox vBoxIndicadorEspesifico=new Vbox();
	        			vBoxIndicadorEspesifico.setAlign("center");
	        			vBoxIndicadorEspesifico.setPack("center");
	        			Label lblindicador= new Label();
	        			lblindicador.setPre(true);
	        			lblindicador.setMaxlength(10);
	        			lblindicador.setValue(indicador.getDescIndicador());
	        			Image imgIndicador=new Image();
	        			if(indicador.getValorIndicador()==1)imgIndicador.setSrc("/img/green.jpg");
	        			else if(indicador.getValorIndicador()==2)imgIndicador.setSrc("/img/red.jpg");
	        			else imgIndicador.setSrc("/img/yellow.jpg");
	        			
	        			lblindicador.setParent(vBoxIndicadorEspesifico);
	        			imgIndicador.setParent(vBoxIndicadorEspesifico);
	        			vBoxIndicadorEspesifico.setParent(hBoxIndicador);
	            		
	            	}
	        		
	        		hBoxIndicador.setParent(celdaIndicadores);
	        		
	        		//agrego la celda a la fila
	        		celdaDatosPersonales.setParent(row);
	        		celdaIndicadores.setParent(row);
	        		row.setValue(alumno);
	        		row.setParent(lstBandejaCasos);
        		}
        		
        	}
        	
        	
        }
    } 
    
   
    public void abrirVentanDetalle(VwAlumnosActivos alumno) throws Exception
    {
    	
//    	if(poseeIndicadoresIrregulares(alumno))
//    	{
	        java.util.Properties params = new java.util.Properties();
	        params.put("alumno", alumno);
	        params.put("contactos", getContactosAlumno(alumno.getId()));
			Window win = (Window) Executions.createComponents("/celia/detalle_caso.zul", null,params);
			win.doModal();
//    	}
//    	else
//    	{
//    		Messagebox.show("El alumno no posee Indicadores Irregulares para mostrar.",
//					"Estado de Indicadores", Messagebox.OK,
//					Messagebox.INFORMATION);
//    	}
    }
    
    public boolean poseeIndicadoresIrregulares(VwAlumnosActivos alumno){
    	    	
    	for(VwIndicadoresAlumnos indicador:alumno.getIndicadoresAlumnos()){
    		if(indicador.getValorIndicador()==2){
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public String getCarreraAlumno(){
    	String carrera="-";
    	//TODO obtener este valor de una tabla que de deberá crear en el esquema seguimientos_alumno
    	
    	return carrera;
    }
    
    public String getContactosAlumno(Long aluId) throws Exception{
    	String contactos="-";
    	int cantidadContactos=celInteraccionCasoService.getCantidadContactos(aluId);
    	contactos=String.valueOf(cantidadContactos);
    	return contactos;
    }
    
    void openDetalle()
    {
		java.util.Properties params=new java.util.Properties ();
 		Window win = (Window) Executions.createComponents("/celia/detalle_caso.zul", null, params);
 		win.setPosition("center");
		win.doModal();	        
    }
    
   

}
