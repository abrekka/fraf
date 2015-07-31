package no.ica.fraf.gui.importing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import no.ica.fraf.common.Batchable;
import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.dao.AvdelingOmsetningDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.dao.BuntTypeDAO;
import no.ica.fraf.dao.pkg.AvdelingOmsetningPkgDAO;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.YesNoInteger;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntStatus;
import no.ica.fraf.model.BuntType;
import no.ica.fraf.util.ModelUtil;

import org.apache.log4j.Logger;

/**
 * Klasse som håndterer import av budsjett
 * 
 * @author abr99
 * 
 */
public class ImportBudget extends AbstractImportInterface {
	/**
	 * Kolonnenavn for importtabell
	 */
	private static String[] columnNames = { "Avdnr", "Navn", "Budsjett",
			"Feilmedling" };

	/**
	 * Kolonnemetoder for importtabell
	 */
	private static String[] methods = { "Avdnr", "Navn", "Budsjett",
			"Feilmelding" };

	/**
	 * Klassetyper for kolonner for importtabell
	 */
	private static Class[] params = { Integer.class, String.class,
			BigDecimal.class, String.class };

	/**
	 * Def for importtabell
	 */
	private static ObjectTableDef importTableDef = new ObjectTableDef(
			columnNames, methods, params);

	/**
	 * TableModel for importtabell
	 */
	private ObjectTableModel<Object> importTableModel = new ObjectTableModel<Object>(
			importTableDef);

	/**
	 * Kolonnenavn for budsjettabell
	 */
	private static final String[] COLUMN_NAMES_BUDGET = { "Avdeling", "År",
			"Periode", "Beløp", "Korreksjon", "Korrigert beløp", "Kommentar",
			"Manuell" };

	/**
	 * Kolonnemetoder for budsjettabell
	 */
	private static final String[] METHODS_BUDGET = { "Avdeling", "Aar",
			"Periode", "Belop", "KorreksjonBelop", "KorrigertBelop",
			"KorreksjonKommentar", "Manuell" };

	/**
	 * Klassetyper for budsjettabell
	 */
	private static final Class[] PARAMS_BUDGET = { Avdeling.class,
			Integer.class, Integer.class, BigDecimal.class, BigDecimal.class,
			BigDecimal.class, String.class, YesNoInteger.class };

