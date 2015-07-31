package no.ica.fraf.gui.report;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.dao.SpeiletBetingelseDAO;
import no.ica.fraf.dao.fenistra.LkKontraktobjekterDAO;
import no.ica.fraf.dao.fenistra.MrKontiDAO;
import no.ica.fraf.enums.ReportEnum;
import no.ica.fraf.model.MrKontiOrg;
import no.ica.fraf.util.ApplicationUtil;
import no.ica.fraf.util.ModelUtil;

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
 * Panel for å filtrere på hvilke betingelser det skal hentes status for
 * 
 * @author abr99
 * 
 */
public class PanelMirrorStatus extends AbstractReportPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JComboBox comboBoxAccount;

	/**
	 * 
	 */
	private JCheckBox checkBoxMirror;

	/**
	 * @param aReportFrame
	 */
	public PanelMirrorStatus(ReportFrame aReportFrame) {
		super(aReportFrame,ReportEnum.REPORT_MIRROR_STATUS);
	}

	/**
	 * @see no.ica.fraf.gui.report.AbstractReportPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
		super.initGUI();
		try {
			{
				JLabel labelAccount = new JLabel();
				panelCenter.add(labelAccount);
				labelAccount.setText("Konto:");
			}
			{
				comboBoxAccount = new JComboBox();
				panelCenter.add(comboBoxAccount);
				comboBoxAccount.setPreferredSize(new Dimension(200, 25));
			}
			{
				checkBoxMirror = new JCheckBox();
				panelCenter.add(checkBoxMirror);
				checkBoxMirror.setText("Speilet");
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
		MrKontiDAO mrKontiDAO = (MrKontiDAO) ModelUtil.getBean("mrKontiDAO");
		List<Object> accounts = mrKontiDAO.findAllExpenses();

		if (accounts != null) {
			accounts.add(0, "alle");
			ComboBoxModel model = new DefaultComboBoxModel(accounts.toArray());
			comboBoxAccount.setModel(model);
		}

	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	@SuppressWarnings("unchecked")
	public Object doWork(Object[] params, JLabel labelInfo) {
		String errorString = null;
		try {
			MrKontiOrg mrKonti = null;
			List reportLines;
			LkKontraktobjekterDAO lkKontraktobjekterDAO = (LkKontraktobjekterDAO) ModelUtil
					.getBean("lkKontraktobjekterDAO");
			AvdelingDAO avdelingDAO = (AvdelingDAO) ModelUtil
					.getBean("avdelingDAO");
			SpeiletBetingelseDAO speiletBetingelseDAO = (SpeiletBetingelseDAO) ModelUtil
					.getBean("speiletBetingelseDAO");

			if (comboBoxAccount.getSelectedIndex() != 0) {
				mrKonti = (MrKontiOrg) comboBoxAccount.getSelectedItem();
			}

			List<Integer> departmentIds = avdelingDAO.findAllId(null, null);
			List<String> idStrings = getDepIdsAsStrings(departmentIds);

			List<Integer> contractIds = lkKontraktobjekterDAO
					.findAllIdByMrKonti(mrKonti, idStrings);
			List<Integer> speiletBetingelser = speiletBetingelseDAO
					.findAllKontraktObjektIder();
			List<Object> reportContractIds;

			if (checkBoxMirror.isSelected()) {
				reportContractIds = ApplicationUtil.getUnion(contractIds,
						speiletBetingelser);
			} else {
				reportContractIds = ApplicationUtil.getDiff(contractIds,
						speiletBetingelser);
			}
			reportLines = lkKontraktobjekterDAO.findByIds(reportContractIds);
			reportFrame.loadData(reportLines);
		} catch (RuntimeException ex) {
			errorString = ex.getMessage();
		}
		return errorString;
	}

	private List<String> getDepIdsAsStrings(List<Integer> departmentIds) {
		List<String> idStrings=new ArrayList<String>();
		if(departmentIds!=null){
		for(Integer id:departmentIds){
			idStrings.add(String.valueOf(id));
		}
		}
		return idStrings;
	}

}
