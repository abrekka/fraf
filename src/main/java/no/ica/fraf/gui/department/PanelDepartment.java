package no.ica.fraf.gui.department;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.enums.LazyLoadAvdelingEnum;
import no.ica.fraf.gui.department.DepartmentFrame.TableMouseListener;
import no.ica.fraf.gui.handlers.DepartmentViewHandler;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingMangel;
import no.ica.fraf.model.AvdelingV;
import no.ica.fraf.util.AvdelingLoggUtil;

/**
 * Panel som viser generell data om avdeling
 * @author abr99
 *
 */
/**
 * @author abr99
 * 
 */
public final class PanelDepartment extends FrafPanel<Avdeling> {
	/**
	 * 
	 */
	private DepartmentViewHandler departmentViewHandler;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Gjeldende avdeling fra view
	 */
	private AvdelingV currentAvdelingV;

	/**
	 * Holder på nye mangler som ikke har blitt lagret, slik at de ikke skal
	 * mistes når det kjøres en lazyload
	 */
	private Vector<AvdelingMangel> newUnsavedMissing = new Vector<AvdelingMangel>();

	/**
	 * Holder på slettede mangler som ikke har blitt lagret, slik at de ikke
	 * skal mistes når det kjøres en lazyload
	 */
	private Vector<AvdelingMangel> deletetUnsavedMissing = new Vector<AvdelingMangel>();

