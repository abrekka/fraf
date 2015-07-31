package no.ica.tollpost.dao.pkg;

import no.ica.fraf.FrafException;

public interface TgFakturaPkgDAO {
	/**
	 * Setter om database er test
	 * 
	 * @param test
	 */
	void setTest(boolean test);

	/**
	 * Henter om database er test
	 * 
	 * @return true dersom test
	 */
	void fakturer(Integer buntId)throws FrafException;
	public boolean getTest();
	//void settBuntBokfort(Integer buntId)throws FrafException;
}
