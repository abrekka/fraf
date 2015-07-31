package no.ica.fraf.gui.handlers;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.dao.BetingelseGruppeDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.dao.BuntTypeDAO;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.dao.FakturaVDAO;
import no.ica.fraf.dao.pkg.BuntPkgDAO;
import no.ica.fraf.dao.pkg.ImportBetingelsePkgDAO;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.gui.DeductBatchView;
import no.ica.fraf.gui.DeductDetailsView;
import no.ica.fraf.gui.DeductImportView;
import no.ica.fraf.gui.DeductInvoiceView;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingAvregningBelop;
import no.ica.fraf.model.AvdelingAvregningImport;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntStatus;
import no.ica.fraf.model.BuntType;
import no.ica.fraf.service.AvdelingAvregningBelopManager;
import no.ica.fraf.service.AvdelingAvregningImportManager;
import no.ica.fraf.service.AvdelingAvregningManager;
import no.ica.fraf.service.EflowUsersVManager;
import no.ica.fraf.service.TotalAvregningVManager;
import no.ica.fraf.util.ExcelUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.binding.beans.PropertyAdapter;

/**
 * Hjelpeklasse for visning av avregning og håndterer funksjonalitet rundt dette
 * 
 * @author abr99
 * 
 */
public class DeductViewHandler implements Closeable {
	/**
	 * 
	 */
	FileHolder fileHolder;

	/**
	 * 
	 */
	private PropertyAdapter adapter;

	/**
	 * 
	 */
	BuntDAO buntDAO;

	/**
	 * 
	 */
	JButton buttonImport;

	/**
	 * 
	 */
	private ApplUser applUser;

	/**
	 * 
	 */
	List<Bunt> batchList;

	/**
	 * 
	 */
	private DeductImportViewHandler deductImportViewHandler;

	/**
	 * 
	 */
	private AvdelingAvregningBelopManager avdelingAvregningBelopManager;

	/**
	 * 
	 */
	private AvdelingAvregningManager avdelingAvregningManager;

	/**
	 * 
	 */
	AvdelingAvregningImportManager avdelingAvregningImportManager;

	/**
	 * 
	 */
	private FakturaDAO fakturaDAO;

	/**
	 * 
	 */
	BetingelseGruppeDAO betingelseGruppeDAO;

	/**
	 * 
	 */
	private BuntPkgDAO buntPkgDAO;

	/**
	 * 
	 */
	private String excelDir;

	/**
	 * 
	 */
	private TotalAvregningVManager totalAvregningVManager;

	/**
	 * 
	 */
	//private FakturaVDAO fakturaVDAO;

	/**
	 * 
	 */
	private ImportBetingelsePkgDAO importBetingelsePkgDAO;

	/**
	 * 
	 */
	//private EflowUsersVManager eflowUsersVManager;

	private DeductBatchViewHandler deductBatchViewHandler;

	/**
	 * @param aBuntDAO
	 * @param aApplUser
	 * @param aAvdelingAvregningBelopManager
	 * @param aAvdelingAvregningManager
	 * @param aFakturaDAO
	 * @param aBetingelseGruppeDAO
	 * @param aBuntPkgDAO
	 * @param aExcelDir
	 * @param aTotalAvregningVManager
	 * @param aFakturaVDAO
	 * @param aImportBetingelsePkgDAO
	 * @param aAvdelingAvregningImportManager
	 * @param aEflowUsersVManager
	 */
	public DeductViewHandler(BuntDAO aBuntDAO, ApplUser aApplUser,
			AvdelingAvregningBelopManager aAvdelingAvregningBelopManager,
			AvdelingAvregningManager aAvdelingAvregningManager,
			FakturaDAO aFakturaDAO, BetingelseGruppeDAO aBetingelseGruppeDAO,
			BuntPkgDAO aBuntPkgDAO, String aExcelDir,
			TotalAvregningVManager aTotalAvregningVManager,
			//FakturaVDAO aFakturaVDAO,
			ImportBetingelsePkgDAO aImportBetingelsePkgDAO,
			AvdelingAvregningImportManager aAvdelingAvregningImportManager
			//,EflowUsersVManager aEflowUsersVManager
			) {
		//eflowUsersVManager = aEflowUsersVManager;
		avdelingAvregningImportManager = aAvdelingAvregningImportManager;
		importBetingelsePkgDAO = aImportBetingelsePkgDAO;
		//fakturaVDAO = aFakturaVDAO;
		totalAvregningVManager = aTotalAvregningVManager;
		excelDir = aExcelDir;
		buntPkgDAO = aBuntPkgDAO;
		betingelseGruppeDAO = aBetingelseGruppeDAO;
		fakturaDAO = aFakturaDAO;
		avdelingAvregningManager = aAvdelingAvregningManager;
		avdelingAvregningBelopManager = aAvdelingAvregningBelopManager;

		applUser = aApplUser;
		buntDAO = aBuntDAO;
		fileHolder = new FileHolder();
		adapter = new PropertyAdapter(fileHolder, "fileName", true);
	}

