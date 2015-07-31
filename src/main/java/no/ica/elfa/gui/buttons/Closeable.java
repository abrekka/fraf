package no.ica.elfa.gui.buttons;

/**
 * Interface for klasser som skal bruke avbrytknapp
 * @author abr99
 *
 */
public interface Closeable {
	/**
	 * Sjekker om dialog kan stenges
	 * @param actionString
	 * @return true dersom dialog kan stenges
	 */
	boolean canClose(String actionString);
}
