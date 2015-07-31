package no.ica.tollpost.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import no.ica.fraf.common.Line;
import no.ica.fraf.model.Bunt;
import no.ica.tollpost.dao.TgImportDAO;
import no.ica.tollpost.gui.TgImportTypeEnum;
import no.ica.tollpost.model.TgImport;
import no.ica.tollpost.service.TgImportManager;

public class TgImportManagerImpl implements TgImportManager {
	private TgImportDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setTgImportDAO(TgImportDAO dao) {
		this.dao = dao;
		
		
	}

	public List<TgImport> findByBuntAndType(final Bunt bunt,final TgImportTypeEnum importType) {
		return dao.findByBuntAndType(bunt,importType);
	}

	public List<TgImport> findWithoutAvdnrByBuntId(Integer buntId) {
		return dao.findWithoutAvdnrByBuntId(buntId);
	}

	public void saveTgImport(TgImport tgImport) {
		dao.saveObject(tgImport);
		
	}

	public List<Line> findWithoutAvdnrById(Integer id) {
		List<TgImport> list =findWithoutAvdnrByBuntId(id);
		List<Line> lines = new ArrayList<Line>();
		if(list!=null){
			for(TgImport tgImport:list){
				lines.add(tgImport);
			}
		}
		return lines;
	}

	public void saveLine(Line line) {
		dao.saveObject((TgImport)line);
		
	}

	public List<TgImport> findBySendNrAndKolliId(String sendNr, String kolliId) {
		BigDecimal sendNrNumber=null;
		if(sendNr!=null){
			sendNrNumber=BigDecimal.valueOf(Long.valueOf(sendNr));
		}
		return dao.findBySendNrAndKolliId(sendNrNumber, kolliId);
	}

	public void lazyLoadBunt(TgImport tgImport) {
		dao.lazyLoadBunt(tgImport);
		
	}


}
