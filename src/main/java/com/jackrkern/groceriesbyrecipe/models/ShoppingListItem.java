package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/* @author "Jack Kern" */

@Data
@Entity
@Table(name = "shoppingList")
public class ShoppingListItem
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shoppingListID;

	@Column
	private int count;

	@OneToOne
	@JoinColumn(name = "itemID", referencedColumnName = "itemID")
	private Item item;

	@ManyToOne
	@JoinColumn(name = "userID", nullable = false)
	private User user;

	public String toString()
	{
		if (count > 1)
			return item.getDescription() + " (x" + count + ")";
		else
			return item.getDescription();
	}
}