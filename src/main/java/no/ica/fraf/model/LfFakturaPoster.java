package no.ica.fraf.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for tabell LfFakturaPoster i Fenistra
 * 
 * @author abr99
 * 
 */
public class LfFakturaPoster extends BaseObject {
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"dd.MM.yyyy");
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer fakturaPostId;

	/**
	 * 
	 */
	private Integer fakturaNr;

	/**
	 * 
	 */
	private String fakturatekst;

	/**
	 * 
	 */
	private Integer kontraktObjektId;

	/**
	 * 
	 */
	private Date fra;

	/**
	 * 
	 */
	private Date til;

	/**
	 * 
	 */
	private Date terminStart;

	/**
	 * 
	 */
	private BigDecimal postBeloep;

	/**
	 * 
	 */
	private Integer remitering;

	/**
	 * 
	 */
	public LfFakturaPoster() {
	}

	/**
	 * @param nr
	 * @param id
	 * @param fakturatekst
	 * @param dato
	 * @param id2
	 * @param beloep
	 * @param start
	 * @param dato2
	 * @param remitering
	 */
	public LfFakturaPoster(Integer nr, Integer id, String fakturatekst,
			Date dato, Integer id2, BigDecimal beloep, Date start, Date dato2,
			Integer remitering) {
		fakturaNr = nr;
		fakturaPostId = id;
		this.fakturatekst = fakturatekst;
		fra = dato;
		kontraktObjektId = id2;
		postBeloep = beloep;
		terminStart = start;
		til = dato2;
		this.remitering = remitering;
	}

	/**
	 * @return fakturanummer
	 */
	public Integer getFakturaNr() {
		return fakturaNr;
	}

	/**
	 * @param fakturaNr
	 */
	public void setFakturaNr(Integer fakturaNr) {
		this.fakturaNr = fakturaNr;
	}

	/**
	 * @return id
	 */
	public Integer getFakturaPostId() {
		return fakturaPostId;
	}

	/**
	 * @param fakturaPostId
	 */
	public void setFakturaPostId(Integer fakturaPostId) {
		this.fakturaPostId = fakturaPostId;
	}

	/**
	 * @return fakturatekst
	 */
	public String getFakturatekst() {
		return fakturatekst;
	}

	/**
	 * @param fakturatekst
	 */
	public void setFakturatekst(String fakturatekst) {
		this.fakturatekst = fakturatekst;
	}

	/**
	 * @return fradato
	 */
	public Date getFra() {
		return fra;
	}

	/**
	 * @param fra
	 */
	public void setFra(Date fra) {
		this.fra = fra;
	}

	/**
	 * @return kontraktobjektid
	 */
	public Integer getKontraktObjektId() {
		return kontraktObjektId;
	}

	/**
	 * @param kontraktObjektId
	 */
	public void setKontraktObjektId(Integer kontraktObjektId) {
		this.kontraktObjektId = kontraktObjektId;
	}

	/**
	 * @return beløp
	 */
	public BigDecimal getPostBeloep() {
		return postBeloep;
	}

	/**
	 * @param postBeloep
	 */
	public void setPostBeloep(BigDecimal postBeloep) {
		this.postBeloep = postBeloep;
	}

	/**
	 * @return terminstart
	 */
	public Date getTerminStart() {
		return terminStart;
	}

	/**
	 * @param terminStart
	 */
	public void setTerminStart(Date terminStart) {
		this.terminStart = terminStart;
	}

	/**
	 * @return tildato
	 */
	public Date getTil() {
		return til;
	}

	/**
	 * @param til
	 */
	public void setTil(Date til) {
		this.til = til;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof LfFakturaPoster))
			return false;
		LfFakturaPoster castOther = (LfFakturaPoster) other;
		return new EqualsBuilder().append(fakturaPostId,
				castOther.fakturaPostId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(fakturaPostId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
				.append("fakturaPostId", fakturaPostId)
				.append("fakturaNr", fakturaNr)
				.append("fakturatekst", fakturatekst)
				.append("kontraktObjektId", kontraktObjektId)
				.append("fraDato", fra).append("tilDato", til)
				.append("terminStart", terminStart)
				.append("postBeloep", postBeloep).toString();
	}

	/**
	 * @return remitering
	 */
	public Integer getRemitering() {
		return remitering;
	}

	/**
	 * @param remitering
	 */
	public void setRemitering(Integer remitering) {
		this.remitering = remitering;
	}

	public LfFakturaPoster fakturapostId(String id) {
		setFakturaPostId(Integer.valueOf(id));
		return this;
	}

	public LfFakturaPoster fakturanr(String fakturanr) {
		setFakturaNr(Integer.valueOf(fakturanr));
		return this;
	}

	public LfFakturaPoster fakturatekst(String tekst) {
		setFakturatekst(tekst);
		return this;
	}

	public LfFakturaPoster kontraktobjektId(String id) {
		setKontraktObjektId(Integer.valueOf(id));
		return this;
	}

	public LfFakturaPoster fra(String fradato) throws ParseException {
		setFra(simpleDateFormat.parse(fradato));
		return this;
	}

	public LfFakturaPoster til(String tilDato) throws ParseException {
		setTil(simpleDateFormat.parse(tilDato));
		return this;
	}

	public LfFakturaPoster terminstart(String terminstart)
			throws ParseException {
		if (!"0".equalsIgnoreCase(terminstart)) {
			setTerminStart(simpleDateFormat.parse(terminstart));
		}
		return this;
	}

	public LfFakturaPoster postbeloep(String postbeloep) {
		setPostBeloep(BigDecimal.valueOf(Long.valueOf(postbeloep)));
		return this;
	}

	public LfFakturaPoster remitering(String remitering) {
		setRemitering(Integer.valueOf(remitering));
		return this;
	}

}
