package no.ica.fraf.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.ApplUserDAO;
import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.dao.AvdelingKontraktDAO;
import no.ica.fraf.dao.AvdelingOmsetningDAO;
import no.ica.fraf.dao.AvregningBasisTypeDAO;
import no.ica.fraf.dao.AvregningFrekvensTypeDAO;
import no.ica.fraf.dao.AvregningTypeDAO;
import no.ica.fraf.dao.BetingelseGruppeDAO;
import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntFeilDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.dao.FakturaFeilDAO;
import no.ica.fraf.dao.FakturaLinjeDAO;
import no.ica.fraf.dao.KontraktTypeDAO;
import no.ica.fraf.dao.MangelTypeDAO;
import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.dao.RegnskapKladdDAO;
import no.ica.fraf.dao.pkg.BuntPkgDAO;
import no.ica.fraf.dao.pkg.FranchisePkgDAO;
import no.ica.fraf.dao.pkg.RegnskapPkgDAO;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.enums.LazyLoadAvdelingEnum;
import no.ica.fraf.enums.LazyLoadFakturaEnum;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.AvdelingKontrakt;
import no.ica.fraf.model.AvdelingOmsetning;
import no.ica.fraf.model.AvregningBasisType;
import no.ica.fraf.model.AvregningFrekvensType;
import no.ica.fraf.model.AvregningType;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntFeil;
import no.ica.fraf.model.BuntStatus;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaFeil;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.model.KontraktType;
import no.ica.fraf.model.MangelType;
import no.ica.fraf.model.Mva;
import no.ica.fraf.model.RegnskapKladd;
import no.ica.fraf.model.SpeiletBetingelse;
import no.ica.fraf.model.SpeiletKostnad;
import no.ica.fraf.service.ThreadInvoiceManager;
import no.ica.fraf.service.impl.BaseManager;

/**
 * Hjelpeklasse til tester
 * 
 * @author abr99
 * 
 */
public class UtilTest {
	static {
		BaseManager.setTest(true);
	}

	/**
	 * DAo for bunt
	 */
	private BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");

	private AvdelingKontraktDAO avdelingKontraktDAO = (AvdelingKontraktDAO) ModelUtil
			.getBean("avdelingKontraktDAO");

	/**
	 * DAo for mangeltype
	 */
	private MangelTypeDAO mangelTypeDAO = (MangelTypeDAO) ModelUtil
			.getBean("mangelTypeDAO");

	/**
	 * DAO for buntstatus
	 */
	private BuntStatusDAO buntStatusDAO = (BuntStatusDAO) ModelUtil
			.getBean("buntStatusDAO");

	/**
	 * DAO for databasepakke REGSNKAP_PKG
	 */
	private RegnskapPkgDAO regnskapPkgDAO = (RegnskapPkgDAO) ModelUtil
			.getBean("regnskapPkgDAO");

	/**
	 * DAO for mva
	 */
	private MvaDAO mvaDAO = (MvaDAO) ModelUtil.getBean("mvaDAO");

	/**
	 * DAO for avdeling
	 */
	private AvdelingDAO avdelingDAO = (AvdelingDAO) ModelUtil
			.getBean("avdelingDAO");

	/**
	 * DAO for frekvens
	 */
	private AvregningFrekvensTypeDAO avregningFrekvensTypeDAO = (AvregningFrekvensTypeDAO) ModelUtil
			.getBean("avregningFrekvensTypeDAO");

	/**
	 * DAO for avregningtype
	 */
	private AvregningTypeDAO avregningTypeDAO = (AvregningTypeDAO) ModelUtil
			.getBean("avregningTypeDAO");

	/**
	 * DAO for betingelsetype
	 */
	private BetingelseTypeDAO betingelseTypeDAO = (BetingelseTypeDAO) ModelUtil
			.getBean("betingelseTypeDAO");

	/**
	 * DAO for databasepakke FRANCHISE_PKG
	 */
	private FranchisePkgDAO franchisePkgDAO = (FranchisePkgDAO) ModelUtil
			.getBean("franchisePkgDAO");

	/**
	 * DAO for bruker
	 */
	private ApplUserDAO applUserDAO = (ApplUserDAO) ModelUtil
			.getBean("applUserDAO");

	/**
	 * DAo for avregningbasis
	 */
	private AvregningBasisTypeDAO avregningBasisTypeDAO = (AvregningBasisTypeDAO) ModelUtil
			.getBean("avregningBasisTypeDAO");

	/**
	 * DAO for betingelsegruppe
	 */
	private BetingelseGruppeDAO betingelseGruppeDAO = (BetingelseGruppeDAO) ModelUtil
			.getBean("betingelseGruppeDAO");

	/**
	 * DAO for databasepakke BUNT_PKG
	 */
	private BuntPkgDAO buntPkgDAO = (BuntPkgDAO) ModelUtil
			.getBean("buntPkgDAO");

