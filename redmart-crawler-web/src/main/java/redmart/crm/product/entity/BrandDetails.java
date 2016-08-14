package redmart.crm.product.entity;

public class BrandDetails {
 
	private String brands;
	private String pricingPosition;
	/**
	 * @return the brands
	 */
	public String getBrands() {
		return brands;
	}
	/**
	 * @param brands the brands to set
	 */
	public void setBrands(String brands) {
		this.brands = brands;
	}
	/**
	 * @return the pricingPosition
	 */
	public String getPricingPosition() {
		return pricingPosition;
	}
	/**
	 * @param pricingPosition the pricingPosition to set
	 */
	public void setPricingPosition(String pricingPosition) {
		this.pricingPosition = pricingPosition;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BrandDetails [brands=" + brands + ", pricingPosition="
				+ pricingPosition + "]";
	}
	
	
}
