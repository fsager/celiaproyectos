package ar.com.celia.core.persistence;

import ar.com.celia.core.domain.Auditable;

public interface Auditor {
	public void saveAuditoria(Auditable itemAuditable) throws Exception;
}