	/**
	 * DAo for faktura
	 */
	private FakturaDAO fakturaDAO = (FakturaDAO) ModelUtil
			.getBean("fakturaDAO");

	/**
	 * DAO for fakturafeil
	 */
	private FakturaFeilDAO fakturaFeilDAO = (FakturaFeilDAO) ModelUtil
			.getBean("fakturaFeilDAO");

	/**
	 * DAo for fakturalinje
	 */
	private FakturaLinjeDAO fakturaLinjeDAO = (FakturaLinjeDAO) ModelUtil
			.getBean("fakturaLinjeDAO");

	/**
	 * DAO for buntfeil
	 */
	private BuntFeilDAO buntFeilDAO = (BuntFeilDAO) ModelUtil
			.getBean("buntFeilDAO");

	/**
	 * DAO for kontrakttype
	 */
	private KontraktTypeDAO kontraktTypeDAO = (KontraktTypeDAO) ModelUtil
			.getBean("kontraktTypeDAO");

	/**
	 * DAo for bokføringsselskap
	 */
	private BokfSelskapDAO bokfSelskapDAO = (BokfSelskapDAO) ModelUtil
			.getBean("bokfSelskapDAO");

	/**
	 * Avdeling
	 */
	private Avdeling avdeling;

	/**
	 * Datoformaterer
	 */
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy.MM.dd");

	/**
	 * Bruker
	 */
	private ApplUser applUser;

	/**
	 * Avregningtype
	 */
	private AvregningBasisType avregningBasisType;

	/**
	 * Speilet betingelse
	 */
	// private SpeiletBetingelse speiletBetingelse;
	/**
	 * Bunter som skal rulles tilbake
	 */
	private Vector<Integer> buntIder = new Vector<Integer>();

	/**
	 * Mangeltype
	 */
	private MangelType mangelType = null;

	/**
	 * Initirerer hjelpeklasse
	 * 
	 * @throws Exception
	 */
	public void setUp() throws Exception {
		applUser = applUserDAO
				.findByUser(System.getProperty("user.name"), null);

		avregningBasisType = avregningBasisTypeDAO.findByKode("OMS");

		BokfSelskap bokfSelskap = bokfSelskapDAO.findByName("100");

		avdeling = new Avdeling();
		avdeling.setAvdnr(new Integer(9999));
		avdeling.setBokfSelskap(bokfSelskap);
		avdeling.setOpprettetDato(simpleDateFormat.parse("2005.01.01"));
		avdelingDAO.saveAvdeling(avdeling);

		avdelingDAO.saveAvdeling(avdeling);
	}

	/**
	 * Rydder opp etter test
	 * 
	 * @throws Exception
	 */
	public void tearDown() throws Exception {
		removeBunter();
		removeOmsetningForAvdeling(avdeling);
		removeRegnskapForAvdeling(avdeling);
		removeFakturaForAvdleing(avdeling);
		if (avdeling != null) {
			avdelingDAO.removeAvdeling(avdeling.getAvdelingId());
		}

		if (mangelType != null) {
			mangelTypeDAO.removeMangelType(mangelType.getMangelId());
		}
	}

	public void removeFakturaForAvdleing(Avdeling avdeling) {
		fakturaDAO.removeForAvdeling(avdeling);
	}

	public void removeRegnskapForAvdeling(Avdeling avdeling) {
		if (avdeling != null) {
			RegnskapKladdDAO regnskapKladdDAO = (RegnskapKladdDAO) ModelUtil
					.getBean("regnskapKladdDAO");
			regnskapKladdDAO.removeForAvdeling(avdeling);
		}
	}

	public void removeOmsetningForAvdeling(Avdeling avdeling) {
		AvdelingOmsetningDAO avdelingOmsetningDAO = (AvdelingOmsetningDAO) ModelUtil
				.getBean("avdelingOmsetningDAO");
		avdelingOmsetningDAO.removeAvdelingOmsetningForAvdeling(avdeling);
	}

	/**
	 * Ruller tilbake bunter som har blitt laget ved testing
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void removeBunter() throws Exception {
		Collections.sort(buntIder, new BuntComparator());
		for (Integer buntId : buntIder) {
			buntPkgDAO.slettBunt(buntId, 1);
		}
		buntIder.clear();
	}

	/**
	 * Fjerner bokføringsselskap
	 * 
	 * @param bokfSelskap
	 */
	public void removeBokfSelskap(BokfSelskap bokfSelskap) {
		bokfSelskapDAO.removeBokfSelskap(bokfSelskap.getSelskapId());
	}

	/**
	 * Fjerner betingelsetype
	 * 
	 * @param betingelseType
	 */
	public void removeBetingelseType(BetingelseType betingelseType) {
		if (betingelseType != null
				&& betingelseType.getBetingelseTypeId() != null) {
			betingelseTypeDAO.removeBetingelseType(betingelseType
					.getBetingelseTypeId());
		}
	}

