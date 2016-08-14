package redmart.crm.product.mongo.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
*
* @author dipankar.saha
*/
@Entity("product")
public class Product {
	
	@Id
	private String productId;
	private Integer id;
	private String title;
	private Double price;
	private Double promotePrice;
	private String brand;
	private String categoryName1;
    private String categoryName2;
	
	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * @return the promotePrice
	 */
	public Double getPromotePrice() {
		return promotePrice;
	}
	/**
	 * @param promotePrice the promotePrice to set
	 */
	public void setPromotePrice(Double promotePrice) {
		this.promotePrice = promotePrice;
	}
	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * @return the categoryName1
	 */
	public String getCategoryName1() {
		return categoryName1;
	}
	/**
	 * @param categoryName1 the categoryName1 to set
	 */
	public void setCategoryName1(String categoryName1) {
		this.categoryName1 = categoryName1;
	}
	/**
	 * @return the categoryName2
	 */
	public String getCategoryName2() {
		return categoryName2;
	}
	/**
	 * @param categoryName2 the categoryName2 to set
	 */
	public void setCategoryName2(String categoryName2) {
		this.categoryName2 = categoryName2;
	}
    
    
    

}
