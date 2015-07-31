package no.ica.elfa.service.impl;

import no.ica.elfa.dao.pkg.E2bPkgDAO;
import no.ica.elfa.service.E2bPkgManager;
import no.ica.fraf.FrafException;
import no.ica.fraf.xml.E2bParamEnum;

/**
 * Implementasjon av manager mot pakke E2B_PKG
 * 
 * @author abr99
 * 
 */
public class E2bPkgManagerImpl implements E2bPkgManager {
	/**
	 * 
	 */
	private E2bPkgDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setE2bPkgDAO(E2bPkgDAO dao) {
		this.dao = dao;
	}

	/**
	 * @see no.ica.elfa.service.E2bPkgManager#getSequenceNumber()
	 */
	public Integer getSequenceNumber() throws FrafException {
		return dao.getSequenceNumber();
	}

	/**
	 * @see no.ica.elfa.service.E2bPkgManager#getFilename(java.lang.String)
	 */
	public String getFilename() throws FrafException {
		return "Elfa" + getSequenceNumber() + "."
				+ E2bParamEnum.E2B_FILE_SUFFIX.getParamValue();
	}

}
