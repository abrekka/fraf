package no.ica.fraf.gui.readsale;

import no.ica.fraf.common.Locker;
import no.ica.fraf.common.Locking;
import no.ica.fraf.dao.AvdelingOmsetningDAO;
import no.ica.fraf.dao.AvregningBasisTypeDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.LaasDAO;
import no.ica.fraf.dao.LaasTypeDAO;
import no.ica.fraf.dao.pkg.AvdelingOmsetningPkgDAO;
import no.ica.fraf.dao.pkg.BuntPkgDAO;
import no.ica.fraf.gui.Login;
import no.ica.fraf.gui.LoginDialog;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.AvregningBasisType;
import no.ica.fraf.service.SapSaleManager;

import org.jmock.integration.junit3.MockObjectTestCase;

public class BudgetSalesreaderSelectorTest extends MockObjectTestCase {

	public void testSelectIntegratedBudgetSalesReader() {
		Login login = new LoginDialog();
		AvdelingOmsetningPkgDAO avdelingOmsetningPkgDAO = mock(AvdelingOmsetningPkgDAO.class);
		AvregningBasisTypeDAO avregningBasisTypeDAO = mock(AvregningBasisTypeDAO.class);
		AvdelingOmsetningDAO avdelingOmsetningDAO = mock(AvdelingOmsetningDAO.class);
		LaasTypeDAO laasTypeDAO = mock(LaasTypeDAO.class);
		LaasDAO laasDAO = mock(LaasDAO.class);
		BuntDAO buntDAO = mock(BuntDAO.class);
		BuntPkgDAO buntPkgDAO = mock(BuntPkgDAO.class);
		SapSaleManager sapSaleManager = mock(SapSaleManager.class);
		String baseDataDir = null;
		String fileName = null;
		Locker locker = new Locking(laasTypeDAO,laasDAO);
		ReadBudgetSaleFrame readBudgetSaleFrame = new ReadBudgetSaleFrame(
				login, avdelingOmsetningPkgDAO, avregningBasisTypeDAO,
				avdelingOmsetningDAO, laasTypeDAO, laasDAO, buntDAO,
				buntPkgDAO, sapSaleManager, baseDataDir, fileName,locker,null);
		Integer year = null;
		Integer period = null;
		Integer depFrom = null;
		Integer depTo = null;
		AvregningBasisType avregningBasisType = null;

		AbstractBudgetSalesReader abstractBudgetSalesReader = BudgetSalesReaderEnum
				.getBudgetSalesReader(false).getBudgetSalesReader(
						null, 
						year, period, depFrom, depTo,
						avregningBasisType, avdelingOmsetningPkgDAO, login,
						sapSaleManager, baseDataDir, fileName,locker,null,null);
		assertEquals(
				true,
				abstractBudgetSalesReader instanceof IntegratedBudgetSalesReader);
	}

	public void testSelectStandaloneBudgetSalesReader() {
		Login login = new LoginDialog();
		AvdelingOmsetningPkgDAO avdelingOmsetningPkgDAO = mock(AvdelingOmsetningPkgDAO.class);
		AvregningBasisTypeDAO avregningBasisTypeDAO = mock(AvregningBasisTypeDAO.class);
		AvdelingOmsetningDAO avdelingOmsetningDAO = mock(AvdelingOmsetningDAO.class);
		LaasTypeDAO laasTypeDAO = mock(LaasTypeDAO.class);
		LaasDAO laasDAO = mock(LaasDAO.class);
		BuntDAO buntDAO = mock(BuntDAO.class);
		BuntPkgDAO buntPkgDAO = mock(BuntPkgDAO.class);
		SapSaleManager sapSaleManager = mock(SapSaleManager.class);
		String baseDataDir = null;
		String fileName = null;
		Locker locker = new Locking(laasTypeDAO,laasDAO);
		ReadBudgetSaleFrame readBudgetSaleFrame = new ReadBudgetSaleFrame(
				login, avdelingOmsetningPkgDAO, avregningBasisTypeDAO,
				avdelingOmsetningDAO, laasTypeDAO, laasDAO, buntDAO,
				buntPkgDAO, sapSaleManager, baseDataDir, fileName,locker,null);
		Integer year = null;
		Integer period = null;
		Integer depFrom = null;
		Integer depTo = null;
		AvregningBasisType avregningBasisType = null;

		AbstractBudgetSalesReader abstractBudgetSalesReader = BudgetSalesReaderEnum
				.getBudgetSalesReader(true).getBudgetSalesReader(
						null, 
						year, period, depFrom, depTo,
						avregningBasisType, avdelingOmsetningPkgDAO, login,
						sapSaleManager, baseDataDir, fileName,locker,null,null);
		assertEquals(true,
				abstractBudgetSalesReader instanceof SapBudgetSalesReader);
	}
}