	/**
	 * Setter inn bokføringsselskap
	 * 
	 * @param fakturaNr
	 * @param selskapNavn
	 * @param tilPs
	 * @return bokføringsselskap
	 */
	public BokfSelskap addBokfSelskap(BigDecimal fakturaNr, String selskapNavn,
			Integer tilPs) {
		BokfSelskap bokfSelskap = new BokfSelskap();
		bokfSelskap.setFakturaNr(fakturaNr);
		bokfSelskap.setIbruk(0);
		bokfSelskap.setSelskapNavn(selskapNavn);
		bokfSelskap.setTilPs(tilPs);
		bokfSelskapDAO.saveBokfSelskap(bokfSelskap);
		return bokfSelskap;
	}

	/**
	 * Setter inn betingelse på avdeling som ble laget ved setup
	 * 
	 * @param frekvens
	 * @param betingelse
	 * @param speiling
	 * @param fraDato
	 * @param tilDato
	 * @param avregning
	 * @param belop
	 * @param sats
	 * @param satsFraBelop
	 * @param satsTilBelop
	 * @return betingelse
	 * @throws Exception
	 */
	public AvdelingBetingelse addAvdelingBetingelse(String frekvens,
			String betingelse, int speiling, String fraDato, String tilDato,
			String avregning, BigDecimal belop, BigDecimal sats,
			BigDecimal satsFraBelop, BigDecimal satsTilBelop) throws Exception {
		return addAvdelingBetingelse(frekvens, betingelse, speiling, fraDato,
				tilDato, avregning, belop, sats, satsFraBelop, satsTilBelop,
				avdeling, null, null, null, null, null, null);
	}

	/**
	 * @param frekvens
	 * @param betingelse
	 * @param speiling
	 * @param fraDato
	 * @param tilDato
	 * @param avregning
	 * @param belop
	 * @param sats
	 * @param satsFraBelop
	 * @param satsTilBelop
	 * @param selskapNavn
	 * @param konto
	 * @param bokfAvdeling
	 * @param mvaKode
	 * @param fakturaLinjeTekst
	 * @param fakturaTekst
	 * @return betingelse
	 * @throws Exception
	 */
	public AvdelingBetingelse addAvdelingBetingelse(String frekvens,
			String betingelse, int speiling, String fraDato, String tilDato,
			String avregning, BigDecimal belop, BigDecimal sats,
			BigDecimal satsFraBelop, BigDecimal satsTilBelop,
			String selskapNavn, String konto, String bokfAvdeling,
			String mvaKode, String fakturaLinjeTekst, String fakturaTekst)
			throws Exception {
		return addAvdelingBetingelse(frekvens, betingelse, speiling, fraDato,
				tilDato, avregning, belop, sats, satsFraBelop, satsTilBelop,
				avdeling, selskapNavn, konto, bokfAvdeling, mvaKode,
				fakturaLinjeTekst, fakturaTekst);
	}

	/**
	 * Setter inn betingelse
	 * 
	 * /**
	 * 
	 * @param frekvens
	 * @param betingelse
	 * @param speiling
	 * @param fraDato
	 * @param tilDato
	 * @param avregning
	 * @param belop
	 * @param sats
	 * @param satsFraBelop
	 * @param satsTilBelop
	 * @param aAvdeling
	 * @param selskapNavn
	 * @param konto
	 * @param bokfAvdeling
	 * @param mvaKode
	 * @param fakturaLinjeTekst
	 * @param fakturaTekst
	 * @return betingelse
	 * @throws Exception
	 */
	public AvdelingBetingelse addAvdelingBetingelse(String frekvens,
			String betingelse, int speiling, String fraDato, String tilDato,
			String avregning, BigDecimal belop, BigDecimal sats,
			BigDecimal satsFraBelop, BigDecimal satsTilBelop,
			Avdeling aAvdeling, String selskapNavn, String konto,
			String bokfAvdeling, String mvaKode, String fakturaLinjeTekst,
			String fakturaTekst) throws Exception {
		AvregningFrekvensType avregningFrekvensType = avregningFrekvensTypeDAO
				.findByKode(frekvens);
		AvregningType avregningType = avregningTypeDAO.findByKode(avregning);
		BetingelseType betingelseType = betingelseTypeDAO
				.findByKode(betingelse);

		BokfSelskap bokfSelskap = null;
		if (selskapNavn != null) {
			bokfSelskap = bokfSelskapDAO.findByName(selskapNavn);
		}
		Mva mva = null;
		if (mvaKode != null) {
			mva = mvaDAO.findByMvaKode(mvaKode);
		}

		AvdelingBetingelse avdelingBetingelseNoMirror = new AvdelingBetingelse();
		avdelingBetingelseNoMirror.setAvdeling(aAvdeling);
		avdelingBetingelseNoMirror
				.setAvregningFrekvensType(avregningFrekvensType);
		avdelingBetingelseNoMirror.setAvregningType(avregningType);
		avdelingBetingelseNoMirror.setBetingelseType(betingelseType);
		avdelingBetingelseNoMirror.setFraDato(simpleDateFormat.parse(fraDato));
		avdelingBetingelseNoMirror.setSpeilet(speiling);
		avdelingBetingelseNoMirror.setSatsFraBelop(satsFraBelop);
		avdelingBetingelseNoMirror.setSatsTilBelop(satsTilBelop);
		avdelingBetingelseNoMirror.setBokfSelskap(bokfSelskap);
		avdelingBetingelseNoMirror.setKonto(konto);
		avdelingBetingelseNoMirror.setBokfAvdeling(bokfAvdeling);
		avdelingBetingelseNoMirror.setMva(mva);
		avdelingBetingelseNoMirror.setTekst(fakturaLinjeTekst);
		avdelingBetingelseNoMirror.setFakturaTekst(fakturaTekst);

		if (sats != null) {
			avdelingBetingelseNoMirror.setSats(sats);
		} else {
			avdelingBetingelseNoMirror.setBelop(belop);
		}
		avdelingBetingelseNoMirror.setTilDato(simpleDateFormat.parse(tilDato));
		aAvdeling.addAvdelingBetingelse(avdelingBetingelseNoMirror);
		avdelingDAO.saveAvdeling(aAvdeling);
		return avdelingBetingelseNoMirror;
	}

