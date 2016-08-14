/**
 * 
 */
package redmart.crm.product.rest.entities;


import redmart.crm.product.entity.Product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * This class gives information about the resources available under the version 'V1'
 * @author dipankar.saha
 *
 */
@JsonPropertyOrder({ "product", "link" })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class V1 {

	Product product;
	Link link;
	

	
	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
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
