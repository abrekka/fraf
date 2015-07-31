package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class AvdelingAvregningBelop extends BaseObject{
	private Integer avregningBelopId;
	private AvdelingAvregningImport avdelingAvregningImport;
	private Integer maaned;
	private BigDecimal belop;
	private Set<AvdelingAvregning> avdelingAvregnings;
	public AvdelingAvregningBelop() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AvdelingAvregningBelop(AvdelingAvregningImport import1, Integer id, BigDecimal belop, Integer maaned,Set<AvdelingAvregning> avdelingAvregnings) {
		super();
		avdelingAvregningImport = import1;
		avregningBelopId = id;
		this.belop = belop;
		this.maaned = maaned;
		this.avdelingAvregnings=avdelingAvregnings;
	}
	public AvdelingAvregningImport getAvdelingAvregningImport() {
		return avdelingAvregningImport;
	}
	public void setAvdelingAvregningImport(
			AvdelingAvregningImport avdelingAvregningImport) {
		this.avdelingAvregningImport = avdelingAvregningImport;
	}
	public Integer getAvregningBelopId() {
		return avregningBelopId;
	}
	public void setAvregningBelopId(Integer avregningBelopId) {
		this.avregningBelopId = avregningBelopId;
	}
	public BigDecimal getBelop() {
		return belop;
	}
	public void setBelop(BigDecimal belop) {
		this.belop = belop;
	}
	public Integer getMaaned() {
		return maaned;
	}
	public void setMaaned(Integer maaned) {
		this.maaned = maaned;
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AvdelingAvregningBelop))
			return false;
		AvdelingAvregningBelop castOther = (AvdelingAvregningBelop) other;
		return new EqualsBuilder().append(avdelingAvregningImport,
				castOther.avdelingAvregningImport).append(maaned,
				castOther.maaned).append(belop, castOther.belop).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdelingAvregningImport).append(
				maaned).append(belop).toHashCode();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("avregningBelopId",
				avregningBelopId).append("avdelingAvregningImport",
				avdelingAvregningImport).append("maaned", maaned).append(
				"belop", belop).toString();
	}
	public Set<AvdelingAvregning> getAvdelingAvregnings() {
		return avdelingAvregnings;
	}
	public void setAvdelingAvregnings(Set<AvdelingAvregning> avdelingAvregnings) {
		this.avdelingAvregnings = avdelingAvregnings;
	}
}
