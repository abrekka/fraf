package no.ica.tollpost.service.impl;

import no.ica.elfa.dao.FileSequenceDAO;
import no.ica.elfa.service.FileSequenceManager;

public class TgFileSequenceManagerImpl implements FileSequenceManager{
private FileSequenceDAO dao;
	
	public void setFileSequenceDAO(FileSequenceDAO dao) {
        this.dao = dao;
    }
	public Integer getNextNumber() {
		return dao.getNextNumber();
	}
	
}
