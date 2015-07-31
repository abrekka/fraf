package no.ica.fraf.util;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.table.TableModel;

import no.ica.elfa.util.ExcelSheetConfig;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.ApplParamDAO;
import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplParam;
import no.ica.fraf.model.ApplUser;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jdesktop.jdic.desktop.Desktop;
import org.jdesktop.jdic.desktop.DesktopException;

/**
 * Klasse som brukes til generering av excelfiler
 * 
 * @author abr99
 * 
 */
public class ExcelUtil {
	/**
	 * 
	 */
	private TableModel currentTableModel;

	/**
	 * 
	 */
	private String currentHeading;

	/**
	 * 
	 */
	private String currentExcelSheetName;

	/**
	 * Sidestørrelse
	 */
	private static final int FETCH_SIZE = 1000;

	/**
	 * Logger til database
	 */
	private Logger logger = Logger.getLogger(ExcelUtil.class);

	/**
	 * Path til excel
	 */
	private static final String EXCEL_FILE_PATH_1 = "c:\\Program Files\\Microsoft Office\\Office\\Excel.exe";

	/**
	 * Path til excel dersom den første pathen ikke funker
	 */
	private static final String EXCEL_FILE_PATH_2 = "c:\\Program Files\\Microsoft Office\\Office10\\Excel.exe";

	private HSSFSheet readSheet;

	/**
	 * @param heading
	 */
	public ExcelUtil(String heading) {
		this(null, heading, null);
	}

	/**
	 * @param aTableModel
	 * @param heading
	 * @param excelSheetName
	 */
	public ExcelUtil(TableModel aTableModel, String heading,
			String excelSheetName) {
		currentTableModel = aTableModel;
		currentHeading = heading;
		currentExcelSheetName = excelSheetName;
	}

	/**
	 * Genrerer excelfil
	 * 
	 * @param directory
	 * @param fileName
	 * @param excelListable
	 * @param tableModel
	 * @param filter
	 * @param numberCols
	 * @param labelInfo
	 * @throws FrafException
	 */
	public void showDataInExcel(String directory, String fileName,
			ExcelListable excelListable, ObjectTableModel tableModel,
			Object[] filter, List<Integer> numberCols, JLabel labelInfo)
			throws FrafException {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet;

		String infoString = "Genererer excel-fil...rad ";

		if (currentHeading != null && currentHeading.length() != 0) {
			sheet = wb.createSheet(currentHeading);
		} else {
			sheet = wb.createSheet("sheet");
		}

		int currentRow = 0;
		HSSFRow row;
		HSSFCell cell;

		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 16);
		font.setBoldweight((short) 10);

		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(font);

		// Overskrift
		if (currentHeading != null && currentHeading.length() != 0) {
			row = sheet.createRow((short) currentRow);
			cell = row.createCell((short) 0);
			cell.setCellValue(currentHeading);
			cell.setCellStyle(style);
			currentRow++;
		}

		// Kolonneoverskrift
		row = sheet.createRow((short) currentRow);
		currentRow++;
		int columnCount = tableModel.getColumnCount();
		int rowCount = excelListable.getCount(filter).intValue();
		String columnHeading;

		// Create a cell and put a value in it.

		for (int i = 0; i < columnCount; i++) {
			columnHeading = tableModel.getColumnName(i);
			cell = row.createCell((short) i);
			cell.setCellValue(columnHeading);
			cell.setCellStyle(style);
		}

		int currentIndex = 0;
		Collection objects = excelListable.getNextList(filter, currentIndex,
				FETCH_SIZE);
		tableModel.setData(objects);

		int lastPage;
		int mod = rowCount % FETCH_SIZE;
		int div = rowCount / FETCH_SIZE;

		if ((mod) == 0) {
			lastPage = div;
		} else if ((div) == 0) {
			lastPage = 1;
		} else {
			lastPage = (div) + 1;
		}

		// Data
		int j;
		int k;
		int totalRowCount = 0;
		int counter;

		if (rowCount < FETCH_SIZE) {
			counter = rowCount;
		} else {
			counter = FETCH_SIZE;
		}

