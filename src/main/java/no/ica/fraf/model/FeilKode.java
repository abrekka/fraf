package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for tabell FEIL_KODE
 * 
 * @author abr99
 * 
 */
public class FeilKode extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer feilKodeId;

	/**
	 * 
	 */
	private String feilKode;

	/**
	 * 
	 */
	private String feilBeskrivelse;

	/**
	 * 
	 */
	public FeilKode() {
	}

	/**
	 * @param beskrivelse
	 * @param kode
	 * @param id
	 */
	public FeilKode(String beskrivelse, String kode, Integer id) {
		feilBeskrivelse = beskrivelse;
		feilKode = kode;
		feilKodeId = id;
	}

	/**
	 * @return beskrivelse
	 */
	public String getFeilBeskrivelse() {
		return feilBeskrivelse;
	}

	/**
	 * @param feilBeskrivelse
	 */
	public void setFeilBeskrivelse(String feilBeskrivelse) {
		this.feilBeskrivelse = feilBeskrivelse;
	}

	/**
	 * @return kode
	 */
	public String getFeilKode() {
		return feilKode;
	}

	/**
	 * @param feilKode
	 */
	public void setFeilKode(String feilKode) {
		this.feilKode = feilKode;
	}

	/**
	 * @return id
	 */
	public Integer getFeilKodeId() {
		return feilKodeId;
	}

	/**
	 * @param feilKodeId
	 */
	public void setFeilKodeId(Integer feilKodeId) {
		this.feilKodeId = feilKodeId;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof FeilKode))
			return false;
		FeilKode castOther = (FeilKode) other;
		return new EqualsBuilder().append(feilKode, castOther.feilKode)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(feilKode).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"feilBeskrivelse", feilBeskrivelse).toString();
	}

}
