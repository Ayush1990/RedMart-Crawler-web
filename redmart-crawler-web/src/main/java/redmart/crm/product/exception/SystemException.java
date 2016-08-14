/**
 * 
 */
package redmart.crm.product.exception;

/**
 * This exception class represents whenever a System Error occurs
 * @author dipankar.saha
 *
 */
@SuppressWarnings("serial")
public class SystemException extends Exception {
	
	public SystemException() {
		super();
	}
	
	public SystemException(String message) {
		super(message);
	}
	
	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}
}
