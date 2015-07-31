package no.ica.fraf.model;

import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Klasse for tabell KONTRAKT_TYPE
 * 
 * @author abr99
 * 
 */
public class KontraktType extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private String kontraktTypeId;

	/** nullable persistent field */
	private String kontraktTypeKode;

	/** nullable persistent field */
	private String kontraktTypeNavn;

	/** persistent field */
	private Set kontraktBetingelses;
	private Integer gjeldende;

	/**
	 * @param kontraktTypeId
	 * @param kontraktTypeKode
	 * @param kontraktTypeNavn
	 * @param kontraktBetingelses
	 */
	public KontraktType(String kontraktTypeId, String kontraktTypeKode,
			String kontraktTypeNavn, Set kontraktBetingelses,Integer gjeldende) {
		this.kontraktTypeId = kontraktTypeId;
		this.kontraktTypeKode = kontraktTypeKode;
		this.kontraktTypeNavn = kontraktTypeNavn;
		this.kontraktBetingelses = kontraktBetingelses;
		this.gjeldende=gjeldende;
	}

	/** default constructor */
	public KontraktType() {
	}

	/**
	 * @param kontraktTypeId
	 * @param kontraktBetingelses
	 */
	public KontraktType(String kontraktTypeId, Set kontraktBetingelses) {
		this.kontraktTypeId = kontraktTypeId;
		this.kontraktBetingelses = kontraktBetingelses;
	}

	/**
	 * @return id
	 */
	public String getKontraktTypeId() {
		return this.kontraktTypeId;
	}

	/**
	 * @param kontraktTypeId
	 */
	public void setKontraktTypeId(String kontraktTypeId) {
		this.kontraktTypeId = kontraktTypeId;
	}

	/**
	 * @return kode
	 */
	public String getKontraktTypeKode() {
		return this.kontraktTypeKode;
	}

	/**
	 * @param kontraktTypeKode
	 */
	public void setKontraktTypeKode(String kontraktTypeKode) {
		this.kontraktTypeKode = kontraktTypeKode;
	}

	/**
	 * @return navn
	 */
	public String getKontraktTypeNavn() {
		return this.kontraktTypeNavn;
	}

	/**
	 * @param kontraktTypeNavn
	 */
	public void setKontraktTypeNavn(String kontraktTypeNavn) {
		this.kontraktTypeNavn = kontraktTypeNavn;
	}

	/**
	 * @return kontraktbetingelser
	 */
	public Set getKontraktBetingelses() {
		return this.kontraktBetingelses;
	}

	/**
	 * @param kontraktBetingelses
	 */
	public void setKontraktBetingelses(Set kontraktBetingelses) {
		this.kontraktBetingelses = kontraktBetingelses;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof KontraktType))
			return false;
		KontraktType castOther = (KontraktType) other;
		return new EqualsBuilder().append(kontraktTypeKode,
				castOther.kontraktTypeKode).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(kontraktTypeKode).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return kontraktTypeNavn;
	}

	public Integer getGjeldende() {
		return gjeldende;
	}

	public void setGjeldende(Integer gjeldende) {
		this.gjeldende = gjeldende;
	}

}
