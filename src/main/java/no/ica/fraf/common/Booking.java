/**
 * 
 */
package no.ica.fraf.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.swing.JLabel;

import no.ica.elfa.model.ActionEnum;
import no.ica.elfa.service.FileSequenceManager;
import no.ica.elfa.service.LazyLoadBatchEnum;
import no.ica.fraf.FrafException;
import no.ica.fraf.dao.LaasDAO;
import no.ica.fraf.dao.LaasTypeDAO;
import no.ica.fraf.enums.LaasTypeEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Laas;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.util.ThreadCaller;
import no.ica.fraf.xml.InvoiceInterface;

import org.apache.commons.io.FileUtils;

/**
 * Håndterer bokføring
 * 
 * @author abr99
 * 
 */
public class Booking implements Threadable {
	/**
	 * 
	 */
	private Batchable batchable;

	/**
	 * 
	 */
	private BatchManagerInterface batchManagerInterface;

	/**
	 * 
	 */
	private BookingInterface bookingInterface;

	/**
	 * 
	 */
	private BatchStatusManagerInterface batchStatusManagerInterface;

	/**
	 * 
	 */
	private FileSequenceManager fileSequenceManager;

	/**
	 * 
	 */
	private WindowInterface window;

	/**
	 * 
	 */
	private static final String AR_STARTPOST = "AR_STARTPOST";

	/**
	 * 
	 */
	private static final String AR_SLUTTPOST = "AR_SLUTTPOST";

	/**
	 * 
	 */
	private String exportPath;

	/**
	 * 
	 */
	private ThreadCaller caller;

	private SystemEnum systemEnum;

	//private Laas bookingLaas;

	private ApplUser applUser;
	private Locker locker;

