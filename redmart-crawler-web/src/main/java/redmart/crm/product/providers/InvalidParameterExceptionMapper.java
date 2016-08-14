package redmart.crm.product.providers;

import static redmart.crm.product.rest.entities.ErrorConstants.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import redmart.crm.product.exception.InvalidParameterException;
import redmart.crm.product.rest.entities.ErrorEntity;

/**
 * This Exception Mapper is used whenever a Invalid Parameter Exception occurs.
 * 
 * @author dipankar.saha
 *
 */
@Provider
public class InvalidParameterExceptionMapper implements ExceptionMapper<InvalidParameterException> {

	@Override
	public Response toResponse(InvalidParameterException exception) {
		ErrorEntity errorEntity = new ErrorEntity();
		errorEntity.setCode(INVALID_PARAMETER);
		errorEntity.setMessage(exception.getMessage());
		return Response.status(Status.BAD_REQUEST).entity(errorEntity).build();
	}
}
