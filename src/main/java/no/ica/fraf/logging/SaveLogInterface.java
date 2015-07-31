package no.ica.fraf.logging;

/**
 * Interface som brukes til lagring av logg i database
 * @author abr99
 *
 */
public interface SaveLogInterface {
	/**
	 * Lagrer logg til database
	 * @param object objekt som skal lagres
	 */
	public void save(Object object);
}