	/**
	 * Setter inn betingelsetype
	 * 
	 * @param betingelseGruppeNavn
	 * @param betingelseNavn
	 * @param betingelseTypeKode
	 * @param bokfSelskap
	 * @param isDebit
	 * @param konto
	 * @param mvaKode
	 * @return betingelsetype
	 */
	public BetingelseType addBetingelseType(String betingelseGruppeNavn,
			String betingelseNavn, String betingelseTypeKode,
			BokfSelskap bokfSelskap,
			// Integer isDebit,
			String inntektskontoE, String inntektskontoF, String mvaKodeE,
			String mvaKodeF) {
		BetingelseGruppe betingelseGruppe = betingelseGruppeDAO
				.findByName(betingelseGruppeNavn);
		Mva mvaE = mvaDAO.findByMvaKode(mvaKodeE);
		Mva mvaF = mvaDAO.findByMvaKode(mvaKodeF);
		BetingelseType betingelseType = new BetingelseType();
		betingelseType.setBetingelseGruppe(betingelseGruppe);
		betingelseType.setBetingelseNavn(betingelseNavn);
		betingelseType.setBetingelseTypeKode(betingelseTypeKode);
		betingelseType.setBokfSelskap(bokfSelskap);
		// betingelseType.setIsDebit(isDebit);
		betingelseType.setInntektskontoE(inntektskontoE);
		betingelseType.setInntektskontoF(inntektskontoF);
		betingelseType.setMvaE(mvaE);
		betingelseType.setMvaF(mvaF);
		betingelseType.setLinkAvtale(1);

		betingelseTypeDAO.saveBetingelseType(betingelseType);
		return betingelseType;
	}

	/**
	 * Setter inn kontrakt for avdeling opprettet i setup
	 * 
	 * @param fra
	 * @param til
	 * @throws Exception
	 */
	public AvdelingKontrakt addAvdelingKontrakt(String fra, String til)
			throws Exception {
		return addAvdelingKontrakt(fra, til, avdeling);
	}

	/**
	 * Setter inn kontrakt
	 * 
	 * @param fra
	 * @param til
	 * @param aAvdeling
	 * @throws Exception
	 */
	public AvdelingKontrakt addAvdelingKontrakt(String fra, String til,
			Avdeling aAvdeling) throws Exception {
		return addAvdelingKontrakt(fra, til, aAvdeling, "MND", "ETT", "A",
				"OMS");
	}

	/**
	 * Legger til kontrakt
	 * 
	 * @param fra
	 * @param til
	 * @param aAvdeling
	 * @param frekvens
	 * @param avregning
	 * @param typeKontrakt
	 * @param avregningBasisTypeString
	 * @throws Exception
	 */
	public AvdelingKontrakt addAvdelingKontrakt(String fra, String til,
			Avdeling aAvdeling, String frekvens, String avregning,
			String typeKontrakt, String avregningBasisTypeString)
			throws Exception {
		AvregningFrekvensType avregningFrekvensType = avregningFrekvensTypeDAO
				.findByKode(frekvens);
		AvregningType avregningType = avregningTypeDAO.findByKode(avregning);
		KontraktType kontraktType = kontraktTypeDAO.findByKode(typeKontrakt);
		AvregningBasisType avregningBasis = avregningBasisTypeDAO
				.findByKode(avregningBasisTypeString);

		AvdelingKontrakt avdelingKontrakt = new AvdelingKontrakt();
		avdelingKontrakt.setAvdeling(aAvdeling);
		avdelingKontrakt.setAvregningBasisType(avregningBasis);
		avdelingKontrakt.setAvregningFrekvensType(avregningFrekvensType);
		avdelingKontrakt.setAvregningType(avregningType);
		// avdelingKontrakt.setFraDato(simpleDateFormat.parse("2005.10.01"));
		avdelingKontrakt.setFraDato(simpleDateFormat.parse(fra));
		avdelingKontrakt.setKontraktType(kontraktType);
		// avdelingKontrakt.setTilDato(simpleDateFormat.parse("2006.01.31"));
		avdelingKontrakt.setTilDato(simpleDateFormat.parse(til));
		aAvdeling.addAvdelingKontrakt(avdelingKontrakt);

		avdelingDAO.saveAvdeling(aAvdeling);
		return avdelingKontrakt;

	}

