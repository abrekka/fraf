package no.ica.fraf.model;

import no.ica.fraf.common.BatchStatusInterface;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for tabell BUNT_STATUS
 * 
 * @author abr99
 * 
 */
public class BuntStatus extends BaseObject implements BatchStatusInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer buntStatusId;

	/**
	 * 
	 */
	private String kode;

	/**
	 * 
	 */
	private String beskrivelse;

	/**
	 * 
	 */
	public BuntStatus() {
	}

	/**
	 * @param beskrivelse
	 * @param id
	 * @param kode
	 */
	public BuntStatus(String beskrivelse, Integer id, String kode) {
		this.beskrivelse = beskrivelse;
		buntStatusId = id;
		this.kode = kode;
	}

	/**
	 * @return beskrivelse
	 */
	public String getBeskrivelse() {
		return beskrivelse;
	}

	/**
	 * @param beskrivelse
	 */
	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	/**
	 * @return id
	 */
	public Integer getBuntStatusId() {
		return buntStatusId;
	}

	/**
	 * @param buntStatusId
	 */
	public void setBuntStatusId(Integer buntStatusId) {
		this.buntStatusId = buntStatusId;
	}

	/**
	 * @return kode
	 */
	public String getKode() {
		return kode;
	}

	/**
	 * @param kode
	 */
	public void setKode(String kode) {
		this.kode = kode;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof BuntStatus))
			return false;
		BuntStatus castOther = (BuntStatus) other;
		return new EqualsBuilder().append(kode, castOther.kode).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(kode).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"beskrivelse", beskrivelse).toString();
	}

	public Boolean isXmlGenerated() {
		if(kode.equalsIgnoreCase("XM")||kode.equalsIgnoreCase("XB")){
			return true;
		}
		return false;
	}

}
