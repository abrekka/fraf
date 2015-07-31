package no.ica.elfa.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;

import no.ica.elfa.gui.handlers.ImportCreditViewHandler;
import no.ica.elfa.model.CreditImport;
import no.ica.elfa.service.CreditImportManager;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.dao.BuntTypeDAO;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntStatus;
import no.ica.fraf.model.BuntType;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Håndterer innlesing av kredittfiler
 * 
 * @author abr99
 * 
 */
public class CreditFileReader implements Threadable {
	/**
	 * 
	 */
	private File file;

	/**
	 * 
	 */
	private WindowInterface window;

	/**
	 * 
	 */
	private JLabel label;

	/**
	 * 
	 */
	private ApplUser frafApplUser;

	/**
	 * 
	 */
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

	/**
	 * 
	 */
	private List<CreditImport> creditList;

	/**
	 * 
	 */
	private ImportCreditViewHandler viewhandler;

	/**
	 * @param aFile
	 * @param aApplUser
	 * @param aWindow
	 * @param aCreditList
	 * @param handler
	 */
	public CreditFileReader(File aFile, ApplUser aApplUser,
			WindowInterface aWindow, List<CreditImport> aCreditList,
			ImportCreditViewHandler handler) {
		file = aFile;
		frafApplUser = aApplUser;

		window = aWindow;
		creditList = aCreditList;
		this.viewhandler = handler;
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
	 */
	public void enableComponents(boolean enable) {
	}

	/**
	 * Importerer fil
	 * 
	 * @return feilmelding
	 */
	private String importFile() {
		label.setText("Importerer kredittnotaer");
		CreditImportManager creditImportManager = (CreditImportManager) ModelUtil
				.getBean("creditImportManager");

		if (creditImportManager.fileIsImported(file.getName())) {
			{
				return "Filen er allerede lest inn";
			}
		}
		StringBuffer errorMsg = new StringBuffer();
		try {

			Date fromDate = null;
			Date toDate = null;
			FileReader creditFile = new FileReader(file);
			BufferedReader bufCreditFile = new BufferedReader(creditFile);
			String oneLine;
			String[] stringCol;

			BuntTypeDAO buntTypeDAO = (BuntTypeDAO) ModelUtil
					.getBean("buntTypeDAO");
			BuntType buntType = buntTypeDAO
					.findByKode(BuntTypeEnum.BATCH_TYPE_ELFA);

			BuntStatusDAO buntStatusDAO = (BuntStatusDAO) ModelUtil
					.getBean("buntStatusDAO");
			BuntStatus buntStatus = buntStatusDAO
					.findByKode(BuntStatusEnum.INNLEST);

			int lineCount = 0;
			CreditImport creditImport = null;
			Bunt bunt = new Bunt(null, buntType, buntStatus, Calendar
					.getInstance().getTime(), frafApplUser, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					null, null, null, null);

			// Fjerner overskrift
			bufCreditFile.readLine();

			while ((oneLine = bufCreditFile.readLine()) != null) {

				lineCount++;
				// Splitter linje i deler basert på semikolon
				stringCol = oneLine.split(";");
				if (stringCol.length == 4) {
					creditImport = new CreditImport();
					creditImport.setCreditDate(dateFormat.parse(stringCol[0]));

					if (fromDate == null
							|| fromDate.after(creditImport.getCreditDate())) {
						fromDate = creditImport.getCreditDate();
					}

					if (toDate == null
							|| toDate.before(creditImport.getCreditDate())) {
						toDate = creditImport.getCreditDate();
					}

					creditImport.setCode(stringCol[1]);
					creditImport.setPrice(BigDecimal.valueOf(Long
							.valueOf(stringCol[2])));
					creditImport.setDepNr(Integer.valueOf(stringCol[3]));
					creditImport.setCounter(lineCount);
					creditImport.setFileName(file.getName());
					bunt.addCreditImport(creditImport);
					creditList.add(creditImport);
				}

			}
			bunt.setFraDato(fromDate);
			bunt.setTilDato(toDate);
			BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");
			buntDAO.saveBunt(bunt);

		} catch (Exception ex) {
			errorMsg.append(ex.getMessage());
			ex.printStackTrace();

		}
		if (errorMsg.length() != 0) {
			return errorMsg.toString();
		}
		return null;
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	public Object doWork(Object[] params, JLabel labelInfo) {
		label = labelInfo;
		return importFile();
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
		if (object != null) {
			GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil", object
					.toString());
		}
		viewhandler.enableButtons();
	}

}
