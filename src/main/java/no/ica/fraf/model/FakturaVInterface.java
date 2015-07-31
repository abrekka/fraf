package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.Date;

public interface FakturaVInterface {

	/**
	 * @return avsenderadresse1
	 */
	public abstract String getAvsenderAdresse1();

	/**
	 * @param avsenderAdresse1
	 */
	public abstract void setAvsenderAdresse1(String avsenderAdresse1);

	/**
	 * @return avsenderadresse2
	 */
	public abstract String getAvsenderAdresse2();

	/**
	 * @param avsenderAdresse2
	 */
	public abstract void setAvsenderAdresse2(String avsenderAdresse2);

	/**
	 * @return avsenderadresse3
	 */
	public abstract String getAvsenderAdresse3();

	/**
	 * @param avsenderAdresse3
	 */
	public abstract void setAvsenderAdresse3(String avsenderAdresse3);

	/**
	 * @return avsenderkontonummer
	 */
	public abstract String getAvsenderKontonr();

	/**
	 * @param avsenderKontonr
	 */
	public abstract void setAvsenderKontonr(String avsenderKontonr);

	/**
	 * @return avsendernavn
	 */
	public abstract String getAvsenderNavn();

	/**
	 * @param avsenderNavn
	 */
	public abstract void setAvsenderNavn(String avsenderNavn);

	/**
	 * @return avsenderorgnr
	 */
	public abstract String getAvsenderOrgNr();

	/**
	 * @param avsenderOrgNr
	 */
	public abstract void setAvsenderOrgNr(String avsenderOrgNr);

	/**
	 * @return avsendertelefax
	 */
	public abstract String getAvsenderTelefax();

	/**
	 * @param avsenderTelefax
	 */
	public abstract void setAvsenderTelefax(String avsenderTelefax);

	/**
	 * @return avsender telefonnummer
	 */
	public abstract String getAvsenderTelefon();

	/**
	 * @param avsenderTelefon
	 */
	public abstract void setAvsenderTelefon(String avsenderTelefon);

	/**
	 * @return adresse1
	 */
	public abstract String getAdresse1();

	/**
	 * @param adresse1
	 */
	public abstract void setAdresse1(String adresse1);

	/**
	 * @return adresse2
	 */
	public abstract String getAdresse2();

	/**
	 * @param adresse2
	 */
	public abstract void setAdresse2(String adresse2);

	/**
	 * @return avdnr
	 */
	public abstract Integer getAvdnr();

	/**
	 * @param avdnr
	 */
	public abstract void setAvdnr(Integer avdnr);

	/**
	 * @return beløp
	 */
	public abstract BigDecimal getBelop();

	/**
	 * @param belop
	 */
	public abstract void setBelop(BigDecimal belop);

	/**
	 * @return fakturadato
	 */
	public abstract Date getFakturaDato();

	/**
	 * @param fakturaDato
	 */
	public abstract void setFakturaDato(Date fakturaDato);

	/**
	 * @return fakturaid
	 */
	public abstract Integer getFakturaId();

	/**
	 * @param fakturaId
	 */
	public abstract void setFakturaId(Integer fakturaId);

	/**
	 * @return fakturanummer
	 */
	public abstract String getFakturaNr();

	/**
	 * @param fakturaNr
	 */
	public abstract void setFakturaNr(String fakturaNr);

	/**
	 * @return forfallsdato
	 */
	public abstract Date getForfallDato();

	/**
	 * @param forfallDato
	 */
	public abstract void setForfallDato(Date forfallDato);

	/**
	 * @return juridisknavn
	 */
	public abstract String getJuridiskNavn();

	/**
	 * @param juridiskNavn
	 */
	public abstract void setJuridiskNavn(String juridiskNavn);

	/**
	 * @return mottakernavn
	 */
	public abstract String getMottakerNavn();

	/**
	 * @param mottakerNavn
	 */
	public abstract void setMottakerNavn(String mottakerNavn);

	/**
	 * @return mvabeløp
	 */
	public abstract BigDecimal getMvaBelop();

	/**
	 * @param mvaBelop
	 */
	public abstract void setMvaBelop(BigDecimal mvaBelop);

	/**
	 * @return postnr
	 */
	public abstract String getPostnr();

	/**
	 * @param postnr
	 */
	public abstract void setPostnr(String postnr);

	/**
	 * @return poststed
	 */
	public abstract String getPoststed();

	/**
	 * @param poststed
	 */
	public abstract void setPoststed(String poststed);

	/**
	 * @return totalbeløp
	 */
	public abstract BigDecimal getTotalBelop();

	/**
	 * @param totalBelop
	 */
	public abstract void setTotalBelop(BigDecimal totalBelop);

	/**
	 * @return buntid
	 */
	public abstract Integer getBuntId();

	/**
	 * @param buntId
	 */
	public abstract void setBuntId(Integer buntId);

	/**
	 * @return fakturatittel
	 */
	public abstract String getFakturaTittel();

	/**
	 * @param fakturaTittel
	 */
	public abstract void setFakturaTittel(String fakturaTittel);

	/**
	 * @return kid
	 */
	public abstract String getKid();

	/**
	 * @param kid
	 */
	public abstract void setKid(String kid);

	/**
	 * @return bokføringsselskap
	 */
	public abstract String getBokfSelskap();

	/**
	 * @param bokfSelskap
	 */
	public abstract void setBokfSelskap(String bokfSelskap);

	/**
	 * @return fakturert av
	 */
	public abstract String getFakturertAv();

	/**
	 * @param fakturertAv
	 */
	public abstract void setFakturertAv(String fakturertAv);

	/**
	 * @return avtalenummer
	 */
	public abstract String getAvtalenr();

	/**
	 * @param avtalenr
	 */
	public abstract void setAvtalenr(String avtalenr);

	/**
	 * @return ica-konto tekst
	 */
	public abstract String getIcaKontoTekst();

	/**
	 * @param icaKontoTekst
	 */
	public abstract void setIcaKontoTekst(String icaKontoTekst);

	public abstract Integer getHarSatsLinje();

	public abstract void setHarSatsLinje(Integer harSatsLinje);

}