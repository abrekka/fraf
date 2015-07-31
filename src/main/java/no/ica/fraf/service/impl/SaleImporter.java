package no.ica.fraf.service.impl;

import java.io.File;
import java.math.BigDecimal;

import no.ica.fraf.FrafException;
import no.ica.fraf.model.SapSale;
import no.ica.fraf.service.SapSaleManager;
import no.ica.fraf.service.SapXmlDataImporter;
import noNamespace.SALESDATADocument;
import noNamespace.PLANTDocument.PLANT;
import noNamespace.SALESDATADocument.SALESDATA;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class SaleImporter extends AbstractSapXmlDataImporter<SALESDATADocument>
		implements SapXmlDataImporter {
	private SapSaleManager sapSaleManager;

	@Inject
	public SaleImporter(SapSaleManager manager,
			@Named("base_data") final String baseDataDir,
			@Named("base_sales_file_name") final String filename,
			@Named("imported_dir") final String importedDir) {
		super(baseDataDir, filename, importedDir);
		sapSaleManager = manager;
	}

	@Override
	protected SALESDATADocument getXmlData(File xmlFile, XmlOptions xmlOptions)
			throws FrafException {
		try {
			return SALESDATADocument.Factory.parse(xmlFile, xmlOptions);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrafException(e);
		}
	}

	@Override
	protected void importData(SALESDATADocument xmlObject) throws FrafException {
		SALESDATA salesdata = xmlObject.getSALESDATA();
		importSales(salesdata);

	}

	private void importSales(SALESDATA salesdata) {
		PLANT[] plants = salesdata.getPLANTArray();
		for (PLANT plant : plants) {
			getAndSaveSapSale(plant);
			
		}
	}

	private void saveSapSale(SapSale sapSale) {
		if (sapSale != null) {
			sapSaleManager.save(sapSale);
		}

	}

	private void getAndSaveSapSale(PLANT plant) {
		
		if (plant.getSALES() != null) {
			Integer avdnr = Integer.valueOf(plant.getWERKS());
			Integer periode = Integer.valueOf(plant.getMONAT());
			Integer year = Integer.valueOf(plant.getGJAHR());
			
			setAndSaveSaleForPeriode(avdnr,year,periode,plant.getSALES(),plant.getBUDGET());
			
			year=periode==1?year-1:year;
			periode=periode==1?periode=12:(periode-1);
			
			
			setAndSaveSaleForPeriode(avdnr,year,periode,plant.getSALESPREV(),null);
			
		}
	}

	private void setAndSaveSaleForPeriode(Integer avdnr,Integer year,Integer periode,String sale,String budget) {
		SapSale sapSale = null;
		sapSale = sapSaleManager.findByAvdnrPeriode(avdnr, year, periode);
		sapSale = sapSale != null ? sapSale : new SapSale();
		sapSale.setDepartmentNr(avdnr);
		sapSale.setPeriode(periode);
		sapSale.setYear(year);
		if (!StringUtils.isEmpty(sale)) {
			sapSale.setSale(BigDecimal.valueOf(Double.valueOf(sale)));
		}
		if(!StringUtils.isEmpty(budget)){
			sapSale.setBudget(BigDecimal.valueOf(Double.valueOf(budget)));
		}
		saveSapSale(sapSale);
	}

}
