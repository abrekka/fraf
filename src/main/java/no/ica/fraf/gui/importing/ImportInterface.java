package no.ica.fraf.gui.importing;

import java.io.File;
import java.util.List;

import javax.swing.JTable;

import no.ica.fraf.FrafException;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.model.ImportBetingelse;

/**
 * Interface som brukes av vindu for import ved import av betingelser og
 * budsjett
 * 
 * @author abr99
 * 
 */
public interface ImportInterface {

	/**
	 * Henter TableModel for importtabell
	 * 
	 * @return TableModel for importtabell
	 */
	public ObjectTableModel<Object> getTableModelImport();

	/**
	 * Setter kolonnebredde for importtabell
	 * 
	 * @param table
	 */
	public void setColumnWidthsImport(JTable table);

	/**
	 * Henter alle importobjekter
	 * 
	 * @return alle importobjekter
	 */
	public List<Object> findAllImport();

	/**
	 * Kjører selve importen
	 * 
	 * @param readFile
	 * @param file
	 * @param hasHead
	 * @param param
	 * @return resultat av eksport
	 */
	public Object doImport(boolean readFile, File file, boolean hasHead,
			Object param);

	/**
	 * Sletter importobjekter
	 * 
	 * @param list
	 */
	public void deleteListImport(List list);

	/**
	 * Henter TableModel for loggtabell
	 * 
	 * @return TableModel for loggtabell
	 */
	public ObjectTableModel<Batchable> getTableModelBatch();

	/**
	 * Setter kolonnebredder for loggtabell
	 * 
	 * @param table
	 */
	public void setColumnWidthsBatch(JTable table);

	/**
	 * Henter TableModel for detaljtabell
	 * 
	 * @return TableModel for detaljtabell
	 */
	public ObjectTableModel<Object> getTableModelDetails();

	/**
	 * Setter kolonnebredder for detaljtabell
	 * 
	 * @param table
	 */
	public void setColumnWidthsDetails(JTable table);

	/**
	 * Finner bunter for gitt importtype
	 * 
	 * @return bunter for gitt importtype
	 */
	public List<Batchable> findBatches();

	/**
	 * Finner detaljer tilhørende en bunt
	 * 
	 * @param object
	 * @return detaljer tilhørende en bunt
	 */
	public List<Object> findDetailsByBatch(Object object);

	/**
	 * Ruller tilbake bunt
	 * 
	 * @param object
	 * @throws FrafException
	 */
	public void rollback(Object object) throws FrafException;

	/**
	 * Importerer lukket avdeling
	 * 
	 * @param importBetingelser
	 */
	public void importClosedDepartment(List<ImportBetingelse> importBetingelser);

	/**
	 * Henter hjelpetekst for import
	 * 
	 * @return hjelpetekst
	 */
	String getHelpText();
}
