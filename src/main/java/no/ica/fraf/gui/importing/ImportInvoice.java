package no.ica.fraf.gui.importing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import no.ica.fraf.common.Batchable;
import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.dao.BuntTypeDAO;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.dao.FakturaLinjeDAO;
import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.dao.view.AvdelingVDAO;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingV;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntStatus;
import no.ica.fraf.model.BuntType;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.model.Mva;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.apache.log4j.Logger;

/**
 * Håndterer import av faktura
 * 
 * @author abr99
 * 
 */
public class ImportInvoice extends AbstractImportInterface {
    /**
     * Kolonnenavn for fakturatabell
     */
    private static final String[] COLUMN_NAMES_INVOICE = { "Avdeling",
            "Fakturanr", "Gruppe", "Fakturatittel", "Fakturadato",
            "Forfallsdato", "Fakturabeløp", "År", "Fra periode", "Til periode",
            "Opprettet" };

    /**
     * Kolonnemetoder for budsjettabell
     */
    private static final String[] METHODS_INVOICE = { "Avdeling", "FakturaNr",
            "BetingelseGruppe", "FakturaTittel", "FakturaDato", "ForfallDato",
            "TotalBelop", "Aar", "FraPeriode", "TilPeriode", "OpprettetDato" };

    /**
     * Klassetyper for budsjettabell
     */
    private static final Class[] PARAMS_INVOICE = { Avdeling.class,
            Integer.class, BetingelseGruppe.class, String.class, Date.class,
            Date.class, BigDecimal.class, Integer.class, Integer.class,
            Integer.class, Date.class };

    /**
     * Kolonneformat for budsjettabell
     */
    private static final ColumnFormatEnum[] FORMAT_COLUMNS_INVOICE = new ColumnFormatEnum[] {
            ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
            ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
            ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
            ColumnFormatEnum.CURRENCY, ColumnFormatEnum.NONE,
            ColumnFormatEnum.NONE, ColumnFormatEnum.NONE, ColumnFormatEnum.NONE };

    /**
     * Def for fakturatabell
     */
    private static final ObjectTableDef INVOICE_TABLE_DEF = new ObjectTableDef(
            COLUMN_NAMES_INVOICE, METHODS_INVOICE, PARAMS_INVOICE,
            FORMAT_COLUMNS_INVOICE);

    /**
     * TableModel for fakturatabell
     */
    private ObjectTableModel<Object> invoiceTableModel = new ObjectTableModel<Object>(
            INVOICE_TABLE_DEF);

    /**
     * DAO for avdeling view
     */
    private AvdelingVDAO avdelingVDAO = (AvdelingVDAO) ModelUtil
            .getBean("avdelingVDAO");

    /**
     * DAO for bokføringsselakp
     */
    private BokfSelskapDAO bokfSelskapDAO = (BokfSelskapDAO) ModelUtil
            .getBean("bokfSelskapDAO");

    /**
     * DAO for betingelsetype
     */
    private BetingelseTypeDAO betingelseTypeDAO = (BetingelseTypeDAO) ModelUtil
            .getBean("betingelseTypeDAO");

    /**
     * DAO for mva
     */
    private MvaDAO mvaDAO = (MvaDAO) ModelUtil.getBean("mvaDAO");

    /**
     * DAO for faktura
     */
    private FakturaDAO fakturaDAO = (FakturaDAO) ModelUtil
            .getBean("fakturaDAO");

    /**
     * DAO for fakturalinje
     */
    private FakturaLinjeDAO fakturaLinjeDAO = (FakturaLinjeDAO) ModelUtil
            .getBean("fakturaLinjeDAO");

    /**
     * DAO for avdeling
     */
    private AvdelingDAO avdelingDAO = (AvdelingDAO) ModelUtil
            .getBean("avdelingDAO");

    /**
     * DAO for bunt
     */
    private BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");

    /**
     * Bruker
     */
    private ApplUser currentApplUser;

    /**
     * DAO for bunttype
     */
    private BuntTypeDAO buntTypeDAO = (BuntTypeDAO) ModelUtil
            .getBean("buntTypeDAO");

    /**
     * DAO for buntstatus
     */
    private BuntStatusDAO buntStatusDAO = (BuntStatusDAO) ModelUtil
            .getBean("buntStatusDAO");

    /**
     * Feil oppstått under import
     */
    private List<Object> feilList;