	/**
	 * Setter inn speilet betingelse
	 * 
	 * @param avdelingBetingelse
	 * @param frekvens
	 * @param kontraktObjectId
	 * @param speiletFraDato
	 * @return speilet betingelse
	 * @throws Exception
	 */
	public SpeiletBetingelse addSpeiletBetingelse(
			AvdelingBetingelse avdelingBetingelse, String frekvens,
			Integer kontraktObjectId, String speiletFraDato) throws Exception {
		AvregningFrekvensType avregningFrekvensType = avregningFrekvensTypeDAO
				.findByKode(frekvens);

		SpeiletBetingelse speiletBetingelse = new SpeiletBetingelse();
		speiletBetingelse.setAvdeling(avdeling);
		speiletBetingelse.setAvdelingBetingelse(avdelingBetingelse);
		speiletBetingelse.setAvregningFrekvensType(avregningFrekvensType);
		speiletBetingelse.setKontraktObjektId(kontraktObjectId);
		speiletBetingelse.setSpeiletFraDato(simpleDateFormat
				.parse(speiletFraDato));
		avdeling.addSpeiletBetingelse(speiletBetingelse);

		avdelingDAO.saveAvdeling(avdeling);
		return speiletBetingelse;
	}

	/**
	 * Setter inn speilet kostnad
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param belop
	 * @param fakturaPostID
	 * @param speiletBetingelse
	 * @throws Exception
	 */
	public void addSpeiletKostnad(String fromDate, String toDate,
			BigDecimal belop, int fakturaPostID,
			SpeiletBetingelse speiletBetingelse) throws Exception {

		SpeiletKostnad speiletKostnad = new SpeiletKostnad();
		speiletKostnad.setBelop(belop);
		speiletKostnad.setFakturaPostId(fakturaPostID);
		speiletKostnad.setFraDato(simpleDateFormat.parse(fromDate));
		speiletKostnad.setSpeiletBetingelse(speiletBetingelse);
		speiletKostnad.setTilDato(simpleDateFormat.parse(toDate));

		speiletBetingelse.addSpeiletKostnad(speiletKostnad);

		avdelingDAO.saveAvdeling(avdeling);
	}

	/**
	 * Finner betingelsegruppe
	 * 
	 * @param betingelseGruppeNavn
	 * @return betingelsegruppe
	 */
	public BetingelseGruppe findBetingelseGruppe(String betingelseGruppeNavn) {
		return betingelseGruppeDAO.findByName(betingelseGruppeNavn);
	}

	/**
	 * @param year
	 * @param fromPeriode
	 * @param toPeriode
	 * @param betingelseGruppeNavn
	 * @return buntid
	 * @throws Exception
	 */
	public Integer fakturerPeriode(Integer year, Integer fromPeriode,
			Integer toPeriode, String betingelseGruppeNavn, Integer selskapId)
			throws Exception {
		return fakturerPeriode(year, fromPeriode, toPeriode,
				betingelseGruppeNavn, 9999, 9999, "OMS", null, null, selskapId,
				null);
	}

	public Integer fakturerPeriode(Integer year, Integer fromPeriode,
			Integer toPeriode, String betingelseGruppeNavn, Integer selskapId,
			Integer[] betingelseGrupper) throws Exception {
		return fakturerPeriode(year, fromPeriode, toPeriode,
				betingelseGruppeNavn, 9999, 9999, "OMS", null, null, selskapId,
				betingelseGrupper);
	}

	public Integer fakturerPeriode(Integer year, Integer fromPeriode,
			Integer toPeriode, String betingelseGruppeNavn, Integer selskapId,
			Integer[] betingelseGrupper, Integer avdnr) throws Exception {
		return fakturerPeriode(year, fromPeriode, toPeriode,
				betingelseGruppeNavn, avdnr, avdnr, "OMS", null, null,
				selskapId, betingelseGrupper);
	}

	public Integer fakturerPeriode(Integer year, Integer fromPeriode,
			Integer toPeriode, String betingelseGruppeNavn, Integer selskapId,
			Integer[] betingelseGrupper, Integer fromAvdnr, Integer toAvdnr)
			throws Exception {
		return fakturerPeriode(year, fromPeriode, toPeriode,
				betingelseGruppeNavn, fromAvdnr, toAvdnr, "OMS", null, null,
				selskapId, betingelseGrupper);
	}

