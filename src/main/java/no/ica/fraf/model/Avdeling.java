package no.ica.fraf.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Tabell AVDELING
 * 
 * @author abr99
 * 
 */
public class Avdeling extends BaseObject {
	/**
	 * True dersom avdeling er modifisert, brukes av GUI for å vite når det skal
	 * spøøres om avdeling skal lagres
	 */
	private boolean modified = false;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer avdelingId;

	/**
	 * 
	 */
	private Integer avdnr;

	/**
	 * 
	 */
	private Date opprettetDato;

	/**
	 * 
	 */
	private Date endretDato;

	/**
	 * 
	 */
	private ApplUser applUser;

	/**
	 * 
	 */
	private String kommentar;

	/**
	 * 
	 */
	private BokfSelskap bokfSelskap;

	/**
	 * 
	 */
	private Set<AvdelingKontrakt> avdelingKontrakts;

	/**
	 * 
	 */
	private Set<Faktura> fakturas;

	/**
	 * 
	 */
	private Set<AvdelingBetingelse> avdelingBetingelses;

	/**
	 * 
	 */
	private String kid;

	/**
	 * 
	 */
	private Set<Eierforhold> eierforholds;

	/**
	 * 
	 */
	private Set<Adendum> adendums;

	/**
	 * 
	 */
	private Set<AnnenKontrakt> annenKontrakts;

	/**
	 * 
	 */
	private Set<Sikkerhet> sikkerhets;

	/**
	 * 
	 */
	private Set<AvdelingLogg> avdelingLoggs;

	/**
	 * 
	 */
	private Set<AvdelingOmsetning> avdelingOmsetnings;

	/**
	 * 
	 */
	private Set<SpeiletBetingelse> speiletBetingelses;

	/**
	 * 
	 */
	private Set<AvdelingMangel> avdelingMangels;

	/**
	 * 
	 */
	//private Rik2AvdV rik2AvdV;
	private Department department;

	/**
	 * 
	 */
	private String franchisetaker;

	/**
	 * 
	 */
	private Integer pib;

	/**
	 * 
	 */
	private Set<Garanti> garantier;

	/**
	 * 
	 */
	private String archiveInfo;

	/**
	 * Konstruktør
	 * 
	 * @param avdelingId
	 * @param avdnr
	 * @param opprettetDato
	 * @param endretDato
	 * @param applUser
	 * @param kommentar
	 * @param bokfSelskap
	 * @param avdelingKontrakts
	 * @param fakturas
	 * @param avdelingBetingelses
	 * @param kid
	 * @param eierforholds
	 * @param adendums
	 * @param annenKontrakts
	 * @param sikkerhets
	 * @param avdelingLoggs
	 * @param avdelingOmsetnings
	 * @param speiletBetingelse
	 * @param avdelingMangels
	 * @param rik2AvdV
	 * @param franchisetaker
	 * @param pib
	 * @param garantier
	 * @param archiveInfo
	 */
	public Avdeling(Integer avdelingId, Integer avdnr, Date opprettetDato,
			Date endretDato, ApplUser applUser, String kommentar,
			BokfSelskap bokfSelskap, Set<AvdelingKontrakt> avdelingKontrakts,
			Set<Faktura> fakturas, Set<AvdelingBetingelse> avdelingBetingelses,
			String kid, Set<Eierforhold> eierforholds, Set<Adendum> adendums,
			Set<AnnenKontrakt> annenKontrakts, Set<Sikkerhet> sikkerhets,
			Set<AvdelingLogg> avdelingLoggs,
			Set<AvdelingOmsetning> avdelingOmsetnings,
			Set<SpeiletBetingelse> speiletBetingelse,
			Set<AvdelingMangel> avdelingMangels, 
			//Rik2AvdV rik2AvdV,
			Department aDepartment,
			String franchisetaker, Integer pib, Set<Garanti> garantier,
			String archiveInfo) {
		this.avdelingId = avdelingId;
		this.avdnr = avdnr;
		this.opprettetDato = opprettetDato;
		this.endretDato = endretDato;
		this.applUser = applUser;
		this.kommentar = kommentar;
		this.bokfSelskap = bokfSelskap;
		this.avdelingKontrakts = avdelingKontrakts;
		this.fakturas = fakturas;
		this.avdelingBetingelses = avdelingBetingelses;
		this.kid = kid;
		this.eierforholds = eierforholds;
		this.adendums = adendums;
		this.annenKontrakts = annenKontrakts;
		this.sikkerhets = sikkerhets;
		this.avdelingLoggs = avdelingLoggs;
		this.avdelingOmsetnings = avdelingOmsetnings;
		this.speiletBetingelses = speiletBetingelse;
		this.avdelingMangels = avdelingMangels;
		//this.rik2AvdV = rik2AvdV;
		this.department=aDepartment;
		this.franchisetaker = franchisetaker;
		this.pib = pib;
		this.garantier = garantier;
		this.archiveInfo = archiveInfo;
	}

