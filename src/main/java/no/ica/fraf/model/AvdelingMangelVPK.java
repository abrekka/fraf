package no.ica.fraf.model;

import java.io.Serializable;

/**
 * Nøkkelklasse for AvdelingMangelV
 * @author abr99
 *
 */
public class AvdelingMangelVPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Integer avdnr;
	/**
	 * 
	 */
	private String mangelBeskrivelse;
	/**
	 * 
	 */
	public AvdelingMangelVPK() {
		super();
	}
	/**
	 * @param avdnr
	 * @param beskrivelse
	 */
	public AvdelingMangelVPK(Integer avdnr, String beskrivelse) {
		super();
		this.avdnr = avdnr;
		mangelBeskrivelse = beskrivelse;
	}
	/**
	 * @return Returns the avdnr.
	 */
	public Integer getAvdnr() {
		return avdnr;
	}
	/**
	 * @param avdnr The avdnr to set.
	 */
	public void setAvdnr(Integer avdnr) {
		this.avdnr = avdnr;
	}
	/**
	 * @return Returns the mangelBeskrivelse.
	 */
	public String getMangelBeskrivelse() {
		return mangelBeskrivelse;
	}
	/**
	 * @param mangelBeskrivelse The mangelBeskrivelse to set.
	 */
	public void setMangelBeskrivelse(String mangelBeskrivelse) {
		this.mangelBeskrivelse = mangelBeskrivelse;
	}
}
