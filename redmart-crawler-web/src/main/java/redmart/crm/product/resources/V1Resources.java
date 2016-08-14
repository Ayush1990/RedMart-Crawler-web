/**
 * 
 */
package redmart.crm.product.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import redmart.crm.product.entity.Product;
import redmart.crm.product.rest.entities.V1;



/**
 * This class represents the version 1 of Product Management
 * @author dipankar.saha
 *
 */
public class V1Resources {

	//private static Logger logger = LoggerFactory.getLogger(V1Resources.class);
	@Context 
	private UriInfo uriInfo;
	/**
	 * Retrieves all the available resources under Product Manager API Version 1.
	 * @return  available resources in API version 1
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public V1 getResources() {
		Product product = new Product();
		V1 v1 = new V1();
		v1.setProduct(product);
		return v1;
	}
	
	/**
	 * Get the handle to {@link ProductResource}
	 * @param resourceContext
	 * @return {@link ProductResource}
	 */
	@Path("product")
	public ProductResource getProductResource(@Context ResourceContext resourceContext) {
		return resourceContext.getResource(ProductResource.class);
	}
}
