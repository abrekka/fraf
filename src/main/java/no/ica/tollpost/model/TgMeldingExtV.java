package no.ica.tollpost.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import no.ica.fraf.model.BaseObject;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TgMeldingExtV extends BaseObject {
	private Integer meldingId;

	private String meldingstype;

	private BigDecimal belop;

	private Date transDato;

	private String transRef;

	private Date forfallsdato;

	private Date dtReg;

	private BigDecimal transfilId;
	private Set<TgLinjeExtV> tgLinjeExtVs;
	private String lopenrFil;

	public TgMeldingExtV() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TgMeldingExtV(BigDecimal belop, Date reg, Date forfallsdato,
			Integer id, String meldingstype, Date dato, BigDecimal id2,
			String ref,Set<TgLinjeExtV> tgLinjeExtVs,String lopenrFil) {
		super();
		// TODO Auto-generated constructor stub
		this.belop = belop;
		dtReg = reg;
		this.forfallsdato = forfallsdato;
		meldingId = id;
		this.meldingstype = meldingstype;
		transDato = dato;
		transfilId = id2;
		transRef = ref;
		this.tgLinjeExtVs=tgLinjeExtVs;
		this.lopenrFil=lopenrFil;
	}

	public BigDecimal getBelop() {
		return belop;
	}

	public void setBelop(BigDecimal belop) {
		this.belop = belop;
	}

	public Date getDtReg() {
		return dtReg;
	}

	public void setDtReg(Date dtReg) {
		this.dtReg = dtReg;
	}

	public Date getForfallsdato() {
		return forfallsdato;
	}

	public void setForfallsdato(Date forfallsdato) {
		this.forfallsdato = forfallsdato;
	}

	public Integer getMeldingId() {
		return meldingId;
	}

	public void setMeldingId(Integer meldingId) {
		this.meldingId = meldingId;
	}

	public String getMeldingstype() {
		return meldingstype;
	}

	public void setMeldingstype(String meldingstype) {
		this.meldingstype = meldingstype;
	}

	public Date getTransDato() {
		return transDato;
	}

	public void setTransDato(Date transDato) {
		this.transDato = transDato;
	}

	public BigDecimal getTransfilId() {
		return transfilId;
	}

	public void setTransfilId(BigDecimal transfilId) {
		this.transfilId = transfilId;
	}

	public String getTransRef() {
		return transRef;
	}

	public void setTransRef(String transRef) {
		this.transRef = transRef;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof TgMeldingExtV))
			return false;
		TgMeldingExtV castOther = (TgMeldingExtV) other;
		return new EqualsBuilder().append(meldingId, castOther.meldingId)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(meldingId).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("meldingId", meldingId).append(
				"meldingstype", meldingstype).append("belop", belop).append(
				"transDato", transDato).append("transRef", transRef).append(
				"forfallsdato", forfallsdato).append("dtReg", dtReg).append(
				"transfilId", transfilId).toString();
	}

	public Set<TgLinjeExtV> getTgLinjeExtVs() {
		return tgLinjeExtVs;
	}

	public void setTgLinjeExtVs(Set<TgLinjeExtV> tgLinjeExtVs) {
		this.tgLinjeExtVs = tgLinjeExtVs;
	}

	public String getLopenrFil() {
		return lopenrFil;
	}

	public void setLopenrFil(String lopenrFil) {
		this.lopenrFil = lopenrFil;
	}

}