	/**
	 * Lager tekstfelt for filnavn
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldFileName() {
		JTextField textField = BasicComponentFactory.createTextField(adapter);
		textField.setName("TextFieldFileName");
		return textField;
	}

	/**
	 * Lager knapp for å velge fil
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonSelectFile(WindowInterface window) {
		JButton button = new JButton(new SelectFileAction(window));
		button.setName("ButtonSelectFile");
		return button;

	}

	/**
	 * Lager knapp for å importere fil
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonImport(WindowInterface window) {
		buttonImport = new JButton(new ImportAction(window, applUser));
		buttonImport.setEnabled(false);
		buttonImport.setName("ButtonImport");
		return buttonImport;

	}

	/**
	 * Lager panel for bunt
	 * 
	 * @param window
	 * @return panel
	 */
	public JPanel getBatchPanel(WindowInterface window) {
		deductBatchViewHandler = new DeductBatchViewHandler(buntDAO,
				buntPkgDAO, totalAvregningVManager, avdelingAvregningManager,
				excelDir, applUser, 
				//fakturaVDAO, 
				importBetingelsePkgDAO);
		batchList = deductBatchViewHandler.getBatchList();
		return new DeductBatchView(deductBatchViewHandler).buildPanel(window);
	}

	/**
	 * Lager panel for import
	 * 
	 * @return panel
	 */
	public JPanel getImportPanel() {
		deductImportViewHandler = new DeductImportViewHandler(null,
				deductBatchViewHandler, avdelingAvregningBelopManager);
		return new DeductImportView(deductImportViewHandler).buildPanel();
	}

	/**
	 * Lager panel for avregning
	 * 
	 * @param window
	 * @return panel
	 */
	public JPanel getDeductPanel(WindowInterface window) {
		return new DeductDetailsView(new DeductDetailsViewHandler(
				deductImportViewHandler, avdelingAvregningManager, excelDir,
				applUser, avdelingAvregningImportManager)).buildPanel(window);
	}

	/**
	 * Lager panel for fakturaer
	 * 
	 * @param window
	 * @return panel
	 */
	public JPanel getInvoicePanel(WindowInterface window) {
		return new DeductInvoiceView(new DeductInvoiceViewHandler(
				deductBatchViewHandler, fakturaDAO, applUser
				)).buildPanel(window);
	}

	/**
	 * Lager avbrytknapp
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonCancel(WindowInterface window) {
		return new CancelButton(window, this);
	}

	/**
	 * Håndterer filinformasjon
	 * 
	 * @author abr99
	 * 
	 */
	public class FileHolder extends Model {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private File file;

		/**
		 * @return filnavn
		 */
		public String getFileName() {
			if (file != null) {
				return file.getAbsolutePath();
			}
			return "";
		}

		/**
		 * @param fileName
		 */
		public void setFileName(String fileName) {
			String oldName = getFileName();
			file = new File(fileName);

			firePropertyChange("fileName", oldName, fileName);
		}

		/**
		 * @return fil
		 */
		public File getFile() {
			return file;
		}

		/**
		 * @param file
		 */
		public void setFile(File file) {
			setFileName(file.getAbsolutePath());
		}
	}

