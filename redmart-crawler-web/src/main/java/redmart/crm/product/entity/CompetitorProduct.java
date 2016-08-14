package redmart.crm.product.entity;

/**
 * @author dipankar.saha
 *
 */
public class CompetitorProduct {
	
	private String title;
	private Integer matchedProductId;
	private String brand;
	private Boolean inStock;
	private Double price;
	private Boolean onPromo;
	private String promoPrice;
	private String categoryName1;
	private String categoryName2;
	/**
	 * 
	 */
	public CompetitorProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param title
	 * @param matchedProductId
	 * @param brand
	 * @param inStock
	 * @param price
	 * @param onPromo
	 * @param promoPrice
	 * @param categoryName1
	 * @param categoryName2
	 */
	public CompetitorProduct(String title, Integer matchedProductId,
			String brand, Boolean inStock, Double price, Boolean onPromo,
			String promoPrice, String categoryName1, String categoryName2) {
		super();
		this.title = title;
		this.matchedProductId = matchedProductId;
		this.brand = brand;
		this.inStock = inStock;
		this.price = price;
		this.onPromo = onPromo;
		this.promoPrice = promoPrice;
		this.categoryName1 = categoryName1;
		this.categoryName2 = categoryName2;
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
	 * @return the matchedProductId
	 */
	public Integer getMatchedProductId() {
		return matchedProductId;
	}
	/**
	 * @param matchedProductId the matchedProductId to set
	 */
	public void setMatchedProductId(Integer matchedProductId) {
		this.matchedProductId = matchedProductId;
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
	 * @return the inStock
	 */
	public Boolean getInStock() {
		return inStock;
	}
	/**
	 * @param inStock the inStock to set
	 */
	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
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
	 * @return the onPromo
	 */
	public Boolean getOnPromo() {
		return onPromo;
	}
	/**
	 * @param onPromo the onPromo to set
	 */
	public void setOnPromo(Boolean onPromo) {
		this.onPromo = onPromo;
	}
	/**
	 * @return the promoPrice
	 */
	public String getPromoPrice() {
		return promoPrice;
	}
	/**
	 * @param promoPrice the promoPrice to set
	 */
	public void setPromoPrice(String promoPrice) {
		this.promoPrice = promoPrice;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompetitorProduct [title=" + title + ", matchedProductId="
				+ matchedProductId + ", brand=" + brand + ", inStock="
				+ inStock + ", price=" + price + ", onPromo=" + onPromo
				+ ", promoPrice=" + promoPrice + ", categoryName1="
				+ categoryName1 + ", categoryName2=" + categoryName2 + "]";
	}
	
	
	
	
	

}
