package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Sikkerhet
 * 
 * @author abr99
 * 
 */
/**
 * @author abr99
 *
 */
/**
 * @author abr99
 * 
 */
public class Sikkerhet extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Sikkerhetid primærmøkkel
	 */
	private Integer sikkerhetId;

	/**
	 * Verdi på sikkerhet
	 */
	private String sikkerhetVerdi;

	/**
	 * Type sikkerhet
	 */
	private SikkerhetType sikkerhetType;

	/**
	 * Avdeling som har sikkerhet
	 */
	private Avdeling avdeling;

	/**
	 * 1 derom sikkerhet er tinglyst
	 */
	private Integer tinglyst;

	/**
	 * 
	 */
	private String kommentar;

	/**
	 * Konstruktør
	 * 
	 */
	public Sikkerhet() {
	}

	/**
	 * Konstruktør
	 * 
	 * @param sikkerhetId
	 * @param sikkerhetVerdi
	 * @param sikkerhetType
	 * @param avdeling
	 * @param tinglyst
	 * @param kommentar
	 */
	public Sikkerhet(Integer sikkerhetId, String sikkerhetVerdi,
			SikkerhetType sikkerhetType, Avdeling avdeling, Integer tinglyst,
			String kommentar) {
		this.sikkerhetId = sikkerhetId;
		this.sikkerhetVerdi = sikkerhetVerdi;
		this.sikkerhetType = sikkerhetType;
		this.avdeling = avdeling;
		this.tinglyst = tinglyst;
		this.kommentar = kommentar;
	}

	/**
	 * Henter avdeling
	 * 
	 * @return avdeling
	 */
	public Avdeling getAvdeling() {
		return avdeling;
	}

	/**
	 * Setter avdeling
	 * 
	 * @param avdeling
	 *            avdeling som har sikkerhet
	 */
	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}

	/**
	 * Henter id
	 * 
	 * @return id
	 */
	public Integer getSikkerhetId() {
		return sikkerhetId;
	}

	/**
	 * Setter id
	 * 
	 * @param sikkerhetId
	 *            id
	 */
	public void setSikkerhetId(Integer sikkerhetId) {
		this.sikkerhetId = sikkerhetId;
	}

	/**
	 * Henter type
	 * 
	 * @return type
	 */
	public SikkerhetType getSikkerhetType() {
		return sikkerhetType;
	}

	/**
	 * Setter type
	 * 
	 * @param sikkerhetType
	 *            type
	 */
	public void setSikkerhetType(SikkerhetType sikkerhetType) {
		this.sikkerhetType = sikkerhetType;
	}

	/**
	 * Henter verdi
	 * 
	 * @return verdi
	 */
	public String getSikkerhetVerdi() {
		return sikkerhetVerdi;
	}

	/**
	 * Setter verdi
	 * 
	 * @param sikkerhetVerdi
	 *            verdi
	 */
	public void setSikkerhetVerdi(String sikkerhetVerdi) {
		this.sikkerhetVerdi = sikkerhetVerdi;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Sikkerhet)) {
			return false;
		}
		Sikkerhet rhs = (Sikkerhet) object;
		return new EqualsBuilder()
				.append(this.sikkerhetType, rhs.sikkerhetType).append(
						this.sikkerhetId, rhs.sikkerhetId).append(
						this.avdeling, rhs.avdeling).append(
						this.sikkerhetVerdi, rhs.sikkerhetVerdi).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-2015955503, -1908239259).append(
				this.sikkerhetType).append(this.sikkerhetId).append(
				this.avdeling).append(this.sikkerhetVerdi).toHashCode();
	}

	/**
	 * Henter objektid
	 * 
	 * @return id
	 */
	@Override
	public Object getObjectId() {
		return sikkerhetId;
	}

	/**
	 * @see no.ica.fraf.model.BaseObject#getObjectName()
	 */
	@Override
	public String getObjectName() {
		return "Sikkerhet";
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"sikkerhetVerdi", sikkerhetVerdi).append("sikkerhetType",
				sikkerhetType).toString();
	}

	/**
	 * Henter ut om sikkerhet er tinglyst
	 * 
	 * @return 1 dersom tinglyst
	 */
	public Integer getTinglyst() {
		return tinglyst;
	}

	/**
	 * Setter om sikkerhet er tinglyst
	 * 
	 * @param tinglyst
	 *            1 dersom tinglyst
	 */
	public void setTinglyst(Integer tinglyst) {
		this.tinglyst = tinglyst;
	}

	/**
	 * @return kommentar
	 */
	public String getKommentar() {
		return kommentar;
	}

	/**
	 * @param kommentar
	 */
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

}
