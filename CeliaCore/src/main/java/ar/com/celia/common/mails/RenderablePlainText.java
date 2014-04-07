/*
 * RenderablePlainText.java
 *
 * Created on 10 November 2005, 10:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ar.com.celia.common.mails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.mail.Message;
import javax.mail.MessagingException;

/**
 *
 * @author Dj
 */
public class RenderablePlainText implements Renderable {
    
    String bodytext;
    String subject;
    
    /** Creates a new instance of RenderablePlainText */
    public RenderablePlainText(Message message) throws MessagingException, IOException {
        subject=message.getSubject().substring("MailPage:".length());
        bodytext=(String)message.getContent();
    }
    
    public Attachment getAttachment(int i) {
        return null;
    }
    
    public int getAttachmentCount() {
        return 0;
    }
    
    public String getBodytext() {
        return "<PRE>"+bodytext+"</PRE>";
    }
    
    public String getSubject() {
        return subject;
    }
    
}
