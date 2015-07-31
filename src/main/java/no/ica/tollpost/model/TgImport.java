package no.ica.tollpost.model;

import java.math.BigDecimal;
import java.util.Date;

import no.ica.fraf.common.Line;
import no.ica.fraf.model.BaseObject;
import no.ica.fraf.model.Bunt;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TgImport extends BaseObject implements Line{
	private Integer linjeId;

	private Integer meldingId;

	private Integer avdnr;

	private Date transDato;

	private Integer antTrans;

	private BigDecimal sendingsnr;

	private String kolliId;

	private BigDecimal belop;

	private String meldingstype;

	private Date dtReg;

	private BigDecimal transfilId;

	private Bunt bunt;

	private String avtaletype;

	private String dataset;

	//private Integer fakturaId;
	private TgFaktura tgFaktura;
	private String lopenrFil;
	private String transType;
	private Integer butiksNr;

	public TgImport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TgImport(Integer trans, Integer avdnr, BigDecimal belop, Bunt bunt,
			Date reg, String id, Integer id2, Integer id3, String meldingstype,
			BigDecimal sendingsnr, Date dato, BigDecimal id4,
			String avtaletype, String dataset,TgFaktura tgFaktura,String lopenrFil,String transType,Integer butiskNr) {
		super();
		// TODO Auto-generated constructor stub
		antTrans = trans;
		this.avdnr = avdnr;
		this.belop = belop;
		this.bunt = bunt;
		dtReg = reg;
		kolliId = id;
		linjeId = id2;
		meldingId = id3;
		this.meldingstype = meldingstype;
		this.sendingsnr = sendingsnr;
		transDato = dato;
		transfilId = id4;
		this.avtaletype = avtaletype;
		this.dataset = dataset;
		this.tgFaktura=tgFaktura;
		this.lopenrFil=lopenrFil;
		this.transType=transType;
		this.butiksNr=butiksNr;
	}

	public Integer getAntTrans() {
		return antTrans;
	}

	public void setAntTrans(Integer antTrans) {
		this.antTrans = antTrans;
	}

	public Integer getAvdnr() {
		return avdnr;
	}

	public void setAvdnr(Integer avdnr) {
		this.avdnr = avdnr;
	}

	public BigDecimal getBelop() {
		return belop;
	}

	public void setBelop(BigDecimal belop) {
		this.belop = belop;
	}

	public Bunt getBunt() {
		return bunt;
	}

	public void setBunt(Bunt bunt) {
		this.bunt = bunt;
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
		if (!(other instanceof TgImport))
			return false;
		TgImport castOther = (TgImport) other;
		return new EqualsBuilder().append(linjeId, castOther.linjeId)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(linjeId).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("linjeId", linjeId).append(
				"meldingId", meldingId).append("avdnr", avdnr).append(
				"transDato", transDato).append("antTrans", antTrans).append(
				"sendingsnr", sendingsnr).append("kolliId", kolliId).append(
				"belop", belop).append("meldingstype", meldingstype).append(
				"dtReg", dtReg).append("transfilId", transfilId).append("bunt",
				bunt).toString();
	}

	public String getAvtaletype() {
		return avtaletype;
	}

	public void setAvtaletype(String avtaletype) {
		this.avtaletype = avtaletype;
	}

	public String getDataset() {
		return dataset;
	}

	public void setDataset(String dataset) {
		this.dataset = dataset;
	}

	public TgFaktura getTgFaktura() {
		return tgFaktura;
	}

	public void setTgFaktura(TgFaktura tgFaktura) {
		this.tgFaktura = tgFaktura;
	}

	public String getLopenrFil() {
		return lopenrFil;
	}

	public void setLopenrFil(String lopenrFil) {
		this.lopenrFil = lopenrFil;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public Integer getButiksNr() {
		return butiksNr;
	}

	public void setButiksNr(Integer butiksNr) {
		this.butiksNr = butiksNr;
	}

	public Date getDato() {
		return transDato;
	}
}
