package ar.com.celia.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.output.ByteArrayOutputStream;

public class CompresorDescompresor {

	public static byte[] comprimir(byte[] array,String zipEntryName) throws Exception {
		ByteArrayOutputStream bos=new ByteArrayOutputStream(); 
		ZipOutputStream zout = new ZipOutputStream(bos);
		ZipEntry zipEntry=new ZipEntry(zipEntryName);
		zout.putNextEntry(zipEntry);
		zout.write(array);

		zout.flush();
		zout.close();
		bos.close();
		
		return bos.toByteArray();
	}
	
	public static byte[] descomprimir(byte[] array) throws Exception {
		ByteArrayOutputStream bos=new ByteArrayOutputStream(); 
		ByteArrayInputStream bis=new ByteArrayInputStream(array);
		ZipInputStream zin=new ZipInputStream(bis);
		
		ZipEntry entry;
        while((entry = zin.getNextEntry()) != null) {
        
        	int readed=-1;
        	while ((readed=zin.read())!= -1) {
        		bos.write(readed);
        	}
        	
        	bos.flush();
        	bos.close();
        }

        zin.close();
        bis.close();
        bos.flush();
		bos.close();
		
		return bos.toByteArray();
	}
	
}
