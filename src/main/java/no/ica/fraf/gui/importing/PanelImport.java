package no.ica.fraf.gui.importing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import no.ica.fraf.common.Locker;
import no.ica.fraf.common.Locking;
import no.ica.fraf.dao.LaasDAO;
import no.ica.fraf.dao.LaasTypeDAO;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.ImportEnum;
import no.ica.fraf.enums.LaasTypeEnum;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.ImportBetingelse;
import no.ica.fraf.model.Laas;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
/**
 * Panel som håndterer import av betingelser og budsjett
 */
public class PanelImport extends javax.swing.JPanel implements Threadable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JTable tableImport;

	/**
	 * 
	 */
	private JButton buttonDeleteRows;

	/**
	 * 
	 */
	private JButton buttonReadFile;

	/**
	 * 
	 */
	private JButton buttonFileChooser;

	/**
	 * 
	 */
	private JTextField textFieldFileName;

	/**
	 * DAO for låstype
	 */
	private LaasTypeDAO laasTypeDAO = (LaasTypeDAO) ModelUtil
			.getBean("laasTypeDAO");

	/**
	 * DAO for lås
	 */
	private LaasDAO laasDAO = (LaasDAO) ModelUtil.getBean("laasDAO");

	/**
	 * Gjeldende lås
	 */
	//private Laas importLaas;

	/**
	 * TabelModel for tabell
	 */
	private ObjectTableModel<Object> objectTableModel;

	/**
	 * 
	 */
	private JTextField textFieldYear;

	/**
	 * 
	 */
	private JButton buttonImportClosed;

	/**
	 * Fil som skal importeres
	 */
	private File importFile;

	/**
	 * Vindu som bruker dette panelet
	 */
	private JInternalFrame internalFrame;

	/**
	 * Bruker
	 */
	private ApplUser currentApplUser;

	/**
	 * 
	 */
	private JRadioButton radioButtonHead;

	/**
	 * Gjeldende importtype
	 */
	private ImportEnum currentImportType;

	/**
	 * Gjeldende importklasse
	 */
	private ImportInterface currentImport;
	private Locker locker;
	

	/**
	 * Konstruktør
	 * 
	 * @param aInternalFrame
	 * @param applUser
	 * @param importType
	 */
	public PanelImport(JInternalFrame aInternalFrame, ApplUser applUser,
			ImportEnum importType) {
		super();
		LaasTypeDAO laasTypeDAO=(LaasTypeDAO)ModelUtil.getBean("laasTypeDAO");
		LaasDAO laasDAO=(LaasDAO)ModelUtil.getBean("laasDAO");
		locker=new Locking(laasTypeDAO,laasDAO);
		internalFrame = aInternalFrame;
		currentApplUser = applUser;
		currentImportType = importType;
		initGUI();
		currentImport = getImportClass(currentImportType);

		
		initTable();
	}

	/**
	 * Henter gjeldende importklasse
	 * 
	 * @param importType
	 * @return gjeldende importklasse
	 */
	private ImportInterface getImportClass(ImportEnum importType) {
		switch (importType) {
		case IMPORT_CONDITION:
			return new ImportCondition(internalFrame, currentApplUser);
		case IMPORT_BUDGET:
			return new ImportBudget(currentApplUser);
		case IMPORT_INVOICE:
			return new ImportInvoice(currentApplUser);
		}
		return null;
	}

	/**
	 * Initierer GUI
	 */
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(775, 300));
			{
				JScrollPane scrollPaneImport = new JScrollPane();
				this.add(scrollPaneImport, BorderLayout.CENTER);
				{
					tableImport = new JTable();
					scrollPaneImport.setViewportView(tableImport);
					tableImport.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				}
			}
			{
				JPanel panelSouth = new JPanel();
				FlowLayout panelSouthLayout = new FlowLayout();
				panelSouthLayout.setAlignment(FlowLayout.LEFT);
				panelSouth.setLayout(panelSouthLayout);
				this.add(panelSouth, BorderLayout.SOUTH);
				panelSouth.setPreferredSize(new java.awt.Dimension(10, 40));
				{
					buttonReadFile = new JButton();
					buttonReadFile.setIcon(IconEnum.ICON_IMPORT.getIcon());
					buttonReadFile.setMnemonic(KeyEvent.VK_I);
					panelSouth.add(buttonReadFile);
					buttonReadFile.setEnabled(false);
					buttonReadFile.setText("Importer");
					buttonReadFile.setPreferredSize(new java.awt.Dimension(115,
							25));
					buttonReadFile.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonImportActionPerformed(evt);
						}
					});
				}
				{
					buttonDeleteRows = new JButton();
					buttonDeleteRows.setEnabled(false);
					buttonDeleteRows.setIcon(IconEnum.ICON_DELETE.getIcon());
					buttonDeleteRows.setMnemonic(KeyEvent.VK_S);
					panelSouth.add(buttonDeleteRows);
					buttonDeleteRows.setText("Slett rader");
					buttonDeleteRows.setPreferredSize(new java.awt.Dimension(
							115, 25));
					buttonDeleteRows.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonDeleteRowsActionPerformed(evt);
						}
					});
				}
				buttonImportClosed = new JButton();
				buttonImportClosed.setEnabled(false);
				if (currentImportType == ImportEnum.IMPORT_CONDITION) {

					buttonImportClosed.setIcon(IconEnum.ICON_IMPORT.getIcon());
					buttonImportClosed.setMnemonic(KeyEvent.VK_N);
					panelSouth.add(buttonImportClosed);
					buttonImportClosed.setText("Importer nedlagt avdeling");
					buttonImportClosed.setPreferredSize(new java.awt.Dimension(
							205, 25));
					buttonImportClosed.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonImportClosedActionPerformed(evt);
						}
					});
				}
			}
			{
				JPanel panelNorth = new JPanel();
				this.add(panelNorth, BorderLayout.NORTH);
				panelNorth.setPreferredSize(new java.awt.Dimension(10, 50));
				{
					JLabel labelYear = new JLabel();
					if (currentImportType == ImportEnum.IMPORT_BUDGET) {
						panelNorth.add(labelYear);
						labelYear.setText("År:");
					}
				}
				
				{
					textFieldYear = new JTextField();
					if (currentImportType == ImportEnum.IMPORT_BUDGET) {
						panelNorth.add(textFieldYear);
						textFieldYear.setPreferredSize(new java.awt.Dimension(
								70, 20));
						textFieldYear.setText(String.valueOf(Calendar
								.getInstance().get(Calendar.YEAR)));
					}
				}
				
				{
					JLabel labelFileName = new JLabel();
					panelNorth.add(labelFileName, new GridBagConstraints(1, 1,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelFileName.setText("Filnavn:");
				}
				{
					textFieldFileName = new JTextField();
					panelNorth.add(textFieldFileName, new GridBagConstraints(2,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					textFieldFileName.setPreferredSize(new java.awt.Dimension(
							350, 20));
				}
				{
					buttonFileChooser = new JButton();
					panelNorth.add(buttonFileChooser, new GridBagConstraints(3,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					buttonFileChooser.setText("...");
					buttonFileChooser.setPreferredSize(new java.awt.Dimension(
							30, 20));
					buttonFileChooser.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonFileChooserActionPerformed(evt);
						}
					});
				}
				{
					radioButtonHead = new JRadioButton();
					panelNorth.add(radioButtonHead);
					radioButtonHead.setText("Filhode");
					radioButtonHead.setSelected(true);
				}
				{
					JButton buttonHelp = new JButton();
					panelNorth.add(buttonHelp);
					buttonHelp.setIcon(IconEnum.ICON_HELP.getIcon());
					buttonHelp.setPreferredSize(new java.awt.Dimension(20, 20));
					buttonHelp.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonHelpActionPerformed(evt);
						}
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initierer tabell
	 */
	private void initTable() {
		objectTableModel = currentImport.getTableModelImport();
		tableImport.setModel(objectTableModel);
		objectTableModel.setEditable(false);

		currentImport.setColumnWidthsImport(tableImport);

		initTableData();

	}

	/**
	 * Laster data
	 */
	private void initTableData() {
		List<Object> dataList = currentImport.findAllImport();

		if (dataList != null && dataList.size() != 0) {
			objectTableModel.setData(dataList);
			buttonReadFile.setEnabled(true);
			buttonDeleteRows.setEnabled(true);
			buttonImportClosed.setEnabled(true);
		} else {
			objectTableModel.deleteData();
		}
	}

	/**
	 * Kjør import
	 * 
	 * @param evt
	 */
	void buttonImportActionPerformed(ActionEvent evt) {
		//importLaas=locker.lock(currentApplUser,internalFrame,getLassTypeEnum(),null);
		if(!locker.lock(currentApplUser,internalFrame,getLassTypeEnum(),null)){
			return;
		}
		//Laas laas = lockImport(currentApplUser);
		Integer year = null;

		if (currentImportType == ImportEnum.IMPORT_BUDGET) {
			year = Integer.valueOf(textFieldYear.getText());
		}

		/*if (laas != null) {
			GuiUtil.showErrorMsgFrame(internalFrame, "Låst",
					"Import kjøres av " + laas.getApplUser()
							+ " prøv igjen senere");
			return;
		}*/
		if (textFieldFileName.getText().length() != 0) {
			if (importFile == null || !importFile.exists()) {
				GuiUtil.showErrorMsgFrame(this, "Feil",
						"Det må velges en gyldig fil for import");
				return;
			}
			GuiUtil.runInThread(internalFrame, "Import", "Importerer...", this,
					new Object[] { new Boolean(true), year });
		} else {

			GuiUtil.runInThread(internalFrame, "Import", "Importerer...", this,
					new Object[] { new Boolean(false), year });
		}

	}
	
	private LaasTypeEnum getLassTypeEnum(){
		switch (currentImportType) {
		case IMPORT_BUDGET:
			return LaasTypeEnum.BUD;
		case IMPORT_CONDITION:
			return LaasTypeEnum.IMP;
		case IMPORT_INVOICE:
			return LaasTypeEnum.IMP_FAK;
			default:
				return null;
		}
	}

	/**
	 * Prøver å låse import.
	 * 
	 * @param applUser
	 * @return dersom import var låst returneres denne, hvis ikke lages ny lås
	 *         og null returneres
	 */
	/*private Laas lockImport(ApplUser applUser) {
		LaasType laasType = null;
		switch (currentImportType) {
		case IMPORT_BUDGET:
			laasType = laasTypeDAO.findByKode(LaasTypeEnum.BUD);
			break;
		case IMPORT_CONDITION:
			laasType = laasTypeDAO.findByKode(LaasTypeEnum.IMP);
			break;
		case IMPORT_INVOICE:
			laasType = laasTypeDAO.findByKode(LaasTypeEnum.IMP_FAK);
			break;
		}
		Laas laas = laasDAO.findByLaasType(laasType);

		if (laas != null) {
			return laas;
		}

		currentLaas = new Laas();
		currentLaas.setApplUser(applUser);
		currentLaas.setLaasDato(Calendar.getInstance().getTime());
		currentLaas.setLaasType(laasType);
		laasDAO.saveLaas(currentLaas);
		return null;
	}*/

	/**
	 * Slette alle rader i tabell
	 * 
	 * @param evt
	 */
	void buttonDeleteRowsActionPerformed(ActionEvent evt) {
		if (GuiUtil.showConfirmFrame(this, "Slette",
				"Vil du virkelig slette alle radene?")) {
			List list = objectTableModel.deleteData();
			currentImport.deleteListImport(list);
			buttonDeleteRows.setEnabled(false);
			buttonImportClosed.setEnabled(false);
		}
	}

	/**
	 * Velg fil
	 * 
	 * @param evt
	 */
	void buttonFileChooserActionPerformed(ActionEvent evt) {
		JFileChooser fileChooser = new JFileChooser();
		File defaultDir = new File("betingelser");
		fileChooser.setCurrentDirectory(defaultDir);

		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			buttonReadFile.setEnabled(true);
			importFile = fileChooser.getSelectedFile();

			textFieldFileName.setText(importFile.getAbsolutePath());
		}
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
	 */
	public void enableComponents(boolean enable) {
		buttonDeleteRows.setEnabled(enable);
		buttonFileChooser.setEnabled(enable);
		buttonReadFile.setEnabled(enable);
		buttonImportClosed.setEnabled(enable);
		internalFrame.setEnabled(enable);
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	public Object doWork(Object[] params, JLabel labelInfo) {
		return currentImport.doImport(((Boolean) params[0]).booleanValue(),
				importFile, radioButtonHead.isSelected(), params[1]);
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
		locker.unlock();
		/*if (currentLaas != null) {
			laasDAO.removeLaas(currentLaas.getLaasId());
		}*/
		if (object != null) {
			GuiUtil.showErrorMsgFrame(internalFrame, "Feil", (String) object);
		}
		initTableData();
	}

	/**
	 * Importer avdelinger som er avsluttet
	 * 
	 * @param evt
	 */
	void buttonImportClosedActionPerformed(ActionEvent evt) {
		if (tableImport.getSelectedRow() < 0) {
			GuiUtil.showErrorMsgFrame(internalFrame, "Feil",
					"Det må velges en avdeling som er nedlagt");
			return;
		}

		int[] selectedRows = tableImport.getSelectedRows();
		List<ImportBetingelse> importBetingelser = new ArrayList<ImportBetingelse>();

		ImportBetingelse importBetingelse;

		for (int i = 0; i < selectedRows.length; i++) {
			importBetingelse = (ImportBetingelse) objectTableModel
					.getObjectAtIndex(selectedRows[i]);

			if (importBetingelse!=null && importBetingelse.getFeilmelding()!=null&&!importBetingelse.getFeilmelding().equalsIgnoreCase(
					"Avdeling er nedlagt")) {
				GuiUtil.showErrorMsgFrame(internalFrame, "Feil",
						"Dette kan kun gjøres for avdelinger som er nedlagt");
				return;
			}

			importBetingelser.add(importBetingelse);
		}

		// ImportBetingelse importBetingelse = (ImportBetingelse)
		// objectTableModel.getObjectAtIndex(tableImport.getSelectedRow());

		/*if (!importBetingelse.getFeilmelding().equalsIgnoreCase(
				"Avdeling er nedlagt")) {
			GuiUtil.showErrorMsgFrame(internalFrame, "Feil",
					"Dette kan kun gjøres for avdelinger som er nedlagt");
			return;
		}*/
		currentImport.importClosedDepartment(importBetingelser);
		//importBetingelse = (ImportBetingelse) objectTableModel.deleteRow(tableImport.getSelectedRow());
		objectTableModel.deleteRows(selectedRows);

	}

	/**
	 * Vis hjelp
	 * 
	 * @param evt
	 */
	void buttonHelpActionPerformed(ActionEvent evt) {
		GuiUtil.showMsgFrame(this, "Hjelp til import", currentImport
				.getHelpText());
	}
}
