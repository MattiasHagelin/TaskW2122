/*
 * Class to manage the cart
 */
package task.w2122.controllers;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.inject.Inject;
import javax.inject.Named;

import task.w2122.namedbeans.Messages;
import task.w2122.namedbeans.SessionBean;
import task.w2122.util.OrderManager;

@Named
@RequestScoped
public class CartController {

	private HtmlDataTable itemDataTable;
	private Long quantity;

	@Inject
	private SessionBean sessionBean;
	
	@Inject
	private Messages message;

	/* 
	 * Add new item to the order arrylist or alter the quantity if the
	 * item already exists in the arraylist.
	 */
	public String addToCart(){
		String addItem = null;
		OrderManager item = (OrderManager) itemDataTable.getRowData();
		if(order().isEmpty()){
			if(null != item.getQuantity()){
				order().add(new OrderManager(item.getItem(), item.getQuantity()));
			}else{
				message.errorMessage("Nothing was added to the cart, please add a quantity");
				addItem = null;
			}
		}else{
			boolean inOrder = false;
			for(OrderManager items : order()){
				if(item.getItem().getItemId() == items.getItem().getItemId()){
					Long newQuantity = null;
					try{
						if(null != item.getQuantity()){
							newQuantity = (items.getQuantity() + item.getQuantity());
							items.setQuantity(newQuantity);
						}else{
							message.errorMessage("Nothing was added to the cart, please add a quantity");
							addItem = null;
						}
					}catch (NullPointerException e){
					}
					inOrder = true;
					break;
				}
			}
			if(!inOrder){
				if(null != item.getQuantity()){
					order().add(new OrderManager(item.getItem(), item.getQuantity()));
				}else{
					message.errorMessage("Nothing was added to the cart, please add a quantity");
					addItem = null;
				}
			}
		}
		item.setQuantity(null);
		return addItem;
	}

	/*
	 * Removes all items from the order arraylist.
	 */
	public String clearCart(){
		order().clear();
		return "cart";
	}

	public List<OrderManager> getCart(){
		return sessionBean.getOrder();
	}
	public void placeOrder(){
	}

	public HtmlDataTable getItemDataTable() {
		return itemDataTable;
	}

	public void setItemDataTable(HtmlDataTable itemDataTable) {
		this.itemDataTable = itemDataTable;
	}

	/*
	 * Alter the quantity of a item or remove the item from the 
	 * order arraylist if quantity is 0 or less.
	 */
	public String editCart(){
		OrderManager orderManager = (OrderManager) itemDataTable.getRowData();
		for(OrderManager item : order()){
			if(orderManager.getItem().getItemId() == item.getItem().getItemId()){
				Long newQty = item.getQuantity();
				item.setQuantity(newQty);
				if(newQty <= 0){
					order().remove(item);
				}
				break;
			}
		}
		return "cart";
	}

	/*
	 * Sums up the quantity of all items in the order arraylist.
	 */
	public String cartStatus(){
		Long totalQty = 0L;
		String cartStatus;
		for(OrderManager item : order()){
			try{
				totalQty += item.getQuantity();
			}catch (NullPointerException e){

			}
		}
		if(totalQty == 1L){
			cartStatus = totalQty + " item in cart";
		}else{
			cartStatus = totalQty + " items in cart";
		}
		return cartStatus;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	/*
	 * Get the order arraylist from the injected sessionBean
	 */
	private List<OrderManager> order(){
		return sessionBean.getOrder();
	}

}
