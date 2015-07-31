package no.ica.fraf.gui.report;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.dao.SpeiletBetingelseDAO;
import no.ica.fraf.dao.fenistra.LkKontraktobjekterDAO;
import no.ica.fraf.enums.ReportEnum;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.LkKontraktobjekter;
import no.ica.fraf.model.SpeiletBetingelse;
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
 * Pnel for filtrering for rapport speilede betingelser
 * 
 * @author abr99
 * 
 */
public class PanelMirror extends AbstractReportPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JComboBox comboBoxCondition;

	/**
	 * @param aReportFrame
	 */
	public PanelMirror(ReportFrame aReportFrame) {
		super(aReportFrame, ReportEnum.REPORT_MIRROR);
	}

	/**
	 * @see no.ica.fraf.gui.report.AbstractReportPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
		super.initGUI();
		try {
			{
				JLabel labelCondition = new JLabel();
				panelCenter.add(labelCondition);
				labelCondition.setText("Betingelse:");
				labelCondition.setBounds(343, 46, 60, 30);
			}
			{
				comboBoxCondition = new JComboBox();
				panelCenter.add(comboBoxCondition);
				comboBoxCondition.setPreferredSize(new java.awt.Dimension(120,
						25));
			}
			this.setPreferredSize(new java.awt.Dimension(700, 72));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see no.ica.fraf.gui.report.AbstractReportPanel#initData()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void initData() {
		BetingelseTypeDAO betingelseTypeDAO = (BetingelseTypeDAO) ModelUtil
				.getBean("betingelseTypeDAO");
		List list = betingelseTypeDAO.findAllFranchise();

		if (list != null) {
			list.add(0, "alle");
			ComboBoxModel model = new DefaultComboBoxModel(list.toArray());
			comboBoxCondition.setModel(model);
		}

	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	@SuppressWarnings("unchecked")
    public Object doWork(Object[] params, JLabel labelInfo) {
		BetingelseType betingelseType = null;
		if (comboBoxCondition.getSelectedIndex() > 0) {
			betingelseType = (BetingelseType) comboBoxCondition
					.getSelectedItem();
		}
		List<SpeiletBetingelse> reportLines;

		SpeiletBetingelseDAO speiletBetingelseDAO = (SpeiletBetingelseDAO) ModelUtil
				.getBean("speiletBetingelseDAO");
		if (betingelseType == null) {
			reportLines = speiletBetingelseDAO.findAll();
		} else {
			reportLines = speiletBetingelseDAO
					.findByBetingelseType(betingelseType);
		}
        
        setLkKontraktobjekter(reportLines);

		reportFrame.loadData(reportLines);
		return Boolean.TRUE;
	}
    
    /**
     * Setter kontrakt fra Fenistra på speilede betingelser
     * @param betingelser
     * @return speilede betingelser
     */
    private List<SpeiletBetingelse>setLkKontraktobjekter(List<SpeiletBetingelse> betingelser){
        LkKontraktobjekterDAO lkKontraktobjekterDAO = (LkKontraktobjekterDAO)ModelUtil.getBean("lkKontraktobjekterDAO");
        LkKontraktobjekter lkKontraktobjekter = null;
        for(SpeiletBetingelse speiletBetingelse:betingelser){
            lkKontraktobjekter = lkKontraktobjekterDAO.getLkKontraktobjekter(speiletBetingelse.getKontraktObjektId());
            speiletBetingelse.setLkKontraktobjekter(lkKontraktobjekter);
        }
        return betingelser;
    }

}
