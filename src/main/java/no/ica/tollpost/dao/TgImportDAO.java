package no.ica.tollpost.dao;

import java.math.BigDecimal;
import java.util.List;

import no.ica.fraf.dao.DAO;
import no.ica.fraf.model.Bunt;
import no.ica.tollpost.gui.TgImportTypeEnum;
import no.ica.tollpost.model.TgImport;

public interface TgImportDAO extends DAO<TgImport> {
	List<TgImport> findByBuntAndType(final Bunt bunt,final TgImportTypeEnum importType);
	List<TgImport> findWithoutAvdnrByBuntId(Integer buntId);
	List<TgImport> findBySendNrAndKolliId(BigDecimal sendNr, String kolliId);
	void lazyLoadBunt(TgImport tgImport);
}
