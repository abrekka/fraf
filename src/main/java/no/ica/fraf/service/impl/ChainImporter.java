package no.ica.fraf.service.impl;

import no.ica.fraf.FrafException;
import no.ica.fraf.model.SapChain;
import no.ica.fraf.model.SapDepartment;
import no.ica.fraf.service.SapChainManager;

public class ChainImporter extends SapDataExcelImporter<SapChain>{
	private static final int COLUMN_SELSKAP = 0;
	private static final int COLUMN_KJEDE = 1;
	private static final int COLUMN_NAVN = 2;
	private SapChainManager sapChainManager;
	public ChainImporter(SapChainManager manager) {
		super(manager,"base_chain_file_name");
	}
	@Override
	protected SapChain getData(int row) throws FrafException {
		SapChain chain = new SapChain();
		setChainData(chain, row);
		return chain;
	}

	private void setChainData(SapChain chain,
			int row) throws FrafException {
		try {
			//selskap
			String value = readCell(row, COLUMN_SELSKAP);
			chain.setSelskap(value);
			//kjede
			value = readCell(row, COLUMN_KJEDE);
			chain.setKjede(value);
			// navn
			value = readCell(row, COLUMN_NAVN);
			chain.setChainName(value);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrafException(e);
		}
	}


}
