package redmart.crm.product.resources;


import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.jcabi.aspects.Loggable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import redmart.crm.product.core.ProductService;
import redmart.crm.product.core.impl.ProductServiceImpl;
import redmart.crm.product.entity.BrandDetails;
import redmart.crm.product.exception.InvalidParameterException;
import redmart.crm.product.exception.SystemException;
import redmart.crm.product.rest.entities.ErrorEntity;

/**
 * This class is used to describe resources for the Product Management Project.
 * 
 * @author dipankar.saha
 */
@Path("product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "ProductManagerResource", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
public class ProductResource {
	
	static Logger _logger = Logger.getLogger(ProductResource.class.getName());
	
	/*@POST
	@ApiOperation(value = "import redmart product data", notes = "import redmart product data", response = Response.class)
	@ApiResponses({
			@ApiResponse(code = 400, message = "Bad request. Incorrect input", response = ErrorEntity.class),
			@ApiResponse(code = 200, message = "import redmart product data is  successful.", response = Response.class) })
	@Path("/importRedmartData")
	@Loggable(Loggable.DEBUG)*/
	public Response importRedmartData(
			@QueryParam("filePath") String filePath)
			throws InvalidParameterException, SystemException {
		_logger.info("In importRedmartData rest call");
		ProductService service = new ProductServiceImpl();
		boolean  isImported = service.importRedMartData(filePath);
		_logger.info("Exiting importRedmartData rest call");
		return Response.status(Status.OK).entity(isImported).build();
	}
	
	/*@POST
	@ApiOperation(value = "import competitor product data", notes = "import competitor product data", response = Response.class)
	@ApiResponses({
			@ApiResponse(code = 400, message = "Bad request. Incorrect input", response = ErrorEntity.class),
			@ApiResponse(code = 200, message = "import competitor product data is  successful.", response = Response.class) })
	@Path("/importCompetitorData")
	@Loggable(Loggable.DEBUG)*/
	public Response importCompetitorData(
			@QueryParam("filePath") String filePath)
			throws InvalidParameterException, SystemException {
		_logger.info("In importCompetitorData rest call");
		ProductService service = new ProductServiceImpl();
		boolean  isImported = service.importCompetitorData(filePath);
		_logger.info("Exting importCompetitorData rest call");
		return Response.status(Status.OK).entity(isImported).build();
	}

	@POST
	@ApiOperation(value = "Total product count", notes = "Total product count", response = Response.class)
	@ApiResponses({
			@ApiResponse(code = 400, message = "Bad request. Incorrect input", response = ErrorEntity.class),
			@ApiResponse(code = 200, message = "Total product count is  successful.", response = Response.class) })
	@Path("/totalCount")
	@Loggable(Loggable.DEBUG)
	public Response getTotalProductCount(
			@QueryParam("productType") String productType)
			throws InvalidParameterException, SystemException {
		_logger.info("In totalCount rest call");
		ProductService service = new ProductServiceImpl();
		long totalCount = service.getTotalProductCount(productType,null);
		_logger.info("Exiting totalCount rest call");
		return Response.status(Status.OK).entity(totalCount).build();
	}
	
	@GET
	@ApiOperation(value = "Total matched product count", notes = "Total matched product count", response = Response.class)
	@ApiResponses({
			@ApiResponse(code = 400, message = "Bad request. Incorrect input", response = ErrorEntity.class),
			@ApiResponse(code = 200, message = "Total matched product count is  successful.", response = Response.class) })
	@Path("/totalMatchedCount")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Loggable(Loggable.DEBUG)
	public Response getTotalMatchedProductCount()
			throws InvalidParameterException, SystemException {
		_logger.info("In totalMatchedCount rest call");
		ProductService service = new ProductServiceImpl();
		long totalCount = service.getTotalMatchedProductCount(null);
		_logger.info("Exiting totalMatchedCount rest call");
		return Response.status(Status.OK).entity(totalCount).build();
	}
	
	@GET
	@ApiOperation(value = "Total unmatched product count", notes = "Total unmatched product count", response = Response.class)
	@ApiResponses({
			@ApiResponse(code = 400, message = "Bad request. Incorrect input", response = ErrorEntity.class),
			@ApiResponse(code = 200, message = "Total unmatched product count is  successful.", response = Response.class) })
	@Path("/totalUnmatchedCount")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Loggable(Loggable.DEBUG)
	public Response getTotalUnmatchedProductCount()
			throws InvalidParameterException, SystemException {
		_logger.info("In totalUnmatchedCount rest call");
		ProductService service = new ProductServiceImpl();
		long totalCount = service.getTotalUnmatchedProductCount(null);
		_logger.info("Exiting totalUnmatchedCount rest call");
		return Response.status(Status.OK).entity(totalCount).build();
	}
	
	@GET
	@ApiOperation(value = "Total underpriced product count", notes = "Total underpriced product count", response = Response.class)
	@ApiResponses({
			@ApiResponse(code = 400, message = "Bad request. Incorrect input", response = ErrorEntity.class),
			@ApiResponse(code = 200, message = "Total underpriced product count is  successful.", response = Response.class) })
	@Path("/totalUnderpricedCount")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Loggable(Loggable.DEBUG)
	public Response getTotalUnderpricedCount()
			throws InvalidParameterException, SystemException {
		_logger.info("In totalUnderpricedCount rest call");
		ProductService service = new ProductServiceImpl();
		long totalCount = service.getTotalUnderpricedProduct(null);
		_logger.info("Exiting totalUnderpricedCount rest call");
		return Response.status(Status.OK).entity(totalCount).build();
	}
	
	@GET
	@ApiOperation(value = "Total overpriced product count", notes = "Total overpriced product count", response = Response.class)
	@ApiResponses({
			@ApiResponse(code = 400, message = "Bad request. Incorrect input", response = ErrorEntity.class),
			@ApiResponse(code = 200, message = "Total overpriced product count is  successful.", response = Response.class) })
	@Path("/totalOverpricedCount")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Loggable(Loggable.DEBUG)
	public Response getTotalOverpricedCount()
			throws InvalidParameterException, SystemException {
		_logger.info("In totalOverpricedCount rest call");
		ProductService service = new ProductServiceImpl();
		long totalCount = service.getTotalOverpricedProduct(null);
		_logger.info("Exiting totalOverpricedCount rest call");
		return Response.status(Status.OK).entity(totalCount).build();
	}
	
	@GET
	@ApiOperation(value = "Total samepriced product count", notes = "Total samepriced product count", response = Response.class)
	@ApiResponses({
			@ApiResponse(code = 400, message = "Bad request. Incorrect input", response = ErrorEntity.class),
			@ApiResponse(code = 200, message = "Total samepriced product count is  successful.", response = Response.class) })
	@Path("/totalSamepricedCount")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Loggable(Loggable.DEBUG)
	public Response getTotalSamepricedCount()
			throws InvalidParameterException, SystemException {
		_logger.info("In totalSamepricedCount rest call");
		ProductService service = new ProductServiceImpl();
		long totalCount = service.getTotalSamepricedProduct(null);
		_logger.info("Exiting totalSamepricedCount rest call");
		return Response.status(Status.OK).entity(totalCount).build();
	}
	
	@GET
	@ApiOperation(value = "brand wise price details", notes = "brand wise price details", response = BrandDetails.class,  responseContainer = "List")
	@ApiResponses({
			@ApiResponse(code = 400, message = "Bad request. Incorrect input", response = ErrorEntity.class),
			@ApiResponse(code = 200, message = "brand wise price details is  successful.", response = Response.class) })
	@Path("/totalBrandwisePrice")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Loggable(Loggable.DEBUG)
	public Response getBrandwisePrice()
			throws InvalidParameterException, SystemException {
		_logger.info("In totalBrandwisePrice rest call");
		ProductService service = new ProductServiceImpl();
		List<BrandDetails> brandMap = service.getBrandsWisePrice(null);
		_logger.info("Exiting totalBrandwisePrice rest call");
		return Response.status(Status.OK).entity(brandMap).build();
	}
	
	@POST
	@Path("/_redmartFileupload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "upload file by fileUpload", notes = "upload file by fileUpload", response = Response.class)
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content. Incorrect input", response = ErrorEntity.class),
			@ApiResponse(code = 400, message = "Bad Request, Required file not uploaded properly.", response = ErrorEntity.class),
			@ApiResponse(code = 201, message = "upload file by fileUpload", response = Response.class) })
	@Loggable
	public Response doRedmartFileUpload(@FormDataParam("file") InputStream uploadInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		boolean isFileUploaded = true;
		ErrorEntity errorResponse = new ErrorEntity();

		try {
			String content = IOUtils.toString(uploadInputStream, null);
			ProductService service = new ProductServiceImpl();
			service.importRedMartFile(content, "redmart.csv",null);
			
		} catch (Exception e) {
			isFileUploaded = false;
			errorResponse.setCode("500");
			errorResponse.setMessage("ERROR While procesing the JD Document");
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
		}
		return Response.ok().entity(isFileUploaded).build();
	}
	
	@POST
	@Path("/_competitorFileupload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "upload file by fileUpload", notes = "upload file by fileUpload", response = Response.class)
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content. Incorrect input", response = ErrorEntity.class),
			@ApiResponse(code = 400, message = "Bad Request, Required file not uploaded properly.", response = ErrorEntity.class),
			@ApiResponse(code = 201, message = "upload file by fileUpload", response = Response.class) })
	@Loggable
	public Response doCompetitorFileUpload(@FormDataParam("file") InputStream uploadInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		boolean isFileUploaded = true;
		ErrorEntity errorResponse = new ErrorEntity();

		try {
			String content = IOUtils.toString(uploadInputStream, null);
			ProductService service = new ProductServiceImpl();
			service.importCompetitorFile(content, "competitor.csv",null); 
			
		} catch (Exception e) {
			isFileUploaded = false;
			errorResponse.setCode("500");
			errorResponse.setMessage("ERROR While procesing the JD Document");
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
		}
		return Response.ok().entity(isFileUploaded).build();
	}
	

}
