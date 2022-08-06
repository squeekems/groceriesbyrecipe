package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.*;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/* @author "Jack Kern" */

@Entity
@Table(name = TABLENAMEITEM)
public class Item
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemID;

	@Column
	private String description;

	@OneToOne
	@JoinColumn(name = AISLEID, referencedColumnName = AISLEID)
	private Aisle aisle;

	@ManyToOne
	@JoinColumn(name = USERID, nullable = false)
	private User user;

	public Item()
	{}

	public Item(String description, Aisle aisle, User user)
	{
		this();
		this.description = description;
		this.aisle = aisle;
		this.user = user;
	}

	// @return the itemID
	public Long getItemID()
	{ return itemID; }

	// @param itemID the itemID to set
	public void setItemID(Long itemID)
	{ this.itemID = itemID; }

	// @return the description
	public String getDescription()
	{ return description; }

	// @param description the description to set
	public void setDescription(String description)
	{ this.description = description.trim(); }

	// @return the aisle
	public Aisle getAisle()
	{ return aisle; }

	// @param aisle the aisle to set
	public void setAisle(Aisle aisle)
	{ this.aisle = aisle; }

	// @return the user
	public User getUser()
	{ return user; }

	// @param user the user to set
	public void setUser(User user)
	{ this.user = user; }

	public String toDetailedString()
	{
		return user + "'s Item [itemID=" + itemID + ", description=" + description + ", aisle=" + aisle + "]";
	}

	@Override
	public String toString()
	{
		return description;
	}
}