package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/* @author "Jack Kern" */

@Entity
@Table(name = "aisles")
public class Aisle
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long aisleID;

	@Column
	private String name;

	@ManyToOne
	@JoinColumn(name = "userID", nullable = false)
	private User user;

	// @return the aisleID
	public Long getAisleID()
	{ return aisleID; }

	// @param aisleID the aisleID to set
	public void setAisleID(Long aisleID)
	{ this.aisleID = aisleID; }

	// @return the name
	public String getName()
	{ return name; }

	// @param name the name to set
	public void setName(String name)
	{ this.name = name; }

	// @return the user
	public User getUser()
	{ return user; }

	// @param user the user to set
	public void setUser(User user)
	{ this.user = user; }

	public int compareTo(Aisle aisle)
	{
		return name.compareTo(aisle.getName());
	}

	public String toString()
	{
		return name;
	}
}