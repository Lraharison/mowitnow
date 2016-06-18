package fr.xebia.mowitnow.util;

import org.slf4j.Logger;

import fr.xebia.mowitnow.exception.MowItNowException;

/**
 * Class utilitaire de gestion d'exception.
 * 
 * @author Raharison L
 */
public class ExceptionUtil {

	private ExceptionUtil() {

	}

	/**
	 * Logg error.
	 *
	 * @param message the message
	 * @param logger the logger
	 * @throws MowItNowException the mow it now exception
	 */
	public static void loggError(String message, Logger logger) throws MowItNowException {
		logger.error(message);
		throw new MowItNowException(message);
	}
}
