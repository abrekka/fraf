package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Klasse for tabell OMSETNING_TYPE
 * 
 * @author abr99
 * 
 */
public class OmsetningType extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer omsetningTypeId;

	/**
	 * 
	 */
	private String omsetningTypeKode;

	/**
	 * 
	 */
	private String omsetningTypeTxt;

	/**
	 * 
	 */
	public OmsetningType() {
	}

	/**
	 * @param omsetningTypeId
	 * @param omsetningTypeKode
	 * @param omsetningTypeTxt
	 */
	public OmsetningType(Integer omsetningTypeId, String omsetningTypeKode,
			String omsetningTypeTxt) {
		this.omsetningTypeId = omsetningTypeId;
		this.omsetningTypeKode = omsetningTypeKode;
		this.omsetningTypeTxt = omsetningTypeTxt;
	}

	/**
	 * @return id
	 */
	public Integer getOmsetningTypeId() {
		return omsetningTypeId;
	}

	/**
	 * @param omsetningTypeId
	 */
	public void setOmsetningTypeId(Integer omsetningTypeId) {
		this.omsetningTypeId = omsetningTypeId;
	}

	/**
	 * @return kode
	 */
	public String getOmsetningTypeKode() {
		return omsetningTypeKode;
	}

	/**
	 * @param omsetningTypeKode
	 */
	public void setOmsetningTypeKode(String omsetningTypeKode) {
		this.omsetningTypeKode = omsetningTypeKode;
	}

	/**
	 * @return beskrivelse
	 */
	public String getOmsetningTypeTxt() {
		return omsetningTypeTxt;
	}

	/**
	 * @param omsetningTypeTxt
	 */
	public void setOmsetningTypeTxt(String omsetningTypeTxt) {
		this.omsetningTypeTxt = omsetningTypeTxt;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof OmsetningType)) {
			return false;
		}
		OmsetningType rhs = (OmsetningType) object;
		return new EqualsBuilder().append(this.omsetningTypeKode,
				rhs.omsetningTypeKode).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(1473408329, -106165261).append(
				omsetningTypeKode).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return omsetningTypeTxt;
	}

}
