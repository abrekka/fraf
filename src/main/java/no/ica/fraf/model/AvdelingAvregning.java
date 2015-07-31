package no.ica.fraf.model;

import java.math.BigDecimal;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class AvdelingAvregning extends BaseObject{
	private Integer avdelingAvregningId;
	private AvdelingBetingelse avdelingBetingelse;
	private BigDecimal avregnetAvgift;
	private BigDecimal fakturertAvgift;
	private BigDecimal avregning;
	private AvdelingAvregningBelop avdelingAvregningBelop;
	public AvdelingAvregning() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AvdelingAvregning(AvdelingAvregningBelop belop, Integer id, AvdelingBetingelse betingelse, BigDecimal avgift, BigDecimal avregning, BigDecimal avgift2) {
		super();
		// TODO Auto-generated constructor stub
		avdelingAvregningBelop = belop;
		avdelingAvregningId = id;
		avdelingBetingelse = betingelse;
		avregnetAvgift = avgift;
		this.avregning = avregning;
		fakturertAvgift = avgift2;
	}
	public AvdelingAvregningBelop getAvdelingAvregningBelop() {
		return avdelingAvregningBelop;
	}
	public void setAvdelingAvregningBelop(
			AvdelingAvregningBelop avdelingAvregningBelop) {
		this.avdelingAvregningBelop = avdelingAvregningBelop;
	}
	public Integer getAvdelingAvregningId() {
		return avdelingAvregningId;
	}
	public void setAvdelingAvregningId(Integer avdelingAvregningId) {
		this.avdelingAvregningId = avdelingAvregningId;
	}
	public AvdelingBetingelse getAvdelingBetingelse() {
		return avdelingBetingelse;
	}
	public void setAvdelingBetingelse(AvdelingBetingelse avdelingBetingelse) {
		this.avdelingBetingelse = avdelingBetingelse;
	}
	public BigDecimal getAvregnetAvgift() {
		return avregnetAvgift;
	}
	public void setAvregnetAvgift(BigDecimal avregnetAvgift) {
		this.avregnetAvgift = avregnetAvgift;
	}
	public BigDecimal getAvregning() {
		return avregning;
	}
	public void setAvregning(BigDecimal avregning) {
		this.avregning = avregning;
	}
	public BigDecimal getFakturertAvgift() {
		return fakturertAvgift;
	}
	public void setFakturertAvgift(BigDecimal fakturertAvgift) {
		this.fakturertAvgift = fakturertAvgift;
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AvdelingAvregning))
			return false;
		AvdelingAvregning castOther = (AvdelingAvregning) other;
		return new EqualsBuilder().append(avdelingBetingelse,
				castOther.avdelingBetingelse).append(avregnetAvgift,
				castOther.avregnetAvgift).append(fakturertAvgift,
				castOther.fakturertAvgift).append(avregning,
				castOther.avregning).append(avdelingAvregningBelop,
				castOther.avdelingAvregningBelop).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdelingBetingelse).append(
				avregnetAvgift).append(fakturertAvgift).append(avregning)
				.append(avdelingAvregningBelop).toHashCode();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("avdelingAvregningId",
				avdelingAvregningId).append("avdelingBetingelse",
				avdelingBetingelse).append("avregnetAvgift", avregnetAvgift)
				.append("fakturertAvgift", fakturertAvgift).append("avregning",
						avregning).append("avdelingAvregningBelop",
						avdelingAvregningBelop).toString();
	}
}