    /**
     * Kolonnenavn for importtabell
     */
    private static String[] columnNames = { "Avdnr", "Bruttobeløp", "Mvakode",
            "År", "Fra periode", "Til periode", "Betingelse", "Fakturanr",
            "Fakturadato", "Forfallsdato", "Selskap", "Tekst", "Fakturatekst",
            "Rekkefølge", "Feilmelding" };

    /**
     * Kolonnemetoder for importtabell
     */
    private static String[] methods = { "Avdnr", "Belop", "MvaKode", "Aar",
            "FraPeriode", "TilPeriode", "Betingelse", "Fakturanr",
            "FakturaDato", "ForfallsDato", "BokfSelskap", "Tekst",
            "FakturaTekst", "Rekkefolge", "Feilmelding" };

    /**
     * Klassetyper for importtabell
     */
    private static Class[] params = { Integer.class, BigDecimal.class,
            Mva.class, Integer.class, Integer.class, Integer.class,
            BetingelseType.class, String.class, Date.class, Date.class,
            BokfSelskap.class, String.class, String.class, Integer.class,
            String.class };

    /**
     * Def for importtabell
     */
    private static ObjectTableDef importTableDef = new ObjectTableDef(
            columnNames, methods, params);

    /**
     * TableModel for importtabell
     */
    private ObjectTableModel<InvoiceImport> importTableModel = new ObjectTableModel<InvoiceImport>(
            importTableDef);

    /**
     * Logger til database
     */
    private static Logger logger = Logger.getLogger(ImportInvoice.class);

    /**
     * @param applUser
     */
    public ImportInvoice(ApplUser applUser) {
        currentApplUser = applUser;
    }

    /**
     * @see no.ica.fraf.gui.importing.ImportInterface#getTableModelImport()
     */
    @SuppressWarnings("unchecked")
	public ObjectTableModel getTableModelImport() {
        return importTableModel;
    }

    /**
     * @see no.ica.fraf.gui.importing.ImportInterface#setColumnWidthsImport(javax.swing.JTable)
     */
    public void setColumnWidthsImport(JTable table) {
        // Avdnr
        TableColumn col = table.getColumnModel().getColumn(0);
        col.setPreferredWidth(50);

        // Bruttobeløp
        col = table.getColumnModel().getColumn(1);
        col.setPreferredWidth(150);

        // Mvakode
        col = table.getColumnModel().getColumn(2);
        col.setPreferredWidth(50);

        // År
        col = table.getColumnModel().getColumn(3);
        col.setPreferredWidth(50);

        // Fra periode
        col = table.getColumnModel().getColumn(4);
        col.setPreferredWidth(60);

        // Til periode
        col = table.getColumnModel().getColumn(5);
        col.setPreferredWidth(60);

        // Betingelse
        col = table.getColumnModel().getColumn(6);
        col.setPreferredWidth(80);

        // Fakturanr
        col = table.getColumnModel().getColumn(7);
        col.setPreferredWidth(100);

        // Fakturadato
        col = table.getColumnModel().getColumn(8);
        col.setPreferredWidth(100);

        // Forfallsdato
        col = table.getColumnModel().getColumn(9);
        col.setPreferredWidth(100);

        // Selskap
        col = table.getColumnModel().getColumn(10);
        col.setPreferredWidth(100);

        // Tekst
        col = table.getColumnModel().getColumn(11);
        col.setPreferredWidth(100);

        // Fakturatekst
        col = table.getColumnModel().getColumn(12);
        col.setPreferredWidth(100);

        // Rekkefølge
        col = table.getColumnModel().getColumn(13);
        col.setPreferredWidth(60);

        // Feilmelding
        col = table.getColumnModel().getColumn(14);
        col.setPreferredWidth(200);
    }

    /**
     * @see no.ica.fraf.gui.importing.ImportInterface#findAllImport()
     */
    public List<Object> findAllImport() {
        return feilList;
    }

