package no.ica.fraf.enums;

/**
 * Enum for sortering
 * 
 * @author abr99
 * 
 */
public enum SortingEnum {
	/**
	 * Sorter desc
	 */
	DESCENDING(-1),
	/**
	 * Ikke sorter
	 */
	NOT_SORTED(0),
	/**
	 * Sorter asc
	 */
	ASCENDING(1);

	/**
	 * Verdi som brukes for å finne ut av hvilken sorting neste eller forrige er
	 */
	private int enumInt;

	/**
	 * Kosntruktør
	 * 
	 * @param aEnumInt
	 */
	SortingEnum(int aEnumInt) {
		enumInt = aEnumInt;
	}

	/**
	 * Finner ut hvilken sorting som er neste basert på -1 eller 1 for forrige
	 * eller neste
	 * 
	 * @param value
	 * @return sortering
	 */
	public SortingEnum add(int value) {
		int tmpValue = enumInt + value;

		return getSortingColumn((tmpValue + 4) % 3 - 1);
	}

	/**
	 * Finner enum basert på enumverdi
	 * 
	 * @param enumValue
	 * @return sortering
	 */
	private static SortingEnum getSortingColumn(int enumValue) {
		switch (enumValue) {
		case -1:
			return DESCENDING;
		case 0:
			return NOT_SORTED;
		case 1:
			return ASCENDING;
		default:
			return NOT_SORTED;
		}
	}
}
