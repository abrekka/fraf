package no.ica.tollpost.model;

import java.math.BigDecimal;
import java.util.Date;

import no.ica.fraf.model.BaseObject;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TgNotImportedV extends BaseObject {
	private Integer meldingId;
	private String meldingstype;
	private BigDecimal belop;
	private Date transDato;
	private Date dtReg;
	private String lopenrFil;
	private Integer importert;
	public TgNotImportedV() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TgNotImportedV(BigDecimal belop, Date reg, Integer id, String meldingstype, Date dato,String lopenrFil,Integer importert) {
		super();
		// TODO Auto-generated constructor stub
		this.belop = belop;
		dtReg = reg;
		meldingId = id;
		this.meldingstype = meldingstype;
		transDato = dato;
		this.lopenrFil=lopenrFil;
		this.importert=importert;
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
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof TgNotImportedV))
			return false;
		TgNotImportedV castOther = (TgNotImportedV) other;
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
				"transDato", transDato).append("dtReg", dtReg).toString();
	}
	public String getLopenrFil() {
		return lopenrFil;
	}
	public void setLopenrFil(String lopenrFil) {
		this.lopenrFil = lopenrFil;
	}
	public Integer getImportert() {
		return importert;
	}
	public void setImportert(Integer importert) {
		this.importert = importert;
	}
}
