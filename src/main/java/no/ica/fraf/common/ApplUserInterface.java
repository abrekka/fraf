package no.ica.fraf.common;

import no.ica.fraf.model.ApplUser;

/**
 * Interface for klasser som representerer brukertabeller
 * 
 * @author abr99
 * 
 */
public interface ApplUserInterface {
	/**
	 * @return fornavn
	 */
	String getFirstName();

	/**
	 * @return etternavnO
	 */
	String getSurname();
	ApplUser getApplUser();
}
