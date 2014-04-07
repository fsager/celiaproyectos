package ar.com.celia.common.mails;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

public class ImapMailDownloader {

	private String host;
	private String user;
	private String password;
	private Properties javaMailProperties=new Properties();

	public RenderableMessage[] getNewErrorMessages() throws Exception
	{
		// Get system properties
		//Properties properties = System.getProperties();
		
		
		Enumeration enus=javaMailProperties.propertyNames();
		
		while(enus.hasMoreElements())
		{
			String pro=(String)enus.nextElement();
			System.out.println(pro+" = "+javaMailProperties.getProperty(pro));
		}

		// Get the default Session object.
		Session session = Session.getDefaultInstance(javaMailProperties);
		session.setDebug(true);
		session.setDebugOut(System.out);
		
		// Get a Store object that implements the specified protocol.
		Store store = session.getStore("imap");

		// Connect to the current host using the specified username and
		// password.
		store.connect(host, user, password);
		//store.connect();
		// Create a Folder object corresponding to the given name.
		Folder folder = store.getFolder("inbox");

		// Open the Folder.
		folder.open(Folder.READ_WRITE);
		
		SearchTerm st = 
			  new AndTerm(
			    new SubjectTerm("Errores en"), 
			    new FlagTerm(new Flags(Flags.Flag.SEEN),false));
		
		// Get the messages from the server
		int maxSize = folder.getMessageCount();

		Message[] messages = folder.search(st);
		
        /*FetchProfile fp=new FetchProfile();
        fp.add(FetchProfile.Item.CONTENT_INFO);
        fp.add(FetchProfile.Item.ENVELOPE);
       	folder.fetch(messages,fp);*/

		RenderableMessage[] renderableMessage=new RenderableMessage[messages.length];
		for(int i=0;i<messages.length;i++)
		{
			RenderableMessage rm = new RenderableMessage(messages[i]);
			renderableMessage[i]=rm;
		}
		
		folder.close(true);
		store.close();
		
		return renderableMessage;
	}
	
	
	public static void main(String args[]) throws Exception {
		
		Properties properties = System.getProperties();
	
		ImapMailDownloader imapMailDownloader=new ImapMailDownloader();
		imapMailDownloader.setHost("imap.gmail.com");
		imapMailDownloader.setUser("errores@.com.ar");//TODO definir dirección de correo electrónico.
		imapMailDownloader.setPassword("garoto.cacao");
		
		Properties pro=new Properties();
		pro.put("mail.debug", "true");
		pro.put("mail.imap.port", "993");
		imapMailDownloader.setJavaMailProperties(pro);
		

		Renderable[] rds=imapMailDownloader.getNewErrorMessages();
		
		for(Renderable rd:rds)
		{
			System.out.println(rd.getSubject());
		}

	}


	public static void getMultiPartMessage(StringBuffer sb, javax.mail.internet.MimeMultipart mimeMultiPart) throws MessagingException, IOException
	{
		for (int i=0;i<mimeMultiPart.getCount();i++)
		{
			if (mimeMultiPart.getBodyPart(i).getContent() instanceof com.sun.mail.util.BASE64DecoderStream)
			{
				
			}
			if (mimeMultiPart.getBodyPart(i).getContent() instanceof Multipart)
			{
				Multipart multipart = (Multipart) mimeMultiPart.getBodyPart(i).getContent();
	            //System.out.println(multipart.getCount());
	
	            for (int si = 0; si < multipart.getCount(); si++) {
	                //System.out.println(i);
	                //System.out.println(multipart.getContentType());
	                BodyPart bodyPart = multipart.getBodyPart(si);
	                java.io.InputStream stream = bodyPart.getInputStream();
	                java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(stream));
	
	                while (br.ready()) {
	                    sb.append(br.readLine());
	                }
	            }
			}
		}
	}
	
	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	public Properties getJavaMailProperties() {
		return javaMailProperties;
	}
	
	public void setJavaMailProperties(Properties javaMailProperties) {
		this.javaMailProperties.putAll(javaMailProperties);
	}
}
