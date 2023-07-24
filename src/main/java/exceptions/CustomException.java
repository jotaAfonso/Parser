package exceptions;

/**
 * The Class CustomException.
 */
public class CustomException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new custom exception.
	 */
	public CustomException() {
	}

	/**
	 * Instantiates a new custom exception.
	 *
	 * @param message - message of the exception
	 */
	public CustomException(String message) {
		super(message);
	}
}
