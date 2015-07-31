package no.ica.fraf.gui.report;

import java.util.List;

import javax.swing.JLabel;

import no.ica.fraf.dao.AvdelingOmsetningDAO;
import no.ica.fraf.enums.ReportEnum;
import no.ica.fraf.util.ModelUtil;

import com.toedter.calendar.JYearChooser;

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
 * Panel for uthenting av avdelinger som mangler budsjett
 * 
 * @author abr99
 * 
 */
public class PanelNoBudget extends AbstractReportPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JYearChooser yearChooser;

	/**
	 * Konstruktør
	 * 
	 * @param aReportFrame
	 */
	public PanelNoBudget(ReportFrame aReportFrame) {
		super(aReportFrame, ReportEnum.REPORT_MANGLENDE_BUDSJETT);
	}

	/**
	 * @see no.ica.fraf.gui.report.AbstractReportPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
		super.initGUI();
		try {

			this.setPreferredSize(new java.awt.Dimension(475, 79));

			{

				{
					JLabel labelYear = new JLabel();
					panelCenter.add(labelYear);
					labelYear.setText("År:");
				}
				{
					yearChooser = new JYearChooser();
					panelCenter.add(yearChooser);
					yearChooser
							.setPreferredSize(new java.awt.Dimension(70, 20));
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
		Integer year = yearChooser.getYear();

		AvdelingOmsetningDAO avdelingOmsetningDAO = (AvdelingOmsetningDAO) ModelUtil
				.getBean("avdelingOmsetningDAO");
		List reportLines = avdelingOmsetningDAO.findAvdelingUtenBudsjett(year);

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
