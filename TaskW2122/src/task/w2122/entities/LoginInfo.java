/*
 * Class that represent LOGIN_INFO table in the DB
 */
package task.w2122.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="login_info")
public class LoginInfo implements Serializable{

	@Id
	@Column(name="LOGIN_INFO_ID")
	private Long loginInfoId;
	
	@Column(name="LOGIN_NAME")
	private String loginName;
	
	private String password;
	
	@OneToOne
	@JoinColumn(name="CUSTOMER_ID")
	private Customer customer;

	public Long getLoginInfoId() {
		return loginInfoId;
	}

	public void setLoginInfoId(Long loginInfoId) {
		this.loginInfoId = loginInfoId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
