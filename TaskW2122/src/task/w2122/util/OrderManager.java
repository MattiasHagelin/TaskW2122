/*
 * Class to add quantity to items from the DB
 */
package task.w2122.util;

import task.w2122.entities.Items;

public class OrderManager {
	
	private Items item;
	private Long quantity;
	
	public OrderManager(){
		
	}
	
	public OrderManager(Items item, Long quantity){
		setItem(item);
		setQuantity(quantity);
	}

	public Long getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Items getItem() {
		return item;
	}

	public void setItem(Items item) {
		this.item = item;
	}

}
