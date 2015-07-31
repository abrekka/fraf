package no.ica.fraf.model;

import java.math.BigDecimal;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TotalAvregningV extends BaseObject{
	private TotalAvregningVPK totalAvregningVPK;
	private BigDecimal sumAvregning;
	private Integer aar;
	public TotalAvregningV() {
		super();
	}
	public TotalAvregningV(BigDecimal avregning, TotalAvregningVPK avregningVPK,Integer aar) {
		super();
		sumAvregning = avregning;
		totalAvregningVPK = avregningVPK;
		this.aar=aar;
	}
	public BigDecimal getSumAvregning() {
		return sumAvregning;
	}
	public void setSumAvregning(BigDecimal sumAvregning) {
		this.sumAvregning = sumAvregning;
	}
	public TotalAvregningVPK getTotalAvregningVPK() {
		return totalAvregningVPK;
	}
	public void setTotalAvregningVPK(TotalAvregningVPK totalAvregningVPK) {
		this.totalAvregningVPK = totalAvregningVPK;
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof TotalAvregningV))
			return false;
		TotalAvregningV castOther = (TotalAvregningV) other;
		return new EqualsBuilder().append(totalAvregningVPK,
				castOther.totalAvregningVPK).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(totalAvregningVPK).toHashCode();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("totalAvregningVPK",
				totalAvregningVPK).append("sumAvregning", sumAvregning)
				.toString();
	}
	public Integer getAar() {
		return aar;
	}
	public void setAar(Integer aar) {
		this.aar = aar;
	}
	
}