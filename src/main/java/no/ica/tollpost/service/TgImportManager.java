package no.ica.tollpost.service;

import java.util.List;

import no.ica.fraf.common.LineManager;
import no.ica.fraf.model.Bunt;
import no.ica.tollpost.gui.TgImportTypeEnum;
import no.ica.tollpost.model.TgImport;

public interface TgImportManager extends LineManager{
	List<TgImport> findByBuntAndType(final Bunt bunt,final TgImportTypeEnum importType);
	List<TgImport> findWithoutAvdnrByBuntId(Integer buntId);
	void saveTgImport(TgImport tgImport);
	List<TgImport> findBySendNrAndKolliId(String sendNr,String kolliId);
	void lazyLoadBunt(TgImport tgImport);
}
