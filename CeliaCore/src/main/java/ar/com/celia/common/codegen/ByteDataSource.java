package ar.com.celia.common.codegen;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ByteDataSource implements javax.activation.DataSource {
	protected String contentType;
	protected String name;
	protected ByteArrayInputStream bais;
		
	public ByteDataSource (byte byts[])
	{
		bais=new ByteArrayInputStream(byts);
	}
	
	public InputStream getInputStream() throws IOException {
		return bais;
	}

	public OutputStream getOutputStream() throws IOException {
		return null;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
