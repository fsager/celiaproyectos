package ar.com.celia.common.codegen;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BizAndServiceGenerator {
	//public static String p_temp_path=".\\templates\\";
	//public static String p_path_biz=".\\builds\\business\\";
	//public static String p_path_srv=".\\builds\\service\\";
	//public static String p_path_srv_impl=".\\builds\\service\\impl\\";
	
	public static void makeClass (String root_package, String className) throws Exception
	{	
		makeServiceDefinition (root_package, className);
		makeServiceImpl (root_package, className);
		makeBusiness (root_package, className);
	}
	
	private static void makeServiceDefinition (String root_package, String className) throws Exception
	{
		FileInputStream fis=new FileInputStream (ClassPaths.getTemplatePath()+"ServiceDefinition.txt");
		int cant=fis.available();
		byte byt[]=new byte [cant];
		fis.read(byt);
		fis.close();
		String template=new String (byt);
		template=template.replaceAll("-##root_package##-", root_package);
		template=template.replaceAll("-##ClassName##-", className);
		
		FileOutputStream fos=new FileOutputStream (ClassPaths.getServicePath(root_package)+className+"Definition.java");
		fos.write (template.getBytes());
		fos.close();
	}
	
	private static void makeServiceImpl (String root_package, String className) throws Exception
	{
		FileInputStream fis=new FileInputStream (ClassPaths.getTemplatePath()+"ServiceImpl.txt");
		int cant=fis.available();
		byte byt[]=new byte [cant];
		fis.read(byt);
		fis.close();
		String template=new String (byt);
		template=template.replaceAll("-##root_package##-", root_package);
		template=template.replaceAll("-##ClassName##-", className);
		
		FileOutputStream fos=new FileOutputStream (ClassPaths.getServiceImplPath(root_package)+className+"Service.java");
		fos.write (template.getBytes());
		fos.close();
	}
	
	private static void makeBusiness (String root_package, String className) throws Exception
	{
		FileInputStream fis=new FileInputStream (ClassPaths.getTemplatePath()+"BusinessObject.txt");
		int cant=fis.available();
		byte byt[]=new byte [cant];
		fis.read(byt);
		fis.close();
		String template=new String (byt);
		template=template.replaceAll("-##root_package##-", root_package);
		template=template.replaceAll("-##ClassName##-", className);
		
		FileOutputStream fos=new FileOutputStream (ClassPaths.getBusinessPath(root_package)+className+"Biz.java");
		fos.write (template.getBytes());
		fos.close();
	}
	

	public static void addMethods (String root_package, String className,String methodSing) throws Exception
	{	
		addMethodBusiness (root_package,className,methodSing);
		addMethodServiceImpl (root_package,className,methodSing);
		addMethodServiceDefinition  (root_package,className,methodSing);
	}
	
	private static void addMethodBusiness (String root_package, String className,String methodSing) throws Exception
	{
		FileInputStream fis=new FileInputStream (ClassPaths.getBusinessPath(root_package)+className+"Biz.java");
		int cant=fis.available();
		byte byt[]=new byte [cant];
		fis.read(byt);
		fis.close();
		String template=new String (byt);
		int pos=template.lastIndexOf("}");
		
		template=template.substring(0,pos);
		String returns="";
		
		if(returnMethod(methodSing))
			returns="return ";
		
		String inputParams=getInputParams(methodSing);
		
		String method=	"\n\t"+methodSing+"{\n"+
						"\t\t"+returns+"dao."+getMethodName(methodSing)+"("+inputParams+");\n"+
						"\t}\n";
		
		template+=method+"\n}";
		
		
		FileOutputStream fos=new FileOutputStream (ClassPaths.getBusinessPath(root_package)+className+"Biz.java");
		fos.write (template.getBytes());
		fos.close();
	}
	
	private static void addMethodServiceDefinition (String root_package, String className,String methodSing) throws Exception
	{
		FileInputStream fis=new FileInputStream (ClassPaths.getServicePath(root_package)+className+"Definition.java");
		int cant=fis.available();
		byte byt[]=new byte [cant];
		fis.read(byt);
		fis.close();
		String template=new String (byt);
		int pos=template.lastIndexOf("}");
		
		template=template.substring(0,pos);
		
		String method=	"\n\t"+methodSing+";";
		
		template+=method+"\n}";
		
		
		FileOutputStream fos=new FileOutputStream (ClassPaths.getServicePath(root_package)+className+"Definition.java");
		fos.write (template.getBytes());
		fos.close();
	}
	
	private static void addMethodServiceImpl (String root_package, String className,String methodSing) throws Exception
	{
		FileInputStream fis=new FileInputStream (ClassPaths.getServiceImplPath(root_package)+className+"Service.java");
		int cant=fis.available();
		byte byt[]=new byte [cant];
		fis.read(byt);
		fis.close();
		String template=new String (byt);
		int pos=template.lastIndexOf("}");
		
		template=template.substring(0,pos);
		String returns="";
		
		if(returnMethod(methodSing))
			returns="return ";
		
		String inputParams=getInputParams(methodSing);
		
		String method=	"\n\t"+methodSing+"{\n"+
						"\t\t"+returns+"biz."+getMethodName(methodSing)+"("+inputParams+");\n"+
						"\t}\n";
		
		template+=method+"\n}";
		
		FileOutputStream fos=new FileOutputStream (ClassPaths.getServiceImplPath(root_package)+className+"Service.java");
		fos.write (template.getBytes());
		fos.close();
	}

	private static boolean returnMethod(String methodSing)
	{
		return !methodSing.contains("void");
	}
	
	private static String getInputParams(String methodSing)
	{
		String inputParams=new String();
		int posApertura=methodSing.indexOf("(");
		int posCierre=methodSing.indexOf(")");
		
		inputParams=methodSing.substring(posApertura+1,posCierre);
		inputParams=inputParams.trim();
		String imputs[]=inputParams.split(",");
		
		inputParams=new String();
		for(int i=0;i<imputs.length;i++)
		{
			String[] input=imputs[i].split(" ");
			
			for(int j=0;j<input.length;j++)
			{
				if(input[j].matches("(?u)[a-z].*") && !input[j].contains("."))
					inputParams+=input[j]+",";
			}
		}
		
		if(inputParams.length()>1)
		inputParams=inputParams.substring(0,inputParams.length()-1);
		
		return inputParams;
	}
	
	private static String getMethodName(String methodSing)
	{
		String inputParams=new String();
		int posApertura=methodSing.indexOf("(");		
		inputParams=methodSing.substring(0,posApertura);
		inputParams=inputParams.trim();
		inputParams=inputParams.substring(inputParams.lastIndexOf(" "));
		inputParams=inputParams.trim();
		
		return inputParams;
	}
	
	public static void main(String args[]) {
		
	}
}
