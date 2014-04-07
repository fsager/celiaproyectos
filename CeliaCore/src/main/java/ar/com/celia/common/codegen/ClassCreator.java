package ar.com.celia.common.codegen;


public class ClassCreator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			boolean doPersistence=true;
			boolean doService=true;
			boolean showSpring=true;
			
			boolean showSpringAllRelatedObjectsTogather=true;
			
			ClassPaths.proyecto="SeguimientoAlumnos/primary-source";

			String[] clases={"VwAlumnosActivos","VwIndicadoresAlumnos"};

			String domain="ar.com.celia.seguimiento_alumnos";
			if (doPersistence)
			{	
				for(int i=0;i<clases.length;i++)
				{
					HibernateGenerator.makeClass(domain,clases[i]);
				}
			}
			if (doService)
			{
				for(int i=0;i<clases.length;i++)
				{
					BizAndServiceGenerator.makeClass(domain,clases[i]);
				}
			}

			if (showSpring)
			{
				SpringXmlGenerator sxg=new SpringXmlGenerator();
				
				for(int i=0;i<clases.length;i++)
				{
					if(showSpringAllRelatedObjectsTogather)
						sxg.makeClassAllInOne(domain,clases[i]);
					else
						sxg.makeClass(domain,clases[i]);
						
				}
		        
				if(showSpringAllRelatedObjectsTogather)
					sxg.showClassAllInOne(System.out);
				else
					sxg.showClass(System.out);
				
			}
			
			for (int i = 0; i < clases.length; i++) {
				System.out.println("<mapping class=\"" + domain+".domain."+ clases[i] + "\" />");
			}

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
