package ar.com.celia.common.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Id;

import ar.com.celia.common.annotation.ComplexType;
import ar.com.celia.common.annotation.ZkComponent;
import ar.com.celia.core.business.ContextManagerCore;
import ar.com.celia.core.domain.Auditable;

public class ReflectionUtil {
	public static Method getMethodFromField(Field field,Class clase) throws Exception
	{
		return getMethodFromFieldName(field.getName(),clase);
	}
	
	public static Object getValueFromField(Field field,Object instance) throws Exception
	{
		Method method=getMethodFromFieldName(field.getName(),instance.getClass());
		
		
		return method.invoke(instance); 
		
	}
	
	public static Field getIdField(Class clase) throws Exception
	{
		Field[] fields=clase.getDeclaredFields();
		for(Field field:fields)
		{
			Method method=getMethodFromField(field,clase);
			if(method.isAnnotationPresent(Id.class))
			{
				return field;
			}
		}
		
		return null;
	}
	
	public static Object getValueOfIdField(Object instancia) throws Exception
	{
		Method methods[]=instancia.getClass().getDeclaredMethods();
		for(Method method:methods)
		{
			if(method.isAnnotationPresent(Id.class))
			{
				Object obj=method.invoke(instancia,null);
				return obj;
			}
		}
		
		return null;
	}
	
	public static Method getMethodFromFieldName(String fieldName,Class clase) throws Exception
	{
		String fieldNameFirstLetter=fieldName.toUpperCase().substring(0,1);
		String fieldNameRest=fieldName.substring(1);
		String methodName= "get"+fieldNameFirstLetter+fieldNameRest;
		Method method=clase.getDeclaredMethod(methodName,null);
		
		return method;
	}
	
	public static Method getMethod(Class clase,String methodName,Class...parameter) throws Exception
	{
		Method method=null;
		try
		{
			method=clase.getDeclaredMethod(methodName,parameter);
		}
		catch(Exception e)
		{
			method=clase.getMethod(methodName,parameter);
		}
		
		return method;
	}
		
	public static Method getSetMethodFromField(Field field,Class clase,Class...classes) throws Exception
	{
		String fieldNameFirstLetter=field.getName().toUpperCase().substring(0,1);
		String fieldNameRest=field.getName().substring(1);
		String methodName= "set"+fieldNameFirstLetter+fieldNameRest;
		
		Method method=clase.getDeclaredMethod(methodName,classes);
		
		return method;
	}
	
	public static Method getSetMethodFromField(String field,Class clase,Class...classes) throws Exception
	{
		String fieldNameFirstLetter=field.toUpperCase().substring(0,1);
		String fieldNameRest=field.substring(1);
		String methodName= "set"+fieldNameFirstLetter+fieldNameRest;
		
		Method method=clase.getDeclaredMethod(methodName,classes);
		
		return method;
	}
	
	public static void setEmptyValuesToNull(Object objt) throws Exception
	{
    	Field fields[]=objt.getClass().getDeclaredFields();
		for(Field field:fields)
		{
			Method methodGet=ReflectionUtil.getMethodFromField(field,objt.getClass());
			Object labelObj=methodGet.invoke(objt, null);
			
			if(labelObj instanceof String)
			{
				if(labelObj.equals(""))
				{
					Method methodSet=ReflectionUtil.getSetMethodFromField(field,objt.getClass(),String.class);
					methodSet.invoke(objt,(String)null);
				}
			}
			
		}
	}
	
	public static void addComodinToStringValues(Object objt) throws Exception
	{
    	Field fields[]=objt.getClass().getDeclaredFields();
		for(Field field:fields)
		{
			Method methodGet=ReflectionUtil.getMethodFromField(field,objt.getClass());
			Object labelObj=methodGet.invoke(objt, null);
			
			if(labelObj instanceof String)
			{
				if(labelObj!=null)
				{
					Method methodSet=ReflectionUtil.getSetMethodFromField(field,objt.getClass(),String.class);
					methodSet.invoke(objt,"%"+labelObj+"%");
				}
			}
		}
	}
	
	public static String getReadableFieldName(Method method,Field field)
	{
		String fieldName=field.getName();
		
		try
		{
			if(method.isAnnotationPresent(ZkComponent.class))
			{
				ZkComponent zkComponent=method.getAnnotation(ZkComponent.class);
				if(!zkComponent.label().equals(""))
				{
					return zkComponent.label();
				}	
			}
			
			return fieldName.substring(3);	

		}
		catch(Exception e){
			return field.getName();
		}		
	}
	
	public static String getLabelAnnotationFromClass(Class clase)
	{
		try
		{
			if(clase.isAnnotationPresent(ZkComponent.class))
			{
				Annotation anno=clase.getAnnotation(ZkComponent.class);
				ZkComponent zkComponent=(ZkComponent)anno;
				if(!zkComponent.label().equals(""))
				{
					return zkComponent.label();
				}	
			}
			
			String fieldName=clase.getSimpleName();
			return fieldName.substring(3);
		}
		catch(Exception e){
			return clase.getSimpleName();
		}	
	}
	
	public static javax.persistence.Column getColumnAnnotation(Method method)
	{
		if(method.isAnnotationPresent(javax.persistence.Column.class))
		{
			javax.persistence.Column columns=method.getAnnotation(javax.persistence.Column.class);
			return columns;
		}

		return null;
	}
	
