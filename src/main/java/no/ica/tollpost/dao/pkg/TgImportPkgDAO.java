package no.ica.tollpost.dao.pkg;

import no.ica.fraf.FrafException;

public interface TgImportPkgDAO {
	void importer(Integer[] meldingIder,Integer userId) throws FrafException;
}