	/**
	 * @param aBatchable
	 * @param aWindow
	 * @param aBatchManagerInterface
	 * @param aBookingInterface
	 * @param aBatchStatusManagerInterface
	 * @param aFileSequenceManager
	 * @param aThreadCaller
	 */
	public Booking(Batchable aBatchable, WindowInterface aWindow,
			BatchManagerInterface aBatchManagerInterface,
			BookingInterface aBookingInterface,
			BatchStatusManagerInterface aBatchStatusManagerInterface,
			FileSequenceManager aFileSequenceManager,
			ThreadCaller aThreadCaller, SystemEnum aSystemEnum,
			ApplUser aApplUser) {
		applUser = aApplUser;
		systemEnum = aSystemEnum;
		caller = aThreadCaller;
		fileSequenceManager = aFileSequenceManager;
		batchStatusManagerInterface = aBatchStatusManagerInterface;
		bookingInterface = aBookingInterface;
		batchManagerInterface = aBatchManagerInterface;
		batchable = aBatchable;
		window = aWindow;
		LaasTypeDAO laasTypeDAO=(LaasTypeDAO)ModelUtil.getBean("laasTypeDAO");
		LaasDAO laasDAO=(LaasDAO)ModelUtil.getBean("laasDAO");
		locker = new Locking(laasTypeDAO,laasDAO);
		
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
	 */
	public void enableComponents(boolean enable) {
		caller.updateEnablement();
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	public Object doWork(Object[] params, JLabel labelInfo) {
		String errorString = null;
		PrintWriter printWriter = null;
		//bookingLaas = locker.lock(applUser, window.getComponent(),LaasTypeEnum.BF, null);
		if (!locker.lock(applUser, window.getComponent(),
				LaasTypeEnum.BF, null)) {
			return null;
		}
		try {
			labelInfo.setText("Bokfører bunt");

			File file = null;
			labelInfo.setText("Bokfører bunt - henter fakturaer");
			batchManagerInterface.lazyLoadBatch(batchable,
					new LazyLoadBatchEnum[] { LazyLoadBatchEnum.INVOICES,
							LazyLoadBatchEnum.ELFA_INVOICES });

			String directPsDir = "";
			Set<InvoiceInterface> invoices = null;

			directPsDir = ApplParamUtil.getStringParam("direct_ps_export_path");
			exportPath = ApplParamUtil.getStringParam("ps_export_path");

			if (FrafMain.getInstance().isTest()) {
				exportPath += "/test/";
			}

			invoices = batchable.getInvoiceInterfaces(systemEnum);

			if (invoices != null && invoices.size() != 0) {
				String fileName = "tmp.bil";
				File dir = new File(exportPath + "\\");
				if (!dir.exists()) {
					if (!dir.mkdir()) {
						throw new FrafException("Kunne ikke lage katalog "
								+ exportPath);
					}
				}
				file = new File(exportPath + "/" + fileName);

				printWriter = new PrintWriter(new BufferedWriter(
						new FileWriter(file)));
				printWriter.println(AR_STARTPOST);
				labelInfo.setText("Bokfører bunt - skriver til fil");
				for (InvoiceInterface invoiceInterface : invoices) {
					bookingInterface.bookInvoiceLine(invoiceInterface,
							batchable, printWriter);
				}
				printWriter.println(AR_SLUTTPOST);

				if (printWriter != null) {
					printWriter.close();
				}
			}

			if (file != null && errorString == null) {

				checkPsFile(file, invoices.size());
				String newFullFileName = renameFile(file, bookingInterface);
				String newFileName = newFullFileName.substring(newFullFileName
						.lastIndexOf("/") + 1);
				BatchStatusInterface statusInterface = batchStatusManagerInterface
						.findStatus(batchable.getBatchStatus(),
								ActionEnum.BOKFOR);
				batchable.setBatchStatus(statusInterface);
				batchable.setFileName(newFileName);
				batchManagerInterface.saveBatch(batchable);

				FileUtils.copyFile(new File(newFullFileName), new File(
						directPsDir + "/" + newFileName));
			}
		} catch (IOException e) {
			errorString = e.getMessage();
			e.printStackTrace();
		} catch (FrafException frafEx) {
			errorString = frafEx.getMessage();
			frafEx.printStackTrace();
		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
		}

		return errorString;
	}

	/**
	 * Sjekker at bokføringsfil inneholder like mange fakturaer som bunt
	 * 
	 * @param file
	 * @param invoiceCount
	 * @throws FrafException
	 */
	private void checkPsFile(File file, int invoiceCount) throws FrafException {
		FileReader conditionFile = null;
		try {
			conditionFile = new FileReader(file);
			BufferedReader bufConFile = new BufferedReader(conditionFile);
			String oneLine;
			String[] stringCol;
			int lineCount = 0;
			int invoicesInFile = 0;
			while ((oneLine = bufConFile.readLine()) != null) {

				lineCount++;
				if (lineCount == 1) {
					if (!oneLine.equalsIgnoreCase("AR_STARTPOST")) {
						throw new FrafException("Fil mangler startport");
					}
				} else {

					stringCol = oneLine.split(";");
					if (stringCol != null && stringCol.length == 1
							&& stringCol[0].equalsIgnoreCase("AR_SLUTTPOST")) {
						break;
					}
					if ((stringCol == null || stringCol.length != 24)) {
						throw new FrafException("Linje " + lineCount
								+ " har for få kolonner");
					}
					if (stringCol[2].equalsIgnoreCase("D  ")) {
						invoicesInFile++;
					}
				}
			}

			if (invoiceCount != invoicesInFile) {
				throw new FrafException(
						"Ikke alle fakturaer er skrevet til bokføringsfil");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FrafException("Fil " + file.getName()
					+ " ble ikke funnet");
		} catch (IOException ioE) {
			throw new FrafException(ioE);
		} finally {
			try {
				conditionFile.close();
			} catch (IOException e) {

				e.printStackTrace();
				throw new FrafException(e);
			}
		}
	}

	/**
	 * Renamer fil fra tmp til reell fil
	 * 
	 * @param tmpFile
	 * @param bookingInterface1
	 * @return feilmelding
	 */
	private String renameFile(File tmpFile, BookingInterface bookingInterface1) {
		Integer seq = fileSequenceManager.getNextNumber();

		String fileName = bookingInterface1.getFileName(seq);
		String fullFileName = exportPath + "/" + fileName;
		File newFile = new File(fullFileName);
		tmpFile.renameTo(newFile);

		return fullFileName;
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
		if (object != null) {
			GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil", object
					.toString());
		}
		//locker.unlock(bookingLaas);
		locker.unlock();

	}

}