	/**
	 * Selektering av fil
	 * 
	 * @author abr99
	 * 
	 */
	private class SelectFileAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param aWindow
		 */
		public SelectFileAction(WindowInterface aWindow) {
			super("...");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser fileChooser = new JFileChooser();
			File defaultDir = new File("innfiler");
			fileChooser.setCurrentDirectory(defaultDir);
			fileChooser.setApproveButtonText("Save");

			if (fileChooser.showOpenDialog(window.getComponent()) == JFileChooser.APPROVE_OPTION) {
				fileHolder.setFile(fileChooser.getSelectedFile());

				buttonImport.setEnabled(true);
			}

		}
	}

	/**
	 * Import
	 * 
	 * @author abr99
	 * 
	 */
	private class ImportAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * 
		 */
		private ApplUser applUser1;

		/**
		 * @param aWindow
		 * @param aApplUser
		 */
		public ImportAction(WindowInterface aWindow, ApplUser aApplUser) {
			super("Importer");
			applUser1 = aApplUser;
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			String yearString = JOptionPane.showInputDialog(window
					.getComponent(), "Årstall");
			
			if(!StringUtils.isNumeric(yearString)||StringUtils.isBlank(yearString)){
				GuiUtil
				.showConfirmDialog(
					"Klarte ikke lese årstall",
						"Feil som ikke er håndtert");

			}
			
			GuiUtil.runInThreadWheel(window.getRootPane(), new DeductImporter(
					fileHolder.getFile(), applUser1, window, Integer
							.valueOf(yearString)), null);

		}
	}

	/**
	 * @see no.ica.elfa.gui.buttons.Closeable#canClose(java.lang.String)
	 */
	public boolean canClose(String actionString) {
		return true;
	}

	/**
	 * Import
	 * 
	 * @author abr99
	 * 
	 */
	private class DeductImporter implements Threadable {
		/**
		 * 
		 */
		private File importFile;

		/**
		 * 
		 */
		private ApplUser applUser1;

		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * 
		 */
		private Integer year;

		/**
		 * @param aImportFile
		 * @param aApplUser
		 * @param aWindow
		 * @param aYear
		 */
		public DeductImporter(File aImportFile, ApplUser aApplUser,
				WindowInterface aWindow, Integer aYear) {
			year = aYear;
			window = aWindow;
			applUser1 = aApplUser;
			importFile = aImportFile;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
		 */
		public void enableComponents(boolean enable) {
		}

		/**
		 * Importerer fra excelfil
		 * 
		 * @param file
		 * @throws FrafException
		 */
		private void importDeductFromExcel(File file) throws FrafException {
			String colValue = null;
			int row = 1;
			try {
				ExcelUtil excelUtil = new ExcelUtil(null);
				excelUtil.openExcelFileForReading(file);

				AvdelingDAO avdelingDAO = (AvdelingDAO) ModelUtil
						.getBean("avdelingDAO");
				BuntTypeDAO buntTypeDAO = (BuntTypeDAO) ModelUtil
						.getBean("buntTypeDAO");
				BuntStatusDAO buntStatusDAO = (BuntStatusDAO) ModelUtil
						.getBean("buntStatusDAO");
				BetingelseGruppe betingelseGruppe = betingelseGruppeDAO
						.findByName("Franchiseavgift");

				Avdeling avdeling;
				AvdelingAvregningImport avdelingAvregningImport = null;

				BuntType buntType = buntTypeDAO
						.findByKode(BuntTypeEnum.BATCH_TYPE_INN_AVR);
				BuntStatus buntStatus = buntStatusDAO
						.findByKode(BuntStatusEnum.NY);

				Bunt bunt = new Bunt(null, buntType, buntStatus, Calendar
						.getInstance().getTime(), applUser1, null, null, null,
						null, null, null, null, betingelseGruppe, null, null, null,
						null, null, null, null, null, null);

				

				String avdnr = excelUtil.readCell(row, 0, true);
				
				BigDecimal belop = null;
				AvdelingAvregningBelop avdelingAvregningBelop = null;

				while (avdnr != null) {
					avdeling = avdelingDAO.findByAvdnr(Integer.valueOf(avdnr));

					avdelingAvregningImport = avdelingAvregningImportManager
							.findByAvdnrAndYearNotNullNotCredit(Integer
									.valueOf(avdnr), year);

					if (avdelingAvregningImport != null) {
						throw new FrafException("Avdeling " + avdnr
								+ " er allerede avregnet for " + year);
					}

					avdelingAvregningImport = new AvdelingAvregningImport(avdeling,
							Integer.valueOf(avdnr), null, bunt, null, year);
					bunt.addAvdelingAvregningImport(avdelingAvregningImport);

					for (int col = 1; col <= 12; col++) {
						colValue = excelUtil.readCell(row, col, false);
						if (colValue != null) {
							belop = new BigDecimal(colValue.trim().replaceAll(" ",
									"").replaceAll(",", "."));

						} else {
							belop = BigDecimal.valueOf(0);
						}
						avdelingAvregningBelop = new AvdelingAvregningBelop(
								avdelingAvregningImport, null, belop, col, null);
						avdelingAvregningImport
								.addAvdelingAvregningBelop(avdelingAvregningBelop);
					}

					row++;
					avdnr = excelUtil.readCell(row, 0, true);
				}
				buntDAO.saveBunt(bunt);
				batchList.add(bunt);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new FrafException("Feil format "+colValue+" i rad "+row);
			}

		}

		/**
		 * Importerer fra semikolonbasert fil
		 * 
		 * @throws Exception
		 */
		private void importDeductFromCvs() throws Exception {
			FileReader deductFile = new FileReader(importFile);
			BufferedReader bufDeductFile = new BufferedReader(deductFile);
			String oneLine;
			String[] stringCol;
			BetingelseGruppe betingelseGruppe = betingelseGruppeDAO
					.findByName("Franchiseavgift");
			BuntStatusDAO buntStatusDAO = (BuntStatusDAO) ModelUtil
					.getBean("buntStatusDAO");
			BuntTypeDAO buntTypeDAO = (BuntTypeDAO) ModelUtil
					.getBean("buntTypeDAO");

			AvdelingDAO avdelingDAO = (AvdelingDAO) ModelUtil
					.getBean("avdelingDAO");

			BuntStatus buntStatus = buntStatusDAO
					.findByKode(BuntStatusEnum.NY);
			BuntType buntType = buntTypeDAO
					.findByKode(BuntTypeEnum.BATCH_TYPE_INN_AVR);

			int lineCount = 0;
			AvdelingAvregningImport avdelingAvregningImport = null;
			AvdelingAvregningBelop avdelingAvregningBelop = null;
			Bunt bunt = new Bunt(null, buntType, buntStatus, Calendar
					.getInstance().getTime(), applUser1, null, null, null,
					null, null, null, null, betingelseGruppe, null, null, null,
					null, null, null, null, null, null);

			// Fjerner overskrift
			bufDeductFile.readLine();
			Integer avdnr;
			Avdeling avdeling;
			BigDecimal belop = null;

			while ((oneLine = bufDeductFile.readLine()) != null) {

				lineCount++;
				// Splitter linje i deler basert på semikolon
				stringCol = oneLine.split(";");
				if (stringCol.length < 2) {
					throw new FrafException("Linje " + lineCount
							+ " inneholder for få kolonner");
				}
				avdnr = Integer.valueOf(stringCol[0]);
				avdeling = avdelingDAO.findByAvdnr(avdnr);

				avdelingAvregningImport = avdelingAvregningImportManager
						.findByAvdnrAndYearNotNullNotCredit(Integer
								.valueOf(avdnr), year);

				if (avdelingAvregningImport != null) {
					throw new FrafException("Avdeling " + avdnr
							+ " er allerede avregnet for " + year);
				}

				avdelingAvregningImport = new AvdelingAvregningImport(avdeling,
						avdnr, null, bunt, null, year);
				bunt.addAvdelingAvregningImport(avdelingAvregningImport);

				for (int i = 1; i <= 12; i++) {
					if (stringCol.length <= i) {
						belop = BigDecimal.valueOf(0);
					} else {
						if (stringCol[i].length() != 0) {
							belop = BigDecimal.valueOf(Double
									.valueOf(stringCol[i].trim().replaceAll(
											" ", "").replaceAll(",", ".")));

						}
					}
					avdelingAvregningBelop = new AvdelingAvregningBelop(
							avdelingAvregningImport, null, belop, i, null);
					avdelingAvregningImport
							.addAvdelingAvregningBelop(avdelingAvregningBelop);
				}

			}
			buntDAO.saveBunt(bunt);
			batchList.add(bunt);

		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
		 *      javax.swing.JLabel)
		 */
		public Object doWork(Object[] params, JLabel labelInfo) {
			labelInfo.setText("Importerer avregning...");
			String errorString = null;
			try {
				if (importFile.getName().endsWith("xls")) {
					importDeductFromExcel(importFile);
				} else {
					importDeductFromCvs();
				}
			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}

			return errorString;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null) {
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil",
						object.toString());
			}

		}

	}

}