	public static javax.persistence.ManyToOne getManyToOneAnnotation(Method method)
	{
		if(method.isAnnotationPresent(javax.persistence.ManyToOne.class))
		{
			javax.persistence.ManyToOne manyToOne=method.getAnnotation(javax.persistence.ManyToOne.class);
			return manyToOne;
		}

		return null;
	}
	
	public static javax.persistence.JoinColumn getJoinColumnAnnotation(Method method)
	{
		if(method.isAnnotationPresent(javax.persistence.JoinColumn.class))
		{
				javax.persistence.JoinColumn joinColumn=method.getAnnotation(javax.persistence.JoinColumn.class);
				return joinColumn;
		}
		
		return null;
	}

	public static String getSpringBeanFromDomainClass(String domainName)
	{
		String springBean="";

		String packages[]=domainName.split("\\.");
		String className=packages[packages.length-1];
		
		String classNameFirstLetter=className.toLowerCase().substring(0,1);
		String classNameRest=className.substring(1);
		springBean= classNameFirstLetter+classNameRest+"Service";
		
		return springBean;
	}
	
	public static Collection getCollectionWithFilters(String clase,String[] attributeToFilter,Object[] valueToFilter,String method,boolean nulls)throws Exception
	{
		//Instancio un objeto de la clase que recibo por parametros
		Object obj=Class.forName(clase).newInstance();
		
		if(attributeToFilter!=null)
		{
			for(int i=0;i<attributeToFilter.length;i++)
			{
				//Obtengo el atributo donde hay que setar el valor de dominio por el cual vamos a filtrar
				Field field=obj.getClass().getDeclaredField(attributeToFilter[i]);
				//Obtengo el metodo para setear el valor del dominio a la istancia
				Method setMethod=ReflectionUtil.getSetMethodFromField(field,obj.getClass(),field.getType());
				setMethod.invoke(obj,valueToFilter[i]);				
			}
		}
		
		//Obtengo el listado de objecto con los filtros setados.
		Collection list=new ArrayList();
		if(nulls)
		{
			list.add(null);	
		}
		list.addAll(getCollectionBySpringBeanAndMethod(clase,method,obj));
		
		return list;
	}
	
	public static Collection getCollectionWithFilters(String clase,String attributeToFilter,Object valueToFilter,String method,boolean nulls)throws Exception
	{
		String[] attributeToFilterArr=null;
		Object[] valueToFilterArr=null;
		
		if(attributeToFilter!=null)
		{
			attributeToFilterArr=new String[1];
			attributeToFilterArr[0]=attributeToFilter;
			
			valueToFilterArr=new Object[1];
			valueToFilterArr[0]=valueToFilter;
		}

		
		return getCollectionWithFilters(clase,attributeToFilterArr,valueToFilterArr,method,nulls);
	}
	
	public static String[] getAllDependencies(Class className) throws Exception
	{
		String dependencies=new String();
		Method methods[]=className.getDeclaredMethods();
		for(Method method:methods)
		{
			if(method.isAnnotationPresent(javax.persistence.ManyToOne.class))
			{
				Class returnClass=(Class)method.getGenericReturnType();
				//String claseSimpleName=returnClass.getSimpleName();
				String claseSimpleName=method.getName();
				claseSimpleName=claseSimpleName.replaceAll("get","");
				String fieldNameFirstLetter=claseSimpleName.toLowerCase().substring(0,1);
				String fieldNameRest=claseSimpleName.substring(1);
				dependencies+=fieldNameFirstLetter+fieldNameRest+",";				
			}
		}
		
		return dependencies.split(",");
	}
	
	public static boolean implementsInterface(Class clase,Class interfazToAnalice)
	{
		Class interfaces[]=clase.getInterfaces();
		for(Class interfaz:interfaces)
		{
			if(interfaz.equals(interfazToAnalice))
				return true;
		}
		
		return false;
	}
	
	public static Collection getCollectionBySpringBeanAndMethod(String className,String method,Object instancia) throws Exception
	{
		if(method==null)
			method="getAll";
		
		String springBean=ReflectionUtil.getSpringBeanFromDomainClass(className);
		Object springBeanService=ContextManagerCore.getBizObjectApp(springBean);
		boolean lazys=true;
		
		String[] falseLazy=getAllDependencies(instancia.getClass());
		if(falseLazy==null)
			falseLazy=new String[0];
		Method mt=null;
		
		try
		{
			mt=ReflectionUtil.getMethod(springBeanService.getClass(),method,instancia.getClass(),falseLazy.getClass());
		}catch(java.lang.NoSuchMethodException e)
		{
			lazys=false;
			mt=ReflectionUtil.getMethod(springBeanService.getClass(),method,instancia.getClass());
		}
		
		Collection lista=null;
		if(lazys)
			lista =(Collection)mt.invoke(springBeanService,instancia,falseLazy);
		else
			lista =(Collection)mt.invoke(springBeanService,instancia);
		
		return lista;
	}
	
	public static Object convertTo(String classTo,Object objectFrom)
	{
		try
		{
			Method mt=Class.forName(classTo).getDeclaredMethod("valueOf",objectFrom.getClass());
			return mt.invoke(null, objectFrom);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
