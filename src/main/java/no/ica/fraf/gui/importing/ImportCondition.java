package no.ica.fraf.gui.importing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import no.ica.fraf.common.Batchable;
import no.ica.fraf.dao.AvdelingBetingelseDAO;
import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.dao.AvregningFrekvensTypeDAO;
import no.ica.fraf.dao.AvregningTypeDAO;
import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.dao.BuntTypeDAO;
import no.ica.fraf.dao.ImportBetingelseDAO;
import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.dao.pkg.ImportBetingelsePkgDAO;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.AvregningFrekvensType;
import no.ica.fraf.model.AvregningType;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntStatus;
import no.ica.fraf.model.BuntType;
import no.ica.fraf.model.ImportBetingelse;
import no.ica.fraf.model.Mva;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.apache.log4j.Logger;

/**
 * Klasse som håndterer import av betingelser
 * 
 * @author abr99
 * 
 */
public class ImportCondition extends AbstractImportInterface {

	/**
	 * Kolonnenavn for betingelsetabell
	 */
	private static final String[] COLUMN_NAMES_CONDITION = { "Avdeling",
			"Product", "Beløp", "Fra dato", "Til dato", "Betingelse",
			"Frekvens", "Avregning", "Selskap", "Konto", "Avdeling", "Mvakode",
			"Fakturalinjetekst", "Fakturatekst", "Rekkefølge" };

	/**
	 * Kolonnemetoder for betingelsetabell
	 */
	private static final String[] METHODS_CONDITION = { "Avdeling", "Prosjekt",
			"Belop", "FraDato", "TilDato", "BetingelseType",
			"AvregningFrekvensType", "AvregningType", "BokfSelskap", "Konto",
			"BokfAvdeling", "Mva", "Tekst", "FakturaTekst", "FakturaTekstRek" };

	/**
	 * Klassetyper for betingelsetabell
	 */
	private static final Class[] PARAMS_CONDITION = { Avdeling.class,
			String.class, BigDecimal.class, Date.class, Date.class,
			BetingelseType.class, AvregningFrekvensType.class,
			AvregningType.class, BokfSelskap.class, String.class, String.class,
			Mva.class, String.class, String.class, Integer.class };

