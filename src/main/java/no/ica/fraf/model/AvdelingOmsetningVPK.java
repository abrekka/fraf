package no.ica.fraf.model;

import java.io.Serializable;

/**
 * Klasse som er nøkkel for klassen AvdelingOmsetningV
 * 
 * @author abr99
 * 
 */
public class AvdelingOmsetningVPK implements Serializable {
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
	private Integer aar;

	/**
	 * 
	 */
	private Integer periode;

	/**
	 * 
	 */
	public AvdelingOmsetningVPK() {
		super();
	}

	/**
	 * @param avdnr
	 * @param aar
	 * @param periode
	 */
	public AvdelingOmsetningVPK(Integer avdnr, Integer aar, Integer periode) {
		super();
		this.avdnr = avdnr;
		this.aar = aar;
		this.periode = periode;
	}

	/**
	 * @return Returns the avdnr.
	 */
	public Integer getAvdnr() {
		return avdnr;
	}

	/**
	 * @param avdnr
	 *            The avdnr to set.
	 */
	public void setAvdnr(Integer avdnr) {
		this.avdnr = avdnr;
	}

	/**
	 * @return Returns the periode.
	 */
	public Integer getPeriode() {
		return periode;
	}

	/**
	 * @param periode
	 *            The periode to set.
	 */
	public void setPeriode(Integer periode) {
		this.periode = periode;
	}

	/**
	 * @return Returns the aar.
	 */
	public Integer getAar() {
		return aar;
	}

	/**
	 * @param aar
	 *            The aar to set.
	 */
	public void setAar(Integer aar) {
		this.aar = aar;
	}
}
