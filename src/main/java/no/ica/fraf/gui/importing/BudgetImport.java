package no.ica.fraf.gui.importing;

import java.math.BigDecimal;

/**
 * Klasse som brukes til å representere en linje i budsjettfil
 * 
 * @author abr99
 * 
 */
public class BudgetImport{
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
	private BigDecimal budsjett;

	/**
	 * 
	 */
	private String feilmelding;

	/**
	 * Kosntruktør
	 */
	public BudgetImport() {
	}

	/**
	 * Konstruktør
	 * 
	 * @param avdnr
	 * @param budsjett
	 * @param feilmelding
	 * @param navn
	 */
	public BudgetImport(Integer avdnr, BigDecimal budsjett, String feilmelding,
			String navn) {
		this.avdnr = avdnr;
		this.budsjett = budsjett;
		this.feilmelding = feilmelding;
		this.navn = navn;
	}

	/**
	 * @return avdnr
	 */
	public Integer getAvdnr() {
		return avdnr;
	}

	/**
	 * @param avdnr
	 */
	public void setAvdnr(Integer avdnr) {
		this.avdnr = avdnr;
	}

	/**
	 * @return budsjett
	 */
	public BigDecimal getBudsjett() {
		return budsjett;
	}

	/**
	 * @param budsjett
	 */
	public void setBudsjett(BigDecimal budsjett) {
		this.budsjett = budsjett;
	}

	/**
	 * @return feilmelding
	 */
	public String getFeilmelding() {
		return feilmelding;
	}

	/**
	 * @param feilmelding
	 */
	public void setFeilmelding(String feilmelding) {
		this.feilmelding = feilmelding;
	}

	/**
	 * @return navn
	 */
	public String getNavn() {
		return navn;
	}

	/**
	 * @param navn
	 */
	public void setNavn(String navn) {
		this.navn = navn;
	}


}