	/**
	 * Kosntruktør
	 */
	public Avdeling() {
	}

	/**
	 * @return Returns the avdelingMangels.
	 */
	public Set<AvdelingMangel> getAvdelingMangels() {
		return avdelingMangels;
	}

	/**
	 * @param avdelingMangels
	 *            The avdelingMangels to set.
	 */
	public void setAvdelingMangels(Set<AvdelingMangel> avdelingMangels) {
		this.avdelingMangels = avdelingMangels;
	}

	/**
	 * @return id
	 */
	public Integer getAvdelingId() {
		return this.avdelingId;
	}

	/**
	 * @param avdelingId
	 */
	public void setAvdelingId(Integer avdelingId) {
		this.avdelingId = avdelingId;
	}

	/**
	 * @return avdnr
	 */
	public Integer getAvdnr() {
		return this.avdnr;
	}

	/**
	 * @param avdnr
	 */
	public void setAvdnr(Integer avdnr) {
		this.avdnr = avdnr;
	}

	/**
	 * @return opprettet dato
	 */
	public Date getOpprettetDato() {
		return this.opprettetDato;
	}

	/**
	 * @param opprettetDato
	 */
	public void setOpprettetDato(Date opprettetDato) {
		this.opprettetDato = opprettetDato;
	}

	/**
	 * @return endret dato
	 */
	public Date getEndretDato() {
		return this.endretDato;
	}

	/**
	 * @param endretDato
	 */
	public void setEndretDato(Date endretDato) {
		this.endretDato = endretDato;
	}

	/**
	 * @return kommentar
	 */
	public String getKommentar() {
		return this.kommentar;
	}

	/**
	 * @param kommentar
	 */
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	/**
	 * @return kontrakter
	 */
	public Set<AvdelingKontrakt> getAvdelingKontrakts() {
		return this.avdelingKontrakts;
	}

	/**
	 * @param avdelingKontrakts
	 */
	public void setAvdelingKontrakts(Set<AvdelingKontrakt> avdelingKontrakts) {
		this.avdelingKontrakts = avdelingKontrakts;
	}

	/**
	 * @return fakturaer
	 */
	public Set<Faktura> getFakturas() {
		return this.fakturas;
	}

	/**
	 * @param fakturas
	 */
	public void setFakturas(Set<Faktura> fakturas) {
		this.fakturas = fakturas;
	}

	/**
	 * @return betingelser
	 */
	public Set<AvdelingBetingelse> getAvdelingBetingelses() {
		return this.avdelingBetingelses;
	}

	/**
	 * @param avdelingBetingelses
	 */
	public void setAvdelingBetingelses(
			Set<AvdelingBetingelse> avdelingBetingelses) {
		this.avdelingBetingelses = avdelingBetingelses;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (avdnr != null) {
			return avdnr.toString();
		}
		return "";
	}

	/**
	 * @return bokføringsselskap
	 */
	public BokfSelskap getBokfSelskap() {
		return bokfSelskap;
	}

	/**
	 * @param bokfSelskap
	 */
	public void setBokfSelskap(BokfSelskap bokfSelskap) {
		this.bokfSelskap = bokfSelskap;
	}

	/**
	 * @param eierforhold
	 */
	public void addEierforhold(Eierforhold eierforhold) {
		if (this.eierforholds == null) {
			this.eierforholds = new HashSet<Eierforhold>();
		}
		this.eierforholds.add(eierforhold);
		eierforhold.setAvdeling(this);
	}

