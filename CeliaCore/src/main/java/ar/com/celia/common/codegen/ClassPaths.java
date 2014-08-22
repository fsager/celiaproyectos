package ar.com.celia.common.codegen;

import java.io.File;

public class ClassPaths {
	
	public static String proyecto; 
	
	public static String getTemplatePath()
	{
		return "."+File.separator+"templates"+File.separator;
	}
	
	public static String getPersistenceDaoPath(String root_package)
	{
			return getFile(root_package,File.separator+"persistence"+File.separator);
	}
	
	public static String getPersistenceImplPath(String root_package)
	{
		return getFile(root_package,File.separator+"persistence"+File.separator+"impl"+File.separator);
	}
	
	public static String getBusinessPath(String root_package)
	{
			return getFile(root_package,File.separator+"business"+File.separator);
	}
	
	public static String getServicePath(String root_package)
	{
			return getFile(root_package,File.separator+"service"+File.separator);
	}
	
	public static String getServiceImplPath(String root_package)
	{
			return getFile(root_package,File.separator+"service"+File.separator+"impl"+File.separator);
	}
	
	private static String getFile(String root_package,String folderDestino)
	{
		try
		{			
			File f=new File(".."+File.separator+proyecto+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+root_package+folderDestino);
			
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
