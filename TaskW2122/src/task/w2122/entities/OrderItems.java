/*
 * Class to represent table ORDER_ITEMS in DB
 */
package task.w2122.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import task.w2122.compositekeys.OrderItemPK;

@SuppressWarnings("serial")
@Entity
@Table(name="order_items")
@IdClass(value = OrderItemPK.class)
public class OrderItems implements Serializable{
	
	@Id
	@Column(name="ORDER_ID")
	private Long orderId;
	
	@Id
	@Column(name="ITEM_ID")
	private Long itemId;
	
	@Column(name="ITEM_QTY")
	private Long itemQuantity;
	
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(Long itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

}
