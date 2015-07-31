package no.ica.fraf.service;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import no.ica.fraf.dao.fenistra.LfFakturaPosterDAO;
import no.ica.fraf.dao.fenistra.LkKontraktobjekterDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.LfFakturaPoster;
import no.ica.fraf.model.LkKontraktobjekter;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class ImportFenistraServiceTest {
	static {
		BaseManager.setTest(true);
		FrafMain.setStandalone(true);
	}

	private ImportFenistraService importFenistraService;
	private LkKontraktobjekterDAO lkKontraktobjekterDAO;
	private LfFakturaPosterDAO lfFakturaPosterDAO;

	@Before
	public void settopp() {
		lkKontraktobjekterDAO = (LkKontraktobjekterDAO) ModelUtil
				.getBean("lkKontraktobjekterDAO");
		lfFakturaPosterDAO = (LfFakturaPosterDAO) ModelUtil
				.getBean("lfFakturaPosterDAO");
		importFenistraService = new ImportFenistraService(lkKontraktobjekterDAO,lfFakturaPosterDAO);
	}
	
	@Test
	public void skalHaandtereTomLinje() throws Exception{
		LkKontraktobjekter lkKontraktobjekter = importFenistraService.importerKontraktlinje("", 1);
		Assertions.assertThat(lkKontraktobjekter).isNull();
	}
	
	@Test
	public void skalImportereFakturaposterFraFenistra() throws ParseException{
		File fakturaposterfil = new File(ImportFenistraServiceTest.class
				.getClassLoader().getResource("fenistra_fakturaposter.txt")
				.getFile());
		
		importFenistraService.importerFenistraFakturaposter(fakturaposterfil,null);
		
		List<Integer> kontraktider=Lists.newArrayList(6306);
		List<LfFakturaPoster> fakturaposter = lfFakturaPosterDAO.findByKontraktObjektIder(kontraktider);
		Assertions.assertThat(fakturaposter).hasSize(1);
	}
	
	@Test(expected=RuntimeException.class)
	public void skalKasteExceptionDersomHeadingIkkeStemmer() throws Exception{
		File kontraktfil = new File(ImportFenistraServiceTest.class
				.getClassLoader().getResource("fenistra_kontrakter_med_feil_heading.txt")
				.getFile());

		importFenistraService.importerFenistraKontrakter(kontraktfil,null);
	}

	@Test
	public void skalImportereKontrakterFraFenistra() throws Exception {
		File kontraktfil = new File(ImportFenistraServiceTest.class
				.getClassLoader().getResource("fenistra_kontrakter.txt")
				.getFile());

		importFenistraService.importerFenistraKontrakter(kontraktfil,null);

		List<LkKontraktobjekter> kontrakter = lkKontraktobjekterDAO
				.findByAvdnr("3829");
		
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd.MM.yyyy");

		Assertions.assertThat(kontrakter).hasSize(1);
		Assertions.assertThat(kontrakter.get(0).getKontraktObjektId()).isEqualTo(22);
		Assertions.assertThat(kontrakter.get(0).getKontraktObjektNr()).isEqualTo("3829");
		Assertions.assertThat(kontrakter.get(0).getKontraktObjekt()).isEqualTo("Fast leie");
		Assertions.assertThat(kontrakter.get(0).getTerminer()).isEqualTo(12);
		Assertions.assertThat(simpleDateFormat.format(kontrakter.get(0).getStartDato())).isEqualTo("15.08.1993");
		Assertions.assertThat(simpleDateFormat.format(kontrakter.get(0).getSluttDato())).isEqualTo("15.08.2018");
		Assertions.assertThat(kontrakter.get(0).getAarsBeloep()).isEqualTo(BigDecimal.valueOf(560529));
		Assertions.assertThat(kontrakter.get(0).getFakturabeloep()).isEqualTo(BigDecimal.valueOf(46711));
	}
}
