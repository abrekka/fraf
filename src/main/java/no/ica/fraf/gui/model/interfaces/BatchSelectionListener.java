package no.ica.fraf.gui.model.interfaces;

/**
 * Interface for klasser som skal lytte p� hendelser om at bunt er valgt
 * 
 * @author abr99
 * 
 */
public interface BatchSelectionListener {
	/**
	 * Denne metoden vil bli kj�rt p� lytter n�r bunt er selektert
	 * 
	 * @param batchId
	 */
	public void batchSelected(Integer batchId);
}
