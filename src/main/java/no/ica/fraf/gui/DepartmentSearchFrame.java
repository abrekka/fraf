package no.ica.fraf.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.MouseInputListener;
import javax.swing.table.TableColumn;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.ApplUserTypeDAO;
import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.dao.view.AvdelingVDAO;
import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.department.DepartmentFrame;
import no.ica.fraf.gui.model.ContractOverdueColumn;
import no.ica.fraf.gui.model.ContractOverdueTableCellRenderer;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.TableSorter;
import no.ica.fraf.gui.model.YesNoInteger;
import no.ica.fraf.gui.model.TableSorter.Directive;
import no.ica.fraf.gui.model.interfaces.SortingListener;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.AvdelingV;
import no.ica.fraf.util.ExcelUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Dette er oppstartsbildet til applikasjonen og kan brukes til å hente ut de
 * avdelinger man måtte ønske
 * 
 * @author abr99
 * 
 */
public class DepartmentSearchFrame extends javax.swing.JInternalFrame implements
		Threadable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JLabel labelTableCount;

	/**
	 * 
	 */
	JTable tableWorksheet;

	/**
	 * 
	 */
	JScrollPane scrollPaneWorksheet;

	/**
	 * 
	 */
	private PanelFilterSearch panelFilterSearch;

	/**
	 * 
	 */
	JButton buttonRefresh;

	/**
	 * 
	 */
	JScrollPane scrollPaneFilter;

	/**
	 * 
	 */
	JButton buttonShowDepartment;

	/**
	 * 
	 */
	private JButton buttonExcel;

	/**
	 * 
	 */
	JButton buttonDeleteDepartment;

	/**
	 * 
	 */
	JButton buttonNewDepartment;

	/**
	 * 
	 */
	JButton buttonCancel;

	/**
	 * InternalFrame-peker til bruk i innerklasser
	 */
	//JInternalFrame internalFrame;

	/**
	 * Sorterer tabell
	 */
	TableSorter tableSorter;

	/**
	 * 
	 */
	JPopupMenu popupMenuExcel;

	/**
	 * 
	 */
	JMenuItem menuItemExcel;

	/**
	 * Bruker
	 */
	ApplUser currentUser;

	/**
	 * TableModel for tabell
	 */
	private ObjectTableModel<AvdelingV> avdelingVTableModel;

	/**
	 * DAO for avdeling view
	 */
	AvdelingVDAO avdelingVDAO;

	/**
	 * DAO for avdeling
	 */
	private AvdelingDAO avdelingDAO;

	/**
	 * Gjeldende filter
	 */
	AvdelingV currentFilter=new AvdelingV();;

	/**
	 * DAO for brukertype
	 */
	private ApplUserTypeDAO applUserTypeDAO;

	/**
	 * 
	 */
	private JButton buttonHelp;

	/**
	 * 
	 */
	private JCheckBox checkBoxShowAll;

	/**
	 * 
	 */
	private JButton buttonClearFilter;

	/**
	 * 
	 */
	private JButton buttonFilter;

	/**
	 * Def for tabell
	 */
	private ObjectTableDef objectTableDef;

	/**
	 * Gjeldende sortering
	 */
	List currentDirectives = null;

	/**
	 * Kolonnenavn
	 */
	private static String[] COLUMN_NAMES = { "I", "Avdnr", "Navn",
			"Juridisk navn", "Kontrakt fra", "Kontrakt til", "Opprettet",
			"Endret", "Status", "Kontrakt", "Region", "Kjede", "Bokf. selskap",
			"Selskap", "Salgssjef", "Fornyelse", "PIB", "Kontaktperson",
			"Franchisetaker", "Arkiv" };

	/**
	 * Metoder for verdier i kolonner
	 */
	private static String[] METHODS = { "ContractOverdue", "Avdnr",
			"AvdelingNavn", "JuridiskNavn", "KontraktFraDato",
			"KontraktTilDato", "OpprettetDato", "EndretDato", "Status",
			"KontraktType", "Region", "Kjede", "BokfSelskap",
			"SelskapRegnskap", "Salgssjef", "FornyelseTypeTxt", "Pib",
			"Ansvarlig", "Franchisetaker", "ArchiveInfo" };

	/**
	 * Klassetyper for kolonner
	 */
	private static Class[] PARAMS = { ContractOverdueColumn.class,
			String.class, String.class, String.class, Date.class, Date.class,
			Date.class, Date.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, YesNoInteger.class, String.class, String.class,
			String.class };

	/**
	 * Kolonnenavn i excel
	 */
	static String[] COLUMN_NAMES_EXCEL = { "Avdnr", "Navn", "Juridisk navn",
			"Kontrakt fra", "Kontrakt til", "Opprettet", "Endret", "Status",
			"Kontrakt", "Region", "Kjede", "Bokf. selskap", "Selskap",
			"Salgssjef", "Fornyelse", "PIB", "Kontaktperson", "Franchisetaker",
			"Arkiv" };

	/**
	 * Metoder for kolonner i excel
	 */
	static String[] METHODS_EXCEL = { "Avdnr", "AvdelingNavn", "JuridiskNavn",
			"KontraktFraDato", "KontraktTilDato", "OpprettetDato",
			"EndretDato", "Status", "KontraktType", "Region", "Kjede",
			"BokfSelskap", "SelskapRegnskap", "Salgssjef", "FornyelseTypeTxt",
			"Pib", "Ansvarlig", "Franchisetaker", "ArchiveInfo" };

	/**
	 * Klassetyper for kolonner i excel
	 */
	static Class[] PARAMS_EXCEL = { String.class, String.class, String.class,
			Date.class, Date.class, Date.class, Date.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, YesNoInteger.class,
			String.class, String.class, String.class };

	/**
	 * Kolonneformatering i excel
	 */
	static ColumnFormatEnum[] FORMATS_EXCEL = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.DATE,
			ColumnFormatEnum.DATE, ColumnFormatEnum.DATE,
			ColumnFormatEnum.DATE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE, ColumnFormatEnum.NONE };

	/**
	 * 
	 */
	// private EflowUsersVManager eflowUsersVManager;
	/**
	 * Konstruktør
	 * 
	 * @param parentFrame
	 *            hovedvindu
	 * @param applUser
	 *            bruker
	 */
	public DepartmentSearchFrame(ApplUser applUser
	// ,EflowUsersVManager aEflowUsersVManager
	) {
		super();
		// eflowUsersVManager = aEflowUsersVManager;
		//internalFrame = this;
		currentUser = applUser;

		avdelingVDAO = (AvdelingVDAO) ModelUtil.getBean("avdelingVDAO");
		avdelingDAO = (AvdelingDAO) ModelUtil.getBean("avdelingDAO");
		applUserTypeDAO = (ApplUserTypeDAO) ModelUtil
				.getBean("applUserTypeDAO");

		tableSorter = new TableSorter();
		tableSorter.addSortChangeListener(new TableSortingListener());
		initGUI();
		initUser();
		initTable();

		setFrameIcon(IconEnum.ICON_FRAF.getIcon());
	}

	/**
	 * Initierer tilgang for bruker
	 * 
	 */
	private void initUser() {
		buttonDeleteDepartment.setEnabled(false);
		buttonNewDepartment.setEnabled(false);
		if (applUserTypeDAO.isWriter(currentUser.getApplUserType())
				|| applUserTypeDAO.isAdministrator(currentUser
						.getApplUserType())) {
			buttonDeleteDepartment.setEnabled(true);
			buttonNewDepartment.setEnabled(true);
		}
	}

	/**
	 * Initierer tabell som vise avdelinger
	 * 
	 */
	private void initTable() {

		objectTableDef = new ObjectTableDef(COLUMN_NAMES, METHODS, PARAMS);

		avdelingVTableModel = new ObjectTableModel<AvdelingV>(objectTableDef);
		avdelingVTableModel.setEditable(false);
		tableSorter.setTableModel(avdelingVTableModel);
		tableSorter.setTableHeader(tableWorksheet.getTableHeader());
		tableWorksheet.setModel(tableSorter);
		tableWorksheet.putClientProperty("terminateEditOnFocusLost",
				Boolean.TRUE);
		setColumnWidths();

	}

	/**
	 * Laster data
	 * 
	 * @param filter
	 *            filtrering som er satt på avdelinger
	 * @param init
	 *            true dersom metoden skal kjøre initiering
	 * @param directives
	 *            sortering
	 */
	public void loadData(final AvdelingV filter, final boolean init,
			final List directives) {
		GuiUtil.runInThread(this, "Avdelinger", "Henter avdelinger...", this,
				new Object[] { filter, new Boolean(init), directives });

	}

	/**
	 * Initerer GUI
	 */
	private void initGUI() {
		setTitle("Avdelinger");

		ButtonListener buttonListener = new ButtonListener();
		MyAdjustmentListener adjustmentListener = new MyAdjustmentListener();
		DepartmentSearchTableMouseListener ledworkshPsTableMouseListener = new DepartmentSearchTableMouseListener();
		try {
			this.setPreferredSize(new java.awt.Dimension(955, 450));
			this.setBounds(0, 0, 955, 450);
			BorderLayout thisLayout = new BorderLayout();
			this.getContentPane().setLayout(thisLayout);
			setVisible(true);
			this.setIconifiable(true);
			this.setMaximizable(true);
			this.setResizable(true);
			this.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent evt) {
					rootKeyPressed(evt);
				}
			});

			{
				JPanel panelCenter = new JPanel();
				BorderLayout panelCenterLayout = new BorderLayout();
				panelCenter.setLayout(panelCenterLayout);
				this.getContentPane().add(panelCenter, BorderLayout.CENTER);
				{
					JPanel panelCenterCenter = new JPanel();
					BorderLayout panelCenterCenterLayout = new BorderLayout();
					panelCenterCenter.setLayout(panelCenterCenterLayout);
					panelCenter.add(panelCenterCenter, BorderLayout.CENTER);
					{
						scrollPaneWorksheet = new JScrollPane();
						panelCenterCenter.add(scrollPaneWorksheet,
								BorderLayout.CENTER);
						{
							tableWorksheet = new JTable();
							scrollPaneWorksheet.setViewportView(tableWorksheet);
							tableWorksheet
									.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
							tableWorksheet
									.addMouseListener(ledworkshPsTableMouseListener);

							tableWorksheet.addKeyListener(new KeyAdapter() {
								@Override
								public void keyPressed(KeyEvent e) {
									tableWorksheetKeyPressed(e);
								}
							});

						}
						scrollPaneWorksheet.getHorizontalScrollBar()
								.addAdjustmentListener(adjustmentListener);
					}
					{
						JPanel panelCenterWest = new JPanel();
						panelCenterCenter.add(panelCenterWest,
								BorderLayout.WEST);
						panelCenterWest
								.setPreferredSize(new java.awt.Dimension(5, 10));
					}
				}
			}
			{
				JPanel panelSouth = new JPanel();
				BorderLayout panelSouthLayout = new BorderLayout();
				panelSouth.setLayout(panelSouthLayout);
				this.getContentPane().add(panelSouth, BorderLayout.SOUTH);
				panelSouth.setPreferredSize(new java.awt.Dimension(10, 70));
				{
					JPanel panelButtons = new JPanel();
					BorderLayout panelButtonsLayout = new BorderLayout();
					panelButtons.setLayout(panelButtonsLayout);
					panelSouth.add(panelButtons, BorderLayout.CENTER);
					{
						JPanel panelCenterButtons = new JPanel();
						panelButtons.add(panelCenterButtons,
								BorderLayout.CENTER);
						{
							buttonShowDepartment = new JButton();
							buttonShowDepartment.setIcon(IconEnum.ICON_SHOW
									.getIcon());
							buttonShowDepartment.setEnabled(false);
							buttonShowDepartment.setMnemonic(KeyEvent.VK_V);
							panelCenterButtons.add(buttonShowDepartment);
							buttonShowDepartment.setText("Vis avdeling");
							buttonShowDepartment
									.setPreferredSize(new java.awt.Dimension(
											130, 25));
							buttonShowDepartment
									.addActionListener(buttonListener);
						}
						{
							buttonExcel = new JButton();
							buttonExcel.setIcon(IconEnum.ICON_EXCEL.getIcon());
							buttonExcel.setMnemonic(KeyEvent.VK_E);
							panelCenterButtons.add(buttonExcel);
							buttonExcel.setText("Excel");
							buttonExcel
									.setPreferredSize(new java.awt.Dimension(
											130, 25));
							buttonExcel.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									buttonExcelActionPerformed(evt);
								}
							});
						}
						{
							buttonNewDepartment = new JButton();
							buttonNewDepartment.setIcon(IconEnum.ICON_CREATE
									.getIcon());
							buttonNewDepartment.setMnemonic(KeyEvent.VK_N);
							panelCenterButtons.add(buttonNewDepartment);
							buttonNewDepartment.setText("Ny avdeling");
							buttonNewDepartment
									.setPreferredSize(new java.awt.Dimension(
											130, 25));
							buttonNewDepartment
									.addActionListener(buttonListener);
						}
						{
							buttonDeleteDepartment = new JButton();
							buttonDeleteDepartment.setIcon(IconEnum.ICON_DELETE
									.getIcon());
							buttonDeleteDepartment.setEnabled(false);
							buttonDeleteDepartment.setMnemonic(KeyEvent.VK_S);
							panelCenterButtons.add(buttonDeleteDepartment);
							buttonDeleteDepartment.setText("Slett avdeling");
							buttonDeleteDepartment
									.setPreferredSize(new java.awt.Dimension(
											130, 25));
							buttonDeleteDepartment
									.addActionListener(buttonListener);
						}
						{
							buttonRefresh = new JButton();
							buttonRefresh.setIcon(IconEnum.ICON_REFRESH
									.getIcon());
							buttonRefresh.setMnemonic(KeyEvent.VK_O);
							panelCenterButtons.add(buttonRefresh);
							buttonRefresh.setText("Oppdater");
							buttonRefresh
									.setPreferredSize(new java.awt.Dimension(
											130, 25));
							buttonRefresh.addActionListener(buttonListener);
						}
						{
							buttonCancel = new JButton();
							buttonCancel
									.setIcon(IconEnum.ICON_CANCEL.getIcon());
							buttonCancel.setMnemonic(KeyEvent.VK_A);
							panelCenterButtons.add(buttonCancel);
							buttonCancel.setText("Avbryt");
							buttonCancel
									.setPreferredSize(new java.awt.Dimension(
											130, 25));
							buttonCancel.addActionListener(buttonListener);
						}
					}
				}
				{
					JPanel panelInfo = new JPanel();
					FlowLayout panelInfoLayout = new FlowLayout();
					panelInfoLayout.setAlignment(FlowLayout.LEFT);
					panelInfo.setLayout(panelInfoLayout);
					panelSouth.add(panelInfo, BorderLayout.NORTH);
					panelInfo.setPreferredSize(new java.awt.Dimension(10, 20));
					{
						JLabel labelCount = new JLabel();
						panelInfo.add(labelCount);
						labelCount.setText("Antall:");
					}
					{
						labelTableCount = new JLabel();
						panelInfo.add(labelTableCount);
						labelTableCount
								.setPreferredSize(new java.awt.Dimension(150,
										10));
					}
				}
			}
			{
				JPanel panelNorth = new JPanel();
				BorderLayout panelNorthLayout = new BorderLayout();
				panelNorth.setLayout(panelNorthLayout);
				this.getContentPane().add(panelNorth, BorderLayout.NORTH);
				panelNorth.setPreferredSize(new java.awt.Dimension(10, 110));
				panelNorth
						.setBorder(BorderFactory.createTitledBorder("Filter"));
				panelNorth.setSize(980, 110);
				{
					scrollPaneFilter = new JScrollPane();

					panelNorth.add(scrollPaneFilter, BorderLayout.CENTER);
					scrollPaneFilter
							.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					{
						panelFilterSearch = new PanelFilterSearch(this);
						scrollPaneFilter.setViewportView(panelFilterSearch);

					}
				}
				{
					popupMenuExcel = new JPopupMenu("Excel");
					menuItemExcel = new JMenuItem("Eksporter data til excel");
					menuItemExcel.setIcon(IconEnum.ICON_EXCEL.getIcon());
					popupMenuExcel.add(menuItemExcel);
					menuItemExcel.addActionListener(new MenuItemListener(this));

				}
				scrollPaneFilter.getHorizontalScrollBar().setVisible(false);
				scrollPaneFilter.setSize(1000, 84);
				{
					JPanel panelFilterButtons = new JPanel();
					panelNorth.add(panelFilterButtons, BorderLayout.SOUTH);
					panelFilterButtons.setPreferredSize(new java.awt.Dimension(
							10, 40));
					{
						checkBoxShowAll = new JCheckBox();
						panelFilterButtons.add(checkBoxShowAll);
						checkBoxShowAll.setText("Vis alle");
						checkBoxShowAll.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								checkBoxShowAllActionPerformed(evt);
							}
						});
					}
					{
						buttonFilter = new JButton();
						buttonFilter.setIcon(IconEnum.ICON_FILTER.getIcon());
						panelFilterButtons.add(buttonFilter);
						buttonFilter.setText("Filtrer");
						buttonFilter.setPreferredSize(new java.awt.Dimension(
								110, 25));
						buttonFilter.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								buttonFilterActionPerformed(evt);
							}
						});
					}
					{
						buttonClearFilter = new JButton();
						buttonClearFilter.setIcon(IconEnum.ICON_NO_FILTER
								.getIcon());
						panelFilterButtons.add(buttonClearFilter);
						buttonClearFilter.setText("Tøm filter");
						buttonClearFilter
								.setPreferredSize(new java.awt.Dimension(110,
										25));
						buttonClearFilter
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										buttonClearFilterActionPerformed(evt);
									}
								});
					}
					{
						buttonHelp = new JButton();
						panelFilterButtons.add(buttonHelp);
						buttonHelp.setIcon(IconEnum.ICON_HELP.getIcon());
						panelFilterButtons.add(buttonHelp);
						buttonHelp.setActionCommand("help");
						buttonHelp.setPreferredSize(new java.awt.Dimension(20,
								20));
						buttonHelp.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								buttonHelpActionPerformed(evt);
							}
						});
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initierer data
	 * 
	 * @param filter
	 * @param init
	 * @param directives
	 * @return true dersom alt gikk greit
	 */
	private String initData(AvdelingV filter, boolean init,
			List<Directive> directives) {
		if (init) {
			initTable();
		}
		currentFilter = filter;
		currentDirectives = directives;
		String errorString = null;
		try {
			Collection<AvdelingV> avdelingVs = null;

			if (filter != null || checkBoxShowAll.isSelected()) {
				filter=filter!=null?filter:new AvdelingV();
				avdelingVs = avdelingVDAO.findAll(directives, filter);
				labelTableCount.setText(String.valueOf(avdelingVs.size()));
			}

			avdelingVTableModel.setData(avdelingVs);

			if (avdelingVs != null && avdelingVs.size() != 0) {
				tableWorksheet.changeSelection(0, 0, false, false);
				tableWorksheet.requestFocus();
			}
			boolean usingFilter = false;

			if (filter != null) {
				usingFilter = true;
			}

			StringBuffer infoText = new StringBuffer();

			if (avdelingVs!=null&&avdelingVs.size() <= 0 && !usingFilter) {
				infoText.append("Det finnes ingen avdelinger");
			} else if (avdelingVs!=null&&avdelingVs.size() <= 0 && usingFilter) {
				infoText
						.append("Det finnes ingen avdelinger som tilfredstiller filter");
			}

		} catch (Exception e) {
			errorString = e.getMessage();
			e.printStackTrace();
		}
		return errorString;
	}

	/**
	 * Setter kolonnebredder
	 */
	private void setColumnWidths() {
		ContractOverdueTableCellRenderer contractOverdueTableCellRenderer = new ContractOverdueTableCellRenderer();
		// Ikon
		TableColumn col = tableWorksheet.getColumnModel().getColumn(0);
		col.setPreferredWidth(25);
		col.setCellRenderer(contractOverdueTableCellRenderer);

		// Avdnr
		col = tableWorksheet.getColumnModel().getColumn(1);
		col.setPreferredWidth(60);

		// Avdelingsnavn
		col = tableWorksheet.getColumnModel().getColumn(2);
		col.setPreferredWidth(200);

		// Juridisk navn
		col = tableWorksheet.getColumnModel().getColumn(3);
		col.setPreferredWidth(200);

		// Kontrakt fra
		col = tableWorksheet.getColumnModel().getColumn(4);
		col.setPreferredWidth(125);

		// Kontyrakt til
		col = tableWorksheet.getColumnModel().getColumn(5);
		col.setPreferredWidth(125);

		// Oprettet
		col = tableWorksheet.getColumnModel().getColumn(6);
		col.setPreferredWidth(125);

		// Endret
		col = tableWorksheet.getColumnModel().getColumn(7);
		col.setPreferredWidth(125);

		// Status
		col = tableWorksheet.getColumnModel().getColumn(8);
		col.setPreferredWidth(80);

		// Kontrakt
		col = tableWorksheet.getColumnModel().getColumn(9);
		col.setPreferredWidth(145);

		// Region
		col = tableWorksheet.getColumnModel().getColumn(10);
		col.setPreferredWidth(170);

		// Kjede
		col = tableWorksheet.getColumnModel().getColumn(11);
		col.setPreferredWidth(140);

		// BokfSelskap
		col = tableWorksheet.getColumnModel().getColumn(12);
		col.setPreferredWidth(90);

		// Selskap
		col = tableWorksheet.getColumnModel().getColumn(13);
		col.setPreferredWidth(70);

		// Salgssjef
		col = tableWorksheet.getColumnModel().getColumn(14);
		col.setPreferredWidth(120);

		// Fornyelse
		col = tableWorksheet.getColumnModel().getColumn(15);
		col.setPreferredWidth(150);

		// PIB
		col = tableWorksheet.getColumnModel().getColumn(16);
		col.setPreferredWidth(70);

		// Kontaktperson
		col = tableWorksheet.getColumnModel().getColumn(17);
		col.setPreferredWidth(150);

		// Franchisetaker
		col = tableWorksheet.getColumnModel().getColumn(18);
		col.setPreferredWidth(150);

		// Arkiv
		col = tableWorksheet.getColumnModel().getColumn(19);
		col.setPreferredWidth(50);
	}

	/**
	 * Klasse som håndterer knappetrykk
	 * 
	 * @author abr99
	 * 
	 */
	private class ButtonListener implements ActionListener {

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent action) {
			GuiUtil.setWaitCursor(FrafMain.getInstance());
			/** ***** Cancel *** */
			if (action.getActionCommand().equalsIgnoreCase(
					buttonCancel.getActionCommand())) {
				exitDialog();
			} else if (action.getActionCommand().equalsIgnoreCase(
					buttonRefresh.getActionCommand())) {

				refresh();
			} else if (action.getActionCommand().equalsIgnoreCase(
					buttonShowDepartment.getActionCommand())) {

				showDepartmentFrame(false);
			} else if (action.getActionCommand().equalsIgnoreCase(
					buttonNewDepartment.getActionCommand())) {

				showDepartmentFrame(true);
			} else if (action.getActionCommand().equalsIgnoreCase(
					buttonDeleteDepartment.getActionCommand())) {

				deleteDepartment();
			}
			GuiUtil.setDefaultCursor(FrafMain.getInstance());
		}

	}

	/**
	 * Oppdaterer dialog
	 */
	public void refresh() {
		loadData(currentFilter, false, currentDirectives);
	}

	/**
	 * Avslutter dialog
	 */
	void exitDialog() {
		dispose();
	}

	/**
	 * Enabler/disabler knapper
	 * 
	 * @param enable
	 */
	private void enableButtons(boolean enable) {
		checkBoxShowAll.setEnabled(enable);
		buttonFilter.setEnabled(enable);
		buttonClearFilter.setEnabled(enable);
		buttonHelp.setEnabled(enable);
		buttonCancel.setEnabled(enable);

		buttonRefresh.setEnabled(enable);
		buttonExcel.setEnabled(enable);
		if (enable) {
			initUser();
		} else {
			buttonDeleteDepartment.setEnabled(enable);
			buttonNewDepartment.setEnabled(enable);
		}

		buttonShowDepartment.setEnabled(enable);

	}

	/**
	 * Klasse som håndterer synkronisering av to scrollpane
	 * 
	 * @author abr99
	 * 
	 */
	class MyAdjustmentListener implements AdjustmentListener {

		/**
		 * @see java.awt.event.AdjustmentListener#adjustmentValueChanged(java.awt.event.AdjustmentEvent)
		 */
		public void adjustmentValueChanged(AdjustmentEvent event) {
			JScrollBar scrollBarWorksheet = scrollPaneWorksheet
					.getHorizontalScrollBar();
			JScrollBar scrollBarFilter = scrollPaneFilter
					.getHorizontalScrollBar();
			int newValue;
			newValue = scrollBarWorksheet.getValue();
			scrollBarFilter.setValue(newValue);

		}

	}

	/**
	 * Enabler/disabler knapper for dialog
	 * 
	 * @param enable
	 */
	void enableDepartmentButtons(boolean enable) {
		buttonShowDepartment.setEnabled(enable);
		if (enable) {
			initUser();
		} else {
			buttonDeleteDepartment.setEnabled(enable);
		}
	}

	/**
	 * Klasse som håndterer museklikk i tabell
	 * 
	 * @author abr99
	 * 
	 */
	private class DepartmentSearchTableMouseListener implements
			MouseInputListener {

		/**
		 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 */
		public void mouseClicked(MouseEvent e) {
			enableDepartmentButtons(true);
			if (e.getButton() == MouseEvent.BUTTON3) {
				popupMenuExcel.show(tableWorksheet, e.getX(), e.getY());
			}

			if (e.getClickCount() == 2) {

				Point point = e.getPoint();
				int row = tableWorksheet.rowAtPoint(point);

				GuiUtil.setWaitCursor(FrafMain.getInstance());

				ObjectTableModel model = (ObjectTableModel) tableSorter
						.getTableModel();

				AvdelingV avdelingV = (AvdelingV) model
						.getObjectAtIndex(tableSorter.modelIndex(row));

				showDepartmentFrame(avdelingV);

				GuiUtil.setDefaultCursor(FrafMain.getInstance());

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
	 * Viser dialog for valgt avdeling
	 * 
	 * @param avdelingV
	 */
	void showDepartmentFrame(AvdelingV avdelingV) {
		GuiUtil.setWaitCursor(FrafMain.getInstance());
		DepartmentFrame departmentFrame = new DepartmentFrame(avdelingV,
				currentUser
		// , eflowUsersVManager
		);

		departmentFrame.setLocation(GuiUtil.getCenter(FrafMain.getInstance()
				.getDesktopPane().getSize(), departmentFrame.getSize()));
		FrafMain.getInstance().addInternalFrame(departmentFrame);

		departmentFrame.setVisible(true);
		try {
			departmentFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException ex) {
			ex.printStackTrace();
		}
		GuiUtil.setDefaultCursor(FrafMain.getInstance());
	}

	/**
	 * Klasse som håndterer popupmeny i tabell
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
		public void actionPerformed(ActionEvent actionEvent) {
			if (actionEvent.getActionCommand().equalsIgnoreCase(
					menuItemExcel.getText())) {
				GuiUtil.setWaitCursor(parent);

				showDataInExcel();
				GuiUtil.setDefaultCursor(parent);

			}
		}

	}

	/**
	 * Esporterer data til excel
	 */
	void showDataInExcel() {
		GuiUtil.runInThread(this, "Excel", "Genererer excel-fil",
				new ShowExcel(), null);

	}

	/**
	 * Klasse som håndterer eksport av data til excel
	 * 
	 * @author abr99
	 * 
	 */
	private class ShowExcel implements Threadable {

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
		 */
		public void enableComponents(boolean enable) {
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
		 *      javax.swing.JLabel)
		 */
		public Object doWork(Object[] params, JLabel labelInfo) {
			ObjectTableDef depTableDef = new ObjectTableDef(COLUMN_NAMES_EXCEL,
					METHODS_EXCEL, PARAMS_EXCEL, FORMATS_EXCEL);
			ObjectTableModel depTableModelExcel = new ObjectTableModel(
					depTableDef);

			final ExcelUtil excelUtil = new ExcelUtil(null);
			final String dir = excelUtil.prepare(currentUser, FrafMain.getInstance());
			String errorString = null;

			if (dir == null) {
				return new Boolean(false);
			}
			try {

				Object[] filters = new Object[] { currentDirectives,
						currentFilter };
				excelUtil.showDataInExcel(dir, "tmp.xls", avdelingVDAO,
						depTableModelExcel, filters, null, null);
			} catch (FrafException e) {
				errorString = e.getMessage();
				e.printStackTrace();
			}

			return errorString;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null) {
				GuiUtil.showErrorMsgFrame(FrafMain.getInstance(),
						"Feil ved generering av wxcel-fil", object.toString());
			}
		}

	}

	/**
	 * Viser dialog for valgt avdeling
	 * 
	 * @param newDepartment
	 */
	void showDepartmentFrame(boolean newDepartment) {
		GuiUtil.setWaitCursor(FrafMain.getInstance());
		AvdelingV avdelingV = null;
		if (!newDepartment) {
			int index = tableWorksheet.getSelectedRow();

			if (index < 0) {
				GuiUtil.showErrorMsgFrame(this, "Feil",
						"Det må velges en avdeling");
				return;
			}
			ObjectTableModel model = (ObjectTableModel) tableSorter
					.getTableModel();
			avdelingV = (AvdelingV) model.getObjectAtIndex(tableSorter
					.modelIndex(index));
		}

		showDepartmentFrame(avdelingV);
		GuiUtil.setDefaultCursor(FrafMain.getInstance());
	}

	/**
	 * Sletter valgt avdeling
	 */
	void deleteDepartment() {
		if (tableWorksheet.getSelectedRow() < 0) {
			GuiUtil.showErrorMsgFrame(this, "Feil", "Du må velge en avdeling!");
			return;
		}
		if (!GuiUtil.showConfirmFrame(this, "Slette?",
				"Vil du virkelig slette avdeling?")) {
			return;
		}

		ObjectTableModel model = (ObjectTableModel) tableSorter.getTableModel();
		AvdelingV avdelingV = (AvdelingV) model.getObjectAtIndex(tableSorter
				.modelIndex(tableWorksheet.getSelectedRow()));

		avdelingDAO.removeAvdeling(avdelingV.getAvdelingId());
		refresh();
	}

	/**
	 * Klasse som håndterer endring av sortering
	 * 
	 * @author abr99
	 * 
	 */
	private class TableSortingListener implements SortingListener {

		/**
		 * @see no.ica.fraf.gui.model.interfaces.SortingListener#sortingChanged(java.util.List)
		 */
		public void sortingChanged(List directives) {

			if (directives == null) {
				return;
			}
			loadData(currentFilter, false, directives);

		}

	}

	/**
	 * Kjøres ved trykk på knapp for eksport til excel
	 * 
	 * @param evt
	 */
	void buttonExcelActionPerformed(ActionEvent evt) {
		showDataInExcel();
	}

	/**
	 * Ved trykk på ENTER blir denne metoden kjørt, og vil vise valgt avdeling
	 * 
	 * @param e
	 */
	void tableWorksheetKeyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			showDepartmentFrame(false);
		}
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
	 */
	public void enableComponents(boolean enable) {
		enableButtons(enable);
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	@SuppressWarnings("unchecked")
	public Object doWork(Object[] params, JLabel labelInfo) {
		return initData((AvdelingV) params[0], ((Boolean) params[1])
				.booleanValue(), (List<Directive>) params[2]);
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
		if (object != null) {
			GuiUtil.showErrorMsgFrame(this, "Feil", object.toString());
		}
		tableWorksheet.requestFocus();

	}

	/**
	 * @return Returns the currentDirectives.
	 */
	public List getCurrentDirectives() {
		return currentDirectives;
	}

	/**
	 * @return Returns the currentFilter.
	 */
	public AvdelingV getCurrentFilter() {
		return currentFilter;
	}

	/**
	 * Kjøres dersom ENTER trykkes, filtrering kjøres
	 * 
	 * @param evt
	 */
	void rootKeyPressed(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			doFilter();
		}
	}

	/**
	 * Filtrerer
	 */
	public void doFilter() {
		currentFilter = panelFilterSearch.getFilter();
		loadData(currentFilter, false, getCurrentDirectives());
	}

	/**
	 * Filtrer
	 * 
	 * @param evt
	 */
	void buttonFilterActionPerformed(ActionEvent evt) {
		doFilter();
	}

	/**
	 * Fjern filter
	 * 
	 * @param evt
	 */
	void buttonClearFilterActionPerformed(ActionEvent evt) {
		currentFilter = new AvdelingV();;
		panelFilterSearch.clearFilter();

		loadData(null, false, getCurrentDirectives());
	}

	/**
	 * Vis alle
	 * 
	 * @param evt
	 */
	void checkBoxShowAllActionPerformed(ActionEvent evt) {
		loadData(getCurrentFilter(), false, getCurrentDirectives());
	}

	/**
	 * Vis hjelp
	 * 
	 * @param evt
	 */
	void buttonHelpActionPerformed(ActionEvent evt) {
		GuiUtil
				.showMsgFrame(
						this,
						"Hjelp til filtrering",
						"Generelt skriv inn ønsket filtrering for hvert felt og trykk Filtrer.\n"
								+ "All filtrering gjøres ved at alle definerte filtre må passe.\n"
								+ "Spesialtegn:\n"
								+ "%-tegn - hvilket som helst tegn og udefinert antall. Eks: %oslo - all tekst som slutter på oslo\n"
								+ "_-tegn - ett og kun ett tegn. Eks: _slo - all tekst som består av fire tegn og slutter på oslo\n"
								+ "tomme - alle felt som er tomme\n"
								+ "ikke tomme - alle felter som ikke er tomme\n"
								+ "Knappen Tøm filtrering tømmer alle feltene som definerer filteret");
	}

}
