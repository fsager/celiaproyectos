package ar.com.celia.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {

	public static String getHash(String message) throws NoSuchAlgorithmException{
		
		String hash = "";
		
		byte[] digest = null;
		byte[] buffer = message.getBytes();
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(buffer);
        digest = md.digest();
        hash = toHex(digest);
		
		return hash;
	}
	
	private static String toHex(byte[] digest){
		String hash = "";
		for (byte aux : digest) {
			int b = aux & 0xff; // Cast the byte to hex
				
			if (Integer.toHexString(b).length() == 1){
				hash += "0";
			}else{
				hash += Integer.toHexString(b);
			}
		}
		
		return hash;
	}
}