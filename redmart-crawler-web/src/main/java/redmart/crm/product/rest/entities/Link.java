package redmart.crm.product.rest.entities;

/**
 * This class represents the information about a resource through hyperlink(s).
 * The Link entity would enable us to leverage the HATEOAS(Hypermedia As The Engine Of Application State) principle of REST
 * @author dipankar.saha
 */
public class Link {
	
	/**
	 * Represents the type of the link. Could be self, canonical, status
	 */
	private LinkType rel;
	/**
	 * Represents the URL of the info. about the resource
	 */
	private String href;
	
	public Link() { }
	
	/**
	 * Create a new link with rel and href
	 * @param rel the type of the link
	 * @param href the link URL
	 */
	public Link(LinkType rel, String href) {
		this.rel = rel;
		this.href = href;
	}
	
	/**
	 * @return the type of the link
	 */
	public LinkType getRel() {
		return rel;
	}

	/**
	 * @param rel the type of the link to set
	 */
	public void setRel(LinkType rel) {
		this.rel = rel;
	}

	/**
	 * @return the link URL
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href the link URL to set
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * Represents the type of the link
	 * @author karviswa
	 *
	 */
	public enum LinkType {
		/**
		 * The link points to self
		 */
		SELF, 
		
		/**
		 * The link is canonical
		 */
		CANONICAL, 
		
		//STATUS, 
		
		/**
		 * The link points to the next page of resources
		 */
		NEXT, 
		
		/**
		 * The link points to the previous page of resources
		 */
		PREV;
		
		/**
		 * To convert from enum to its lowercase string equivalent
		 */
		public String toString() {
			switch(this) {
				case SELF: 
					return "self";
				case CANONICAL:
					return "canonical";
//				case STATUS:
//					return "status";
				case NEXT:
					return "next";
				case PREV:
					return "prev";
				default: return name();
			}
		};
		
		/**
		 * To convert from enum to string for json representation
		 * @return lowercase value of the enum value
		 */
		// @JsonValue
		public String toJson() {
			return name().toLowerCase();
		}
		
		/**
		 * To convert from url param value to enum 
		 * @param text
		 * @return enum value of the represented string
		 */
		// @JsonCreator
		public static LinkType fromString(String text) {
			return valueOf(text.toUpperCase());
		}
	}

}
