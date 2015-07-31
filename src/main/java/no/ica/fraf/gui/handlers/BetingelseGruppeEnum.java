package no.ica.fraf.gui.handlers;

/**
 * Enum som brukes til å styre hvilke betingelser som skal vises
 * @author abr99
 *
 */
public enum BetingelseGruppeEnum {
	/**
	 * Ale betingelser
	 */
	ADMIN("Admin"), 
	/**
	 * Regnskap 
	 */
	REGNSKAP("Regnskap"), 
	/**
	 * Marked 
	 */
	MARKED("Marked");

	/**
	 * 
	 */
	private String groupName;

	/**
	 * @param aGroupName
	 */
	private BetingelseGruppeEnum(String aGroupName) {
		groupName = aGroupName;
	}

	/**
	 * @return gruppenavn
	 */
	public String getGroupName() {
		return groupName;
	}

}
