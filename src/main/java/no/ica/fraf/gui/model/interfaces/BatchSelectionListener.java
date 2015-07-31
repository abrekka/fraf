package no.ica.fraf.gui.model.interfaces;

/**
 * Interface for klasser som skal lytte på hendelser om at bunt er valgt
 * 
 * @author abr99
 * 
 */
public interface BatchSelectionListener {
	/**
	 * Denne metoden vil bli kjørt på lytter når bunt er selektert
	 * 
	 * @param batchId
	 */
	public void batchSelected(Integer batchId);
}
