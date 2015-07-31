package no.ica.elfa.model;

/**
 * Enum for buntstatus
 * 
 * @author abr99
 * 
 */
public enum BatchStatusEnum {
	/**
	 * Innlest
	 */
	STATUS_INNLEST(0, "Innlest"),
	/**
	 * Importert
	 */
	STATUS_IMPORTERT(1, "Importert"),
	/**
	 * Fakturert
	 */
	STATUS_FAKTURA(2, "Fakturert"),
	/**
	 * Laget e2b-fil
	 */
	STATUS_E2B(3, "XML"),
	/**
	 * Bokf�rt
	 */
	STATUS_BOKFOERT(4, "Bokf�rt"),
	/**
	 * XML og bokf�rt
	 */
	STATUS_XML_BOKFOERT(5, "XML/Bokf�rt");
	/**
	 * Statusverdi
	 */
	private Integer value;

	/**
	 * Beskrivelse
	 */
	private String description;

	/**
	 * @param aValue
	 * @param desc
	 */
	private BatchStatusEnum(Integer aValue, String desc) {
		value = aValue;
		description = desc;
	}

	/**
	 * @return beskrivelse
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return statusverdi
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * Henter enum basert p� status
	 * 
	 * @param status
	 * @return statusenum
	 */
	public static BatchStatusEnum getEnum(BatchStatus status) {
		if (status.getStatusDescription().equalsIgnoreCase("Innlest")) {
			return STATUS_INNLEST;
		} else if (status.getStatusDescription().equalsIgnoreCase("Importert")) {
			return STATUS_IMPORTERT;
		} else if (status.getStatusDescription().equalsIgnoreCase("Fakturert")) {
			return STATUS_FAKTURA;
		} else if (status.getStatusDescription().equalsIgnoreCase("XML")) {
			return STATUS_E2B;
		} else if (status.getStatusDescription().equalsIgnoreCase("Bokf�rt")) {
			return STATUS_BOKFOERT;
		} else if (status.getStatusDescription()
				.equalsIgnoreCase("XML/Bokf�rt")) {
			return STATUS_XML_BOKFOERT;
		} else {
			throw new IllegalStateException("Buntstatus finnes ikke");
		}

	}
}
