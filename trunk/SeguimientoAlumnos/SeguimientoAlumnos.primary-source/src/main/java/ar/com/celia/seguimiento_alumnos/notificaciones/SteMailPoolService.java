package ar.com.celia.seguimiento_alumnos.notificaciones;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;

import ar.com.celia.seguimiento_alumnos.domain.CelPropiedad;
import ar.com.celia.seguimiento_alumnos.service.CelPropiedadDefinition;

public class SteMailPoolService {
	
	private JavaMailSenderImpl mailSender;		
	
	public SteMailPoolService (JavaMailSenderImpl p_mailSender, CelPropiedadDefinition celPropiedadService) 
	{
		try{
			mailSender = p_mailSender;
			CelPropiedad example =new CelPropiedad ();
			example.setProTipo("MAIL.PROP");
			java.util.List<CelPropiedad> lstProps = celPropiedadService.getAll(example, null);
			
			java.util.Properties pr=new java.util.Properties();
			
			
//			pr.put("mail.smtp.port", "465");
//			pr.put("mail.smtp.starttls.enable","true");
//			pr.put("mail.smtp.socketFactory.port", "465");
			
			for (int i=0;i<lstProps.size();i++)
			{
				CelPropiedad prop = (CelPropiedad)lstProps.get(i);
				pr.put(prop.getProClave(), prop.getProValor());
			}
			if (pr.get("mail.smtp.auth")!=null && pr.get("mail.smtp.auth").equals("true"))
				mailSender.setSession(javax.mail.Session.getInstance(pr, new SmtpAuthenticator(pr.getProperty("mail.smtp.user"), pr.getProperty("mail.smtp.pass"))));
			else
				mailSender.setSession(javax.mail.Session.getInstance(pr));
			
			mailSender.setHost(""+pr.get("mail.host"));
			try {
					mailSender.setPort(Integer.parseInt(""+pr.get("mail.smtp.port")));
				}
			catch (Exception e) {
				mailSender.setPort(21);
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		
	}	

	
	public boolean enviarMail(final String to, final String from, final String replyTo, final String cc,
			final String bcc, final String subject, final String message) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				mimeMessage.setRecipients(Message.RecipientType.TO,
						getListFromAddress(to));
				if (cc != null)
					mimeMessage.setRecipients(Message.RecipientType.CC,	getListFromAddress(cc));
				if (bcc != null)
					mimeMessage.setRecipients(Message.RecipientType.BCC,getListFromAddress(bcc));

				mimeMessage.setFrom(new InternetAddress(from));
				mimeMessage.setSubject(subject);
				mimeMessage.setReplyTo(getListFromAddress(replyTo));

				MimeMultipart multipart = new MimeMultipart("related");
				BodyPart messageBodyPart = new MimeBodyPart();
				
				messageBodyPart.setContent(message, "text/html");
				multipart.addBodyPart(messageBodyPart);
				
				mimeMessage.setContent(multipart);
			}
		};

			this.mailSender.send(preparator);

		return true;
	}
	
	protected Address[] getListFromAddress(String p_address)
			throws AddressException {
		StringTokenizer st = new StringTokenizer(p_address, ";");
		Address add[] = new Address[st.countTokens()];
		int i = 0;
		while (st.hasMoreElements()) {
			add[i] = new InternetAddress(st.nextToken());
			i = i + 1;
		}

		return add;
	}
	
	public boolean sendMail(String to, String from, String replyTo, String cc,
			String bcc, String subject, String message, int prioridad) {

		return sendMail (to, from, cc, bcc, subject, message, null, prioridad);
	}


	public JavaMailSenderImpl getMailSender() {
		return mailSender;
	}


	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}
	
	

}