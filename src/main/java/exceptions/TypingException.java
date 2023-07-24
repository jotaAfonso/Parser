package exceptions;

/**
 * The Class TypingException.
 */
public class TypingException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new typing exception.
	 */
	public TypingException() {
	}

	/**
	 * Instantiates a new typing exception.
	 *
	 * @param message - message of the exception
	 */
	public TypingException(String message) {
		super(message);
	}
}
