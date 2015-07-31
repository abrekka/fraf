package no.ica.fraf.gui.report;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import no.ica.fraf.dao.BetingelseGruppeDAO;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.dao.view.BetingelseTotalVDAO;
import no.ica.fraf.enums.ReportEnum;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.BokfSelskap;
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
 * Panel som brukes til å hente ut rapport for totale betingelser, brukes til
 * avstemming
 * 
 * @author abr99
 * 
 */
public class PanelReportConditionTotal extends AbstractReportPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JMonthChooser monthChooserTo;

	/**
	 * 
	 */
	private JMonthChooser monthChooserFrom;

	/**
	 * 
	 */
	private JYearChooser yearChooser;

	/**
	 * 
	 */
	private JComboBox comboBoxCompany;

	/**
	 * 
	 */
	private JComboBox comboBoxGroup;

	/**
	 * Konstruktør
	 * 
	 * @param aReportFrame
	 */
	public PanelReportConditionTotal(ReportFrame aReportFrame) {
		super(aReportFrame, ReportEnum.REPORT_BETINGELSE_TOTAL);

	}

	/**
	 * @see no.ica.fraf.gui.report.AbstractReportPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
		super.initGUI();
		try {
			this.setPreferredSize(new java.awt.Dimension(625, 105));
			{
				GridBagLayout panelCenterLayout = new GridBagLayout();
				panelCenter.setLayout(panelCenterLayout);
				{
					JLabel labelCompany = new JLabel();
					panelCenter.add(labelCompany, new GridBagConstraints(0, 0,
							1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelCompany.setText("Selskap:");
				}
				{
					comboBoxCompany = new JComboBox();
					panelCenter.add(comboBoxCompany, new GridBagConstraints(1,
							0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					comboBoxCompany.setPreferredSize(new java.awt.Dimension(
							100, 20));
				}
				{
					JLabel labelYear = new JLabel();
					panelCenter.add(labelYear, new GridBagConstraints(2, 0, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 10, 0, 0),
							0, 0));
					labelYear.setText("År:");
				}
				{
					yearChooser = new JYearChooser();
					panelCenter.add(yearChooser, new GridBagConstraints(3, 0,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					yearChooser
							.setPreferredSize(new java.awt.Dimension(70, 20));
				}
				{
					JLabel labelFromPeriode = new JLabel();
					panelCenter.add(labelFromPeriode, new GridBagConstraints(4,
							0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 10, 0, 0),
							0, 0));
					labelFromPeriode.setText("Fra periode:");
				}
				{
					monthChooserFrom = new JMonthChooser();
					panelCenter.add(monthChooserFrom, new GridBagConstraints(5,
							0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					monthChooserFrom.setPreferredSize(new java.awt.Dimension(
							110, 20));
				}
				{
					JLabel labeltoPeriode = new JLabel();
					panelCenter.add(labeltoPeriode, new GridBagConstraints(6,
							0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 10, 0, 0),
							0, 0));
					labeltoPeriode.setText("Til periode:");
				}
				{
					monthChooserTo = new JMonthChooser();
					panelCenter.add(monthChooserTo, new GridBagConstraints(7,
							0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					monthChooserTo.setPreferredSize(new java.awt.Dimension(110,
							20));
				}

				{
					JLabel labelGroup = new JLabel();
					panelCenter.add(labelGroup, new GridBagConstraints(8, 0, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 10, 0, 0),
							0, 0));
					labelGroup.setText("Gruppe:");
				}
				{
					comboBoxGroup = new JComboBox();
					panelCenter.add(comboBoxGroup, new GridBagConstraints(9, 0,
							1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					comboBoxGroup.setPreferredSize(new java.awt.Dimension(100,
							20));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see no.ica.fraf.gui.report.AbstractReportPanel#initData()
	 */
	@Override
	protected void initData() {
		BokfSelskapDAO bokfSelskapDAO = (BokfSelskapDAO) ModelUtil
				.getBean("bokfSelskapDAO");
		List companies = bokfSelskapDAO.findAll();

		if (companies != null) {
			ComboBoxModel model = new DefaultComboBoxModel(companies.toArray());
			comboBoxCompany.setModel(model);
		}

		final BetingelseGruppeDAO betingelseGruppeDAO = (BetingelseGruppeDAO) ModelUtil
				.getBean("betingelseGruppeDAO");
		List<BetingelseGruppe> groups = betingelseGruppeDAO.findAll();
		if (groups != null) {
			groups.add(0,new BetingelseGruppe(null,"alle",null,null));
			ComboBoxModel modelGroups = new DefaultComboBoxModel(groups
					.toArray());
			comboBoxGroup.setModel(modelGroups);
		}
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	public Object doWork(Object[] params, JLabel labelInfo) {
		BokfSelskap bokfSelskap = (BokfSelskap) comboBoxCompany
				.getSelectedItem();
		BetingelseGruppe betingelseGruppe = null;
		if (comboBoxGroup.getSelectedIndex() != 0) {
			betingelseGruppe = (BetingelseGruppe) comboBoxGroup
					.getSelectedItem();
		}
		BetingelseTotalVDAO betingelseTotalVDAO = (BetingelseTotalVDAO) ModelUtil
				.getBean("betingelseTotalVDAO");
		List reportLines = betingelseTotalVDAO
				.findByBokfSelskapYearPeriodeGroup(
						bokfSelskap.getSelskapNavn(), new Integer(yearChooser
								.getYear()), new Integer(monthChooserFrom
								.getMonth() + 1), new Integer(monthChooserTo
								.getMonth() + 1), betingelseGruppe);
		reportFrame.loadData(reportLines);
		return Boolean.TRUE;
	}

	/*
	 * protected void setSumTableModel(JTable tableSum) { BigDecimal sum;
	 * TableModel tableSumModel; TableColumn col; int noOfCol =
	 * reportEnum.getNumberOfColumns(); int[] sumCols =
	 * reportEnum.getSumColumns(); List sumColsList = Arrays.asList(sumCols);
	 * 
	 * String[][] row = new String[1][noOfCol]; String[] header = new
	 * String[noOfCol];
	 * 
	 * row[0][0] = "Sum:"; header[0] = "";
	 * 
	 * 
	 * if(sumCols != null && sumCols.length != 0){ for(int i=1;i<sumCols.length;i++){
	 * if(sumColsList.contains(i)){ sum = reportTableModel.getSumColumn(i); sum =
	 * sum.setScale(2, BigDecimal.ROUND_DOWN); row[0][i] = sum.toString();
	 * }else{ row[0][i] = ""; }
	 * 
	 * header[i] = ""; } }
	 * 
	 * tableSumModel = new DefaultTableModel(row, header);
	 * tableSum.setModel(tableSumModel);
	 * 
	 * 
	 * for (int i = 0; i < noOfCol; i++) { col =
	 * tableSum.getColumnModel().getColumn(i);
	 * col.setPreferredWidth(reportEnum.getColumnWidth(i)); } }
	 */
}
