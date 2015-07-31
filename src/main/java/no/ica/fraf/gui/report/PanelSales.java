package no.ica.fraf.gui.report;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import no.ica.fraf.dao.AvdelingOmsetningVDAO;
import no.ica.fraf.enums.ReportEnum;
import no.ica.fraf.util.ModelUtil;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

/**
 * Panel for utvalg ved omsetningsrapport
 * 
 * @author abr99
 * 
 */
public class PanelSales extends AbstractReportPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	JYearChooser yearChooserFrom;

	/**
	 * 
	 */
	JMonthChooser monthChooserFrom;

	/**
	 * 
	 */
	JYearChooser yearChooserTo;

	/**
	 * 
	 */
	JMonthChooser monthChooserTo;

	/**
	 * 
	 */
	JTextField textFieldDep;

	/**
	 * 
	 */
	JComboBox comboBoxStatus;

	/**
	 * @param aReportFrame
	 */
	public PanelSales(ReportFrame aReportFrame) {
		super(aReportFrame,ReportEnum.REPORT_SALES);
	}

	/**
	 * @see no.ica.fraf.gui.report.AbstractReportPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
		super.initGUI();
		try {
			{
				JLabel labelDep = new JLabel();
				panelCenter.add(labelDep);
				labelDep.setText("Avdnr:");
			}
			{
				textFieldDep = new JTextField();
				textFieldDep.setPreferredSize(new Dimension(40, 20));
				panelCenter.add(textFieldDep);
			}
			{
				JLabel labelYearFrom = new JLabel();
				panelCenter.add(labelYearFrom);
				labelYearFrom.setText("Fra år:");
			}
			{
				yearChooserFrom = new JYearChooser();
				yearChooserFrom.setPreferredSize(new Dimension(60, 20));
				panelCenter.add(yearChooserFrom);
			}
			{
				JLabel labelPeriodeFrom = new JLabel();
				panelCenter.add(labelPeriodeFrom);
				labelPeriodeFrom.setText("Fra periode:");
			}
			{
				monthChooserFrom = new JMonthChooser();
				monthChooserFrom.setPreferredSize(new Dimension(100, 20));
				panelCenter.add(monthChooserFrom);
			}

			{
				JLabel labelYearTo = new JLabel();
				panelCenter.add(labelYearTo);
				labelYearTo.setText("Til år:");
			}
			{
				yearChooserTo = new JYearChooser();
				yearChooserTo.setPreferredSize(new Dimension(60, 20));
				panelCenter.add(yearChooserTo);
			}
			{
				JLabel labelPeriodeTo = new JLabel();
				panelCenter.add(labelPeriodeTo);
				labelPeriodeTo.setText("Til periode:");
			}
			{
				monthChooserTo = new JMonthChooser();
				monthChooserTo.setPreferredSize(new Dimension(100, 20));
				panelCenter.add(monthChooserTo);
			}

			{
				JLabel labelStatus = new JLabel();
				panelCenter.add(labelStatus, new GridBagConstraints(1, 1, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				labelStatus.setText("Status:");
			}
			{
				comboBoxStatus = new JComboBox();
				panelCenter.add(comboBoxStatus, new GridBagConstraints(2, 1, 1,
						1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
				comboBoxStatus
						.setPreferredSize(new java.awt.Dimension(100, 20));
			}

			this.setPreferredSize(new java.awt.Dimension(525, 100));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see no.ica.fraf.gui.report.AbstractReportPanel#initData()
	 */
	@Override
	protected void initData() {
		Vector<String> statusVector = new Vector<String>();
		statusVector.add("alle");
		statusVector.add("OK");
		statusVector.add("Nedlagt");
		statusVector.add("Kontrakt utgått");
		ComboBoxModel statusModel = new DefaultComboBoxModel(statusVector);
		comboBoxStatus.setModel(statusModel);

	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	public Object doWork(Object[] params, JLabel labelInfo) {
		Integer avdnr = null;
		String selectedStatus = null;

		if (textFieldDep.getText().length() != 0) {
			avdnr = Integer.valueOf(textFieldDep.getText());
		}

		if (comboBoxStatus.getSelectedIndex() > 0) {
			selectedStatus = (String) comboBoxStatus.getSelectedItem();
		}

		AvdelingOmsetningVDAO avdelingOmsetningVDAO = (AvdelingOmsetningVDAO) ModelUtil
				.getBean("avdelingOmsetningVDAO");
		List reportLines = avdelingOmsetningVDAO.findByPeriodeAndStatus(
				yearChooserFrom.getYear(), monthChooserFrom.getMonth() + 1,
				yearChooserTo.getYear(), monthChooserTo.getMonth() + 1, avdnr,
				selectedStatus);
		reportFrame.loadData(reportLines);
		return null;
	}

}
