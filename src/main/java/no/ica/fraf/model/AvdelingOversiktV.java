package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for view AVDELING_OVERSIKT_V
 * 
 * @author abr99
 * 
 */
public class AvdelingOversiktV extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private AvdelingOversiktVPK avdelingOversiktVPK;

	/**
	 * 
	 */
	private String avdelingNavn;

	/**
	 * 
	 */
	private String adr1;

	/**
	 * 
	 */
	private String adr2;

	/**
	 * 
	 */
	private String postnr;

	/**
	 * 
	 */
	private String poststed;

	/**
	 * 
	 */
	private String juridiskNavn;

	/**
	 * 
	 */
	private String bokfSelskap;

	/**
	 * 
	 */
	private String kjede;

	/**
	 * 
	 */
	private String status;

	/**
	 * 
	 */
	private BigDecimal sumAar;

	/**
	 * 
	 */
	private String betingelseNavn;

	/**
	 * 
	 */
	private Date fraDato;

	/**
	 * 
	 */
	private Date tilDato;

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
	private String avregningFrekvensTypeTxt;

	/**
	 * 
	 */
	private String avregningTypeTxt;

	/**
	 * 
	 */
	private String kontraktType;

	/**
	 * 
	 */
	public AvdelingOversiktV() {
	}

	/**
	 * @param adr1
	 * @param adr2
	 * @param navn
	 * @param oversiktVPK
	 * @param txt
	 * @param txt2
	 * @param belop
	 * @param navn2
	 * @param selskap
	 * @param dato
	 * @param navn3
	 * @param kjede
	 * @param postnr
	 * @param poststed
	 * @param sats
	 * @param status
	 * @param aar
	 * @param dato2
	 * @param kontraktType
	 */
	public AvdelingOversiktV(String adr1, String adr2, String navn,
			AvdelingOversiktVPK oversiktVPK, String txt, String txt2,
			BigDecimal belop, String navn2, String selskap, Date dato,
			String navn3, String kjede, String postnr, String poststed,
			BigDecimal sats, String status, BigDecimal aar, Date dato2,
			String kontraktType) {
		this.adr1 = adr1;
		this.adr2 = adr2;
		avdelingNavn = navn;
		avdelingOversiktVPK = oversiktVPK;
		avregningFrekvensTypeTxt = txt;
		avregningTypeTxt = txt2;
		this.belop = belop;
		betingelseNavn = navn2;
		bokfSelskap = selskap;
		fraDato = dato;
		juridiskNavn = navn3;
		this.kjede = kjede;
		this.postnr = postnr;
		this.poststed = poststed;
		this.sats = sats;
		this.status = status;
		sumAar = aar;
		tilDato = dato2;
		this.kontraktType = kontraktType;
	}

	/**
	 * @return adr1
	 */
	public String getAdr1() {
		return adr1;
	}

	/**
	 * @param adr1
	 */
	public void setAdr1(String adr1) {
		this.adr1 = adr1;
	}

	/**
	 * @return adr 2
	 */
	public String getAdr2() {
		return adr2;
	}

	/**
	 * @param adr2
	 */
	public void setAdr2(String adr2) {
		this.adr2 = adr2;
	}

	/**
	 * @return navn
	 */
	public String getAvdelingNavn() {
		return avdelingNavn;
	}

	/**
	 * @param avdelingNavn
	 */
	public void setAvdelingNavn(String avdelingNavn) {
		this.avdelingNavn = avdelingNavn;
	}

	/**
	 * @return avdnr
	 */
	public Integer getAvdnr() {
		return avdelingOversiktVPK.getAvdnr();
	}

	/**
	 * @return avregningfrekvens
	 */
	public String getAvregningFrekvensTypeTxt() {
		return avregningFrekvensTypeTxt;
	}

	/**
	 * @param avregningFrekvensTypeTxt
	 */
	public void setAvregningFrekvensTypeTxt(String avregningFrekvensTypeTxt) {
		this.avregningFrekvensTypeTxt = avregningFrekvensTypeTxt;
	}

	/**
	 * @return avregningtype
	 */
	public String getAvregningTypeTxt() {
		return avregningTypeTxt;
	}

	/**
	 * @param avregningTypeTxt
	 */
	public void setAvregningTypeTxt(String avregningTypeTxt) {
		this.avregningTypeTxt = avregningTypeTxt;
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
	 * @return betingelsenavn
	 */
	public String getBetingelseNavn() {
		return betingelseNavn;
	}

	/**
	 * @param betingelseNavn
	 */
	public void setBetingelseNavn(String betingelseNavn) {
		this.betingelseNavn = betingelseNavn;
	}

	/**
	 * @return bokføringsselskap
	 */
	public String getBokfSelskap() {
		return bokfSelskap;
	}

	/**
	 * @param bokfSelskap
	 */
	public void setBokfSelskap(String bokfSelskap) {
		this.bokfSelskap = bokfSelskap;
	}

	/**
	 * @return fra dato
	 */
	public Date getFraDato() {
		return fraDato;
	}

	/**
	 * @param fraDato
	 */
	public void setFraDato(Date fraDato) {
		this.fraDato = fraDato;
	}

	/**
	 * @return juridisk navn
	 */
	public String getJuridiskNavn() {
		return juridiskNavn;
	}

	/**
	 * @param juridiskNavn
	 */
	public void setJuridiskNavn(String juridiskNavn) {
		this.juridiskNavn = juridiskNavn;
	}

	/**
	 * @return kjede
	 */
	public String getKjede() {
		return kjede;
	}

	/**
	 * @param kjede
	 */
	public void setKjede(String kjede) {
		this.kjede = kjede;
	}

	/**
	 * @return postnr
	 */
	public String getPostnr() {
		return postnr;
	}

	/**
	 * @param postnr
	 */
	public void setPostnr(String postnr) {
		this.postnr = postnr;
	}

	/**
	 * @return poststed
	 */
	public String getPoststed() {
		return poststed;
	}

	/**
	 * @param poststed
	 */
	public void setPoststed(String poststed) {
		this.poststed = poststed;
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
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return sum pr år
	 */
	public BigDecimal getSumAar() {
		return sumAar;
	}

	/**
	 * @param sumAar
	 */
	public void setSumAar(BigDecimal sumAar) {
		this.sumAar = sumAar;
	}

	/**
	 * @return til dato
	 */
	public Date getTilDato() {
		return tilDato;
	}

	/**
	 * @param tilDato
	 */
	public void setTilDato(Date tilDato) {
		this.tilDato = tilDato;
	}

	/**
	 * @return år
	 */
	public Integer getAar() {
		return avdelingOversiktVPK.getAar();
	}

	/**
	 * @return betingelseid
	 */
	public Integer getAvdelingBetingelseId() {
		return avdelingOversiktVPK.getAvdelingBetingelseId();
	}

	/**
	 * @return nøkkel
	 */
	public AvdelingOversiktVPK getAvdelingOversiktVPK() {
		return avdelingOversiktVPK;
	}

	/**
	 * @param avdelingOversiktVPK
	 */
	public void setAvdelingOversiktVPK(AvdelingOversiktVPK avdelingOversiktVPK) {
		this.avdelingOversiktVPK = avdelingOversiktVPK;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AvdelingOversiktV))
			return false;
		AvdelingOversiktV castOther = (AvdelingOversiktV) other;
		return new EqualsBuilder().append(avdelingOversiktVPK,
				castOther.avdelingOversiktVPK).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdelingOversiktVPK).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"avdelingOversiktVPK", avdelingOversiktVPK).toString();
	}

	/**
	 * @return kontrakttype
	 */
	public String getKontraktType() {
		return kontraktType;
	}

	/**
	 * @param kontraktType
	 */
	public void setKontraktType(String kontraktType) {
		this.kontraktType = kontraktType;
	}
}
