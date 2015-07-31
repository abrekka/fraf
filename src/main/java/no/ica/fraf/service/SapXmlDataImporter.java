package no.ica.fraf.service;

import no.ica.fraf.FrafException;

public interface SapXmlDataImporter {

	public abstract void importXmlData() throws FrafException;

	public abstract boolean checkFiles()throws FrafException;

}