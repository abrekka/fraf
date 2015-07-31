package no.ica.fraf.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for tabell AVDELING_STATUS_TYPE
 * 
 * @author abr99
 * 
 */
public class AvdelingStatusType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private BigDecimal typeId;

	/** persistent field */
	private String typeTxt;

	/**
	 * @param typeId
	 * @param typeTxt
	 */
	public AvdelingStatusType(BigDecimal typeId, String typeTxt) {
		this.typeId = typeId;
		this.typeTxt = typeTxt;
	}

	/** default constructor */
	public AvdelingStatusType() {
	}

	/**
	 * @return id
	 */
	public BigDecimal getTypeId() {
		return this.typeId;
	}

	/**
	 * @param typeId
	 */
	public void setTypeId(BigDecimal typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return tekst
	 */
	public String getTypeTxt() {
		return this.typeTxt;
	}

	/**
	 * @param typeTxt
	 */
	public void setTypeTxt(String typeTxt) {
		this.typeTxt = typeTxt;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("typeId", getTypeId())
				.toString();
	}

}
