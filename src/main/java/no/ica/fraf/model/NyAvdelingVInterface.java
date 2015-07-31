package no.ica.fraf.model;

import java.util.Date;

public interface NyAvdelingVInterface {

	/**
	 * @return avdnr
	 */
	public abstract Integer getAvdnr();

	/**
	 * @param avdnr
	 */
	public abstract void setAvdnr(Integer avdnr);

	/**
	 * @return start
	 */
	public abstract Date getDtStart();

	/**
	 * @param dtStart
	 */
	public abstract void setDtStart(Date dtStart);

	/**
	 * @return navn
	 */
	public abstract String getNavn();

	/**
	 * @param navn
	 */
	public abstract void setNavn(String navn);

	public abstract String getAvtaletype();

	public abstract void setAvtaletype(String avtaletype);

	public abstract String getDatasetConcorde();

	public abstract void setDatasetConcorde(String datasetConcorde);

}