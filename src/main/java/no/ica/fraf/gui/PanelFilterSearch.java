package no.ica.fraf.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import no.ica.fraf.gui.model.YesNoComboable;
import no.ica.fraf.gui.model.YesNoInteger;
import no.ica.fraf.model.AvdelingStatus;
import no.ica.fraf.model.AvdelingV;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Chain;
import no.ica.fraf.model.FornyelseType;
import no.ica.fraf.model.KontraktType;
import no.ica.fraf.model.Region;
import no.ica.fraf.model.Rik2KjedeV;
import no.ica.fraf.model.Rik2RegionV;
import no.ica.fraf.util.DataListUtil;
import no.ica.fraf.util.DataListUtilFactory;
import no.ica.fraf.util.ModelUtil;

import com.toedter.calendar.JDateChooser;

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
 * Panel som inneholder alle muligheter for filtrering i filteringsvinduet
 * 
 * @author abr99
 */
public class PanelFilterSearch extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JComboBox comboBoxRegion;

	/**
	 * 
	 */
	private JComboBox comboBoxPib;

	/**
	 * 
	 */
	private JComboBox comboBoxRenew;

	/**
	 * 
	 */
	private JComboBox comboBoxSalesmanager;

	/**
	 * 
	 */
	private JCheckBox checkBoxOverdue;

	/**
	 * 
	 */
	private JTextField textFieldLawName;

	/**
	 * 
	 */
	private JTextField textFieldContact;

	/**
	 * 
	 */
	private JTextField textFieldFranchiseOwner;

	/**
	 * 
	 */
	private JComboBox comboBoxContract;

	/**
	 * 
	 */
	private JComboBox comboBoxStatus;

	/**
	 * 
	 */
	private JDateChooser dateChooserChanged;

	/**
	 * 
	 */
	private JDateChooser dateChooserCreated;

	/**
	 * 
	 */
	private JDateChooser dateChooserContractTo;

	/**
	 * 
	 */
	private JDateChooser dateChooserContractFrom;

	/**
	 * 
	 */
	private JTextField textFieldCompany;

	/**
	 * 
	 */
	private JComboBox comboBoxPortefoly;

	/**
	 * 
	 */
	private JComboBox comboBoxChain;

	/**
	 * 
	 */
	private JTextField textFieldDepName;

	/**
	 * 
	 */
	private JTextField textFieldArchive;

	/**
	 * 
	 */
	JTextField textFieldDepId;

	/**
	 * 
	 */
	DepartmentSearchFrame departmentSearchFrame;

	/**
	 * @param parent
	 */
	public PanelFilterSearch(DepartmentSearchFrame parent) {
		super();
		departmentSearchFrame = parent;
		initGUI();
		initFilter();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				textFieldDepId.requestFocus();
			}
		});
	}

	/**
	 * Initierer GUI
	 */
	private void initGUI() {
		FrameKeyEventListener frameKeyEventListener = new FrameKeyEventListener();
		int width = 0;
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);

			{
				JPanel panelNoth = new JPanel();
				GridBagLayout panelNothLayout = new GridBagLayout();
				panelNoth.setLayout(panelNothLayout);
				this.add(panelNoth, BorderLayout.NORTH);
				panelNoth.setPreferredSize(new java.awt.Dimension(10, 40));
				{
					JLabel labelDepId = new JLabel();
					panelNoth.add(labelDepId, new GridBagConstraints(1, 0, 1,
							1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 10, 0, 0),
							0, 0));
					labelDepId.setText("Avdnr");
				}
				{
					textFieldDepId = new JTextField();
					panelNoth.add(textFieldDepId, new GridBagConstraints(1, 1,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					textFieldDepId
							.setMinimumSize(new java.awt.Dimension(65, 20));
					textFieldDepId.setPreferredSize(new java.awt.Dimension(65,
							20));
					textFieldDepId.setSize(65, 20);
					textFieldDepId.addKeyListener(frameKeyEventListener);
					width += 65;
				}
				{
					JLabel labelDepName = new JLabel();
					panelNoth.add(labelDepName, new GridBagConstraints(2, 0, 1,
							1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelDepName.setText("Avdelingsnavn");
				}
				{
					textFieldDepName = new JTextField();
					panelNoth.add(textFieldDepName, new GridBagConstraints(2,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					textFieldDepName.setMinimumSize(new java.awt.Dimension(200,
							20));
					textFieldDepName.setPreferredSize(new java.awt.Dimension(
							200, 20));
					textFieldDepName.addKeyListener(frameKeyEventListener);
					width += 200;
				}

				{
					JLabel labelContractFrom = new JLabel();
					panelNoth.add(labelContractFrom, new GridBagConstraints(4,
							0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelContractFrom.setText("Kontrakt fra");
				}
				{
					JLabel labelContratcTo = new JLabel();
					panelNoth.add(labelContratcTo, new GridBagConstraints(5, 0,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelContratcTo.setText("Kontrakt til");
				}
				{
					JLabel labelCreated = new JLabel();
					panelNoth.add(labelCreated, new GridBagConstraints(6, 0, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelCreated.setText("Opprettet");
				}
				{
					JLabel labelChanged = new JLabel();
					panelNoth.add(labelChanged, new GridBagConstraints(7, 0, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelChanged.setText("Endret");
				}
				{
					JLabel labelStatus = new JLabel();
					panelNoth.add(labelStatus, new GridBagConstraints(8, 0, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelStatus.setText("Status");
				}
				{
					JLabel labelContractType = new JLabel();
					panelNoth.add(labelContractType, new GridBagConstraints(9,
							0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelContractType.setText("Kontrakt");
				}
				{
					JLabel labelRegion = new JLabel();
					panelNoth.add(labelRegion, new GridBagConstraints(10, 0, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelRegion.setText("Region");
				}
				{
					JLabel labelChain = new JLabel();
					panelNoth.add(labelChain, new GridBagConstraints(11, 0, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelChain.setText("Kjede");
				}
				{
					JLabel labelPortefoly = new JLabel();
					panelNoth.add(labelPortefoly, new GridBagConstraints(12, 0,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelPortefoly.setText("Bokf.selskap");
				}
				{
					JLabel labelCompany = new JLabel();
					panelNoth.add(labelCompany, new GridBagConstraints(13, 0,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelCompany.setText("Selskap");
				}
				{
					comboBoxRegion = new JComboBox();
					panelNoth.add(comboBoxRegion, new GridBagConstraints(10, 1,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					comboBoxRegion.setPreferredSize(new java.awt.Dimension(170,
							20));
					comboBoxRegion.setMinimumSize(new java.awt.Dimension(170,
							25));
					comboBoxRegion.addKeyListener(frameKeyEventListener);
					width += 170;
				}
				{
					comboBoxChain = new JComboBox();
					panelNoth.add(comboBoxChain, new GridBagConstraints(11, 1,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					comboBoxChain.setPreferredSize(new java.awt.Dimension(140,
							20));
					comboBoxChain
							.setMinimumSize(new java.awt.Dimension(140, 25));
					comboBoxChain.addKeyListener(frameKeyEventListener);
					width += 140;
				}
				{
					comboBoxPortefoly = new JComboBox();
					panelNoth.add(comboBoxPortefoly, new GridBagConstraints(12,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					comboBoxPortefoly.setPreferredSize(new java.awt.Dimension(
							90, 20));
					comboBoxPortefoly.setMinimumSize(new java.awt.Dimension(90,
							25));
					width += 90;
				}
				{
					textFieldCompany = new JTextField();
					panelNoth.add(textFieldCompany, new GridBagConstraints(13,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					textFieldCompany.setPreferredSize(new java.awt.Dimension(
							70, 20));
					textFieldCompany.setMinimumSize(new java.awt.Dimension(70,
							25));
					width += 70;
				}
				{
					dateChooserContractFrom = new JDateChooser(true);
					panelNoth.add(dateChooserContractFrom,
							new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
									GridBagConstraints.CENTER,
									GridBagConstraints.NONE, new Insets(0, 0,
											0, 0), 0, 0));
					dateChooserContractFrom
							.setMinimumSize(new java.awt.Dimension(125, 20));
					dateChooserContractFrom
							.setPreferredSize(new java.awt.Dimension(125, 20));
					dateChooserContractFrom.setSize(125, 20);
					width += 125;
				}
				{
					dateChooserContractTo = new JDateChooser(true);
					panelNoth.add(dateChooserContractTo,
							new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0,
									GridBagConstraints.CENTER,
									GridBagConstraints.NONE, new Insets(0, 0,
											0, 0), 0, 0));
					dateChooserContractTo
							.setMinimumSize(new java.awt.Dimension(125, 20));
					dateChooserContractTo
							.setPreferredSize(new java.awt.Dimension(125, 20));
					dateChooserContractTo.setSize(125, 20);
					width += 125;
				}
				{
					dateChooserCreated = new JDateChooser(true);
					panelNoth.add(dateChooserCreated, new GridBagConstraints(6,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					dateChooserCreated.setMinimumSize(new java.awt.Dimension(
							125, 20));
					dateChooserCreated.setPreferredSize(new java.awt.Dimension(
							125, 20));
					dateChooserCreated.setSize(125, 20);
					width += 125;
				}
				{
					dateChooserChanged = new JDateChooser(true);
					panelNoth.add(dateChooserChanged, new GridBagConstraints(7,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					dateChooserChanged.setMinimumSize(new java.awt.Dimension(
							125, 20));
					dateChooserChanged.setPreferredSize(new java.awt.Dimension(
							125, 20));
					dateChooserChanged.setSize(125, 20);
					width += 125;
				}
				{
					comboBoxStatus = new JComboBox();
					panelNoth.add(comboBoxStatus, new GridBagConstraints(8, 1,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					comboBoxStatus.setPreferredSize(new java.awt.Dimension(80,
							20));
					comboBoxStatus
							.setMinimumSize(new java.awt.Dimension(80, 25));
					width += 80;
				}
				{
					this.setMinimumSize(new java.awt.Dimension(1676, 10));
					comboBoxContract = new JComboBox();
					panelNoth.add(comboBoxContract, new GridBagConstraints(9,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					comboBoxContract.setPreferredSize(new java.awt.Dimension(
							145, 20));
					comboBoxContract.setMinimumSize(new java.awt.Dimension(145,
							25));
					width += 145;
				}
				{
					JLabel labelLawName = new JLabel();
					panelNoth.add(labelLawName, new GridBagConstraints(3, 0, 1,
							1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelLawName.setText("Juridisk navn");
				}
				{
					textFieldLawName = new JTextField();
					panelNoth.add(textFieldLawName, new GridBagConstraints(3,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					textFieldLawName.setPreferredSize(new java.awt.Dimension(
							200, 20));
					textFieldLawName.setMinimumSize(new java.awt.Dimension(200,
							20));
					textFieldLawName.addKeyListener(frameKeyEventListener);
					width += 200;
				}
				{
					JLabel labelInfo = new JLabel();
					panelNoth.add(labelInfo, new GridBagConstraints(0, 0, 1, 1,
							0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelInfo.setText("I");
				}
				{
					checkBoxOverdue = new JCheckBox();
					panelNoth.add(checkBoxOverdue, new GridBagConstraints(0, 1,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					width += 20;
				}
				{
					JLabel labelSalesmanager = new JLabel();
					panelNoth.add(labelSalesmanager, new GridBagConstraints(14,
							0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelSalesmanager.setText("Salgssjef");
				}
				{
					comboBoxSalesmanager = new JComboBox();
					panelNoth.add(comboBoxSalesmanager, new GridBagConstraints(
							14, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					comboBoxSalesmanager
							.setPreferredSize(new java.awt.Dimension(120, 20));
					comboBoxSalesmanager.setMinimumSize(new java.awt.Dimension(
							120, 20));
					width += 120;
				}
				{
					JLabel labelRenew = new JLabel();
					panelNoth.add(labelRenew, new GridBagConstraints(15, 0, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelRenew.setText("Fornyelse");
				}
				{
					comboBoxRenew = new JComboBox();
					panelNoth.add(comboBoxRenew, new GridBagConstraints(15, 1,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					comboBoxRenew.setPreferredSize(new java.awt.Dimension(150,
							20));
					comboBoxRenew
							.setMinimumSize(new java.awt.Dimension(150, 20));
					width += 150;
				}
				{
					JLabel labelPib = new JLabel();
					panelNoth.add(labelPib, new GridBagConstraints(16, 0, 1, 1,
							0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelPib.setText("PIB");
				}
				{
					comboBoxPib = new JComboBox();
					panelNoth.add(comboBoxPib, new GridBagConstraints(16, 1, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					comboBoxPib
							.setPreferredSize(new java.awt.Dimension(70, 20));
					comboBoxPib.setMinimumSize(new java.awt.Dimension(70, 20));
					width += 70;
				}

				{
					JLabel labelKontaktperson = new JLabel();
					panelNoth.add(labelKontaktperson, new GridBagConstraints(
							17, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelKontaktperson.setText("Kontaktperson");
				}
				{
					textFieldContact = new JTextField();
					panelNoth.add(textFieldContact, new GridBagConstraints(17,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					textFieldContact.setPreferredSize(new java.awt.Dimension(
							150, 20));
					textFieldContact.setMinimumSize(new java.awt.Dimension(150,
							20));
					textFieldContact.addKeyListener(frameKeyEventListener);
					width += 150;
				}
				{
					JLabel labelFranchiseOwner = new JLabel();
					panelNoth.add(labelFranchiseOwner, new GridBagConstraints(
							18, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelFranchiseOwner.setText("Franchisetaker");
				}
				{
					textFieldFranchiseOwner = new JTextField();
					panelNoth.add(textFieldFranchiseOwner,
							new GridBagConstraints(18, 1, 1, 1, 0.0, 0.0,
									GridBagConstraints.WEST,
									GridBagConstraints.NONE, new Insets(0, 0,
											0, 0), 0, 0));
					textFieldFranchiseOwner
							.setPreferredSize(new java.awt.Dimension(150, 20));
					textFieldFranchiseOwner
							.setMinimumSize(new java.awt.Dimension(150, 20));
					textFieldFranchiseOwner
							.addKeyListener(frameKeyEventListener);
					width += 150;
				}

				{
					JLabel labelArchive = new JLabel();
					panelNoth.add(labelArchive, new GridBagConstraints(19, 0,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelArchive.setText("Arkiv");
				}
				{
					textFieldArchive = new JTextField();
					panelNoth.add(textFieldArchive, new GridBagConstraints(19,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					textFieldArchive.setPreferredSize(new java.awt.Dimension(
							70, 20));
					textFieldArchive.setMinimumSize(new java.awt.Dimension(70,
							20));
					textFieldArchive.addKeyListener(frameKeyEventListener);
					width += 70;
				}

			}
			{
				this.setPreferredSize(new java.awt.Dimension(width, 4));
				this.setSize(new java.awt.Dimension(width, 40));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Klasse som håndterer tastetrykk i dialog
	 * 
	 * @author abr99
	 */
	public class FrameKeyEventListener implements KeyListener {

		/**
		 * Ved trykk på ENTER blir filtrering kjørt
		 * 
		 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
		 */
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				departmentSearchFrame.doFilter();
			}

		}

		/**
		 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
		 */
		public void keyReleased(KeyEvent e) {
		}

		/**
		 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
		 */
		public void keyTyped(KeyEvent e) {
		}

	}

	/**
	 * Henter filter som er definert
	 * 
	 * @return filter
	 */
	public AvdelingV getFilter() {
		AvdelingV avdelingVFilter = new AvdelingV();

		if (checkBoxOverdue.isSelected()) {
			avdelingVFilter.setKontraktUtgaar(new Integer(1));
		} else {
			avdelingVFilter.setKontraktUtgaar(null);
		}

		if (textFieldDepId.getText().length() != 0) {
			try {
				avdelingVFilter.setAvdnr(new Integer(textFieldDepId.getText()));
			} catch (NumberFormatException e) {
				avdelingVFilter.setAvdnr(0);
			}
		}

		if (textFieldDepName.getText().length() != 0) {
			avdelingVFilter.setAvdelingNavn(textFieldDepName.getText());
		}

		if (textFieldLawName.getText().length() != 0) {
			avdelingVFilter.setJuridiskNavn(textFieldLawName.getText());
		}

		if (dateChooserContractFrom.getDate() != null) {
			avdelingVFilter.setKontraktFraDato(dateChooserContractFrom
					.getDate());
		}

		if (dateChooserContractTo.getDate() != null) {
			avdelingVFilter.setKontraktTilDato(dateChooserContractTo.getDate());
		}

		if (dateChooserCreated.getDate() != null) {
			avdelingVFilter.setOpprettetDato(dateChooserCreated.getDate());
		}

		if (dateChooserChanged.getDate() != null) {
			avdelingVFilter.setEndretDato(dateChooserChanged.getDate());
		}

		if (comboBoxStatus.getSelectedIndex() != 0) {
			AvdelingStatus avdelingStatus = (AvdelingStatus) comboBoxStatus
					.getSelectedItem();

			if (avdelingStatus != null) {
				avdelingVFilter.setStatus(avdelingStatus.getStatusTxt());
			}
		}

		if (comboBoxContract.getSelectedIndex() != 0) {
			KontraktType kontraktType = (KontraktType) comboBoxContract
					.getSelectedItem();

			if (kontraktType != null) {
				avdelingVFilter.setKontraktType(kontraktType
						.getKontraktTypeKode());
			}
		}

		if (comboBoxRegion.getSelectedIndex() != 0) {
			Region region = (Region) comboBoxRegion
					.getSelectedItem();

			if (region != null) {
				avdelingVFilter.setRegion(region.getNavn());
			}
		}

		if (comboBoxChain.getSelectedIndex() != 0) {
			Chain chain = (Chain) comboBoxChain
					.getSelectedItem();

			if (chain != null) {
				avdelingVFilter.setKjede(chain.getNavn());
			}
		}

		if (comboBoxPortefoly.getSelectedIndex() != 0) {
			BokfSelskap bokfSelskap = (BokfSelskap) comboBoxPortefoly
					.getSelectedItem();

			if (bokfSelskap != null) {
				avdelingVFilter.setBokfSelskap(bokfSelskap.getSelskapNavn());
			}
		}

		if (textFieldCompany.getText().length() != 0) {
			avdelingVFilter.setSelskapRegnskap(textFieldCompany.getText());
		}

		if (comboBoxSalesmanager.getSelectedIndex() != 0) {
			String salgssjef = (String) comboBoxSalesmanager.getSelectedItem();

			if (salgssjef != null) {
				avdelingVFilter.setSalgssjef(salgssjef);
			}
		}

		if (comboBoxRenew.getSelectedIndex() != 0) {
			FornyelseType fornyelseType = (FornyelseType) comboBoxRenew
					.getSelectedItem();

			if (fornyelseType != null) {
				avdelingVFilter.setFornyelseTypeTxt(fornyelseType
						.getFornyelseTypeTxt());
			}
		}

		YesNoInteger yesNoInteger = (YesNoInteger) comboBoxPib
				.getSelectedItem();
		if (yesNoInteger.integerValue != -1) {
			avdelingVFilter.setPib(yesNoInteger.integerValue);
		}

		if (textFieldContact.getText().length() > 0) {
			avdelingVFilter.setAnsvarlig(textFieldContact.getText());
		}

		if (textFieldFranchiseOwner.getText().length() > 0) {
			avdelingVFilter
					.setFranchisetaker(textFieldFranchiseOwner.getText());
		}

		if (textFieldArchive.getText().length() > 0) {
			avdelingVFilter.setArchiveInfo(textFieldArchive.getText());
		}
		return avdelingVFilter;
	}

	/**
	 * Initierer tabell som vise avdelinger
	 */
	private void initFilter() {
		DataListUtil dataListUtil = DataListUtilFactory.getInstance(new ModelUtil());
		List<Region> regions = dataListUtil.getRegioner();
		List<Chain> kjeder = dataListUtil.getKjeder();
		List<BokfSelskap> selskap = dataListUtil.getBokfSelskaper();
		List<AvdelingStatus> status = dataListUtil.getStatuser();
		List<KontraktType> kontraktType = dataListUtil.getKontrakttyper();
		List<String> managers = dataListUtil.getDistriktssjefer();
		List<FornyelseType> renewables = dataListUtil.getFornyelseTyper();
		List<YesNoInteger> yesNoList = new YesNoComboable(true)
				.getComboValues(null);

		if (regions == null) {
			regions = new ArrayList<Region>();
		}

		if (kjeder == null) {
			kjeder = new ArrayList<Chain>();
		}

		if (selskap == null) {
			selskap = new ArrayList<BokfSelskap>();
		}

		if (status == null) {
			status = new ArrayList<AvdelingStatus>();
		}

		if (kontraktType == null) {
			kontraktType = new ArrayList<KontraktType>();
		}
		if (managers == null) {
			managers = new ArrayList<String>();
		}
		if (renewables == null) {
			renewables = new ArrayList<FornyelseType>();
		}
		regions.add(0, new Rik2RegionV("alle",null,null));

		kjeder.add(0,new Rik2KjedeV(null,"alle",null));
		selskap.add(0,new BokfSelskap(null,null,null,null,null,"alle",null,null,null,null,null,null,null,null,null,null,null,null,null,null));
		status.add(0, new AvdelingStatus(null,"alle"));
		kontraktType.add(0,new KontraktType(null,null,"alle",null,null));
		managers.add(0, "alle");
		renewables.add(0, new FornyelseType(null,null,null,"alle"));

		ComboBoxModel regionModel = new DefaultComboBoxModel(regions.toArray());
		//ComboBoxModel regionModel = new DefaultComboBoxModel(kjeder.toArray());

		ComboBoxModel kjedeModel = new DefaultComboBoxModel(kjeder.toArray());

		ComboBoxModel selskapModel = new DefaultComboBoxModel(selskap.toArray());

		ComboBoxModel statusModel = new DefaultComboBoxModel(status.toArray());

		ComboBoxModel kontraktTypeModel = new DefaultComboBoxModel(kontraktType
				.toArray());
		ComboBoxModel managerModel = new DefaultComboBoxModel(managers
				.toArray());
		ComboBoxModel renewableModel = new DefaultComboBoxModel(renewables
				.toArray());
		ComboBoxModel yesNoModel = new DefaultComboBoxModel(yesNoList.toArray());

		comboBoxRegion.setModel(regionModel);
		comboBoxChain.setModel(kjedeModel);
		comboBoxPortefoly.setModel(selskapModel);
		comboBoxStatus.setModel(statusModel);
		comboBoxContract.setModel(kontraktTypeModel);
		comboBoxSalesmanager.setModel(managerModel);
		comboBoxRenew.setModel(renewableModel);
		comboBoxPib.setModel(yesNoModel);
	}
	
	

	/**
	 * Fjerner filter
	 */
	public void clearFilter() {
		textFieldDepId.setText("");
		textFieldDepName.setText("");
		textFieldLawName.setText("");
		dateChooserChanged.setDate(null);
		dateChooserContractFrom.setDate(null);
		dateChooserContractTo.setDate(null);
		dateChooserCreated.setDate(null);
		comboBoxChain.setSelectedIndex(0);
		textFieldCompany.setText("");
		comboBoxContract.setSelectedIndex(0);
		comboBoxPortefoly.setSelectedIndex(0);
		comboBoxRegion.setSelectedIndex(0);
		comboBoxStatus.setSelectedIndex(0);
		checkBoxOverdue.setSelected(false);
		comboBoxRenew.setSelectedIndex(0);
		comboBoxPib.setSelectedIndex(0);
		textFieldContact.setText("");
		textFieldFranchiseOwner.setText("");

	}

}
