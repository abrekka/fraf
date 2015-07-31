package no.ica.fraf.model;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klassse for view NY_AVDELING_V
 * 
 * @author abr99
 * 
 */
public class NyAvdelingV extends BaseObject implements NyAvdelingVInterface {
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
	private String navn;

	/**
	 * 
	 */
	private Date dtStart;
	private String avtaletype;
	private String datasetConcorde;

	/**
	 * 
	 */
	public NyAvdelingV() {
	}

	/**
	 * @param avdnr
	 * @param start
	 * @param navn
	 */
	public NyAvdelingV(Integer avdnr, Date start, String navn,String avtaletype,String datasetConcorde) {
		this.avdnr = avdnr;
		dtStart = start;
		this.navn = navn;
		this.avtaletype=avtaletype;
		this.datasetConcorde=datasetConcorde;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.NyAvdelingVInterface#getAvdnr()
	 */
	public Integer getAvdnr() {
		return avdnr;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.NyAvdelingVInterface#setAvdnr(java.lang.Integer)
	 */
	public void setAvdnr(Integer avdnr) {
		this.avdnr = avdnr;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.NyAvdelingVInterface#getDtStart()
	 */
	public Date getDtStart() {
		return dtStart;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.NyAvdelingVInterface#setDtStart(java.util.Date)
	 */
	public void setDtStart(Date dtStart) {
		this.dtStart = dtStart;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.NyAvdelingVInterface#getNavn()
	 */
	public String getNavn() {
		return navn;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.NyAvdelingVInterface#setNavn(java.lang.String)
	 */
	public void setNavn(String navn) {
		this.navn = navn;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof NyAvdelingV))
			return false;
		NyAvdelingV castOther = (NyAvdelingV) other;
		return new EqualsBuilder().append(avdnr, castOther.avdnr).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdnr).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"avdnr", avdnr).toString();
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.NyAvdelingVInterface#getAvtaletype()
	 */
	public String getAvtaletype() {
		return avtaletype;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.NyAvdelingVInterface#setAvtaletype(java.lang.String)
	 */
	public void setAvtaletype(String avtaletype) {
		this.avtaletype = avtaletype;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.NyAvdelingVInterface#getDatasetConcorde()
	 */
	public String getDatasetConcorde() {
		return datasetConcorde;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.NyAvdelingVInterface#setDatasetConcorde(java.lang.String)
	 */
	public void setDatasetConcorde(String datasetConcorde) {
		this.datasetConcorde = datasetConcorde;
	}
}
