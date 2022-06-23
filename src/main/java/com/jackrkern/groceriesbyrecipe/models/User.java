package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/* @author "Jack Kern" */

@Data
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

	public String toString()
	{
		String fullName;
		if (firstName != null)
		{
			fullName = firstName;
			if (lastName != null)
			{
				fullName += " " + lastName;
			}
		} else if (lastName != null)
			fullName = lastName;
		else if (username != null)
			fullName = username;
		else
			fullName = email;
		return fullName;
	}
}