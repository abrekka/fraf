package no.ica.fraf.gui.report;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import no.ica.fraf.FrafException;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.ReportEnum;
import no.ica.fraf.enums.ReportTypeEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.TableSorter;
import no.ica.fraf.gui.model.interfaces.Excelable;
import no.ica.fraf.gui.model.interfaces.SortingListener;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.report.ReportViewer;
import no.ica.fraf.util.ExcelUtil;
import no.ica.fraf.util.GuiUtil;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXTable;

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
 * Vindu som brukes til å vise rapporter i
 * 
 * @author abr99
 * 
 */
public class ReportFrame extends javax.swing.JInternalFrame implements
		Excelable, Threadable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JLabel labelCounter;

	/**
	 * 
	 */
	JScrollPane scrollPaneReport;

	/**
	 * 
	 */
	private JTable tableSum;

	/**
	 * 
	 */
	JScrollPane scrollPaneSum;

	/**
	 * 
	 */
	JPopupMenu popupMenuExcel;

	/**
	 * 
	 */
	JMenuItem menuItemExcel;

	/**
	 * 
	 */
	JButton buttonExcel;

	/**
	 * 
	 */
	JButton buttonCancel;

	/**
	 * 
	 */
	private JXTable tableReport;

	/**
	 * 
	 */
	private JPanel panelSouth;

	/**
	 * Peker til vindu til bruk i innerklasser
	 */
	JInternalFrame internalFrame;

	/**
	 * Sorterer tabell
	 */
	TableSorter tableSorter;

	/**
	 * Nruker
	 */
	ApplUser currentApplUser;

	/**
	 * 
	 */
	private JPanel panelInfo;

	/**
	 * 
	 */
	AbstractReportPanel filterPanel;

	/**
	 * 
	 */
	List currentDirectives = null;

	/**
	 * Gjeldende rapporttype
	 */
	ReportEnum currentReportType = ReportEnum.REPORT_BETINGELSE_TOTAL;

	/**
	 * Logger til database
	 */
	static Logger logger = Logger.getLogger(ReportFrame.class);
	JButton buttonPrint;
	

	/**
	 * Konstruktør
	 * 
	 * @param applUser
	 * @param reportType
	 */
	public ReportFrame(ApplUser applUser, ReportEnum reportType) {
		super();
		
		internalFrame = this;
		currentApplUser = applUser;
		currentReportType = reportType;

		initGUI();

		setFrameIcon(IconEnum.ICON_FRAF.getIcon());

		initTable();
	}

	/**
	 * Laster data
	 * 
	 * @param dataList
	 */
	public void loadData(final List dataList) {
		GuiUtil.runInThread(this, "Rapport - "
				+ currentReportType.getReportString(), "Henter data...", this,
				new Object[] { dataList });
	}

	/**
	 * Henter TableModel for tabell
	 * 
	 * @return TableModel for tabell
	 */
	/*
	 * private ObjectTableModel getObjectTableModel() { reportTableModel =
	 * currentReportType.getObjectTableModel(); return reportTableModel; }
	 */

	/**
	 * Setter kolonnebredder
	 */
	private void setColumnWidth() {
		TableColumn col;
		int noOfCol = currentReportType.getNumberOfColumns();
		for (int i = 0; i < noOfCol; i++) {
			col = tableReport.getColumnModel().getColumn(i);
			col.setPreferredWidth(currentReportType.getColumnWidth(i));
		}

	}

	/**
	 * Initierer tabell
	 */
	private void initTable() {
		tableSorter = new TableSorter();

		// tableSorter.setTableModel(getObjectTableModel());
		tableSorter.setTableModel(filterPanel.getReportTableModel());
		tableReport.setHighlighters(filterPanel.getHighlighterPipeline());
		tableSorter.setTableHeader(tableReport.getTableHeader());
		tableReport.setModel(tableSorter);
		tableSorter.addSortChangeListener(new TableSortingListener());

		setColumnWidth();
	}

	/**
	 * Laster data
	 * 
	 * @param dataList
	 */
	private void initData(List dataList) {
		if (dataList == null) {
			// reportTableModel.deleteData();
			filterPanel.getReportTableModel().deleteData();
			labelCounter.setText("0");
		} else {
			// reportTableModel.setData(dataList);
			filterPanel.getReportTableModel().setData(dataList);
			labelCounter.setText(String.valueOf(dataList.size()));
		}

		// setSumTableModel(reportTableModel);
		setSumTableModel(filterPanel.getReportTableModel());

	}

	/**
	 * Setter data i summeringstabell
	 * 
	 * @param dataModel
	 */
	protected void setSumTableModel(ObjectTableModel dataModel) {
		BigDecimal sum;
		TableModel tableSumModel;
		TableColumn col;
		int noOfCol = currentReportType.getNumberOfColumns();
		List<Integer> sumCols = currentReportType.getSumColumns();
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

		String[][] row = new String[1][noOfCol];
		String[] header = new String[noOfCol];

		row[0][0] = "Sum:";
		header[0] = "";

		if (sumCols != null && sumCols.size() != 0) {
			for (int i = 1; i < noOfCol; i++) {
				if (sumCols.contains(i)) {
					sum = filterPanel.getReportTableModel().getSumColumn(i);
					sum = sum.setScale(2, BigDecimal.ROUND_DOWN);
					row[0][i] = numberFormat.format(sum);
				} else {
					row[0][i] = "";
				}

				header[i] = "";
			}
		}

		tableSumModel = new DefaultTableModel(row, header);
		tableSum.setModel(tableSumModel);

		for (int i = 0; i < noOfCol; i++) {
			col = tableSum.getColumnModel().getColumn(i);
			col.setPreferredWidth(currentReportType.getColumnWidth(i));
			DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(SwingConstants.RIGHT);
			col.setCellRenderer(renderer);
		}
	}

	/**
	 * Initierer GUI
	 */
	private void initGUI() {
		ButtonListener buttonListener = new ButtonListener();
		TableMouseListener tableMouseListener = new TableMouseListener();
		MyAdjustmentListener adjustmentListener = new MyAdjustmentListener();
		try {
			this.setPreferredSize(currentReportType.getWindowSize());
			this.setBounds(0, 0, currentReportType.getWindowSize().width,currentReportType.getWindowSize().height);
			/*switch (currentReportType) {

			case REPORT_BETINGELSE_TOTAL:
				this.setPreferredSize(new java.awt.Dimension(800, 500));
				this.setBounds(0, 0, 800, 500);
				break;
			case REPORT_AVDELING_OVERSIKT:
				this.setPreferredSize(new java.awt.Dimension(800, 500));
				this.setBounds(0, 0, 800, 500);
				break;
			case REPORT_NY_AVDELING:
				this.setPreferredSize(new java.awt.Dimension(500, 500));
				this.setBounds(0, 0, 500, 500);
				break;
			case REPORT_AVDELING_BETINGELSE:
				this.setPreferredSize(new java.awt.Dimension(1000, 500));
				this.setBounds(0, 0, 1200, 500);
				break;
			case REPORT_MANGLENDE_BUDSJETT:
				this.setPreferredSize(new java.awt.Dimension(300, 500));
				this.setBounds(0, 0, 300, 500);
				break;
			case REPORT_TOTAL_FAKTURERING:
				this.setPreferredSize(new java.awt.Dimension(1200, 500));
				this.setBounds(0, 0, 1200, 500);
				break;
			case REPORT_MISSING:
				this.setPreferredSize(new java.awt.Dimension(750, 500));
				this.setBounds(0, 0, 750, 500);
				break;
			case REPORT_MIRROR:
				this.setPreferredSize(new java.awt.Dimension(700, 500));
				this.setBounds(0, 0, 750, 500);
				break;
			case REPORT_MIRROR_STATUS:
				this.setPreferredSize(new java.awt.Dimension(350, 500));
				this.setBounds(0, 0, 350, 500);
				break;
			case REPORT_SALES:
				this.setPreferredSize(new java.awt.Dimension(800, 500));
				this.setBounds(0, 0, 800, 500);
				break;
            case REPORT_ARCHIVE:
                this.setPreferredSize(new java.awt.Dimension(400, 500));
                this.setBounds(0, 0, 300, 500);
                break;
            case REPORT_SECURITY:
                this.setPreferredSize(new java.awt.Dimension(790, 500));
                this.setBounds(0, 0, 790, 500);
                break;
            case REPORT_NEDLAGT_AVDELING:
				this.setPreferredSize(new java.awt.Dimension(500, 500));
				this.setBounds(0, 0, 500, 500);
				break;
			}*/

			BorderLayout thisLayout = new BorderLayout();
			this.getContentPane().setLayout(thisLayout);
			setVisible(true);
			this.setTitle("Rapport - " + currentReportType.getReportString());
			this.setIconifiable(true);
			this.setMaximizable(true);
			this.setResizable(true);
			{
				JPanel panelCenter = new JPanel();
				BorderLayout jPanel1Layout = new BorderLayout();
				panelCenter.setLayout(jPanel1Layout);
				this.getContentPane().add(panelCenter, BorderLayout.CENTER);
				{
					scrollPaneReport = new JScrollPane();
					scrollPaneReport
							.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					panelCenter.add(scrollPaneReport, BorderLayout.CENTER);
					{
						tableReport = new JXTable();
						tableReport.setName("tableReport");
						
						scrollPaneReport.setViewportView(tableReport);
						tableReport.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						tableReport.addMouseListener(tableMouseListener);
						tableReport.getTableHeader().addMouseListener(
								new MouseListener() {

									public void mouseClicked(MouseEvent e) {
									}

									public void mousePressed(MouseEvent e) {
									}

									@SuppressWarnings("synthetic-access")
									public void mouseReleased(MouseEvent e) {
										resizeSumColumns();

									}

									public void mouseEntered(MouseEvent e) {
									}

									public void mouseExited(MouseEvent e) {
									}

								});
					}
				}
				{
					scrollPaneSum = new JScrollPane();
					panelCenter.add(scrollPaneSum, BorderLayout.SOUTH);
					scrollPaneSum
							.setPreferredSize(new java.awt.Dimension(3, 35));
					{
						tableSum = new JTable();
						tableSum.setTableHeader(null);
						scrollPaneSum.setViewportView(tableSum);
						tableSum.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					}
					scrollPaneSum.getHorizontalScrollBar()
							.addAdjustmentListener(adjustmentListener);
				}
			}
			{
				filterPanel = null;
				switch (currentReportType) {
				case REPORT_BETINGELSE_TOTAL:
					filterPanel = new PanelReportConditionTotal(this);
					break;
				case REPORT_AVDELING_OVERSIKT:
					filterPanel = new PanelAvdelingOversikt(this);
					tableSum.setVisible(false);
					break;
				case REPORT_NY_AVDELING:
					filterPanel = new PanelNyAvdeling(this);
					tableSum.setVisible(false);
					break;
				case REPORT_AVDELING_BETINGELSE:
					filterPanel = new PanelAvdelingBetingelse(this);
					tableSum.setVisible(false);
					break;
				case REPORT_MANGLENDE_BUDSJETT:
					filterPanel = new PanelNoBudget(this);
					tableSum.setVisible(false);
					break;
				case REPORT_TOTAL_FAKTURERING:
					filterPanel = new PanelTotalInvoice(this);
					break;
				case REPORT_MISSING:
					filterPanel = new PanelMissing(this);
					tableSum.setVisible(false);
					break;
				case REPORT_MIRROR:
					filterPanel = new PanelMirror(this);
					tableSum.setVisible(false);
					break;
				case REPORT_MIRROR_STATUS:
					filterPanel = new PanelMirrorStatus(this);
					tableSum.setVisible(false);
					break;
				case REPORT_SALES:
					filterPanel = new PanelSales(this);
					tableSum.setVisible(false);
					break;
                case REPORT_ARCHIVE:
                    filterPanel = new PanelArchive(this);
                    tableSum.setVisible(false);
                    break;
                case REPORT_SECURITY:
                    filterPanel = new PanelSecurity(this);
                    tableSum.setVisible(false);
                    break;
                case REPORT_NEDLAGT_AVDELING:
					filterPanel = new PanelNedlagtAvdeling(this);
					tableSum.setVisible(false);
					break;
                case REPORT_AVREGNING_SAMMENDARG:
					filterPanel = new PanelDeductSummary(this);
					tableSum.setVisible(false);
					break;
                case REPORT_SPEILET_BETINGELSE_MANGEL:
					filterPanel = new PanelSpeiletBetingelseMangel(this);
					tableSum.setVisible(false);
					break;
				}

				this.getContentPane().add(filterPanel, BorderLayout.NORTH);
			}
			{
				panelSouth = new JPanel();
				BorderLayout panelSouthLayout = new BorderLayout();
				panelSouth.setLayout(panelSouthLayout);
				this.getContentPane().add(panelSouth, BorderLayout.SOUTH);
				panelSouth.setPreferredSize(new java.awt.Dimension(10, 60));
				{
					JPanel panelButtons = new JPanel();
					FlowLayout panelButtonsLayout = new FlowLayout();
					panelButtons.setLayout(panelButtonsLayout);
					panelSouth.add(panelButtons, BorderLayout.SOUTH);
					{
						buttonExcel = new JButton();
						buttonExcel.setIcon(IconEnum.ICON_EXCEL.getIcon());
						buttonExcel.setMnemonic(KeyEvent.VK_E);
						panelButtons.add(buttonExcel);
						buttonExcel.setText("Excel");
						buttonExcel.setPreferredSize(new java.awt.Dimension(90,
								25));
						buttonExcel.addActionListener(buttonListener);
					}
					if(currentReportType.usePrintButton())
					{
						buttonPrint = new JButton();
						buttonPrint.setIcon(IconEnum.ICON_PRINT.getIcon());
						buttonPrint.setMnemonic(KeyEvent.VK_S);
						panelButtons.add(buttonPrint);
						buttonPrint.setText("Skriv ut");
						buttonPrint.setPreferredSize(new java.awt.Dimension(90,
								25));
						buttonPrint.addActionListener(buttonListener);
					}
					{
						buttonCancel = new JButton();
						buttonCancel.setIcon(IconEnum.ICON_CANCEL.getIcon());
						buttonCancel.setMnemonic(KeyEvent.VK_A);
						panelButtons.add(buttonCancel);
						buttonCancel.setText("Avbryt");
						buttonCancel.setPreferredSize(new java.awt.Dimension(
								90, 25));
						buttonCancel.addActionListener(buttonListener);
					}

				}
				{
					panelInfo = new JPanel();
					FlowLayout panelInfoLayout = new FlowLayout();
					panelInfoLayout.setAlignment(FlowLayout.LEFT);
					panelInfo.setLayout(panelInfoLayout);
					panelSouth.add(panelInfo, BorderLayout.CENTER);
					{
						JLabel labelCount = new JLabel();
						panelInfo.add(labelCount);
						labelCount.setText("Antall:");
					}
					{
						labelCounter = new JLabel();
						panelInfo.add(labelCounter);
						labelCounter.setPreferredSize(new java.awt.Dimension(
								30, 20));
					}
				}

			}
			{
				popupMenuExcel = new JPopupMenu("Excel");
				menuItemExcel = new JMenuItem("Eksporter data til excel");
				menuItemExcel.setIcon(IconEnum.ICON_EXCEL.getIcon());
				popupMenuExcel.add(menuItemExcel);
				menuItemExcel.addActionListener(new MenuItemListener(this));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Resizer kolonner for suntabell dersom rapporttabell endrer seg
	 */
	private void resizeSumColumns() {
		TableColumnModel reportColumnModel = tableReport.getColumnModel();
		for (int i = 0; i < 2; i++) {
			TableColumnModel sumColumnModel = tableSum.getColumnModel();
			if (sumColumnModel.getColumnCount() != 0) {
				for (int j = 0; j < reportColumnModel.getColumnCount(); j++) {
					sumColumnModel.getColumn(j).setPreferredWidth(
							reportColumnModel.getColumn(j).getPreferredWidth());
				}
			}

		}

	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
	 */
	public void enableComponents(boolean enable) {
		tableReport.setEnabled(enable);
		buttonExcel.setEnabled(enable);
		buttonCancel.setEnabled(enable);
		if(buttonPrint!=null){
			buttonPrint.setEnabled(enable);
		}
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
		public void actionPerformed(final ActionEvent action) {
			GuiUtil.setWaitCursor(internalFrame);
			/** *** Cancel **** */
			if (action.getActionCommand().equalsIgnoreCase(
					buttonCancel.getActionCommand())) {
				exitFrame();
			} else if (action.getActionCommand().equalsIgnoreCase(
					buttonExcel.getActionCommand())) {
				showDataInExcel();
			}
			else if (action.getActionCommand().equalsIgnoreCase(
					buttonPrint.getActionCommand())) {
				print();
			}
			GuiUtil.setDefaultCursor(internalFrame);
		}
	}

	/**
	 * Avslutter vindu
	 */
	void exitFrame() {
		dispose();
	}

	/**
	 * Klasse som håndterer museklikk i tabell
	 * 
	 * @author abr99
	 * 
	 */
	private class TableMouseListener implements MouseInputListener {

		/**
		 * Denne metoden blir kjørt når det klikkes i tabellen. Dersom det
		 * gjøres dobbeltklikk vil dialog for editering vises
		 * 
		 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 */
		public void mouseClicked(final MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON3) {
				popupMenuExcel.show((JTable) e.getSource(), e.getX(), e.getY());
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
	 * Klasse som håndterer valg i popupmeny
	 * 
	 * @author abr99
	 * 
	 */
	private class MenuItemListener implements ActionListener {
		private Component parent;
		public MenuItemListener(Component aParent){
			parent=aParent;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(final ActionEvent actionEvent) {
			if (actionEvent.getActionCommand().equalsIgnoreCase(
					menuItemExcel.getText())) {
				GuiUtil.setWaitCursor(parent);
				
				showDataInExcel();
				GuiUtil.setDefaultCursor(parent);
				
			}
		}

	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Excelable#showDataInExcel()
	 */
	public void showDataInExcel() {
		GuiUtil.runInThread(this, "Generering av excelfil",
				"Genererer excelfil", new ExcelGenerator(), null);

	}
	
	public void print(){
		GuiUtil.runInThreadWheel(this.getRootPane(),new Printer(),null);
	}

	/**
	 * Klasse som håndterer synkronisering av tabell og summeringstabell
	 * 
	 * @author abr99
	 * 
	 */
	class MyAdjustmentListener implements AdjustmentListener {

		/**
		 * @see java.awt.event.AdjustmentListener#adjustmentValueChanged(java.awt.event.AdjustmentEvent)
		 */
		public void adjustmentValueChanged(final AdjustmentEvent event) {
			JScrollBar scrollBarSum = scrollPaneSum.getHorizontalScrollBar();
			JScrollBar scrollBarReport = scrollPaneReport
					.getHorizontalScrollBar();
			int newValue;
			newValue = scrollBarSum.getValue();
			scrollBarReport.setValue(newValue);

		}

	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	public Object doWork(Object[] params, JLabel labelInfo) {
		initData((List) params[0]);
		return Boolean.TRUE;
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
	}

	/**
	 * Klasse som håndterer generering av excelfil
	 * 
	 * @author atb
	 * 
	 */
	private class ExcelGenerator implements Threadable {

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
			ExcelUtil excelUtil;
			StringBuffer fileName;
			List<Integer> sumColumns = currentReportType.getSumColumns();

			List<Integer> numCols = currentReportType.getNumColumns();
			List<Integer> dateCols = currentReportType.getDateColumns();
			StringBuffer returnString = new StringBuffer();
			ObjectTableModel excelModel = filterPanel
					.getReportTableModelExcel();
			excelModel.setData(filterPanel.getReportTableModel().getData());

			if (currentReportType.isListable()) {
				excelUtil = new ExcelUtil(currentReportType.getReportString());
			} else {

				excelUtil = new ExcelUtil(excelModel, currentReportType
						.getReportString(), currentReportType.getReportString());
			}

			fileName = new StringBuffer(currentReportType.getReportString())
					.append("_Rapport").append(".xls");
			String dir = excelUtil.prepare(currentApplUser, internalFrame);
			Object filter = filterPanel.getFilter();

			try {
				if (dir != null) {
					if (currentReportType.isListable()) {
						excelUtil.showDataInExcel(dir.toString(), fileName
								.toString().replaceAll(" ", "_"),
								currentReportType.getExcelListable(),
								excelModel, new Object[] { currentDirectives,
										filter }, numCols, labelInfo);
					} else {

						excelUtil.showDataInExcel(dir.toString(), fileName
								.toString().replaceAll(" ", "_"), sumColumns,
								numCols, labelInfo, dateCols);

					}

					returnString
							.append("Dersom ikke excel starter automatisk, ligger excelfil med navn "
									+ fileName.toString()
									+ " under katalog "
									+ dir.toString());
				}
			} catch (FrafException e) {
				logger.error("Feil ved generering av excelfil", e);
				returnString.append("Feil: ").append(e.getMessage());
				e.printStackTrace();
			} catch (RuntimeException runEx) {
				logger.error("Feil ved generering av excelfil", runEx);
				returnString.append("Feil: ").append(runEx.getMessage());
			}

			return returnString.toString();
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null) {
				GuiUtil
						.showMsgFrame(internalFrame, "Ferdig", object
								.toString());
			}

		}

	}

	/**
	 * Klasse som håndterer sortering
	 * 
	 * @author atb
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
			currentDirectives = directives;

		}

	}
	
	private class Printer implements Threadable{

		public void enableComponents(boolean enable) {
			// TODO Auto-generated method stub
			
		}

		public Object doWork(Object[] params, JLabel labelInfo) {
			labelInfo.setText("Genererer fakturaer...");
			String errorString=null;
			try {
				Collection<Faktura> invoices = filterPanel.getInvoices();
				if(invoices!=null){
					ReportViewer reportViewer = new ReportViewer("Fakturaer");
					reportViewer.setLocation(GuiUtil.getCenter(FrafMain
							.getInstance().getSize(), reportViewer.getSize()));
					FrafMain.getInstance().addInternalFrame(reportViewer);
					// reportViewer.generateInvoiceReport(invoiceReportModel,
					// con,reportType, printable, parameters);
					reportViewer.generateInvoiceReportFromBean(invoices, null,
							ReportTypeEnum.FRANCHISE, true, null);
				}
			} catch (FrafException e) {
				errorString=e.getMessage();
				e.printStackTrace();
			}
			return errorString;
		}

		public void doWhenFinished(Object object) {
			if(object!=null){
				GuiUtil.showErrorMsgDialog(internalFrame,"Feil",object.toString());
			}
			
		}
		
	}
}
