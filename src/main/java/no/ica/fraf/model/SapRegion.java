package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class SapRegion extends BaseObject implements Region{
	private String region;
	private String selskap;
	private String regionName;
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getSelskap() {
		return selskap;
	}
	public void setSelskap(String selskap) {
		this.selskap = selskap;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof SapChain))
			return false;
		SapRegion castOther = (SapRegion) other;
		return new EqualsBuilder().append(region, castOther.region).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(region).toHashCode();
	}
	@Override
	public String toString() {
		return regionName;
	}
	public String getNavn() {
		return regionName;
	}
	public String getRegionId() {
		return this.region;
	}
	
}
