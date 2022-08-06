package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.*;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/* @author "Jack Kern" */

@Entity
@Table(name = TABLENAMEAISLES)
public class Aisle
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long aisleID;

	@Column
	private String name;

	@ManyToOne
	@JoinColumn(name = USERID, nullable = false)
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
	{ this.name = name.trim(); }

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

	public String toDetailedString()
	{
		return user + "'s Aisle [aisleID=" + aisleID + ", name=" + name + "]";
	}

	@Override
	public String toString()
	{
		return name;
	}
}