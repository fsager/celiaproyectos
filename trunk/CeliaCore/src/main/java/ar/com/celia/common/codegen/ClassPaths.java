package ar.com.celia.common.codegen;

import java.io.File;

public class ClassPaths {
	
	public static String proyecto; 
	
	public static String getTemplatePath()
	{
		return ".\\templates\\";
	}
	
	public static String getPersistenceDaoPath(String root_package)
	{
			return getFile(root_package,"\\persistence\\");
	}
	
	public static String getPersistenceImplPath(String root_package)
	{
		return getFile(root_package,"\\persistence\\impl\\");
	}
	
	public static String getBusinessPath(String root_package)
	{
			return getFile(root_package,"\\business\\");
	}
	
	public static String getServicePath(String root_package)
	{
			return getFile(root_package,"\\service\\");
	}
	
	public static String getServiceImplPath(String root_package)
	{
			return getFile(root_package,"\\service\\impl\\");
	}
	
	private static String getFile(String root_package,String folderDestino)
	{
		try
		{
			root_package=root_package.replaceAll("\\.","\\\\");
			
			File f=new File("..\\"+proyecto+"\\src\\"+root_package+folderDestino);
			
			if(!f.exists())
				f.mkdirs();
			
			return root_package=f.getCanonicalPath()+File.separator;
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}


}
