package no.ica.fraf.gui.department;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.MouseInputListener;

import no.ica.fraf.FrafException;
import no.ica.fraf.common.Locker;
import no.ica.fraf.common.Locking;
import no.ica.fraf.dao.ApplUserTypeDAO;
import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.dao.LaasDAO;
import no.ica.fraf.dao.LaasTypeDAO;
import no.ica.fraf.dao.view.AvdelingVDAO;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.LaasTypeEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.Adendum;
import no.ica.fraf.model.AnnenKontrakt;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.AvdelingKontrakt;
import no.ica.fraf.model.AvdelingLogg;
import no.ica.fraf.model.AvdelingOmsetning;
import no.ica.fraf.model.AvdelingV;
import no.ica.fraf.model.Eierforhold;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.Garanti;
import no.ica.fraf.model.LkKontraktobjekter;
import no.ica.fraf.model.Sikkerhet;
import no.ica.fraf.model.SpeiletKostnad;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.apache.log4j.Logger;

/**
 * Vindu som viser informasjon om en avdeling
 */
/**
 * @author abr99
 * 
 */
public class DepartmentFrame extends javax.swing.JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JPanel panelCenter;

	/**
	 * 
	 */
	private PanelDepartment panelDepartment;

	/**
	 * 
	 */
	FrafPanel<Faktura> panelInvoice;

	/**
	 * 
	 */
	FrafPanel<LkKontraktobjekter> panelRental;

	/**
	 * 
	 */
	private FrafTabbedPane tabbedPaneDepartment;

	/**
	 * 
	 */
	private JButton buttonSave = new JButton();

	/**
	 * 
	 */
	private JButton buttonCancel;

	/**
	 * 
	 */
	private JPanel panelSouth;

	/**
	 * Gjeldende avdeling
	 */
	private Avdeling currentAvdeling;

	/**
	 * Gjeldende avdeling view
	 */
	private AvdelingV currentAvdelingV;

	/**
	 * DAO for avdeling
	 */
	private static AvdelingDAO avdelingDAO;

	/**
	 * DAO for avdeling view
	 */
	private static AvdelingVDAO avdelingVDAO;

	/**
	 * DAO for rik2 view
	 */
	//private static Rik2AvdVDAO rik2AvdVDAO;

	/**
	 * Bruker
	 */
	ApplUser currentApplUser;

	/**
	 * 
	 */
	JPopupMenu popupMenuTable;

	/**
	 * 
	 */
	JMenuItem menuItemAddRow;

	/**
	 * 
	 */
	JMenuItem menuItemRemoveRow;

	/**
	 * 
	 */
	JMenuItem menuItemExcel;

	/**
	 * Panel med betingelser
	 */
	FrafPanel<AvdelingBetingelse> panelCondition;

	/**
	 * Panel med eierskap
	 */
	FrafPanel<Eierforhold> panelOwnerships;

	/**
	 * Panel med addendum
	 */
	FrafPanel<Adendum> panelAdendum;

	/**
	 * Panel med andre kontrakter
	 */
	FrafPanel<AnnenKontrakt> panelOtherContract;

	/**
	 * Panel med kontrakter
	 */
	FrafPanel<AvdelingKontrakt> panelContracts;

	/**
	 * Panel med sikkerhet
	 */
	FrafPanel<Sikkerhet> panelSecurity;

	/**
	 * Panel med logg
	 */
	FrafPanel<AvdelingLogg> panelLog;

	/**
	 * Panel med budsjett
	 */
	FrafPanel<AvdelingOmsetning> panelBudgets;

	/**
	 * Panel med omsetning
	 */
	FrafPanel<AvdelingOmsetning> panelSolds;

	/**
	 * Panel med andre betingelser
	 */
	//FrafPanel<AvdelingBetingelse> panelOtherConditions;

	/**
	 * Panel med speilede kostnader
	 */
	FrafPanel<SpeiletKostnad> panelMirrorCondition;

	/**
	 * Panel med garantier
	 */
	FrafPanel<Garanti> panelGuarantee;

	/**
	 * DAO for bruker
	 */
	ApplUserTypeDAO applUserTypeDAO;

	/**
	 * DAO for låser
	 */
	private LaasDAO laasDAO;

	/**
	 * DAO for låstype
	 */
	private LaasTypeDAO laasTypeDAO;

	/**
	 * Gjeldende lås
	 */
	//private Laas departmentLaas;

	/**
	 * True dersom frame er readonly
	 */
	boolean readOnly = false;

	/**
	 * Logger
	 */
	private static final Logger logger = Logger
			.getLogger(DepartmentFrame.class);
	private Locker locker;

	/**
	 * Konstruktør
	 * 
	 * @param avdelingV
	 * @param applUser
	 */
	public DepartmentFrame(AvdelingV avdelingV, ApplUser applUser) {
		LaasTypeDAO laasTypeDAO=(LaasTypeDAO)ModelUtil.getBean("laasTypeDAO");
		LaasDAO laasDAO=(LaasDAO)ModelUtil.getBean("laasDAO");
		locker = new Locking(laasTypeDAO,laasDAO);
		currentAvdelingV = avdelingV;
		currentApplUser = applUser;

		initDAO();

		initUser();
		if (currentAvdelingV != null) {
			currentAvdeling = avdelingDAO
					.getAvdeling(avdelingV.getAvdelingId());

			if (!readOnly) {
				//departmentLaas = locker.lock(currentApplUser, FrafMain.getInstance().getContentPane(), LaasTypeEnum.AVD,currentAvdeling);
				if (!locker.lock(currentApplUser, FrafMain
						.getInstance().getContentPane(), LaasTypeEnum.AVD,
						currentAvdeling)) {
					readOnly = true;
					initUser();
				}
			}
		} else {
			currentAvdeling = new Avdeling();
		}

		initGUI();

		setFrameIcon(IconEnum.ICON_FRAF.getIcon());

	}

	/**
	 * Initierere bruker
	 */
	private void initUser() {
		if (applUserTypeDAO.isReader(currentApplUser.getApplUserType())) {
			readOnly = true;
			buttonSave.setEnabled(false);

		} else if (applUserTypeDAO
				.isRegnskap(currentApplUser.getApplUserType())
				|| applUserTypeDAO.isMarked(currentApplUser.getApplUserType())) {
			readOnly = true;
			buttonSave.setEnabled(true);
		}
	}

	/**
	 * Initierere DAO'er
	 */
	private void initDAO() {
		if (avdelingDAO == null) {
			avdelingDAO = (AvdelingDAO) ModelUtil.getBean("avdelingDAO");
		}

		if (avdelingVDAO == null) {
			avdelingVDAO = (AvdelingVDAO) ModelUtil.getBean("avdelingVDAO");
		}

		/*if (rik2AvdVDAO == null) {
			rik2AvdVDAO = (Rik2AvdVDAO) ModelUtil.getBean("rik2AvdVDAO");
		}*/

		if (applUserTypeDAO == null) {
			applUserTypeDAO = (ApplUserTypeDAO) ModelUtil
					.getBean("applUserTypeDAO");
		}

		if (laasTypeDAO == null) {
			laasTypeDAO = (LaasTypeDAO) ModelUtil.getBean("laasTypeDAO");
		}

		if (laasDAO == null) {
			laasDAO = (LaasDAO) ModelUtil.getBean("laasDAO");
		}

	}

	/**
	 * Initierer GUI
	 */
	private void initGUI() {
		TableMouseListener tableMouseListener = new TableMouseListener();
		try {
			this.setPreferredSize(new java.awt.Dimension(950, 650));
			this.setBounds(0, 0, 950, 650);
			BorderLayout thisLayout = new BorderLayout();
			this.getContentPane().setLayout(thisLayout);
			setVisible(true);
			this.setIconifiable(true);
			this.setMaximizable(true);
			this.setResizable(true);
			if (currentAvdeling != null) {
				this.setTitle("Avdeling - " + currentAvdeling.getAvdnr());
			} else {
				this.setTitle("Ny avdeling");
			}

			popupMenuTable = new JPopupMenu("Meny");
			menuItemAddRow = new JMenuItem("");
			menuItemAddRow.setIcon(IconEnum.ICON_CREATE.getIcon());
			menuItemAddRow.setActionCommand("addRow");
			menuItemAddRow.addActionListener(new MenuItemListener(this));
			menuItemRemoveRow = new JMenuItem("");
			menuItemRemoveRow.setIcon(IconEnum.ICON_DELETE.getIcon());
			menuItemRemoveRow.setActionCommand("removeRow");
			menuItemRemoveRow.addActionListener(new MenuItemListener(this));

			menuItemExcel = new JMenuItem("Excel");
			menuItemExcel.setIcon(IconEnum.ICON_EXCEL.getIcon());
			popupMenuTable.add(menuItemExcel);
			menuItemExcel.addActionListener(new MenuItemListener(this));

			{

				panelDepartment = new PanelDepartment(this, currentAvdeling,
						currentAvdelingV, currentApplUser, readOnly);

				this.getContentPane().add(panelDepartment, BorderLayout.NORTH);
			}

			{
				panelCenter = new JPanel();
				BorderLayout jPanel1Layout = new BorderLayout();
				panelCenter.setLayout(jPanel1Layout);
				this.getContentPane().add(panelCenter, BorderLayout.CENTER);
				{
					tabbedPaneDepartment = new FrafTabbedPane();
					panelCenter.add(tabbedPaneDepartment, BorderLayout.CENTER);
					panelInvoice = new PanelInvoiceNew(this, currentAvdeling,
							currentApplUser, readOnly);
					tabbedPaneDepartment
							.addTab("Faktura", IconEnum.ICON_INVOICE.getIcon(),
									panelInvoice, null);
					panelInvoice.addTableMouseListener(tableMouseListener);
					{
						panelContracts = new PanelContract(this,
								currentAvdeling, currentApplUser, readOnly);
						tabbedPaneDepartment.addTab("Kontrakt",
								IconEnum.ICON_CONTRACT.getIcon(),
								panelContracts, null);
						panelContracts
								.addTableMouseListener(tableMouseListener);
					}
					{
						panelCondition = new PanelCondition(this,
								currentAvdeling, currentApplUser, readOnly);

						tabbedPaneDepartment.addTab("Betingelser",
								IconEnum.ICON_CONDITION.getIcon(),
								panelCondition, null);
						panelCondition
								.addTableMouseListener(tableMouseListener);
					}
					{
						panelBudgets = new PanelBudget(this, currentAvdeling,
								currentApplUser, readOnly);
						tabbedPaneDepartment.addTab("Budsjett",
								IconEnum.ICON_BUDGET.getIcon(), panelBudgets,
								null);
						panelBudgets.addTableMouseListener(tableMouseListener);
					}
					{
						panelSolds = new PanelSold(this, currentAvdeling,
								currentApplUser, readOnly);
						tabbedPaneDepartment.addTab("Omsetning",
								IconEnum.ICON_SOLD.getIcon(), panelSolds, null);
						panelSolds.addTableMouseListener(tableMouseListener);
					}
					{
						panelOwnerships = new PanelOwnership(this,
								currentAvdeling, currentApplUser, readOnly);
						tabbedPaneDepartment.addTab("Eierforhold",
								IconEnum.ICON_OWNERSHIP.getIcon(),
								panelOwnerships, null);
						panelOwnerships
								.addTableMouseListener(tableMouseListener);

					}
					{
						panelSecurity = new PanelSecurity(this,
								currentAvdeling, currentApplUser, readOnly);
						tabbedPaneDepartment.addTab("Sikkerhet",
								IconEnum.ICON_SECURITY.getIcon(),
								panelSecurity, null);
						panelSecurity.addTableMouseListener(tableMouseListener);

					}
					{
						panelAdendum = new PanelAdendum(this, currentAvdeling,
								currentApplUser, readOnly);
						tabbedPaneDepartment.addTab("Addendum",
								IconEnum.ICON_ADDENDUM.getIcon(), panelAdendum,
								null);
						panelAdendum.addTableMouseListener(tableMouseListener);

					}
					{
						panelRental = new PanelRental(this, currentAvdeling,
								currentApplUser, readOnly);
						tabbedPaneDepartment.addTab("Innleie",
								IconEnum.ICON_RENTAL.getIcon(), panelRental,
								null);
						panelRental.addTableMouseListener(tableMouseListener);

					}
					{
						panelOtherContract = new PanelOtherContract(this,
								currentAvdeling, currentApplUser, readOnly);
						tabbedPaneDepartment.addTab("Øvrige kontrakter",
								IconEnum.ICON_OTHER_CONTRACTS.getIcon(),
								panelOtherContract, null);
						panelOtherContract
								.addTableMouseListener(tableMouseListener);

					}
					/*{
						panelOtherConditions = new PanelOtherConditions(this,
								currentAvdeling, currentApplUser, readOnly);
						tabbedPaneDepartment.addTab("Øvrige betingelser",
								IconEnum.ICON_CONDITION.getIcon(),
								panelOtherConditions, null);
						panelOtherConditions
								.addTableMouseListener(tableMouseListener);

					}*/
					{
						panelMirrorCondition = new PanelMirrorCondition(this,
								currentAvdeling, currentApplUser, readOnly);
						tabbedPaneDepartment.addTab("Speilede kostnader",
								IconEnum.ICON_MIRROR.getIcon(),
								panelMirrorCondition, null);
						panelMirrorCondition
								.addTableMouseListener(tableMouseListener);

					}
					{
						panelGuarantee = new PanelGuarantee(this,
								currentAvdeling, currentApplUser, readOnly);
						tabbedPaneDepartment.addTab("Garantier",
								IconEnum.ICON_GUARANTEE.getIcon(),
								panelGuarantee, null);
						panelGuarantee
								.addTableMouseListener(tableMouseListener);

					}
					{
						panelLog = new PanelLog(this, currentAvdeling,
								currentApplUser, readOnly);
						tabbedPaneDepartment.addTab("Logg", IconEnum.ICON_LOG
								.getIcon(), panelLog, null);
						panelLog.addTableMouseListener(tableMouseListener);

					}

				}
			}
			{
				panelSouth = new JPanel();
				this.getContentPane().add(panelSouth, BorderLayout.SOUTH);
				panelSouth.setPreferredSize(new java.awt.Dimension(10, 40));
				{
					buttonSave.setIcon(IconEnum.ICON_SAVE.getIcon());
					buttonSave.setMnemonic(KeyEvent.VK_G);
					panelSouth.add(buttonSave);
					buttonSave.setText("Lagre");
					buttonSave.setPreferredSize(new java.awt.Dimension(90, 25));
					buttonSave.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonSaveActionPerformed(evt);
						}
					});

				}
				{
					buttonCancel = new JButton();
					buttonCancel.setIcon(IconEnum.ICON_CANCEL.getIcon());
					buttonCancel.setMnemonic(KeyEvent.VK_A);
					panelSouth.add(buttonCancel);
					buttonCancel.setText("Avbryt");
					buttonCancel
							.setPreferredSize(new java.awt.Dimension(90, 25));
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
	 * Avslutter vindu
	 * 
	 * @param evt
	 */
	void buttonCancelActionPerformed(ActionEvent evt) {
		if (tabbedPaneDepartment.isTabbedPanesModified()
				|| panelDepartment.isModified()) {
			if (!GuiUtil.showConfirmFrame(this, "Avslutte?",
					"Vil du avslutte uten å lagre data?")) {
				return;
			}
		}
		locker.unlock();
		dispose();
	}

	/**
	 * Lagrer endringer
	 * 
	 * @param evt
	 */
	void buttonSaveActionPerformed(ActionEvent evt) {
		boolean newDep = false;
		try {

			tabbedPaneDepartment.currentPanelBeforeSave();
			Avdeling panelAvdeling = panelDepartment.getCurrentAvdeling();

			if (panelAvdeling.getAvdelingId() == null) {

				newDep = true;
			}

			if (panelAvdeling.getAvdnr() == null) {

				GuiUtil.showErrorMsgFrame(this, "Feil", "Avdeling må ha avdnr");
				return;
			}

			if (!newDep) {
				tabbedPaneDepartment.logAdded();
			}
			panelDepartment.logAdded();

			avdelingDAO.saveAvdeling(panelAvdeling);

			panelDepartment.setModified(false);

			panelDepartment.clearAdded();

			tabbedPaneDepartment.currentPanelSaved();
			currentAvdelingV = avdelingVDAO.getAvdelingV(currentAvdeling
					.getAvdelingId());

			if (!panelAvdeling.equals(currentAvdeling)) {
				currentAvdeling = panelAvdeling;
				panelDepartment.setAvdeling(currentAvdeling, currentAvdelingV);
			} else {
				panelDepartment.setAvdeling(null, currentAvdelingV);
			}
		} catch (RuntimeException e) {
			if (newDep) {
				currentAvdeling.setAvdelingId(null);
			}
			tabbedPaneDepartment.currentPanelSaveFailed();
			String msg = GuiUtil.getUserExceptionMsg(e.getCause());

			if (msg == null) {
				msg = e.getMessage();
			}
			logger.error("Feil ved lagring av avdeling", e);
			GuiUtil.showErrorMsgFrame(this, "Feil ved lagring", msg);
		} catch (FrafException fraEx) {
			tabbedPaneDepartment.currentPanelSaveFailed();
			logger.error("Feil ved lagring av avdeling", fraEx);
			GuiUtil.showErrorMsgFrame(this, "Feil ved lagring", fraEx
					.getMessage());
		}
	}

	/**
	 * Klasse som håndterer popupmeny i alle panel for avdeling
	 * 
	 * @author abr99
	 * 
	 */
	private class MenuItemListener implements ActionListener {
		/**
		 * 
		 */
		private Component parent;

		/**
		 * @param aParent
		 */
		public MenuItemListener(Component aParent) {
			parent = aParent;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent actionEvent) {

			JPopupMenu sourceParent = (JPopupMenu) ((JComponent) actionEvent
					.getSource()).getParent();
			String invokerName = sourceParent.getInvoker().getName();
			FrafPanel frafPanel = null;

			if (invokerName != null) {
				if (invokerName.equalsIgnoreCase(PanelOwnership.NAME_OWNERSHIP)) {
					frafPanel = panelOwnerships;
				} else if (invokerName
						.equalsIgnoreCase(PanelCondition.NAME_CONDITION)) {
					frafPanel = panelCondition;
				} else if (invokerName
						.equalsIgnoreCase(PanelAdendum.NAME_ADENDUM)) {
					frafPanel = panelAdendum;
				} else if (invokerName
						.equalsIgnoreCase(PanelOtherContract.NAME_OTHER_CONTRACT)) {
					frafPanel = panelOtherContract;
				} else if (invokerName
						.equalsIgnoreCase(PanelContract.NAME_CONTRACT)) {
					frafPanel = panelContracts;
				} else if (invokerName
						.equalsIgnoreCase(PanelSecurity.NAME_SECURITY)) {
					frafPanel = panelSecurity;
				} else if (invokerName.equalsIgnoreCase(PanelLog.NAME_LOG)) {
					frafPanel = panelLog;
				} else if (invokerName
						.equalsIgnoreCase(PanelInvoice.NAME_INVOICE)) {
					frafPanel = panelInvoice;
				} else if (invokerName
						.equalsIgnoreCase(PanelBudget.NAME_BUDGET)) {
					frafPanel = panelBudgets;
				} else if (invokerName.equalsIgnoreCase(PanelSold.NAME_SOLD)) {
					frafPanel = panelSolds;
				/*} else if (invokerName
						.equalsIgnoreCase(PanelOtherConditions.NAME_OTHER_CONDITIONS)) {
					frafPanel = panelOtherConditions;*/
				} else if (invokerName
						.equalsIgnoreCase(PanelRental.NAME_RENTAL)) {
					frafPanel = panelRental;
				} else if (invokerName
						.equalsIgnoreCase(PanelMirrorCondition.NAME_MIRROR)) {
					frafPanel = panelMirrorCondition;
				} else if (invokerName
						.equalsIgnoreCase(PanelGuarantee.NAME_GUARANTEE)) {
					frafPanel = panelGuarantee;
				}
			}

			if (actionEvent.getActionCommand().equalsIgnoreCase(
					menuItemAddRow.getActionCommand())) {
				GuiUtil.setWaitCursor(parent);

				if (frafPanel != null) {
					frafPanel.addObject();
				}
				GuiUtil.setDefaultCursor(parent);

			} else if (actionEvent.getActionCommand().equalsIgnoreCase(
					menuItemRemoveRow.getActionCommand())) {
				GuiUtil.setWaitCursor(parent);

				if (frafPanel != null) {
					frafPanel.removeObject();
				}
				GuiUtil.setDefaultCursor(parent);

			} else if (actionEvent.getActionCommand().equalsIgnoreCase(
					menuItemExcel.getActionCommand())) {
				GuiUtil.setWaitCursor(parent);

				if (frafPanel != null) {
					frafPanel.showInExcel();
				}
				GuiUtil.setDefaultCursor(parent);

			}

		}

	}

	/**
	 * Klasse som håndterer museklikk i tabeller og scrollpaneler
	 * 
	 * @author abr99
	 * 
	 */
	public class TableMouseListener implements MouseInputListener {

		/**
		 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 */
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON3) {
				JComponent source = (JComponent) e.getSource();
				String addText = null;
				String removeText = null;

				String sourceName = source.getName();

				if (sourceName != null && !readOnly) {
					if (sourceName
							.equalsIgnoreCase(PanelOwnership.NAME_OWNERSHIP)) {
						addText = "Legg til eier";
						removeText = "Fjern eier";
					} else if (sourceName
							.equalsIgnoreCase(PanelCondition.NAME_CONDITION)) {
						addText = "Legg til betingelse";
						removeText = "Fjern betingelse";
					} else if (sourceName
							.equalsIgnoreCase(PanelAdendum.NAME_ADENDUM)) {
						addText = "Legg til adendum";
						removeText = "Fjern adendum";
					} else if (sourceName
							.equalsIgnoreCase(PanelOtherContract.NAME_OTHER_CONTRACT)) {
						addText = "Legg til kontrakt";
						removeText = "Fjern kontrakt";
					} else if (sourceName
							.equalsIgnoreCase(PanelContract.NAME_CONTRACT)) {
						addText = "Legg til kontrakt";
						removeText = "Fjern kontrakt";
					} else if (sourceName
							.equalsIgnoreCase(PanelSecurity.NAME_SECURITY)) {
						addText = "Legg til sikkerhet";
						removeText = "Fjern sikkerhet";
					} else if (sourceName.equalsIgnoreCase(PanelLog.NAME_LOG)) {
						addText = "Legg til kommentar";
						removeText = null;
					} else if (sourceName
							.equalsIgnoreCase(PanelBudget.NAME_BUDGET)) {
						removeText = "Fjern budsjett";
					} else if (sourceName
							.equalsIgnoreCase(PanelOtherConditions.NAME_OTHER_CONDITIONS)) {
						addText = "Legg til betingelse";
						removeText = "Fjern betingelse";
					} else if (sourceName
							.equalsIgnoreCase(PanelRental.NAME_RENTAL)) {
						removeText = "Fjern speiling";
					} else if (sourceName
							.equalsIgnoreCase(PanelMirrorCondition.NAME_MIRROR)) {
						removeText = "Fjern speilet kostnad";
					} else if (sourceName
							.equalsIgnoreCase(PanelGuarantee.NAME_GUARANTEE)) {
						addText = "Legg til garanti";
						removeText = "Fjern garanti";
					}
				}
				if (addText != null) {
					menuItemAddRow.setText(addText);
					popupMenuTable.add(menuItemAddRow);

				} else {
					popupMenuTable.remove(menuItemAddRow);
				}

				if (removeText != null) {
					menuItemRemoveRow.setText(removeText);
					popupMenuTable.add(menuItemRemoveRow);
				} else {
					popupMenuTable.remove(menuItemRemoveRow);
				}

				popupMenuTable.show(source, e.getX(), e.getY());
			}

		}

		/**
		 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
		 */
		public void mouseEntered(MouseEvent arg0) {
		}

		/**
		 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
		 */
		public void mouseExited(MouseEvent arg0) {
		}

		/**
		 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
		 */
		public void mousePressed(MouseEvent arg0) {
		}

		/**
		 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
		 */
		public void mouseReleased(MouseEvent arg0) {
		}

		/**
		 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
		 */
		public void mouseDragged(MouseEvent arg0) {
		}

		/**
		 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
		 */
		public void mouseMoved(MouseEvent arg0) {
		}

	}

	/**
	 * Enabler/disabler komponenter i vindu
	 * 
	 * @param enable
	 */
	public void enableFrameComponents(boolean enable) {
		buttonCancel.setEnabled(enable);
		buttonSave.setEnabled(enable);
		tabbedPaneDepartment.setEnabled(enable);
	}
}
