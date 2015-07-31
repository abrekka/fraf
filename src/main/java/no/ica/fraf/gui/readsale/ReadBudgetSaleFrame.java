package no.ica.fraf.gui.readsale;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import no.ica.fraf.common.Locker;
import no.ica.fraf.common.Locking;
import no.ica.fraf.dao.AvdelingOmsetningDAO;
import no.ica.fraf.dao.AvregningBasisTypeDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.LaasDAO;
import no.ica.fraf.dao.LaasTypeDAO;
import no.ica.fraf.dao.pkg.AvdelingOmsetningPkgDAO;
import no.ica.fraf.dao.pkg.BuntPkgDAO;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.LaasTypeEnum;
import no.ica.fraf.gui.Login;
import no.ica.fraf.gui.PanelBatch;
import no.ica.fraf.gui.model.interfaces.BatchSelectionListener;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.AvregningBasisType;
import no.ica.fraf.service.SapSaleManager;
import no.ica.fraf.util.GuiUtil;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

/**
 * This code was generated using CloudGarden's Jigloo SWT/Swing GUI Builder,
 * which is free for non-commercial use. If Jigloo is being used commercially
 * (ie, by a corporation, company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo. Please visit
 * www.cloudgarden.com for details. Use of Jigloo implies acceptance of these
 * licensing terms. ************************************* A COMMERCIAL LICENSE
 * HAS NOT BEEN PURCHASED for this machine, so Jigloo or this code cannot be
 * used legally for any corporate or commercial purpose.
 * *************************************
 */
/**
 * Vindu som brukes til import av budsjett og omsetning
 * 
 * @author abr99
 * 
 */
