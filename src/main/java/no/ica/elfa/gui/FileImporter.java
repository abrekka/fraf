package no.ica.elfa.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;

import no.ica.elfa.gui.handlers.FileImportViewHandler;
import no.ica.elfa.model.EepHead;
import no.ica.elfa.model.EepHeadLoad;
import no.ica.elfa.model.EepLineItemLoad;
import no.ica.elfa.service.EepHeadLoadManager;
import no.ica.elfa.service.EepHeadManager;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.util.GuiUtil;

import org.apache.commons.io.FileUtils;

/**
 * Håndterer import av fil
 * 
 * @author abr99
 * 
 */
public class FileImporter implements Threadable {
	/**
	 * 
	 */
	private File fileDir;

	/**
	 * 
	 */
	private EepHeadLoadManager eepHeadLoadManager;

	/**
	 * 
	 */
	private EepHeadManager eepHeadManager;

	/**
	 * 
	 */
	private WindowInterface window;

	/**
	 * 
	 */
	private FileImportViewHandler viewHandler;

	/**
	 * 
	 */
	private JLabel label;

	/**
	 * 
	 */
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	/**
	 * @param dir
	 * @param headLoadManager
	 * @param aWindow
	 * @param fileImportViewHandler
	 * @param aEepHeadManager
	 */
	public FileImporter(File dir, EepHeadLoadManager headLoadManager,
			WindowInterface aWindow,
			FileImportViewHandler fileImportViewHandler,
			EepHeadManager aEepHeadManager) {
		fileDir = dir;
		eepHeadLoadManager = headLoadManager;
		window = aWindow;
		viewHandler = fileImportViewHandler;
		eepHeadManager = aEepHeadManager;
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
	 */
	public void enableComponents(boolean enable) {
	}

	/**
	 * Importerer filer
	 * 
	 * @return feilmelding
	 */
	private String importFiles() {
		label.setText("Importerer salgsfiler");
		File[] importFiles = fileDir.listFiles();
		File bakDir = new File(fileDir.getAbsolutePath() + "/bak");
		String errorString = null;
		try {
			if (importFiles != null) {
				for (File file : importFiles) {
					if (file.isFile()) {
						importFile(file, bakDir);
					}

				}
			}

		} catch (Exception e) {
			errorString = e.getMessage();
			e.printStackTrace();
		}
		return errorString;
	}

	/**
	 * Importerer fil
	 * 
	 * @param file
	 * @param bakDir
	 * @throws FrafException
	 */
	private void importFile(File file, File bakDir) throws FrafException {
		StringBuffer errorMsg = new StringBuffer();
		try {
			FileReader salesFile = new FileReader(file);
			BufferedReader bufSaleFile = new BufferedReader(salesFile);
			String oneLine;

			int lineCount = 0;
			EepHeadLoad eepHeadLoad = null;
			EepHead eepHead = null;
			EepLineItemLoad eepLineItemLoad;

			Integer sequenceNumber = null;
			int lineCounter = 0;

			while ((oneLine = bufSaleFile.readLine()) != null) {

				lineCount++;

				if (oneLine.substring(0, 1).equalsIgnoreCase("H")) {
					lineCounter = 0;
					sequenceNumber = Integer.valueOf(oneLine.substring(60, 67));
					eepHeadLoad = null;
					eepHeadLoad = eepHeadLoadManager
							.findBySequenceNumber(sequenceNumber);
					eepHead = eepHeadManager
							.findBySequenceNumber(sequenceNumber);
					label.setText("Importerer salgsfiler - sekvensnummer "
							+ sequenceNumber);

					if (eepHeadLoad != null) {
						throw new FrafException("Salgsfil med sekvensnummer "
								+ sequenceNumber + " er allerede importert");
					}

					if (eepHead != null) {
						throw new FrafException("Salgsfil med sekvensnummer "
								+ sequenceNumber + " er allerede importert");
					}
					eepHeadLoad = new EepHeadLoad();
					eepHeadLoad.setSequenceNumber(Integer.valueOf(oneLine
							.substring(60, 67)));

					eepHeadLoad.setRecordType(oneLine.substring(0, 1));
					eepHeadLoad.setFileType("SAL");

					eepHeadLoad.setFileName(oneLine.substring(2, 22));
					eepHeadLoad.setFileDate(dateFormat.parse(oneLine.substring(
							23, 31)));
					eepHeadLoad.setPeriodStart(dateFormat.parse(oneLine
							.substring(32, 40)));
					eepHeadLoad.setPeriodEnd(dateFormat.parse(oneLine
							.substring(41, 49)));
					eepHeadLoad.setNumberOfRecords(Integer.valueOf(oneLine
							.substring(50, 59)));
					eepHeadLoad
							.setCreatedDate(Calendar.getInstance().getTime());
				} else if (oneLine.substring(0, 1).equalsIgnoreCase("R")) {
					label.setText("Importerer salgsfiler - sekvensnummer "
							+ sequenceNumber + " linje " + lineCounter++);
					eepLineItemLoad = new EepLineItemLoad();
					eepLineItemLoad.setRecordType(oneLine.substring(0, 1));
					eepLineItemLoad.setFileType("SAL");
					eepLineItemLoad.setSequenceNumber(Integer.valueOf(oneLine
							.substring(108, 117)));
					eepLineItemLoad.setFromDate(dateFormat.parse(oneLine
							.substring(2, 10)));
					eepLineItemLoad.setToDate(dateFormat.parse(oneLine
							.substring(11, 19)));
					eepLineItemLoad.setStoreNumber(Integer.valueOf(oneLine
							.substring(20, 38)));
					eepLineItemLoad.setArticleNumber(BigDecimal.valueOf(Long
							.valueOf(oneLine.substring(39, 53))));
					eepLineItemLoad.setArticleDescription(oneLine.substring(54,
							84));
					eepLineItemLoad.setNumberOfSoldVouchers(Integer
							.valueOf(oneLine.substring(85, 94)));
					eepLineItemLoad.setArticleOutPrice(BigDecimal.valueOf(Long
							.valueOf(oneLine.substring(95, 107))));
					eepLineItemLoad.setCreatedDate(Calendar.getInstance()
							.getTime());
					eepHeadLoad.addEepLineItemLoad(eepLineItemLoad);

				} else {
					errorMsg.append("Feil recordtype");
				}

			}
			label.setText("Importerer salgsfiler - sekvensnummer "
					+ sequenceNumber + " lagrer data");
			eepHeadLoadManager.saveEepHeadLoad(eepHeadLoad);

			salesFile.close();
			bufSaleFile.close();

			if (bakDir.exists()) {
				FileUtils.copyFileToDirectory(file, bakDir);
				FileUtils.forceDelete(file);
			}
		} catch (Exception ex) {
			errorMsg.append(ex.getMessage());
			ex.printStackTrace();
		}
		if (errorMsg.length() != 0) {
			throw new FrafException(errorMsg.toString());
		}

	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	public Object doWork(Object[] params, JLabel labelInfo) {
		label = labelInfo;
		return importFiles();
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
		if (object != null) {
			GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil", object
					.toString());
		}
		viewHandler.refresh();
	}

}
