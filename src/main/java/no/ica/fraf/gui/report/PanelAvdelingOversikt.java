package no.ica.fraf.gui.report;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import no.ica.fraf.dao.view.AvdelingOversiktVDAO;
import no.ica.fraf.enums.ReportEnum;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Chain;
import no.ica.fraf.model.Rik2KjedeV;
import no.ica.fraf.util.DataListUtil;
import no.ica.fraf.util.DataListUtilFactory;
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
 * Panel som henter ut budsjettert omsetning for avdeling
 * 
 * @author abr99
 * 
 */
public class PanelAvdelingOversikt extends AbstractReportPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private JComboBox comboBoxChain;

    /**
     * 
     */
    private JComboBox comboBoxBook;

    /**
     * 
     */
    private JComboBox comboBoxStatus;

    

    /**
     * 
     */
    private JYearChooser yearChooser;

    /**
     * Konstruktør
     * 
     * @param aReportFrame
     */
    public PanelAvdelingOversikt(ReportFrame aReportFrame) {
        super(aReportFrame,ReportEnum.REPORT_AVDELING_OVERSIKT);
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

        DataListUtil dataListUtil=DataListUtilFactory.getInstance(new ModelUtil());
        List<BokfSelskap> selskapRegnskap = dataListUtil.getBokfSelskaper();

        if (selskapRegnskap != null) {
            selskapRegnskap.add(0,new BokfSelskap("alle"));
            ComboBoxModel model = new DefaultComboBoxModel(selskapRegnskap
                    .toArray());
            comboBoxBook.setModel(model);
        }

        List<Chain> kjeder = dataListUtil.getKjeder();

        if (kjeder != null) {
            kjeder.add(0,new Rik2KjedeV(null,"alle",null));
            ComboBoxModel chainModel = new DefaultComboBoxModel(kjeder
                    .toArray());
            comboBoxChain.setModel(chainModel);
        }
    }

    /**
     * @see no.ica.fraf.gui.report.AbstractReportPanel#initGUI()
     */
    @Override
    protected void initGUI() {
        super.initGUI();
        try {
            this.setPreferredSize(new java.awt.Dimension(679, 129));
            {
                GridBagLayout panelCenterLayout = new GridBagLayout();
                panelCenter.setLayout(panelCenterLayout);
                this.add(panelCenter, BorderLayout.CENTER);
                {
                    JLabel labelStatus = new JLabel();
                    panelCenter.add(labelStatus, new GridBagConstraints(1, 1,
                            1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                            GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
                            0));
                    labelStatus.setText("Status:");
                }
                {
                    comboBoxStatus = new JComboBox();
                    panelCenter.add(comboBoxStatus, new GridBagConstraints(2,
                            1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                            GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
                            0));
                    comboBoxStatus.setPreferredSize(new java.awt.Dimension(100,
                            20));
                }
                {
                    JLabel labelBook = new JLabel();
                    panelCenter.add(labelBook, new GridBagConstraints(3, 1, 1,
                            1, 0.0, 0.0, GridBagConstraints.CENTER,
                            GridBagConstraints.NONE, new Insets(0, 10, 0, 0),
                            0, 0));
                    labelBook.setText("Bokf. selskap:");
                }
                {
                    comboBoxBook = new JComboBox();
                    panelCenter.add(comboBoxBook, new GridBagConstraints(4, 1,
                            1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                            GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
                            0));
                    comboBoxBook.setPreferredSize(new java.awt.Dimension(100,
                            20));
                }
                {
                    JLabel labelChain = new JLabel();
                    panelCenter.add(labelChain, new GridBagConstraints(5, 1, 1,
                            1, 0.0, 0.0, GridBagConstraints.CENTER,
                            GridBagConstraints.NONE, new Insets(0, 10, 0, 0),
                            0, 0));
                    labelChain.setText("Kjede:");
                }
                {
                    comboBoxChain = new JComboBox();
                    panelCenter.add(comboBoxChain, new GridBagConstraints(6, 1,
                            1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                            GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
                            0));
                    comboBoxChain.setPreferredSize(new java.awt.Dimension(150,
                            20));
                }
                {
                    JLabel labelYear = new JLabel();
                    panelCenter.add(labelYear, new GridBagConstraints(7, 1, 1,
                            1, 0.0, 0.0, GridBagConstraints.CENTER,
                            GridBagConstraints.NONE, new Insets(0, 10, 0, 0),
                            0, 0));
                    labelYear.setText("År:");
                }
                {
                    yearChooser = new JYearChooser();
                    panelCenter.add(yearChooser, new GridBagConstraints(8, 1,
                            1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                            GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
                            0));
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
        String selectedStatus = null;
        BokfSelskap bokfSelskap = null;
        Rik2KjedeV rik2KjedeV = null;

        if (comboBoxStatus.getSelectedIndex() > 0) {
            selectedStatus = (String) comboBoxStatus.getSelectedItem();
        }

        if (comboBoxBook.getSelectedIndex() > 0) {
            bokfSelskap = (BokfSelskap) comboBoxBook.getSelectedItem();
        }

        if (comboBoxChain.getSelectedIndex() > 0) {
            rik2KjedeV = (Rik2KjedeV) comboBoxChain.getSelectedItem();
        }

        AvdelingOversiktVDAO avdelingOversiktVDAO = (AvdelingOversiktVDAO) ModelUtil
        .getBean("avdelingOversiktVDAO");
        List reportLines = avdelingOversiktVDAO
                .findByStatusBokfSelskapChainYear(selectedStatus, bokfSelskap,
                        rik2KjedeV, new Integer(yearChooser.getYear()));
        reportFrame.loadData(reportLines);
        return Boolean.TRUE;
    }

}
