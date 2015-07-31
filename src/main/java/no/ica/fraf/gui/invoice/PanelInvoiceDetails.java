package no.ica.fraf.gui.invoice;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.TableColumn;

import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.enums.LazyLoadFakturaEnum;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

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
 * Panel som viser detaljer om en faktura
 * 
 * @author abr99
 * 
 */
public class PanelInvoiceDetails extends javax.swing.JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private JPanel panelInvoiceDetails;

    /**
     * 
     */
    private JScrollPane scrollPaneCalculation;

    /**
     * 
     */
    private JPanel panelCalculation;

    /**
     * 
     */
    private JTextField textFieldPeriodeTo;

    /**
     * 
     */
    private JTextField textFieldPeriodeFrom;

    /**
     * 
     */
    private JLabel labelPeriode;

    /**
     * 
     */
    private JTextField textFieldYear;

    /**
     * 
     */
    private JLabel labelYear;

    /**
     * 
     */
    private JTextField textFieldCreated;

    /**
     * 
     */
    private JLabel labelCreated;

    /**
     * 
     */
    private JTextField textFieldDueDate;

    /**
     * 
     */
    private JLabel labelDueDate;

    /**
     * 
     */
    private JTextField textFieldInvoiceDate;

    /**
     * 
     */
    private JTextField textFieldAddress1;

    /**
     * 
     */
    private JLabel labelInvoiceDate;

    /**
     * 
     */
    private JTextField textFieldInvoiceNr;

    /**
     * 
     */
    private JLabel labelInvoiceNr;

    /**
     * 
     */
    private JTextField textFieldDepId;

    /**
     * 
     */
    private JLabel labelDepId;

    /**
     * 
     */
    private JTextField textFieldInvoiceAmount;

    /**
     * 
     */
    private JLabel labelInvoiceAmount;

    /**
     * 
     */
    private JTextField textFieldVat;

    /**
     * 
     */
    private JLabel labelVat;

    /**
     * 
     */
    private JTextField textFieldSumInvoice;

    /**
     * 
     */
    private JLabel labelSumInvoice;

    /**
     * 
     */
    private JTextField textFieldBasis;

    /**
     * 
     */
    private JLabel labelBasis;

    /**
     * 
     */
    private JPanel panelSum;

    /**
     * 
     */
    private JTextField textFieldInvoiceText;

    /**
     * 
     */
    private JTable tableCalculation;

    /**
     * 
     */
    private JPanel panelDetails;

    /**
     * 
     */
    private JPanel panelInvoicee;

    /**
     * 
     */
    private JTextField textFieldPostalDistrict;

    /**
     * 
     */
    private JTextField textFieldPostalCode;

    /**
     * 
     */
    private JTextField textFieldAddress2;

    /**
     * 
     */
    private JTextField textFieldName;

    /**
     * TableModel for tabell
     */
    private ObjectTableModel invoiceTextTableModel;

    /**
     * DAO for faktura
     */
    private static FakturaDAO fakturaDAO;

    /**
     * Kontruktør
     * 
     * @param faktura
     */
    public PanelInvoiceDetails(Faktura faktura) {
        super();

        if (fakturaDAO == null) {
            fakturaDAO = (FakturaDAO) ModelUtil.getBean("fakturaDAO");
        }

        initGUI();
        initTable(faktura);
        initData(faktura);
    }

    /**
     * Initierer tabell
     * 
     * @param faktura
     */
    private void initTable(Faktura faktura) {
        if (invoiceTextTableModel == null) {
            String[] columnNames = { "", "" };
            String[] methods = { "FakturaTekst", "Belop" };
            Class[] params = { String.class, BigDecimal.class };
            ObjectTableDef invoiceTextTableDef = new ObjectTableDef(
                    columnNames, methods, params);

            invoiceTextTableModel = new ObjectTableModel(invoiceTextTableDef);
            invoiceTextTableModel.setEditable(false);
            tableCalculation.setModel(invoiceTextTableModel);

            // Fakturatekst
            TableColumn col = tableCalculation.getColumnModel().getColumn(0);
            col.setPreferredWidth(190);

            // Beløp
            col = tableCalculation.getColumnModel().getColumn(1);
            col.setPreferredWidth(140);

            if (faktura != null) {

                fakturaDAO.loadLazy(faktura,
                        new LazyLoadFakturaEnum[]{LazyLoadFakturaEnum.LOAD_INVOICE_TEXT});
                fakturaDAO.lazyLoadBunt(faktura);
                Set<?> invoiceText = faktura.getFakturaTeksts();
                invoiceTextTableModel.setData(invoiceText);

            }
        }
    }

    /**
     * Laster data
     * 
     * @param faktura
     */
    private void initData(Faktura faktura) {
        textFieldName.setText(faktura.getMottakerNavn());
        textFieldAddress1.setText(faktura.getAdresse1());
        textFieldAddress2.setText(faktura.getAdresse2());
        textFieldPostalCode.setText(faktura.getPostnr());
        textFieldPostalDistrict.setText(faktura.getPoststed());

        if (faktura.getAvdnr() != null) {
            textFieldDepId.setText(faktura.getAvdnr().toString());
        }

        textFieldInvoiceNr.setText(faktura.getFakturaNr());

        if (faktura.getFakturaDato() != null) {
            textFieldInvoiceDate.setText(GuiUtil.SIMPLE_DATE_FORMAT
                    .format(faktura.getFakturaDato()));
        }

        if (faktura.getForfallDato() != null) {
            textFieldDueDate.setText(GuiUtil.SIMPLE_DATE_FORMAT.format(faktura
                    .getForfallDato()));
        }

        if (faktura.getOpprettetDato() != null) {
            textFieldCreated.setText(GuiUtil.SIMPLE_DATE_FORMAT.format(faktura
                    .getOpprettetDato()));
        }

        if (faktura.getAar() != null) {
            textFieldYear.setText(faktura.getAar().toString());
        }

        if (faktura.getFraPeriode() != null) {
            textFieldPeriodeFrom.setText(faktura.getFraPeriode().toString());
        }

        if (faktura.getTilPeriode() != null) {
            textFieldPeriodeTo.setText(faktura.getTilPeriode().toString());
        }

        textFieldInvoiceText.setText(faktura.getFakturaTittel());

        if (faktura.getGrunnlagBelop() != null) {
            textFieldBasis.setText(faktura.getGrunnlagBelop().toString());
        }

        if (faktura.getBelop() != null) {
            textFieldSumInvoice.setText(faktura.getBelop().toString());
        }

        if (faktura.getMvaBelop() != null) {
            textFieldVat.setText(faktura.getMvaBelop().toString());
        }

        if (faktura.getTotalBelop() != null) {
            textFieldInvoiceAmount.setText(faktura.getTotalBelop().toString());
        }
    }

    /**
     * Initierer GUI
     */
    private void initGUI() {
        try {
            BorderLayout thisLayout = new BorderLayout();
            this.setLayout(thisLayout);
            this.setPreferredSize(new java.awt.Dimension(617, 312));
            Border titledBorder = BorderFactory.createTitledBorder("Faktura");
            Border bevelBorder = BorderFactory
                    .createEtchedBorder(BevelBorder.LOWERED);
            this.setBorder(BorderFactory.createCompoundBorder(bevelBorder,
                    titledBorder));
            {
                panelInvoiceDetails = new JPanel();
                BorderLayout panelInvoiceDetailsLayout = new BorderLayout();
                panelInvoiceDetails.setLayout(panelInvoiceDetailsLayout);
                this.add(panelInvoiceDetails, BorderLayout.WEST);
                panelInvoiceDetails.setPreferredSize(new java.awt.Dimension(
                        220, 10));
                {
                    panelInvoicee = new JPanel();
                    GridBagLayout panelInvoiceeLayout = new GridBagLayout();
                    panelInvoicee.setLayout(panelInvoiceeLayout);
                    panelInvoiceDetails.add(panelInvoicee, BorderLayout.NORTH);
                    panelInvoicee.setPreferredSize(new java.awt.Dimension(250,
                            110));
                    panelInvoicee.setBorder(BorderFactory
                            .createTitledBorder("Adressat"));
                    {
                        textFieldName = new JTextField();
                        panelInvoicee.add(textFieldName,
                                new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
                                        GridBagConstraints.CENTER,
                                        GridBagConstraints.NONE, new Insets(0,
                                                0, 0, 0), 0, 0));
                        textFieldName.setPreferredSize(new java.awt.Dimension(
                                200, 20));
                        textFieldName.setEnabled(false);
                        textFieldName.setMinimumSize(new java.awt.Dimension(
                                200, 20));
                    }
                    {
                        textFieldAddress1 = new JTextField();
                        panelInvoicee.add(textFieldAddress1,
                                new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0,
                                        GridBagConstraints.CENTER,
                                        GridBagConstraints.HORIZONTAL,
                                        new Insets(0, 0, 0, 0), 0, 0));
                        textFieldAddress1.setEnabled(false);
                    }
                    {
                        textFieldAddress2 = new JTextField();
                        panelInvoicee.add(textFieldAddress2,
                                new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0,
                                        GridBagConstraints.CENTER,
                                        GridBagConstraints.HORIZONTAL,
                                        new Insets(0, 0, 0, 0), 0, 0));
                        textFieldAddress2.setEnabled(false);
                    }
                    {
                        textFieldPostalCode = new JTextField();
                        panelInvoicee.add(textFieldPostalCode,
                                new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                                        GridBagConstraints.WEST,
                                        GridBagConstraints.HORIZONTAL,
                                        new Insets(0, 0, 0, 0), 0, 0));
                        textFieldPostalCode
                                .setPreferredSize(new java.awt.Dimension(60, 20));
                        textFieldPostalCode
                                .setMinimumSize(new java.awt.Dimension(60, 20));
                        textFieldPostalCode.setEnabled(false);
                    }
                    {
                        textFieldPostalDistrict = new JTextField();
                        panelInvoicee.add(textFieldPostalDistrict,
                                new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                                        GridBagConstraints.WEST,
                                        GridBagConstraints.HORIZONTAL,
                                        new Insets(0, 5, 0, 0), 0, 0));
                        textFieldPostalDistrict.setEnabled(false);
                        textFieldPostalDistrict
                                .setPreferredSize(new java.awt.Dimension(135,
                                        20));
                    }
                }
                {
                    panelDetails = new JPanel();
                    GridBagLayout panelDetailsLayout = new GridBagLayout();
                    panelDetails.setLayout(panelDetailsLayout);
                    panelInvoiceDetails.add(panelDetails, BorderLayout.CENTER);
                    panelDetails.setBorder(BorderFactory
                            .createTitledBorder("Detaljer"));
                    {
                        labelDepId = new JLabel();
                        panelDetails.add(labelDepId, new GridBagConstraints(0,
                                0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                                GridBagConstraints.NONE,
                                new Insets(0, 0, 0, 0), 0, 0));
                        labelDepId.setText("Avdnr:");
                    }
                    {
                        textFieldDepId = new JTextField();
                        panelDetails.add(textFieldDepId,
                                new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                                        GridBagConstraints.WEST,
                                        GridBagConstraints.NONE, new Insets(0,
                                                5, 0, 0), 0, 0));
                        textFieldDepId.setPreferredSize(new java.awt.Dimension(
                                50, 20));
                        textFieldDepId.setEnabled(false);
                        textFieldDepId.setMinimumSize(new java.awt.Dimension(
                                50, 20));
                    }
                    {
                        labelInvoiceNr = new JLabel();
                        panelDetails.add(labelInvoiceNr,
                                new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                                        GridBagConstraints.WEST,
                                        GridBagConstraints.NONE, new Insets(1,
                                                0, 0, 0), 0, 0));
                        labelInvoiceNr.setText("Fakturanr:");
                    }
                    {
                        textFieldInvoiceNr = new JTextField();
                        panelDetails.add(textFieldInvoiceNr,
                                new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
                                        GridBagConstraints.WEST,
                                        GridBagConstraints.NONE, new Insets(1,
                                                5, 0, 0), 0, 0));
                        textFieldInvoiceNr
                                .setPreferredSize(new java.awt.Dimension(100,
                                        20));
                        textFieldInvoiceNr.setEnabled(false);
                        textFieldInvoiceNr
                                .setMinimumSize(new java.awt.Dimension(100, 20));
                    }
                    {
                        labelInvoiceDate = new JLabel();
                        panelDetails.add(labelInvoiceDate,
                                new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                                        GridBagConstraints.WEST,
                                        GridBagConstraints.NONE, new Insets(1,
                                                0, 0, 0), 0, 0));
                        labelInvoiceDate.setText("Fakturadato:");
                    }
                    {
                        textFieldInvoiceDate = new JTextField();
                        panelDetails.add(textFieldInvoiceDate,
                                new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0,
                                        GridBagConstraints.WEST,
                                        GridBagConstraints.HORIZONTAL,
                                        new Insets(1, 5, 0, 0), 0, 0));
                        textFieldInvoiceDate.setEnabled(false);
                    }
                    {
                        labelDueDate = new JLabel();
                        panelDetails.add(labelDueDate, new GridBagConstraints(
                                0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                                GridBagConstraints.NONE,
                                new Insets(1, 0, 0, 0), 0, 0));
                        labelDueDate.setText("Forfallsdato:");
                    }
                    {
                        textFieldDueDate = new JTextField();
                        panelDetails.add(textFieldDueDate,
                                new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0,
                                        GridBagConstraints.WEST,
                                        GridBagConstraints.HORIZONTAL,
                                        new Insets(1, 5, 0, 0), 0, 0));
                        textFieldDueDate.setEnabled(false);
                    }
                    {
                        labelCreated = new JLabel();
                        panelDetails.add(labelCreated, new GridBagConstraints(
                                0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                                GridBagConstraints.NONE,
                                new Insets(1, 0, 0, 0), 0, 0));
                        labelCreated.setText("Opprettet:");
                    }
                    {
                        textFieldCreated = new JTextField();
                        panelDetails.add(textFieldCreated,
                                new GridBagConstraints(1, 4, 2, 1, 0.0, 0.0,
                                        GridBagConstraints.WEST,
                                        GridBagConstraints.HORIZONTAL,
                                        new Insets(1, 5, 0, 0), 0, 0));
                        textFieldCreated.setEnabled(false);
                    }
                    {
                        labelYear = new JLabel();
                        panelDetails.add(labelYear, new GridBagConstraints(0,
                                5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                                GridBagConstraints.NONE,
                                new Insets(1, 0, 0, 0), 0, 0));
                        labelYear.setText("År:");
                    }
                    {
                        textFieldYear = new JTextField();
                        panelDetails.add(textFieldYear, new GridBagConstraints(
                                1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                                GridBagConstraints.HORIZONTAL, new Insets(1, 5,
                                        0, 0), 0, 0));
                        textFieldYear.setEnabled(false);
                    }
                    {
                        labelPeriode = new JLabel();
                        panelDetails.add(labelPeriode, new GridBagConstraints(
                                0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                                GridBagConstraints.NONE,
                                new Insets(1, 0, 0, 0), 0, 0));
                        labelPeriode.setText("Fra/til periode:");
                    }
                    {
                        textFieldPeriodeFrom = new JTextField();
                        panelDetails.add(textFieldPeriodeFrom,
                                new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                                        GridBagConstraints.WEST,
                                        GridBagConstraints.HORIZONTAL,
                                        new Insets(1, 5, 0, 0), 0, 0));
                        textFieldPeriodeFrom.setEnabled(false);
                    }
                    {
                        textFieldPeriodeTo = new JTextField();
                        panelDetails.add(textFieldPeriodeTo,
                                new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
                                        GridBagConstraints.WEST,
                                        GridBagConstraints.HORIZONTAL,
                                        new Insets(1, 5, 0, 0), 0, 0));
                        textFieldPeriodeTo.setEnabled(false);
                    }
                }
            }
            {
                panelCalculation = new JPanel();
                BorderLayout panelCalculationLayout = new BorderLayout();
                panelCalculation.setLayout(panelCalculationLayout);
                this.add(panelCalculation, BorderLayout.CENTER);
                panelCalculation.setBorder(BorderFactory
                        .createTitledBorder("Avgiftsberegning"));
                {
                    scrollPaneCalculation = new JScrollPane();
                    panelCalculation.add(scrollPaneCalculation,
                            BorderLayout.CENTER);
                    {
                        tableCalculation = new JTable();
                        scrollPaneCalculation.setViewportView(tableCalculation);
                        tableCalculation
                                .setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    }
                }
                {
                    textFieldInvoiceText = new JTextField();
                    panelCalculation.add(textFieldInvoiceText,
                            BorderLayout.NORTH);
                    textFieldInvoiceText.setEnabled(false);
                }
                {
                    panelSum = new JPanel();
                    GridBagLayout panelSumLayout = new GridBagLayout();
                    panelSum.setLayout(panelSumLayout);
                    panelCalculation.add(panelSum, BorderLayout.SOUTH);
                    panelSum.setPreferredSize(new java.awt.Dimension(10, 40));
                    {
                        labelBasis = new JLabel();
                        panelSum.add(labelBasis, new GridBagConstraints(0, 0,
                                1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                                GridBagConstraints.NONE,
                                new Insets(0, 0, 0, 0), 0, 0));
                        labelBasis.setText("Grunnlag");
                    }
                    {
                        textFieldBasis = new JTextField();
                        panelSum.add(textFieldBasis, new GridBagConstraints(0,
                                1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                                GridBagConstraints.NONE,
                                new Insets(0, 0, 0, 0), 0, 0));
                        textFieldBasis.setPreferredSize(new java.awt.Dimension(
                                80, 20));
                        textFieldBasis.setMinimumSize(new java.awt.Dimension(
                                80, 20));
                        textFieldBasis.setEnabled(false);
                    }
                    {
                        labelSumInvoice = new JLabel();
                        panelSum.add(labelSumInvoice, new GridBagConstraints(1,
                                0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                                GridBagConstraints.NONE,
                                new Insets(0, 5, 0, 0), 0, 0));
                        labelSumInvoice.setText("Sum faktura");
                    }
                    {
                        textFieldSumInvoice = new JTextField();
                        panelSum.add(textFieldSumInvoice,
                                new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                                        GridBagConstraints.WEST,
                                        GridBagConstraints.NONE, new Insets(0,
                                                5, 0, 0), 0, 0));
                        textFieldSumInvoice
                                .setPreferredSize(new java.awt.Dimension(80, 20));
                        textFieldSumInvoice
                                .setMinimumSize(new java.awt.Dimension(80, 20));
                        textFieldSumInvoice.setEnabled(false);
                    }
                    {
                        labelVat = new JLabel();
                        panelSum.add(labelVat, new GridBagConstraints(2, 0, 1,
                                1, 0.0, 0.0, GridBagConstraints.WEST,
                                GridBagConstraints.NONE,
                                new Insets(0, 5, 0, 0), 0, 0));
                        labelVat.setText("Merverdiavgift");
                    }
                    {
                        textFieldVat = new JTextField();
                        panelSum.add(textFieldVat, new GridBagConstraints(2, 1,
                                1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                                GridBagConstraints.NONE,
                                new Insets(0, 5, 0, 0), 0, 0));
                        textFieldVat.setPreferredSize(new java.awt.Dimension(
                                80, 20));
                        textFieldVat.setMinimumSize(new java.awt.Dimension(80,
                                20));
                        textFieldVat.setEnabled(false);
                    }
                    {
                        labelInvoiceAmount = new JLabel();
                        panelSum.add(labelInvoiceAmount,
                                new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                                        GridBagConstraints.WEST,
                                        GridBagConstraints.NONE, new Insets(0,
                                                5, 0, 0), 0, 0));
                        labelInvoiceAmount.setText("Fakturabeløp");
                    }
                    {
                        textFieldInvoiceAmount = new JTextField();
                        panelSum.add(textFieldInvoiceAmount,
                                new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                                        GridBagConstraints.WEST,
                                        GridBagConstraints.NONE, new Insets(0,
                                                5, 0, 0), 0, 0));
                        textFieldInvoiceAmount
                                .setPreferredSize(new java.awt.Dimension(80, 20));
                        textFieldInvoiceAmount
                                .setMinimumSize(new java.awt.Dimension(80, 20));
                        textFieldInvoiceAmount.setEnabled(false);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