	public List<Integer> fakturerPeriodeInThread(Integer year,
			Integer fromPeriode, Integer toPeriode,
			String betingelseGruppeNavn, Integer selskapId,
			Integer[] betingelseGrupper, Integer fromAvdnr, Integer toAvdnr,Date dueDate,Date invoiceDate)
			throws Exception {

		ThreadInvoiceManager threadInvoicer = new ThreadInvoiceManager();

		AvregningBasisType basisType = avregningBasisTypeDAO
		.findByKode("OMS");
		
		Integer betingelseGruppeId = null;
		if (betingelseGruppeNavn != null) {
			BetingelseGruppe betingelseGruppe = findBetingelseGruppe(betingelseGruppeNavn);
			betingelseGruppeId = betingelseGruppe.getBetingelseGruppeId();
		}
		
		
		
		return threadInvoicer.makeInvoices(year, fromPeriode, toPeriode,
				fromAvdnr, toAvdnr, basisType.getAvregningBasisTypeId(), betingelseGruppeId,
				betingelseGrupper, null, dueDate, invoiceDate,
				null, null, selskapId, applUser.getUserId());
	}

	/**
	 * Fakturerer periode
	 * 
	 * @param year
	 * @param fromPeriode
	 * @param toPeriode
	 * @param betingelseGruppeNavn
	 * @param avdnr
	 * @param avregningBasis
	 * @param notDepartments
	 * @param fakturerAvregning
	 * @return buntid
	 * @throws Exception
	 */
	public Integer fakturerPeriode(Integer year, Integer fromPeriode,
			Integer toPeriode, String betingelseGruppeNavn, Integer fromAvdnr,
			Integer toAvdnr, String avregningBasis, Integer[] notDepartments,
			String fakturerAvregning, Integer selskapId,
			Integer[] betingelseGrupper) throws Exception {
		AvregningBasisType basisType = avregningBasisTypeDAO
				.findByKode(avregningBasis);
		Integer avregningTypeFakturaId = null;
		if (fakturerAvregning != null) {
			AvregningType avregningTypeFaktura = avregningTypeDAO
					.findByKode(fakturerAvregning);
			avregningTypeFakturaId = avregningTypeFaktura.getAvregningTypeId();
		}
		Integer betingelseGruppeId = null;
		if (betingelseGruppeNavn != null) {
			BetingelseGruppe betingelseGruppe = findBetingelseGruppe(betingelseGruppeNavn);
			betingelseGruppeId = betingelseGruppe.getBetingelseGruppeId();
		}

		return franchisePkgDAO.fakturerPerioder(applUser.getUserId(), year,
				fromPeriode, toPeriode, fromAvdnr, toAvdnr, simpleDateFormat
						.parse("2005.10.01"), simpleDateFormat
						.parse("2005.10.01"), basisType
						.getAvregningBasisTypeId(), betingelseGruppeId,
				betingelseGrupper, null, notDepartments,
				avregningTypeFakturaId, selskapId);
	}

	/**
	 * Lager kredittnota
	 * 
	 * @param fakturaId
	 * @return buntid
	 * @throws Exception
	 */
	public Integer lagKredittnota(Integer fakturaId) throws Exception {
		return franchisePkgDAO.lagKredittnota(applUser.getUserId(), fakturaId,
				simpleDateFormat.parse("2005.10.01"), simpleDateFormat
						.parse("2005.10.01"));
	}

	/**
	 * Legger til buntid som skal rulles tilbake etter test
	 * 
	 * @param buntId
	 */
	public void addBunt(Integer buntId) {
		buntIder.add(buntId);
	}
	public void addBuntList(List<Integer> aBuntList){
		buntIder.addAll(aBuntList);
	}

	/**
	 * Finner fakturaer for gitt bunt
	 * 
	 * @param buntId
	 * @return fakturaer for gitt bunt
	 */
	public List<Faktura> findFakturaerByBuntId(Integer buntId) {
		return fakturaDAO.findByBuntId(buntId);
	}

	/**
	 * Finner buntfeil for gitt bunt
	 * 
	 * @param buntId
	 * @return buntfeil for gitt bunt
	 */
	public List<BuntFeil> findBuntFeilById(Integer buntId) {
		return buntFeilDAO.findByBuntId(buntId);
	}

	/**
	 * Finner fakturafeil for gitt faktura
	 * 
	 * @param fakturaId
	 * @return fakturafeil for gitt faktura
	 */
	public List<FakturaFeil> findFakturaFeilByFakturaId(Integer fakturaId) {
		return fakturaFeilDAO.findByFakturaId(fakturaId);
	}

