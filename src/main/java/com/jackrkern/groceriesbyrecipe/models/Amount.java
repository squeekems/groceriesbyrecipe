package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* @author "Jack Kern" */

@Entity
@Table(name = "amounts")
public class Amount
{
	private int amountID;
	private String value;

	@Id
	@Column(name = "amountID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getAmountID()
	{ return amountID; }

	// @param amountID the amountID to set
	public void setAmountID(int amountID)
	{ this.amountID = amountID; }

	@Column(name = "value")
	public String getValue()
	{ return value; }

	// @param value the value to set
	public void setValue(String value)
	{ this.value = value; }

	@Override
	public String toString()
	{
		return "Amount [amountID=" + amountID + ", value=" + value + "]";
	}
}