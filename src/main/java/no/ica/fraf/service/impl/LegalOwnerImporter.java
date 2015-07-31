package no.ica.fraf.service.impl;

import java.math.BigInteger;

import no.ica.fraf.FrafException;
import no.ica.fraf.model.SapLegalOwner;
import no.ica.fraf.service.SapManager;

public class LegalOwnerImporter extends SapDataExcelImporter<SapLegalOwner> {

	private static final int COLUMN_NR = 0;
	private static final int COLUMN_NAVN = 1;
	private static final int COLUMN_ADR1 = 2;
	private static final int COLUMN_POSTNR = 3;
	private static final int COLUMN_POSTSTED = 4;
	private static final int COLUMN_ORG_NR = 5;

	public LegalOwnerImporter(SapManager<SapLegalOwner> manager) {
		super(manager, "base_legal_owner_file_name");
	}

	@Override
	protected SapLegalOwner getData(int row) throws FrafException {
		SapLegalOwner legalOwner = new SapLegalOwner();
		setLegalOwnerData(legalOwner, row);
		return legalOwner;
	}
	
	private void setLegalOwnerData(SapLegalOwner legalOwner, int row) throws FrafException {
		try {
			// nr
			String value = readCell(row, COLUMN_NR);
			legalOwner.setNr(Integer.valueOf(value));
			// navn
			value = readCell(row, COLUMN_NAVN);
			legalOwner.setLegalOwnerName(value);
			// adr1
			value = readCell(row, COLUMN_ADR1);
			legalOwner.setAdr1(value);
			// postnr
			value = readCell(row, COLUMN_POSTNR);
			legalOwner.setPostnr(value);
			// poststed
			value = readCell(row, COLUMN_POSTSTED);
			legalOwner.setPoststed(value);
			// orgnr
			value = readCell(row, COLUMN_ORG_NR);
			legalOwner.setOrgNr(value!=null?BigInteger.valueOf(Long.valueOf(value)):null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrafException(e);
		}
	}


}
