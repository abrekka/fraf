package no.ica.tollpost.model;

import java.math.BigDecimal;
import java.util.Date;

import no.ica.fraf.model.BaseObject;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TgLinjeExtV extends BaseObject {
	private Integer linjeId;

	private Integer lokasjonId;

	private Date transDato;

	private Integer antTrans;

	private BigDecimal sendingsnr;

	private String kolliId;

	private BigDecimal belop;

	private Date dtReg;

	private BigDecimal transfilId;

	private TgMeldingExtV tgMeldingExtV;

	private String transType;

	public TgLinjeExtV() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TgLinjeExtV(Integer trans, BigDecimal belop, Date reg, String id,
			Integer id2, Integer id3, BigDecimal sendingsnr,
			TgMeldingExtV extV, Date dato, BigDecimal id4,String transType) {
		super();
		// TODO Auto-generated constructor stub
		antTrans = trans;
		this.belop = belop;
		dtReg = reg;
		kolliId = id;
		linjeId = id2;
		lokasjonId = id3;
		this.sendingsnr = sendingsnr;
		tgMeldingExtV = extV;
		transDato = dato;
		transfilId = id4;
		this.transType=transType;
	}

	public Integer getAntTrans() {
		return antTrans;
	}

	public void setAntTrans(Integer antTrans) {
		this.antTrans = antTrans;
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

	public String getKolliId() {
		return kolliId;
	}

	public void setKolliId(String kolliId) {
		this.kolliId = kolliId;
	}

	public Integer getLinjeId() {
		return linjeId;
	}

	public void setLinjeId(Integer linjeId) {
		this.linjeId = linjeId;
	}

	public Integer getLokasjonId() {
		return lokasjonId;
	}

	public void setLokasjonId(Integer lokasjonId) {
		this.lokasjonId = lokasjonId;
	}

	public BigDecimal getSendingsnr() {
		return sendingsnr;
	}

	public void setSendingsnr(BigDecimal sendingsnr) {
		this.sendingsnr = sendingsnr;
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

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof TgLinjeExtV))
			return false;
		TgLinjeExtV castOther = (TgLinjeExtV) other;
		return new EqualsBuilder().append(linjeId, castOther.linjeId)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(linjeId).toHashCode();
	}

	public TgMeldingExtV getTgMeldingExtV() {
		return tgMeldingExtV;
	}

	public void setTgMeldingExtV(TgMeldingExtV tgMeldingExtV) {
		this.tgMeldingExtV = tgMeldingExtV;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("linjeId", linjeId).append(
				"lokasjonId", lokasjonId).append("transDato", transDato)
				.append("antTrans", antTrans).append("sendingsnr", sendingsnr)
				.append("kolliId", kolliId).append("belop", belop).append(
						"dtReg", dtReg).append("transfilId", transfilId)
				.append("tgMeldingExtV", tgMeldingExtV).toString();
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

}
