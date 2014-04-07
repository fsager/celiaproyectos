package ar.com.celia.core.log;

import java.sql.PreparedStatement;

import org.apache.log4j.spi.LoggingEvent;

import ar.com.celia.common.utils.HashGenerator;

public class JDBCAppender extends org.apache.log4j.AppenderSkeleton{
	private static java.sql.Connection connection;
	
	@Override
	protected void append(LoggingEvent event) {
		java.io.StringWriter stackTrace=null;
		try
		{
			connection = ar.com.celia.core.business.ContextManagerCore.getConnectionForJDBCAppender();
			
			Throwable thr=null;
	    	stackTrace = new java.io.StringWriter();
	    	String className="";
	    	String cause="";
	    	String moreInfo="";
	    	
			if(event.getThrowableInformation()!=null && event.getThrowableInformation().getThrowable()!=null)
			{
				thr=event.getThrowableInformation().getThrowable();
				java.io.PrintWriter pw = new java.io.PrintWriter(stackTrace);
				thr.printStackTrace(pw);
				
				className=thr.getClass().toString();
				if(thr.getCause()!=null)
					cause=thr.getCause().getLocalizedMessage();
				
				pw.append(getMoreInfoMessage(thr));
			}
			
			if(event.getLocationInformation()!=null)
			{
				moreInfo="Class: "+event.getLocationInformation().getClassName()+", FileName"+event.getLocationInformation().getFileName()+
				",LineNumber: "+event.getLocationInformation().getLineNumber()+",MethodName "+event.getLocationInformation().getMethodName();
			}
			
			String hashId = HashGenerator.getHash(event.getMessage().toString());
			
	    	String sql="insert "+
				    	 "into celia_error "+
				    			"(err_id, "+
				    			"err_exception, "+
				    			"err_message, "+
				    			"err_extra_info, "+
				    			"err_cause, "+
				    			"err_stack_trace, "+
				    			"err_nombre_proyecto, "+
				    			"err_fecha_error," +
				    			"err_hash_id) "+
				    		  "values "+
				    			"(celia_error_id.nextval, "+
				    			"?," + //err_exception
				    			"?," + //err_message
				    			"?," + //err_extra_info
				    			"?," + //err_cause
				    			"?," + //err_stack_trace
				    			"?," + //err_nombre_proyecto
				    			"sysdate," + //err_fecha_error
				    			"?)"; // err_hash_id
	    	
	    	PreparedStatement st=connection.prepareStatement(sql);
	    	
	    	st.setString(1,className);
	    	st.setString(2,event.getMessage()!=null ? event.getMessage().toString() : null);
	    	st.setString(3,moreInfo);
	    	st.setString(4,cause);
	    	
	    	st.setString(5,stackTrace.toString());
	    	st.setString(6,this.getName());
	    	
	    	st.setString(7, event.getMessage() != null ? HashGenerator.getHash(event.getMessage().toString()) : null);
	    	
	    	st.execute();
	    	st.close();
	    	connection.close();
		}
		catch(Exception e)
		{
			System.out.println(stackTrace);
			throw new RuntimeException("Imposible registrar el error en la base de datos.",e);
		}
		
	}
	
	public static String getMoreInfoMessage(Throwable e)
	{
		String moreInfo=new String();
		if(e instanceof org.hibernate.JDBCException)
		{
			org.hibernate.JDBCException jdbcException=(org.hibernate.JDBCException)e;
			moreInfo+="SQL con Error: "+jdbcException.getSQL();
		}
		
    	Throwable cause=e.getCause();
    	while(cause!=null)
    	{
        	if(cause instanceof org.hibernate.JDBCException)
        	{
        		org.hibernate.JDBCException jdbcException=(org.hibernate.JDBCException)cause;
        		moreInfo+="SQL con Error: "+jdbcException.getSQL();
        	}

        	cause=cause.getCause();
    	}
    	
    	return moreInfo;
	}
	
	@Override
	public void close(){
		try
		{
			connection.close();
		}
		catch(Exception e)
		{
			//throw new RuntimeException(e);
		}
	}
	
	@Override
	public boolean requiresLayout() {
		return false;
	} 
	
   /* public JDBCAppender() {
        super();
    }
    
    protected Connection getConnection() {
        try {
            
        	connection = ar.com.celia.core.business.ContextManagerCore.getConnectionForJDBCAppender();
            return connection;
            
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
    }
    
    protected void closeConnection(Connection con) {
        try {
            //this.g
        	con.close();
        
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
    }
   
    @Override
    public synchronized void doAppend(LoggingEvent event) {
    	super.doAppend(event);
    }
    
    protected String getLogStatement(org.apache.log4j.spi.LoggingEvent event) {


    	
    	
    	
    	
    	sqlStatement=sql;
        return sqlStatement;
    }*/

   
}
