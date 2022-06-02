package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* @author "Jack Kern" */

@Entity
@Table(name = "items")
public class Item
{
	private int itemID;
	private String description;
	private String aisle;
	private int userID;

	@Id
	@Column(name = "itemID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getItemID()
	{ return itemID; }

	// @param itemID the itemID to set
	public void setItemID(int itemID)
	{ this.itemID = itemID; }

	@Column(name = "description")
	public String getDescription()
	{ return description; }

	// @param description the description to set
	public void setDescription(String description)
	{ this.description = description; }

	@Column(name = "aisle")
	public String getAisle()
	{ return aisle; }

	// @param aisle the aisle to set
	public void setAisle(String aisle)
	{ this.aisle = aisle; }

	@Column(name = "userID")
	public int getUserID()
	{ return userID; }

	// @param userID the userID to set
	public void setUserID(int userID)
	{ this.userID = userID; }

	@Override
	public String toString()
	{
		return "Item [itemID="	+ itemID + ", description=" + description + ", aisle=" + aisle + ", userID=" + userID
				+ "]";
	}
}