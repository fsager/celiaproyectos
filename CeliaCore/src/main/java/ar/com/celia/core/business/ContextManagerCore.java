package ar.com.celia.core.business;

import java.lang.reflect.Method;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;


public class ContextManagerCore implements java.io.Serializable {

	//private static final Log log = LogFactory.getLog(ContextManagerCore.class);
	
    private static ApplicationContext delegateStatic;
    public static final boolean debug = false;
    private static java.sql.Connection conn = null;
    private static java.sql.Connection connJDBCAppender = null;
    private static transient DelegatingVariableResolver delegate;

    
    static {
    	delegate = new org.zkoss.zkplus.spring.DelegatingVariableResolver();
    	try {
    		String ctxs[]=new String [2];
        	ctxs[0]="applicationContextCore.xml";
        	ctxs[1]="applicationContextCoreDB.xml";
        	delegateStatic = new ClassPathXmlApplicationContext(ctxs);
    	}
    	catch (Exception e) {
    		throw new RuntimeException("ContextManagerCore error al cargar applicationContextCore.xml",e);
    	}
    }
    

    public ContextManagerCore() {
        
    }
    
    
	public static Object getBizObject(String p_business_object) {
		try {
			Object business_object=delegate.resolveVariable(p_business_object);;
			
			if(business_object==null)
				business_object=delegateStatic.getBean(p_business_object);
			return business_object;
		} catch (Exception e) {
			if (debug)
				System.out
						.println("llame al metodo getBizObject en forma ESTATICA: "
								+ delegateStatic
								+ " bean: "
								+ p_business_object);
			return delegateStatic.getBean(p_business_object);
		}
	}
	
	/**
	 * Retorna la implementación de un objeto de negocio. Puede ser un objeto
	 * implementado por el servicio o una implementación Java.
	 * 
	 * @param p_business_object    Nombre del objeto de negocio que se desea obtener.
	 */
	public static Object getBizObjectApp(String p_business_object)
	{	
		return delegate.resolveVariable(p_business_object);
	}
	
	public static java.sql.Connection getCurrentConnection () throws SQLException
	{
		if (conn==null || conn.isClosed())
		{
			DataSource datasource =(DataSource) ContextManagerCore.getBizObject("dataSource");
			conn = datasource.getConnection();

		}
		return conn;
	}
	
	public static java.sql.Connection getConnectionForJDBCAppender () throws SQLException
	{
		if (connJDBCAppender==null || connJDBCAppender.isClosed())
		{
			DataSource datasource =(DataSource) ContextManagerCore.getBizObject("dataSourceJDBCAppender");
			connJDBCAppender = datasource.getConnection();			
		}
		
		return connJDBCAppender;
	}
	
	
	public static String getAppProperty (String p_clave)
	{
		Class ctx;
		try {
			ctx = Class.forName((String)getBizObject("contextManager"));
			Method met = ctx.getMethod("getProperty", String.class);
			String property =(String) met.invoke(ctx.newInstance(), p_clave);
			
			
	    	if (property == null)
	    		throw new Exception ("Propiedad "+p_clave+" no definida en el sistema");
	    	else
	    		return (property);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
