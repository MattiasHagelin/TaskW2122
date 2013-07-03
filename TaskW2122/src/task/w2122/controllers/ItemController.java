/*
 * Class to manage the items from the DB
 */
package task.w2122.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import task.w2122.entities.Items;

@Named
@ApplicationScoped
public class ItemController {

	private List <Items> items = null;

	@PersistenceUnit(name="TaskW2122")
	private EntityManagerFactory entityManagerFactory;

	public ItemController() {
	}

	public List<Items> getItems(){
		return items;
	}

	/*
	 * Fills the list items with data from DB when the application is started
	 */
	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init(){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("SELECT a FROM Items a");
		items = (List<Items>)query.getResultList();
		entityManager.close();
	}
}