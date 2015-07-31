package no.ica.fraf.util;

import java.io.File;

import no.ica.fraf.FrafException;

public class DirUtil {
	public static  String checkAndCreateDir(String dir)
			throws FrafException {
		File dirFile = new File(dir);

		// Sjekker om eksportkatalog finnes, dersom ikke blir denne prøvd lagd
		if (!dirFile.exists()) {

			if (!dirFile.mkdir()) {
				throw new FrafException("Kunne ikke lage katalog "
						+ dir);
			}
		}
		return dir;
	}
}
