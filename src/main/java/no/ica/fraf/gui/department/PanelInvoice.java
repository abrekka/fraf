package no.ica.fraf.gui.department;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.pkg.FranchisePkgDAO;
import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.enums.LazyLoadAvdelingEnum;
import no.ica.fraf.gui.DateChooserDialog;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.invoice.InvoiceFrame;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.YesNoComboable;
import no.ica.fraf.gui.model.YesNoInteger;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.service.EflowUsersVManager;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

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
 * Panel for visning av fakturaer
 * 
 * @author abr99
 * 
 */
public final class PanelInvoice extends FrafPanel<Faktura> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	JButton buttonCredit;

	/**
	 * 
	 */
	JButton buttonRegenerate;

	/**
	 * 
	 */
	JButton buttonShowInvoice;

	/**
	 * DAO for databasepakke FRANCHISE_PKG
	 */
	static FranchisePkgDAO franchisePkgDAO = (FranchisePkgDAO) ModelUtil
			.getBean("franchisePkgDAO");

	/**
	 * Navn på panel som brukes til popupmeny
	 */
	public static final String NAME_INVOICE = "INVOICE";

	/**
	 * Kolonnenavn
	 */
	private static final String[] COLUMN_NAMES = { "Xml", "Fakturanr",
			"Gruppe", "Fakturatittel", "Fakturadato", "Forfallsdato",
			"Fakturagrunnlag", "Fakturabeløp", "År", "Fra periode",
			"Til periode", "Opprettet", "Reversert", "Slettet",
			"Reversert faktura" };

	/**
	 * Kolonnemetoder
	 */
	private static final String[] METHODS = { "XmlGenerertDato", "FakturaNr",
			"BetingelseGruppe", "FakturaTittel", "FakturaDato", "ForfallDato",
			"GrunnlagBelop", "Belop", "Aar", "FraPeriode", "TilPeriode",
			"OpprettetDato", "Reversert", "Slettet", "ReversertFakturaId" };

	/**
	 * Klassetyper for klonner
	 */
	private static final Class[] PARAMS = { Date.class, Integer.class,
			BetingelseGruppe.class, String.class, Date.class, Date.class,
			BigDecimal.class, BigDecimal.class, BigDecimal.class,
			BigDecimal.class, BigDecimal.class, Date.class, YesNoInteger.class,
			YesNoInteger.class, Integer.class };

	/**
	 * Kolonneformat
	 */
	private static final ColumnFormatEnum[] FORMAT_COLUMNS_GUI = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.CURRENCY, ColumnFormatEnum.CURRENCY,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE, ColumnFormatEnum.NONE };

	/**
	 * Kolonneformat for excel
	 */
	private static final ColumnFormatEnum[] FORMAT_COLUMNS_EXCEL = new ColumnFormatEnum[] {
			ColumnFormatEnum.DATE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.DATE, ColumnFormatEnum.DATE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.DATE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE, ColumnFormatEnum.NONE };

	/**
	 * 
	 */
	//private EflowUsersVManager eflowUsersVManager;

	/**
	 * Konstruktør
	 * 
	 * @param aInternalFrame
	 * @param avdeling
	 * @param applUser
	 * @param isReadOnly
	 * @param aEflowUsersVManager
	 */
	public PanelInvoice(DepartmentFrame aInternalFrame, Avdeling avdeling,
			ApplUser applUser, boolean isReadOnly
			//,EflowUsersVManager aEflowUsersVManager
			) {
		super(aInternalFrame, avdeling, applUser, isReadOnly);
		//eflowUsersVManager = aEflowUsersVManager;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#init()
	 */
	@Override
	protected void init() {
		super.init();

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
		super.initGUI();
		try {
			{
				JPanel panelInvoiceButtons = new JPanel();
				this.add(panelInvoiceButtons, BorderLayout.SOUTH);
				FlowLayout jPanel1Layout1 = new FlowLayout();
				jPanel1Layout1.setAlignment(FlowLayout.LEFT);
				panelInvoiceButtons.setPreferredSize(new java.awt.Dimension(10,
						40));
				panelInvoiceButtons.setLayout(jPanel1Layout1);
				{
					buttonShowInvoice = new JButton();
					buttonShowInvoice.setEnabled(false);
					panelInvoiceButtons.add(buttonShowInvoice);
					buttonShowInvoice.setText("Vis faktura");
					buttonShowInvoice.setPreferredSize(new java.awt.Dimension(
							120, 25));
					buttonShowInvoice.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonShowInvoiceActionPerformed(evt);
						}
					});
				}
				{
					buttonCredit = new JButton();
					buttonCredit.setEnabled(false);
					panelInvoiceButtons.add(buttonCredit);
					buttonCredit.setText("Lag kredittnota");
					buttonCredit.setPreferredSize(new java.awt.Dimension(120,
							25));
					buttonCredit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonCreditActionPerformed(evt);
						}
					});

				}
				{
					buttonRegenerate = new JButton();
					buttonRegenerate.setEnabled(false);
					panelInvoiceButtons.add(buttonRegenerate);
					buttonRegenerate.setText("Regenerer");
					buttonRegenerate.setPreferredSize(new java.awt.Dimension(
							120, 25));
					buttonRegenerate.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonRegenerateActionPerformed(evt);
						}
					});

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 * Klikk med mus i tabell. Enabler tabell.
	 * 
	 * @param evt
	 */
	@Override
	protected void tableDataMouseClicked(MouseEvent evt) {
		if (evt.getClickCount() == 1) {
			if (!readOnly) {
				buttonCredit.setEnabled(true);
				buttonRegenerate.setEnabled(true);

			}
			buttonShowInvoice.setEnabled(true);
		} else if (evt.getClickCount() == 2) {
			GuiUtil.setWaitCursor(departmentFrame);
			showInvoice();
			GuiUtil.setDefaultCursor(departmentFrame);
		}
	}

	/**
	 * Faktura skal vises
	 * 
	 * @param evt
	 */
	void buttonShowInvoiceActionPerformed(ActionEvent evt) {
		showInvoice();
	}

	/**
	 * Viser faktura
	 */
	private void showInvoice() {
		if (tableData.getSelectedRow() < 0) {
			GuiUtil.showErrorMsgFrame(this, "Feil", "det må velges en faktura");
		}

		Faktura faktura = (Faktura) objectTableModel.getObjectAtIndex(tableData
				.getSelectedRow());

		InvoiceFrame invoiceFrame = new InvoiceFrame(faktura, currentApplUser);

		invoiceFrame.setVisible(true);
		FrafMain.getInstance().getContentPane().add(invoiceFrame);
		invoiceFrame.setLocation(GuiUtil.getCenter(FrafMain.getInstance()
				.getSize(), invoiceFrame.getSize()));
		FrafMain.getInstance().addInternalFrame(invoiceFrame);
		try {
			invoiceFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Skal lage kredittnota
	 * 
	 * @param evt
	 */
	void buttonCreditActionPerformed(ActionEvent evt) {
		GuiUtil.runInThread(departmentFrame, "Kredittnota",
				"Genererer kredittnota...", new Credit(), null);
	}

	/**
	 * Regenerer faktura
	 * 
	 * @param evt
	 */
	void buttonRegenerateActionPerformed(ActionEvent evt) {
		GuiUtil.runInThread(departmentFrame, "Regenerer",
				"Regenerer faktura...", new Regenerate(), null);
	}

	/**
	 * Klasse som håndterer laging av kredittnota
	 * 
	 * @author abr99
	 * 
	 */
	private class Credit implements Threadable {

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
		 */
		public void enableComponents(boolean enable) {
			buttonCredit.setEnabled(enable);
			buttonShowInvoice.setEnabled(enable);
			buttonRegenerate.setEnabled(enable);
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
		 *      javax.swing.JLabel)
		 */
		public Object doWork(Object[] params, JLabel labelInfo) {
			departmentFrame.enableFrameComponents(false);
			DateChooserDialog dateChooserDialog = new DateChooserDialog(
					FrafMain.getInstance(), "Datoer", 2, new String[] {
							"Fakturadato", "Forfallsdato" }, false, null);

			dateChooserDialog.setVisible(true);
			String errorString = null;

			if (dateChooserDialog.isOk()) {

				try {
					Faktura faktura = (Faktura) objectTableModel
							.getObjectAtIndex(tableData.getSelectedRow());
					Date invoiceDate = dateChooserDialog.getFromDate();
					Date dueDate = dateChooserDialog.getToDate();

					franchisePkgDAO.lagKredittnota(currentApplUser.getUserId(),
							faktura.getFakturaId(), invoiceDate, dueDate);
					loadData();

				} catch (FrafException e) {
					errorString = GuiUtil.getUserExceptionMsg(e);
					e.printStackTrace();
				}
			}

			return errorString;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null) {
				GuiUtil.showErrorMsgFrame(departmentFrame, "Feil", object
						.toString());
			}
			departmentFrame.enableFrameComponents(true);
		}

	}

	/**
	 * Regenerer faktura
	 * 
	 * @author abr99
	 * 
	 */
	private class Regenerate implements Threadable {

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
			departmentFrame.enableFrameComponents(false);
			DateChooserDialog dateChooserDialog = new DateChooserDialog(
					FrafMain.getInstance(), "Datoer", 2, new String[] {
							"Fakturadato", "Forfallsdato" }, false, null);

			dateChooserDialog.setVisible(true);
			String errorString = null;

			if (dateChooserDialog.isOk()) {

				try {
					Faktura faktura = (Faktura) objectTableModel
							.getObjectAtIndex(tableData.getSelectedRow());
					if (faktura.getFakturaTittel().indexOf("KREDITTNOTA") < 0) {
						errorString = "Kan ikke regenerer faktura som ikke er en kredittnota";
					} else if (!faktura.getFraPeriode().equals(
							faktura.getTilPeriode())) {
						errorString = "Kan ikke regenerer faktura som ikke er for en periode";
					} else {
						Date invoiceDate = dateChooserDialog.getFromDate();
						Date dueDate = dateChooserDialog.getToDate();

						franchisePkgDAO.regenerer(currentApplUser.getUserId(),
								faktura.getFakturaId(), invoiceDate, dueDate);
						loadData();
					}

				} catch (FrafException e) {
					errorString = GuiUtil.getUserExceptionMsg(e);
					e.printStackTrace();
				}
			}

			return errorString;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null) {
				GuiUtil.showErrorMsgFrame(departmentFrame, "Feil", object
						.toString());
			}
			departmentFrame.enableFrameComponents(true);
		}

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getNumberCols()
	 */
	@Override
	protected Vector<Integer> getNumberCols() {
		Vector<Integer> tmpVector = new Vector<Integer>();
		tmpVector.add(new Integer(5));
		tmpVector.add(new Integer(6));
		return tmpVector;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getExcelTableModel()
	 */
	@Override
	protected TableModel getExcelTableModel() {
		ObjectTableDef invoiceTableDef = new ObjectTableDef(COLUMN_NAMES,
				METHODS, PARAMS, FORMAT_COLUMNS_EXCEL);

		ObjectTableModel<Faktura> invoiceTableModelExcel = new ObjectTableModel<Faktura>(
				invoiceTableDef);
		invoiceTableModelExcel.setData(objectTableModel.getData());
		return invoiceTableModelExcel;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#reloadData()
	 */
	@Override
	public void reloadData() {
		loadData();

	}

	/**
	 * Klasse som håndterer lasting av fakturaer
	 * 
	 * @author abr99
	 * 
	 */
	private class LoadInvoice implements Threadable {

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
			if (currentAvdeling != null) {
				avdelingDAO
						.loadLazy(
								currentAvdeling,
								new LazyLoadAvdelingEnum[] { LazyLoadAvdelingEnum.LOAD_INVOICE });
				Set<Faktura> invoices = currentAvdeling.getFakturas();
				objectTableModel.setData(invoices);
				objectTableModel.setEditable(false);

				/*
				 * if (invoices == null || invoices.size() == 0) {
				 * buttonCredit.setEnabled(false);
				 * buttonShowInvoice.setEnabled(false);
				 * buttonRegenerate.setEnabled(false); }
				 */
			}
			return Boolean.TRUE;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			buttonCredit.setEnabled(false);
			buttonShowInvoice.setEnabled(false);
			buttonRegenerate.setEnabled(false);
		}

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getTableDef()
	 */
	@Override
	protected ObjectTableDef getTableDef() {
		return new ObjectTableDef(COLUMN_NAMES, METHODS, PARAMS,
				FORMAT_COLUMNS_GUI);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initTableWidth()
	 */
	@Override
	protected void initTableWidth() {
		// Xml
		TableColumn col = tableData.getColumnModel().getColumn(0);
		col.setPreferredWidth(70);
		// fakturanr
		col = tableData.getColumnModel().getColumn(1);
		col.setPreferredWidth(70);

		// betingelsegruppe
		col = tableData.getColumnModel().getColumn(2);
		col.setPreferredWidth(100);

		// fakturabeskrivelse
		col = tableData.getColumnModel().getColumn(3);
		col.setPreferredWidth(140);

		// Fakturadato
		col = tableData.getColumnModel().getColumn(4);
		col.setPreferredWidth(80);

		// forfallsdato
		col = tableData.getColumnModel().getColumn(5);
		col.setPreferredWidth(80);

		// fakturagrunnlag
		col = tableData.getColumnModel().getColumn(6);
		col.setPreferredWidth(110);

		// fakturabeløp
		col = tableData.getColumnModel().getColumn(7);
		col.setPreferredWidth(110);

		// År
		col = tableData.getColumnModel().getColumn(8);
		col.setPreferredWidth(50);

		// Fra periode
		col = tableData.getColumnModel().getColumn(9);
		col.setPreferredWidth(70);

		// Til periode
		col = tableData.getColumnModel().getColumn(10);
		col.setPreferredWidth(70);

		// Opprettet
		col = tableData.getColumnModel().getColumn(11);
		col.setPreferredWidth(80);

		// Reversert
		col = tableData.getColumnModel().getColumn(12);
		col.setPreferredWidth(60);
		col.setCellEditor(GuiUtil.createComboCellEditor(new YesNoComboable(),
				null));

		// Slettet
		col = tableData.getColumnModel().getColumn(13);
		col.setPreferredWidth(60);
		col.setCellEditor(GuiUtil.createComboCellEditor(new YesNoComboable(),
				null));

		// Reversert faktura
		col = tableData.getColumnModel().getColumn(14);
		col.setPreferredWidth(80);

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getPanelName()
	 */
	@Override
	protected String getPanelName() {
		return NAME_INVOICE;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getHeading()
	 */
	@Override
	String getHeading() {
		return "Fakturaer";
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getLoadClass()
	 */
	@Override
	Threadable getLoadClass() {
		return new LoadInvoice();
	}
}
