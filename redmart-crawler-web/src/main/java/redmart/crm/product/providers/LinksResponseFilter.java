/**
 * 
 */
package redmart.crm.product.providers;

import java.net.URI;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.Provider;

import redmart.crm.product.entity.Product;
import redmart.crm.product.rest.entities.CollectionEntity;
import redmart.crm.product.rest.entities.Link;
import redmart.crm.product.rest.entities.SingleEntity;
import redmart.crm.product.rest.entities.V1;
import redmart.crm.product.rest.entities.Link.LinkType;


/**
 * This servlet response filter is responsible for setting links(for HATEOAS) in the
 * response.
 * 
 * @author dipankar.saha
 *
 */
@Provider
@SuppressWarnings("rawtypes")
public class LinksResponseFilter implements ContainerResponseFilter {

	private static final String PRODUCT = "Product";

	/**
	 * @see javax.ws.rs.container.ContainerResponseFilter#filter(ContainerRequestContext, ContainerResponseContext)
	 */
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {

        Object entity = responseContext.getEntity();

        if (entity != null) {
            UriBuilder uriBuilder = requestContext.getUriInfo().getAbsolutePathBuilder();
            MultivaluedMap<String, String> map = requestContext.getUriInfo().getQueryParameters();
            if (entity instanceof SingleEntity) {
                String method = requestContext.getMethod();
                ItemType itemType = method.equalsIgnoreCase("POST") ? ItemType.NEW_ITEM : ItemType.SINGLE_ITEM;
                setResourceLinks((SingleEntity) entity, uriBuilder, requestContext, responseContext, itemType);
            } else if (entity instanceof CollectionEntity) {
                setResourceCollectionLinks((CollectionEntity) entity, uriBuilder, requestContext, responseContext,map);
            } else if (entity instanceof V1) {
                setV1Links((V1) entity, uriBuilder);
            }
        }
    }

    private void setV1Links(V1 entity, UriBuilder uriBuilder) {

        UriBuilder ub = uriBuilder.clone();
        Link link = new Link(LinkType.SELF, ub.build().toString());

        ub = uriBuilder.clone();
        Link jobForV1Link = new Link(LinkType.SELF, ub.path("configs").build().toString());

        ((V1) entity).getProduct().addLink(jobForV1Link);
        ((V1) entity).setLink(link);
    }

    /**
     * This helper method helps in setting the links for the collection
     * entities.
     * 
     * @param entity
     * @param uriBuilder
     * @param containerRequest
     * @param containerResponse
     */
    private void setResourceCollectionLinks(CollectionEntity entity, UriBuilder uriBuilder, ContainerRequestContext requestContext,
            ContainerResponseContext responseContext, MultivaluedMap<String, String> map) {

        UriBuilder ub = uriBuilder.clone();
        String link = ub.build().toString();
        if(map.size()>0){
        	link=link+"?";
        	for (MultivaluedMap.Entry<String, List<String>> entry : map.entrySet()) {
        		if(entry.getValue()!=null && !entry.getValue().isEmpty()){
        			link=link+entry.getKey()+"="+entry.getValue().get(0)+"&";
        		}
        	}
        	link = link.substring(0, link.length()-1);
        }
        Link selfLink = new Link(LinkType.SELF, link);
        entity.addLink(selfLink);

        Collection<?> items = entity.getItems();
        for (Object resource : items) {
            if (resource instanceof SingleEntity) {
                setResourceLinks((SingleEntity) resource, uriBuilder, requestContext, responseContext, ItemType.COLLECTION_ITEM);
            }
        }
    }

    /**
     * This helper method helps in setting the links for the entity that needs
     * to be returned in the response
     * 
     * @param entity
     * @param uriBuilder
     * @param containerRequest
     * @param containerResponse
     * @param itemType
     */
	private void setResourceLinks(SingleEntity entity, UriBuilder uriBuilder, ContainerRequestContext requestContext,
            ContainerResponseContext responseContext, ItemType itemType) {

        UriBuilder ub = uriBuilder.clone();
        URI linkURI = null;
        String link = null;
        switch (itemType) {
        case NEW_ITEM:
        	if (entity instanceof Product) {
                ub.path(new String(((Product) entity).getId().toString()));
            }
        	linkURI = ub.build();
        	String product = linkURI.toASCIIString();
        	
            if (responseContext.getHeaders() != null) {
                responseContext.getHeaders().add(PRODUCT, product);
            }
        	link = linkURI.toString();
            break;
        case COLLECTION_ITEM:
        	linkURI = ub.build();
        	String linkStr = linkURI.toASCIIString();
        	int index = linkStr.lastIndexOf("/");
        	link = linkStr.substring(0, index + 1);
            if (entity instanceof Product) {
            	index = linkStr.substring(0, index-1).lastIndexOf("/");
            	link = linkStr.substring(0, index + 1);
            	link += "/product/"+((Product) entity).getId();
            }
            break;
        case SINGLE_ITEM:
        	linkURI = ub.build();
        	link = linkURI.toString();
        default:
            // no-op
            break;
        }

        Link selfLink = new Link(LinkType.SELF, link);
        entity.addLink(selfLink);
    }

    /**
     * This identifies the type of the entity returned in the response. Could be
     * a single, collection or a new entity
     * 
     * @author dipankar.saha
     */
    private enum ItemType {
        NEW_ITEM, COLLECTION_ITEM, SINGLE_ITEM
    }
}
