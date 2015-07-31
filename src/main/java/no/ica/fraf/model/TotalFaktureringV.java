package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for view TOTAL_FAKTURERING_V
 * 
 * @author abr99
 * 
 */
public class TotalFaktureringV extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer fakturaLinjeId;

	/**
	 * 
	 */
	private Integer aar;

	/**
	 * 
	 */
	private Integer fraPeriode;

	/**
	 * 
	 */
	private Integer tilPeriode;

	/**
	 * 
	 */
	private String linjeBeskrivelse;

	/**
	 * 
	 */
	private Integer avdnr;

	/**
	 * 
	 */
	private BigDecimal totalBelop;

	/**
	 * 
	 */
	private String kjedeNavn;

	/**
	 * 
	 */
	private String regionNavn;

	/**
	 * 
	 */
	private String salgssjef;

	/**
	 * 
	 */
	private BigDecimal mvaBelop;

	/**
	 * 
	 */
	private String navn;

	/**
	 * 
	 */
	private BigDecimal sats;

	/**
	 * 
	 */
	private BigDecimal belop;

	/**
	 * 
	 */
	private String fakturaNr;

	/**
	 * 
	 */
	private String selskapNavn;

	/**
	 * Finnes ikke i view men brukes ved filtrering
	 */
	private Integer tilAar;

	/**
	 * Finnes ikke i view men brukes til filtrering
	 */
	private Integer avdNrTil;

	private String betingelseTypeKode;
	private String juridiskEier;
	private String juridiskEierAdr1;
	private String juridiskEierPostnr;
	private String juridiskEierPoststed;
	private Integer betingelseGruppeId;
	private Date forfallDato;

	/**
	 * @param fakturaLinjeId
	 * @param aar
	 * @param fraPeriode
	 * @param tilPeriode
	 * @param linjeBeskrivelse
	 * @param avdnr
	 * @param totalBelop
	 * @param kjedeNavn
	 * @param regionNavn
	 * @param salgssjef
	 * @param mvaBelop
	 * @param navn
	 * @param sats
	 * @param belop
	 * @param fakturaNr
	 * @param selskapNavn
	 */
	public TotalFaktureringV(Integer fakturaLinjeId, Integer aar,
			Integer fraPeriode, Integer tilPeriode, String linjeBeskrivelse,
			Integer avdnr, BigDecimal totalBelop, String kjedeNavn,
			String regionNavn, String salgssjef, BigDecimal mvaBelop,
			String navn, BigDecimal sats, BigDecimal belop, String fakturaNr,
			String selskapNavn,String betingelseTypeKode,String juridiskEier,String juridiskEierAdr1,
			String juridiskEierPostnr,String juridiskEierPoststed,Integer betingelseGruppeId,final Date aForfallDato) {
		super();
		this.fakturaLinjeId = fakturaLinjeId;
		this.aar = aar;
		this.fraPeriode = fraPeriode;
		this.tilPeriode = tilPeriode;
		this.linjeBeskrivelse = linjeBeskrivelse;
		this.avdnr = avdnr;
		this.totalBelop = totalBelop;
		this.kjedeNavn = kjedeNavn;
		this.regionNavn = regionNavn;
		this.salgssjef = salgssjef;
		this.mvaBelop = mvaBelop;
		this.navn = navn;
		this.sats = sats;
		this.belop = belop;
		this.fakturaNr = fakturaNr;
		this.selskapNavn = selskapNavn;
		this.betingelseTypeKode=betingelseTypeKode;
		this.juridiskEier=juridiskEier;
		this.juridiskEierAdr1=juridiskEierAdr1;
		this.juridiskEierPostnr=juridiskEierPostnr;
		this.juridiskEierPoststed=juridiskEierPoststed;
		this.betingelseGruppeId=betingelseGruppeId;
		this.forfallDato=aForfallDato;
		
	}

	/**
	 * 
	 */
	public TotalFaktureringV() {
		super();
	}

	/**
	 * Finner kolonnenavn ut i fra indeks
	 * 
	 * @param colIndex
	 * @return kolonnenavn
	 */
	public static String getColumndefName(int colIndex) {
		switch (colIndex) {
		case 0:
			return "aar";
		case 1:
			return "fraPeriode";
		case 2:
			return "tilPeriode";
		case 3:
			return "avdnr";
		case 4:
			return "navn";
		case 5:
			return "linjeBeskrivelse";
		case 6:
			return "sats";
		case 7:
			return "belop";
		case 8:
			return "mvaBelop";
		case 9:
			return "kjedeNavn";
		case 10:
			return "regionNavn";
		case 11:
			return "salgssjef";
		case 12:
			return "totalBelop";
		}
		return "";
	}

	/**
	 * @return avdnr
	 */
	public Integer getAvdnr() {
		return avdnr;
	}

	/**
	 * @param avdnr
	 */
	public void setAvdnr(Integer avdnr) {
		this.avdnr = avdnr;
	}

	/**
	 * @return beløp
	 */
	public BigDecimal getBelop() {
		return belop;
	}

	/**
	 * @param belop
	 */
	public void setBelop(BigDecimal belop) {
		this.belop = belop;
	}

	/**
	 * @return id
	 */
	public Integer getFakturaLinjeId() {
		return fakturaLinjeId;
	}

	/**
	 * @param fakturaLinjeId
	 */
	public void setFakturaLinjeId(Integer fakturaLinjeId) {
		this.fakturaLinjeId = fakturaLinjeId;
	}

	/**
	 * @return fra periode
	 */
	public Integer getFraPeriode() {
		return fraPeriode;
	}

	/**
	 * @param fraPeriode
	 */
	public void setFraPeriode(Integer fraPeriode) {
		this.fraPeriode = fraPeriode;
	}

	/**
	 * @return kjedenavn
	 */
	public String getKjedeNavn() {
		return kjedeNavn;
	}

	/**
	 * @param kjedeNavn
	 */
	public void setKjedeNavn(String kjedeNavn) {
		this.kjedeNavn = kjedeNavn;
	}

	/**
	 * @return linjebeskrivelse
	 */
	public String getLinjeBeskrivelse() {
		return linjeBeskrivelse;
	}

	/**
	 * @param linjeBeskrivelse
	 */
	public void setLinjeBeskrivelse(String linjeBeskrivelse) {
		this.linjeBeskrivelse = linjeBeskrivelse;
	}

	/**
	 * @return mvabeløp
	 */
	public BigDecimal getMvaBelop() {
		return mvaBelop;
	}

	/**
	 * @param mvaBelop
	 */
	public void setMvaBelop(BigDecimal mvaBelop) {
		this.mvaBelop = mvaBelop;
	}

	/**
	 * @return navn
	 */
	public String getNavn() {
		return navn;
	}

	/**
	 * @param navn
	 */
	public void setNavn(String navn) {
		this.navn = navn;
	}

	/**
	 * @return regionnavn
	 */
	public String getRegionNavn() {
		return regionNavn;
	}

	/**
	 * @param regionNavn
	 */
	public void setRegionNavn(String regionNavn) {
		this.regionNavn = regionNavn;
	}

	/**
	 * @return salgssjef
	 */
	public String getSalgssjef() {
		return salgssjef;
	}

	/**
	 * @param salgssjef
	 */
	public void setSalgssjef(String salgssjef) {
		this.salgssjef = salgssjef;
	}

	/**
	 * @return sats
	 */
	public BigDecimal getSats() {
		return sats;
	}

	/**
	 * @param sats
	 */
	public void setSats(BigDecimal sats) {
		this.sats = sats;
	}

	/**
	 * @return til periode
	 */
	public Integer getTilPeriode() {
		return tilPeriode;
	}

	/**
	 * @param tilPeriode
	 */
	public void setTilPeriode(Integer tilPeriode) {
		this.tilPeriode = tilPeriode;
	}

	/**
	 * @return totalbeløp
	 */
	public BigDecimal getTotalBelop() {
		return totalBelop;
	}

	/**
	 * @param totalBelop
	 */
	public void setTotalBelop(BigDecimal totalBelop) {
		this.totalBelop = totalBelop;
	}

	/**
	 * @return år
	 */
	public Integer getAar() {
		return aar;
	}

	/**
	 * @param aar
	 */
	public void setAar(Integer aar) {
		this.aar = aar;
	}

	/**
	 * @return Returns the fakturaNr.
	 */
	public String getFakturaNr() {
		return fakturaNr;
	}

	/**
	 * @param fakturaNr
	 *            The fakturaNr to set.
	 */
	public void setFakturaNr(String fakturaNr) {
		this.fakturaNr = fakturaNr;
	}

	/**
	 * @return Returns the selskapNavn.
	 */
	public String getSelskapNavn() {
		return selskapNavn;
	}

	/**
	 * @param selskapNavn
	 *            The selskapNavn to set.
	 */
	public void setSelskapNavn(String selskapNavn) {
		this.selskapNavn = selskapNavn;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("fakturaLinjeId",
				fakturaLinjeId).append("aar", aar).append("fraPeriode",
				fraPeriode).append("tilPeriode", tilPeriode).append(
				"linjeBeskrivelse", linjeBeskrivelse).append("avdnr", avdnr)
				.append("totalBelop", totalBelop)
				.append("kjedeNavn", kjedeNavn)
				.append("regionNavn", regionNavn)
				.append("salgssjef", salgssjef).append("mvaBelop", mvaBelop)
				.append("navn", navn).append("sats", sats).append("belop",
						belop).toString();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof TotalFaktureringV))
			return false;
		TotalFaktureringV castOther = (TotalFaktureringV) other;
		return new EqualsBuilder().append(fakturaLinjeId,
				castOther.fakturaLinjeId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(fakturaLinjeId).toHashCode();
	}

	/**
	 * @return Returns the tilAar.
	 */
	public Integer getTilAar() {
		return tilAar;
	}

	/**
	 * @param tilAar
	 *            The tilAar to set.
	 */
	public void setTilAar(Integer tilAar) {
		this.tilAar = tilAar;
	}

	/**
	 * @return Returns the avdNrTil.
	 */
	public Integer getAvdNrTil() {
		return avdNrTil;
	}

	/**
	 * @param avdNrTil
	 *            The avdNrTil to set.
	 */
	public void setAvdNrTil(Integer avdNrTil) {
		this.avdNrTil = avdNrTil;
	}

	public String getBetingelseTypeKode() {
		return betingelseTypeKode;
	}

	public void setBetingelseTypeKode(String betingelseTypeKode) {
		this.betingelseTypeKode = betingelseTypeKode;
	}

	public String getJuridiskEier() {
		return juridiskEier;
	}

	public void setJuridiskEier(String juridiskEier) {
		this.juridiskEier = juridiskEier;
	}

	public String getJuridiskEierAdr1() {
		return juridiskEierAdr1;
	}

	public void setJuridiskEierAdr1(String juridiskEierAdr1) {
		this.juridiskEierAdr1 = juridiskEierAdr1;
	}

	public String getJuridiskEierPostnr() {
		return juridiskEierPostnr;
	}

	public void setJuridiskEierPostnr(String juridiskEierPostnr) {
		this.juridiskEierPostnr = juridiskEierPostnr;
	}

	public String getJuridiskEierPoststed() {
		return juridiskEierPoststed;
	}

	public void setJuridiskEierPoststed(String juridiskEierPoststed) {
		this.juridiskEierPoststed = juridiskEierPoststed;
	}

	public Integer getBetingelseGruppeId() {
		return betingelseGruppeId;
	}

	public void setBetingelseGruppeId(Integer betingelseGruppeId) {
		this.betingelseGruppeId = betingelseGruppeId;
	}

	public Date getForfallDato() {
		return forfallDato;
	}

	public void setForfallDato(Date forfallDato) {
		this.forfallDato = forfallDato;
	}
}
