package no.ica.fraf.util;

import java.util.List;

import no.ica.fraf.model.AvdelingStatus;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Chain;
import no.ica.fraf.model.FornyelseType;
import no.ica.fraf.model.KontraktType;
import no.ica.fraf.model.Region;

/**
 * Hjelpeklasse for lister til combobokser
 * 
 * @author abr99
 * 
 */
public interface DataListUtil {
	
	List<AvdelingStatus> getStatuser();

	List<KontraktType> getKontrakttyper();

	
	List<BokfSelskap> getBokfSelskaper();

	List<Region> getRegioner();

	List<Chain> getKjeder();

	List<String> getDistriktssjefer();

	List<FornyelseType> getFornyelseTyper();

	void loadLists();
	List<String> getRegionNames();
}
