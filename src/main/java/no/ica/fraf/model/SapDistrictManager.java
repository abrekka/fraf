package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class SapDistrictManager extends BaseObject implements DistrictManager{
	//private Integer distriktssjef;
	private String districtManagerName;
	//private Integer kjede;
	/*public Integer getDistriktssjef() {
		return distriktssjef;
	}
	public void setDistriktssjef(Integer distriktssjef) {
		this.distriktssjef = distriktssjef;
	}*/
	public String getDistrictManagerName() {
		return districtManagerName;
	}
	public void setDistrictManagerName(String districtManagerName) {
		this.districtManagerName = districtManagerName;
	}
	/*public Integer getKjede() {
		return kjede;
	}
	public void setKjede(Integer kjede) {
		this.kjede = kjede;
	}*/
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof SapDistrictManager))
			return false;
		SapDistrictManager castOther = (SapDistrictManager) other;
		return new EqualsBuilder().append(districtManagerName,
				castOther.districtManagerName).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(districtManagerName).toHashCode();
	}
	@Override
	public String toString() {
		return districtManagerName;
	}
}