		for (int l = 0; l < lastPage; l++) {
			for (j = currentRow; j < counter + currentRow; j++) {
				row = sheet.createRow((short) j);
				totalRowCount++;

				if (labelInfo != null) {
					labelInfo.setText(infoString + totalRowCount);
				}

				for (k = 0; k < columnCount; k++) {

					if (tableModel.getValueAt(j - currentRow, k) != null) {

						if (numberCols != null
								&& numberCols.contains(new Integer(k))) {
							try {
								row
										.createCell((short) k)
										.setCellValue(
												Double
														.valueOf(
																String
																		.valueOf(tableModel
																				.getValueAt(
																						j
																								- currentRow,
																						k)))
														.doubleValue());
							} catch (NumberFormatException e) {
								cell = row.createCell((short) k);
								cell.setCellValue(String.valueOf(tableModel
										.getValueAt(j - currentRow, k)));
							}
						} else {

							cell = row.createCell((short) k);
							cell.setCellValue(String.valueOf(tableModel
									.getValueAt(j - currentRow, k)));
						}
					}
				}

			}
			if ((rowCount - totalRowCount) < FETCH_SIZE) {
				counter = rowCount - totalRowCount;
			} else {
				counter = FETCH_SIZE;
			}

			currentRow = j;
			currentIndex = currentIndex + FETCH_SIZE;
			objects = excelListable.getNextList(filter, currentIndex,
					FETCH_SIZE);
			tableModel.setData(objects);
		}

