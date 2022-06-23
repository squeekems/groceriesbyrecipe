package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/* @author "Jack Kern" */

@Data
@Entity
@Table(name = "ingredients")
public class Ingredient
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ingredientID;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "amountID", referencedColumnName = "amountID")
	private Amount amount;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "unitOfMeasurementID", referencedColumnName = "unitOfMeasurementID")
	private UnitOfMeasurement unitOfMeasurement;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itemID", referencedColumnName = "itemID")
	private Item item;

	public String toString()
	{
		return amount.getValue() + " " + unitOfMeasurement.getName() + " " + item.getDescription();
	}
}