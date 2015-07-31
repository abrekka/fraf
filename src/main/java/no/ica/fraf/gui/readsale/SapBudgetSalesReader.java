package no.ica.fraf.gui.readsale;

import no.ica.fraf.FrafException;
import no.ica.fraf.common.Locker;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.pkg.AvdelingOmsetningPkgDAO;
import no.ica.fraf.gui.Login;
import no.ica.fraf.model.AvregningBasisType;
import no.ica.fraf.service.SapSaleManager;
import no.ica.fraf.service.impl.SaleImporter;

public class SapBudgetSalesReader extends AbstractBudgetSalesReader{
	private SapSaleManager sapSaleManager;
	private String baseDataDir;
	private String fileName;
	private String importedDir;
	public SapBudgetSalesReader(ListHolder listHolder, 
			Integer year,
			Integer period, Integer depFrom, Integer depTo,
			AvregningBasisType avregningBasisType,
			AvdelingOmsetningPkgDAO avdelingOmsetningPkgDAO, Login login,
			SapSaleManager aSapSaleManager,String aBaseDataDir,String aFileName,Locker locker,String aImportedDir,WindowInterface window) {
		super(listHolder, 
				year, period, depFrom, depTo, avregningBasisType,
				avdelingOmsetningPkgDAO, login,locker,window);
		importedDir=aImportedDir;
		sapSaleManager=aSapSaleManager;
		baseDataDir=aBaseDataDir;
		fileName=aFileName;
	}

	@Override
	protected String prepareImport() {
		String errorString="";
		try {
			importSapSaleData();
		} catch (FrafException e) {
			e.printStackTrace();
			errorString=e.getMessage();
		}
		return errorString;
	}

	private void importSapSaleData() throws FrafException {
		SaleImporter saleImporter = new SaleImporter(sapSaleManager,baseDataDir,fileName,importedDir);
		saleImporter.importXmlData();
	}

}
