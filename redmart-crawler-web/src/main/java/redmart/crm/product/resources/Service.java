package redmart.crm.product.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import redmart.crm.product.rest.entities.CollectionEntity;
import redmart.crm.product.rest.entities.Link;
import redmart.crm.product.rest.entities.Version;
import redmart.crm.product.rest.entities.Link.LinkType;




@Path("/")
public class Service {
	
	@Context private UriInfo uriInfo;
	private static final int VERSIONS_COUNT = 1;
	/**
	 * Retrieve all versions of the scheduler API.
	 * @return list of versions
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CollectionEntity<Version> getVersions() {
		
		List<Version> versions = new ArrayList<Version>();
		String versionNumber = null;
		Version version = null;
		
		for(int i=1; i<=VERSIONS_COUNT; i++) {
			versionNumber = "v" + i;
			Link link = new Link(LinkType.CANONICAL, uriInfo.getAbsolutePathBuilder().path(versionNumber).build().toString());
			version = new Version();
			version.setName(versionNumber);
			version.setLink(link);
			versions.add(version);
		}
		CollectionEntity<Version> versionsCollection = new CollectionEntity<Version>();
		versionsCollection.setItems(versions);
		return versionsCollection;
	}
	/**
	 * Retrieve all resources available for scheduler API version 1.
	 * @return resources
	 */
	@Path("v1")
	public V1Resources getServiceV1(@Context ResourceContext resourceContext) {
		return resourceContext.getResource(V1Resources.class);
	}
}
