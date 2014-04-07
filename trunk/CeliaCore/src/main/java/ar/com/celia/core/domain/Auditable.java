package ar.com.celia.core.domain;

public interface Auditable {

	public void setAudFechaIns(java.util.Date date);

	public void setAudFechaUpd(java.util.Date date);
	
	public void setAudUsrIns(String stringUser);

	public void setAudUsrUpd(String stringUser);

	public java.util.Date getAudFechaIns();
	
	public java.util.Date getAudFechaUpd();
	
	public String getAudUsrIns();
	
	public String getAudUsrUpd();
	
	
}
