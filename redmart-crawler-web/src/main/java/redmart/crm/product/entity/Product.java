package redmart.crm.product.entity;

import redmart.crm.product.rest.entities.SingleEntity;

/**
 * @author dipankar.saha
 *
 */
public class Product extends SingleEntity  {
	
	private Integer id;
	private String title;
	private Double price;
	private Double promotePrice;
	private String brand;
	private String categoryName1;
    private String categoryName2;
    
    
    
	/**
	 * 
	 */
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param productId
	 * @param title
	 * @param price
	 * @param promotePrice
	 * @param brand
	 * @param categoryName1
	 * @param categoryName2
	 */
	public Product(Integer id, String title, Double price,
			Double promotePrice, String brand, String categoryName1,
			String categoryName2) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.promotePrice = promotePrice;
		this.brand = brand;
		this.categoryName1 = categoryName1;
		this.categoryName2 = categoryName2;
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
	public double getPrice() {
		return price;
	}



	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}



	/**
	 * @return the promotePrice
	 */
	public double getPromotePrice() {
		return promotePrice;
	}



	/**
	 * @param promotePrice the promotePrice to set
	 */
	public void setPromotePrice(double promotePrice) {
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



	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title
				+ ", price=" + price + ", promotePrice=" + promotePrice
				+ ", brand=" + brand + ", categoryName1=" + categoryName1
				+ ", categoryName2=" + categoryName2 + "]";
	}
    
    
    
    
    

}