    /**
     * @see no.ica.fraf.gui.importing.ImportInterface#doImport(boolean,
     *      java.io.File, boolean, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public Object doImport(boolean readFile, File file, boolean hasHead,
            Object param) {
        // ta hensyn til isdebit
        StringBuffer errorMsg = null;
        StringBuffer feilmelding = new StringBuffer();
        try {
            InvoiceImport invoiceImport;
            feilList = new ArrayList<Object>();
            Mva mva = null;
            BetingelseType betingelseType = null;
            Avdeling avdeling = null;

            if (readFile) {
                FileReader budgetFile = new FileReader(file);
                BufferedReader bufBudFile = new BufferedReader(budgetFile);
                String oneLine;
                String[] stringCol;
                int lineCount = 0;

                if (hasHead) {
                    bufBudFile.readLine(); // fjerner heading
                }

                while ((oneLine = bufBudFile.readLine()) != null) {
                    try {
                        lineCount++;
                        // Splitter linje i deler basert på semikolon
                        stringCol = oneLine.split(";");

                        if (stringCol == null || stringCol.length < 10) {
                            errorMsg = new StringBuffer("Linje ").append(
                                    lineCount).append(
                                    " inneholder for få kolonner.");
                        } else {
                            invoiceImport = new InvoiceImport();
                            invoiceImport.setAvdnr(new Integer(stringCol[0]));

                            avdeling = avdelingDAO.findByAvdnr(invoiceImport
                                    .getAvdnr());

                            if (avdeling != null) {
                                invoiceImport.setAvdeling(avdeling);
                            } else {
                                feilmelding.append(" Fant ikke avdeling");
                            }

                            invoiceImport.setBelop(new BigDecimal(stringCol[1]
                                    .replaceAll(",", ".").replaceAll(" ", "")));
                            invoiceImport.setMvaKode(stringCol[2]);
                            mva = mvaDAO.findByMvaKode(stringCol[2]);
                            if (mva != null) {
                                invoiceImport.setMva(mva);
                            } else {
                                feilmelding.append(" Fant ikke mva");
                            }
                            invoiceImport.setAar(new Integer(stringCol[3]));
                            invoiceImport.setFraPeriode(new Integer(
                                    stringCol[4]));
                            invoiceImport.setTilPeriode(new Integer(
                                    stringCol[5]));
                            invoiceImport.setBetingelse(stringCol[6]);
                            betingelseType = betingelseTypeDAO
                                    .findByKode(stringCol[6]);
                            if (betingelseType != null) {
                                invoiceImport.setBetingelseType(betingelseType);
                            } else {
                                feilmelding.append(" Fant ikke betingelse");
                            }
                            invoiceImport.setFakturanr(stringCol[7]);
                            invoiceImport
                                    .setFakturaDato(GuiUtil.SIMPLE_DATE_FORMAT
                                            .parse(stringCol[8]));
                            invoiceImport
                                    .setForfallsDato(GuiUtil.SIMPLE_DATE_FORMAT
                                            .parse(stringCol[9]));

                            if (stringCol.length >= 11
                                    && stringCol[10].length() != 0) {
                                invoiceImport.setBokfSelskap(bokfSelskapDAO
                                        .findByName(stringCol[10]));
                            }

                            if (stringCol.length >= 12
                                    && stringCol[11].length() != 0) {
                                invoiceImport.setTekst(stringCol[11]);
                            }

                            if (stringCol.length >= 13
                                    && stringCol[12].length() != 0) {
                                invoiceImport.setFakturaTekst(stringCol[12]);
                            }

                            if (stringCol.length >= 14
                                    && stringCol[13].length() != 0) {
                                invoiceImport.setRekkefolge(new Integer(
                                        stringCol[13]));
                            }

                            if (feilmelding.length() != 0) {
                                invoiceImport.setFeilmelding(feilmelding
                                        .toString());
                                feilList.add(invoiceImport);
                                feilmelding = new StringBuffer();
                            }
                            importTableModel.addRow(invoiceImport);
                        }
                    } catch (Exception ex) {
                        errorMsg = new StringBuffer(
                                "Det oppstod en feil ved import av rad ")
                                .append(lineCount).append(" ").append(
                                        ex.getMessage());
                        logger.error("Feil ved import av betingelser", ex);
                        ex.printStackTrace();
                    }
                }
            }

            if (errorMsg == null) {
                List<InvoiceImport> importList = importTableModel.getData();
                Collections.sort(importList);
                AvdelingV avdelingV;
                BuntStatus buntStatus = buntStatusDAO
                        .findByKode(BuntStatusEnum.NY);
                BuntType buntType = buntTypeDAO
                        .findByKode(BuntTypeEnum.BATCH_TYPE_IMP_FAK);

                Bunt bunt = new Bunt();
                bunt.setApplUser(currentApplUser);
                bunt.setBuntStatus(buntStatus);
                bunt.setBuntType(buntType);
                bunt.setOpprettetDato(Calendar.getInstance().getTime());
                buntDAO.saveBunt(bunt);

                if (importList != null) {
                    Faktura faktura = null;
                    FakturaLinje fakturaLinje = null;
                    InvoiceImport currentImport = null;
                    BigDecimal fakturaBelop = null;
                    BigDecimal fakturaMvaBelop = null;
                    BigDecimal mvaBelop = null;
                    BigDecimal totalBelop = null;
                    StringBuffer fakturaTittel = null;
                    BigDecimal dec100 = new BigDecimal(100);
                    BigDecimal belop = null;

                    for (InvoiceImport importable : importList) {
                    

                        if (importable.getFeilmelding() == null
                                || importable.getFeilmelding().length() == 0) {
                            if (currentImport == null
                                    || currentImport.compareTo((importable)) != 0) {
                                if (faktura != null) {
                                    faktura.setBelop(fakturaBelop);
                                    faktura.setMvaBelop(fakturaMvaBelop);
                                    faktura.setTotalBelop(fakturaBelop
                                            .add(fakturaMvaBelop));

                                    fakturaDAO.saveFaktura(faktura);
                                }
                                fakturaBelop = new BigDecimal(0);
                                fakturaMvaBelop = new BigDecimal(0);
                                currentImport = importable;
                                avdeling = importable.getAvdeling();
                                avdelingV = avdelingVDAO.getAvdelingV(avdeling
                                        .getAvdelingId());

                                faktura = new Faktura();
                                faktura.setBunt(bunt);
                                faktura.setAvdeling(avdeling);
                                faktura.setAvdnr(importable.getAvdnr());
                                faktura.setFakturaDato(((InvoiceImport)importable)
                                        .getFakturaDato());
                                faktura.setAar(((InvoiceImport)importable).getAar());
                                faktura.setFraPeriode(((InvoiceImport)importable)
                                        .getFraPeriode());
                                faktura.setForfallDato(((InvoiceImport)importable)
                                        .getForfallsDato());
                                faktura.setTilPeriode(((InvoiceImport)importable)
                                        .getTilPeriode());
                                faktura.setFakturaNr(((InvoiceImport)importable)
                                        .getFakturanr());
                                faktura.setOpprettetDato(Calendar.getInstance()
                                        .getTime());

                                fakturaTittel = new StringBuffer("Faktura ")
                                        .append(((InvoiceImport)importable).getAar())
                                        .append("/")
                                        .append(((InvoiceImport)importable).getFraPeriode());

                                if (!((InvoiceImport)importable).getFraPeriode().equals(
                                		((InvoiceImport)importable).getTilPeriode())) {
                                    fakturaTittel.append(" - ").append(
                                    		((InvoiceImport)importable).getTilPeriode());
                                }

                                faktura.setFakturaTittel(fakturaTittel
                                        .toString());
                                faktura.setFakturaTekst(((InvoiceImport)importable)
                                        .getFakturaTekst());
                                faktura.setMottakerNavn(avdeling.getDepartment()
                                        .getDepartmentName());
                                faktura.setAdresse1(avdeling.getDepartment()
                                        .getAdr1());
                                faktura.setAdresse2(avdeling.getDepartment()
                                        .getAdr2());
                                faktura.setPostnr(String.format("%04d",
                                        avdeling.getDepartment().getPostnr()));
                                faktura.setPoststed(avdeling.getDepartment()
                                        .getPoststed());
                                faktura.setBetingelseGruppe(((InvoiceImport)importable)
                                        .getBetingelseType()
                                        .getBetingelseGruppe());

                                if (((InvoiceImport)importable).getBokfSelskap() != null) {
                                    faktura.setBokfSelskap(((InvoiceImport)importable)
                                            .getBokfSelskap());
                                } else if (((InvoiceImport)importable).getBetingelseType()
                                        .getBokfSelskap() != null) {
                                    faktura.setBokfSelskap(((InvoiceImport)importable)
                                            .getBetingelseType()
                                            .getBokfSelskap());
                                } else {
                                    faktura.setBokfSelskap(bokfSelskapDAO
                                            .findByName(avdelingV
                                                    .getBokfSelskap()));
                                }
                                fakturaDAO.saveFaktura(faktura);
                            }

                            fakturaLinje = new FakturaLinje();

                            betingelseType = ((InvoiceImport)importable)
                                    .getBetingelseType();
                            fakturaLinje.setBetingelseType(betingelseType);
                            fakturaLinje.setFaktura(faktura);

                            StringBuffer linjeBeskrivelse = new StringBuffer(
                                    betingelseType.getBetingelseNavn());

                            if (((InvoiceImport)importable).getTekst() != null) {
                                linjeBeskrivelse.append(" ").append(
                                		((InvoiceImport)importable).getTekst());
                            }

                            fakturaLinje.setLinjeBeskrivelse(linjeBeskrivelse
                                    .toString());

                            belop = ((InvoiceImport)importable).getBelop();

                            /*if (betingelseType.getIsDebit() != 1) {
                                belop = belop.multiply(decMinus1);
                            }*/

