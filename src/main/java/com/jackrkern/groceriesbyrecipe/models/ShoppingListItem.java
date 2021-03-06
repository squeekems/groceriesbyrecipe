package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.*;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/* @author "Jack Kern" */

@Entity
@Table(name = SHOPPINGLIST)
public class ShoppingListItem
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shoppingListID;

	@Column
	private int count;

	@OneToOne
	@JoinColumn(name = ITEMID, referencedColumnName = ITEMID)
	private Item item;

	@ManyToOne
	@JoinColumn(name = USERID, nullable = false)
	private User user;

	/*  */
	public ShoppingListItem()
	{}

	/* @param user */
	public ShoppingListItem(User user)
	{
		this();
		this.user = user;
	}

	/* @param item /* @param user */
	public ShoppingListItem(Item item, User user)
	{
		this(user);
		this.item = item;
		this.count = 1;
	}

	/* @param item /* @param user */
	public ShoppingListItem(Item item)
	{
		this(item.getUser());
		this.item = item;
		this.count = 1;
	}

	/* @param count /* @param item /* @param user */
	public ShoppingListItem(int count, Item item, User user)
	{
		this(item, user);
		this.count = count;
	}

	// @return the shoppingListID
	public Long getShoppingListID()
	{ return shoppingListID; }

	// @param shoppingListID the shoppingListID to set
	public void setShoppingListID(Long shoppingListID)
	{ this.shoppingListID = shoppingListID; }

	// @return the count
	public int getCount()
	{ return count; }

	// @param count the count to set
	public void setCount(int count)
	{ this.count = count; }

	// @return the item
	public Item getItem()
	{ return item; }

	// @param item the item to set
	public void setItem(Item item)
	{ this.item = item; }

	// @return the user
	public User getUser()
	{ return user; }

	// @param user the user to set
	public void setUser(User user)
	{ this.user = user; }

	public String toString()
	{
		if (count > 1)
			return item + " (x" + count + ")";
//			return String.format(ENTITY2TOSTRING, cSHOPPINGLISTITEM, item, String.format(COUNTTOSTRING, count));
		else
			return item.toString();
//			return String.format(ENTITYTOSTRING, cSHOPPINGLISTITEM, item);
	}
}