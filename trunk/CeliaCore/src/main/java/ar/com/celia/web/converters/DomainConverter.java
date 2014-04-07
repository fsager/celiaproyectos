package ar.com.celia.web.converters;

import java.lang.reflect.Method;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

import ar.com.celia.common.annotation.Domain;
import ar.com.celia.common.reflection.ReflectionUtil;

public class DomainConverter implements TypeConverter {

	
	public Object coerceToBean(java.lang.Object val,org.zkoss.zk.ui.Component comp){
		try
		{
			if(val!=null)
			{
				Domain domain=(Domain)comp.getAttribute("domain");
				String[] attributeToFilter={domain.attributeDominio(),domain.attributeMostrar()};
				//el valor es el valor a motrar
				Object[] valueToFilter={domain.domainValue(),val.toString()};
				return filtrar(val,comp,attributeToFilter,valueToFilter,domain.attributeClave(),true);
			}
			
			return null;
		}			
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
 
	private Object filtrar(Object val,Component comp,String[] attributeToFilter,Object[] valueToFilter,String attributoRetornar,boolean convert)
	{
		try
		{
			Domain domain=(Domain)comp.getAttribute("domain");
			List list=(List)ReflectionUtil.getCollectionWithFilters(domain.domainClass(),attributeToFilter,valueToFilter,domain.methodToRetrive(),false);
			
			if(list.size()<=0)
				return val;
			
			Object dominio=list.get(0);
			Method method=ReflectionUtil.getMethodFromFieldName(attributoRetornar,dominio.getClass());
			Object clave=method.invoke(dominio,null);

			if(convert)
			{
				clave=ReflectionUtil.convertTo(domain.convertTo(),clave);
			}
			
			return clave;
		}			
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}	
	}
	public Object coerceToUi(java.lang.Object val, org.zkoss.zk.ui.Component comp) {
		
		try
		{
			if(val!=null)
			{
				Domain domain=(Domain)comp.getAttribute("domain");
				String[] attributeToFilter={domain.attributeDominio(),domain.attributeClave()};
				//el valor es el valor clave
				Object[] valueToFilter={domain.domainValue(),val.toString()};
				return filtrar(val,comp,attributeToFilter,valueToFilter,domain.attributeMostrar(),false);
			}
			
			return null;
		}			
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}