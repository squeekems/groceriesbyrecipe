package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/* @author "Jack Kern" */

@Entity
@Table(name = "list")
public class ShoppingList
{
	private int listID;
	private int count;
	private Item item;
	private User user;

	public ShoppingList()
	{}

	@Id
	@Column(name = "listID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getListID()
	{ return listID; }

	// @param listID the listID to set
	public void setListID(int listID)
	{ this.listID = listID; }

	@Column(name = "count")
	public int getCount()
	{ return count; }

	// @param count the count to set
	public void setCount(int count)
	{ this.count = count; }

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itemID", referencedColumnName = "itemID")
	public Item getItem()
	{ return item; }

	// @param item the item to set
	public void setItem(Item item)
	{ this.item = item; }

	@ManyToOne
	@JoinColumn(name = "userID", nullable = false)
	public User getUser()
	{ return user; }

	// @param user the user to set
	public void setUser(User user)
	{ this.user = user; }

	@Override
	public String toString()
	{
		return "ShoppingList [listID=" + listID + ", count=" + count + ", item=" + item + ", user=" + user + "]";
	}
}