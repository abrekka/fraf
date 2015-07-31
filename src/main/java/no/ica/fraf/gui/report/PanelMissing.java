package no.ica.fraf.gui.report;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import no.ica.fraf.dao.MangelTypeDAO;
import no.ica.fraf.dao.view.AvdelingMangelVDAO;
import no.ica.fraf.enums.ReportEnum;
import no.ica.fraf.model.AvdelingMangelV;
import no.ica.fraf.model.MangelType;
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
 * Panel for utvalg ved rapport over mangler for avdelinger
 * 
 * @author abr99
 * 
 */
public class PanelMissing extends AbstractReportPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private JComboBox comboBoxMissing;

    /**
     * @param aReportFrame
     */
    public PanelMissing(ReportFrame aReportFrame) {
        super(aReportFrame,ReportEnum.REPORT_MISSING);
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
                    labelMising.setText("Mangel:");
                }
                {
                    comboBoxMissing = new JComboBox();
                    panelCenter.add(comboBoxMissing);
                    comboBoxMissing.setPreferredSize(new java.awt.Dimension(
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
        MangelTypeDAO mangelTypeDAO = (MangelTypeDAO) ModelUtil
                .getBean("mangelTypeDAO");
        List<MangelType> mangler = mangelTypeDAO.findAll();

        if (mangler != null) {
            mangler.add(0,new MangelType("alle",null,null));
            ComboBoxModel model = new DefaultComboBoxModel(mangler.toArray());
            comboBoxMissing.setModel(model);
        }

    }

    /**
     * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
     *      javax.swing.JLabel)
     */
    public Object doWork(Object[] params, JLabel labelInfo) {
        MangelType mangelType = null;
        if (comboBoxMissing.getSelectedIndex() > 0) {
            mangelType = (MangelType) comboBoxMissing.getSelectedItem();
        }
        AvdelingMangelVDAO avdelingMangelVDAO = (AvdelingMangelVDAO) ModelUtil
                .getBean("avdelingMangelVDAO");
        List<AvdelingMangelV> reportLines = avdelingMangelVDAO
                .findMangler(mangelType);
        reportFrame.loadData(reportLines);
        return Boolean.TRUE;
    }

}