		openExcelFile(fileName, directory, wb);

	}

	/**
	 * Åpner excelfil
	 * 
	 * @param fileName
	 * @param directory
	 * @param wb
	 * @throws FrafException
	 */
	private static void openExcelFile(String fileName, String directory,
			HSSFWorkbook wb) throws FrafException {
		File dir = new File(directory);

		if (!dir.exists()) {
			dir.mkdir();
		}

		if (directory.indexOf("/") == -1) {
			directory += "/";
		}

		StringBuffer fullfileName = new StringBuffer(directory)
				.append(fileName);

		StringBuffer cmd;

		File excelFile1 = new File(EXCEL_FILE_PATH_1);
		File excelFile2 = new File(EXCEL_FILE_PATH_2);

		if (excelFile1.exists()) {
			cmd = new StringBuffer("cmd /C ").append("\"").append(
					EXCEL_FILE_PATH_1).append(" \" ").append(fullfileName);
		} else if (excelFile2.exists()) {
			cmd = new StringBuffer("cmd /C ").append("\"").append(
					EXCEL_FILE_PATH_2).append(" \" ").append(fullfileName);
		} else {
			cmd = new StringBuffer("cmd /C excel.exe \"").append(fullfileName)
					.append("\"");
		}


		try {
			FileOutputStream fileOut = new FileOutputStream(fullfileName
					.toString());
			wb.write(fileOut);
			fileOut.close();
			
			Desktop.open(new File(fullfileName.toString()));

			//Runtime.getRuntime().exec(cmd.toString());

		}catch(DesktopException de){
			de.printStackTrace();
			throw new FrafException(de);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FrafException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrafException(e);
		}
	}

	/**
	 * @param directory
	 * @param fileName
	 * @param sumColumns
	 * @param numberCols
	 * @param labelInfo
	 * @throws FrafException
	 */
	public void showDataInExcel(String directory, String fileName,
			List<Integer> sumColumns, List<Integer> numberCols, JLabel labelInfo)
			throws FrafException {
		showDataInExcel(directory, fileName, sumColumns, numberCols, labelInfo,
				null);
	}

	/**
	 * Genrerer excelfil
	 * 
	 * @param directory
	 * @param fileName
	 * @param sumColumns
	 * @param numberCols
	 * @param labelInfo
	 * @param dateCols
	 * @throws FrafException
	 */
	public void showDataInExcel(String directory, String fileName,
			List<Integer> sumColumns, List<Integer> numberCols,
			JLabel labelInfo, List<Integer> dateCols) throws FrafException {
		String[] sumColStrings = null;
		if (sumColumns != null) {
			int noOfCols = sumColumns.size();
			int i = 0;
			sumColStrings = new String[noOfCols];
			for (Integer sumCol : sumColumns) {
				sumColStrings[i] = getColLetter(sumCol);
				i++;
			}
		}
		showDataInExcel(directory, fileName, sumColStrings, numberCols,
				labelInfo, dateCols);
	}

	/**
	 * @param directory
	 * @param fileName
	 * @param sumColumns
	 * @param numberCols
	 * @param labelInfo
	 * @throws FrafException
	 */
	public void showDataInExcel(String directory, String fileName,
			String[] sumColumns, List<Integer> numberCols, JLabel labelInfo)
			throws FrafException {
		showDataInExcel(directory, fileName, sumColumns, numberCols, labelInfo,
				null);
	}

	/**
	 * Genererer excelfil
	 * 
	 * @param directory
	 * @param fileName
	 * @param sumColumns
	 * @param numberCols
	 * @param labelInfo
	 * @param dateCols
	 * @throws FrafException
	 */
	public void showDataInExcel(String directory, String fileName,
			String[] sumColumns, List<Integer> numberCols, JLabel labelInfo,
			List<Integer> dateCols) throws FrafException {
		if (directory == null || directory.length() == 0) {
			throw new FrafException("Katalog ikke satt");
		}
		String infoString = "Genererer excel-fil...rad ";
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet;

		if (currentExcelSheetName != null
				&& currentExcelSheetName.length() != 0) {
			sheet = wb.createSheet(currentExcelSheetName);
		} else {
			sheet = wb.createSheet("sheet");
		}

		int currentRow = 0;
		HSSFRow row;
		HSSFCell cell;

		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 16);
		font.setBoldweight((short) 10);

		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(font);

		HSSFCellStyle dateStyle = wb.createCellStyle();
		dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));

		// Overskrift
		if (currentHeading != null && currentHeading.length() != 0) {
			row = sheet.createRow((short) currentRow);
			cell = row.createCell((short) 0);
			cell.setCellValue(currentHeading);
			cell.setCellStyle(style);
			currentRow++;
		}

		// Kolonneoverskrift
		row = sheet.createRow((short) currentRow);
		currentRow++;
		int columnCount = currentTableModel.getColumnCount();
		int rowCount = currentTableModel.getRowCount();
		String columnHeading;

		// Create a cell and put a value in it.

		for (int i = 0; i < columnCount; i++) {
			columnHeading = currentTableModel.getColumnName(i);
			cell = row.createCell((short) i);
			cell.setCellValue(columnHeading);
			cell.setCellStyle(style);
		}

		// Data
		int j;
		int k;
		for (j = currentRow; j < rowCount + currentRow; j++) {
			if (labelInfo != null) {
				labelInfo.setText(infoString + (j + 1));
			}
			row = sheet.createRow((short) j);

			for (k = 0; k < columnCount; k++) {
				if (currentTableModel.getValueAt(j - currentRow, k) != null) {
					if (numberCols != null
							&& numberCols.contains(new Integer(k))) {
						row
								.createCell((short) k)
								.setCellValue(
										Double
												.valueOf(
														String
																.valueOf(currentTableModel
																		.getValueAt(
																				j
																						- currentRow,
																				k)))
												.doubleValue());
					} else if (dateCols != null
							&& dateCols.contains(new Integer(k))) {
						try {

							cell = row.createCell((short) k);
							cell
									.setCellValue(((DateFormat) ColumnFormatEnum.DATE_YYYYMMDD
											.getFormatter()).parse(String
											.valueOf(currentTableModel
													.getValueAt(j - currentRow,
															k))));

							cell.setCellStyle(dateStyle);

						} catch (ParseException e) {
							e.printStackTrace();
						}
					}

					else {
						cell = row.createCell((short) k);
						cell.setCellValue(String.valueOf(currentTableModel
								.getValueAt(j - currentRow, k)));
					}
				}
			}
		}

		StringBuffer formula;

		if (sumColumns != null && sumColumns.length != 0) {
			row = sheet.createRow((short) j);
			row.createCell((short) 0).setCellValue("Sum:");

			for (int l = 0; l < sumColumns.length; l++) {
				formula = new StringBuffer("SUM(").append(sumColumns[l])
						.append(currentRow + 1).append(":").append(
								sumColumns[l]).append(j).append(")");
				row.createCell(getColNumber(sumColumns[l])).setCellFormula(
						formula.toString());
			}

		}

		openExcelFile(fileName, directory, wb);
	}

	/**
	 * Finner kolonnenummer basert på kolonnebokstav
	 * 
	 * @param colLetter
	 * @return kolonnenummer basert på kolonnebokstav
	 * @throws FrafException
	 */
	private short getColNumber(String colLetter) throws FrafException {
		if (colLetter.equalsIgnoreCase("A")) {
			return 0;
		} else if (colLetter.equalsIgnoreCase("B")) {
			return 1;
		} else if (colLetter.equalsIgnoreCase("C")) {
			return 2;
		} else if (colLetter.equalsIgnoreCase("D")) {
			return 3;
		} else if (colLetter.equalsIgnoreCase("E")) {
			return 4;
		} else if (colLetter.equalsIgnoreCase("F")) {
			return 5;
		} else if (colLetter.equalsIgnoreCase("G")) {
			return 6;
		} else if (colLetter.equalsIgnoreCase("H")) {
			return 7;
		} else if (colLetter.equalsIgnoreCase("I")) {
			return 8;
		} else if (colLetter.equalsIgnoreCase("J")) {
			return 9;
		} else if (colLetter.equalsIgnoreCase("K")) {
			return 10;
		} else if (colLetter.equalsIgnoreCase("L")) {
			return 11;
		} else if (colLetter.equalsIgnoreCase("M")) {
			return 12;
		} else if (colLetter.equalsIgnoreCase("N")) {
			return 13;
		} else if (colLetter.equalsIgnoreCase("O")) {
			return 14;
		} else if (colLetter.equalsIgnoreCase("P")) {
			return 15;
		} else if (colLetter.equalsIgnoreCase("Q")) {
			return 16;
		} else if (colLetter.equalsIgnoreCase("R")) {
			return 17;
		} else if (colLetter.equalsIgnoreCase("S")) {
			return 18;
		} else if (colLetter.equalsIgnoreCase("T")) {
			return 19;
		} else if (colLetter.equalsIgnoreCase("U")) {
			return 20;
		} else {
			throw new FrafException("Kolonne ikke definert");
		}

	}

	/**
	 * Finner kolonnebokstav basert på kolonnenummer
	 * 
	 * @param col
	 * @return kolonnebokstav basert på kolonnenummer
	 * @throws FrafException
	 */
	private static String getColLetter(int col) {
		switch (col) {
		case 0:
			return "A";
		case 1:
			return "B";
		case 2:
			return "C";
		case 3:
			return "D";
		case 4:
			return "E";
		case 5:
			return "F";
		case 6:
			return "G";
		case 7:
			return "H";
		case 8:
			return "I";
		case 9:
			return "J";
		case 10:
			return "K";
		case 11:
			return "L";
		case 12:
			return "M";
		case 13:
			return "N";
		case 14:
			return "O";
		case 15:
			return "P";
		case 16:
			return "Q";
		case 17:
			return "R";
		case 18:
			return "S";
		default:
			throw new IllegalStateException("Kolonne ikke definert");
		}
	}

	/**
	 * Forbereder generering av excelfil
	 * 
	 * @param applUser
	 * @param displayComponent
	 * @return katalog for generering
	 */
	public String prepare(ApplUser applUser, Component displayComponent) {

		try {
			StringBuffer dir = new StringBuffer(ApplParamUtil.getStringParam("excel_file_path"));
			if ((dir.lastIndexOf("\\") != dir.length() - 1)
					&& (dir.lastIndexOf("/") != dir.length() - 1)) {
				dir.append("/");
			}
			File dirFile = new File(dir.toString());

			if (!dirFile.exists()) {
				if (!dirFile.mkdir()) {
					GuiUtil.showErrorMsgFrame(displayComponent, "Feil",
							"Kunne ikke lage katalog " + dir.toString());
					return null;
				}
			}
			dir.append(applUser.getUserName()).append("\\");

			dirFile = new File(dir.toString());

			if (!dirFile.exists()) {
				if (!dirFile.mkdir()) {
					GuiUtil.showErrorMsgFrame(displayComponent, "Feil",
							"Kunne ikke lage katalog " + dir.toString());
					return null;
				}
			}
			
			return dir.toString();
		} catch (FrafException e) {
			GuiUtil.showErrorMsgDialog(displayComponent, "Feil", e.getMessage());
		}
		return null;
	}

	/*
	 * public static void importConditions(String fileName, boolean
	 * hasHead,ObjectTableModel importTableModel) throws FrafException {
	 * ImportBetingelseDAO importBetingelseDAO =
	 * (ImportBetingelseDAO)ModelUtil.getBean("importBetingelseDAO"); try {
	 * POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream( fileName));
	 * HSSFWorkbook wb = new HSSFWorkbook(fs); HSSFSheet sheet =
	 * wb.getSheetAt(0); HSSFRow row; HSSFCell cell; ImportBetingelse
	 * importBetingelse;
	 * 
	 * int lastRow = sheet.getLastRowNum(); int firstRow = 0; int lastCell = 0;
	 * int lineCount = 0; int cellType; String cellValue;
	 * 
	 * if (hasHead) { firstRow = 1; }
	 * 
	 * for (int i = firstRow; i < lastRow; i++) { lineCount++; row =
	 * sheet.getRow(i); lastCell = row.getLastCellNum();
	 * 
	 * if (lastCell < 7) { throw new FrafException("Linje " + lineCount +
	 * "inneholder for få kolonner."); }
	 * 
	 * importBetingelse = new ImportBetingelse();
	 * importBetingelse.setImportDato(Calendar .getInstance().getTime());
	 * 
	 * for(int j=0;j<=lastCell;j++){ cell = row.getCell((short)j); cellType =
	 * cell.getCellType(); switch(j){ case 0: switch(cellType){ case
	 * HSSFCell.CELL_TYPE_NUMERIC: importBetingelse
	 * .setAvdnr(Integer.valueOf(Double.valueOf(cell.getNumericCellValue()).intValue()));
	 * break; default: importBetingelse .setAvdnr(new
	 * Integer(cell.getStringCellValue())); } break; case 1: switch(cellType){
	 * case HSSFCell.CELL_TYPE_NUMERIC:
	 * importBetingelse.setSumPrFrekvens(BigDecimal.valueOf(cell.getNumericCellValue()));
	 * break; default: importBetingelse.setSumPrFrekvens(new
	 * BigDecimal(cell.getStringCellValue().replaceAll(",", ".") .replaceAll(" ",
	 * ""))); }
	 * 
	 * break; case 2: switch(cellType){ case HSSFCell.CELL_TYPE_NUMERIC:
	 * cellValue = String.valueOf(cell.getNumericCellValue()); break; default:
	 * cellValue = cell.getStringCellValue(); } importBetingelse
	 * .setFraDato(GuiUtil.SIMPLE_DATE_FORMAT .parse(cellValue)); break; case 3:
	 * switch(cellType){ case HSSFCell.CELL_TYPE_NUMERIC: cellValue =
	 * String.valueOf(cell.getNumericCellValue()); break; default: cellValue =
	 * cell.getStringCellValue(); } importBetingelse
	 * .setTilDato(GuiUtil.SIMPLE_DATE_FORMAT .parse(cellValue)); break; case 4:
	 * importBetingelse .setBetingelseTypeKode(cell.getStringCellValue());
	 * break; case 5:
	 * importBetingelse.setFrekvensKode(cell.getStringCellValue()); break; case
	 * 6: importBetingelse.setAvregningTypeKode(cell.getStringCellValue());
	 * break; case 7: if (cell.getStringCellValue().length() != 0) {
	 * importBetingelse .setSelskapNavn(cell.getStringCellValue()); } else {
	 * importBetingelse.setSelskapNavn(null); } break; case 8:
	 * importBetingelse.setTekst(cell.getStringCellValue()); break; case 9:
	 * importBetingelse.setFakturaTekst(cell.getStringCellValue()); break; case
	 * 10: importBetingelse .setFakturaTekstRek(new Integer(
	 * cell.getStringCellValue())); break; } }
	 * importTableModel.addRow(importBetingelse); importBetingelseDAO
	 * .saveImportBetingelse(importBetingelse); } } catch (Exception ex) { throw
	 * new FrafException(ex); } }
	 */

	public void showDataInExcel(String directory, String fileName,
			JLabel labelInfo, List<ExcelSheetConfig> configs)
			throws FrafException {
		if (directory == null || directory.length() == 0) {
			throw new FrafException("Katalog ikke satt");
		}
		HSSFWorkbook wb = new HSSFWorkbook();

		for (ExcelSheetConfig excelSheetConfig : configs) {
			wb = showDataInExcel(wb, labelInfo, excelSheetConfig);
		}

		openExcelFile(fileName, directory, wb);
	}

	private HSSFWorkbook showDataInExcel(HSSFWorkbook wb, JLabel labelInfo,
			ExcelSheetConfig excelSheetConfig) throws FrafException {
		String infoString = "Genererer excel-fil...rad ";
		HSSFSheet sheet;

		currentTableModel = excelSheetConfig.getSheetTableModel();
		List<Integer> numberCols = excelSheetConfig.getNumberCols();
		String[] sumColumns = excelSheetConfig.getSumColumns();

		if (excelSheetConfig.getSheetName() != null
				&& excelSheetConfig.getSheetName().length() != 0) {
			sheet = wb.createSheet(excelSheetConfig.getSheetName());
		} else {
			sheet = wb.createSheet("sheet");
		}

		int currentRow = 0;
		HSSFRow row;
		HSSFCell cell;

		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) excelSheetConfig.getHeaderFont());
		if (excelSheetConfig.isHeaderBold()) {
			font.setBoldweight((short) 10);
		}

		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(font);

		// Overskrift
		if (excelSheetConfig.getHeading() != null
				&& excelSheetConfig.getHeading().length() != 0) {
			row = sheet.createRow((short) currentRow);
			cell = row.createCell((short) 0);
			cell.setCellValue(excelSheetConfig.getHeading());
			cell.setCellStyle(style);
			currentRow++;
		}

		// Kolonneoverskrift
		row = sheet.createRow((short) currentRow);
		currentRow++;
		int columnCount = currentTableModel.getColumnCount();
		int rowCount = currentTableModel.getRowCount();
		String columnHeading;

		// Create a cell and put a value in it.

		for (int i = 0; i < columnCount; i++) {
			columnHeading = currentTableModel.getColumnName(i);
			cell = row.createCell((short) i);
			cell.setCellValue(columnHeading);
			cell.setCellStyle(style);
		}

		// Data
		int j;
		int k;
		for (j = currentRow; j < rowCount + currentRow; j++) {
			if (labelInfo != null) {
				labelInfo.setText(infoString + (j + 1));
			}
			row = sheet.createRow((short) j);

			for (k = 0; k < columnCount; k++) {
				if (currentTableModel.getValueAt(j - currentRow, k) != null) {
					if (numberCols != null
							&& numberCols.contains(new Integer(k))) {
						row
								.createCell((short) k)
								.setCellValue(
										Double
												.valueOf(
														String
																.valueOf(currentTableModel
																		.getValueAt(
																				j
																						- currentRow,
																				k)))
												.doubleValue());
					} else {
						cell = row.createCell((short) k);
						cell.setCellValue(String.valueOf(currentTableModel
								.getValueAt(j - currentRow, k)));
					}
				}
			}
		}

		StringBuffer formula;

		if (sumColumns != null && sumColumns.length != 0) {
			row = sheet.createRow((short) j);
			row.createCell((short) 0).setCellValue("Sum:");

			for (int l = 0; l < sumColumns.length; l++) {
				formula = new StringBuffer("SUM(").append(sumColumns[l])
						.append(currentRow + 1).append(":").append(
								sumColumns[l]).append(j).append(")");
				row.createCell(getColNumber(sumColumns[l])).setCellFormula(
						formula.toString());
			}

		}
		return wb;
	}

	public void openExcelFileForReading(File file) throws FrafException {
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			readSheet = wb.getSheetAt(0);
			

		} catch (Exception e) {

			e.printStackTrace();
			throw new FrafException(e);
		}

	}

	public String readCell(int row, int col, boolean ignoreDecimal) {
		HSSFRow excelRow = readSheet.getRow(row);
		if (excelRow != null) {
			HSSFCell cell = excelRow.getCell((short) col);
			String value;
			if (cell != null) {
				if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					if (ignoreDecimal) {
						value = String.format("%1$.0f", cell
								.getNumericCellValue());
					} else {
						value = String.format("%1$.2f", cell
								.getNumericCellValue());
					}
				} else {
					value = cell.getStringCellValue();
				}
				if (value != null && value.length() != 0) {
					return value;
				}
			}
		}
		return null;

	}
	
	public String readCellString(int row, int col) {
		HSSFRow excelRow = readSheet.getRow(row);
		if (excelRow != null) {
			HSSFCell cell = excelRow.getCell((short) col);
			String value;
			if (cell != null) {
				if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					
						value = String.format("%1$.0f", cell
								.getNumericCellValue());
				}else{
					value = cell.getStringCellValue();
				}
				
				if (value != null && value.length() != 0) {
					return value;
				}
			}
		}
		return null;

	}
	
	public static void showDataInExcel(String directory, String fileName,
			JLabel labelInfo, String heading, TableModel tableModel,
			List<Integer> numberCols, Map<Integer, Integer> colSize,
			int headFontSize,boolean wrapText) throws FrafException {
		showDataInExcel(directory, fileName, labelInfo, heading, tableModel,
				numberCols, colSize, headFontSize, null, null, null, null, null,wrapText);
	}
	
	public static void showDataInExcel(String directory, String fileName,
			JLabel labelInfo, String heading, TableModel tableModel,
			List<Integer> numberCols, Map<Integer, Integer> colSize,
			int headFontSize, Integer groupColumn,
			List<Integer> notVisibleColumns, Integer groupSumValueColumn,
			Integer groupSumColumn, Integer groupResultColumn,boolean wrapText)
			throws FrafException {
		if (directory == null || directory.length() == 0) {
			throw new FrafException("Katalog ikke satt");
		}
		int notVisibleColumnsSize=0;
		//dersom kolonner skal være usynlige må disse trekkes fra ved laging av celler
		if(notVisibleColumns !=null){
			notVisibleColumnsSize=notVisibleColumns.size();
		}
		String infoString = "Genererer excel-fil...rad ";
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet;

		sheet = wb.createSheet("sheet");

		int currentRow = 0;
		HSSFRow row;
		HSSFCell cell;

		HSSFFont headFont = wb.createFont();
		headFont.setFontHeightInPoints((short) headFontSize);
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		HSSFCellStyle headStyle = wb.createCellStyle();
		headStyle.setFont(headFont);
		headStyle.setFillBackgroundColor(new HSSFColor.BLUE_GREY().getIndex());

		HSSFCellStyle groupStyle = wb.createCellStyle();
		groupStyle.setFont(headFont);
		groupStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		groupStyle.setFillBackgroundColor(HSSFColor.BLUE_GREY.index);
		groupStyle.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
		groupStyle.setBorderBottom((short) 1);
		groupStyle.setBorderLeft((short) 1);
		groupStyle.setBorderRight((short) 1);
		groupStyle.setBorderTop((short) 1);
		groupStyle.setFillPattern((short) 1);

		// Overskrift
		if (heading != null && heading.length() != 0) {
			row = sheet.createRow((short) currentRow);
			cell = row.createCell((short) 0);
			cell.setCellValue(heading);
			cell.setCellStyle(headStyle);
			currentRow++;
		}

		// Kolonneoverskrift
		row = sheet.createRow((short) currentRow);
		currentRow++;
		int columnCount = tableModel.getColumnCount();
		int rowCount = tableModel.getRowCount();
		String columnHeading;

		//Skriver ut kolonneoverskrift
		for (int i = 0; i < columnCount; i++) {
			if (notVisibleColumns == null || !notVisibleColumns.contains(i)) {
				columnHeading = tableModel.getColumnName(i);
				cell = row.createCell((short) i);
				cell.setCellValue(columnHeading);
				cell.setCellStyle(headStyle);
			}
		}
		
		String groupValue = "";
		List<ExcelGroupSum> formulaCells = new ArrayList<ExcelGroupSum>();
		String groupSumValue = "";
		ExcelGroupSum currentExcelGroupSum = null;
		HSSFCellStyle dayStyle = wb.createCellStyle();
		dayStyle.setWrapText(wrapText);

		// Data
		int j;
		int k;
		int l = currentRow;
		//Går gjennom alle rader og kolonner
		for (j = currentRow; j < rowCount + currentRow; j++) {
			//dersom data skal grupperes
			if (groupColumn != null
					&& !tableModel.getValueAt(j - currentRow, groupColumn)
							.equals(groupValue)) {
				//setter forrige grupperingssum
				if (currentExcelGroupSum != null) {
					currentExcelGroupSum.setToRow((short) (l));
					formulaCells.add(currentExcelGroupSum);
					groupSumValue = "";
					currentExcelGroupSum = null;
				}
				//henter grupperingsverdi og setter ny overskrift for gruppering
				groupValue = (String) tableModel.getValueAt(j - currentRow,
						groupColumn);
				row = sheet.createRow((short) l);

				cell = row.createCell((short) 0);
				cell.setCellValue(groupValue);

				cell.setCellStyle(groupStyle);
				sheet.addMergedRegion(new Region((short) l, (short) 0,
						(short) l, (short) (columnCount
								- notVisibleColumnsSize - 1)));
				l++;

			}
			//setter genereringsinfo dersom label ikke er NULL
			if (labelInfo != null) {
				labelInfo.setText(infoString + (j + 1));
			}
			
			row = sheet.createRow((short) l);
			l++;

			//går gjennom alle kolonner for rad
			for (k = 0; k < columnCount; k++) {
				//dersom kolonne skal være synlig
				if (notVisibleColumns == null || !notVisibleColumns.contains(k)) {
					//dersom kolonnebredde er satt
					if (colSize != null) {
						Integer columnSize = colSize.get(k);
						if (columnSize != null) {
							sheet.setColumnWidth((short) k, columnSize
									.shortValue());
						}
					}
					cell = row.createCell((short) k);
					//dersom celle har verdi
					if (tableModel.getValueAt(j - currentRow, k) != null) {

						//dersom det er grupperingssum satt og den er ulik forrige
						if (groupSumValueColumn != null
								&& !tableModel.getValueAt(j - currentRow,
										groupSumValueColumn).equals(
										groupSumValue)) {
							groupSumValue = (String) tableModel.getValueAt(j
									- currentRow, groupSumValueColumn);

							dayStyle = wb.createCellStyle();
							dayStyle.setBorderBottom((short) 1);
							dayStyle.setBorderLeft((short) 1);
							dayStyle.setBorderRight((short) 1);
							dayStyle.setBorderTop((short) 1);
							short dayColorIndex = DayEnum
									.getDayColorIndex(groupSumValue);
							dayStyle.setFillBackgroundColor(dayColorIndex);
							dayStyle.setFillForegroundColor(dayColorIndex);
							dayStyle.setFillPattern((short) 1);

							if (currentExcelGroupSum != null) {
								currentExcelGroupSum.setToRow((short) (l - 1));
								formulaCells.add(currentExcelGroupSum);
							}
							currentExcelGroupSum = new ExcelGroupSum((short) l,
									row.createCell(groupResultColumn
											.shortValue()), groupSumColumn
											.shortValue(), dayColorIndex, wb
											.createCellStyle());

						}
						cell.setCellStyle(dayStyle);

						//dersom kolonne ikke er summeringskolonne
						if (groupResultColumn == null || k != groupResultColumn) {

							if (numberCols != null
									&& numberCols.contains(new Integer(k))) {
								cell.setCellValue(Double.valueOf(
										String.valueOf(tableModel.getValueAt(j
												- currentRow, k)))
										.doubleValue());
							} else {
								Class<?> clazz = tableModel.getColumnClass(k);
								if(clazz.equals(Integer.class)||clazz.equals(BigDecimal.class)){
									cell.setCellValue(Double.valueOf(
											String.valueOf(tableModel.getValueAt(j
													- currentRow, k)))
											.doubleValue());
								}else{
								cell.setCellValue(String.valueOf(tableModel
										.getValueAt(j - currentRow, k)));
								}
							}
						}
						//dersom celle ikke har verdi settes den til tomstreng for å få med eventuell formatering
					} else {
						cell.setCellStyle(dayStyle);
						cell.setCellValue("");
					}
				}
			}
		}
		//setter siste grupperingssum dersom det finnes
		if (currentExcelGroupSum != null) {
			currentExcelGroupSum.setToRow((short) l);
			formulaCells.add(currentExcelGroupSum);
		}

		//går gjennom grupperingssummer og skriver inn formler
		for (ExcelGroupSum excelGroupSum : formulaCells) {
			excelGroupSum.setFormula();

			sheet.addMergedRegion(new Region((short) (excelGroupSum
					.getFromRow() - 1), groupResultColumn.shortValue(),
					(short) (excelGroupSum.getToRow() - 1), groupResultColumn
							.shortValue()));
		}
		openExcelFile(fileName, directory, wb);
	}

	private static class ExcelGroupSum {
		private short sumColumn;

		private HSSFCell resultCell;

		private short fromRow;

		private short toRow;

		ExcelGroupSum(short aFromRow, HSSFCell aResultCell, short aSumColumn,
				short colorIndex, HSSFCellStyle style) {
			fromRow = aFromRow;
			resultCell = aResultCell;

			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			style.setFillBackgroundColor(colorIndex);
			style.setFillForegroundColor(colorIndex);
			style.setFillPattern((short) 1);

			style.setBorderBottom((short) 1);
			style.setBorderLeft((short) 1);
			style.setBorderRight((short) 1);
			style.setBorderTop((short) 1);

			resultCell.setCellStyle(style);

			sumColumn = aSumColumn;
		}

		public void setToRow(short aToRow) {
			toRow = aToRow;
		}

		public short getFromRow() {
			return fromRow;
		}

		public short getToRow() {
			return toRow;
		}

		public void setFormula() {
			StringBuilder stringBuilder = new StringBuilder("SUM(").append(
					getColLetter(sumColumn)).append(fromRow).append(":")
					.append(getColLetter(sumColumn)).append(toRow).append(")");

			resultCell.setCellFormula(stringBuilder.toString());

		}
	}

	public static void exportToExcel(WindowInterface window,TableModel excelTableModel,ApplUser aApplUser,List<Integer> someNumCols,String aFileName){
		GuiUtil.runInThreadWheel(window.getRootPane(),new ExcelUtil.ExcelExporter(window,excelTableModel,aApplUser,someNumCols,aFileName),null);
	}
	
	public static class ExcelExporter implements Threadable{
		private TableModel tableModel;
		private ApplUser applUser;
		private List<Integer> numCols;
		private WindowInterface window;
		private String fileName;
		
		public ExcelExporter(WindowInterface aWindow,TableModel excelTableModel,ApplUser aApplUser,List<Integer> someNumCols,String aFileName){
			fileName=aFileName;
			window=aWindow;
			numCols=someNumCols;
			applUser=aApplUser;
			tableModel=excelTableModel;
		}

		public void enableComponents(boolean enable) {
			// TODO Auto-generated method stub
			
		}

		public Object doWork(Object[] params, JLabel labelInfo) {
			String msg = null;
			try {

				final ExcelUtil excelUtil = new ExcelUtil(
						tableModel,
						"", "Sheet1");
				final String dir = excelUtil.prepare(applUser, window.getComponent());


				excelUtil.showDataInExcel(dir, fileName, (String[]) null,
						numCols, labelInfo);
			} catch (FrafException e) {
				msg = e.getMessage();
			}
			return msg;
		}

		public void doWhenFinished(Object object) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
