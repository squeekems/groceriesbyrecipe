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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userID;

	@Column
	private String username;

	@Column(nullable = false, unique = true, length = 45)
	private String email;

	@Column(nullable = false, length = 64)
	private String password;

	@Column
	private String lastName;

	@Column
	private String firstName;

	// @return the userID
	public Long getUserID()
	{ return userID; }

	// @param userID the userID to set
	public void setUserID(Long userID)
	{ this.userID = userID; }

	// @return the username
	public String getUsername()
	{ return username; }

	// @param username the username to set
	public void setUsername(String username)
	{ this.username = username; }

	// @return the email
	public String getEmail()
	{ return email; }

	// @param email the email to set
	public void setEmail(String email)
	{ this.email = email; }

	// @return the password
	public String getPassword()
	{ return password; }

	// @param password the password to set
	public void setPassword(String password)
	{ this.password = password; }

	// @return the lastName
	public String getLastName()
	{ return lastName; }

	// @param lastName the lastName to set
	public void setLastName(String lastName)
	{ this.lastName = lastName; }

	// @return the firstName
	public String getFirstName()
	{ return firstName; }

	// @param firstName the firstName to set
	public void setFirstName(String firstName)
	{ this.firstName = firstName; }

	public String toString()
	{
		String fullName;
		if (firstName != null && firstName != "" && !firstName.isEmpty())
		{
			fullName = firstName;
			if (lastName != null && lastName != "" && !lastName.isEmpty())
			{
				fullName += " " + lastName;
			}
		} else if (lastName != null && lastName != "" && !lastName.isEmpty())
			fullName = lastName;
		else if (username != null && username != "" && !username.isEmpty())
			fullName = username;
		else
			fullName = email;
		return fullName;
	}
}