	/**
	 * @param eierforhold
	 */
	public void removeEierforhold(Eierforhold eierforhold) {
		if (this.eierforholds == null) {
			return;
		}
		this.eierforholds.remove(eierforhold);
	}

	/**
	 * @param adendum
	 */
	public void addAdendum(Adendum adendum) {
		if (this.adendums == null) {
			this.adendums = new HashSet<Adendum>();
		}
		this.adendums.add(adendum);
		adendum.setAvdeling(this);
	}

	/**
	 * @param adendum
	 */
	public void removeAdendum(Adendum adendum) {
		if (this.adendums == null) {
			return;
		}
		this.adendums.remove(adendum);
	}

	/**
	 * @return kid
	 */
	public String getKid() {
		return kid;
	}

	/**
	 * @param kid
	 */
	public void setKid(String kid) {
		this.kid = kid;
	}

	/**
	 * @return eierforhold
	 */
	public Set<Eierforhold> getEierforholds() {
		return eierforholds;
	}

	/**
	 * @param eierforholds
	 */
	public void setEierforholds(Set<Eierforhold> eierforholds) {
		this.eierforholds = eierforholds;
	}

	/**
	 * @return addendum
	 */
	public Set<Adendum> getAdendums() {
		return adendums;
	}

	/**
	 * @param adendums
	 */
	public void setAdendums(Set<Adendum> adendums) {
		this.adendums = adendums;
	}

	/**
	 * @return andre kontrakter
	 */
	public Set<AnnenKontrakt> getAnnenKontrakts() {
		return annenKontrakts;
	}

	/**
	 * @param annenKontrakts
	 */
	public void setAnnenKontrakts(Set<AnnenKontrakt> annenKontrakts) {
		this.annenKontrakts = annenKontrakts;
	}

	/**
	 * @return sikkerhet
	 */
	public Set<Sikkerhet> getSikkerhets() {
		return sikkerhets;
	}

	/**
	 * @param sikkerhets
	 */
	public void setSikkerhets(Set<Sikkerhet> sikkerhets) {
		this.sikkerhets = sikkerhets;
	}

	/**
	 * @param sikkerhet
	 */
	public void addSikkerhet(Sikkerhet sikkerhet) {
		if (this.sikkerhets == null) {
			this.sikkerhets = new HashSet<Sikkerhet>();
		}
		this.sikkerhets.add(sikkerhet);
		sikkerhet.setAvdeling(this);
	}

	/**
	 * @param sikkerhet
	 */
	public void removeSikkerhet(Sikkerhet sikkerhet) {
		if (this.sikkerhets == null) {
			return;
		}
		this.sikkerhets.remove(sikkerhet);
	}

	/**
	 * @param avdelingKontrakt
	 */
	public void addAvdelingKontrakt(AvdelingKontrakt avdelingKontrakt) {
		if (this.avdelingKontrakts == null) {
			this.avdelingKontrakts = new HashSet<AvdelingKontrakt>();
		}
		this.avdelingKontrakts.add(avdelingKontrakt);
		avdelingKontrakt.setAvdeling(this);
	}

	/**
	 * @param avdelingKontrakt
	 */
	public void removeAvdelingKontrakt(AvdelingKontrakt avdelingKontrakt) {
		if (this.avdelingKontrakts == null) {
			return;
		}
		this.avdelingKontrakts.remove(avdelingKontrakt);
	}

	/**
	 * @param avdelingBetingelse
	 */
	public void addAvdelingBetingelse(AvdelingBetingelse avdelingBetingelse) {
		if (this.avdelingBetingelses == null) {
			this.avdelingBetingelses = new HashSet<AvdelingBetingelse>();
		}
		avdelingBetingelse.setAvdeling(this);
		this.avdelingBetingelses.add(avdelingBetingelse);
		
	}

	public void updateAvdelingBetingelseTilDato(
			AvdelingBetingelse avdelingBetingelse) {
		if (this.avdelingBetingelses != null) {
			for (AvdelingBetingelse betingelse : avdelingBetingelses) {
				if (betingelse.getAvdelingBetingelseId().equals(
						avdelingBetingelse.getAvdelingBetingelseId())) {
					betingelse.setTilDato(avdelingBetingelse.getTilDato());
					betingelse.setSlettet(avdelingBetingelse.getSlettet());
				}
			}

		}
	}

