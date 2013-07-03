/*
 * Class to manage the order.
 */
package task.w2122.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import task.w2122.entities.Items;
import task.w2122.entities.Order;
import task.w2122.namedbeans.Messages;
import task.w2122.namedbeans.SessionBean;
import task.w2122.util.OrderManager;

@Named
@RequestScoped
public class OrderController {

	@PersistenceUnit(name="TaskW2122")
	private EntityManagerFactory entityManagerFactory;

	@Resource
	private UserTransaction userTransaction;

	@Inject
	private SessionBean sessionBean;
	
	@Inject 
	private Messages message;

	/*
	 * Check the status of the injected sessionBean and redirects to xhtml-pages
	 */
	public String confirmOrder(){
		String check;
		if(sessionBean.getCustomer()==null){
			message.errorMessage("You have to login");
			check = null;
		}else if(sessionBean.getOrder().isEmpty()){
			message.errorMessage("The cart is empty");
			check = null;
		}else{
			check = "confirmation";
			newOrderNumber();
		}
		return check + "?faces-redirect=true";
	}

	/*
	 * Saves the order arraylist to the DB
	 */
	public String placeOrder(){
		String saveOrder = "cart";
		String errorMessage;
		if(null == sessionBean.getCustomer()){
			errorMessage = "You have to login";
			message.errorMessage(errorMessage);
			saveOrder = null;
		}else {
			EntityManager entityManager = entityManager();
			Order order = new Order();
			Long orderId = newOrderId();
			List<Items> items = new ArrayList<Items>();

			for(OrderManager item : sessionBean.getOrder()){
				items.add(item.getItem());
			}
			if(!items.isEmpty()){
				saveOrder = "shop";
				order.setOrderId(orderId);
				order.setOrderNumber(newOrderNumber());
				order.setOrderDesc(sessionBean.getOrderDesc());
				order.setCustomer(sessionBean.getCustomer());
				order.setItems(items);
				try{
					userTransaction.begin();
					entityManager.persist(order);
					userTransaction.commit();
				}catch (Exception e){

				}
				Query query = entityManager.createQuery("UPDATE OrderItems a SET a.itemQuantity = :qty WHERE " +
						"a.itemId = :itemId AND a.orderId = :orderId");
				for(OrderManager item : sessionBean.getOrder()){
					query.setParameter("qty", item.getQuantity());
					query.setParameter("itemId", item.getItem().getItemId());
					query.setParameter("orderId", orderId);
					try{
						userTransaction.begin();
						query.executeUpdate();
						userTransaction.commit();
					}catch (Exception e){

					}

				}
				sessionBean.getOrder().clear();
				entityManager.close();
			}else{
				errorMessage = "The cart is empty, please add some items.";
				message.errorMessage(errorMessage);
				saveOrder = null;
			}
		}
		sessionBean.setOrderDesc(null);
		return saveOrder + "?faces-redirect=true";
	}

	/*
	 * Generate new orderId
	 */
	private Long newOrderId(){
		Long id = lastOrderId();
		id += 1L;
		return id;
	}

	/*
	 * Generate new orderNumber
	 */
	private String newOrderNumber(){
		String orderNumber;
		orderNumber = "000A" + lastOrderId();
		sessionBean.setOrderNumber(orderNumber);
		return orderNumber;
	}
	/*
	 * creates a entityManager
	 */
	private EntityManager entityManager(){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}
	/*
	 * Redirects to cart.xhtml
	 */
	public String cancel(){
		return "cart?faces-redirect=true";
	}

	/*
	 * Gets the last OrderId from table ORDERS in DB
	 */
	@SuppressWarnings("unchecked")
	private Long lastOrderId() {
		Long id = 0L;
		List<Order> orders;
		Query query = entityManager().createQuery("SELECT a FROM Order a ORDER BY a.orderId DESC");
		query.setMaxResults(1);
		orders = (List<Order>) query.getResultList();

		if(orders.isEmpty()){
			id = 0L;
		}else{
			id = orders.get(0).getOrderId();
		}
		return id;
	}
}