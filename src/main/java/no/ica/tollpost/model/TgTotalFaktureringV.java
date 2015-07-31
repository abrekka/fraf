package no.ica.tollpost.model;

import java.math.BigDecimal;

import no.ica.fraf.model.BaseObject;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TgTotalFaktureringV extends BaseObject{
	private TgTotalFaktureringVPK tgTotalFaktureringVPK;
	private BigDecimal totalBelop;
	public TgTotalFaktureringV() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TgTotalFaktureringV(TgTotalFaktureringVPK faktureringVPK, BigDecimal belop) {
		super();
		// TODO Auto-generated constructor stub
		tgTotalFaktureringVPK = faktureringVPK;
		totalBelop = belop;
	}
	public TgTotalFaktureringVPK getTgTotalFaktureringVPK() {
		return tgTotalFaktureringVPK;
	}
	public void setTgTotalFaktureringVPK(TgTotalFaktureringVPK tgTotalFaktureringVPK) {
		this.tgTotalFaktureringVPK = tgTotalFaktureringVPK;
	}
	public BigDecimal getTotalBelop() {
		return totalBelop;
	}
	public void setTotalBelop(BigDecimal totalBelop) {
		this.totalBelop = totalBelop;
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof TgTotalFaktureringV))
			return false;
		TgTotalFaktureringV castOther = (TgTotalFaktureringV) other;
		return new EqualsBuilder().append(tgTotalFaktureringVPK,
				castOther.tgTotalFaktureringVPK).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(tgTotalFaktureringVPK).toHashCode();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("tgTotalFaktureringVPK",
				tgTotalFaktureringVPK).append("totalBelop", totalBelop)
				.toString();
	}
}
