package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Klasse for view RIK2_REGION_V
 * 
 * @author abr99
 * 
 */
public class Rik2RegionV extends BaseObject implements Region{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String selskap;

	/**
	 * 
	 */
	private Integer region;

	/**
	 * 
	 */
	private String navn;

	/**
	 * 
	 */
	public Rik2RegionV() {
		super();
	}

	/**
	 * @param navn
	 * @param region
	 * @param selskap
	 */
	public Rik2RegionV(String navn, Integer region, String selskap) {
		super();
		this.navn = navn;
		this.region = region;
		this.selskap = selskap;
	}

	/**
	 * @return navn
	 */
	public String getNavn() {
		return navn;
	}

	/**
	 * @param navn
	 */
	public void setNavn(String navn) {
		this.navn = navn;
	}

	/**
	 * @return id til region
	 */
	public Integer getRegion() {
		return region;
	}

	/**
	 * @param region
	 */
	public void setRegion(Integer region) {
		this.region = region;
	}

	/**
	 * @return selskap
	 */
	public String getSelskap() {
		return selskap;
	}

	/**
	 * @param selskap
	 */
	public void setSelskap(String selskap) {
		this.selskap = selskap;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Rik2RegionV))
			return false;
		Rik2RegionV castOther = (Rik2RegionV) other;
		return new EqualsBuilder().append(region, castOther.region).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(region).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return navn;
	}

	public Integer getRegionId() {
		return this.region;
	}

}
