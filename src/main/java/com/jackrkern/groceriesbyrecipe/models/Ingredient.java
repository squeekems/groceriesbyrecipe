package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	private int ingredientID;
	private Amount amount;
	private UnitOfMeasurement unitOfMeasurement;
	private Item item;

	public Ingredient()
	{
		amount = new Amount();
		unitOfMeasurement = new UnitOfMeasurement();
		item = new Item();
	}

	@Id
	@Column(name = "ingredientID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getIngredientID()
	{ return ingredientID; }

	// @param ingredientID the ingredientID to set
	public void setIngredientID(int ingredientID)
	{ this.ingredientID = ingredientID; }

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "amountID", referencedColumnName = "amountID")
	public Amount getAmount()
	{ return amount; }

	// @param amount the amount to set
	public void setAmount(Amount amount)
	{ this.amount = amount; }

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "unitOfMeasurementID", referencedColumnName = "unitOfMeasurementID")
	public UnitOfMeasurement getUnitOfMeasurement()
	{ return unitOfMeasurement; }

	// @param unitOfMeasurement the unitOfMeasurement to set
	public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement)
	{ this.unitOfMeasurement = unitOfMeasurement; }

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itemID", referencedColumnName = "itemID")
	public Item getItem()
	{ return item; }

	// @param item the item to set
	public void setItem(Item item)
	{ this.item = item; }

	@Override
	public String toString()
	{
		return "Ingredient [ingredientID="	+ ingredientID + ", amount=" + amount + ", unitOfMeasurement="
				+ unitOfMeasurement + ", item=" + item + "]";
	}
}