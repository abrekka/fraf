package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Klasse for tabell AVREGNING_TYPE
 * 
 * @author abr99
 * 
 */
public class AvregningType extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer avregningTypeId;

	/**
	 * 
	 */
	private String avregningTypeKode;

	/**
	 * 
	 */
	private String avregningTypeTxt;

	/**
	 * 
	 */
	public AvregningType() {
		super();
	}

	/**
	 * @param avregningTypeId
	 * @param avregningTypeKode
	 * @param avregningTypeTxt
	 */
	public AvregningType(Integer avregningTypeId, String avregningTypeKode,
			String avregningTypeTxt) {
		super();
		this.avregningTypeId = avregningTypeId;
		this.avregningTypeKode = avregningTypeKode;
		this.avregningTypeTxt = avregningTypeTxt;

	}

	/**
	 * @return id
	 */
	public Integer getAvregningTypeId() {
		return avregningTypeId;
	}

	/**
	 * @param avregningTypeId
	 */
	public void setAvregningTypeId(Integer avregningTypeId) {
		this.avregningTypeId = avregningTypeId;
	}

	/**
	 * @return kode
	 */
	public String getAvregningTypeKode() {
		return avregningTypeKode;
	}

	/**
	 * @param avregningTypeKode
	 */
	public void setAvregningTypeKode(String avregningTypeKode) {
		this.avregningTypeKode = avregningTypeKode;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AvregningType)) {
			return false;
		}
		AvregningType rhs = (AvregningType) object;
		return new EqualsBuilder().append(this.avregningTypeKode,
				rhs.avregningTypeKode).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(1861008403, 458643259).append(
				this.avregningTypeKode).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return avregningTypeTxt;
	}

	/**
	 * @return txt
	 */
	public String getAvregningTypeTxt() {
		return avregningTypeTxt;
	}

	/**
	 * @param avregningTypeTxt
	 */
	public void setAvregningTypeTxt(String avregningTypeTxt) {
		this.avregningTypeTxt = avregningTypeTxt;
	}

}
