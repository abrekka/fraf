package no.ica.fraf.gui.readsale;

import no.ica.fraf.common.Locker;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.pkg.AvdelingOmsetningPkgDAO;
import no.ica.fraf.gui.Login;
import no.ica.fraf.model.AvregningBasisType;

public class IntegratedBudgetSalesReader extends AbstractBudgetSalesReader{

	IntegratedBudgetSalesReader(ListHolder listHolder,
			Integer year, Integer period, Integer depFrom, Integer depTo,
			AvregningBasisType avregningBasisType,
			AvdelingOmsetningPkgDAO avdelingOmsetningPkgDAO, Login login,Locker locker,WindowInterface window) {
		super(listHolder,year, period, depFrom, depTo, avregningBasisType,
				avdelingOmsetningPkgDAO, login,locker,window);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String prepareImport() {
		return null;
	}

}
