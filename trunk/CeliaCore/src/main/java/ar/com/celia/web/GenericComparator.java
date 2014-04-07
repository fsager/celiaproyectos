package ar.com.celia.web;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

import org.zkoss.zul.Listitem;

public class GenericComparator implements Comparator{
    private boolean asc=false;
    private String attribute;
    
	public GenericComparator(boolean asc,String attribute)
    {
		this.asc=asc;		
		
		this.attribute=attribute;
    }

    public int compare(Object o1, Object o2)
    {
    	int value=-1;
    	//Obtengo el value del Lisitem desde branch
    	Object value1=((Listitem)o1).getValue(); 
    	Object value2=((Listitem)o2).getValue();
    	
    	value1=getObject(value1);
    	value2=getObject(value2);
    	
    	if(value1==null)
    		return 1;//Los null al ultimo
    	else if(value2==null)
    		return -1;//Los null al ultimo
    	
    	if(value1 instanceof java.util.Date)
    		value=compareDate(value1,value2);
    	else if(value1 instanceof java.lang.String)
    		value=compareString(value1,value2);
    	else if(value1 instanceof java.lang.Number)
    		value=compareInteger(value1,value2);
    	else
    		throw new RuntimeException("Clase :"+value1.getClass()+" no implementada en el comparador.");
    	
    	return asc ? value: -value;
    }
    
    private int compareDate(Object o1, Object o2)
    {
    	Date date1=(Date)o1; 
    	Date date2=(Date)o2;
    	
    	return date1.compareTo(date2);
    }
    
    private int compareString(Object o1, Object o2)
    {
    	String str1=(String)o1; 
    	String str2=(String)o2;
    	
    	return str1.compareTo(str2);
    }
    
    private int compareInteger(Object o1, Object o2)
    {
    	Number nro1=(Number)o1; 
    	Number nro2=(Number)o2;
    	
    	BigDecimal big1=new BigDecimal(nro1.longValue());
    	BigDecimal big2=new BigDecimal(nro2.longValue());
    	
    	return big1.compareTo(big2);
    }
    
    public Object getObject(Object o) 
    {
    	try
    	{
    		Object value1=o;
    		String[] attributes=attribute.split("\\.");
    		
    		if(attributes.length<1)
    		{
    			attributes=new String[1];
    			attributes[0]=attribute;
    		}
    		
    		for(String atr:attributes)
    		{
        		String fistLeter=atr.substring(0,1).toUpperCase();
        		String restOfString=atr.substring(1,atr.length());
        		
        		Method method=value1.getClass().getMethod("get"+fistLeter+restOfString, null);
        		value1=method.invoke(value1,null);
    		}
    		
    		return value1;
    		
    	}
    	catch (Exception e) {
    		throw new RuntimeException(e);
		}
    }
}