	/**
	 * Konstruktør
	 * 
	 * @param internalFrame
	 * @param avdeling
	 * @param avdelingV
	 * @param applUser
	 * @param isReadOnly
	 */
	public PanelDepartment(DepartmentFrame internalFrame, Avdeling avdeling,
			AvdelingV avdelingV, ApplUser applUser, boolean isReadOnly) {
		super(internalFrame, avdeling, applUser, isReadOnly);

		currentAvdelingV = avdelingV;
		buildDepartmentPanel();
		// initData(currentAvdeling);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#init()
	 */
	@Override
	protected void init() {
		super.init();
		// initCombo();

		// textFieldDepId.requestFocus();
	}

	/**
	 * Setter gjeldende avdeling
	 * 
	 * @param avdeling
	 * @param avdelingV
	 */
	public void setAvdeling(Avdeling avdeling, AvdelingV avdelingV) {
		/*
		 * if (avdeling != null) { currentAvdeling = avdeling; }
		 * currentAvdelingV = avdelingV;
		 * 
		 * if (currentAvdeling == null || currentAvdeling.getAvdnr() == null) {
		 * comboBoxPostCompany.setEnabled(false); } else {
		 * comboBoxPostCompany.setEnabled(true); }
		 * 
		 * initData(avdeling);
		 */
	}

	/**
	 * Initierer selskap comboboks
	 */
	/*
	 * private void initCombo() { List<BokfSelskap> selskap =
	 * DataListUtil.getBokfSelskaper();
	 * 
	 * if (selskap == null) { selskap = new ArrayList<BokfSelskap>(); }
	 * 
	 * selskap.add(0, new BokfSelskap("ingen")); selskapModel = new
	 * DefaultComboBoxModel(selskap.toArray());
	 * comboBoxPostCompany.setModel(selskapModel);
	 *  }
	 */

	private void buildDepartmentPanel() {
		avdelingDAO.loadLazy(currentAvdeling, new LazyLoadAvdelingEnum[] {
				LazyLoadAvdelingEnum.LOAD_ADENDUM,
				LazyLoadAvdelingEnum.LOAD_MANGLER,
				LazyLoadAvdelingEnum.LOAD_CONDITION });
		WindowInterface window = new JInternalFrameAdapter(departmentFrame);
		departmentViewHandler = new DepartmentViewHandler(currentAvdeling,
				currentAvdelingV, currentApplUser, this, readOnly);
		DepartmentView departmentView = new DepartmentView(
				departmentViewHandler);
		this.add(departmentView.buildPanel(window));
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
		/*
		 * try { GridBagLayout thisLayout = new GridBagLayout();
		 * this.setLayout(thisLayout); this.setPreferredSize(new
		 * java.awt.Dimension(800, 300));
		 * 
		 * 
		 *  { JLabel labelDepId = new JLabel(); this.add(labelDepId, new
		 * GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		 * labelDepId.setText("Avdnr:"); } { textFieldDepId = new JTextField();
		 * this.add(textFieldDepId, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
		 * GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0,
		 * 0), 0, 0)); if (currentAvdeling != null &&
		 * currentAvdeling.getAvdelingId() != null) {
		 * textFieldDepId.setEditable(false); } else {
		 * textFieldDepId.setEditable(true); } textFieldDepId.setMinimumSize(new
		 * java.awt.Dimension(50, 20)); textFieldDepId.setPreferredSize(new
		 * java.awt.Dimension(50, 20));
		 *  } { buttonUpdateDep = new JButton();
		 * buttonUpdateDep.setIcon(IconEnum.ICON_REFRESH.getIcon());
		 * this.add(buttonUpdateDep, new GridBagConstraints(3, 1, 1, 1, 0.0,
		 * 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
		 * 5, 0, 0), 0, 0)); buttonUpdateDep.setText("Oppdater");
		 * buttonUpdateDep .setPreferredSize(new java.awt.Dimension(110, 20));
		 * buttonUpdateDep.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent evt) {
		 * buttonUpdateDepActionPerformed(evt); } });
		 * buttonUpdateDep.setEnabled(!readOnly);
		 *  } { JLabel labelDepName = new JLabel(); this.add(labelDepName, new
		 * GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		 * labelDepName.setText("Navn:"); } { textFieldDepName = new
		 * JTextField(); this.add(textFieldDepName, new GridBagConstraints(2, 2,
		 * 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
		 * textFieldDepName.setEditable(false); textFieldDepName
		 * .setMinimumSize(new java.awt.Dimension(150, 20));
		 * textFieldDepName.setPreferredSize(new java.awt.Dimension(200, 20));
		 *  } { JLabel labelLawName = new JLabel(); this.add(labelLawName, new
		 * GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		 * labelLawName.setText("Juridisk navn:"); } { textFieldLawName = new
		 * JTextField(); this.add(textFieldLawName, new GridBagConstraints(2, 3,
		 * 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
		 * textFieldLawName.setEditable(false); } { JLabel labelAddress1 = new
		 * JLabel(); this.add(labelAddress1, new GridBagConstraints(1, 5, 1, 1,
		 * 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new
		 * Insets(5, 0, 0, 0), 0, 0)); labelAddress1.setText("Adresse 1:"); } {
		 * textFieldAddress1 = new JTextField(); this.add(textFieldAddress1, new
		 * GridBagConstraints(2, 5, 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
		 * textFieldAddress1.setEditable(false); } { JLabel labelAddress2 = new
		 * JLabel(); this.add(labelAddress2, new GridBagConstraints(1, 6, 1, 1,
		 * 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new
		 * Insets(5, 0, 0, 0), 0, 0)); labelAddress2.setText("Adresse2:"); } {
		 * textFieldAddress2 = new JTextField(); this.add(textFieldAddress2, new
		 * GridBagConstraints(2, 6, 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
		 * textFieldAddress2.setEditable(false); } { JLabel labelPostCodePlace =
		 * new JLabel(); this.add(labelPostCodePlace, new GridBagConstraints(1,
		 * 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
		 * new Insets(5, 0, 0, 0), 0, 0));
		 * labelPostCodePlace.setText("Postnr/sted:"); } { textFieldPostalCode =
		 * new JTextField(); this.add(textFieldPostalCode, new
		 * GridBagConstraints(2, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
		 * textFieldPostalCode.setEditable(false); } { textFieldPostalPlace =
		 * new JTextField(); this.add(textFieldPostalPlace, new
		 * GridBagConstraints(3, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
		 * textFieldPostalPlace.setEditable(false); } { JLabel labelContact =
		 * new JLabel(); this.add(labelContact, new GridBagConstraints(4, 1, 1,
		 * 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new
		 * Insets(5, 10, 0, 0), 0, 0)); labelContact.setText("Kontaktpers:"); }
		 *  { textFieldContact = new JTextField(); this.add(textFieldContact,
		 * new GridBagConstraints(5, 1, 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
		 * textFieldContact.setEditable(false);
		 * textFieldContact.setPreferredSize(new java.awt.Dimension(150, 20));
		 * textFieldContact .setMinimumSize(new java.awt.Dimension(150, 20)); } {
		 * JLabel labelFranchise = new JLabel(); this .add(labelFranchise, new
		 * GridBagConstraints(4, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.NONE, new Insets(5, 10, 0, 0), 0, 0));
		 * labelFranchise.setText("Franchisetaker:"); } {
		 * textFieldFranchisetaker = new JTextField();
		 * this.add(textFieldFranchisetaker, new GridBagConstraints(5, 2, 2, 1,
		 * 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new
		 * Insets(5, 5, 0, 0), 0, 0));
		 * textFieldFranchisetaker.setEditable(true); textFieldFranchisetaker
		 * .setPreferredSize(new java.awt.Dimension(150, 20));
		 * textFieldFranchisetaker.addKeyListener(new KeyAdapter() { @Override
		 * public void keyTyped(KeyEvent evt) { textKeyTyped(evt); } });
		 * textFieldFranchisetaker.setEditable(!readOnly);
		 *  }
		 *  { JLabel labelComment = new JLabel(); this.add(labelComment, new
		 * GridBagConstraints(1, 8, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		 * labelComment.setText("Kommentar:"); } { scrollPaneComment = new
		 * JScrollPane(); this.add(scrollPaneComment, new GridBagConstraints(2,
		 * 8, 5, 3, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
		 * scrollPaneComment .setMinimumSize(new java.awt.Dimension(200, 70));
		 * scrollPaneComment.setPreferredSize(new java.awt.Dimension(200, 70));
		 * scrollPaneComment.setMaximumSize(new java.awt.Dimension(200, 32767));
		 * scrollPaneComment.setSize(200, 70); { textAreaComment = new
		 * JTextArea(); scrollPaneComment.setViewportView(textAreaComment);
		 * textAreaComment.addKeyListener(new KeyAdapter() { @Override public
		 * void keyTyped(KeyEvent evt) { textKeyTyped(evt); } });
		 * textAreaComment.setEnabled(!readOnly);
		 *  } }
		 *  { JLabel labelRegion = new JLabel(); this.add(labelRegion, new
		 * GridBagConstraints(4, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.NONE, new Insets(5, 10, 0, 0), 0, 0));
		 * labelRegion.setText("Region:"); } { JLabel labelPortefolie = new
		 * JLabel(); this .add(labelPortefolie, new GridBagConstraints(4, 7, 1,
		 * 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new
		 * Insets(5, 10, 0, 0), 0, 0)); labelPortefolie.setText("Bokf.
		 * selskap:"); } { comboBoxPostCompany = new JComboBox();
		 * 
		 * if (currentAvdeling == null || currentAvdeling.getAvdnr() == null) {
		 * comboBoxPostCompany.setEnabled(false); } else {
		 * comboBoxPostCompany.setEnabled(true); } this.add(comboBoxPostCompany,
		 * new GridBagConstraints(5, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
		 * comboBoxPostCompany.setPreferredSize(new java.awt.Dimension(60, 20));
		 * comboBoxPostCompany.setEnabled(!readOnly);
		 *  } { JLabel labelCompany = new JLabel(); this.add(labelCompany, new
		 * GridBagConstraints(7, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.NONE, new Insets(5, 10, 0, 0), 0, 0));
		 * labelCompany.setText("Selskap:"); }
		 *  { JLabel labelChain = new JLabel(); this.add(labelChain, new
		 * GridBagConstraints(10, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 0, 0));
		 * labelChain.setText("Kjede:"); } { textFieldChain = new JTextField();
		 * this.add(textFieldChain, new GridBagConstraints(12, 1, 2, 1, 0.0,
		 * 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new
		 * Insets(0, 5, 0, 0), 0, 0)); textFieldChain.setEditable(false);
		 * textFieldChain.setMinimumSize(new java.awt.Dimension(130, 20));
		 * textFieldChain .setPreferredSize(new java.awt.Dimension(130, 20)); }
		 *  { JLabel labelCondition = new JLabel(); this .add(labelCondition,
		 * new GridBagConstraints(10, 2, 1, 1, 0.0, 0.0,
		 * GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 10,
		 * 0, 0), 0, 0)); labelCondition.setText("Tjenester:"); }
		 *  { tableServices = getTableServices(); // tableServices = new
		 * JXTable(); // tableServices.setColumnControlVisible(true); //
		 * tableServices.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); JScrollPane
		 * scrollPane = new JScrollPane(tableServices);
		 * scrollPane.setViewportView(tableServices); this.add(scrollPane, new
		 * GridBagConstraints(12, 2, 2, 4, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 0), 0, 0));
		 * 
		 * scrollPane.setMinimumSize(new java.awt.Dimension(130, 100));
		 * scrollPane.setPreferredSize(new java.awt.Dimension(130, 100)); }
		 *  { JLabel labelCreated = new JLabel(); this.add(labelCreated, new
		 * GridBagConstraints(7, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.NONE, new Insets(5, 10, 0, 0), 0, 0));
		 * labelCreated.setText("Opprettet:"); } { JLabel labelStatus = new
		 * JLabel(); this.add(labelStatus, new GridBagConstraints(7, 4, 1, 1,
		 * 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new
		 * Insets(5, 10, 0, 0), 0, 0)); labelStatus.setText("Status:"); } {
		 * 
		 * this.add(textFieldStatus, new GridBagConstraints(8, 4, 2, 1, 0.0,
		 * 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new
		 * Insets(5, 5, 0, 0), 0, 0)); textFieldStatus.setEditable(false); }
		 *  {
		 * 
		 * this.add(textFieldRegion, new GridBagConstraints(5, 5, 2, 1, 0.0,
		 * 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new
		 * Insets(5, 5, 0, 0), 0, 0)); textFieldRegion.setEditable(false); } {
		 * 
		 * this.add(textFieldCompany, new GridBagConstraints(8, 1, 1, 1, 0.0,
		 * 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new
		 * Insets(5, 5, 0, 0), 0, 0)); textFieldCompany.setEditable(false);
		 * textFieldCompany .setPreferredSize(new java.awt.Dimension(80, 20));
		 * textFieldCompany.setMinimumSize(new java.awt.Dimension(80, 20)); } {
		 * textFieldNrOfAdendum = new JTextField();
		 * this.add(textFieldNrOfAdendum, new GridBagConstraints(8, 7, 1, 1,
		 * 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new
		 * Insets(5, 5, 0, 0), 0, 0)); textFieldNrOfAdendum.setEditable(false);
		 * textFieldNrOfAdendum.setPreferredSize(new java.awt.Dimension( 50,
		 * 20)); textFieldNrOfAdendum.setMinimumSize(new java.awt.Dimension(50,
		 * 20)); } { JLabel labelNrOfAdendum = new JLabel(); this
		 * .add(labelNrOfAdendum, new GridBagConstraints(7, 7, 1, 1, 0.0, 0.0,
		 * GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 10,
		 * 0, 0), 0, 0)); labelNrOfAdendum.setText("Addendum:"); } { JLabel
		 * labelContractType = new JLabel(); this .add(labelContractType, new
		 * GridBagConstraints(7, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.NONE, new Insets(5, 10, 0, 0), 0, 0));
		 * labelContractType.setText("Kontrakttype:"); } {
		 * 
		 * this.add(textFieldContractType, new GridBagConstraints(8, 5, 1, 1,
		 * 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new
		 * Insets(5, 5, 0, 0), 0, 0)); textFieldContractType.setEditable(false);
		 * textFieldContractType.setPreferredSize(new java.awt.Dimension( 50,
		 * 20)); } { JLabel labelKid = new JLabel(); this.add(labelKid, new
		 * GridBagConstraints(4, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.NONE, new Insets(5, 10, 0, 0), 0, 0));
		 * labelKid.setText("KID:"); } {
		 * 
		 * this.add(textFieldKid, new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0,
		 * GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5,
		 * 5, 0, 0), 0, 0)); textFieldKid.setPreferredSize(new
		 * java.awt.Dimension(100, 20)); textFieldKid.setMinimumSize(new
		 * java.awt.Dimension(100, 20)); textFieldKid.addKeyListener(new
		 * KeyAdapter() { @Override public void keyTyped(KeyEvent evt) { if
		 * (currentAvdeling != null) { currentAvdeling.setModified(true); } }
		 * }); textFieldKid.addFocusListener(new FocusAdapter() { @Override
		 * public void focusLost(FocusEvent evt) { textFieldKidFocusLost(evt); }
		 * }); textFieldKid.setEditable(!readOnly);
		 *  } { JLabel labelOrgNr = new JLabel(); this.add(labelOrgNr, new
		 * GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		 * labelOrgNr.setText("Org. nr:"); } { textFieldOrgNr = new
		 * JTextField(); this.add(textFieldOrgNr, new GridBagConstraints(2, 4,
		 * 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
		 * textFieldOrgNr.setEditable(false);
		 * textFieldOrgNr.setPreferredSize(new java.awt.Dimension(80, 20)); } {
		 * JLabel labelFranchise = new JLabel(); this .add(labelFranchise, new
		 * GridBagConstraints(7, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.NONE, new Insets(5, 10, 0, 0), 0, 0));
		 * labelFranchise.setText("Avtaletype:"); } {
		 * 
		 * this.add(textFieldFranchise, new GridBagConstraints(8, 6, 1, 1, 0.0,
		 * 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new
		 * Insets(5, 5, 0, 0), 0, 0)); textFieldFranchise.setEditable(false); } {
		 * //dateChooserCreated = new JDateChooser();
		 * this.add(dateChooserCreated, new GridBagConstraints(8, 2, 2, 1, 0.0,
		 * 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5,
		 * 5, 0, 0), 0, 0));
		 * dateChooserCreated.getSpinner().setEnabled(!readOnly);
		 *  } { labelClosedDate = new JLabel(); this.add(labelClosedDate, new
		 * GridBagConstraints(9, 6, 4, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
		 * labelClosedDate .setPreferredSize(new java.awt.Dimension(100, 20)); } {
		 * JLabel labelSalesmanager = new JLabel(); this .add(labelSalesmanager,
		 * new GridBagConstraints(4, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.NONE, new Insets(5, 10, 0, 0), 0, 0));
		 * labelSalesmanager.setText("Salgssjef:"); } { textFieldSalesmanager =
		 * new JTextField(); this.add(textFieldSalesmanager, new
		 * GridBagConstraints(5, 6, 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
		 * textFieldSalesmanager.setEditable(false); }
		 *  { JLabel labelPib = new JLabel(); this.add(labelPib, new
		 * GridBagConstraints(7, 8, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.NONE, new Insets(5, 10, 0, 0), 0, 0));
		 * labelPib.setText("PIB:"); } {
		 * 
		 * this.add(checkBoxPib, new GridBagConstraints(8, 8, 1, 1, 0.0, 0.0,
		 * GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0,
		 * 0), 0, 0)); checkBoxPib.addMouseListener(new MouseAdapter() {
		 * @Override public void mouseClicked(MouseEvent evt) {
		 * checkBoxPibMouseClicked(evt); } });
		 * checkBoxPib.setEnabled(!readOnly);
		 *  }
		 *  { JLabel labelArchive = new JLabel(); this.add(labelArchive, new
		 * GridBagConstraints(8, 8, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
		 * GridBagConstraints.NONE, new Insets(5, 10, 0, 0), 0, 0));
		 * labelArchive.setText("Arkiv:"); } { textFieldArchive = new
		 * JTextField(); textFieldArchive.setPreferredSize(new Dimension(50,
		 * 20)); textFieldArchive.setMinimumSize(new Dimension(50, 20));
		 * textFieldArchive.addKeyListener(new KeyAdapter() { @Override public
		 * void keyTyped(KeyEvent evt) { textKeyTyped(evt); } });
		 * this.add(textFieldArchive, new GridBagConstraints(9, 8, 3, 1, 0.0,
		 * 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5,
		 * 5, 0, 0), 0, 0));
		 *  }
		 *  { JLabel labelMissing = new JLabel(); this.add(labelMissing, new
		 * GridBagConstraints(7, 9, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.NONE, new Insets(5, 10, 0, 0), 0, 0));
		 * labelMissing.setText("Mangler:"); } { JScrollPane scrollPaneMissing =
		 * new JScrollPane(); this.add(scrollPaneMissing, new
		 * GridBagConstraints(8, 9, 3, 2, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.BOTH, new Insets(5, 5, 0, 0), 0, 0)); {
		 * listMissing = new JList();
		 * scrollPaneMissing.setViewportView(listMissing); listMissing
		 * .setSelectionMode(ListSelectionModel.SINGLE_SELECTION); } } {
		 * buttonAddMissing = new JButton();
		 * buttonAddMissing.setToolTipText("Legg til mangel");
		 * this.add(buttonAddMissing, new GridBagConstraints(11, 10, 1, 1, 0.0,
		 * 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new
		 * Insets(0, 0, 0, 0), 0, 0)); buttonAddMissing.setText("...");
		 * buttonAddMissing .setPreferredSize(new java.awt.Dimension(30, 20));
		 * buttonAddMissing.setMinimumSize(new java.awt.Dimension(30, 20));
		 * buttonAddMissing.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent evt) {
		 * buttonAddMissingActionPerformed(evt); } });
		 * buttonAddMissing.setEnabled(!readOnly);
		 *  } { buttonDeleteMissing = new JButton();
		 * buttonDeleteMissing.setToolTipText("Fjern mangel");
		 * this.add(buttonDeleteMissing, new GridBagConstraints(11, 9, 1, 1,
		 * 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new
		 * Insets(5, 0, 0, 0), 0, 0));
		 * buttonDeleteMissing.setIcon(IconEnum.ICON_DELETE.getIcon());
		 * buttonDeleteMissing.setPreferredSize(new java.awt.Dimension(30, 20));
		 * buttonDeleteMissing.setMinimumSize(new java.awt.Dimension(30, 20));
		 * buttonDeleteMissing.addActionListener(new ActionListener() { public
		 * void actionPerformed(ActionEvent evt) {
		 * buttonDeleteMissingActionPerformed(evt); } });
		 * buttonDeleteMissing.setEnabled(!readOnly); } { JLabel labelStart =
		 * new JLabel(); this.add(labelStart, new GridBagConstraints(7, 3, 1, 1,
		 * 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new
		 * Insets(5, 10, 0, 0), 0, 0)); labelStart.setText("Oppstart:"); } {
		 * textFieldStart = new JTextField(); this.add(textFieldStart, new
		 * GridBagConstraints(8, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
		 * GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
		 * textFieldStart.setEditable(false); }
		 *  } catch (Exception e) { e.printStackTrace(); }
		 */
	}

	/**
	 * Knappen oppdater trykket. Henter informasjon fra RIK og oppdaterer GUI
	 * 
	 * @param evt
	 */
	/*
	 * void buttonUpdateDepActionPerformed(ActionEvent evt) { Rik2AvdV rik2AvdV =
	 * null;
	 * 
	 * if (currentAvdeling != null && currentAvdeling.getAvdnr() != null) {
	 * initData(currentAvdeling); } else if (textFieldDepId.getText().length() !=
	 * 0) { rik2AvdV = rik2AvdVDAO.getRik2AvdV(new Integer(textFieldDepId
	 * .getText()));
	 * 
	 * if (rik2AvdV == null) { GuiUtil.showErrorMsgFrame(this, "Avdeling finnes
	 * ikke", "Avdeling finnes ikke"); return; } currentAvdelingV = new
	 * AvdelingV(); currentAvdelingV.setAdr1(rik2AvdV.getAdr1());
	 * currentAvdelingV.setAdr2(rik2AvdV.getAdr2());
	 * currentAvdelingV.setAnsvarlig(rik2AvdV.getAnsvarlig());
	 * currentAvdelingV.setAvdelingNavn(rik2AvdV.getNavn());
	 * currentAvdelingV.setAvdnr(rik2AvdV.getAvdnr()); }
	 * initData(currentAvdeling); }
	 */

	/**
	 * Initierer data fra avdelingview
	 */
	/*
	 * private void initDataFromView() { if (currentAvdelingV != null) {
	 * textFieldDepName.setText(currentAvdelingV.getAvdelingNavn());
	 * textFieldLawName.setText(currentAvdelingV.getJuridiskNavn());
	 * textFieldAddress1.setText(currentAvdelingV.getAdr1());
	 * textFieldAddress2.setText(currentAvdelingV.getAdr2());
	 * textFieldPostalCode.setText(currentAvdelingV.getPostnr());
	 * textFieldPostalPlace.setText(currentAvdelingV.getPoststed());
	 * textFieldContact.setText(currentAvdelingV.getAnsvarlig());
	 * textFieldContractType.setText(currentAvdelingV.getKontraktType());
	 * textFieldFranchise.setText(currentAvdelingV.getAvtaletype());
	 * textFieldStatus.setText(currentAvdelingV.getStatus()); if
	 * (currentAvdelingV.getDtStart() != null) {
	 * textFieldStart.setText(GuiUtil.SIMPLE_DATE_FORMAT
	 * .format(currentAvdelingV.getDtStart())); }
	 * 
	 * if (currentAvdelingV.getNedlagt() != null) {
	 * labelClosedDate.setText(GuiUtil.SIMPLE_DATE_FORMAT
	 * .format(currentAvdelingV.getNedlagt())); }
	 * 
	 * textFieldChain.setText(currentAvdelingV.getKjede());
	 * textFieldRegion.setText(currentAvdelingV.getRegion());
	 * textFieldCompany.setText(currentAvdelingV.getSelskapRegnskap());
	 * selskapModel.setSelectedItem(currentAvdelingV.getBokfSelskap()); if
	 * (currentAvdelingV.getOrgNr() != null) {
	 * textFieldOrgNr.setText(currentAvdelingV.getOrgNr().toString()); }
	 * textFieldSalesmanager.setText(currentAvdelingV.getSalgssjef());
	 * 
	 * if (currentAvdeling == null || currentAvdeling.getFranchisetaker() ==
	 * null || currentAvdeling.getFranchisetaker().length() == 0) {
	 * textFieldFranchisetaker .setText(currentAvdelingV.getAnsvarlig()); } } }
	 */

	/**
	 * Laster data
	 * 
	 * @param avdeling
	 */
	/*
	 * private void initData(Avdeling avdeling) { if (!guiInitiated) { init();
	 * guiInitiated = true; } if (avdeling != null) { if (avdeling.getAvdnr() !=
	 * null) { avdelingDAO.loadLazy(avdeling, new LazyLoadAvdelingEnum[] {
	 * LazyLoadAvdelingEnum.LOAD_ADENDUM, LazyLoadAvdelingEnum.LOAD_MANGLER });
	 * textFieldDepId.setText(avdeling.getAvdnr().toString()); }
	 * 
	 * textAreaComment.setText(avdeling.getKommentar());
	 * textFieldKid.setText(avdeling.getKid());
	 * textFieldFranchisetaker.setText(avdeling.getFranchisetaker());
	 * 
	 * if (avdeling.getOpprettetDato() != null) {
	 * dateChooserCreated.setDate(avdeling.getOpprettetDato()); } if
	 * (avdeling.getAdendums() != null && avdeling.getAdendums().size() != 0) {
	 * 
	 * textFieldNrOfAdendum.setText(String.valueOf(avdeling
	 * .getAdendums().size())); } Set missing = avdeling.getAvdelingMangels();
	 * if (missing != null) { listModelMissing = new
	 * DefaultComboBoxModel(missing.toArray());
	 * 
	 * listMissing.setModel(listModelMissing); }
	 * 
	 * if (avdeling.getPib() != null && avdeling.getPib() == 1) {
	 * checkBoxPib.setSelected(true); } else { checkBoxPib.setSelected(false); }
	 * 
	 * if (avdeling.getArchiveInfo() != null) {
	 * textFieldArchive.setText(avdeling.getArchiveInfo()); }
	 * 
	 * initDataFromView(); }
	 *  }
	 */

	/**
	 * @param tableMouseListener
	 * @see no.ica.fraf.gui.department.FrafPanel#addTableMouseListener(no.ica.fraf.gui.department.DepartmentFrame.TableMouseListener)
	 */
	@Override
	public void addTableMouseListener(TableMouseListener tableMouseListener) {
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#addObject()
	 */
	@Override
	public void addObject() {
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#removeObject()
	 */
	@Override
	public void removeObject() {
	}

	/**
	 * Logger forandring
	 * 
	 * @param textFieldText
	 * @param avdelingText
	 * @param fieldName
	 * @return true dersom slettet
	 */
	private boolean checkAndLogChange(String textFieldText,
			String avdelingText, String fieldName) {
		boolean deleted = false;
		if (textFieldText.length() != 0) {
			if (avdelingText != null
					&& !avdelingText.equalsIgnoreCase(textFieldText)
					|| avdelingText == null) {

				AvdelingLoggUtil.logg(currentApplUser, currentAvdeling,
						"Endret " + fieldName + " fra " + avdelingText
								+ " til " + textFieldText);

			}
		} else {
			if (avdelingText != null
					&& !avdelingText.equalsIgnoreCase(textFieldText)) {
				AvdelingLoggUtil.logg(currentApplUser, currentAvdeling,
						"Fjernet " + fieldName + " som var " + avdelingText);
				deleted = true;
			}
		}
		return deleted;
	}

	/**
	 * Henter gjeldende avdeling
	 * 
	 * @return gjeldende avdeling
	 */
	public Avdeling getCurrentAvdeling() {
		Map<String, String[]> fieldChanges = departmentViewHandler
				.getFieldChanges();
		Set<String> fieldNames = fieldChanges.keySet();
		for (String fieldName : fieldNames) {
			String[] values = fieldChanges.get(fieldName);
			checkAndLogChange(values[1], values[0], fieldName);
		}
		departmentViewHandler.resetChanges();

		return currentAvdeling;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#reloadData()
	 */
	@Override
	public void reloadData() {
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#clearAdded()
	 */
	@Override
	public void clearAdded() {
		newUnsavedMissing.clear();
		deletetUnsavedMissing.clear();
		super.clearAdded();
	}

	/**
	 * Tastetrykk
	 * 
	 * @param evt
	 */
	void textKeyTyped(KeyEvent evt) {
		if (currentAvdeling != null) {
			currentAvdeling.setModified(true);
		}

	}

	/**
	 * PIB er forandret
	 * 
	 * @param evt
	 */
	void checkBoxPibMouseClicked(MouseEvent evt) {
		if (currentAvdeling != null) {
			currentAvdeling.setModified(true);
		}

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getTableDef()
	 */
	@Override
	protected ObjectTableDef getTableDef() {
		return null;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initTableWidth()
	 */
	@Override
	protected void initTableWidth() {
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getPanelName()
	 */
	@Override
	protected String getPanelName() {
		return null;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getHeading()
	 */
	@Override
	String getHeading() {

		return null;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getLoadClass()
	 */
	@Override
	Threadable getLoadClass() {
		return null;
	}

}
