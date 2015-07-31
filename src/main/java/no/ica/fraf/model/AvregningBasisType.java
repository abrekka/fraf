package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Klasse for tabell AVREGNING_BASIS_TYPE
 * 
 * @author abr99
 * 
 */
public class AvregningBasisType extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer avregningBasisTypeId;

	/**
	 * 
	 */
	private String avregningBasisTypeKode;

	/**
	 * 
	 */
	private String avregningBasisTypeTxt;

	/**
	 * 
	 */
	public AvregningBasisType() {
		super();
	}

	/**
	 * @param avregningBasisTypeId
	 * @param avregningBasisTypeKode
	 * @param avregningBasisTypeTxt
	 */
	public AvregningBasisType(Integer avregningBasisTypeId,
			String avregningBasisTypeKode, String avregningBasisTypeTxt) {
		super();
		this.avregningBasisTypeId = avregningBasisTypeId;
		this.avregningBasisTypeKode = avregningBasisTypeKode;
		this.avregningBasisTypeTxt = avregningBasisTypeTxt;
	}

	/**
	 * @return txt
	 */
	public String getAvregningBasisTypeTxt() {
		return avregningBasisTypeTxt;
	}

	/**
	 * @param avregningBasisTypeTxt
	 */
	public void setAvregningBasisTypeTxt(String avregningBasisTypeTxt) {
		this.avregningBasisTypeTxt = avregningBasisTypeTxt;
	}

	/**
	 * @return id
	 */
	public Integer getAvregningBasisTypeId() {
		return avregningBasisTypeId;
	}

	/**
	 * @param avregningBasisTypeId
	 */
	public void setAvregningBasisTypeId(Integer avregningBasisTypeId) {
		this.avregningBasisTypeId = avregningBasisTypeId;
	}

	/**
	 * @return kode
	 */
	public String getAvregningBasisTypeKode() {
		return avregningBasisTypeKode;
	}

	/**
	 * @param avregningBasisTypeKode
	 */
	public void setAvregningBasisTypeKode(String avregningBasisTypeKode) {
		this.avregningBasisTypeKode = avregningBasisTypeKode;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AvregningBasisType)) {
			return false;
		}
		AvregningBasisType rhs = (AvregningBasisType) object;
		return new EqualsBuilder().append(this.avregningBasisTypeKode,
				rhs.avregningBasisTypeKode).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(1069334845, -1782680775).append(
				this.avregningBasisTypeKode).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return avregningBasisTypeTxt;
	}

}
