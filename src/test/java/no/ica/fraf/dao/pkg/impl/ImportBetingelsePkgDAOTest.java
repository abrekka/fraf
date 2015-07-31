package no.ica.fraf.dao.pkg.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;
import no.ica.fraf.dao.ImportBetingelseDAO;
import no.ica.fraf.dao.pkg.ImportBetingelsePkgDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.ImportBetingelse;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.util.UtilTest;

public class ImportBetingelsePkgDAOTest extends TestCase{
	private UtilTest utilTest = new UtilTest();
	private boolean standalone;
	
	public ImportBetingelsePkgDAOTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
        standalone=isStandalone;
	}

	@Override
	protected void setUp() throws Exception {
		FrafMain.setStandalone(standalone);
		utilTest.setUp();
		setUpImport();
	}

	@Override
	protected void tearDown() throws Exception {
		utilTest.tearDown();
	}
	
	public void testImportBetingelse(){
		ImportBetingelsePkgDAO importBetingelsePkgDAO = (ImportBetingelsePkgDAO) ModelUtil
		.getBean("importBetingelsePkgDAO");
		importBetingelsePkgDAO.importBetingelser(10);
		ImportBetingelseDAO importBetingelseDAO=(ImportBetingelseDAO)ModelUtil.getBean(ImportBetingelseDAO.DAO_NAME);
		List<Object> imports = importBetingelseDAO.findAll();
		assertEquals(0, imports.size());
		Avdeling avdeling=utilTest.findAvdeling(9999);
		Set<AvdelingBetingelse> betingelser=avdeling.getAvdelingBetingelses();
		assertEquals(1, betingelser.size());
		AvdelingBetingelse betingelse=betingelser.iterator().next();
		assertEquals("20090101", GuiUtil.SIMPLE_DATE_FORMAT.format(betingelse.getFraDato()));
		assertEquals("20091231", GuiUtil.SIMPLE_DATE_FORMAT.format(betingelse.getTilDato()));
		assertEquals(BigDecimal.valueOf(1000), betingelse.getBelop());
		assertEquals("tekst", betingelse.getTekst());
		assertEquals("fakturatekst", betingelse.getFakturaTekst());
		assertEquals("konto", betingelse.getKonto());
		assertEquals("avdeling", betingelse.getBokfAvdeling());
	}

	private void setUpImport() throws Exception{
		ImportBetingelse importBetingelse = new ImportBetingelse();

		importBetingelse.setImportDato(Calendar
				.getInstance().getTime());
		// Avdnr

		importBetingelse.setAvdnr(Integer
				.valueOf(9999));

		// Prosjekt
		importBetingelse.setProsjekt("prosjekt");
		// Beløp
		importBetingelse.setSumPrFrekvens(BigDecimal.valueOf(1000));
		// Fra dato
		importBetingelse.setFraDato(GuiUtil.SIMPLE_DATE_FORMAT.parse("20090101"));
			// Til dato
			importBetingelse.setTilDato(GuiUtil.SIMPLE_DATE_FORMAT.parse("20091231"));
		// Betingelse
		importBetingelse.setBetingelseTypeKode("FRA");
			importBetingelse.setFrekvensKode("MND");
			// Avregning
			importBetingelse
					.setAvregningTypeKode("ETT");

		// Selskap
				importBetingelse
						.setSelskapNavn("100");
		// Konto
			importBetingelse.setKonto("konto");
		// Bokførinsavdeling
			importBetingelse.setBokfAvdeling("avdeling");
		// Mvakode
			importBetingelse.setMvaKode("03");
		
		// Tekst
			importBetingelse.setTekst("tekst");
		
		// Fakturatekst
			importBetingelse.setFakturaTekst("fakturatekst");
		
		// Rekkefølge
			importBetingelse
					.setFakturaTekstRek(99);
		
			ImportBetingelseDAO importBetingelseDAO = (ImportBetingelseDAO) ModelUtil
			.getBean("importBetingelseDAO");
		importBetingelseDAO
				.saveImportBetingelse(importBetingelse);

	}
}
