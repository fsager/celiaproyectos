/*
 * RenderableMessage.java
 *
 * Created on 09 November 2005, 10:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ar.com.celia.common.mails;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;

/**
 *
 * @author Dj
 */
public class RenderableMessage implements Renderable {
    
    private String subject;
    private String bodytext;
    ArrayList<Attachment> attachments;
    private Message message;
    
    /** Creates a new instance of RenderableMessage */
    public RenderableMessage(Message m) throws MessagingException,IOException {
        this.message=m;
    	subject=m.getSubject();//.substring("MailPage:".length());
        attachments=new ArrayList<Attachment>();
        extractPart(m);
    }
    
    private void extractPart(final Part part) throws MessagingException, IOException {
        if(part.getContent() instanceof Multipart) {
            Multipart mp=(Multipart)part.getContent();
            for(int i=0;i<mp.getCount();i++) {
                extractPart(mp.getBodyPart(i));
            }
            return;
        }
        
        if(part.getContentType().toLowerCase().startsWith("text/html")) {
            if(bodytext==null) {
                bodytext=(String)part.getContent();
            } else {
                bodytext=bodytext+"<HR/>"+(String)part.getContent();
            }
        } else if(!part.getContentType().toLowerCase().startsWith("text/plain")) {
            Attachment attachment=new Attachment();
            attachment.setContenttype(part.getContentType());
            attachment.setFilename(part.getFileName());
             
            InputStream in=part.getInputStream();
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
           
            byte[] buffer=new byte[8192];
            int count=0;
            while((count=in.read(buffer))>=0) bos.write(buffer,0,count);
            in.close();
            attachment.setContent(bos.toByteArray());
            attachments.add(attachment);
            
        }
    }
    
    public String getSubject() {
        return subject;
    }
    
    public String getBodytext() {
        return bodytext;
    }
    
    public int getAttachmentCount() {
        if(attachments==null) return 0;
        return attachments.size();
    }
    
    public Attachment getAttachment(int i) {
        return attachments.get(i);
    }
    
    public Message getMessage() {
		return message;
	}
}
