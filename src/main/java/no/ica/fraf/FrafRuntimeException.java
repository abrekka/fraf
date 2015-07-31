package no.ica.fraf;

/**
 * Runtime exception for FRAF
 * @author abr99
 *
 */
public class FrafRuntimeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param msg
	 */
	public FrafRuntimeException(String msg) {
		super(msg);
	}
}
