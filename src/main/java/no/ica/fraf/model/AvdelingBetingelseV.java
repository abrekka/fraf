package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * View AVDELING_BETINGELSE_V
 * 
 * @author abr99
 * 
 */
public class AvdelingBetingelseV extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer avdelingBetingelseId;

	/**
	 * 
	 */
	private Integer avdnr;

	/**
	 * 
	 */
	private String navn;

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
	private String avregningFrekvens;

	/**
	 * 
	 */
	private String avregning;

	/**
	 * 
	 */
	private String region;

	/**
	 * 
	 */
	private String kjede;

	/**
	 * 
	 */
	private Integer speilet;

	/**
	 * 
	 */
	private String status;
	/**
	 * 
	 */
	private String kontraktType;
	/**
	 * 
	 */
	private String betingelseGruppeNavn;
    /**
     * 
     */
    private String avregningBasisTypeTxt;
	
	/**
	 * 
	 */
	public AvdelingBetingelseV() {
	}

	/**
	 * @param avdelingBetingelseId
	 * @param avdnr
	 * @param avregning
	 * @param frekvens
	 * @param belop
	 * @param navn
	 * @param dato
	 * @param kjede
	 * @param navn2
	 * @param region
	 * @param sats
	 * @param dato2
	 * @param speilet
	 * @param status
	 * @param kontraktType
	 * @param betingelseGruppeNavn
	 */
	public AvdelingBetingelseV(Integer avdelingBetingelseId, Integer avdnr,
			String avregning, String frekvens, BigDecimal belop, String navn,
			Date dato, String kjede, String navn2, String region,
			BigDecimal sats, Date dato2, Integer speilet,String status,String kontraktType,String betingelseGruppeNavn,String avregningBasisTypeTxt) {
		this.avdelingBetingelseId = avdelingBetingelseId;
		this.avdnr = avdnr;
		this.avregning = avregning;
		avregningFrekvens = frekvens;
		this.belop = belop;
		betingelseNavn = navn;
		fraDato = dato;
		this.kjede = kjede;
		navn = navn2;
		this.region = region;
		this.sats = sats;
		tilDato = dato2;
		this.speilet = speilet;
		this.status = status;
		this.kontraktType = kontraktType;
		this.betingelseGruppeNavn = betingelseGruppeNavn;
        this.avregningBasisTypeTxt = avregningBasisTypeTxt;
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
	 * @return avregning
	 */
	public String getAvregning() {
		return avregning;
	}

	/**
	 * @param avregning
	 */
	public void setAvregning(String avregning) {
		this.avregning = avregning;
	}

	/**
	 * @return frekvens
	 */
	public String getAvregningFrekvens() {
		return avregningFrekvens;
	}

	/**
	 * @param avregningFrekvens
	 */
	public void setAvregningFrekvens(String avregningFrekvens) {
		this.avregningFrekvens = avregningFrekvens;
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
	 * @return betingelse
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
	 * @return region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region
	 */
	public void setRegion(String region) {
		this.region = region;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"avdnr", avdnr).append("navn", navn).append("betingelseNavn",
				betingelseNavn).append("fraDato", fraDato).append("tilDato",
				tilDato).append("sats", sats).append("belop", belop).append(
				"avregningFrekvens", avregningFrekvens).append("avregning",
				avregning).append("region", region).append("kjede", kjede)
				.toString();
	}

	/**
	 * @return betingelseid
	 */
	public Integer getAvdelingBetingelseId() {
		return avdelingBetingelseId;
	}

	/**
	 * @param avdelingBetingelseId
	 */
	public void setAvdelingBetingelseId(Integer avdelingBetingelseId) {
		this.avdelingBetingelseId = avdelingBetingelseId;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AvdelingBetingelseV))
			return false;
		AvdelingBetingelseV castOther = (AvdelingBetingelseV) other;
		return new EqualsBuilder().append(avdelingBetingelseId,
				castOther.avdelingBetingelseId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdelingBetingelseId).toHashCode();
	}

	/**
	 * @return Returns the speilet.
	 */
	public Integer getSpeilet() {
		return speilet;
	}

	/**
	 * @param speilet
	 *            The speilet to set.
	 */
	public void setSpeilet(Integer speilet) {
		this.speilet = speilet;
	}

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return Returns the kontraktType.
	 */
	public String getKontraktType() {
		return kontraktType;
	}

	/**
	 * @param kontraktType The kontraktType to set.
	 */
	public void setKontraktType(String kontraktType) {
		this.kontraktType = kontraktType;
	}

	/**
	 * @return Returns the betingelseGruppeNavn.
	 */
	public String getBetingelseGruppeNavn() {
		return betingelseGruppeNavn;
	}

	/**
	 * @param betingelseGruppeNavn The betingelseGruppeNavn to set.
	 */
	public void setBetingelseGruppeNavn(String betingelseGruppeNavn) {
		this.betingelseGruppeNavn = betingelseGruppeNavn;
	}

    public String getAvregningBasisTypeTxt() {
        return avregningBasisTypeTxt;
    }

    public void setAvregningBasisTypeTxt(String avregningBasisTypeTxt) {
        this.avregningBasisTypeTxt = avregningBasisTypeTxt;
    }

	
}
