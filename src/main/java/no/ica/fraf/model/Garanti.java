package no.ica.fraf.model;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for tabell Garanti
 * 
 * @author abr99
 * 
 */
public class Garanti extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer garantiId;

	/**
	 * 
	 */
	private String garantiTxt;

	/**
	 * 
	 */
	private BigDecimal garantiVerdi;

	/**
	 * 
	 */
	private Avdeling avdeling;

	/**
	 * 
	 */
	public Garanti() {
		super();
	}

	/**
	 * @param garantiId
	 * @param garantiTxt
	 * @param garantiVerdi
	 * @param avdeling
	 */
	public Garanti(Integer garantiId, String garantiTxt,
			BigDecimal garantiVerdi, Avdeling avdeling) {
		super();
		this.garantiId = garantiId;
		this.garantiTxt = garantiTxt;
		this.garantiVerdi = garantiVerdi;
		this.avdeling = avdeling;
	}

	/**
	 * @return Returns the avdeling.
	 */
	public Avdeling getAvdeling() {
		return avdeling;
	}

	/**
	 * @param avdeling
	 *            The avdeling to set.
	 */
	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}

	/**
	 * @return Returns the garantiId.
	 */
	public Integer getGarantiId() {
		return garantiId;
	}

	/**
	 * @param garantiId
	 *            The garantiId to set.
	 */
	public void setGarantiId(Integer garantiId) {
		this.garantiId = garantiId;
	}

	/**
	 * @return Returns the garantiTxt.
	 */
	public String getGarantiTxt() {
		return garantiTxt;
	}

	/**
	 * @param garantiTxt
	 *            The garantiTxt to set.
	 */
	public void setGarantiTxt(String garantiTxt) {
		this.garantiTxt = garantiTxt;
	}

	/**
	 * @return Returns the garantiVerdi.
	 */
	public BigDecimal getGarantiVerdi() {
		return garantiVerdi;
	}

	/**
	 * @param garantiVerdi
	 *            The garantiVerdi to set.
	 */
	public void setGarantiVerdi(BigDecimal garantiVerdi) {
		this.garantiVerdi = garantiVerdi;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"garantiTxt", garantiTxt).append("garantiVerdi", garantiVerdi)
				.append("avdeling", avdeling).toString();
		
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Garanti))
			return false;
		Garanti castOther = (Garanti) other;
		return new EqualsBuilder().append(garantiTxt, castOther.garantiTxt)
				.append(garantiVerdi, castOther.garantiVerdi).append(avdeling,
						castOther.avdeling).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(garantiTxt).append(garantiVerdi)
				.append(avdeling).toHashCode();
	}

	/**
	 * @see no.ica.fraf.model.BaseObject#getObjectId()
	 */
	@Override
	public Object getObjectId() {
		return garantiId;
	}

	/**
	 * @see no.ica.fraf.model.BaseObject#getObjectName()
	 */
	@Override
	public String getObjectName() {
		return "Garanti";
	}
}
