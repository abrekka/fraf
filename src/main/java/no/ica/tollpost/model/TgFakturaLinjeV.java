package no.ica.tollpost.model;

import java.math.BigDecimal;
import java.util.Date;

import no.ica.fraf.model.BaseObject;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TgFakturaLinjeV extends BaseObject {
	private Integer tgFakturaLinjeId;

	private Integer tgFakturaId;

	private Integer buntId;

	private String avdelingNavn;

	private String juridiskEierNavn;

	private String adr1;

	private String adr2;

	private Integer postnr;

	private String poststed;

	private Integer avdnr;

	private Date fakturaDato;

	private Date forfallDato;

	private BigDecimal fakturaNr;

	private String userName;

	private String kid;

	private BigDecimal fakturaBelop;

	private BigDecimal mvaButikk;
	private BigDecimal mvaIca;

	private BigDecimal provisjonButikk;
	private BigDecimal provisjonIca;

	private String linjeBeskrivelse;

	private Integer antall;

	private BigDecimal linjeBelop;

	private BigDecimal linjeMvaBelop;

	private String fakturaTittel;
	private Integer icaKonto;
	private BigDecimal linjeNettoBelop;
	private String meldingstype;
	private BigDecimal pris;

	public TgFakturaLinjeV() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TgFakturaLinjeV(String adr1, String adr2, Integer antall,
			String navn, Integer avdnr, BigDecimal provisjonButikk, BigDecimal provisjonIca, Integer id,
			Date dato, BigDecimal nr, Date dato2, String navn2, String kid,
			BigDecimal belop2, String beskrivelse, BigDecimal belop3,
			BigDecimal mvaButikk,BigDecimal mvaIca, Integer postnr, String poststed, Integer id2,
			Integer id3, BigDecimal fakturaBelop, String name,String fakturaTittel,Integer icaKonto,BigDecimal linjeNettoBelop,String meldingstype,BigDecimal pris) {
		super();
		// TODO Auto-generated constructor stub
		this.adr1 = adr1;
		this.adr2 = adr2;
		this.antall = antall;
		avdelingNavn = navn;
		this.avdnr = avdnr;
		this.provisjonButikk = provisjonButikk;
		this.provisjonIca = provisjonIca;
		buntId = id;
		fakturaDato = dato;
		fakturaNr = nr;
		forfallDato = dato2;
		juridiskEierNavn = navn2;
		this.kid = kid;
		linjeBelop = belop2;
		linjeBeskrivelse = beskrivelse;
		linjeMvaBelop = belop3;
		this.mvaButikk = mvaButikk;
		this.mvaIca = mvaIca;
		this.postnr = postnr;
		this.poststed = poststed;
		tgFakturaId = id2;
		tgFakturaLinjeId = id3;
		this.fakturaBelop = fakturaBelop;
		userName = name;
		this.fakturaTittel=fakturaTittel;
		this.icaKonto=icaKonto;
		this.linjeNettoBelop=linjeNettoBelop;
		this.meldingstype=meldingstype;
		this.pris=pris;
	}

	public String getAdr1() {
		return adr1;
	}

	public void setAdr1(String adr1) {
		this.adr1 = adr1;
	}

	public String getAdr2() {
		return adr2;
	}

	public void setAdr2(String adr2) {
		this.adr2 = adr2;
	}

	public Integer getAntall() {
		return antall;
	}

	public void setAntall(Integer antall) {
		this.antall = antall;
	}

	public String getAvdelingNavn() {
		return avdelingNavn;
	}

	public void setAvdelingNavn(String avdelingNavn) {
		this.avdelingNavn = avdelingNavn;
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
		this.provisjonIca= provisjonIca;
	}

	public Integer getBuntId() {
		return buntId;
	}

	public void setBuntId(Integer buntId) {
		this.buntId = buntId;
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

	public Date getForfallDato() {
		return forfallDato;
	}

	public void setForfallDato(Date forfallDato) {
		this.forfallDato = forfallDato;
	}

	public String getJuridiskEierNavn() {
		return juridiskEierNavn;
	}

	public void setJuridiskEierNavn(String juridiskEierNavn) {
		this.juridiskEierNavn = juridiskEierNavn;
	}

	public String getKid() {
		return kid;
	}

	public void setKid(String kid) {
		this.kid = kid;
	}

	public BigDecimal getLinjeBelop() {
		return linjeBelop;
	}

	public void setLinjeBelop(BigDecimal linjeBelop) {
		this.linjeBelop = linjeBelop;
	}

	public String getLinjeBeskrivelse() {
		return linjeBeskrivelse;
	}

	public void setLinjeBeskrivelse(String linjeBeskrivelse) {
		this.linjeBeskrivelse = linjeBeskrivelse;
	}

	public BigDecimal getLinjeMvaBelop() {
		return linjeMvaBelop;
	}

	public void setLinjeMvaBelop(BigDecimal linjeMvaBelop) {
		this.linjeMvaBelop = linjeMvaBelop;
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

	public Integer getPostnr() {
		return postnr;
	}

	public void setPostnr(Integer postnr) {
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

	public Integer getTgFakturaLinjeId() {
		return tgFakturaLinjeId;
	}

	public void setTgFakturaLinjeId(Integer tgFakturaLinjeId) {
		this.tgFakturaLinjeId = tgFakturaLinjeId;
	}

	public BigDecimal getFakturaBelop() {
		return fakturaBelop;
	}

	public void setFakturaBelop(BigDecimal fakturaBelop) {
		this.fakturaBelop = fakturaBelop;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof TgFakturaLinjeV))
			return false;
		TgFakturaLinjeV castOther = (TgFakturaLinjeV) other;
		return new EqualsBuilder().append(tgFakturaLinjeId,
				castOther.tgFakturaLinjeId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(tgFakturaLinjeId).toHashCode();
	}

	public String getFakturaTittel() {
		return fakturaTittel;
	}

	public void setFakturaTittel(String fakturaTittel) {
		this.fakturaTittel = fakturaTittel;
	}

	public Integer getIcaKonto() {
		return icaKonto;
	}

	public void setIcaKonto(Integer icaKonto) {
		this.icaKonto = icaKonto;
	}

	public BigDecimal getLinjeNettoBelop() {
		return linjeNettoBelop;
	}

	public void setLinjeNettoBelop(BigDecimal linjeNettoBelop) {
		this.linjeNettoBelop = linjeNettoBelop;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("tgFakturaLinjeId",
				tgFakturaLinjeId).append("tgFakturaId", tgFakturaId).append(
				"buntId", buntId).append("avdelingNavn", avdelingNavn).append(
				"juridiskEierNavn", juridiskEierNavn).append("adr1", adr1)
				.append("adr2", adr2).append("postnr", postnr).append(
						"poststed", poststed).append("avdnr", avdnr).append(
						"fakturaDato", fakturaDato).append("forfallDato",
						forfallDato).append("fakturaNr", fakturaNr).append(
						"userName", userName).append("kid", kid).append(
						"fakturaBelop", fakturaBelop).append("mvaButikk",
						mvaButikk).append("mvaIca", mvaIca).append(
						"provisjonButikk", provisjonButikk).append(
						"provisjonIca", provisjonIca).append(
						"linjeBeskrivelse", linjeBeskrivelse).append("antall",
						antall).append("linjeBelop", linjeBelop).append(
						"linjeMvaBelop", linjeMvaBelop).append("fakturaTittel",
						fakturaTittel).append("icaKonto", icaKonto).append(
						"linjeNettoBelop", linjeNettoBelop).toString();
	}

	public String getMeldingstype() {
		return meldingstype;
	}

	public void setMeldingstype(String meldingstype) {
		this.meldingstype = meldingstype;
	}

	public BigDecimal getPris() {
		return pris;
	}

	public void setPris(BigDecimal pris) {
		this.pris = pris;
	}

}
