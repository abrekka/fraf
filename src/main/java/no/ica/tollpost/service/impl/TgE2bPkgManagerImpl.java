package no.ica.tollpost.service.impl;

import no.ica.elfa.dao.pkg.E2bPkgDAO;
import no.ica.elfa.service.E2bPkgManager;
import no.ica.fraf.FrafException;
import no.ica.fraf.xml.E2bParamEnum;

public class TgE2bPkgManagerImpl implements E2bPkgManager {
	private E2bPkgDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setE2bPkgDAO(E2bPkgDAO dao) {
		this.dao = dao;
	}

	public Integer getSequenceNumber() throws FrafException{
		return dao.getSequenceNumber();
	}

	public String getFilename() throws FrafException {
		return "Tg" + getSequenceNumber() + "."+ E2bParamEnum.E2B_FILE_SUFFIX_TG.getParamValue();
	}


}
