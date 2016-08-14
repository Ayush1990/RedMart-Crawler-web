package redmart.crm.product.rest.entities;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * This represents a collection of entities such as {@link Location}.
 * @author dipankar.saha
 *
 * @param <T> Entity
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "totalResults", "hasMore", "items", "links" })
public class CollectionEntity<T> extends BaseEntity {

	
	private Collection<T> items;
	private boolean hasMore;
	private int totalResults;

	/**
	 * @return the items
	 */
	public Collection<T> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(Collection<T> items) {
		this.items = items;
	}
	/**
	 * Add a new item to the collection
	 * @param item
	 */
	public void addItem(T item) {
		
		if(item != null) {
			if(items == null) {
				items = new ArrayList<T>();
			}
			items.add(item);
		}
	}
	/**
	 * @return the hasMore
	 */
	public boolean isHasMore() {
		return hasMore;
	}
	/**
	 * @param hasMore the hasMore to set
	 */
	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}
	/**
	 * @return the totalResults
	 */
	public int getTotalResults() {
		return totalResults;
	}
	/**
	 * @param totalResults the totalResults to set
	 */
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
	
}
