package no.ica.tollpost.model;

import java.math.BigDecimal;
import java.util.Date;

import no.ica.fraf.model.BaseObject;
import no.ica.fraf.xml.InvoiceItemInterface;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.CompareToBuilder;

public class TgFakturaLinje extends BaseObject implements Comparable<TgFakturaLinje>, InvoiceItemInterface {
	private Integer tgFakturaLinjeId;

	private TgFaktura tgFaktura;

	private String linjeBeskrivelse;

	private BigDecimal belop;

	private String mvaKode;

	private BigDecimal mvaBelop;

	private Integer antall;

	private BigDecimal nettoBelop;
	private Date transDato;
	private BigDecimal pris;

	public TgFakturaLinje() {
		super();
	}

	public TgFakturaLinje(Integer antall, BigDecimal belop, String beskrivelse,
			BigDecimal belop2, String kode, TgFaktura faktura, Integer id,BigDecimal nettoBelop,Date transDato,BigDecimal pris) {
		super();
		this.antall = antall;
		this.belop = belop;
		linjeBeskrivelse = beskrivelse;
		mvaBelop = belop2;
		mvaKode = kode;
		tgFaktura = faktura;
		tgFakturaLinjeId = id;
		this.nettoBelop=nettoBelop;
		this.transDato=transDato;
		this.pris=pris;
	}

	public Integer getAntall() {
		return antall;
	}

	public void setAntall(Integer antall) {
		this.antall = antall;
	}

	public BigDecimal getBelop() {
		return belop;
	}

	public void setBelop(BigDecimal belop) {
		this.belop = belop;
	}

	public String getLinjeBeskrivelse() {
		return linjeBeskrivelse;
	}

	public void setLinjeBeskrivelse(String linjeBeskrivelse) {
		this.linjeBeskrivelse = linjeBeskrivelse;
	}

	public BigDecimal getMvaBelop() {
		return mvaBelop;
	}

	public void setMvaBelop(BigDecimal mvaBelop) {
		this.mvaBelop = mvaBelop;
	}

	public String getMvaKode() {
		return mvaKode;
	}

	public void setMvaKode(String mvaKode) {
		this.mvaKode = mvaKode;
	}

	public TgFaktura getTgFaktura() {
		return tgFaktura;
	}

	public void setTgFaktura(TgFaktura tgFaktura) {
		this.tgFaktura = tgFaktura;
	}

	public Integer getTgFakturaLinjeId() {
		return tgFakturaLinjeId;
	}

	public void setTgFakturaLinjeId(Integer tgFakturaLinjeId) {
		this.tgFakturaLinjeId = tgFakturaLinjeId;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof TgFakturaLinje))
			return false;
		TgFakturaLinje castOther = (TgFakturaLinje) other;
		return new EqualsBuilder().append(tgFakturaLinjeId,
				castOther.tgFakturaLinjeId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(tgFakturaLinjeId).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("tgFakturaLinjeId",
				tgFakturaLinjeId).append("tgFaktura", tgFaktura).append(
				"linjeBeskrivelse", linjeBeskrivelse).append("belop", belop)
				.append("mvaKode", mvaKode).append("mvaBelop", mvaBelop)
				.append("antall", antall).toString();
	}

	public String getInvoiceItemDescription() {
		return this.linjeBeskrivelse;
	}

	public BigDecimal getNettoBelop() {
		return nettoBelop;
	}

	public void setNettoBelop(BigDecimal nettoBelop) {
		this.nettoBelop = nettoBelop;
	}

	public Date getTransDato() {
		return transDato;
	}

	public void setTransDato(Date transDato) {
		this.transDato = transDato;
	}

	public int compareTo(final TgFakturaLinje other) {
		return new CompareToBuilder().append(transDato, other.transDato)
				.toComparison();
	}

	public BigDecimal getPris() {
		return pris;
	}

	public void setPris(BigDecimal pris) {
		this.pris = pris;
	}
}
