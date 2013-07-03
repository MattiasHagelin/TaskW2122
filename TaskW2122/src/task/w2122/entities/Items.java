/*
 * Class that represent the ITEMS table in the DB
 */
package task.w2122.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@SuppressWarnings("serial")
@Entity
public class Items implements Serializable{

	@Id
	@Column(name="ITEM_ID")
	private long itemId;
	
	@Column(name="ITEM_NUMBER")
	private String itemNumber;
	
	@Column(name="ITEM_SHORT_DESC")
	private String shortDesc;
	
	@Column(name="ITEM_LONG_DESC")
	private String longDesc;
	
	@ManyToMany(mappedBy="items")
	private Collection<Order> orders;
	
	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}

}
