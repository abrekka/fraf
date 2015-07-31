package no.ica.fraf.gui.report;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import no.ica.fraf.dao.SikkerhetTypeDAO;
import no.ica.fraf.dao.view.AvdelingSikkerhetVDAO;
import no.ica.fraf.enums.ReportEnum;
import no.ica.fraf.model.AvdelingSikkerhetV;
import no.ica.fraf.model.SikkerhetType;
import no.ica.fraf.util.ModelUtil;

/**
 * Panel for utvalg ved rapporten sikkerhet
 * @author abr99
 *
 */
public class PanelSecurity extends AbstractReportPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private JComboBox comboBoxSecurity;

    /**
     * @param aReportFrame
     */
    public PanelSecurity(ReportFrame aReportFrame) {
        super(aReportFrame, ReportEnum.REPORT_SECURITY);
    }

    /**
     * @see no.ica.fraf.gui.report.AbstractReportPanel#initGUI()
     */
    @Override
    protected void initGUI() {
        super.initGUI();
        try {
            this.setPreferredSize(new java.awt.Dimension(400, 74));
            {
                {
                    JLabel labelMising = new JLabel();
                    panelCenter.add(labelMising);
                    labelMising.setText("Sikkerhet:");
                }
                {
                    comboBoxSecurity = new JComboBox();
                    panelCenter.add(comboBoxSecurity);
                    comboBoxSecurity.setPreferredSize(new java.awt.Dimension(
                            120, 25));
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
        SikkerhetTypeDAO sikkerhetTypeDAO = (SikkerhetTypeDAO) ModelUtil
                .getBean("sikkerhetTypeDAO");
        List<SikkerhetType> sikkerhet = sikkerhetTypeDAO.findAll();

        if (sikkerhet != null) {
            sikkerhet.add(0,new SikkerhetType(null,"alle"));
            ComboBoxModel model = new DefaultComboBoxModel(sikkerhet.toArray());
            comboBoxSecurity.setModel(model);
        }

    }

    /**
     * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[], javax.swing.JLabel)
     */
    public Object doWork(Object[] params, JLabel labelInfo) {
        SikkerhetType sikkerhetType = null;
        if (comboBoxSecurity.getSelectedIndex() > 0) {
            sikkerhetType = (SikkerhetType) comboBoxSecurity.getSelectedItem();
        }
        AvdelingSikkerhetVDAO avdelingSikkerhetVDAO = (AvdelingSikkerhetVDAO) ModelUtil
                .getBean("avdelingSikkerhetVDAO");
        List<AvdelingSikkerhetV> reportLines = avdelingSikkerhetVDAO
                .findSikkerhet(sikkerhetType);
        reportFrame.loadData(reportLines);
        return Boolean.TRUE;
    }

}
