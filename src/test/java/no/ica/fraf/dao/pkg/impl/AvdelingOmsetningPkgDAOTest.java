package no.ica.fraf.dao.pkg.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import junit.framework.TestCase;
import no.ica.fraf.FrafException;
import no.ica.fraf.dao.ApplUserDAO;
import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.dao.AvdelingOmsetningDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.dao.BuntTypeDAO;
import no.ica.fraf.dao.pkg.AvdelingOmsetningPkgDAO;
import no.ica.fraf.dao.pkg.BuntPkgDAO;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.importing.BudgetImport;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingOmsetning;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntStatus;
import no.ica.fraf.model.BuntType;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

public class AvdelingOmsetningPkgDAOTest extends TestCase {
	

	private Integer buntId;
	private Avdeling avdeling;
	private AvdelingOmsetningPkgDAO avdelingOmsetningPkgDAO;
	private AvdelingDAO avdelingDAO;
	private BigDecimal oms;
	private boolean standalone;
	
	public AvdelingOmsetningPkgDAOTest(boolean isStandalone,BigDecimal omsetning) {
		BaseManager.setTest(true);
		FrafMain.setStandalone(isStandalone);
		oms=omsetning;
		standalone=isStandalone;
	}

	@Override
	protected void setUp() throws Exception {
		FrafMain.setStandalone(standalone);
		avdelingOmsetningPkgDAO = (AvdelingOmsetningPkgDAO) ModelUtil
		.getBean(AvdelingOmsetningPkgDAO.DAO_NAME);
		avdelingDAO=(AvdelingDAO)ModelUtil.getBean(AvdelingDAO.DAO_NAME);
	}

	@Override
	protected void tearDown() throws Exception {
		if (buntId != null) {
			BuntPkgDAO buntPkgDAO = (BuntPkgDAO) ModelUtil
					.getBean("buntPkgDAO");
			buntPkgDAO.slettBunt(buntId);
		}
		if(avdeling!=null){
			AvdelingOmsetningDAO avdelingOmsetningDAO=(AvdelingOmsetningDAO)ModelUtil.getBean(AvdelingOmsetningDAO.DAO_NAME);
			avdelingOmsetningDAO.removeAvdelingOmsetningForAvdeling(avdeling);
		}
	}

	public void testReadSale() throws FrafException {
		avdeling = avdelingDAO.findByAvdnr(4872);
		buntId = avdelingOmsetningPkgDAO.lesInnOmsetning(1, 10, 2009, 5, 4872,
				4872);
		assertNotNull(buntId);
		AvdelingOmsetningDAO avdelingOmsetningDAO = (AvdelingOmsetningDAO) ModelUtil
				.getBean("avdelingOmsetningDAO");
		List<AvdelingOmsetning> omsetning = avdelingOmsetningDAO
				.findByBunt(buntId);
		assertEquals(1, omsetning.size());
		assertEquals(oms, omsetning.get(0)
				.getBelop());
	}

	public void testImportBudget() throws Exception {
		BudgetImport budgetImport = new BudgetImport();
		budgetImport.setAvdnr(1499);
		budgetImport.setNavn("test");
		budgetImport.setBudsjett(BigDecimal.valueOf(1000));

		BuntDAO buntDAO=(BuntDAO)ModelUtil.getBean(BuntDAO.DAO_NAME);
		BuntStatusDAO buntStatusDAO=(BuntStatusDAO)ModelUtil.getBean(BuntStatusDAO.DAO_NAME);
		BuntTypeDAO buntTypeDAO=(BuntTypeDAO)ModelUtil.getBean(BuntTypeDAO.DAO_NAME);
		ApplUserDAO applUserDAO=(ApplUserDAO)ModelUtil.getBean(ApplUserDAO.DAO_NAME);
		
		
		BuntStatus buntStatus = buntStatusDAO.findByKode(BuntStatusEnum.NY);
		BuntType buntType = buntTypeDAO
				.findByKode(BuntTypeEnum.BATCH_TYPE_BUDGET);
		ApplUser currentApplUser=applUserDAO.findByUser("abr99", null);

		Bunt bunt = new Bunt();
		bunt.setAar(2009);
		bunt.setApplUser(currentApplUser);
		bunt.setBuntStatus(buntStatus);
		bunt.setBuntType(buntType);
		bunt.setOpprettetDato(Calendar.getInstance().getTime());
		buntDAO.saveBunt(bunt);
		buntId=bunt.getBuntId();
		
		avdeling = avdelingDAO.findByAvdnr(budgetImport
				.getAvdnr());
		
		if (avdeling != null) {
			
		
			avdelingOmsetningPkgDAO.settInnManueltBudsjett(
					currentApplUser.getUserId(), budgetImport
							.getAvdnr(), 2009, budgetImport
							.getBudsjett(), avdeling
							.getAvdelingId(), bunt.getBuntId());
		}
		
		AvdelingOmsetningDAO avdelingOmsetningDAO=(AvdelingOmsetningDAO)ModelUtil.getBean(AvdelingOmsetningDAO.DAO_NAME);
		AvdelingOmsetning omsetning=avdelingOmsetningDAO.findBudsjettByAvdelingAndPeriod(avdeling, 2009, 1);
		assertNotNull(omsetning);
		assertEquals(BigDecimal.valueOf(83.33), omsetning.getBelop());
	}
}
