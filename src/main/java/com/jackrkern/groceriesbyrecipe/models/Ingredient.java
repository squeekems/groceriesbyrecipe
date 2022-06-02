package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* @author "Jack Kern" */

@Entity
@Table(name = "ingredients")
public class Ingredient
{
	private int ingredientID;
	private int amountID;
	private int unitID;
	private int itemID;

	@Id
	@Column(name = "ingredientID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getIngredientID()
	{ return ingredientID; }

	// @param ingredientID the ingredientID to set
	public void setIngredientID(int ingredientID)
	{ this.ingredientID = ingredientID; }

	@Column(name = "amountID")
	public int getAmountID()
	{ return amountID; }

	// @param amountID the amountID to set
	public void setAmountID(int amountID)
	{ this.amountID = amountID; }

	@Column(name = "unitID")
	public int getUnitID()
	{ return unitID; }

	// @param unitID the unitID to set
	public void setUnitID(int unitID)
	{ this.unitID = unitID; }

	@Column(name = "itemID")
	public int getItemID()
	{ return itemID; }

	// @param itemID the itemID to set
	public void setItemID(int itemID)
	{ this.itemID = itemID; }

	@Override
	public String toString()
	{
		return "Ingredient [ingredientID="	+ ingredientID + ", amountID=" + amountID + ", unitID=" + unitID
				+ ", itemID=" + itemID + "]";
	}
}