	/**
	 * @param avdelingBetingelse
	 */
	public void removeAvdelingBetingelse(AvdelingBetingelse avdelingBetingelse) {
		if (this.avdelingBetingelses == null) {
			return;
		}
		this.avdelingBetingelses.remove(avdelingBetingelse);
	}

	/**
	 * @return logg
	 */
	public Set<AvdelingLogg> getAvdelingLoggs() {
		return avdelingLoggs;
	}

	/**
	 * @param avdelingLoggs
	 */
	public void setAvdelingLoggs(Set<AvdelingLogg> avdelingLoggs) {
		this.avdelingLoggs = avdelingLoggs;
	}

	/**
	 * @param avdelingLogg
	 */
	public void addAvdelingLogg(AvdelingLogg avdelingLogg) {
		if (this.avdelingLoggs == null) {
			this.avdelingLoggs = new HashSet<AvdelingLogg>();
		}
		this.avdelingLoggs.add(avdelingLogg);
		avdelingLogg.setAvdeling(this);
	}

	/**
	 * @return bruker
	 */
	public ApplUser getApplUser() {
		return applUser;
	}

	/**
	 * @param applUser
	 */
	public void setApplUser(ApplUser applUser) {
		this.applUser = applUser;
	}

	/**
	 * @param annenKontrakt
	 */
	public void addAnnenKontrakt(AnnenKontrakt annenKontrakt) {
		if (this.annenKontrakts == null) {
			this.annenKontrakts = new HashSet<AnnenKontrakt>();
		}
		this.annenKontrakts.add(annenKontrakt);
		annenKontrakt.setAvdeling(this);
	}

	/**
	 * @param annenKontrakt
	 */
	public void removeAnnenKontrakt(AnnenKontrakt annenKontrakt) {
		if (this.annenKontrakts == null) {
			return;
		}
		this.annenKontrakts.remove(annenKontrakt);
	}

	/**
	 * @return omsetning
	 */
	public Set getAvdelingOmsetnings() {
		return avdelingOmsetnings;
	}

	/**
	 * @param avdelingOmsetnings
	 */
	public void setAvdelingOmsetnings(Set<AvdelingOmsetning> avdelingOmsetnings) {
		this.avdelingOmsetnings = avdelingOmsetnings;
	}

	/**
	 * @param avdelingOmsetning
	 */
	public void removeAvdelingOmsetning(AvdelingOmsetning avdelingOmsetning) {
		if (this.avdelingOmsetnings == null) {
			return;
		}
		this.avdelingOmsetnings.remove(avdelingOmsetning);
	}

	/**
	 * @param avdelingOmsetning
	 */
	public void addAvdelingOmsetning(AvdelingOmsetning avdelingOmsetning) {
		if (this.avdelingOmsetnings == null) {
			this.avdelingOmsetnings = new HashSet<AvdelingOmsetning>();
		}
		this.avdelingOmsetnings.add(avdelingOmsetning);
		avdelingOmsetning.setAvdeling(this);
	}

	/**
	 * @return speilet betingelse
	 */
	public Set<SpeiletBetingelse> getSpeiletBetingelses() {
		return speiletBetingelses;
	}

	/**
	 * @param speiletBetingelses
	 */
	public void setSpeiletBetingelses(Set<SpeiletBetingelse> speiletBetingelses) {
		this.speiletBetingelses = speiletBetingelses;
	}

	/**
	 * @param speiletBetingelse
	 */
	public void addSpeiletBetingelse(SpeiletBetingelse speiletBetingelse) {
		if (this.speiletBetingelses == null) {
			this.speiletBetingelses = new HashSet<SpeiletBetingelse>();
		}
		this.speiletBetingelses.add(speiletBetingelse);
		speiletBetingelse.setAvdeling(this);
	}

