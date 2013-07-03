/*
 * Class to separate OrderController and CartController from UI
 * Stores data needed to save a order to DB
 */
package task.w2122.namedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import task.w2122.entities.Customer;
import task.w2122.entities.Items;
import task.w2122.util.OrderManager;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class SessionBean implements Serializable{
	
	private Customer customer = null;
	
	private String orderDesc;
	private String orderNumber;
	
	private List<OrderManager> order = new ArrayList<OrderManager>();

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<OrderManager> getOrder() {
		return order;
	}
	public void setOrder(List<OrderManager> order) {
		this.order = order;
	}
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
}
