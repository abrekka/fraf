package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Klasse for tabell AVREGNING_FREKVENS_TYPE
 * 
 * @author abr99
 * 
 */
public class AvregningFrekvensType extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer avregningFrekvensTypeId;

	/**
	 * 
	 */
	private String avregningFrekvensTypeKode;

	/**
	 * 
	 */
	private String avregningFrekvensTypeTxt;

	/**
	 * 
	 */
	private Integer antallMnd;

	/**
	 * 
	 */
	public AvregningFrekvensType() {
		super();
	}

	/**
	 * @param avregningFrekvensTypeId
	 * @param avregningFrekvensTypeKode
	 * @param avregningFrekvensTypeTxt
	 * @param antallMnd
	 */
	public AvregningFrekvensType(Integer avregningFrekvensTypeId,
			String avregningFrekvensTypeKode, String avregningFrekvensTypeTxt,
			Integer antallMnd) {
		super();
		this.avregningFrekvensTypeId = avregningFrekvensTypeId;
		this.avregningFrekvensTypeKode = avregningFrekvensTypeKode;
		this.avregningFrekvensTypeTxt = avregningFrekvensTypeTxt;
		this.antallMnd = antallMnd;
	}

	/**
	 * @return txt
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
	 * @return id
	 */
	public Integer getAvregningFrekvensTypeId() {
		return avregningFrekvensTypeId;
	}

	/**
	 * @param avregningFrekvensTypeId
	 */
	public void setAvregningFrekvensTypeId(Integer avregningFrekvensTypeId) {
		this.avregningFrekvensTypeId = avregningFrekvensTypeId;
	}

	/**
	 * @return kode
	 */
	public String getAvregningFrekvensTypeKode() {
		return avregningFrekvensTypeKode;
	}

	/**
	 * @param avregningFrekvensTypeKode
	 */
	public void setAvregningFrekvensTypeKode(String avregningFrekvensTypeKode) {
		this.avregningFrekvensTypeKode = avregningFrekvensTypeKode;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AvregningFrekvensType)) {
			return false;
		}
		AvregningFrekvensType rhs = (AvregningFrekvensType) object;
		return new EqualsBuilder().append(this.avregningFrekvensTypeKode,
				rhs.avregningFrekvensTypeKode).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(1069334845, -1782680775).append(
				this.avregningFrekvensTypeKode).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return avregningFrekvensTypeTxt;
	}

	/**
	 * @return antall måneder
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
}
