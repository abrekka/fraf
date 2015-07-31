package no.ica.fraf.gui.fenistra;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import no.ica.fraf.dao.fenistra.LfFakturaPosterDAO;
import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.YesNoInteger;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.LfFakturaPoster;
import no.ica.fraf.model.LkKontraktobjekter;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * This code was generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * *************************************
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED
 * for this machine, so Jigloo or this code cannot be used legally
 * for any corporate or commercial purpose.
 * *************************************
 */
/**
 * Dialog viser faktuerte poster fra Fensitra gitt et kontraktobjekt
 */
public class FenistraInvoiceFrame extends javax.swing.JInternalFrame implements
		Threadable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JPanel panelSouth;

	/**
	 * 
	 */
	private JScrollPane scrollPaneInvoices;

	/**
	 * 
	 */
	private JTable tableInvoices;

	/**
	 * 
	 */
	private JButton buttonCancel;

	/**
	 * Gjeldende betingelse fra Fenistra
	 */
	private LkKontraktobjekter currentLkKontraktobjekter;

	/**
	 * DAO for fakturaer i Fenistra
	 */
	private LfFakturaPosterDAO lfFakturaPosterDAO = (LfFakturaPosterDAO) ModelUtil
			.getBean("lfFakturaPosterDAO");

	/**
	 * Kolonnenavn for tabell
	 */
	private static final String[] COLUMN_NAMES = new String[] { "Fakturanr",
			"Tekst", "Fra dato", "Til dato", "Terminstart", "Beløp",
			"Remitering" };

	/**
	 * Metodenavn for [ hente ut verdier til tabell
	 */
	private static final String[] METHODS = new String[] { "FakturaNr",
			"Fakturatekst", "Fra", "Til", "TerminStart", "PostBeloep",
			"Remitering" };// ,"Overfoert"};

	/**
	 * Klassetyper for kolonner
	 */
	private static final Class[] PARAMS = new Class[] { Integer.class,
			String.class, Date.class, Date.class, Date.class, BigDecimal.class,
			YesNoInteger.class };// ,YesNoInteger.class };

	/**
	 * Format på kolonneverdier
	 */
	private static final ColumnFormatEnum[] FORMATS = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.CURRENCY,
			ColumnFormatEnum.NONE };// ,ObjectTableModel.NONE };

	/**
	 * Definsjon for TableModel
	 */
	private static ObjectTableDef def = new ObjectTableDef(COLUMN_NAMES,
			METHODS, PARAMS, FORMATS);

	/**
	 * TableModel som brukes av tabell
	 */
	private ObjectTableModel<LfFakturaPoster> objectTableModel = new ObjectTableModel<LfFakturaPoster>(
			def);

	/**
	 * Konstruktør
	 * 
	 * @param lkKontraktobjekter
	 *            gjeldende betingelse
	 */
	public FenistraInvoiceFrame(LkKontraktobjekter lkKontraktobjekter) {
		super();
		currentLkKontraktobjekter = lkKontraktobjekter;
		initGUI();
		loadData();
		setTableColumnWidth();
		setFrameIcon(IconEnum.ICON_FRAF.getIcon());
	}

	/**
	 * Laster inn data
	 */
	private void loadData() {
		GuiUtil
				.runInThread(this, "Fakturarer", "Henter fakturarer", this,
						null);
	}

	/**
	 * Setter kolonnebredder
	 */
	private void setTableColumnWidth() {
		objectTableModel.setEditable(false);
		tableInvoices.setModel(objectTableModel);
		// Fakturanr
		TableColumn col = tableInvoices.getColumnModel().getColumn(0);
		col.setPreferredWidth(80);

		// Tekst
		col = tableInvoices.getColumnModel().getColumn(1);
		col.setPreferredWidth(250);

		// Fra dato
		col = tableInvoices.getColumnModel().getColumn(2);
		col.setPreferredWidth(100);

		// Til dato
		col = tableInvoices.getColumnModel().getColumn(3);
		col.setPreferredWidth(100);

		// Terminstart
		col = tableInvoices.getColumnModel().getColumn(4);
		col.setPreferredWidth(100);

		// Beløp
		col = tableInvoices.getColumnModel().getColumn(5);
		col.setPreferredWidth(100);

		// Remitering
		col = tableInvoices.getColumnModel().getColumn(6);
		col.setPreferredWidth(70);

		// Overført
		/*
		 * col = tableInvoices.getColumnModel().getColumn(7);
		 * col.setPreferredWidth(50);
		 */
	}

	/**
	 * Initerer GUI
	 */
	private void initGUI() {
		try {
			setTitle("Betingelse "
					+ currentLkKontraktobjekter.getKontraktObjekt());
			this.setPreferredSize(new java.awt.Dimension(830, 300));
			this.setBounds(0, 0, 830, 300);
			BorderLayout thisLayout = new BorderLayout();
			this.getContentPane().setLayout(thisLayout);
			setVisible(true);
			this.setClosable(true);
			this.setIconifiable(true);
			this.setMaximizable(true);
			this.setResizable(true);
			{
				panelSouth = new JPanel();
				this.getContentPane().add(panelSouth, BorderLayout.SOUTH);
				panelSouth.setPreferredSize(new java.awt.Dimension(10, 40));
				{
					buttonCancel = new JButton();
					panelSouth.add(buttonCancel);
					buttonCancel.setText("Avbryt");
					buttonCancel.setIcon(IconEnum.ICON_CANCEL.getIcon());
					buttonCancel.setMnemonic(KeyEvent.VK_A);
					buttonCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonCancelActionPerformed(evt);
						}
					});
				}
			}
			{
				scrollPaneInvoices = new JScrollPane();
				this.getContentPane().add(scrollPaneInvoices,
						BorderLayout.CENTER);
				{
					TableModel tableInvoicesModel = new DefaultTableModel(
							new String[][] { { "One", "Two" },
									{ "Three", "Four" } }, new String[] {
									"Column 1", "Column 2" });
					tableInvoices = new JTable();
					scrollPaneInvoices.setViewportView(tableInvoices);
					tableInvoices.setModel(tableInvoicesModel);
					tableInvoices.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lukker dialog
	 * 
	 * @param evt
	 */
	void buttonCancelActionPerformed(ActionEvent evt) {
		dispose();
	}

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
		List<LfFakturaPoster> data = lfFakturaPosterDAO
				.findByKontrakt(currentLkKontraktobjekter);
		objectTableModel.setData(data);
		return new Boolean(true);
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
	}

}
