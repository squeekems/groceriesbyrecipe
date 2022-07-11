package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/* @author "Jack Kern" */

@Entity
@Table(name = "ingredients")
public class Ingredient
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ingredientID;

	@OneToOne
	@JoinColumn(name = "amountID", referencedColumnName = "amountID")
	private Amount amount;

	@OneToOne
	@JoinColumn(name = "unitOfMeasurementID", referencedColumnName = "unitOfMeasurementID")
	private UnitOfMeasurement unitOfMeasurement;

	@OneToOne
	@JoinColumn(name = "itemID", referencedColumnName = "itemID")
	private Item item;

	// @return the ingredientID
	public Long getIngredientID()
	{ return ingredientID; }

	// @param ingredientID the ingredientID to set
	public void setIngredientID(Long ingredientID)
	{ this.ingredientID = ingredientID; }

	// @return the amount
	public Amount getAmount()
	{ return amount; }

	// @param amount the amount to set
	public void setAmount(Amount amount)
	{ this.amount = amount; }

	// @return the unitOfMeasurement
	public UnitOfMeasurement getUnitOfMeasurement()
	{ return unitOfMeasurement; }

	// @param unitOfMeasurement the unitOfMeasurement to set
	public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement)
	{ this.unitOfMeasurement = unitOfMeasurement; }

	// @return the item
	public Item getItem()
	{ return item; }

	// @param item the item to set
	public void setItem(Item item)
	{ this.item = item; }

	public String toString()
	{
		return amount.getValue() + " " + unitOfMeasurement.getName() + " " + item.getDescription();
	}
}