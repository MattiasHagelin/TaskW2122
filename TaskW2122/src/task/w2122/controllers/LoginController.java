/*
 * Class to manage login and logout
 */
package task.w2122.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import task.w2122.entities.LoginInfo;
import task.w2122.namedbeans.LoginBean;
import task.w2122.namedbeans.Messages;
import task.w2122.namedbeans.SessionBean;

@Named
@RequestScoped
public class LoginController {


	@PersistenceUnit(name="TaskW2122")
	private EntityManagerFactory entityManagerFactory;
	
	@Inject
	private Messages message;
	
	@Inject
	private SessionBean sessionBean;

	@Inject
	private LoginBean loginBean;

	/*
	 * Match given username with DB, compares password for that user and assign customer to the injected sessionBean
	 */
	public String validate(){
		String errorMessage = "Invalid Username or Password";
		String check = "login?faces-redirect=true";
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createQuery("SELECT a FROM LoginInfo a WHERE a.loginName LIKE :name");
		query.setParameter("name", loginBean.getUsername());
		LoginInfo loginInfo = null;
		try{
			loginInfo = (LoginInfo) query.getSingleResult();
			if(loginInfo.getPassword().equals(loginBean.getPassword())){
				sessionBean.setCustomer(loginInfo.getCustomer());
				loginBean.setPassword(null);
				loginBean.setUsername(null);
				loginInfo = null;
				if(sessionBean.getOrder().isEmpty()){
					check = "shop?faces-redirect=true";
				}else{
					check = "confirmation?faces-redirect=true";
				}
			}else{
				message.errorMessage(errorMessage);
				check = nullSetter();
			}
		}catch ( NoResultException e){
			message.errorMessage(errorMessage);
			check = nullSetter();
		}catch (NullPointerException e){
			message.errorMessage(errorMessage);
			check = nullSetter();
		}
		entityManager.close();
		return check;
	}
	/*
	 * Sets loginBean to null and returns null
	 */
	private String nullSetter() {
		loginBean.setPassword(null);
		loginBean.setUsername(null);
		return null;
	}
	/*
	 * The text displayed on the Login/Logout link
	 */
	public String loginOrLogout(){
		String lOrL;
		if(sessionBean.getCustomer() != null){
			lOrL = "Logout";
		}else{
			lOrL = "Login";
		}
		return lOrL;
	}
	/*
	 * Check if logged in and changes where the Login/Logout link redirects to.
	 */
	public String loggedinStatus(){
		String status;
		if(sessionBean.getCustomer() != null){
			sessionBean.setCustomer(null);
			sessionBean.getOrder().clear();
			status = "shop";
		} else {
			status = "login";
		}

		return status + "?faces-redirect=true";

	}

}