	/**
	 * Kolonneformat for betingelsetabell
	 */
	private static final ColumnFormatEnum[] FORMAT_COLUMNS_CONDITION = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.CURRENCY,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE };

	/**
	 * Def for betingelsetabell
	 */
	private static final ObjectTableDef CONDITION_TABLE_DEF = new ObjectTableDef(
			COLUMN_NAMES_CONDITION, METHODS_CONDITION, PARAMS_CONDITION,
			FORMAT_COLUMNS_CONDITION);

	/**
	 * TableModel for betingelstabell
	 */
	private ObjectTableModel<Object> conditionTableModel = new ObjectTableModel<Object>(
			CONDITION_TABLE_DEF);

	/**
	 * Kolonnenavn for importtabell
	 */
	private static String[] columnNames = { "Importdato", "Feilmelding",
			"Avdnr", "Product", "Sum pr. frekvens", "Fra dato", "Til dato",
			"Betingelsetype", "Frekvens", "Avregningstype", "Selskap", "Konto",
			"Avdeling", "Mvakode", "Fakturalinjetekst", "Fakturatekst",
			"Rekkefølge" };

	/**
	 * Kolonnemetoder for importtabell
	 */
	private static String[] methods = { "ImportDato", "Feilmelding", "Avdnr",
			"Prosjekt", "SumPrFrekvens", "FraDato", "TilDato",
			"BetingelseTypeKode", "FrekvensKode", "AvregningTypeKode",
			"SelskapNavn", "Konto", "BokfAvdeling", "MvaKode", "Tekst",
			"FakturaTekst", "FakturaTekstRek" };

	/**
	 * Klassetyper for importtabell
	 */
	private static Class[] params = { Date.class, String.class, Integer.class,
			String.class, BigDecimal.class, Date.class, Date.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, Integer.class };

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
	 * DAO for importbetingelse
	 */
	private ImportBetingelseDAO importBetingelseDAO = (ImportBetingelseDAO) ModelUtil
			.getBean("importBetingelseDAO");

	/**
	 * Bruker
	 */
	private ApplUser currentApplUser;

	/**
	 * DAO til databasepakke IMPORT_BETINGELSE_PKG
	 */
	private ImportBetingelsePkgDAO importBetingelsePkgDAO = (ImportBetingelsePkgDAO) ModelUtil
			.getBean("importBetingelsePkgDAO");

	/**
	 * DAO for bunt
	 */
	private BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");

	/**
	 * DAO for betingelse
	 */
	private AvdelingBetingelseDAO avdelingBetingelseDAO = (AvdelingBetingelseDAO) ModelUtil
			.getBean("avdelingBetingelseDAO");

	/**
	 * DAO for avdeling
	 */
	private AvdelingDAO avdelingDAO = (AvdelingDAO) ModelUtil
			.getBean("avdelingDAO");

	/**
	 * DAO for frekvens
	 */
	private AvregningFrekvensTypeDAO avregningFrekvensTypeDAO = (AvregningFrekvensTypeDAO) ModelUtil
			.getBean("avregningFrekvensTypeDAO");

	/**
	 * DAO for avregning
	 */
	private AvregningTypeDAO avregningTypeDAO = (AvregningTypeDAO) ModelUtil
			.getBean("avregningTypeDAO");

	/**
	 * DAO for betingelsetype
	 */
	private BetingelseTypeDAO betingelseTypeDAO = (BetingelseTypeDAO) ModelUtil
			.getBean("betingelseTypeDAO");

	/**
	 * DAO for bokføringsselskap
	 */
	private BokfSelskapDAO bokfSelskapDAO = (BokfSelskapDAO) ModelUtil
			.getBean("bokfSelskapDAO");

	/**
	 * DAO for buntstatus
	 */
	private BuntStatusDAO buntStatusDAO = (BuntStatusDAO) ModelUtil
			.getBean("buntStatusDAO");

	/**
	 * DAo for bunttype
	 */
	private BuntTypeDAO buntTypeDAO = (BuntTypeDAO) ModelUtil
			.getBean("buntTypeDAO");

	/**
	 * Logger til database
	 */
	private static Logger logger = Logger.getLogger(ImportCondition.class);

	/**
	 * Kosntruktør
	 * 
	 * @param aInternalFrame
	 * @param applUser
	 */
	public ImportCondition(JInternalFrame aInternalFrame, ApplUser applUser) {

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
		// Importdato
		TableColumn col = table.getColumnModel().getColumn(0);
		col.setPreferredWidth(80);

		// Feilmelding
		col = table.getColumnModel().getColumn(1);
		col.setPreferredWidth(135);

		// Avdnr
		col = table.getColumnModel().getColumn(2);
		col.setPreferredWidth(50);

		// Prosjekt
		col = table.getColumnModel().getColumn(3);
		col.setPreferredWidth(50);

		// Sum pr. frekvens
		col = table.getColumnModel().getColumn(4);
		col.setPreferredWidth(100);

		// Fra dato
		col = table.getColumnModel().getColumn(5);
		col.setPreferredWidth(80);

		// Til dato
		col = table.getColumnModel().getColumn(6);
		col.setPreferredWidth(80);

		// Betingelsetype
		col = table.getColumnModel().getColumn(7);
		col.setPreferredWidth(100);

		// Frekvens
		col = table.getColumnModel().getColumn(8);
		col.setPreferredWidth(70);

		// Avregningstype
		col = table.getColumnModel().getColumn(9);
		col.setPreferredWidth(90);

		// Selskap
		col = table.getColumnModel().getColumn(10);
		col.setPreferredWidth(70);

		// Konto
		col = table.getColumnModel().getColumn(11);
		col.setPreferredWidth(70);

		// Avdeling
		col = table.getColumnModel().getColumn(12);
		col.setPreferredWidth(70);

		// Mvakode
		col = table.getColumnModel().getColumn(13);
		col.setPreferredWidth(70);

		// Fakturalinjetekst
		col = table.getColumnModel().getColumn(14);
		col.setPreferredWidth(135);

		// Fakturatekst
		col = table.getColumnModel().getColumn(15);
		col.setPreferredWidth(135);

		// Rekkefølge
		col = table.getColumnModel().getColumn(16);
		col.setPreferredWidth(60);

	}

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#findAllImport()
	 */
	public List<Object> findAllImport() {
		return importBetingelseDAO.findAll();
	}

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#doImport(boolean,
	 *      java.io.File, boolean, java.lang.Object)
	 */
	public Object doImport(boolean readFile, File file, boolean hasHead,
			Object param) {

		StringBuffer errorMsg = null;
		try {
			if (readFile) {
				FileReader conditionFile = new FileReader(file);
				BufferedReader bufConFile = new BufferedReader(conditionFile);
				String oneLine;
				String[] stringCol;

				int lineCount = 0;
				ImportBetingelse importBetingelse;

				if (hasHead) {
					bufConFile.readLine(); // fjerner heading
				}
				SimpleDateFormat dateFormat = GuiUtil.SIMPLE_DATE_FORMAT;
				dateFormat.setLenient(false);

				while ((oneLine = bufConFile.readLine()) != null) {
					try {
						lineCount++;
						// Splitter linje i deler basert på semikolon
						stringCol = oneLine.split(";");

						if (stringCol == null || stringCol.length < 6) {
							errorMsg = new StringBuffer("Linje ").append(
									lineCount).append(
									"inneholder for få kolonner.");
						} else {
							importBetingelse = new ImportBetingelse();

							importBetingelse.setImportDato(Calendar
									.getInstance().getTime());
							// Avdnr

							importBetingelse.setAvdnr(Integer
									.valueOf(stringCol[0]));

							// Prosjekt
							importBetingelse.setProsjekt(stringCol[1]);
							// Beløp
							importBetingelse.setSumPrFrekvens(new BigDecimal(
									stringCol[2].replaceAll(",", ".")
											.replaceAll(" ", "")));
							// Fra dato
							importBetingelse.setFraDato(dateFormat
									.parse(stringCol[3]));
							if (stringCol[4] != null
									&& stringCol[4].length() != 0) {
								// Til dato
								importBetingelse.setTilDato(dateFormat
										.parse(stringCol[4]));
							}
							// Betingelse
							importBetingelse.setBetingelseTypeKode(stringCol[5]
									.trim());
							if (stringCol.length >= 7) {
								// Frekvens
								importBetingelse.setFrekvensKode(stringCol[6]
										.trim());
							}
							if (stringCol.length >= 8) {
								// Avregning
								importBetingelse
										.setAvregningTypeKode(stringCol[7]
												.trim());
							}

							// Selskap
							if (stringCol.length >= 9) {
								if (stringCol[7].length() != 0) {
									importBetingelse
											.setSelskapNavn(stringCol[8]);
								} else {
									importBetingelse.setSelskapNavn(null);
								}
							}
							// Konto
							if (stringCol.length >= 10) {
								importBetingelse.setKonto(stringCol[9]);
							}
							// Bokførinsavdeling
							if (stringCol.length >= 11) {
								importBetingelse.setBokfAvdeling(stringCol[10]);
							}
							// Mvakode
							if (stringCol.length >= 12) {
								importBetingelse.setMvaKode(stringCol[11]);
							}
							// Tekst
							if (stringCol.length >= 13) {
								importBetingelse.setTekst(stringCol[12]);
							}
							// Fakturatekst
							if (stringCol.length >= 14) {
								importBetingelse.setFakturaTekst(stringCol[13]);
							}
							// Rekkefølge
							if (stringCol.length >= 15) {
								importBetingelse
										.setFakturaTekstRek(new Integer(
												stringCol[14]));
							}

							importTableModel.addRow(importBetingelse);
							importBetingelseDAO
									.saveImportBetingelse(importBetingelse);
						}
					} catch (Exception ex) {
						String exMsg = GuiUtil.getUserExceptionMsg(ex);
						errorMsg = new StringBuffer(
								"Det oppstod feil ved import av rad ").append(
								lineCount).append(": ").append(exMsg);
						logger.error("Feil ved import av betingelser,håndtert",
								ex);
						ex.printStackTrace();
					}
				}
			}

			if (errorMsg == null) {
				importBetingelsePkgDAO.importBetingelser(currentApplUser
						.getUserId());
			}
		} catch (Exception e) {
			errorMsg = new StringBuffer("Feil ved import av betingelser ")
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
		importBetingelseDAO.deleteList(list);

	}

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#getTableModelDetails()
	 */
	public ObjectTableModel<Object> getTableModelDetails() {
		return conditionTableModel;
	}

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#setColumnWidthsDetails(javax.swing.JTable)
	 */
	public void setColumnWidthsDetails(JTable table) {
		// Avdeling
		TableColumn col = table.getColumnModel().getColumn(0);
		col.setPreferredWidth(60);

		// Beløp
		col = table.getColumnModel().getColumn(1);
		col.setPreferredWidth(80);

		// Fra dato
		col = table.getColumnModel().getColumn(2);
		col.setPreferredWidth(80);

		// Til dato
		col = table.getColumnModel().getColumn(3);
		col.setPreferredWidth(80);

		// Betingelse
		col = table.getColumnModel().getColumn(4);
		col.setPreferredWidth(80);

		// Frekvens
		col = table.getColumnModel().getColumn(5);
		col.setPreferredWidth(80);

		// Avregning
		col = table.getColumnModel().getColumn(6);
		col.setPreferredWidth(100);

		// Selskap
		col = table.getColumnModel().getColumn(7);
		col.setPreferredWidth(60);

		// Konto
		col = table.getColumnModel().getColumn(8);
		col.setPreferredWidth(60);

		// Avdeling
		col = table.getColumnModel().getColumn(9);
		col.setPreferredWidth(60);

		// Mvakode
		col = table.getColumnModel().getColumn(10);
		col.setPreferredWidth(60);

		// Fakturalinjetekst
		col = table.getColumnModel().getColumn(11);
		col.setPreferredWidth(120);

		// Fakturatekst
		col = table.getColumnModel().getColumn(12);
		col.setPreferredWidth(120);

		// Rekkefølge
		col = table.getColumnModel().getColumn(13);
		col.setPreferredWidth(60);
	}

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#findBatches()
	 */
	public List<Batchable> findBatches() {
		return buntDAO.findImportBunter();
	}

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#findDetailsByBatch(java.lang.Object)
	 */
	public List<Object> findDetailsByBatch(Object object) {
		return avdelingBetingelseDAO.findByBatch((Bunt) object);
	}

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#importClosedDepartment(java.util.List)
	 */
	@Override
	public void importClosedDepartment(List<ImportBetingelse> importBetingelser) {
		if (importBetingelser != null) {
			Avdeling avdeling;
			AvregningFrekvensType avregningFrekvensType;
			AvregningType avregningType;
			BetingelseType betingelseType;
			BokfSelskap bokfSelskap = null;
			Mva mva = null;
			AvdelingBetingelse avdelingBetingelse;
			MvaDAO mvaDAO = (MvaDAO) ModelUtil.getBean("mvaDAO");

			BuntStatus buntStatus = buntStatusDAO
					.findByKode(BuntStatusEnum.NY);
			BuntType buntType = buntTypeDAO
					.findByKode(BuntTypeEnum.BATCH_TYPE_IMPORT);

			Bunt bunt = new Bunt();
			bunt.setApplUser(currentApplUser);
			bunt.setBuntStatus(buntStatus);
			bunt.setBuntType(buntType);
			bunt.setOpprettetDato(Calendar.getInstance().getTime());
			buntDAO.saveBunt(bunt);

			for (ImportBetingelse importBetingelse : importBetingelser) {
				betingelseType = betingelseTypeDAO.findByKode(importBetingelse
						.getBetingelseTypeKode());
				avdeling = avdelingDAO.findByAvdnr(importBetingelse.getAvdnr());
				if (importBetingelse.getFrekvensKode() != null) {
					avregningFrekvensType = avregningFrekvensTypeDAO
							.findByKode(importBetingelse.getFrekvensKode());
				} else {
					avregningFrekvensType = betingelseType
							.getAvregningFrekvensType();
				}
				if (importBetingelse.getAvregningTypeKode() != null) {
					avregningType = avregningTypeDAO
							.findByKode(importBetingelse.getAvregningTypeKode());
				} else {
					avregningType = betingelseType.getAvregningType();
				}

				if (importBetingelse.getSelskapNavn() != null) {
					bokfSelskap = bokfSelskapDAO.findByName(importBetingelse
							.getSelskapNavn());
				}

				if (importBetingelse.getMvaKode() != null) {
					mva = mvaDAO.findByMvaKode(importBetingelse.getMvaKode());
				}

				avdelingBetingelse = new AvdelingBetingelse();

				if (avdeling != null) {
					avdelingBetingelse.setAvdeling(avdeling);
					avdelingBetingelse
							.setAvregningFrekvensType(avregningFrekvensType);
					avdelingBetingelse.setAvregningType(avregningType);
					avdelingBetingelse.setBelop(importBetingelse
							.getSumPrFrekvens());
					avdelingBetingelse.setBetingelseType(betingelseType);
					avdelingBetingelse.setBokfSelskap(bokfSelskap);
					avdelingBetingelse.setBunt(bunt);
					avdelingBetingelse
							.setFraDato(importBetingelse.getFraDato());
					avdelingBetingelse
							.setTilDato(importBetingelse.getTilDato());
					avdelingBetingelse.setTekst(importBetingelse.getTekst());
					avdelingBetingelse.setKonto(importBetingelse.getKonto());
					avdelingBetingelse.setBokfAvdeling(importBetingelse
							.getBokfAvdeling());
					avdelingBetingelse.setMva(mva);
					avdelingBetingelse.setFakturaTekst(importBetingelse
							.getFakturaTekst());
					avdelingBetingelse.setFakturaTekstRek(importBetingelse
							.getFakturaTekstRek());
					avdelingBetingelse.setProsjekt(importBetingelse
							.getProsjekt());

					avdelingBetingelseDAO
							.saveAvdelingBetingelse(avdelingBetingelse);
					importBetingelseDAO.removeImportBetingelse(importBetingelse
							.getImportBetingelseId());
				}
			}
		}
	}

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#getHelpText()
	 */
	public String getHelpText() {
		return "Importfil er av typen semikolonseparert(csv), formatet er som følger:\n"
				+ "Avdnr;Beløp;Fra dato;Til dato;Betingelse;Frekvens;Avregning;Selskap;Konto;Bokføringsavdeling;Mvakode;Tekst på fakturalinje;Tekst nede på faktura;Rekkefølge.\n"
				+ "Alt til og med Avregning må være med, resten tas med etter behov.\n"
				+ "Alle data må være på Concorde-format";
	}
}