	/**
	 * @param speiletBetingelse
	 */
	public void removeSpeiletBetingelse(SpeiletBetingelse speiletBetingelse) {
		if (this.speiletBetingelses == null) {
			return;
		}
		this.speiletBetingelses.remove(speiletBetingelse);
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Avdeling))
			return false;
		Avdeling castOther = (Avdeling) other;
		return new EqualsBuilder().append(avdelingId, castOther.avdelingId)
				.append(avdnr, castOther.avdnr).append(opprettetDato,
						castOther.opprettetDato).append(kommentar,
						castOther.kommentar).append(bokfSelskap,
						castOther.bokfSelskap).append(kid, castOther.kid)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdelingId).append(avdnr).append(
				opprettetDato).append(kommentar).append(bokfSelskap)
				.append(kid).toHashCode();
	}

	/**
	 * @param avdelingMangel
	 */
	public void addAvdelingMangel(AvdelingMangel avdelingMangel) {
		if (this.avdelingMangels == null) {
			this.avdelingMangels = new HashSet<AvdelingMangel>();
		}
		avdelingMangel.setAvdeling(this);
		this.avdelingMangels.add(avdelingMangel);
	}

	/**
	 * @param avdelingMangel
	 */
	public void removeAvdelingMangel(AvdelingMangel avdelingMangel) {
		if (this.avdelingMangels == null) {
			return;
		}
		this.avdelingMangels.remove(avdelingMangel);
		avdelingMangel.setAvdeling(null);
	}

	/**
	 * @return Returns the rik2AvdV.
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param rik2AvdV
	 *            The rik2AvdV to set.
	 */
	public void setDepartment(Department aDepartment) {
		this.department = aDepartment;
	}

	/**
	 * @return Returns the franchisetaker.
	 */
	public String getFranchisetaker() {
		return franchisetaker;
	}

	/**
	 * @param franchisetaker
	 *            The franchisetaker to set.
	 */
	public void setFranchisetaker(String franchisetaker) {
		this.franchisetaker = franchisetaker;
	}

	/**
	 * @return Returns the pib.
	 */
	public Integer getPib() {
		return pib;
	}

	/**
	 * @param pib
	 *            The pib to set.
	 */
	public void setPib(Integer pib) {
		this.pib = pib;
	}

	/**
	 * @return Returns the modified.
	 */
	public boolean isModified() {
		return modified;
	}

	/**
	 * @param modified
	 *            The modified to set.
	 */
	public void setModified(boolean modified) {
		this.modified = modified;
	}

	/**
	 * @param garanti
	 */
	public void addGaranti(Garanti garanti) {
		if (this.garantier == null) {
			this.garantier = new HashSet<Garanti>();
		}
		this.garantier.add(garanti);
		garanti.setAvdeling(this);
	}

	/**
	 * @param garanti
	 */
	public void removeGaranti(Garanti garanti) {
		if (this.garantier == null) {
			return;
		}
		this.garantier.remove(garanti);
	}

	/**
	 * @return Returns the garantier.
	 */
	public Set<Garanti> getGarantier() {
		return garantier;
	}

	/**
	 * @param garantier
	 *            The garantier to set.
	 */
	public void setGarantier(Set<Garanti> garantier) {
		this.garantier = garantier;
	}

	/**
	 * @return arkivinfo
	 */
	public String getArchiveInfo() {
		return archiveInfo;
	}

	/**
	 * @param archiveInfo
	 */
	public void setArchiveInfo(String archiveInfo) {
		this.archiveInfo = archiveInfo;
	}

	public List<AvdelingBetingelse> getGruppeBetingelser(BetingelseGruppe gruppe) {
		List<AvdelingBetingelse> betingelser = new ArrayList<AvdelingBetingelse>();
		Date currentDate = Calendar.getInstance().getTime();
		if (avdelingBetingelses != null) {
			for (AvdelingBetingelse betingelse : avdelingBetingelses) {
				if (betingelse.getBetingelseType().getBetingelseGruppe()
						.equals(gruppe)
						&& !betingelse.isDeleted()
						&& currentDate.after(betingelse.getFraDato())
						&& currentDate.before(betingelse.getTilDato())) {
					betingelser.add(betingelse);
				}
			}
		}
		return betingelser;
	}

	public AvdelingKontrakt getLastKontrakt() {
		AvdelingKontrakt lastKontrakt = null;
		if (avdelingKontrakts != null) {
			for (AvdelingKontrakt kontrakt : avdelingKontrakts) {
				if (lastKontrakt == null
						|| kontrakt.getTilDato().after(
								lastKontrakt.getTilDato())) {
					lastKontrakt = kontrakt;
				}
			}
		}
		return lastKontrakt;
	}
}
