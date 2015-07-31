package no.ica.fraf.gui.readsale;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import no.ica.fraf.dao.AvdelingOmsetningDAO;
import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.TableSorter;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

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
 * Panel for visning av importert omsetning eller budsjett
 * 
 * @author abr99
 * 
 */
public class PanelReadBudgetSale extends javax.swing.JPanel implements
		Threadable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JPanel panelNorth;

	/**
	 * 
	 */
	private JScrollPane scrollPaneSale;

	/**
	 * 
	 */
	private JTable tableSale;

	/**
	 * 
	 */
	JButton buttonSearch;

	/**
	 * 
	 */
	JMonthChooser monthChooser;

	/**
	 * 
	 */
	private JLabel labelPeriode;

	/**
	 * 
	 */
	JYearChooser yearChooser;

	/**
	 * 
	 */
	private JLabel labelYear;

	/**
	 * 
	 */
	JTextField textFieldDep;

	/**
	 * 
	 */
	private JLabel labelDep;

	/**
	 * TableModel for tabell
	 */
	ObjectTableModel saleTableModel;

	/**
	 * True dersom panelet omhandler budsjett
	 */
	boolean isBudget = true;

	/**
	 * DAO for omsetning
	 */
	AvdelingOmsetningDAO avdelingOmsetningDAO;

	/**
	 * Vindu som bruker panel
	 */
	ReadBudgetSaleFrame readBudgetSaleFrame;

	/**
	 * 
	 */
	public PanelReadBudgetSale() {

	}

	/**
	 * @param budget
	 * @param aInternalFrame
	 */
	public PanelReadBudgetSale(boolean budget,
			ReadBudgetSaleFrame aInternalFrame,AvdelingOmsetningDAO aAvdelingOmsetningDAO) {
		super();
		readBudgetSaleFrame = aInternalFrame;

		isBudget = budget;
		avdelingOmsetningDAO = aAvdelingOmsetningDAO;
		initGUI();
		initSaleTable();
	}

	/**
	 * Initerer GUI
	 */
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(465, 300));
			{
				panelNorth = new JPanel();
				FlowLayout panelNorthLayout = new FlowLayout();
				panelNorth.setLayout(panelNorthLayout);
				this.add(panelNorth, BorderLayout.NORTH);
				panelNorth.setPreferredSize(new java.awt.Dimension(10, 30));
				{
					labelDep = new JLabel();
					panelNorth.add(labelDep);
					labelDep.setText("Avdeling:");
				}
				{
					textFieldDep = new JTextField();
					panelNorth.add(textFieldDep);
					textFieldDep
							.setPreferredSize(new java.awt.Dimension(50, 20));
				}
				{
					labelYear = new JLabel();
					panelNorth.add(labelYear);
					labelYear.setText("År:");
				}
				{
					yearChooser = new JYearChooser();

					panelNorth.add(yearChooser);
					yearChooser
							.setPreferredSize(new java.awt.Dimension(60, 20));
				}
				{
					labelPeriode = new JLabel();
					panelNorth.add(labelPeriode);
					labelPeriode.setText("Periode:");
				}
				{
					monthChooser = new JMonthChooser();
					panelNorth.add(monthChooser);
					monthChooser.setPreferredSize(new java.awt.Dimension(110,
							20));
					monthChooser
							.setMinimumSize(new java.awt.Dimension(110, 25));
					monthChooser.setMaximumSize(new java.awt.Dimension(
							2147483647, 25));
				}
				{
					buttonSearch = new JButton();
					buttonSearch.setPreferredSize(new Dimension(80, 20));
					buttonSearch.setIcon(IconEnum.ICON_SEARCH.getIcon());
					buttonSearch.setMnemonic(KeyEvent.VK_S);
					panelNorth.add(buttonSearch);
					buttonSearch.setText("Søk");
					buttonSearch.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonSearchActionPerformed(evt);
						}
					});
				}
			}
			{
				scrollPaneSale = new JScrollPane();
				this.add(scrollPaneSale, BorderLayout.CENTER);
				{
					tableSale = new JTable();
					scrollPaneSale.setViewportView(tableSale);
					tableSale.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initierer tabell
	 */
	private void initSaleTable() {
		if (saleTableModel == null) {
			String[] columnNames = { "Avdnr", "År", "Periode", "Beløp",
					"Korreksjon", "Korrigert beløp", "Kommentar" };
			String[] methods = { "Avdnr", "Aar", "Periode", "Belop",
					"KorreksjonBelop", "KorrigertBelop", "KorreksjonKommentar" };
			Class[] params = { Integer.class, Integer.class, Integer.class,
					BigDecimal.class, BigDecimal.class, BigDecimal.class,
					String.class };
			ColumnFormatEnum[] formatColumns = new ColumnFormatEnum[] {
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.CURRENCY,
					ColumnFormatEnum.CURRENCY, ColumnFormatEnum.CURRENCY,
					ColumnFormatEnum.NONE };
			ObjectTableDef saleTableDef = new ObjectTableDef(columnNames,
					methods, params, formatColumns);

			saleTableModel = new ObjectTableModel(saleTableDef);
			saleTableModel.setEditable(false);

			TableSorter tableSorter = new TableSorter();
			tableSorter.setTableModel(saleTableModel);
			tableSorter.setTableHeader(tableSale.getTableHeader());

			tableSale.setModel(tableSorter);

			// Avdnr
			TableColumn col = tableSale.getColumnModel().getColumn(0);
			col.setPreferredWidth(70);

			// År
			col = tableSale.getColumnModel().getColumn(1);
			col.setPreferredWidth(50);

			// Periode
			col = tableSale.getColumnModel().getColumn(2);
			col.setPreferredWidth(110);

			// Beløp
			col = tableSale.getColumnModel().getColumn(3);
			col.setPreferredWidth(120);

			// Korreksjon
			col = tableSale.getColumnModel().getColumn(4);
			col.setPreferredWidth(120);

			// Korrigert beløp
			col = tableSale.getColumnModel().getColumn(5);
			col.setPreferredWidth(120);

			// Kommentar
			col = tableSale.getColumnModel().getColumn(6);
			col.setPreferredWidth(80);

		}
	}

	/**
	 * Søk
	 * 
	 * @param evt
	 */
	void buttonSearchActionPerformed(ActionEvent evt) {
		GuiUtil.runInThread(readBudgetSaleFrame, "Søker", "Søker...",
				new Seeker(), null);

	}

	/**
	 * Laster data for gitt buntid
	 * 
	 * @param batchId
	 */
	public void loadDataFromBatch(Integer batchId) {
		if (batchId == null) {
			saleTableModel.setData(null);
			return;
		}
		GuiUtil.runInThread(readBudgetSaleFrame, "Data", "Henter data...",
				this, new Object[] { batchId });

	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
	 */
	public void enableComponents(boolean enable) {
		buttonSearch.setEnabled(enable);
		//readBudgetSaleFrame.enableFrameComponents(enable);
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	public Object doWork(Object[] params, JLabel labelInfo) {
		List list = null;

		list = avdelingOmsetningDAO.findByBunt((Integer) params[0]);
		saleTableModel.setData(list);
		return new Boolean(true);
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
	}

	/**
	 * Klasse som håndterer søking
	 * 
	 * @author abr99
	 * 
	 */
	private class Seeker implements Threadable {

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
		 */
		public void enableComponents(boolean enable) {
			buttonSearch.setEnabled(enable);
			readBudgetSaleFrame.enableFrameComponents(enable);
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
		 *      javax.swing.JLabel)
		 */
		public Object doWork(Object[] params, JLabel labelInfo) {
			Integer dep = null;
			Integer year = null;
			Integer periode = null;
			List list = null;

			if (textFieldDep.getText().length() != 0) {
				dep = new Integer(textFieldDep.getText());
			}

			if (yearChooser.getYear() != -1) {
				year = new Integer(yearChooser.getYear());
			}

			if (monthChooser.getMonth() != -1) {
				periode = new Integer(monthChooser.getMonth() + 1);
			}

			if (isBudget) {
				list = avdelingOmsetningDAO
						.findBudgetSaleByDepartmentYearOrPeriode(dep, year,
								periode, "BUD");
			} else {
				list = avdelingOmsetningDAO
						.findBudgetSaleByDepartmentYearOrPeriode(dep, year,
								periode, "OMS");
			}
			saleTableModel.setData(list);
			return Boolean.TRUE;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null && object instanceof String) {
				GuiUtil.showErrorMsgFrame(readBudgetSaleFrame, "Feil", object
						.toString());
			}
		}

	}

}
