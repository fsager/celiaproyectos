package ar.com.celia.core.mbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectName;


public class MBeanServerReader {
	private MBeanServer mBeanServer;
	
	public List<VersionInfo> getVersionBeans() throws Exception
	{	
		/*System.out.println("getVersionBeans: getVersionBeans : getVersionBeans : getVersionBeans");
		
		String domains[]= mBeanServer.getDomains();
		for(String domain: domains)
		{
			System.out.println("domain: "+domain);
		}

		*/
		List appVersion=new ArrayList();
		ObjectName objectNameExample = new ObjectName("VersionInfo.*:name=*"); 
		Set<ObjectName> aplicacionesConVersiones = mBeanServer.queryNames(objectNameExample,null);
		for(ObjectName objectName: aplicacionesConVersiones)
		{
			String version=(String)mBeanServer.getAttribute(objectName,"Version");
			
			VersionInfo vi=new VersionInfo(version);
			vi.setAplicacion(objectName.getKeyProperty("name"));
						
			String parentAplicacion=objectName.getDomain().replaceAll("VersionInfo.", "");
			vi.setParentAplicaciont(parentAplicacion);
			
			appVersion.add(vi);
		}

		return appVersion;
	}
	
	public void setmBeanServer(MBeanServer mBeanServer) {
		this.mBeanServer = mBeanServer;
	}
	
	public MBeanServer getmBeanServer() {
		return mBeanServer;
	}
}
