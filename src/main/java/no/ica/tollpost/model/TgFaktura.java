package no.ica.tollpost.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import no.ica.fraf.common.Batchable;
import no.ica.fraf.model.BaseObject;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.xml.InvoiceInterface;
import no.ica.fraf.xml.InvoiceItemInterface;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TgFaktura extends BaseObject implements InvoiceInterface {
	private Integer tgFakturaId;

	private Integer avdnr;

	private Date fakturaDato;

	private Date forfallDato;

	private BigDecimal fakturaNr;

	private BigDecimal fakturaBelop;

	private Date opprettetDato;

	private String fakturaTittel;

	private String mottakerNavn;

	private String adresse1;

	private String adresse2;

	private String postnr;

	private String poststed;

	private BigDecimal provisjonButikk;
	private BigDecimal provisjonIca;

	private BigDecimal mvaButikk;
	private BigDecimal mvaIca;

	private String meldingstype;

	private Bunt bunt;
	private String kid;
	private Integer icaKonto;
	private Date xmlGenerert;

	private Set<TgFakturaLinje> tgFakturaLinjer;

	public TgFaktura() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TgFaktura(String adresse1, String adresse2, Integer avdnr,
			BigDecimal provisjonButikk,BigDecimal provisjonIca, Bunt bunt, Date dato, BigDecimal nr, String tittel,
			Date dato2, String meldingstype, String navn, BigDecimal mvaButikk,BigDecimal mvaIca,
			Date dato3, String postnr, String poststed, Integer id,
			BigDecimal fakturaBelop, Set<TgFakturaLinje> tgFakturaLinjer,String kid,Integer icaKonto,Date xmlGenerert) {
		super();
		// TODO Auto-generated constructor stub
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.avdnr = avdnr;
		this.provisjonButikk = provisjonButikk;
		this.provisjonIca = provisjonIca;
		this.bunt = bunt;
		fakturaDato = dato;
		fakturaNr = nr;
		fakturaTittel = tittel;
		forfallDato = dato2;
		this.meldingstype = meldingstype;
		mottakerNavn = navn;
		this.mvaButikk = mvaButikk;
		this.mvaIca = mvaIca;
		opprettetDato = dato3;
		this.postnr = postnr;
		this.poststed = poststed;
		tgFakturaId = id;
		this.fakturaBelop = fakturaBelop;
		this.tgFakturaLinjer = tgFakturaLinjer;
		this.kid=kid;
		this.icaKonto=icaKonto;
		this.xmlGenerert=xmlGenerert;
	}

	public String getAdresse1() {
		return adresse1;
	}

	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public Integer getAvdnr() {
		return avdnr;
	}

	public void setAvdnr(Integer avdnr) {
		this.avdnr = avdnr;
	}

	public BigDecimal getProvisjonButikk() {
		return provisjonButikk;
	}

	public void setProvisjonButikk(BigDecimal provisjonButikk) {
		this.provisjonButikk = provisjonButikk;
	}
	public BigDecimal getProvisjonIca() {
		return provisjonIca;
	}

	public void setProvisjonIca(BigDecimal provisjonIca) {
		this.provisjonIca = provisjonIca;
	}

	public Bunt getBunt() {
		return bunt;
	}

	public void setBunt(Bunt bunt) {
		this.bunt = bunt;
	}

	public Date getFakturaDato() {
		return fakturaDato;
	}

	public void setFakturaDato(Date fakturaDato) {
		this.fakturaDato = fakturaDato;
	}

	public BigDecimal getFakturaNr() {
		return fakturaNr;
	}

	public void setFakturaNr(BigDecimal fakturaNr) {
		this.fakturaNr = fakturaNr;
	}

	public String getFakturaTittel() {
		return fakturaTittel;
	}

	public void setFakturaTittel(String fakturaTittel) {
		this.fakturaTittel = fakturaTittel;
	}

	public Date getForfallDato() {
		return forfallDato;
	}

	public void setForfallDato(Date forfallDato) {
		this.forfallDato = forfallDato;
	}

	public String getMeldingstype() {
		return meldingstype;
	}

	public void setMeldingstype(String meldingstype) {
		this.meldingstype = meldingstype;
	}

	public String getMottakerNavn() {
		return mottakerNavn;
	}

	public void setMottakerNavn(String mottakerNavn) {
		this.mottakerNavn = mottakerNavn;
	}

	public BigDecimal getMvaButikk() {
		return mvaButikk;
	}

	public void setMvaButikk(BigDecimal mvaButikk) {
		this.mvaButikk = mvaButikk;
	}
	
	public BigDecimal getMvaIca() {
		return mvaIca;
	}

	public void setMvaIca(BigDecimal mvaIca) {
		this.mvaIca = mvaIca;
	}

	public Date getOpprettetDato() {
		return opprettetDato;
	}

	public void setOpprettetDato(Date opprettetDato) {
		this.opprettetDato = opprettetDato;
	}

	public String getPostnr() {
		return postnr;
	}

	public void setPostnr(String postnr) {
		this.postnr = postnr;
	}

	public String getPoststed() {
		return poststed;
	}

	public void setPoststed(String poststed) {
		this.poststed = poststed;
	}

	public Integer getTgFakturaId() {
		return tgFakturaId;
	}

	public void setTgFakturaId(Integer tgFakturaId) {
		this.tgFakturaId = tgFakturaId;
	}

	public BigDecimal getFakturaBelop() {
		return fakturaBelop;
	}

	public void setFakturaBelop(BigDecimal fakturaBelop) {
		this.fakturaBelop = fakturaBelop;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof TgFaktura))
			return false;
		TgFaktura castOther = (TgFaktura) other;
		return new EqualsBuilder().append(avdnr, castOther.avdnr).append(
				fakturaNr, castOther.fakturaNr).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdnr).append(fakturaNr)
				.toHashCode();
	}

	public Set<TgFakturaLinje> getTgFakturaLinjer() {
		return tgFakturaLinjer;
	}

	public void setTgFakturaLinjer(Set<TgFakturaLinje> tgFakturaLinjer) {
		this.tgFakturaLinjer = tgFakturaLinjer;
	}

	public Batchable getBatchable() {
		return bunt;
	}

	public Integer getStoreNo() {
		return avdnr;
	}

	public BigDecimal getInvoiceNr() {
		return fakturaNr;
	}

	public Set<InvoiceItemInterface> getInvoiceItemInterfaces() {
		Set<InvoiceItemInterface> set = new HashSet<InvoiceItemInterface>();
		if(tgFakturaLinjer!=null){
			for(TgFakturaLinje fakturaLinje:tgFakturaLinjer){
				set.add(fakturaLinje);
			}
		}
		return set;
	}

	public Date getInvoiceDate() {
		return fakturaDato;
	}

	public Date getDueDate() {
		return forfallDato;
	}

	public String getKidNr() {
		return kid;
	}
	
	public String getKid() {
		return kid;
	}
	
	public void setKid(String kid) {
		this.kid=kid;
	}

	public BigDecimal getGrossAmount() {
		return fakturaBelop;//.subtract(mvaButikk).subtract(mvaIca);
	}

	public BigDecimal getVatTotalsAmount() {
		return mvaButikk.add(mvaIca);
	}

	public BigDecimal getNetAmount() {
		return fakturaBelop.subtract(mvaButikk).subtract(mvaIca);
	}

	public BigDecimal getVatBaseAmount() {
		return getNetAmount();
	}

	public String getHeading() {
		return fakturaTittel;
	}

	public Integer getIcaKonto() {
		return icaKonto;
	}

	public void setIcaKonto(Integer icaKonto) {
		this.icaKonto = icaKonto;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("tgFakturaId", tgFakturaId)
				.append("avdnr", avdnr).append("fakturaDato", fakturaDato)
				.append("forfallDato", forfallDato).append("fakturaNr",
						fakturaNr).append("fakturaBelop", fakturaBelop).append(
						"opprettetDato", opprettetDato).append("fakturaTittel",
						fakturaTittel).append("mottakerNavn", mottakerNavn)
				.append("adresse1", adresse1).append("adresse2", adresse2)
				.append("postnr", postnr).append("poststed", poststed).append(
						"provisjonButikk", provisjonButikk).append(
						"provisjonIca", provisjonIca).append("mvaButikk",
						mvaButikk).append("mvaIca", mvaIca).append(
						"meldingstype", meldingstype).append("bunt", bunt)
				.append("kid", kid).append("icaKonto", icaKonto).append(
						"tgFakturaLinjer", tgFakturaLinjer).toString();
	}

	public Integer getNumberOfLines() {
		if(tgFakturaLinjer!=null){
			return tgFakturaLinjer.size();
		}
		return 0;
	}

	public Date getXmlGenerert() {
		return xmlGenerert;
	}

	public void setXmlGenerert(Date xmlGenerert) {
		this.xmlGenerert = xmlGenerert;
	}

	public String getCompanyName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getInvoiceId() {
		return tgFakturaId;
	}

}