	/**
	 * Finner fakturalinjer for gitt faktura
	 * 
	 * @param fakturaId
	 * @return fakturalinjer for gitt faktura
	 */
	public List<FakturaLinje> findFakturaLinjerByFakturaId(Integer fakturaId) {
		return fakturaLinjeDAO.findByFakturaId(fakturaId);
	}

	/**
	 * Henter faktura
	 * 
	 * @param fakturaId
	 * @return faktura
	 */
	public Faktura getFaktura(Integer fakturaId) {
		return fakturaDAO.getFaktura(fakturaId);
	}

	/**
	 * Bokfører bunt
	 * 
	 * @param buntId
	 * @param bokforDato
	 * @throws Exception
	 */
	public void bokfoerBunt(Integer buntId, String bokforDato) throws Exception {
		regnskapPkgDAO.bokforBunt(buntId, simpleDateFormat.parse(bokforDato));
	}

	/**
	 * Setter buntstatus for gitt bunt
	 * 
	 * @param buntId
	 * @param status
	 */
	public void setBuntStatus(Integer buntId, String status) {
		BuntStatus buntStatus = buntStatusDAO
				.findByKode(BuntStatusEnum.FAKTURERT);
		Bunt bunt = buntDAO.getBunt(buntId);
		bunt.setBuntStatus(buntStatus);
		buntDAO.saveBunt(bunt);
	}

	/**
	 * Legger til mangeltype
	 * 
	 * @param mangelKode
	 * @param mangelBeskrivelse
	 * @return mangeltype
	 */
	public MangelType addMangelType(String mangelKode, String mangelBeskrivelse) {
		mangelType = new MangelType();
		mangelType.setMangelKode(mangelKode);
		mangelType.setMangelBeskrivelse(mangelBeskrivelse);
		mangelTypeDAO.saveMangelType(mangelType);
		return mangelType;
	}

	/**
	 * @return avdeling
	 */
	public Avdeling getAvdeling() {
		return avdeling;
	}

	/**
	 * Lagrer avdeling
	 * 
	 * @param aAvdeling
	 */
	public void saveAvdeling(Avdeling aAvdeling) {
		avdelingDAO.saveAvdeling(aAvdeling);
	}

	/**
	 * @param aar
	 * @param belop
	 * @param period
	 * @return omsetning
	 */
	public AvdelingOmsetning addAvdelingOmsetning(Integer aar,
			BigDecimal belop, Integer period, BigDecimal avregning) {
		return addAvdelingOmsetning(aar, belop, period, avdeling, avregning);
	}

	/**
	 * Legger til omsetning
	 * 
	 * @param aar
	 * @param belop
	 * @param period
	 * @param aAvdeling
	 * @return omsetning
	 */
	public AvdelingOmsetning addAvdelingOmsetning(Integer aar,
			BigDecimal belop, Integer period, Avdeling aAvdeling,
			BigDecimal avregning) {
		AvdelingOmsetning avdelingOmsetning = new AvdelingOmsetning();
		AvdelingOmsetningDAO avdelingOmsetningDAO = (AvdelingOmsetningDAO) ModelUtil
				.getBean("avdelingOmsetningDAO");

		avdelingOmsetning.setAar(aar);
		avdelingOmsetning.setAvdeling(aAvdeling);
		avdelingOmsetning.setAvdnr(aAvdeling.getAvdnr());
		avdelingOmsetning.setAvregningBasisType(avregningBasisType);
		avdelingOmsetning.setBelop(belop);
		avdelingOmsetning.setKorrigertBelop(belop);
		avdelingOmsetning.setPeriode(period);
		avdelingOmsetning.setAvregning(avregning);

		avdelingOmsetningDAO.saveAvdelingOmsetning(avdelingOmsetning);
		return avdelingOmsetning;
	}

	public void addAvdelingOmsetningKorreksjon(Integer year,
			BigDecimal korreksjon, Integer period) {
		AvdelingOmsetningDAO avdelingOmsetningDAO = (AvdelingOmsetningDAO) ModelUtil
				.getBean("avdelingOmsetningDAO");

		AvdelingOmsetning avdelingOmsetning = avdelingOmsetningDAO
				.findByAvdelingAndPeriod(avdeling, year, period);

		if (avdelingOmsetning != null) {
			avdelingOmsetning.setKorreksjonBelop(korreksjon);
			avdelingOmsetningDAO.saveAvdelingOmsetning(avdelingOmsetning);
		}
	}

	/**
	 * Legger til budsjett
	 * 
	 * @param aar
	 * @param belop
	 * @param period
	 * @param aAvdeling
	 */
	public void addAvdelingBudget(Integer aar, BigDecimal belop,
			Integer period, Avdeling aAvdeling) {
		AvdelingOmsetning avdelingOmsetning = new AvdelingOmsetning();
		AvdelingOmsetningDAO avdelingOmsetningDAO = (AvdelingOmsetningDAO) ModelUtil
				.getBean("avdelingOmsetningDAO");
		AvregningBasisType avregningBasisTypeBud = avregningBasisTypeDAO
				.findByKode("BUD");

		avdelingOmsetning.setAar(aar);
		avdelingOmsetning.setAvdeling(aAvdeling);
		avdelingOmsetning.setAvdnr(aAvdeling.getAvdnr());
		avdelingOmsetning.setAvregningBasisType(avregningBasisTypeBud);
		avdelingOmsetning.setBelop(belop);
		avdelingOmsetning.setKorrigertBelop(belop);
		avdelingOmsetning.setPeriode(period);

		avdelingOmsetningDAO.saveAvdelingOmsetning(avdelingOmsetning);
	}

