package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* @author "Jack Kern" */

@Entity
@Table(name = "users")
public class User
{
	private int userID;
	private String username;
	private String password;
	private String lastName;
	private String firstName;

	@Id
	@Column(name = "userID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getUserID()
	{ return userID; }

	// @param userID the userID to set
	public void setUserID(int userID)
	{ this.userID = userID; }

	@Column(name = "username")
	public String getUsername()
	{ return username; }

	// @param username the username to set
	public void setUsername(String username)
	{ this.username = username; }

	@Column(name = "password")
	public String getPassword()
	{ return password; }

	// @param password the password to set
	public void setPassword(String password)
	{ this.password = password; }

	@Column(name = "lastName")
	public String getLastName()
	{ return lastName; }

	// @param lastName the lastName to set
	public void setLastName(String lastName)
	{ this.lastName = lastName; }

	@Column(name = "firstName")
	public String getFirstName()
	{ return firstName; }

	// @param firstName the firstName to set
	public void setFirstName(String firstName)
	{ this.firstName = firstName; }

	@Override
	public String toString()
	{
		return "User [userID="	+ userID + ", username=" + username + ", password=" + password + ", lastName=" + lastName
				+ ", firstName=" + firstName + "]";
	}
}