public class ReadBudgetSaleFrame extends javax.swing.JInternalFrame implements
		BatchSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	JTextField textFieldToDep;

	/**
	 * 
	 */
	JTextField textFieldFromDep;

	/**
	 * 
	 */
	JTabbedPane tabbedPaneCenter;

	/**
	 * 
	 */
	JMonthChooser monthChooser;

	/**
	 * 
	 */
	JYearChooser yearChooser;

	/**
	 * 
	 */
	JRadioButton radioButtonSale;

	/**
	 * 
	 */
	JRadioButton radioButtonBudget;

	/**
	 * 
	 */
	AvdelingOmsetningPkgDAO avdelingOmsetningPkgDAO;

	/**
	 * DAo for avretningbasis
	 */
	AvregningBasisTypeDAO avregningBasisTypeDAO;

	/**
	 * Bruker
	 */
	// ApplUser currentApplUser;
	private Login login;

	/**
	 * Panel for budsjett
	 */
	PanelReadBudgetSale panelReadBudget;

	/**
	 * Panel for omsetning
	 */
	PanelReadBudgetSale panelReadSale;

	/**
	 * Peker til vindu for bruk i innerklasser
	 */
	JInternalFrame internalFrame;

	/**
	 * DAO for omsetning/budsjett
	 */
	private AvdelingOmsetningDAO avdelingOmsetningDAO;

	/**
	 * Panel for bunt
	 */
	PanelBatch panelReadBatch;

	/**
	 * DAO for låstype
	 */
	private LaasTypeDAO laasTypeDAO;

	/**
	 * DAO for lås
	 */
	LaasDAO laasDAO;

	/**
	 * Gjeldende lås
	 */
	// Laas budgetLaas;
	/**
	 * 
	 */
	JButton buttonRead;
	/**
	 * 
	 */
	JButton buttonCancel;
	private Locker locker;
	private BuntDAO buntDAO;
	private BuntPkgDAO buntPkgDAO;
	private SapSaleManager sapSaleManager;
	private String baseXmlDataDir;
	private String fileName;
	private String importedDir;

	@Inject
	public ReadBudgetSaleFrame(Login aLogin,
			AvdelingOmsetningPkgDAO aAvdelingOmsetningPkgDAO,
			AvregningBasisTypeDAO aAvregningBasisTypeDAO,
			AvdelingOmsetningDAO aAvdelingOmsetningDAO,
			LaasTypeDAO aLaasTypeDAO, LaasDAO aLaasDAO, BuntDAO aBuntDAO,
			BuntPkgDAO aBuntPkgDAO, SapSaleManager aSapSaleManager,
			@Named(value="base_data") final String aXmlBaseDir,
			@Named(value="base_sales_file_name") final String aXmlFileName,final Locker aLocker,@Named("imported_dir") final String aImportedDir) {
		super();
		importedDir=aImportedDir;
		sapSaleManager = aSapSaleManager;
		baseXmlDataDir = aXmlBaseDir;
		fileName = aXmlFileName;
		buntDAO = aBuntDAO;
		buntPkgDAO = aBuntPkgDAO;

		internalFrame = this;
		login = aLogin;

		avdelingOmsetningPkgDAO = aAvdelingOmsetningPkgDAO;
		avregningBasisTypeDAO = aAvregningBasisTypeDAO;
		avdelingOmsetningDAO = aAvdelingOmsetningDAO;
		laasTypeDAO = aLaasTypeDAO;
		laasDAO = aLaasDAO;

		locker = aLocker;

		initGUI();

		setFrameIcon(IconEnum.ICON_FRAF.getIcon());
	}

	/**
	 * Initierer GUI
	 */
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(700, 550));
			this.setBounds(25, 25, 700, 550);
			BorderLayout thisLayout = new BorderLayout();
			this.getContentPane().setLayout(thisLayout);
			setVisible(true);
			this.setTitle("Les inn budsjett/omsetning");
			this.setIconifiable(true);
			this.setMaximizable(true);
			this.setResizable(true);
			{
				JPanel panelNorth = new JPanel();
				GridBagLayout panelNorthLayout = new GridBagLayout();
				panelNorth.setLayout(panelNorthLayout);
				this.getContentPane().add(panelNorth, BorderLayout.NORTH);
				panelNorth.setPreferredSize(new java.awt.Dimension(10, 140));
				panelNorth.setBorder(BorderFactory
						.createTitledBorder("Utvalgskriterier"));
				{
					JLabel labelInfo = new JLabel();
					panelNorth.add(labelInfo, new GridBagConstraints(0, 0, 1,
							1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelInfo.setText("Velg hva som skal lese inn:");
				}
				ButtonGroup buttonGroup = new ButtonGroup();
				{
					radioButtonBudget = new JRadioButton();
					radioButtonBudget.setSelected(true);
					panelNorth.add(radioButtonBudget, new GridBagConstraints(0,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(10, 0, 0, 0),
							0, 0));
					radioButtonBudget.setText("Budsjett");
					buttonGroup.add(radioButtonBudget);
					radioButtonBudget.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							radioButtonBudgetActionPerformed(evt);
						}
					});
				}
				{
					radioButtonSale = new JRadioButton();
					panelNorth.add(radioButtonSale, new GridBagConstraints(0,
							2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0,
							0));
					radioButtonSale.setText("Omsetning");
					buttonGroup.add(radioButtonSale);
					radioButtonSale.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							radioButtonSaleActionPerformed(evt);
						}
					});
				}
				{
					JLabel labelYear = new JLabel();
					panelNorth.add(labelYear, new GridBagConstraints(1, 1, 1,
							1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(10, 0, 0, 0),
							0, 0));
					labelYear.setText("År:");
				}
				{
					JLabel labelPeriode = new JLabel();
					panelNorth.add(labelPeriode, new GridBagConstraints(1, 2,
							1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0,
							0));
					labelPeriode.setText("Periode:");
				}
				{
					yearChooser = new JYearChooser();
					panelNorth.add(yearChooser, new GridBagConstraints(2, 1, 1,
							1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(10, 5, 0, 0),
							0, 0));
					yearChooser
							.setPreferredSize(new java.awt.Dimension(50, 20));
				}
				{
					monthChooser = new JMonthChooser();
					panelNorth.add(monthChooser, new GridBagConstraints(2, 2,
							1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0,
							0));
					monthChooser.setPreferredSize(new java.awt.Dimension(110,
							20));
					monthChooser
							.setMinimumSize(new java.awt.Dimension(110, 20));
				}
				{
					JLabel labelFromDep = new JLabel();
					panelNorth.add(labelFromDep, new GridBagConstraints(3, 1,
							1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(10, 30, 0, 0),
							0, 0));
					labelFromDep.setText("Fra avdnr:");
				}
				{
					JLabel labelToDep = new JLabel();
					panelNorth.add(labelToDep, new GridBagConstraints(3, 2, 1,
							1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(5, 30, 0, 0),
							0, 0));
					labelToDep.setText("Til avdnr:");
				}
				{
					textFieldFromDep = new JTextField();
					panelNorth.add(textFieldFromDep, new GridBagConstraints(4,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(10, 0, 0, 0),
							0, 0));
					textFieldFromDep.setPreferredSize(new java.awt.Dimension(
							50, 20));
					textFieldFromDep.setMinimumSize(new java.awt.Dimension(50,
							20));
				}
				{
					textFieldToDep = new JTextField();
					panelNorth.add(textFieldToDep, new GridBagConstraints(4, 2,
							1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0,
									0), 0, 0));
				}
				{
					buttonRead = new JButton();
					buttonRead.setIcon(IconEnum.ICON_READ.getIcon());
					buttonRead.setMnemonic(KeyEvent.VK_L);
					panelNorth.add(buttonRead, new GridBagConstraints(0, 3, 5,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(10, 0, 0, 0),
							0, 0));
					buttonRead.setText("Les inn");
					buttonRead.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonReadActionPerformed(evt);
						}
					});
				}
			}
			{
				tabbedPaneCenter = new JTabbedPane();
				this.getContentPane()
						.add(tabbedPaneCenter, BorderLayout.CENTER);
				{
					panelReadBudget = new PanelReadBudgetSale(true, this,
							avdelingOmsetningDAO);
					tabbedPaneCenter.addTab("Budsjett", IconEnum.ICON_BUDGET
							.getIcon(), panelReadBudget, null);
				}

				{
					panelReadSale = new PanelReadBudgetSale(false, this,
							avdelingOmsetningDAO);
				}

				{
					panelReadBatch = new PanelBatch(this,
							BuntTypeEnum.BATCH_TYPE_BUDGET, buntDAO, buntPkgDAO);
					tabbedPaneCenter.addTab("Logg",
							IconEnum.ICON_LOG.getIcon(), panelReadBatch, null);
					panelReadBatch.addBatchSelectionListener(this);
				}
			}
			{
				JPanel panelSouth = new JPanel();
				this.getContentPane().add(panelSouth, BorderLayout.SOUTH);
				panelSouth.setPreferredSize(new java.awt.Dimension(10, 50));
				{
					buttonCancel = new JButton();
					buttonCancel.setIcon(IconEnum.ICON_CANCEL.getIcon());
					buttonCancel.setPreferredSize(new Dimension(90, 25));
					buttonCancel.setMnemonic(KeyEvent.VK_A);
					panelSouth.add(buttonCancel);
					buttonCancel.setText("Avbryt");
					buttonCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonCancelActionPerformed(evt);
						}
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lukker vindu
	 * 
	 * @param evt
	 */
	void buttonCancelActionPerformed(ActionEvent evt) {
		dispose();
	}

	/**
	 * Les inn budsjett/omsetning
	 * 
	 * @param evt
	 */
	void buttonReadActionPerformed(ActionEvent evt) {
		if (!locker.lock(login.getApplUser(), this, LaasTypeEnum.BUD, null)) {
			return;
		}
		/*
		 * Laas laas = lockRead(currentApplUser);
		 * 
		 * if (laas != null) { GuiUtil.showErrorMsgFrame(this, "Låst",
		 * "Innlesning av budsjett/omsetning gjøres av " + laas.getApplUser() +
		 * " prøv senere"); return; }
		 */
		String loadTitle = null;
		if (radioButtonBudget.isSelected()) {
			loadTitle = "Innlesing av budsjett";
		} else {
			loadTitle = "Innlesing av omsetning";
		}
		AvregningBasisType avregningBasisType;
		if (radioButtonBudget.isSelected()) {
			avregningBasisType = avregningBasisTypeDAO.findByKode("BUD");

		} else {
			avregningBasisType = avregningBasisTypeDAO.findByKode("OMS");
		}

		Integer year = new Integer(yearChooser.getYear());
		Integer period = new Integer(monthChooser.getMonth() + 1);
		Integer depFrom;
		Integer depTo;
		if (textFieldFromDep.getText().length() != 0) {
			depFrom = new Integer(textFieldFromDep.getText());
		} else {
			depFrom = new Integer(1000);
		}

		if (textFieldToDep.getText().length() != 0) {
			depTo = new Integer(textFieldToDep.getText());
		} else {
			depTo = new Integer(9999);
		}

		/*AbstractBudgetSalesReader abstractBudgetSalesReader = BudgetSalesReaderEnum
				.getBudgetSalesReader(true).getBudgetSalesReader(this, year,
						period, depFrom, depTo, avregningBasisType,
						avdelingOmsetningPkgDAO, login,
						sapSaleManager, baseXmlDataDir, fileName,locker,importedDir);

		GuiUtil.runInThread(this, loadTitle, "Leser inn...",
				abstractBudgetSalesReader, null);*/

	}

	/**
	 * Prøver å låse for innlesning av omsetning/budsjett
	 * 
	 * @param applUser
	 * @return lås dersom det allerede finnes en lås
	 */
	/*
	 * private Laas lockRead(ApplUser applUser) { LaasType laasType =
	 * laasTypeDAO.findByKode(LaasTypeEnum.BUD); Laas laas =
	 * laasDAO.findByLaasType(laasType);
	 * 
	 * if (laas != null) { return laas; }
	 * 
	 * currentLaas = new Laas(); currentLaas.setApplUser(applUser);
	 * currentLaas.setLaasDato(Calendar.getInstance().getTime());
	 * currentLaas.setLaasType(laasType); laasDAO.saveLaas(currentLaas); return
	 * null; }
	 */

	/**
	 * @see no.ica.fraf.gui.model.interfaces.BatchSelectionListener#batchSelected(java.lang.Integer)
	 */
	public void batchSelected(Integer batchId) {
		GuiUtil.setWaitCursor(this);
		AvregningBasisType avregningBasisType = avdelingOmsetningDAO
				.getAvregningBasisTypeByBatch(batchId);

		if (avregningBasisType != null
				&& avregningBasisType.getAvregningBasisTypeKode()
						.equalsIgnoreCase("BUD")
				&& radioButtonBudget.isSelected()) {
			panelReadBudget.loadDataFromBatch(batchId);
			panelReadSale.loadDataFromBatch(null);
			tabbedPaneCenter.setTitleAt(0, "Budsjett*");
		} else if (avregningBasisType != null
				&& avregningBasisType.getAvregningBasisTypeKode()
						.equalsIgnoreCase("OMS")
				&& radioButtonSale.isSelected()) {
			panelReadSale.loadDataFromBatch(batchId);
			panelReadBudget.loadDataFromBatch(null);
			tabbedPaneCenter.setTitleAt(0, "Omsetning*");
		} else {
			panelReadSale.loadDataFromBatch(null);
			panelReadBudget.loadDataFromBatch(null);
			if (radioButtonBudget.isSelected()) {
				tabbedPaneCenter.setTitleAt(0, "Budsjett");
			} else if (radioButtonSale.isSelected()) {
				tabbedPaneCenter.setTitleAt(0, "Omsetning");
			}
		}
		GuiUtil.setDefaultCursor(this);
	}

	/**
	 * Enabler/disabler komponenter
	 * 
	 * @param enable
	 */
	private void enableCriteria(boolean enable) {
		yearChooser.setEnabled(enable);
		monthChooser.setEnabled(enable);
		textFieldFromDep.setEnabled(enable);
		textFieldToDep.setEnabled(enable);
	}

	/**
	 * Kjøres når radioknapp for omsetning trykkes
	 * 
	 * @param evt
	 */
	void radioButtonSaleActionPerformed(ActionEvent evt) {
		enableCriteria(true);

		tabbedPaneCenter.remove(0);

		tabbedPaneCenter.insertTab("Omsetning", IconEnum.ICON_SOLD.getIcon(),
				panelReadSale, null, 0);

		panelReadBatch.setBatchType(BuntTypeEnum.BATCH_TYPE_SALE);
		tabbedPaneCenter.setSelectedIndex(0);
	}

	/**
	 * Kjøres når radioknapp for budsjett trykkes
	 * 
	 * @param evt
	 */
	void radioButtonBudgetActionPerformed(ActionEvent evt) {
		enableCriteria(true);

		tabbedPaneCenter.remove(0);

		tabbedPaneCenter.insertTab("Budsjett", IconEnum.ICON_BUDGET.getIcon(),
				panelReadBudget, null, 0);

		panelReadBatch.setBatchType(BuntTypeEnum.BATCH_TYPE_SALE);
		tabbedPaneCenter.setSelectedIndex(0);
	}

	/**
	 * Enabler/disabler komponenter i vindu
	 * 
	 * @param enable
	 */
	public void enableFrameComponents(boolean enable) {
		buttonRead.setEnabled(enable);
		buttonCancel.setEnabled(enable);
		radioButtonBudget.setEnabled(enable);
		radioButtonSale.setEnabled(enable);
		tabbedPaneCenter.setEnabled(enable);
		panelReadBatch.enableComponents(enable);
		panelReadBudget.enableComponents(enable);
		panelReadSale.enableComponents(enable);
	}

	/**
	 * @see javax.swing.JComponent#setEnabled(boolean)
	 */
	@Override
	public void setEnabled(boolean enabled) {
		enableFrameComponents(enabled);
		super.setEnabled(enabled);
	}

	public void loadData(Integer buntId) {
		if (radioButtonBudget.isSelected()) {
			panelReadBudget.loadDataFromBatch(buntId);
		} else {
			panelReadSale.loadDataFromBatch(buntId);
		}
		panelReadBatch.loadData();
	}
}
