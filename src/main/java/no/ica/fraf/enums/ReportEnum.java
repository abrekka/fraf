package no.ica.fraf.enums;

import java.awt.Dimension;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.YesNoInteger;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.util.ExcelListable;
import no.ica.fraf.util.ModelUtil;

/**
 * Enum for rapporter
 * 
 * @author abr99
 * 
 */
public enum ReportEnum {

	/**
	 * Rapport over sikkerhet for avdelinger
	 */
	REPORT_SECURITY("Sikkerhet",
			new String[] { "Avdnr", "Navn", "Juridisk navn", "Sikkerhetstype",
					"Verdi", "Tinglyst", "Kommentar","Status" }, new String[] { "Avdnr",
					"AvdelingNavn", "JuridiskNavn", "SikkerhetTypeTekst",
					"SikkerhetVerdi", "Tinglyst", "Kommentar","Status" }, new Class[] {
					Integer.class, String.class, String.class, String.class,
					String.class, YesNoInteger.class, String.class,String.class },
			new ColumnFormatEnum[] { ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,ColumnFormatEnum.NONE }, new int[] {
					50, 150, 150, 120, 100, 60, 150,80 }, new Integer[] { 0 },
			null, null, null, false, null,new java.awt.Dimension(900, 500),false),
	/**
	 * Enum for rapport over arkiv
	 */
	REPORT_ARCHIVE("Arkiv", new String[] { "Perm", "Avdnr", "Navn", },
			new String[] { "ArchiveInfo", "Avdnr", "Department.DepartmentName" },
			new Class[] { String.class, Integer.class, String.class },
			new ColumnFormatEnum[] { ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE }, new int[] {
					50, 50, 150 }, null, null, null, new ColumnFormatEnum[] {
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE }, false, null,new java.awt.Dimension(400, 500),false),
	/**
	 * Enum for rapport totale betingelser
	 */
	REPORT_BETINGELSE_TOTAL("Totaler betingelse", new String[] { "Selskap",
			"År", "Fra periode", "Til periode", "Betingelse", "Beløp",
			"MVA beløp", "Total beløp" }, new String[] { "BokfSelskap", "Aar",
			"FraPeriode", "TilPeriode", "BetingelseNavn", "Belop", "MvaBelop",
			"TotalBelop" }, new Class[] { String.class, Integer.class,
			Integer.class, Integer.class, String.class, BigDecimal.class,
			BigDecimal.class, BigDecimal.class }, new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.CURRENCY,
			ColumnFormatEnum.CURRENCY, ColumnFormatEnum.CURRENCY }, new int[] {
			50, 50, 80, 80, 120, 120, 120, 120 }, new Integer[] { 0, 1, 2, 3,
			5, 6, 7 }, new Integer[] { 5, 6, 7 }, null, new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE }, false, null,new java.awt.Dimension(800, 500),false),

	/**
	 * Enum for rapport avdeling oversikt
	 */
	REPORT_AVDELING_OVERSIKT("Budsjettert omsetning", new String[] { "Avdnr",
			"Navn", "Adr1", "Adr2", "Postnr", "Poststed", "Driftsselskap",
			"Bokf. selskap", "Kjede", "Status", "År", "Budsjettert",
			"Kontrakttype", "Betingelse", "Fra dato", "Til dato", "Sats",
			"Beløp", "Frekvens", "Avregning" }, new String[] { "Avdnr",
			"AvdelingNavn", "Adr1", "Adr2", "Postnr", "Poststed",
			"JuridiskNavn", "BokfSelskap", "Kjede", "Status", "Aar", "SumAar",
			"KontraktType", "BetingelseNavn", "FraDato", "TilDato", "Sats",
			"Belop", "AvregningFrekvensTypeTxt", "AvregningTypeTxt" },
			new Class[] { String.class, String.class, String.class,
					String.class, String.class, String.class, String.class,
					String.class, String.class, String.class, Integer.class,
					BigDecimal.class, String.class, String.class, Date.class,
					Date.class, BigDecimal.class, BigDecimal.class,
					String.class, String.class }, new ColumnFormatEnum[] {
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.CURRENCY,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.CURRENCY,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE }, new int[] {
					50, 170, 150, 150, 50, 130, 160, 100, 100, 100, 50, 150,
					80, 120, 100, 100, 50, 100, 100, 100 }, new Integer[] { 0,
					7, 10, 11, 16, 17 }, null, null, new ColumnFormatEnum[] {
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE }, false,
			new Integer[] { 14, 15 },new java.awt.Dimension(800, 500),false),
	/**
	 * Enum for rapport over nye avdelinger
	 */
	REPORT_NY_AVDELING("Nye avdelinger", new String[] { "Avdnr", "Navn",
			"Start dato", "Avtale", "Selskap" }, new String[] { "Avdnr",
			"Navn", "DtStart", "Avtaletype", "DatasetConcorde" },
			new Class[] { String.class, String.class, Date.class, String.class,
					String.class }, null, new int[] { 50, 170, 150, 50, 50 },
			new Integer[] { 0 }, null, null, new ColumnFormatEnum[] {
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.DATE_YYYYMMDD, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE }, false, new Integer[] { 2 },new java.awt.Dimension(500, 500),false),
	/**
	 * Enum for rapport for avdelingbetingelser
	 */
	REPORT_AVDELING_BETINGELSE("Betingelser", new String[] { "Avdnr", "Navn",
			"Kontrakttype", "Status", "Betingelse", "Fra dato", "Til dato",
			"Sats", "Beløp", "Speilet", "Frekvens", "Avregning", "Region",
			"Kjede", "Basis" }, new String[] { "Avdnr", "Navn", "KontraktType",
			"Status", "BetingelseNavn", "FraDato", "TilDato", "Sats", "Belop",
			"Speilet", "AvregningFrekvens", "Avregning", "Region", "Kjede",
			"AvregningBasisTypeTxt" }, new Class[] { String.class,
			String.class, String.class, String.class, String.class, Date.class,
			Date.class, BigDecimal.class, BigDecimal.class, YesNoInteger.class,
			String.class, String.class, String.class, String.class,
			String.class }, new ColumnFormatEnum[] { ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.CURRENCY,
			ColumnFormatEnum.YES_NO, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE },
			new int[] { 50, 170, 70, 70, 150, 100, 100, 100, 100, 50, 100, 100,
					100, 100, 100 }, new Integer[] { 0, 7, 8 }, null, null,
			new ColumnFormatEnum[] { ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.DATE_YYYYMMDD,
					ColumnFormatEnum.DATE_YYYYMMDD, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.YES_NO,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE }, false, new Integer[] { 5, 6 },new java.awt.Dimension(1200, 500),false),
	/**
	 * Enum for rapport over manglende budsjett
	 */
	REPORT_MANGLENDE_BUDSJETT("Manglende budsjett", new String[] { "Avdeling",
			"Fra dato", "Til dato" }, new String[] { "Avdeling", "FraDato",
			"TilDato" },
			new Class[] { Avdeling.class, Date.class, Date.class },
			new ColumnFormatEnum[] { ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE }, new int[] {
					70, 100, 100 }, new Integer[] { 0 }, null, null, null,
			false, new Integer[] { 1, 2 },new java.awt.Dimension(300, 500),false),

	/**
	 * Enum for rapport total fakturering
	 */
	REPORT_TOTAL_FAKTURERING("Totaler fakturering", new String[] { "År",
			"Fra periode", "Til periode", "Avdnr", "Navn", "Fakturanr","Forfall", "Kode",
			"Betingelse", "Sats", "Beløp", "Mva", "Kjede", "Region",
			"Salgssjef", "Selskap", "Totalbeløp", "Juridisk eier", "Adresse",
			"Postnr", "Poststed" }, new String[] { "Aar", "FraPeriode",
			"TilPeriode", "Avdnr", "Navn", "FakturaNr","ForfallDato", "BetingelseTypeKode",
			"LinjeBeskrivelse", "Sats", "Belop", "MvaBelop", "KjedeNavn",
			"RegionNavn", "Salgssjef", "SelskapNavn", "TotalBelop",
			"JuridiskEier", "JuridiskEierAdr1", "JuridiskEierPostnr",
			"JuridiskEierPoststed" }, new Class[] { Integer.class,
			Integer.class, Integer.class, Integer.class, String.class,
			String.class,String.class, String.class, String.class, BigDecimal.class,
			BigDecimal.class, BigDecimal.class, String.class, String.class,
			String.class, String.class, BigDecimal.class, String.class,
			String.class, String.class, String.class }, new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.DATE_SHORT,ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.CURRENCY,
			ColumnFormatEnum.CURRENCY, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.CURRENCY,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE }, new int[] { 50, 80,
			80, 50, 120, 80, 50,50, 120, 60, 100, 120, 120, 120, 120, 50, 150,
			100, 100, 50, 100 }, new Integer[] { 0, 1, 2, 3, 9, 10, 11, 16 },
			new Integer[] { 16 }, (ExcelListable) ModelUtil
					.getBean("totalFaktureringVDAO"), new ColumnFormatEnum[] {
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.DATE_SHORT, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE,ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE }, false, null,new java.awt.Dimension(1200, 500),true),
	/**
	 * Enum for rapport over avdelingsmangler
	 */
	REPORT_MISSING("Avdelingsmangler", new String[] { "Avdnr", "Navn",
			"Mangel", "Kommentar", "Status" }, new String[] { "Avdnr", "Navn",
			"MangelBeskrivelse", "Kommentar", "StatusTxt" }, new Class[] {
			Integer.class, String.class, String.class, String.class,
			String.class }, new ColumnFormatEnum[] { ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE }, new int[] { 50,
			150, 120, 350, 50 }, new Integer[] { 0 }, null, null, null, false,
			null,new java.awt.Dimension(750, 500),false),
	/**
	 * Enum for rapport over speilede betingelse
	 */
	REPORT_MIRROR("Speilede betingelser", new String[] { "Avdnr", "Navn",
			"Betingelse", "Transaksjon", "Konto" }, new String[] {
			"Avdeling.Avdnr", "Avdeling.Department.DepartmentName", "AvdelingBetingelse",
			"LkKontraktobjekter.KontraktObjekt",
			"LkKontraktobjekter.MrTransaksjonTyperEd.MrKonti" }, new Class[] {
			Integer.class, String.class, AvdelingBetingelse.class,
			String.class, String.class },
			new ColumnFormatEnum[] { ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE }, new int[] {
					50, 200, 220, 220, 200 }, new Integer[] { 0 }, null, null,
			null, false, null,new java.awt.Dimension(700, 500),false),
	/**
	 * Enum for rapport over speilede betingelse
	 */
	REPORT_MIRROR_STATUS("Status speilede betingelser", new String[] { "Avdnr",
			"Betingelse" },
			new String[] { "KontraktObjektNr", "KontraktObjekt" }, new Class[] {
					String.class, String.class }, new ColumnFormatEnum[] {
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE }, new int[] {
					50, 250 }, new Integer[] { 0 }, null, null, null, false,
			null,new java.awt.Dimension(350, 500),false),
	/**
	 * Enum for rapport over omsetning
	 */
	REPORT_SALES("Omsetning", new String[] { "Avdnr", "Navn", "Kontrakt", "År",
			"Periode", "Omsetning", "Status" }, new String[] { "Avdnr",
			"AvdelingNavn", "KontraktType", "Aar", "Periode", "KorrigertBelop",
			"Status" }, new Class[] { Integer.class, String.class,
			String.class, Integer.class, Integer.class, BigDecimal.class,
			String.class }, new ColumnFormatEnum[] { ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.CURRENCY, ColumnFormatEnum.NONE }, new int[] { 60,
			220, 60, 60, 50, 150, 80 }, new Integer[] { 0, 3, 4, 5 },
			new Integer[] { 5 }, null, new ColumnFormatEnum[] {
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE }, false, null,new java.awt.Dimension(800, 500),false), 
					REPORT_NEDLAGT_AVDELING(
			"Nedlagte avdelinger", new String[] { "Avdnr", "Navn",
					"Start dato", "Sluttdato", "Avtale", "Selskap" },
			new String[] { "Avdnr", "DepartmentName", "DtStart", "DtSlutt", "Avtaletype",
					"DatasetConcorde" }, new Class[] { String.class,
					String.class, Date.class, Date.class, String.class,
					String.class }, null, new int[] { 50, 170, 100, 100, 50 },
			new Integer[] { 0 }, null, null, new ColumnFormatEnum[] {
					ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
					ColumnFormatEnum.DATE_YYYYMMDD,ColumnFormatEnum.DATE_YYYYMMDD, ColumnFormatEnum.NONE,
					ColumnFormatEnum.NONE }, false, new Integer[] { 2 },new java.awt.Dimension(500, 500),false),
					REPORT_AVREGNING_SAMMENDARG(
							"Avregninger", new String[] { "Avdnr", "Navn",
									"Avregnet beløp", "Avregningsdato", "Fakturanr" },
							new String[] { "Avdnr", "MottakerNavn", "TotalBelop", "OpprettetDato", "FakturaNr"}, new Class[] { String.class,
									String.class, BigDecimal.class, Date.class, String.class }, null, new int[] { 50, 170, 100, 100 },
							new Integer[] { 0 }, null, null, new ColumnFormatEnum[] {
									ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
									ColumnFormatEnum.NONE,ColumnFormatEnum.DATE_YYYYMMDD, ColumnFormatEnum.NONE },
									false, new Integer[] { 3 },new java.awt.Dimension(500, 500),false),
									REPORT_SPEILET_BETINGELSE_MANGEL(
											"Speilede betingelser - mangler", new String[] { "Avdnr", "Betingelse",
													"Fra dato", "Til dato"},
											new String[] { "Avdnr", "BetingelseNavn", "FraDato", "TilDato"}, new Class[] { String.class,
													String.class, Date.class, Date.class}, null, new int[] { 50, 100, 100, 100 },
											new Integer[] { 0 }, null, null, new ColumnFormatEnum[] {
													ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
													ColumnFormatEnum.DATE_YYYYMMDD, ColumnFormatEnum.DATE_YYYYMMDD },
													false, new Integer[] { 3 },new java.awt.Dimension(500, 500),false);

	/**
	 * Tekst for rapport
	 */
	private String reportString;

	/**
	 * TableModel for tabell som skal vise rapport
	 */
	// private ObjectTableModel objectTableModel;
	/**
	 * Kolonnebredder
	 */
	private int[] columnWidths;

	/**
	 * Nummerkolonner
	 */
	private List<Integer> numColumns;

	/**
	 * Datokolonner
	 */
	private List<Integer> dateColumns;

	/**
	 * Summeringskolonner
	 */
	private List<Integer> sumColumns;

	/**
	 * Klasse som brukes til å generere excelfil derosm utvalg er for stort til
	 * å ta alt
	 */
	private ExcelListable excelListable;

	/**
	 * True dersom rapporten skal genereres til excel via sider
	 */
	private boolean listable = false;

	/**
	 * Tabelldefinisjon for rapport
	 */
	ObjectTableDef objectTableDef;

	/**
	 * Tabelldefinisjon ved generering til excel
	 */
	ObjectTableDef objectTableDefExcel;
	Dimension windowSize;
	Boolean usePrintButton;

	/**
	 * Konstruktør
	 * 
	 * @param aReportString
	 * @param colNames
	 * @param someMethods
	 * @param params
	 * @param formats
	 * @param widths
	 * @param numCols
	 * @param sumCols
	 * @param aExcelListable
	 * @param formatsExcel
	 * @param listable
	 * @param dateCols
	 */
	private ReportEnum(String aReportString, String[] colNames,
			String[] someMethods, Class[] params, ColumnFormatEnum[] formats,
			int[] widths, Integer[] numCols, Integer[] sumCols,
			ExcelListable aExcelListable, ColumnFormatEnum[] formatsExcel,
			boolean listable, Integer[] dateCols,Dimension aWindowSize,boolean shoulUsePrintButton) {
		usePrintButton=shoulUsePrintButton;
		windowSize=aWindowSize;
		this.listable = listable;
		excelListable = aExcelListable;
		reportString = aReportString;
		columnWidths = widths;
		objectTableDef = new ObjectTableDef(colNames, someMethods, params,
				formats);

		ColumnFormatEnum[] tmpformats;
		if (formatsExcel == null) {
			tmpformats = formats;
		} else {
			tmpformats = formatsExcel;
		}
		objectTableDefExcel = new ObjectTableDef(colNames, someMethods, params,
				tmpformats);

		if (numCols != null) {
			numColumns = new ArrayList<Integer>(Arrays.asList(numCols));
		}
		if (dateCols != null) {
			dateColumns = new ArrayList<Integer>(Arrays.asList(dateCols));
		}
		if (sumCols != null) {
			sumColumns = new ArrayList<Integer>(Arrays.asList(sumCols));
		}
	}

	/**
	 * Henter rapporttekst
	 * 
	 * @return rapporttekst
	 */
	public String getReportString() {
		return reportString;
	}

	/**
	 * Henter TableModel for tabell som skal vise rapport
	 * 
	 * @return TableModel for tabell som skal vise rapport
	 */
	/*
	 * public ObjectTableModel getObjectTableModel() {
	 * objectTableModel.deleteData(); return objectTableModel; }
	 */

	/**
	 * Henter ut tablemodel for generering av excelfil
	 * 
	 * @return tablemodel for generering av excelfil
	 */
	/*
	 * public ObjectTableModel getExcelTableModel() { ObjectTableModel
	 * excelTableModel = null; ColumnFormatEnum[] tmpformats; if (formatsExcel ==
	 * null) { tmpformats = formats; } else { tmpformats = formatsExcel; }
	 * ObjectTableDef objectTableDef = new ObjectTableDef(colNames, someMethods,
	 * params, tmpformats); excelTableModel = new
	 * ObjectTableModel(objectTableDef); if (!listable) {
	 * excelTableModel.setData(objectTableModel.getData()); } return
	 * excelTableModel; }
	 */

	/**
	 * Henter kolonnebredde for gjeldende kolonne
	 * 
	 * @param col
	 * @return kolonnebredde for gjeldende kolonne
	 */
	public int getColumnWidth(int col) {
		return columnWidths[col];
	}

	/**
	 * Henter ut antall kolonner
	 * 
	 * @return antall kolonner
	 */
	public int getNumberOfColumns() {
		return columnWidths.length;
	}

	/**
	 * Henter nummerkolonner
	 * 
	 * @return nummerkolonner
	 */
	public List<Integer> getNumColumns() {
		return numColumns;
	}

	/**
	 * Henter datokolonner
	 * 
	 * @return datokolonner
	 */
	public List<Integer> getDateColumns() {
		return dateColumns;
	}

	/**
	 * Henter summeringskolonner
	 * 
	 * @return summeringskolonner
	 */
	public List<Integer> getSumColumns() {
		return sumColumns;
	}

	/**
	 * Lager en tom TableModel for sumtabell i rapport
	 * 
	 * @return tom TableModel
	 */
	public TableModel getEmptyTableModelSum() {
		String[][] row = new String[1][getNumberOfColumns()];
		String[] header = new String[getNumberOfColumns()];

		row[0][0] = "Sum:";
		header[0] = "";
		for (int i = 1; i < getNumberOfColumns(); i++) {
			row[0][i] = "";
			header[i] = "";
		}

		return new DefaultTableModel(row, header);

	}

	/**
	 * Henter ut klasse som skal brukes til generering av excelfil
	 * 
	 * @return klasse som skal brukes til generering av excelfil
	 */
	public ExcelListable getExcelListable() {
		return excelListable;
	}

	/**
	 * @return Returns the listable.
	 */
	public boolean isListable() {
		return listable;
	}

	/**
	 * @return Returns the objectTableDef.
	 */
	public ObjectTableDef getObjectTableDef() {
		return objectTableDef;
	}

	/**
	 * @return Returns the objectTableDefExcel.
	 */
	public ObjectTableDef getObjectTableDefExcel() {
		return objectTableDefExcel;
	}
	
	public Dimension getWindowSize(){
		return windowSize;
	}
	public boolean usePrintButton(){
		return usePrintButton;
	}

}
