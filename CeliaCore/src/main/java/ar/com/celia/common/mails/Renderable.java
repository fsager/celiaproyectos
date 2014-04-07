/*
 * Renderable.java
 *
 * Created on 10 November 2005, 10:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ar.com.celia.common.mails;

/**
 *
 * @author Dj
 */
public interface Renderable {
    public Attachment getAttachment(int i);

    public int getAttachmentCount();

    public String getBodytext();

    public String getSubject();
    
}
