package no.ica.elfa.util;

import java.util.List;

import javax.swing.table.TableModel;

/**
 * Klasse som brukes til å konfiguerer en sheet i et excelark
 * @author abr99
 *
 */
public class ExcelSheetConfig {
	/**
	 * Sheetnavn
	 */
	private String sheetName;
	/**
	 * Overskrift på sheet (første rad)
	 */
	private String heading;
	/**
	 * TableModle som brukes for å skrive data
	 */
	private TableModel sheetTableModel;
	/**
	 * Font på headerrad
	 */
	private int headerFont;
	/**
	 * Summeringskolonner
	 */
	private String[] sumColumns;
	/**
	 * Tallkolonner
	 */
	private List<Integer> numberCols;
	/**
	 * True dersom overskrift skal være bold
	 */
	private boolean headerBold = false;
	
	
	/**
	 * @param sheetName
	 * @param heading
	 * @param sheetTableModel
	 * @param headerFont
	 * @param sumColumns
	 * @param numberCols
	 * @param headerBold
	 */
	public ExcelSheetConfig(String sheetName, String heading, TableModel sheetTableModel, int headerFont, String[] sumColumns, List<Integer> numberCols, boolean headerBold) {
		super();
		this.sheetName = sheetName;
		this.heading = heading;
		this.sheetTableModel = sheetTableModel;
		this.headerFont = headerFont;
		this.sumColumns = sumColumns;
		this.numberCols = numberCols;
		this.headerBold = headerBold;
	}
	/**
	 * @return Returns the headerFont.
	 */
	public int getHeaderFont() {
		return headerFont;
	}
	/**
	 * @return Returns the numberCols.
	 */
	public List<Integer> getNumberCols() {
		return numberCols;
	}
	/**
	 * @return Returns the sheetName.
	 */
	public String getSheetName() {
		return sheetName;
	}
	/**
	 * @return Returns the sheetTableModel.
	 */
	public TableModel getSheetTableModel() {
		return sheetTableModel;
	}
	/**
	 * @return Returns the sumColumns.
	 */
	public String[] getSumColumns() {
		return sumColumns;
	}
	/**
	 * @return Returns the headerBold.
	 */
	public boolean isHeaderBold() {
		return headerBold;
	}
	/**
	 * @return Returns the heading.
	 */
	public String getHeading() {
		return heading;
	}
	
	
}
