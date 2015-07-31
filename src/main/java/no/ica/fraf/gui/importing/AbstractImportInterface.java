package no.ica.fraf.gui.importing;

import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import no.ica.fraf.FrafException;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.dao.pkg.BuntPkgDAO;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.ImportBetingelse;
import no.ica.fraf.util.ModelUtil;

/**
 * Dette er en abstrakt klasse som implementerer ImportInterface og som brukes
 * av de klassene som brukes til å importere betingelser eller budsjett
 * 
 * @author abr99
 * 
 */
public abstract class AbstractImportInterface implements ImportInterface {
	/**
	 * Kolonnenavn for loggepanel
	 */
	private static final String[] COLUMN_NAMES_LOG = { "Buntnr", "Dato",
			"Bruker" };

	/**
	 * Kolonnemetoder for loggepanel
	 */
	private static final String[] METHODS_LOG = { "BuntId", "OpprettetDato",
			"ApplUser" };

	/**
	 * Klassetyper for kolonner i loggepanel
	 */
	private static final Class[] PARAMS_LOG = { Integer.class, Date.class,
			ApplUser.class };

	/**
	 * Def for tabell i loggepanel
	 */
	private static final ObjectTableDef LOG_TABLE_DEF = new ObjectTableDef(
			COLUMN_NAMES_LOG, METHODS_LOG, PARAMS_LOG);

	/**
	 * Tabell i loggepanel
	 */
	private ObjectTableModel<Batchable> logTableModel = new ObjectTableModel<Batchable>(LOG_TABLE_DEF);

	/**
	 * DAO for bunt
	 */
	private BuntPkgDAO buntPkgDAO = (BuntPkgDAO) ModelUtil
			.getBean("buntPkgDAO");

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#rollback(java.lang.Object)
	 */
	public void rollback(Object object) throws FrafException {
		buntPkgDAO.slettBunt(((Bunt) object).getBuntId());
	}

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#getTableModelBatch()
	 */
	public ObjectTableModel<Batchable> getTableModelBatch() {
		return logTableModel;
	}

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#setColumnWidthsBatch(javax.swing.JTable)
	 */
	public void setColumnWidthsBatch(JTable table) {
		// Buntnr
		TableColumn col = table.getColumnModel().getColumn(0);
		col.setPreferredWidth(60);
		// col.setCellEditor(new no.ica.fraf.gui.model.DateEditor());

		// Dato
		col = table.getColumnModel().getColumn(1);
		col.setPreferredWidth(100);

		// Bruker
		col = table.getColumnModel().getColumn(2);
		col.setPreferredWidth(100);

	}

	/**
	 * @see no.ica.fraf.gui.importing.ImportInterface#importClosedDepartment(java.util.List)
	 */
	public void importClosedDepartment(List<ImportBetingelse> importBetingelser) {

	}
}
