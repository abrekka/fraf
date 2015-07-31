package no.ica.fraf;

import java.lang.Thread.UncaughtExceptionHandler;

import no.ica.fraf.util.GuiUtil;

import org.apache.log4j.Logger;

/**
 * Håndterer exception som ikke er tatt hånd av try/catch og logger disse
 * 
 * @author abr99
 * 
 */
public class FrafUncaughtHandler implements UncaughtExceptionHandler {
	/**
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(FrafUncaughtHandler.class);

	/**
	 * Håndterer exception ved å logge denne
	 * 
	 * @param t
	 * @param e
	 */
	public void uncaughtException(Thread t, Throwable e) {
		logger.error("Feil som ikke er håndtert", e);
		GuiUtil
		.showConfirmDialog(
			e.getMessage(),
				"Feil som ikke er håndtert");
	}

}
