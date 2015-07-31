package no.ica.fraf.model;

import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Klasse for tabell BETINGELSE_GRUPPE
 * 
 * @author abr99
 * 
 */
public class BetingelseGruppe extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer betingelseGruppeId;

	/**
	 * 
	 */
	private String betingelseGruppeNavn;

	/**
	 * 
	 */
	private Integer fakturerMedFranchise=0;

	/**
	 * 
	 */
	private Set betingelseTypes;

	/**
	 * 
	 */
	public BetingelseGruppe() {
	}

	/**
	 * @param betingelseGruppeId
	 * @param betingelseGruppeNavn
	 * @param fakturerMedFranchise
	 * @param betingelseTypes
	 */
	public BetingelseGruppe(Integer betingelseGruppeId,
			String betingelseGruppeNavn, Integer fakturerMedFranchise,
			Set betingelseTypes) {
		this.betingelseGruppeId = betingelseGruppeId;
		this.betingelseGruppeNavn = betingelseGruppeNavn;
		this.fakturerMedFranchise = fakturerMedFranchise;
		this.betingelseTypes = betingelseTypes;
	}

	/**
	 * @return id
	 */
	public Integer getBetingelseGruppeId() {
		return betingelseGruppeId;
	}

	/**
	 * @param betingelseGruppeId
	 */
	public void setBetingelseGruppeId(Integer betingelseGruppeId) {
		this.betingelseGruppeId = betingelseGruppeId;
	}

	/**
	 * @return gruppenavn
	 */
	public String getBetingelseGruppeNavn() {
		return betingelseGruppeNavn;
	}

	/**
	 * @param betingelseGruppeNavn
	 */
	public void setBetingelseGruppeNavn(String betingelseGruppeNavn) {
		this.betingelseGruppeNavn = betingelseGruppeNavn;
	}

	/**
	 * @return om gruppe skal faktureres sammen med franchise
	 */
	public Integer getFakturerMedFranchise() {
		return fakturerMedFranchise;
	}

	/**
	 * @param fakturerMedFranchise
	 */
	public void setFakturerMedFranchise(Integer fakturerMedFranchise) {
		this.fakturerMedFranchise = fakturerMedFranchise;
	}

	/**
	 * @return betingelsetyper
	 */
	public Set getBetingelseTypes() {
		return betingelseTypes;
	}

	/**
	 * @param betingelseTypes
	 */
	public void setBetingelseTypes(Set betingelseTypes) {
		this.betingelseTypes = betingelseTypes;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof BetingelseGruppe))
			return false;
		BetingelseGruppe castOther = (BetingelseGruppe) other;
		return new EqualsBuilder().append(betingelseGruppeNavn,
				castOther.betingelseGruppeNavn).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(betingelseGruppeNavn).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return betingelseGruppeNavn;
	}

}
