/**
 * 
 */
package redmart.crm.product.rest.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * This class represents an error condition.
 * @author dipankar.saha
 *
 */
@JsonPropertyOrder({ "code", "message", "description", "resolution" })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorEntity {

	private String code;
	private String message;
	private String description;
	private String resolution;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the resolution
	 */
	public String getResolution() {
		return resolution;
	}
	/**
	 * @param resolution the resolution to set
	 */
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	
}
