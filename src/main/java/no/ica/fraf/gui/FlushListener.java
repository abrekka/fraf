package no.ica.fraf.gui;

/**
 * Interface for lyttere til om flushing pågår
 * 
 * @author abr99
 * 
 */
public interface FlushListener {
	/**
	 * @param flushing
	 */
	void flushChanged(boolean flushing);
}
