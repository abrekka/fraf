package no.ica.fraf.enums;

/**
 * Enum for å flytte seg fra side til sode i et datasett som er paginert
 * 
 * @author abr99
 * 
 */
public enum MoveEnum {
	/**
	 * Ingen flytting
	 */
	NO_MOVE,
	/**
	 * Flytt til første side
	 */
	FIRST_PAGE,
	/**
	 * Flytt til forrige side
	 */
	PREV_PAGE,
	/**
	 * Flytt til neste side
	 */
	NEXT_PAGE,
	/**
	 * Flytt til siste side
	 */
	LAST_PAGE
}
