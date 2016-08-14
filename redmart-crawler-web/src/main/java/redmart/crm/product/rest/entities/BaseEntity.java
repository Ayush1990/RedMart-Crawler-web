package redmart.crm.product.rest.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the super-set of all resources.
 * @author dipankar.saha
 *
 */
public class BaseEntity {
	
	/**
	 * Links for HATEOAS. Types of links supported are canonical and rel
	 */
	private List<Link> links;

	/**
	 * @return the links
	 */
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	/**
	 * @param link the link to set
	 */
	public void addLink(Link link) {
		
		if(link != null) {
			if(links == null) {
				links = new ArrayList<Link>();
			}
			links.add(link);
		}
	}
	
}
