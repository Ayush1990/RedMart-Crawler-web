package redmart.crm.product.exception;

/**
 * This exception class represents the condition where the input has missing/wrong parameters
 * @author dipankar.saha
 *
 */
@SuppressWarnings("serial")
public class InvalidParameterException extends Exception {
	
	public InvalidParameterException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public InvalidParameterException(String message) {
		super(message);
	}

}
