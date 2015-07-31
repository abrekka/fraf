package no.ica.fraf.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JLabel;

import no.ica.fraf.dao.fenistra.LfFakturaPosterDAO;
import no.ica.fraf.dao.fenistra.LkKontraktobjekterDAO;
import no.ica.fraf.model.LfFakturaPoster;
import no.ica.fraf.model.LkKontraktobjekter;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

public class ImportFenistraService {
	private LkKontraktobjekterDAO lkKontraktobjekterDAO;
	private LfFakturaPosterDAO lfFakturaPosterDAO;

	@Inject
	public ImportFenistraService(LkKontraktobjekterDAO lkKontraktobjekterDAO,
			LfFakturaPosterDAO lfFakturaPosterDAO) {
		this.lkKontraktobjekterDAO = lkKontraktobjekterDAO;
		this.lfFakturaPosterDAO = lfFakturaPosterDAO;
	}

	public void importerFenistraKontrakter(File kontraktfil,JLabel labelInfo)
			throws ParseException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(kontraktfil));
			String linje = br.readLine();
			if (linje != null) {
				sjekkKontraktHeading(linje);
			}
			importerlinjer(br, labelInfo);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Kunne ikke lese fil", e);
		} catch (IOException e) {
			throw new RuntimeException("Kunne ikke lese fil", e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					throw new RuntimeException("Kunne ikke lukke fil", e);
				}
			}
		}

	}

	private void importerlinjer(BufferedReader br,JLabel labelInfo) throws IOException,
			ParseException {
		String linje;
		linje = br.readLine();
		int linjenr = 0;
		while (linje != null) {
			linjenr++;
			if(labelInfo!=null){
			labelInfo.setText("Importerer kontraker..."+linjenr);
			}
			LkKontraktobjekter lkKontraktobjekter = importerKontraktlinje(
					linje, linjenr);

			if (lkKontraktobjekter != null) {
				lkKontraktobjekterDAO.saveObject(lkKontraktobjekter);
			}
			linje = br.readLine();
		}
	}

	protected LkKontraktobjekter importerKontraktlinje(String linje, int linjenr)
			throws ParseException {
		if (!linje.isEmpty()) {
			List<String> splittetLinje = Lists.newArrayList(Splitter.on(';')
					.split(linje));
			if (splittetLinje.size() != 8) {
				throw new RuntimeException(
						"Feil antall kolonner i kontraktsfil i linje nummer "
								+ linjenr);
			}
			LkKontraktobjekter lkKontraktobjekter = new LkKontraktobjekter()
					.kontraktObjektID(
							KontraktHeadingEnum.KONTRAKTOBJEKTID
									.hentUtVerdi(splittetLinje))
					.kontraktObjektNR(
							KontraktHeadingEnum.KONTRAKTOBJEKTNR
									.hentUtVerdi(splittetLinje))
					.kontraktObjekt(
							KontraktHeadingEnum.KONTRAKTOBJEKT
									.hentUtVerdi(splittetLinje))
					.terminer(
							KontraktHeadingEnum.TERMINER
									.hentUtVerdi(splittetLinje))
					.startDato(
							KontraktHeadingEnum.STARTDATO
									.hentUtVerdi(splittetLinje))
					.sluttDato(
							KontraktHeadingEnum.SLUTTDATO
									.hentUtVerdi(splittetLinje))
					.aarsbeloep(
							KontraktHeadingEnum.AARSBELOEP
									.hentUtVerdi(splittetLinje))
					.fakturabeloep(
							KontraktHeadingEnum.FAKTURABELOEP
									.hentUtVerdi(splittetLinje));
			return lkKontraktobjekter;
		}
		return null;
	}

	private void sjekkKontraktHeading(String linje) {
		List<String> splittetHeading = Lists.newArrayList(Splitter.on(';')
				.split(linje));
		if (splittetHeading.size() != 8) {
			throw new RuntimeException(
					"Heading for kontrakter har feil antall kolonner");
		}
		int nr = 0;
		for (String string : splittetHeading) {
			KontraktHeadingEnum headingEnum = KontraktHeadingEnum
					.valueOf(string.toUpperCase());
			headingEnum.sjekkRekkefoelge(nr);
			nr++;
		}

	}

	private void sjekkFakturaposterHeading(String linje) {
		List<String> splittetHeading = Lists.newArrayList(Splitter.on(';')
				.split(linje));
		if (splittetHeading.size() != 11) {
			throw new RuntimeException(
					"Heading for fakturaposter har feil antall kolonner");
		}
		int nr = 0;
		for (String string : splittetHeading) {
			FakturaposterHeadingEnum headingEnum = FakturaposterHeadingEnum
					.valueOf(string.toUpperCase());
			headingEnum.sjekkRekkefoelge(nr);
			nr++;
		}

	}

	public enum KontraktHeadingEnum {
		KONTRAKTOBJEKTID(0), KONTRAKTOBJEKTNR(1), KONTRAKTOBJEKT(2), TERMINER(3), STARTDATO(
				4), SLUTTDATO(5), AARSBELOEP(6), FAKTURABELOEP(7);

		private int indeks;

		private KontraktHeadingEnum(int indeks) {
			this.indeks = indeks;
		}

		public String hentUtVerdi(List<String> splittetLinje) {
			return splittetLinje.get(indeks);
		}

		public void sjekkRekkefoelge(int nr) {
			if (this.indeks != nr) {
				throw new RuntimeException(
						"Feil rekkefølge på kolonner i heading");
			}

		}
	}

	public enum FakturaposterHeadingEnum {
		FAKTURAPOSTID(0), FAKTURANR(1), FAKTURATEKST(2), KONTRAKTOBJEKTID(3), FRA(
				4), TIL(5), TERMINSTART(6), POSTBELOEP(7), REMITERING(8), TRANSAKSJONTYPEEDID(
				9), KONTONR(10);

		private int indeks;

		private FakturaposterHeadingEnum(int indeks) {
			this.indeks = indeks;
		}

		public String hentUtVerdi(List<String> splittetLinje) {
			return splittetLinje.get(indeks);
		}

		public void sjekkRekkefoelge(int nr) {
			if (this.indeks != nr) {
				throw new RuntimeException(
						"Feil rekkefølge på kolonner i heading");
			}

		}
	}

	public void importerFenistraFakturaposter(File fakturaposterfil,JLabel labelInfo)
			throws ParseException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fakturaposterfil));
			String linje = br.readLine();
			if (linje != null) {
				sjekkFakturaposterHeading(linje);
			}
			importerFakturaposter(br,labelInfo);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Kunne ikke lese fil", e);
		} catch (IOException e) {
			throw new RuntimeException("Kunne ikke lese fil", e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					throw new RuntimeException("Kunne ikke lukke fil", e);
				}
			}
		}

	}

	private void importerFakturaposter(BufferedReader br,JLabel labelInfo) throws IOException,
			ParseException {
		String linje;
		linje = br.readLine();
		int linjenr = 0;
		while (linje != null) {
			linjenr++;
			if(labelInfo!=null){
			labelInfo.setText("Importerer fakturaposter... "+linjenr);
			}
			LfFakturaPoster lfFakturaPoster = importerFakturapost(linje,
					linjenr);

			if (lfFakturaPoster != null) {
				lfFakturaPosterDAO.saveObject(lfFakturaPoster);
			}
			linje = br.readLine();
		}
	}

	private LfFakturaPoster importerFakturapost(String linje, int linjenr)
			throws ParseException {
		if (!linje.isEmpty()) {
			List<String> splittetLinje = Lists.newArrayList(Splitter.on(';')
					.split(linje));
			if (splittetLinje.size() != 11) {
				throw new RuntimeException(
						"Feil antall kolonner i fakturaposterfil linjenr "
								+ linjenr);
			}
			LfFakturaPoster lfFakturaPoster = new LfFakturaPoster()
					.fakturapostId(
							FakturaposterHeadingEnum.FAKTURAPOSTID
									.hentUtVerdi(splittetLinje))
					.fakturanr(
							FakturaposterHeadingEnum.FAKTURANR
									.hentUtVerdi(splittetLinje))
					.fakturatekst(
							FakturaposterHeadingEnum.FAKTURATEKST
									.hentUtVerdi(splittetLinje))
					.kontraktobjektId(
							FakturaposterHeadingEnum.KONTRAKTOBJEKTID
									.hentUtVerdi(splittetLinje))
					.fra(FakturaposterHeadingEnum.FRA
							.hentUtVerdi(splittetLinje))
					.til(FakturaposterHeadingEnum.TIL
							.hentUtVerdi(splittetLinje))
					.terminstart(
							FakturaposterHeadingEnum.TERMINSTART
									.hentUtVerdi(splittetLinje))
					.postbeloep(
							FakturaposterHeadingEnum.POSTBELOEP
									.hentUtVerdi(splittetLinje))
					.remitering(
							FakturaposterHeadingEnum.REMITERING
									.hentUtVerdi(splittetLinje));
			return lfFakturaPoster;
		}
		return null;
	}
}
