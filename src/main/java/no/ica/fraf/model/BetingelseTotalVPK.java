package no.ica.fraf.model;

import java.io.Serializable;

/**
 * Nøkkelklasse fro BetingelseTotalV
 * 
 * @author abr99
 * 
 */
public class BetingelseTotalVPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String bokfSelskap;

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
	private String betingelseNavn;

	/**
	 * 
	 */
	public BetingelseTotalVPK() {
	}

	/**
	 * @param bokfSelskap
	 * @param aar
	 * @param fraPeriode
	 * @param tilPeriode
	 * @param betingelseNavn
	 */
	public BetingelseTotalVPK(String bokfSelskap, Integer aar,
			Integer fraPeriode, Integer tilPeriode, String betingelseNavn) {
		super();
		this.bokfSelskap = bokfSelskap;
		this.aar = aar;
		this.fraPeriode = fraPeriode;
		this.tilPeriode = tilPeriode;
		this.betingelseNavn = betingelseNavn;
	}

	/**
	 * @return betingelsetypeid
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
}
