package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import no.ica.elfa.model.CreditImport;
import no.ica.elfa.model.Invoice;
import no.ica.fraf.common.ApplUserInterface;
import no.ica.fraf.common.BatchStatusInterface;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.common.SystemEnum;
import no.ica.fraf.xml.InvoiceInterface;
import no.ica.tollpost.model.TgFaktura;
import no.ica.tollpost.model.TgImport;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * Klasse for tabell BUNT
 * @author abr99
 *
 */
/**
 * @author abr99
 * 
 */
public class Bunt extends AbstractBatchable implements Comparable<Bunt> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer buntId;

	/** nullable persistent field */
	private BuntType buntType;

	/** nullable persistent field */
	private BuntStatus buntStatus;

	/** nullable persistent field */
	private Date opprettetDato;

	/** nullable persistent field */
	private ApplUser applUser;

	/** nullable persistent field */
	private Integer aar;

	/** nullable persistent field */
	private Integer fraPeriode;

	/**
	 * 
	 */
	private Integer tilPeriode;

	/** nullable persistent field */
	private Integer fraAvdnr;

	/** nullable persistent field */
	private Integer tilAvdnr;

	/** nullable persistent field */
	private Integer regionId;

	/** persistent field */

	/** persistent field */
	private Set<Faktura> fakturas;

	private Set<TgImport> tgImports;

	private Set<TgFaktura> tgFakturas;

	private Set<AvdelingAvregningImport> avdelingAvregningImports;

	private Set<AvdelingBetingelse> avdelingBetingelses;

	/**
	 * Elfafakturaer
	 */
	private Set<Invoice> invoices;

	/**
	 * 
	 */
	private BetingelseGruppe betingelseGruppe;

	private String fileName;
	private Date fraDato;
	private Date tilDato;
	private Set<CreditImport> creditImports;

	/**
	 * @param buntId
	 * @param buntType
	 * @param buntStatus
	 * @param opprettetDato
	 * @param applUser
	 * @param aar
	 * @param fraPeriode
	 * @param tilPeriode
	 * @param fraAvdnr
	 * @param tilAvdnr
	 * @param regionId
	 * @param fakturas
	 * @param betingelseGruppe
	 */
	public Bunt(Integer buntId, BuntType buntType, BuntStatus buntStatus,
			Date opprettetDato, ApplUser applUser, Integer aar,
			Integer fraPeriode, Integer tilPeriode, Integer fraAvdnr,
			Integer tilAvdnr, Integer regionId, Set fakturas,
			BetingelseGruppe betingelseGruppe, Set<TgImport> tgImports,
			Set<TgFaktura> tgFakturas,
			Set<AvdelingAvregningImport> avdelingAvregningImports,
			Set<AvdelingBetingelse> avdelingBetingelses, String filaName,
			Set<Invoice> invoices,Date fraDato,Date tilDato,Set<CreditImport> creditImports) {
		this.buntId = buntId;
		this.buntType = buntType;
		this.buntStatus = buntStatus;
		this.opprettetDato = opprettetDato;
		this.applUser = applUser;
		this.aar = aar;
		this.fraPeriode = fraPeriode;
		this.tilPeriode = tilPeriode;
		this.fraAvdnr = fraAvdnr;
		this.tilAvdnr = tilAvdnr;
		this.regionId = regionId;
		this.fakturas = fakturas;
		this.betingelseGruppe = betingelseGruppe;
		this.tgImports = tgImports;
		this.tgFakturas = tgFakturas;
		this.avdelingAvregningImports = avdelingAvregningImports;
		this.avdelingBetingelses = avdelingBetingelses;
		this.fileName = fileName;
		this.invoices = invoices;
		this.fraDato=fraDato;
		this.tilDato=tilDato;
		this.creditImports=creditImports;
	}

	/**
	 * 
	 */
	public Bunt() {
	}

	/**
	 * @return id
	 */
	public Integer getBuntId() {
		return this.buntId;
	}

	/**
	 * @param buntId
	 */
	public void setBuntId(Integer buntId) {
		this.buntId = buntId;
	}

	/**
	 * @return bunttype
	 */
	public BuntType getBuntType() {
		return this.buntType;
	}

	/**
	 * @param buntType
	 */
	public void setBuntType(BuntType buntType) {
		this.buntType = buntType;
	}

	/**
	 * @return status
	 */
	public BuntStatus getBuntStatus() {
		return this.buntStatus;
	}

	/**
	 * @param buntStatus
	 */
	public void setBuntStatus(BuntStatus buntStatus) {
		this.buntStatus = buntStatus;
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
	 * @return bruker
	 */
	public ApplUser getApplUser() {
		return this.applUser;
	}

	/**
	 * @param applUser
	 */
	public void setApplUser(ApplUser applUser) {
		this.applUser = applUser;
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
	 * @return fra avdnr
	 */
	public Integer getFraAvdnr() {
		return this.fraAvdnr;
	}

	/**
	 * @param fraAvdnr
	 */
	public void setFraAvdnr(Integer fraAvdnr) {
		this.fraAvdnr = fraAvdnr;
	}

	/**
	 * @return til avdnr
	 */
	public Integer getTilAvdnr() {
		return this.tilAvdnr;
	}

	/**
	 * @param tilAvdnr
	 */
	public void setTilAvdnr(Integer tilAvdnr) {
		this.tilAvdnr = tilAvdnr;
	}

	/**
	 * @return region
	 */
	public Integer getRegionId() {
		return this.regionId;
	}

	/**
	 * @param regionId
	 */
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	/**
	 * @return fakturaer
	 */
	public Set getFakturas() {
		return this.fakturas;
	}

	/**
	 * @param fakturas
	 */
	public void setFakturas(Set fakturas) {
		this.fakturas = fakturas;
	}

	/**
	 * @return sum
	 */
	public BigDecimal getSum() {
		if (fakturas == null) {
			return null;
		}
		Iterator fakturaIt = fakturas.iterator();
		BigDecimal returnValue = new BigDecimal(0);

		while (fakturaIt.hasNext()) {
			returnValue = returnValue.add(((Faktura) fakturaIt.next())
					.getBelop());
		}
		return returnValue;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Bunt))
			return false;
		Bunt castOther = (Bunt) other;
		return new EqualsBuilder().append(buntId, castOther.buntId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(buntId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer toBuffer = new StringBuffer(buntId.toString());
		if (fileName != null) {
			toBuffer.append(" - ").append(fileName);
		}
		return toBuffer.toString();
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

	public Set<TgImport> getTgImports() {
		return tgImports;
	}

	public void setTgImports(Set<TgImport> tgImports) {
		this.tgImports = tgImports;
	}

	public void addTgImport(TgImport tgImport) {
		if (tgImports == null) {
			tgImports = new HashSet<TgImport>();
		}
		tgImport.setBunt(this);
		tgImports.add(tgImport);
	}

	public BatchStatusInterface getBatchStatus() {
		return buntStatus;
	}

	public Date getFromDate() {
		return fraDato;
	}

	public Date getToDate() {
		return tilDato;
	}

	public Integer getBatchId() {
		return buntId;
	}

	public String getInfoString() {
		return "FRAF ";
	}

	public ApplUserInterface getApplUserInterface() {
		return applUser;
	}

	public Date getCreatedDate() {
		return opprettetDato;
	}

	public void setBatchStatus(BatchStatusInterface batchStatusInterface) {
		setBuntStatus((BuntStatus) batchStatusInterface);

	}

	public String getDirectoryName() {
		return buntId.toString();
	}

	public Set<InvoiceInterface> getInvoiceInterfaces(SystemEnum systemEnum) {
		Set<InvoiceInterface> set = new HashSet<InvoiceInterface>();
		switch(systemEnum){
		case TOLLPOST:
			if (tgFakturas != null) {
				for (TgFaktura tgFaktura : tgFakturas) {
					set.add(tgFaktura);
				}
			}
			break;
		case ELFA:
			if (invoices != null) {
				for (Invoice invoice : invoices) {
					set.add(invoice);
				}
			}
		}
		
		return set;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;

	}

	public Set<TgFaktura> getTgFakturas() {
		return tgFakturas;
	}

	public void setTgFakturas(Set<TgFaktura> tgFakturas) {
		this.tgFakturas = tgFakturas;
	}

	public Set<AvdelingAvregningImport> getAvdelingAvregningImports() {
		return avdelingAvregningImports;
	}

	public void setAvdelingAvregningImports(
			Set<AvdelingAvregningImport> avdelingAvregningImports) {
		this.avdelingAvregningImports = avdelingAvregningImports;
	}

	public void addAvdelingAvregningImport(
			AvdelingAvregningImport avdelingAvregningImport) {
		if (avdelingAvregningImports == null) {
			avdelingAvregningImports = new HashSet<AvdelingAvregningImport>();
		}
		avdelingAvregningImport.setBunt(this);
		avdelingAvregningImports.add(avdelingAvregningImport);

	}

	public Set<AvdelingBetingelse> getAvdelingBetingelses() {
		return avdelingBetingelses;
	}

	public void setAvdelingBetingelses(
			Set<AvdelingBetingelse> avdelingBetingelses) {
		this.avdelingBetingelses = avdelingBetingelses;
	}

	public String getFileName() {
		return fileName;
	}

	public Set<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

	public Date getFraDato() {
		return fraDato;
	}

	public void setFraDato(Date fraDato) {
		this.fraDato = fraDato;
	}

	public Date getTilDato() {
		return tilDato;
	}

	public void setTilDato(Date tilDato) {
		this.tilDato = tilDato;
	}
	
	public void addCreditImport(CreditImport creditImport) {
		if (creditImports == null) {
			creditImports = new HashSet<CreditImport>();
		}
		creditImport.setBunt(this);
		creditImports.add(creditImport);
	}

	public Set<CreditImport> getCreditImports() {
		return creditImports;
	}

	public void setCreditImports(Set<CreditImport> creditImports) {
		this.creditImports = creditImports;
	}

	public int compareTo(final Bunt other) {
		return new CompareToBuilder().append(buntId, other.buntId)
				.toComparison();
	}
}
