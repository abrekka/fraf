package no.ica.fraf.enums;

/**
 * Enum for lazyload av avdeling
 * 
 * @author abr99
 * 
 */
public enum LazyLoadAvdelingEnum {
	/**
	 * Lazy load av faktura
	 */
	LOAD_INVOICE("Fakturas"),
	/**
	 * Lazy load av kontrakt
	 */
	LOAD_CONTRACT("AvdelingKontrakts"),
	/**
	 * Lazy load av betingelse
	 */
	LOAD_CONDITION("AvdelingBetingelses"),
	/**
	 * Lazy load av eierskap
	 */
	LOAD_OWNERSHIP("Eierforholds"),
	/**
	 * Lazy load av addendum
	 */
	LOAD_ADENDUM("Adendums"),
	/**
	 * Lazy load av andre kontrakter
	 */
	LOAD_OTHER_CONTRACT("AnnenKontrakts"),
	/**
	 * Lazy load av sikkerhet
	 */
	LOAD_SECURITY("Sikkerhets"),
	/**
	 * Lazy load av logg
	 */
	LOAD_LOG("AvdelingLoggs"),
	/**
	 * Lazy load av budsjett
	 */
	LOAD_BUDGET("AvdelingOmsetnings"),
	/**
	 * Lazy load av speilet betingelse
	 */
	LOAD_MIRROR("SpeiletBetingelses"),
	/**
	 * Lazy load av mangler
	 */
	LOAD_MANGLER("AvdelingMangels"),
	/**
	 * Lazy load av garantier
	 */
	LOAD_GUARANTEE("Garantier");
	/**
	 * Metode for å hente samling lazy
	 */
	private String lazyMethod;

	/**
	 * @param aLazyMethod
	 */
	private LazyLoadAvdelingEnum(String aLazyMethod) {
		lazyMethod = aLazyMethod;
	}

	/**
	 * @return Returns the lazyMethod.
	 */
	public String getLazyMethod() {
		return lazyMethod;
	}

}
