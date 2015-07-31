package no.ica.fraf.gui.readsale;

import java.util.Map;

import no.ica.fraf.common.Locker;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.pkg.AvdelingOmsetningPkgDAO;
import no.ica.fraf.gui.Login;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.AvregningBasisType;
import no.ica.fraf.service.SapSaleManager;

public enum BudgetSalesReaderEnum {
	INTEGRATED(false) {
		@Override
		public AbstractBudgetSalesReader getBudgetSalesReader(
				ListHolder listHolder, 
				Integer year,
				Integer period, Integer depFrom, Integer depTo,
				AvregningBasisType avregningBasisType,
				AvdelingOmsetningPkgDAO avdelingOmsetningPkgDAO,
				Login login, SapSaleManager sapSaleManager, String baseDataDir, String fileName,Locker locker,String importedDir,WindowInterface window) {
			return new IntegratedBudgetSalesReader(listHolder, 
					year,
					period, depFrom, depTo, avregningBasisType,
					avdelingOmsetningPkgDAO, login,locker,window);
		}
	},
	STANDALONE(true) {
		@Override
		public AbstractBudgetSalesReader getBudgetSalesReader(
				ListHolder listHolder, 
				Integer year,
				Integer period, Integer depFrom, Integer depTo,
				AvregningBasisType avregningBasisType,
				AvdelingOmsetningPkgDAO avdelingOmsetningPkgDAO,
				Login login, SapSaleManager sapSaleManager, String baseDataDir, String fileName,Locker locker,String importedDir,WindowInterface window) {
			return new SapBudgetSalesReader(listHolder, 
					year, period,
					depFrom, depTo, avregningBasisType,
					avdelingOmsetningPkgDAO, login,
					sapSaleManager, baseDataDir, fileName,locker,importedDir,window);
		}
	}

	;
	Boolean standalone;
	private static Map<Boolean, BudgetSalesReaderEnum> readers;

	static {
		readers = new java.util.Hashtable<Boolean, BudgetSalesReaderEnum>();
		BudgetSalesReaderEnum[] array = BudgetSalesReaderEnum.values();
		for (BudgetSalesReaderEnum aEnum : array) {
			readers.put(aEnum.isStandalone(), aEnum);
		}
	}

	private BudgetSalesReaderEnum(boolean isStandalone) {
		standalone = isStandalone;
	}

	public Boolean isStandalone() {
		return standalone;
	}

	public static BudgetSalesReaderEnum getBudgetSalesReader(boolean isStandalone) {
		return readers.get(isStandalone);
	}

	public abstract AbstractBudgetSalesReader getBudgetSalesReader(
			ListHolder listHolder, 
			Integer year,
			Integer period, Integer depFrom, Integer depTo,
			AvregningBasisType avregningBasisType,
			AvdelingOmsetningPkgDAO avdelingOmsetningPkgDAO, Login login,
			SapSaleManager sapSaleManager, String baseDataDir, String fileName,Locker locker,String importedDir,WindowInterface window);
}
