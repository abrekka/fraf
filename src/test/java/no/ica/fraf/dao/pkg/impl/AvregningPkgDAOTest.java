package no.ica.fraf.dao.pkg.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;
import no.ica.elfa.service.LazyLoadInvoiceEnum;
import no.ica.fraf.dao.ApplUserDAO;
import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.dao.BetingelseGruppeDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.dao.BuntTypeDAO;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.dao.RegnskapKladdDAO;
import no.ica.fraf.dao.pkg.AvregningPkgDAO;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingAvregningBelop;
import no.ica.fraf.model.AvdelingAvregningImport;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntStatus;
import no.ica.fraf.model.BuntType;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.model.RegnskapKladd;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

public class AvregningPkgDAOTest extends TestCase{
	private BuntDAO buntDAO;
	private FakturaDAO fakturaDAO;
	private Bunt bunt;
	private Faktura faktura;
	private boolean standalone;
	
	public AvregningPkgDAOTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
        standalone=isStandalone;
	}
	@Override
	protected void setUp() throws Exception {
		FrafMain.setStandalone(standalone);
		buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");
		fakturaDAO=(FakturaDAO)ModelUtil.getBean(FakturaDAO.DAO_NAME);
		setUpAvregning();
	}

	@Override
	protected void tearDown() throws Exception {
		RegnskapKladdDAO regnskapKladdDAO=(RegnskapKladdDAO)ModelUtil.getBean(RegnskapKladdDAO.DAO_NAME);
		List<RegnskapKladd> kladder=regnskapKladdDAO.findRegnskapKladdByBuntId(bunt.getBuntId());
		for(RegnskapKladd kladd:kladder){
			regnskapKladdDAO.removeObject(kladd.getRegnskapKladdId());
		}
		if(faktura!=null){
			fakturaDAO.removeFaktura(faktura.getFakturaId());
		}
		if(bunt!=null){
			buntDAO.removeBunt(bunt.getBuntId());
		}
	}

	public void testKjorAvregningFakturer() throws Exception {
		AvregningPkgDAO avregningPkgDAO=(AvregningPkgDAO)ModelUtil.getBean(AvregningPkgDAO.DAO_NAME);
		assertNotNull(bunt);
		avregningPkgDAO.kjorAvregning(bunt.getBuntId());
		avregningPkgDAO.lagFakturaer(bunt.getBuntId(), Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), "test");
		
		
		List<Faktura> invoices=fakturaDAO.findByBuntId(bunt.getBuntId());
		assertNotNull(invoices);
		assertEquals(1, invoices.size());
		faktura=invoices.get(0);
		assertEquals(Integer.valueOf(2008), faktura.getAar());
		assertEquals(Integer.valueOf(1), faktura.getFraPeriode());
		assertEquals(Integer.valueOf(12), faktura.getTilPeriode());
		assertEquals(BigDecimal.valueOf(-9758.75), faktura.getTotalBelop());
		assertEquals("Avregning 2008 Kreditnota", faktura.getFakturaTittel());
		assertEquals(BigDecimal.valueOf(-7807), faktura.getBelop());
		assertEquals(BigDecimal.valueOf(-1951.75), faktura.getMvaBelop());
		
		fakturaDAO.lazyLoad(faktura, new LazyLoadInvoiceEnum[]{LazyLoadInvoiceEnum.INVOCIE_ITEM});
		Set<FakturaLinje> linjer=faktura.getFakturaLinjes();
		assertNotNull(linjer);
		assertEquals(1, linjer.size());
		FakturaLinje linje = linjer.iterator().next();
		assertEquals("Franchise-avgift", linje.getLinjeBeskrivelse());
		assertEquals(BigDecimal.valueOf(-7807), linje.getBelop());
		assertEquals(BigDecimal.valueOf(1568789.36), linje.getOmsetningBelop());
		assertEquals(BigDecimal.valueOf(1178438), linje.getAvregningBelop());
		assertEquals("03", linje.getMvaKode());
		assertEquals(BigDecimal.valueOf(-1951.75), linje.getMvaBelop());
		assertEquals(BigDecimal.valueOf(-390351.36), linje.getGrunnlagBelop());
		assertEquals(BigDecimal.valueOf(-9758.75), linje.getTotalBelop());
	}

	private void setUpAvregning() {
		BuntTypeDAO buntTypeDAO = (BuntTypeDAO) ModelUtil
		.getBean("buntTypeDAO");
		BuntStatusDAO buntStatusDAO = (BuntStatusDAO) ModelUtil
		.getBean("buntStatusDAO");
		
		BuntType buntType = buntTypeDAO
				.findByKode(BuntTypeEnum.BATCH_TYPE_INN_AVR);
		ApplUserDAO applUserDAO=(ApplUserDAO)ModelUtil.getBean(ApplUserDAO.DAO_NAME);
		BetingelseGruppeDAO betingelseGruppeDAO=(BetingelseGruppeDAO)ModelUtil.getBean(BetingelseGruppeDAO.DAO_NAME);
		ApplUser applUser1 = applUserDAO.findByUser("abr99", null);
		BetingelseGruppe betingelseGruppe = betingelseGruppeDAO
		.findByName("Franchiseavgift");
		BuntStatus buntStatus = buntStatusDAO.findByKode(BuntStatusEnum.NY);
		bunt = new Bunt(null, buntType, buntStatus, Calendar.getInstance()
				.getTime(), applUser1, null, null, null, null, null, null,
				null, betingelseGruppe, null, null, null, null, null, null,
				null, null, null);
		AvdelingDAO avdelingDAO = (AvdelingDAO) ModelUtil
		.getBean("avdelingDAO");
		Avdeling avdeling = avdelingDAO.findByAvdnr(Integer.valueOf(2282));
		AvdelingAvregningImport avdelingAvregningImport = new AvdelingAvregningImport(avdeling,
				2282, null, bunt, null, 2008);
		bunt.addAvdelingAvregningImport(avdelingAvregningImport);
		AvdelingAvregningBelop avdelingAvregningBelop = new AvdelingAvregningBelop(
				avdelingAvregningImport, null, BigDecimal.valueOf(1178438), 1, null);
		avdelingAvregningImport
				.addAvdelingAvregningBelop(avdelingAvregningBelop);
		
		for(int i=2;i<=12;i++){
			avdelingAvregningBelop = new AvdelingAvregningBelop(
					avdelingAvregningImport, null, BigDecimal.valueOf(0), i, null);
			avdelingAvregningImport
					.addAvdelingAvregningBelop(avdelingAvregningBelop);
		}
		buntDAO.saveBunt(bunt);
	}
}
