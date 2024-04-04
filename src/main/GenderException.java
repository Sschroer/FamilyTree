package main;

/**
 * This is the Gender Exception. 
 * It will be thrown when any code dependent on Sex enums is used. 
 * @author Stephen Schroer
 *
 */
public class GenderException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GenderException() {
		super();
	}

	public GenderException(String message) {
		super(message);
	}

	public GenderException(Throwable cause) {
		super(cause);
	}

	public GenderException(String message, Throwable cause) {
		super(message, cause);
	}

	public GenderException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
