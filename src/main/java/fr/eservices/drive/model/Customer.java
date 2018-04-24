package fr.eservices.drive.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Customer {
	@Id
	@GeneratedValue
	private Long id;
	private String 
	login,
	firstName,
	lastName,
	email,
	password;
	@OneToOne
	private Cart activeCart;
	
	public Cart getActiveCart() {
		return activeCart;
	}
	public String getEmail() {
		return email;
	}
	public String getFirstName() {
		return firstName;
	}
	public Long getId() {
		return id;
	}
	public String getLastName() {
		return lastName;
	}
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	public void setActiveCart(Cart activeCart) {
		this.activeCart = activeCart;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
