package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import no.ica.fraf.common.Batchable;
import no.ica.fraf.xml.InvoiceInterface;
import no.ica.fraf.xml.InvoiceItemInterface;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Klasse for tabell FAKTURA
 * 
 * @author atb
 * 
 */
public class Faktura extends BaseObject implements InvoiceInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer fakturaId;

	/** nullable persistent field */
	private Integer avdnr;

	/** nullable persistent field */
	private Date fakturaDato;

	/** nullable persistent field */
	private Integer aar;

	/** nullable persistent field */
	private Integer fraPeriode;

	/** nullable persistent field */
	private Date forfallDato;

	/** nullable persistent field */
	private Integer tilPeriode;

	/** nullable persistent field */
	private String fakturaNr;

	/** nullable persistent field */
	private BigDecimal totalBelop;

	/** nullable persistent field */
	private Date opprettetDato;

	/** nullable persistent field */
	private String fakturaTittel;

	/** nullable persistent field */
	private BigDecimal grunnlagBelop;

	/** nullable persistent field */
	private String fakturaTekst;

	/** nullable persistent field */
	private String mottakerNavn;

	/** nullable persistent field */
	private String adresse1;

	/** nullable persistent field */
	private String adresse2;

	/** nullable persistent field */
	private String postnr;

	/** nullable persistent field */
	private String poststed;

	/** nullable persistent field */
	private BigDecimal belop;

	/** nullable persistent field */
	private BigDecimal mvaBelop;

	/** nullable persistent field */
	private Integer reversert;
	

	/**
	 * 
	 */
	private Integer slettet;

	/** persistent field */
	private no.ica.fraf.model.Avdeling avdeling;

	/** persistent field */
	private Bunt bunt;

	/**
	 * 
	 */
	private Integer reversertFakturaId;

	/**
	 * 
	 */
	private BetingelseGruppe betingelseGruppe;

	/** persistent field */
	private Set<FakturaLinje> fakturaLinjes;

	/**
	 * 
	 */
	private Set<FakturaTekst> fakturaTeksts;

	/**
	 * 
	 */
	private Set<RegnskapKladd> regnskapKladds;

	/**
	 * 
	 */
	private BokfSelskap bokfSelskap;

	private String kid;
	private Date xmlGenerertDato;
	private Integer brukSomGrunnlag;
	
	//felter for å lagre hele fakturaen
	private String avsenderNavn;
	private String avsenderAdresse1;
	private String avsenderAdresse2;
	private String avsenderAdresse3;
	private String avtalenr;
	private String juridiskNavn;
	private String avsenderOrgNr;
	private String avsenderTelefon;
	private String avsenderTelefax;
	private String avsenderKontonr;
	private String fakturertAv;
	private String icaKontoTekst;
	private Integer harSatsLinje;
	private String overtrekksrente;

	/**
	 * @param fakturaId
	 * @param avdnr
	 * @param fakturaDato
	 * @param aar
	 * @param fraPeriode
	 * @param forfallDato
	 * @param tilPeriode
	 * @param fakturaNr
	 * @param totalBelop
	 * @param opprettetDato
	 * @param fakturaTittel
	 * @param grunnlagBelop
	 * @param fakturaTekst
	 * @param mottakerNavn
	 * @param adresse1
	 * @param adresse2
	 * @param postnr
	 * @param poststed
	 * @param belop
	 * @param mvaBelop
	 * @param reversert
	 * @param avdeling
	 * @param bunt
	 * @param fakturaLinjes
	 * @param fakturaTeksts
	 * @param regnskapKladds
	 * @param betingelseGruppe
	 * @param slettet
	 * @param bokfSelskap
	 * @param reversertFakturaId
	 */
	public Faktura(Integer fakturaId, Integer avdnr, Date fakturaDato,
			Integer aar, Integer fraPeriode, Date forfallDato,
			Integer tilPeriode, String fakturaNr, BigDecimal totalBelop,
			Date opprettetDato, String fakturaTittel, BigDecimal grunnlagBelop,
			String fakturaTekst, String mottakerNavn, String adresse1,
			String adresse2, String postnr, String poststed, BigDecimal belop,
			BigDecimal mvaBelop, Integer reversert,
			no.ica.fraf.model.Avdeling avdeling, Bunt bunt,
			Set<FakturaLinje> fakturaLinjes, Set<FakturaTekst> fakturaTeksts,
			Set<RegnskapKladd> regnskapKladds,
			BetingelseGruppe betingelseGruppe, Integer slettet,
			BokfSelskap bokfSelskap, Integer reversertFakturaId, String kid,Date xmlGenerertDato,Integer brukSomGrunnlag,
			String avsenderNavn,String avsenderAdresse1,String avsenderAdresse2,String avsenderAdresse3,String avtalenr,String juridiskNavn,String avsenderOrgNr,String avsenderTelefon,String avsenderTelefax,String avsenderKontonr,String fakturertAv,String icaKontoTekst,Integer harSatsLinje,String overtrekksrente) {
		this.fakturaId = fakturaId;
		this.avdnr = avdnr;
		this.fakturaDato = fakturaDato;
		this.aar = aar;
		this.fraPeriode = fraPeriode;
		this.forfallDato = forfallDato;
		this.tilPeriode = tilPeriode;
		this.fakturaNr = fakturaNr;
		this.totalBelop = totalBelop;
		this.opprettetDato = opprettetDato;
		this.fakturaTittel = fakturaTittel;
		this.grunnlagBelop = grunnlagBelop;
		this.fakturaTekst = fakturaTekst;
		this.mottakerNavn = mottakerNavn;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.postnr = postnr;
		this.poststed = poststed;
		this.belop = belop;
		this.mvaBelop = mvaBelop;
		this.reversert = reversert;
		this.avdeling = avdeling;
		this.bunt = bunt;
		this.fakturaLinjes = fakturaLinjes;
		this.fakturaTeksts = fakturaTeksts;
		this.regnskapKladds = regnskapKladds;
		this.betingelseGruppe = betingelseGruppe;
		this.slettet = slettet;
		this.bokfSelskap = bokfSelskap;
		this.reversertFakturaId = reversertFakturaId;
		this.kid = kid;
		this.xmlGenerertDato=xmlGenerertDato;
		this.brukSomGrunnlag=brukSomGrunnlag;
		
		this.avsenderNavn=avsenderNavn;
		this.avsenderAdresse1=avsenderAdresse1;
		this.avsenderAdresse2=avsenderAdresse2;
		this.avsenderAdresse3=avsenderAdresse3;
		this.avtalenr=avtalenr;
		this.juridiskNavn=juridiskNavn;
		this.avsenderOrgNr=avsenderOrgNr;
		this.avsenderTelefon=avsenderTelefon;
		this.avsenderTelefax=avsenderTelefax;
		this.avsenderKontonr=avsenderKontonr;
		this.fakturertAv=fakturertAv;
		this.icaKontoTekst=icaKontoTekst;
		this.harSatsLinje=harSatsLinje;
		this.overtrekksrente=overtrekksrente;
	}

	/** default constructor */
	public Faktura() {
	}

	/**
	 * @return id
	 */
	public Integer getFakturaId() {
		return this.fakturaId;
	}

	/**
	 * @param fakturaId
	 */
	public void setFakturaId(Integer fakturaId) {
		this.fakturaId = fakturaId;
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
	 * @return fakturadato
	 */
	public Date getFakturaDato() {
		return this.fakturaDato;
	}

	/**
	 * @param fakturaDato
	 */
	public void setFakturaDato(Date fakturaDato) {
		this.fakturaDato = fakturaDato;
	}

	/**
	 * @return år
	 */
	public Integer getAar() {
		return this.aar;
	}

	/**
	 * @param aar
	 */
	public void setAar(Integer aar) {
		this.aar = aar;
	}

	/**
	 * @return fra periode
	 */
	public Integer getFraPeriode() {
		return this.fraPeriode;
	}

	/**
	 * @param fraPeriode
	 */
	public void setFraPeriode(Integer fraPeriode) {
		this.fraPeriode = fraPeriode;
	}

	/**
	 * @return forfallsdato
	 */
	public Date getForfallDato() {
		return this.forfallDato;
	}

	/**
	 * @param forfallDato
	 */
	public void setForfallDato(Date forfallDato) {
		this.forfallDato = forfallDato;
	}

	/**
	 * @return til periode
	 */
	public Integer getTilPeriode() {
		return this.tilPeriode;
	}

	/**
	 * @param tilPeriode
	 */
	public void setTilPeriode(Integer tilPeriode) {
		this.tilPeriode = tilPeriode;
	}

	/**
	 * @return fakturanummer
	 */
	public String getFakturaNr() {
		return this.fakturaNr;
	}

	/**
	 * @param fakturaNr
	 */
	public void setFakturaNr(String fakturaNr) {
		this.fakturaNr = fakturaNr;
	}

	/**
	 * @return totalbeløp
	 */
	public BigDecimal getTotalBelop() {
		return this.totalBelop;
	}

	/**
	 * @param totalBelop
	 */
	public void setTotalBelop(BigDecimal totalBelop) {
		this.totalBelop = totalBelop;
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
	 * @return fakturatittel
	 */
	public String getFakturaTittel() {
		return this.fakturaTittel;
	}

	/**
	 * @param fakturaTittel
	 */
	public void setFakturaTittel(String fakturaTittel) {
		this.fakturaTittel = fakturaTittel;
	}

	/**
	 * @return grunnlagsbeløp
	 */
	public BigDecimal getGrunnlagBelop() {
		return this.grunnlagBelop;
	}

	/**
	 * @param grunnlagBelop
	 */
	public void setGrunnlagBelop(BigDecimal grunnlagBelop) {
		this.grunnlagBelop = grunnlagBelop;
	}

	/**
	 * @return fakturatekst
	 */
	public String getFakturaTekst() {
		return this.fakturaTekst;
	}

	/**
	 * @param fakturaTekst
	 */
	public void setFakturaTekst(String fakturaTekst) {
		this.fakturaTekst = fakturaTekst;
	}

	/**
	 * @return mottakernavn
	 */
	public String getMottakerNavn() {
		return this.mottakerNavn;
	}

	/**
	 * @param mottakerNavn
	 */
	public void setMottakerNavn(String mottakerNavn) {
		this.mottakerNavn = mottakerNavn;
	}

	/**
	 * @return adresse1
	 */
	public String getAdresse1() {
		return this.adresse1;
	}

	/**
	 * @param adresse1
	 */
	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	/**
	 * @return adresse2
	 */
	public String getAdresse2() {
		return this.adresse2;
	}

	/**
	 * @param adresse2
	 */
	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	/**
	 * @return postnummer
	 */
	public String getPostnr() {
		return this.postnr;
	}

	/**
	 * @param postnr
	 */
	public void setPostnr(String postnr) {
		this.postnr = postnr;
	}

	/**
	 * @return poststed
	 */
	public String getPoststed() {
		return this.poststed;
	}

	/**
	 * @param poststed
	 */
	public void setPoststed(String poststed) {
		this.poststed = poststed;
	}

	/**
	 * @return beløp
	 */
	public BigDecimal getBelop() {
		return this.belop;
	}

	/**
	 * @param belop
	 */
	public void setBelop(BigDecimal belop) {
		this.belop = belop;
	}

	/**
	 * @return mvabeløp
	 */
	public BigDecimal getMvaBelop() {
		return this.mvaBelop;
	}

	/**
	 * @param mvaBelop
	 */
	public void setMvaBelop(BigDecimal mvaBelop) {
		this.mvaBelop = mvaBelop;
	}

	/**
	 * @return reversert
	 */
	public Integer getReversert() {
		return this.reversert;
	}

	/**
	 * @param reversert
	 */
	public void setReversert(Integer reversert) {
		this.reversert = reversert;
	}

	/**
	 * @return avdeling
	 */
	public no.ica.fraf.model.Avdeling getAvdeling() {
		return this.avdeling;
	}

	/**
	 * @param avdeling
	 */
	public void setAvdeling(no.ica.fraf.model.Avdeling avdeling) {
		this.avdeling = avdeling;
	}

	/**
	 * @return bunt
	 */
	public no.ica.fraf.model.Bunt getBunt() {
		return this.bunt;
	}

	/**
	 * @param bunt
	 */
	public void setBunt(no.ica.fraf.model.Bunt bunt) {
		this.bunt = bunt;
	}

	/**
	 * @return fakturalinjer
	 */
	public Set<FakturaLinje> getFakturaLinjes() {
		return this.fakturaLinjes;
	}

	/**
	 * @param fakturaLinjes
	 */
	public void setFakturaLinjes(Set<FakturaLinje> fakturaLinjes) {
		this.fakturaLinjes = fakturaLinjes;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Faktura))
			return false;
		Faktura castOther = (Faktura) other;
		return new EqualsBuilder().append(fakturaId, castOther.fakturaId)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(fakturaId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (fakturaId != null) {
			return fakturaId.toString();
		}
		return "";
	}

	/**
	 * @return fakturatekster
	 */
	public Set<FakturaTekst> getFakturaTeksts() {
		return fakturaTeksts;
	}

	/**
	 * @param fakturaTeksts
	 */
	public void setFakturaTeksts(Set<FakturaTekst> fakturaTeksts) {
		this.fakturaTeksts = fakturaTeksts;
	}

	/**
	 * @return regnskapkladder
	 */
	public Set<RegnskapKladd> getRegnskapKladds() {
		return regnskapKladds;
	}

	/**
	 * @param regnskapKladds
	 */
	public void setRegnskapKladds(Set<RegnskapKladd> regnskapKladds) {
		this.regnskapKladds = regnskapKladds;
	}

	/**
	 * @return betingelsegruppe
	 */
	public BetingelseGruppe getBetingelseGruppe() {
		return betingelseGruppe;
	}

	/**
	 * @param betingelseGruppe
	 */
	public void setBetingelseGruppe(BetingelseGruppe betingelseGruppe) {
		this.betingelseGruppe = betingelseGruppe;
	}

	/**
	 * @return slettet
	 */
	public Integer getSlettet() {
		return slettet;
	}

	/**
	 * @param slettet
	 */
	public void setSlettet(Integer slettet) {
		this.slettet = slettet;
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
	 * @return reveresert faktura
	 */
	public Integer getReversertFakturaId() {
		return reversertFakturaId;
	}

	/**
	 * @param reversertFakturaId
	 */
	public void setReversertFakturaId(Integer reversertFakturaId) {
		this.reversertFakturaId = reversertFakturaId;
	}

	public Batchable getBatchable() {
		return bunt;
	}

	public Integer getStoreNo() {
		return avdnr;
	}

	public BigDecimal getInvoiceNr() {
		if(fakturaNr!=null){
		return new BigDecimal(fakturaNr);
		}
		return null;
	}

	public Set<InvoiceItemInterface> getInvoiceItemInterfaces() {
		Set<InvoiceItemInterface> set = new HashSet<InvoiceItemInterface>();
		if (fakturaLinjes != null) {
			for (FakturaLinje fakturaLinje : fakturaLinjes) {
				set.add(fakturaLinje);
			}
		}
		return set;
	}

	public Date getInvoiceDate() {
		return fakturaDato;
	}

	public Date getDueDate() {
		return forfallDato;
	}

	public String getKidNr() {
		return kid;
	}

	public BigDecimal getGrossAmount() {
		return totalBelop;
	}

	public BigDecimal getVatTotalsAmount() {
		return mvaBelop;
	}

	public BigDecimal getNetAmount() {
		return belop;
	}

	public BigDecimal getVatBaseAmount() {
		return belop;
	}

	public String getHeading() {
		return fakturaTittel;
	}

	public Integer getNumberOfLines() {
		if (fakturaLinjes != null) {
			return fakturaLinjes.size();
		}
		return 0;
	}

	public String getKid() {
		return kid;
	}

	public void setKid(String kid) {
		this.kid = kid;
	}

	public Boolean hasSatsLine() {
		Boolean returnValue = Boolean.FALSE;
		for (FakturaLinje fakturaLinje : fakturaLinjes) {
			
			if ((fakturaLinje.getAvdelingBetingelse()!=null&&(fakturaLinje.getAvdelingBetingelse().getSpeilet() == null || fakturaLinje
					.getAvdelingBetingelse().getSpeilet() == 0))
					&& (fakturaLinje.getAvdelingBetingelse().getSats() != null && fakturaLinje
							.getAvdelingBetingelse().getSats().intValue() != 0)) {
				returnValue = Boolean.TRUE;
			}
		}
		return returnValue;
	}

	public Date getXmlGenerertDato() {
		return xmlGenerertDato;
	}

	public void setXmlGenerertDato(Date xmlGenerertDato) {
		this.xmlGenerertDato = xmlGenerertDato;
	}

	public Integer getBrukSomGrunnlag() {
		return brukSomGrunnlag;
	}

	public void setBrukSomGrunnlag(Integer brukSomGrunnlag) {
		this.brukSomGrunnlag = brukSomGrunnlag;
	}

	public String getAvsenderAdresse2() {
		return avsenderAdresse2;
	}

	public void setAvsenderAdresse2(String avenderAdresse2) {
		this.avsenderAdresse2 = avenderAdresse2;
	}

	public String getAvsenderAdresse1() {
		return avsenderAdresse1;
	}

	public void setAvsenderAdresse1(String avsenderAdresse1) {
		this.avsenderAdresse1 = avsenderAdresse1;
	}

	public String getAvsenderAdresse3() {
		return avsenderAdresse3;
	}

	public void setAvsenderAdresse3(String avsenderAdresse3) {
		this.avsenderAdresse3 = avsenderAdresse3;
	}

	public String getAvsenderKontonr() {
		return avsenderKontonr;
	}

	public void setAvsenderKontonr(String avsenderKontonr) {
		this.avsenderKontonr = avsenderKontonr;
	}

	public String getAvsenderNavn() {
		return avsenderNavn;
	}

	public void setAvsenderNavn(String avsenderNavn) {
		this.avsenderNavn = avsenderNavn;
	}

	public String getAvsenderOrgNr() {
		return avsenderOrgNr;
	}

	public void setAvsenderOrgNr(String avsenderOrgNr) {
		this.avsenderOrgNr = avsenderOrgNr;
	}

	public String getAvsenderTelefax() {
		return avsenderTelefax;
	}

	public void setAvsenderTelefax(String avsenderTelefax) {
		this.avsenderTelefax = avsenderTelefax;
	}

	public String getAvsenderTelefon() {
		return avsenderTelefon;
	}

	public void setAvsenderTelefon(String avsenderTelefon) {
		this.avsenderTelefon = avsenderTelefon;
	}

	public String getAvtalenr() {
		return avtalenr;
	}

	public void setAvtalenr(String avtalenr) {
		this.avtalenr = avtalenr;
	}

	public String getFakturertAv() {
		return fakturertAv;
	}

	public void setFakturertAv(String fakturertAv) {
		this.fakturertAv = fakturertAv;
	}

	public Integer getHarSatsLinje() {
		return harSatsLinje;
	}

	public void setHarSatsLinje(Integer harSatsLinje) {
		this.harSatsLinje = harSatsLinje;
	}

	public String getIcaKontoTekst() {
		return icaKontoTekst;
	}

	public void setIcaKontoTekst(String icaKontoTekst) {
		this.icaKontoTekst = icaKontoTekst;
	}

	public String getJuridiskNavn() {
		return juridiskNavn;
	}

	public void setJuridiskNavn(String juridiskNavn) {
		this.juridiskNavn = juridiskNavn;
	}

	public String getOvertrekksrente() {
		return overtrekksrente;
	}

	public void setOvertrekksrente(String overtrekksrente) {
		this.overtrekksrente = overtrekksrente;
	}

	public List<FakturaTekst> getBelopFakturaTekster(){
		List<FakturaTekst> teksts = new ArrayList<FakturaTekst>();
		if(fakturaTeksts!=null){
			for(FakturaTekst tekst:fakturaTeksts){
				if(tekst.getFakturaTekstType()==null){
					teksts.add(tekst);
				}
			}
		}
		return teksts;
	}
	
	public List<FakturaTekst> getBetingelseFakturaTekster(){
		List<FakturaTekst> teksts = new ArrayList<FakturaTekst>();
		if(fakturaTeksts!=null){
			for(FakturaTekst tekst:fakturaTeksts){
				if(tekst.getFakturaTekstType()!=null&&tekst.getFakturaTekstType().equalsIgnoreCase("BET")){
					teksts.add(tekst);
				}
			}
		}
		return teksts;
	}

	public String getCompanyName() {
		return bokfSelskap.getSelskapNavn();
	}

	public Integer getInvoiceId() {
		return fakturaId;
	}

	
}
