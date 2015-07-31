package no.ica.fraf.gui.report;

import java.util.Date;
import java.util.List;

import javax.swing.JLabel;

import no.ica.fraf.dao.DepartmentDAO;
import no.ica.fraf.enums.ReportEnum;
import no.ica.fraf.util.ModelUtil;

import com.toedter.calendar.JDateChooser;

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
 * Panel som brukes til å hente ut nye avdelinger
 * 
 * @author abr99
 * 
 */
public class PanelNedlagtAvdeling extends AbstractReportPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JDateChooser dateChooser;

	/**
	 * Konstruktør
	 * 
	 * @param aReportFrame
	 */
	public PanelNedlagtAvdeling(ReportFrame aReportFrame) {
		super(aReportFrame, ReportEnum.REPORT_NEDLAGT_AVDELING);
	}

	/**
	 * @see no.ica.fraf.gui.report.AbstractReportPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
		super.initGUI();
		try {
			this.setPreferredSize(new java.awt.Dimension(400, 75));
			{
				{
					JLabel labelDate = new JLabel();
					panelCenter.add(labelDate);
					labelDate.setText("Dato:");
				}
				{
					dateChooser = new JDateChooser(true);
					panelCenter.add(dateChooser);
					dateChooser
							.setPreferredSize(new java.awt.Dimension(120, 20));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	public Object doWork(Object[] params, JLabel labelInfo) {
		Date fromDate = dateChooser.getDate();
		List reportLines;
		DepartmentDAO departmentDAO = (DepartmentDAO) ModelUtil
				.getBean(DepartmentDAO.DAO_NAME);

		if (fromDate == null) {
			reportLines = departmentDAO.findAllClosed();
		} else {
			reportLines = departmentDAO.findClosedByDate(fromDate);
		}
		reportFrame.loadData(reportLines);
		return Boolean.TRUE;
	}

	/**
	 * @see no.ica.fraf.gui.report.AbstractReportPanel#initData()
	 */
	@Override
	protected void initData() {
	}

}
