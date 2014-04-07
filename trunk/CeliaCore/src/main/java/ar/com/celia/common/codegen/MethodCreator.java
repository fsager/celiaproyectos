package ar.com.celia.common.codegen;

import java.util.List;



public class MethodCreator {
	
	
	public static void main(String[] args) {
		
		try {
			boolean addToPersistence=true;
			boolean addToService=true;
			
			//Proyecto donde estan las clases donde hay que agregar los metodos
			ClassPaths.proyecto="SeguimientoAlumnos/primary-source";
			
			//A partir de la clase de dominio y del package se identifican las demas clases (Servicce, Dao, Biz y Def)donde se
			//insertaran los metodos.
			///String clases="LabContrato";
			String[] listaClases={"LISTA_CLASES"};//,"LabFamilia","LabMarca","LabModelo","LabModeloCot","LabProvincia"
			
			String domain="ar.com.celia.seguimiento_alumnos";
			
			 
		    for(int i=0;i<listaClases.length; i++){
		    	
				String clases=listaClases[i];
				
				//Firma completa del método a agregar. Se recomienda indicar los packages a los que pertenecen los objetos
				//para evitar problemas de compilación
				String methodSing="public "+clases+" get(java.io.Serializable p_Id,String[] falseLazy) throws Exception";
				
		    	if (addToPersistence)
				{
					HibernateGenerator.addMethods(domain,clases,methodSing);
				}
				if (addToService)
				{
					BizAndServiceGenerator.addMethods(domain,clases,methodSing);
				}
		    }
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
