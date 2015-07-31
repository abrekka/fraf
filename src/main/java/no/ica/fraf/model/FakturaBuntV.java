package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for view FAKTURA_BUNT_V
 * 
 * @author atb
 * 
 */
public class FakturaBuntV extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer buntId;

	/**
	 * 
	 */
	private String buntStatus;

	/**
	 * 
	 */
	private Date opprettetDato;

	/**
	 * 
	 */
	private String opprettetAv;

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
	private Integer fraAvdnr;

	/**
	 * 
	 */
	private Integer tilAvdnr;

	/**
	 * 
	 */
	private BigDecimal buntSum;

	/**
	 * 
	 */
	private Integer betingelseGruppeId;

	/**
	 * 
	 */
	private String betingelseGruppeNavn;
	private Integer opprettetMaaned;
	private Integer opprettetAar;

	/**
	 * 
	 */
	public FakturaBuntV() {
	}

	/**
	 * @param id
	 * @param status
	 * @param sum
	 * @param avdnr
	 * @param av
	 * @param dato
	 * @param fraPeriode
	 * @param tilPeriode
	 * @param avdnr2
	 * @param aar
	 * @param betingelseGruppeId
	 * @param betingelseGruppeNavn
	 */
	public FakturaBuntV(Integer id, String status, BigDecimal sum,
			Integer avdnr, String av, Date dato, Integer fraPeriode,
			Integer tilPeriode, Integer avdnr2, Integer aar,
			Integer betingelseGruppeId, String betingelseGruppeNavn,Integer opprettetMaaned,Integer opprettetAar) {
		buntId = id;
		buntStatus = status;
		buntSum = sum;
		fraAvdnr = avdnr;
		opprettetAv = av;
		opprettetDato = dato;
		this.fraPeriode = fraPeriode;
		this.tilPeriode = tilPeriode;
		tilAvdnr = avdnr2;
		this.aar = aar;
		this.betingelseGruppeId = betingelseGruppeId;
		this.betingelseGruppeNavn = betingelseGruppeNavn;
		this.opprettetMaaned=opprettetMaaned;
		this.opprettetAar=opprettetAar;
	}

	/**
	 * @return buntid
	 */
	public Integer getBuntId() {
		return buntId;
	}

	/**
	 * @param buntId
	 */
	public void setBuntId(Integer buntId) {
		this.buntId = buntId;
	}

	/**
	 * @return buntstatus
	 */
	public String getBuntStatus() {
		return buntStatus;
	}

	/**
	 * @param buntStatus
	 */
	public void setBuntStatus(String buntStatus) {
		this.buntStatus = buntStatus;
	}

	/**
	 * @return buntsum
	 */
	public BigDecimal getBuntSum() {
		return buntSum;
	}

	/**
	 * @param buntSum
	 */
	public void setBuntSum(BigDecimal buntSum) {
		this.buntSum = buntSum;
	}

	/**
	 * @return fra avdnr
	 */
	public Integer getFraAvdnr() {
		return fraAvdnr;
	}

	/**
	 * @param fraAvdnr
	 */
	public void setFraAvdnr(Integer fraAvdnr) {
		this.fraAvdnr = fraAvdnr;
	}

	/**
	 * @return opprettet av
	 */
	public String getOpprettetAv() {
		return opprettetAv;
	}

	/**
	 * @param opprettetAv
	 */
	public void setOpprettetAv(String opprettetAv) {
		this.opprettetAv = opprettetAv;
	}

	/**
	 * @return opprettet dato
	 */
	public Date getOpprettetDato() {
		return opprettetDato;
	}

	/**
	 * @param opprettetDato
	 */
	public void setOpprettetDato(Date opprettetDato) {
		this.opprettetDato = opprettetDato;
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
	 * @return til avdnr
	 */
	public Integer getTilAvdnr() {
		return tilAvdnr;
	}

	/**
	 * @param tilAvdnr
	 */
	public void setTilAvdnr(Integer tilAvdnr) {
		this.tilAvdnr = tilAvdnr;
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
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof FakturaBuntV))
			return false;
		FakturaBuntV castOther = (FakturaBuntV) other;
		return new EqualsBuilder().append(buntId, castOther.buntId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(buntId).toHashCode();
	}

	/**
	 * @return betingelsegruppeid
	 */
	public Integer getBetingelseGruppeId() {
		return betingelseGruppeId;
	}

	/**
	 * @param betingelseGruppeId
	 */
	public void setBetingelseGruppeId(Integer betingelseGruppeId) {
		this.betingelseGruppeId = betingelseGruppeId;
	}

	/**
	 * @return betingelsegruppenavn
	 */
	public String getBetingelseGruppeNavn() {
		return betingelseGruppeNavn;
	}

	/**
	 * @param betingelseGruppeNavn
	 */
	public void setBetingelseGruppeNavn(String betingelseGruppeNavn) {
		this.betingelseGruppeNavn = betingelseGruppeNavn;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"buntId", buntId).append("buntStatus", buntStatus).append(
				"opprettetDato", opprettetDato).append("opprettetAv",
				opprettetAv).append("aar", aar)
				.append("fraPeriode", fraPeriode).append("tilPeriode",
						tilPeriode).append("fraAvdnr", fraAvdnr).append(
						"tilAvdnr", tilAvdnr).append("buntSum", buntSum)
				.append("betingelseGruppeId", betingelseGruppeId).append(
						"betingelseGruppeNavn", betingelseGruppeNavn)
				.toString();
	}

	public Integer getOpprettetMaaned() {
		return opprettetMaaned;
	}

	public void setOpprettetMaaned(Integer opprettetMaaned) {
		this.opprettetMaaned = opprettetMaaned;
	}

	public Integer getOpprettetAar() {
		return opprettetAar;
	}

	public void setOpprettetAar(Integer opprettetAar) {
		this.opprettetAar = opprettetAar;
	}
}