                            mvaBelop = belop.multiply(
                            		((InvoiceImport)importable).getMva().getMvaVerdi())
                                    .divide(dec100);

                            fakturaMvaBelop = fakturaMvaBelop.add(mvaBelop);
                            totalBelop = belop.add(mvaBelop);
                            fakturaBelop = fakturaBelop.add(belop);

                            fakturaLinje.setBelop(belop);
                            fakturaLinje.setMvaBelop(mvaBelop);
                            fakturaLinje.setMvaKode(((InvoiceImport)importable).getMva()
                                    .getMvaKode());
                            fakturaLinje.setTotalBelop(totalBelop);
                            fakturaLinjeDAO.saveFakturaLinje(fakturaLinje);
                        }

                    }
                    if (faktura != null) {
                        faktura.setBelop(fakturaBelop);
                        faktura.setMvaBelop(fakturaMvaBelop);
                        faktura
                                .setTotalBelop(fakturaBelop
                                        .add(fakturaMvaBelop));

                        fakturaDAO.saveFaktura(faktura);
                    }
                }
            }

        } catch (Exception e) {
            errorMsg = new StringBuffer("Feil ved import av faktura ").append(e
                    .getMessage());
            logger.error("Feil ved import av faktura", e);
            e.printStackTrace();
        }
        if (errorMsg != null) {
            return errorMsg.toString();
        }
        return null;

    }

    /**
     * @see no.ica.fraf.gui.importing.ImportInterface#deleteListImport(java.util.List)
     */
    public void deleteListImport(List list) {
    }

    /**
     * @see no.ica.fraf.gui.importing.ImportInterface#getTableModelDetails()
     */
    public ObjectTableModel<Object> getTableModelDetails() {
        return invoiceTableModel;
    }

    /**
     * @see no.ica.fraf.gui.importing.ImportInterface#setColumnWidthsDetails(javax.swing.JTable)
     */
    public void setColumnWidthsDetails(JTable table) {
        // Avdeling
        TableColumn col = table.getColumnModel().getColumn(0);
        col.setPreferredWidth(70);
        // col.setCellEditor(new no.ica.fraf.gui.model.DateEditor());

        // Fakturanr
        col = table.getColumnModel().getColumn(1);
        col.setPreferredWidth(100);

        // Gruppe
        col = table.getColumnModel().getColumn(2);
        col.setPreferredWidth(100);

        // Fakturatittel
        col = table.getColumnModel().getColumn(3);
        col.setPreferredWidth(100);

        // Fakturadato
        col = table.getColumnModel().getColumn(4);
        col.setPreferredWidth(100);

        // Forfallsdato
        col = table.getColumnModel().getColumn(5);
        col.setPreferredWidth(100);

        // Fakturabeløp
        col = table.getColumnModel().getColumn(6);
        col.setPreferredWidth(100);

        // År
        col = table.getColumnModel().getColumn(7);
        col.setPreferredWidth(50);

        // Fra periode
        col = table.getColumnModel().getColumn(8);
        col.setPreferredWidth(60);

        // Til periode
        col = table.getColumnModel().getColumn(9);
        col.setPreferredWidth(60);

        // Opprettet dato
        col = table.getColumnModel().getColumn(10);
        col.setPreferredWidth(100);

    }

    /**
     * @see no.ica.fraf.gui.importing.ImportInterface#findBatches()
     */
    public List<Batchable> findBatches() {
        return buntDAO.findByBatchType(BuntTypeEnum.BATCH_TYPE_IMP_FAK);
    }

    /**
     * @see no.ica.fraf.gui.importing.ImportInterface#findDetailsByBatch(java.lang.Object)
     */
    public List findDetailsByBatch(Object object) {
        return fakturaDAO.findByBuntId(((Bunt) object).getBuntId());
    }

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#getHelpText()
	 */
	public String getHelpText() {
		return "Importfil er av typen semikolonseparert(csv), formatet er som følger:\n"
		+ "Avdnr;Beløp;Mvakode;År;Fra periode;Til periode;Betingelse;Fakturanr;Fakturadato;Forfallsdato;Selskap;Fakturalinjetekst;Fakturatekst;Rekkefølge.\n";
	}

}
