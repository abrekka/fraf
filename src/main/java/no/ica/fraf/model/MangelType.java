package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Tabell MANGEL_TYPE
 * 
 * @author abr99
 * 
 */
public class MangelType extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer mangelId;

	/**
	 * 
	 */
	private String mangelKode;

	/**
	 * 
	 */
	private String mangelBeskrivelse;

	/**
	 * @param beskrivelse
	 * @param kode
	 * @param id
	 */
	public MangelType(String beskrivelse, String kode, Integer id) {
		super();
		mangelBeskrivelse = beskrivelse;
		mangelKode = kode;
		mangelId = id;
	}

	/**
	 * 
	 */
	public MangelType() {
		super();
	}

	/**
	 * @return Returns the mangelBeskrivelse.
	 */
	public String getMangelBeskrivelse() {
		return mangelBeskrivelse;
	}

	/**
	 * @param mangelBeskrivelse
	 *            The mangelBeskrivelse to set.
	 */
	public void setMangelBeskrivelse(String mangelBeskrivelse) {
		this.mangelBeskrivelse = mangelBeskrivelse;
	}

	/**
	 * @return Returns the mangelTypeId.
	 */
	public Integer getMangelId() {
		return mangelId;
	}

	/**
	 * @param mangelId
	 *            The mangelTypeId to set.
	 */
	public void setMangelId(Integer mangelId) {
		this.mangelId = mangelId;
	}

	/**
	 * @return Returns the mangelKode.
	 */
	public String getMangelKode() {
		return mangelKode;
	}

	/**
	 * @param mangelKode
	 *            The mangelKode to set.
	 */
	public void setMangelKode(String mangelKode) {
		this.mangelKode = mangelKode;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof MangelType))
			return false;
		MangelType castOther = (MangelType) other;
		return new EqualsBuilder().append(mangelKode, castOther.mangelKode)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(mangelKode).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"mangelBeskrivelse", mangelBeskrivelse).toString();
	}

}
