package main;

/**
 * This is the Gender Exception. It will be thrown when any code dependent on
 * Sex enums is used.
 * 
 * @author Stephen Schroer
 *
 */
public class GenderException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new GenderException with no specified detail message.
	 */
	public GenderException() {
		super();
	}

	/**
	 * Constructs a new GenderException with the specified detail message.
	 *
	 * @param message
	 *            the detail message
	 */
	public GenderException(String message) {
		super(message);
	}

	/**
	 * Constructs a new GenderException with the specified cause.
	 *
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 *            getCause() method)
	 */
	public GenderException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new GenderException with the specified detail message and
	 * cause.
	 *
	 * @param message
	 *            the detail message
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 *            getCause() method)
	 */
	public GenderException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new GenderException with the specified detail message,
	 * cause, suppression enabled or disabled, and writable stack trace enabled
	 * or disabled.
	 *
	 * @param message
	 *            the detail message
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 *            getCause() method)
	 * @param enableSuppression
	 *            whether or not suppression is enabled or disabled
	 * @param writableStackTrace
	 *            whether or not the stack trace should be writable
	 */
	public GenderException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
