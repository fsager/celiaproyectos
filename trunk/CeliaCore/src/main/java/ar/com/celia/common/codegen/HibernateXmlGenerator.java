package ar.com.celia.common.codegen;

import java.io.PrintStream;

public class HibernateXmlGenerator {

	static StringBuffer sb_dao=new StringBuffer();;

	public HibernateXmlGenerator ()
	{
	}
	
	public void makeClass (String root_package, String className) throws Exception
	{
		sb_dao.append("		<mapping resource=\"ar/com/binside/common/persistence/"+className+".hbm.xml\" />\r\n");
	}
	
	public void showClass (PrintStream os)
	{
		os.println (sb_dao.toString());
	}
}
