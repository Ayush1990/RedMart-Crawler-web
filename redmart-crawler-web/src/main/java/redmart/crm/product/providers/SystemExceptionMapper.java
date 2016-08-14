package redmart.crm.product.providers;

import static redmart.crm.product.rest.entities.ErrorConstants.SYSTEM_ERROR_MSG;
import static redmart.crm.product.rest.entities.ErrorConstants.SYSTEM_EXCEPTION;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import redmart.crm.product.exception.SystemException;
import redmart.crm.product.rest.entities.ErrorEntity;

/**
 * This Exception Mapper is used whenever a System Exception occurs.
 * 
 * @author dipankar.saha
 *
 */
@Provider
public class SystemExceptionMapper implements ExceptionMapper<SystemException> {

	@Override
	public Response toResponse(SystemException exception) {
		ErrorEntity errorEntity = new ErrorEntity();
		errorEntity.setCode(SYSTEM_EXCEPTION);
		errorEntity.setMessage(SYSTEM_ERROR_MSG + exception.getMessage());
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorEntity).build();
	}
}
