package redmart.crm.product.rest.entities;

/**
 * This class represents a particular version of a Job Management API
 * @author dipankar.saha
 *
 */
public class Version {
	
	private String name;
	private Link link;
	
	/**
	 * @return the version
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param version the version to set
	 */
	public void setName(String version) {
		this.name = version;
	}
	/**
	 * @return the link
	 */
	public Link getLink() {
		return link;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(Link link) {
		this.link = link;
	}
	
}
