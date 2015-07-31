package no.ica.tollpost.model;

import java.math.BigDecimal;

import no.ica.fraf.common.ReconcilVInterface;
import no.ica.fraf.model.BaseObject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TgReconcilV extends BaseObject implements ReconcilVInterface{
	private TgReconcilVPK tgReconcilVPK;
	private BigDecimal netto;
	private BigDecimal mva;
	private Integer antall;
	private Integer xmlCount;
	public TgReconcilV() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TgReconcilV(Integer antall, BigDecimal mva, BigDecimal netto, TgReconcilVPK reconcilVPK,Integer xmlCount) {
		super();
		// TODO Auto-generated constructor stub
		this.antall = antall;
		this.mva = mva;
		this.netto = netto;
		tgReconcilVPK = reconcilVPK;
		this.xmlCount=xmlCount;
	}
	public Integer getAntall() {
		return antall;
	}
	public void setAntall(Integer antall) {
		this.antall = antall;
	}
	public BigDecimal getMva() {
		return mva;
	}
	public void setMva(BigDecimal mva) {
		this.mva = mva;
	}
	public BigDecimal getNetto() {
		return netto;
	}
	public void setNetto(BigDecimal netto) {
		this.netto = netto;
	}
	public TgReconcilVPK getTgReconcilVPK() {
		return tgReconcilVPK;
	}
	public void setTgReconcilVPK(TgReconcilVPK tgReconcilVPK) {
		this.tgReconcilVPK = tgReconcilVPK;
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof TgReconcilV))
			return false;
		TgReconcilV castOther = (TgReconcilV) other;
		return new EqualsBuilder().append(tgReconcilVPK,
				castOther.tgReconcilVPK).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(tgReconcilVPK).toHashCode();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("tgReconcilVPK", tgReconcilVPK)
				.append("netto", netto).append("mva", mva).append("antall",
						antall).toString();
	}
	public BigDecimal getNet() {
		return getNetto();
	}
	public BigDecimal getVat() {
		return getMva();
	}
	public String getInvoiceType() {
		return tgReconcilVPK.getMeldingstype();
	}
	public Integer getInvoiceCount() {
		return getAntall();
	}
	public Integer getXmlCount() {
		return xmlCount;
	}
	public void setXmlCount(Integer xmlCount) {
		this.xmlCount = xmlCount;
	}
}