	/**
	 * Oppretter avdeling
	 * 
	 * @param avdnr
	 * @param createdDate
	 * @return avdeling
	 * @throws Exception
	 */
	public Avdeling addAvdeling(Integer avdnr, String createdDate)
			throws Exception {
		Avdeling aAvdeling = new Avdeling();

		BokfSelskap bokfSelskap = bokfSelskapDAO.findByName("100");

		aAvdeling.setAvdnr(avdnr);
		aAvdeling.setOpprettetDato(simpleDateFormat.parse(createdDate));
		aAvdeling.setBokfSelskap(bokfSelskap);
		avdelingDAO.saveAvdeling(aAvdeling);
		return aAvdeling;
	}

	/**
	 * Fjerner avdeling
	 * 
	 * @param aAvdeling
	 */
	public void removeAvdeling(Avdeling aAvdeling) {
		if (aAvdeling != null && aAvdeling.getAvdelingId() != null) {
			avdelingDAO.removeAvdeling(aAvdeling);
		}
	}

	/**
	 * Finner bokføringssselskap
	 * 
	 * @param name
	 * @return bokføringssselskap
	 */
	public BokfSelskap findBokfSelskap(String name) {
		return bokfSelskapDAO.findByName(name);
	}

	/**
	 * Sletter bunt
	 * 
	 * @param buntId
	 * @throws Exception
	 */
	public void slettBunt(Integer buntId) throws Exception {
		buntPkgDAO.slettBunt(buntId);
	}

	/**
	 * Lazyloader bokføring for faktura
	 * 
	 * @param faktura
	 * @param lazyLoadFakturaEnum
	 */
	public void lazyLoadFaktura(Faktura faktura,
			LazyLoadFakturaEnum[] lazyLoadFakturaEnum) {
		fakturaDAO.loadLazy(faktura, lazyLoadFakturaEnum);
	}

	/**
	 * Lagrer omsetning
	 * 
	 * @param avdelingOmsetning
	 */
	public void saveAvdelingOmsetning(AvdelingOmsetning avdelingOmsetning) {
		AvdelingOmsetningDAO avdelingOmsetningDAO = (AvdelingOmsetningDAO) ModelUtil
				.getBean("avdelingOmsetningDAO");

		avdelingOmsetningDAO.saveAvdelingOmsetning(avdelingOmsetning);
	}

	/**
	 * Finner regnskapskladder for en bunt
	 * 
	 * @param buntId
	 * @return regnskapskladder
	 */
	public List<RegnskapKladd> findRegnskapKladdByBuntId(Integer buntId) {
		RegnskapKladdDAO regnskapKladdDAO = (RegnskapKladdDAO) ModelUtil
				.getBean("regnskapKladdDAO");
		return regnskapKladdDAO.findRegnskapKladdByBuntId(buntId);
	}

	/**
	 * Finner avdleing basert på avdnr
	 * 
	 * @param avdNr
	 * @return avdeling
	 */
	public Avdeling findAvdeling(Integer avdNr) {
		Avdeling findAvdeling = avdelingDAO.findByAvdnr(avdNr);
		avdelingDAO
				.loadLazy(
						findAvdeling,
						new LazyLoadAvdelingEnum[] { LazyLoadAvdelingEnum.LOAD_CONDITION });
		return findAvdeling;
	}

	public void updateAvdelingKontrakt(AvdelingKontrakt kontrakt) {
		avdelingKontraktDAO.saveAvdelingKontrakt(kontrakt);
	}

	public void updateAvdelingOmsetning(Integer year, BigDecimal korreksjon, Integer periode) throws FrafException {
		
		AvdelingOmsetningDAO avdelingOmsetningDAO = (AvdelingOmsetningDAO) ModelUtil
				.getBean("avdelingOmsetningDAO");
		AvdelingOmsetning avdelingOmsetning = avdelingOmsetningDAO.findByAvdelingAndPeriod(avdeling, year, periode);
		if(avdelingOmsetning==null){
			throw new FrafException("Omsetning ikke funnet");
		}

		avdelingOmsetning.setKorreksjonBelop(korreksjon);
		avdelingOmsetning.setKorrigertBelop(avdelingOmsetning.getBelop().add(korreksjon));
		

		avdelingOmsetningDAO.saveAvdelingOmsetning(avdelingOmsetning);
		
	}
}
