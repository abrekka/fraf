package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Klasse for tabell FORNYELSE_TYPE
 * 
 * @author abr99
 * 
 */
public class FornyelseType extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer fornyelseTypeId;

	/**
	 * 
	 */
	private String fornyelseTypeKode;

	/**
	 * 
	 */
	private Integer antallMnd;

	/**
	 * 
	 */
	private String fornyelseTypeTxt;

	/**
	 * 
	 */
	public FornyelseType() {
	}

	/**
	 * @param mnd
	 * @param id
	 * @param kode
	 * @param txt
	 */
	public FornyelseType(Integer mnd, Integer id, String kode, String txt) {
		antallMnd = mnd;
		fornyelseTypeId = id;
		fornyelseTypeKode = kode;
		fornyelseTypeTxt = txt;
	}

	/**
	 * @return antall måneder før fornyelse
	 */
	public Integer getAntallMnd() {
		return antallMnd;
	}

	/**
	 * @param antallMnd
	 */
	public void setAntallMnd(Integer antallMnd) {
		this.antallMnd = antallMnd;
	}

	/**
	 * @return id
	 */
	public Integer getFornyelseTypeId() {
		return fornyelseTypeId;
	}

	/**
	 * @param fornyelseTypeId
	 */
	public void setFornyelseTypeId(Integer fornyelseTypeId) {
		this.fornyelseTypeId = fornyelseTypeId;
	}

	/**
	 * @return kode
	 */
	public String getFornyelseTypeKode() {
		return fornyelseTypeKode;
	}

	/**
	 * @param fornyelseTypeKode
	 */
	public void setFornyelseTypeKode(String fornyelseTypeKode) {
		this.fornyelseTypeKode = fornyelseTypeKode;
	}

	/**
	 * @return tekst
	 */
	public String getFornyelseTypeTxt() {
		return fornyelseTypeTxt;
	}

	/**
	 * @param fornyelseTypeTxt
	 */
	public void setFornyelseTypeTxt(String fornyelseTypeTxt) {
		this.fornyelseTypeTxt = fornyelseTypeTxt;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof FornyelseType))
			return false;
		FornyelseType castOther = (FornyelseType) other;
		return new EqualsBuilder().append(fornyelseTypeKode,
				castOther.fornyelseTypeKode).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(fornyelseTypeKode).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return fornyelseTypeTxt;
	}

}
