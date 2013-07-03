/*
 * Class to separate ItemController from UI
 */
package task.w2122.namedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import task.w2122.controllers.ItemController;
import task.w2122.entities.Items;
import task.w2122.util.OrderManager;

@Named
@RequestScoped
public class ItemBean {
	
	@Inject
	private ItemController itemController;
	
	private List<OrderManager> orderManager = new ArrayList<OrderManager>();
	
	/*
	 * Gets items from the injected itemController and puts them in a orderManager List to add quantity
	 */
	public List<OrderManager> getItems(){
		if(orderManager.isEmpty()){
			for(Items item : itemController.getItems()){
				orderManager.add(new OrderManager(item, null));
			}
		}
		return orderManager;
	}

}
