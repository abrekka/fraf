package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for tabell LAAS_TYPE
 * 
 * @author abr99
 * 
 */
public class LaasType extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer laasTypeId;

	/**
	 * 
	 */
	private String laasTypeKode;

	/**
	 * 
	 */
	private String laasTypeTekst;

	/**
	 * 
	 */
	public LaasType() {
	}

	/**
	 * @param id
	 * @param kode
	 * @param tekst
	 */
	public LaasType(Integer id, String kode, String tekst) {
		laasTypeId = id;
		laasTypeKode = kode;
		laasTypeTekst = tekst;
	}

	/**
	 * @return id
	 */
	public Integer getLaasTypeId() {
		return laasTypeId;
	}

	/**
	 * @param laasTypeId
	 */
	public void setLaasTypeId(Integer laasTypeId) {
		this.laasTypeId = laasTypeId;
	}

	/**
	 * @return kode
	 */
	public String getLaasTypeKode() {
		return laasTypeKode;
	}

	/**
	 * @param laasTypeKode
	 */
	public void setLaasTypeKode(String laasTypeKode) {
		this.laasTypeKode = laasTypeKode;
	}

	/**
	 * @return tekst
	 */
	public String getLaasTypeTekst() {
		return laasTypeTekst;
	}

	/**
	 * @param laasTypeTekst
	 */
	public void setLaasTypeTekst(String laasTypeTekst) {
		this.laasTypeTekst = laasTypeTekst;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof LaasType))
			return false;
		LaasType castOther = (LaasType) other;
		return new EqualsBuilder().append(laasTypeKode, castOther.laasTypeKode)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(laasTypeKode).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"laasTypeTekst", laasTypeTekst).toString();
	}
}
