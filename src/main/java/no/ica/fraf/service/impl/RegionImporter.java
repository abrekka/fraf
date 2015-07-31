package no.ica.fraf.service.impl;

import no.ica.fraf.FrafException;
import no.ica.fraf.model.SapRegion;
import no.ica.fraf.service.SapManager;

public class RegionImporter extends SapDataExcelImporter<SapRegion> {

	private static final int COLUMN_SELSKAP = 0;
	private static final int COLUMN_REGION = 1;
	private static final int COLUMN_NAVN = 2;

	public RegionImporter(SapManager<SapRegion> manager) {
		super(manager, "base_region_file_name");
	}

	@Override
	protected SapRegion getData(int row) throws FrafException {
		SapRegion region = new SapRegion();
		setRegionData(region, row);
		return region;
	}

	private void setRegionData(SapRegion region, int row) throws FrafException {
		try {
			// selskap
			String value = readCell(row, COLUMN_SELSKAP);
			region.setSelskap(value);
			// region
			value = readCell(row, COLUMN_REGION);
			region.setRegion(value);
			// navn
			value = readCell(row, COLUMN_NAVN);
			region.setRegionName(value);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrafException(e);
		}
	}

}
