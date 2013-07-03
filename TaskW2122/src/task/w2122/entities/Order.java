/*
 * Class to represent table ORDERS in DB
 */
package task.w2122.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name="orders")
public class Order implements Serializable{

	@Id
	@Column(name="ORDER_ID")
	private Long orderId;
	
	@Column(name="ORDER_NUMBER")
	private String orderNumber;
	
	@Column(name="ORDER_DESCRIPTION")
	private String orderDesc;
	
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private Customer customer;
	
	@ManyToMany
	@JoinTable(name="ORDER_ITEMS", joinColumns=@JoinColumn(name="ORDER_ID", referencedColumnName="ORDER_ID"),
	inverseJoinColumns=@JoinColumn(name="ITEM_ID", referencedColumnName="ITEM_ID"))
	private Collection<Items> items;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collection<Items> getItems() {
		return items;
	}

	public void setItems(Collection<Items> items) {
		this.items = items;
	}

}
