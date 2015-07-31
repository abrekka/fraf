package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for tabell BUNT_TYPE
 * 
 * @author abr99
 * 
 */
public class BuntType extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer buntTypeId;

	/**
	 * 
	 */
	private String kode;

	/**
	 * 
	 */
	private String beskrivelse;

	/**
	 * 
	 */
	public BuntType() {
	}

	/**
	 * @param beskrivelse
	 * @param id
	 * @param kode
	 */
	public BuntType(String beskrivelse, Integer id, String kode) {
		this.beskrivelse = beskrivelse;
		buntTypeId = id;
		this.kode = kode;
	}

	/**
	 * @return beskrivelse
	 */
	public String getBeskrivelse() {
		return beskrivelse;
	}

	/**
	 * @param beskrivelse
	 */
	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	/**
	 * @return id
	 */
	public Integer getBuntTypeId() {
		return buntTypeId;
	}

	/**
	 * @param buntTypeId
	 */
	public void setBuntTypeId(Integer buntTypeId) {
		this.buntTypeId = buntTypeId;
	}

	/**
	 * @return kode
	 */
	public String getKode() {
		return kode;
	}

	/**
	 * @param kode
	 */
	public void setKode(String kode) {
		this.kode = kode;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof BuntType))
			return false;
		BuntType castOther = (BuntType) other;
		return new EqualsBuilder().append(kode, castOther.kode).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(kode).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"beskrivelse", beskrivelse).toString();
	}
}
