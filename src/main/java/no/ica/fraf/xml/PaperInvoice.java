package no.ica.fraf.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Hashtable;

import no.ica.elfa.model.Invoice;
import no.ica.fraf.FrafException;
import no.ica.fraf.util.GuiUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * Klasse som håndterer butikker som skal ha papirfaktura selv om de ligger i BW
 * Butikkene ligger i excelfiler under katalog /franchise_paper
 * 
 * @author abr99
 * 
 */
public class PaperInvoice {
	/**
	 * 
	 */
	private static Hashtable<Integer, Boolean> stores = null;

	public void init() throws FrafException {
		if (stores == null) {
			stores = new Hashtable<Integer, Boolean>();
			readFiles();
		}
	}

	private void readFiles() throws FrafException {
		try {
			File fileDir = new File("franchise_paper");

			if (fileDir.exists()) {
				readAllExcelFiles(fileDir);
			} else {
				throw new FrafException(
						"Kunne ikke finne katalog franchise_paper");
			}
		} catch (FileNotFoundException e) {
			throw new FrafException(e);
		} catch (IOException e) {
			throw new FrafException(e);
		} catch (FrafException e) {
			throw new FrafException(e);
		}
	}

	private void readAllExcelFiles(File fileDir) throws IOException,
			FileNotFoundException {
		File[] files = fileDir.listFiles(new ExcelFileNameFilter());

		for (File file : files) {
			readExcelFile(file);

		}
	}

	private void readExcelFile(File file) throws IOException, FileNotFoundException {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file
				.getAbsolutePath()));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		int rows = sheet.getLastRowNum();
		for (int i = 1; i <= rows; i++) {
			readRow(sheet, i);
		}
	}

	private void readRow(HSSFSheet sheet, int i) {
		HSSFCell cell;
		HSSFRow row;
		Double cellValue;
		
		row = sheet.getRow(i);
		cell = row.getCell((short) 1);
		cellValue = cell.getNumericCellValue();
		Integer storeNo = Integer.valueOf(cellValue.intValue());
		cell = row.getCell((short) 0);
		cellValue = cell.getNumericCellValue();
		Boolean onlyPaper = GuiUtil.convertNumberToBoolean(Integer.valueOf(cellValue.intValue()));
		if(storeNo==5355){
			System.out.println();
		}
		stores.put(storeNo, onlyPaper);
	}

	public boolean paperInvoice(Invoice invoice) throws FrafException {
		return paperInvoice(invoice.getStoreNo());
	}

	public boolean paperInvoice(InvoiceInterface invoiceInterface)
			throws FrafException {
		return paperInvoice(invoiceInterface.getStoreNo());
	}

	public boolean paperInvoice(Integer avdnr) throws FrafException {
		init();

		boolean usePaper = false;
		if (stores.containsKey(avdnr)) {
			usePaper = true;
		}
		return usePaper;
	}
	
	public boolean onlyPaperInvoice(Integer avdnr) throws FrafException {
		init();

		boolean onlyPaper = false;
		if (stores.containsKey(avdnr)) {
			onlyPaper = stores.get(avdnr);
		}
		return onlyPaper;
	}

	/**
	 * Filfilter som bare tillater excelfiler
	 * 
	 * @author abr99
	 * 
	 */
	private class ExcelFileNameFilter implements FilenameFilter {

		/**
		 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
		 */
		public boolean accept(File dir, String name) {
			return name.endsWith(".xls");
		}

	}


}
