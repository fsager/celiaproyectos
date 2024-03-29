package ar.com.celia.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.mail.internet.MimeUtility;

public class Base64Encoder{
	
    public static byte[] encode(byte[] b) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream b64os = MimeUtility.encode(baos, "base64");
        b64os.write(b);
        b64os.close();
        return baos.toByteArray();
     }
     
     public static byte[] decode(byte[] b) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        InputStream b64is = MimeUtility.decode(bais, "base64");
        byte[] tmp = new byte[b.length];
        int n = b64is.read(tmp);
        byte[] res = new byte[n];
        System.arraycopy(tmp, 0, res, 0, n);
        return res;
     }
     
     public static String encrypt(String toEncrypt) throws Exception {
    	 byte res1[] = encode(toEncrypt.getBytes("UTF-8"));    	 
         return new String(res1);
     }
     
     public static String decrypt(String toDecrypt) throws Exception {
    	 byte res1[] = decode(toDecrypt.getBytes("UTF-8"));    	 
         return new String(res1);
     } 

}