	/**
	 * Kolonneformat for budsjettabell
	 */
	private static final ColumnFormatEnum[] FORMAT_COLUMNS_BUDGET = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.CURRENCY,
			ColumnFormatEnum.CURRENCY, ColumnFormatEnum.CURRENCY,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE };

	/**
	 * Def for budsjettabell
	 */
	private static final ObjectTableDef BUDGET_TABLE_DEF = new ObjectTableDef(
			COLUMN_NAMES_BUDGET, METHODS_BUDGET, PARAMS_BUDGET,
			FORMAT_COLUMNS_BUDGET);

	/**
	 * TableModel for budsjettabell
	 */
	private ObjectTableModel<Object> budgetTableModel = new ObjectTableModel<Object>(
			BUDGET_TABLE_DEF);

	/**
	 * Bruker
	 */
	private ApplUser currentApplUser;

	/**
	 * DAO for avdeling
	 */
	private AvdelingDAO avdelingDAO = (AvdelingDAO) ModelUtil
			.getBean("avdelingDAO");

	/**
	 * DAO for pakke AVDELING_OMSETNING_PKG
	 */
	private AvdelingOmsetningPkgDAO avdelingOmsetningPkgDAO = (AvdelingOmsetningPkgDAO) ModelUtil
			.getBean("avdelingOmsetningPkgDAO");

	/**
	 * DAO for buntstatus
	 */
	private BuntStatusDAO buntStatusDAO = (BuntStatusDAO) ModelUtil
			.getBean("buntStatusDAO");

	/**
	 * DAO for bunttype
	 */
	private BuntTypeDAO buntTypeDAO = (BuntTypeDAO) ModelUtil
			.getBean("buntTypeDAO");

	/**
	 * DAO for bunt
	 */
	private BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");

	/**
	 * DAO for omsetning
	 */
	private AvdelingOmsetningDAO avdelingOmsetningDAO = (AvdelingOmsetningDAO) ModelUtil
			.getBean("avdelingOmsetningDAO");

	/**
	 * Feil oppstått under import
	 */
	private List<Object> feilList;

	/**
	 * Logger til database
	 */
	private static Logger logger = Logger.getLogger(ImportBudget.class);

	/**
	 * Konstruktør
	 * 
	 * @param applUser
	 */
	public ImportBudget(ApplUser applUser) {
		currentApplUser = applUser;
	}

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#getTableModelImport()
	 */
	public ObjectTableModel<Object> getTableModelImport() {
		return importTableModel;
	}

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#setColumnWidthsImport(javax.swing.JTable)
	 */
	public void setColumnWidthsImport(JTable table) {
		// Avdnr
		TableColumn col = table.getColumnModel().getColumn(0);
		col.setPreferredWidth(50);

		// Navn
		col = table.getColumnModel().getColumn(1);
		col.setPreferredWidth(200);

		// Budsjett
		col = table.getColumnModel().getColumn(2);
		col.setPreferredWidth(100);

		// Feilmelding
		col = table.getColumnModel().getColumn(3);
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
	 *      java.io.File, boolean,Object)
	 */
	public Object doImport(boolean readFile, File file, boolean hasHead,
			Object param) {
		StringBuffer errorMsg = null;
		try {
			Integer year = (Integer) param;
			BudgetImport budgetImport;
			feilList = new ArrayList<Object>();

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

						if (stringCol == null || stringCol.length < 3) {
							errorMsg = new StringBuffer("Linje ").append(
									lineCount).append(
									" inneholder for få kolonner.");
						} else {
							budgetImport = new BudgetImport();
							budgetImport.setAvdnr(new Integer(stringCol[0]));
							budgetImport.setNavn(stringCol[1]);
							budgetImport.setBudsjett(new BigDecimal(
									stringCol[2].replaceAll(",", ".")
											.replaceAll(" ", "")));

							importTableModel.addRow(budgetImport);
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
				List importList = importTableModel.getData();
				Avdeling avdeling;
				BuntStatus buntStatus = buntStatusDAO
						.findByKode(BuntStatusEnum.NY);
				BuntType buntType = buntTypeDAO
						.findByKode(BuntTypeEnum.BATCH_TYPE_BUDGET);

				Bunt bunt = new Bunt();
				bunt.setAar(year);
				bunt.setApplUser(currentApplUser);
				bunt.setBuntStatus(buntStatus);
				bunt.setBuntType(buntType);
				bunt.setOpprettetDato(Calendar.getInstance().getTime());
				buntDAO.saveBunt(bunt);

				if (importList != null) {
					Iterator listIt = importList.iterator();

					while (listIt.hasNext()) {
						budgetImport = (BudgetImport) listIt.next();
						avdeling = avdelingDAO.findByAvdnr(budgetImport
								.getAvdnr());

						if (avdeling != null) {
							avdelingOmsetningPkgDAO.settInnManueltBudsjett(
									currentApplUser.getUserId(), budgetImport
											.getAvdnr(), year, budgetImport
											.getBudsjett(), avdeling
											.getAvdelingId(), bunt.getBuntId());
						} else {
							budgetImport.setFeilmelding("Finner ikke avdeling");
							feilList.add(budgetImport);
						}
					}
				}
			}

		} catch (Exception e) {
			errorMsg = new StringBuffer("Feil ved import av budsjett ")
					.append(e.getMessage());
			logger.error("Feil ved import av betingelser", e);
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
		return budgetTableModel;
	}

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#setColumnWidthsDetails(javax.swing.JTable)
	 */
	public void setColumnWidthsDetails(JTable table) {
		// Avdeling
		TableColumn col = table.getColumnModel().getColumn(0);
		col.setPreferredWidth(70);

		// År
		col = table.getColumnModel().getColumn(1);
		col.setPreferredWidth(50);

		// Periode
		col = table.getColumnModel().getColumn(2);
		col.setPreferredWidth(50);

		// Beløp
		col = table.getColumnModel().getColumn(3);
		col.setPreferredWidth(100);

		// Korreksjon
		col = table.getColumnModel().getColumn(4);
		col.setPreferredWidth(100);

		// Korrigert beløp
		col = table.getColumnModel().getColumn(5);
		col.setPreferredWidth(100);

		// Kommentar
		col = table.getColumnModel().getColumn(6);
		col.setPreferredWidth(100);

		// Manuell
		col = table.getColumnModel().getColumn(7);
		col.setPreferredWidth(50);

	}

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#findBatches()
	 */
	public List<Batchable> findBatches() {
		return buntDAO.findInnlesBudgetBunter();
	}

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#findDetailsByBatch(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findDetailsByBatch(Object object) {
		return avdelingOmsetningDAO.findByBunt(((Bunt) object).getBuntId());
	}

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#getHelpText()
	 */
	public String getHelpText() {
		return "Importfil er av typen semikolonseparert(csv), formatet er som følger:\n"
				+ "Avdnr;Navn;Årsbeløp;.\n"
				+ "Alle felter må være med, navn kan være tomt.\n";
	}

}
