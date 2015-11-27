package dk.nd.iam.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	
	@Column(name ="USERNAME", unique=true)
	private String userName;
	
	@Column(name = "PASSWORD")
	private String password;

	/*
	 * No arg constructor
	 */
	public User(){
	}
	
	/*
	 * @param username name of user
	 * @param password password for the user
	 */
	public User(String username, String password) {
		this.userName = username;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return userName;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
}
