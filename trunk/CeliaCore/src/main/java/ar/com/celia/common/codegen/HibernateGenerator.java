package ar.com.celia.common.codegen;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

public class HibernateGenerator {


	public static void makeClass (String root_package, String className) throws Exception {
		makeDao (root_package, className);
		makeImpl (root_package, className);
	}
	
	private static void makeDao (String root_package, String className) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		String lineSeparator = System.getProperty("line.separator");
		Scanner scanner = new Scanner(new FileInputStream(ClassPaths.getTemplatePath()+"GenericDao.txt"));
		try {
			while (scanner.hasNextLine()) {
				stringBuilder.append(scanner.nextLine() + lineSeparator);
			}
		}
		finally {
			//Cerramos el FileInputStream y el Scanner.
			scanner.close();
		}
		
		String template = stringBuilder.toString();
		template = template.replaceAll("-##root_package##-", root_package);
		template = template.replaceAll("-##ClassName##-", className);
		FileOutputStream fos = new FileOutputStream (ClassPaths.getPersistenceDaoPath(root_package)+className+"Dao.java");
		
		Writer out = new OutputStreamWriter(fos, "ISO-8859-1");
		try {
			out.write(template);
		}
		finally {
			//Cerramos el FileOutputStream.
		    out.close();
		}
	}
	
	
	private static void makeImpl (String root_package, String className) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		String lineSeparator = System.getProperty("line.separator");
		Scanner scanner = new Scanner(new FileInputStream(ClassPaths.getTemplatePath()+"HibernateHome.txt"));
		try {
			while (scanner.hasNextLine()) {
				stringBuilder.append(scanner.nextLine() + lineSeparator);
			}
		}
		finally {
			//Cerramos el FileInputStream y el Scanner.
			scanner.close();
		}
		
		String template = stringBuilder.toString();
		template = template.replaceAll("-##root_package##-", root_package);
		template = template.replaceAll("-##ClassName##-", className);
		FileOutputStream fos = new FileOutputStream (ClassPaths.getPersistenceImplPath(root_package)+className+"Home.java");
		
		Writer out = new OutputStreamWriter(fos, "ISO-8859-1");
		try {
			out.write(template);
		}
		finally {
			//Cerramos el FileOutputStream.
		    out.close();
		}
	}
	
	
	public static void addMethods (String root_package, String className, String methodSing) throws Exception {	
		addMethodDao (root_package,className,methodSing);
	}
	
	
	private static void addMethodDao (String root_package, String className,String methodSing) throws Exception	{
		FileInputStream fis=new FileInputStream (ClassPaths.getPersistenceDaoPath(root_package)+className+"Dao.java");
		int cant=fis.available();
		byte byt[]=new byte [cant];
		fis.read(byt);
		fis.close();
		String template=new String (byt);
		int pos=template.lastIndexOf("}");
		
		template=template.substring(0,pos);
		
		String method=	"\n\t"+methodSing+";";
		
		template+=method+"\n}";
		
		FileOutputStream fos=new FileOutputStream (ClassPaths.getPersistenceDaoPath(root_package)+className+"Dao.java");
		fos.write (template.getBytes());
		fos.close();
	}
}
