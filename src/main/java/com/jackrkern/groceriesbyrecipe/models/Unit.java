package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* @author "Jack Kern" */

@Entity
@Table(name = "units")
public class Unit
{
	private int unitID;
	private String value;

	@Id
	@Column(name = "unitID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getUnitID()
	{ return unitID; }

	// @param unitID the unitID to set
	public void setUnitID(int unitID)
	{ this.unitID = unitID; }

	@Column(name = "value")
	public String getValue()
	{ return value; }

	// @param value the value to set
	public void setValue(String value)
	{ this.value = value; }

	@Override
	public String toString()
	{
		return "Amount [unitID=" + unitID + ", value=" + value + "]";
	}
}