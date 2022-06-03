package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/* @author "Jack Kern" */

@Entity
@Table(name = "unitsOfMeasurement")
public class UnitOfMeasurement
{
	private int unitOfMeasurementID;
	private String name;
	private User user;

	public UnitOfMeasurement()
	{
		user = new User();
	}

	@Id
	@Column(name = "unitOfMeasurementID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getUnitOfMeasurementID()
	{ return unitOfMeasurementID; }

	// @param unitOfMeasurementID the unitOfMeasurementID to set
	public void setUnitOfMeasurementID(int unitOfMeasurementID)
	{ this.unitOfMeasurementID = unitOfMeasurementID; }

	@Column(name = "name")
	public String getName()
	{ return name; }

	// @param name the name to set
	public void setName(String name)
	{ this.name = name; }

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
		return "Amount [unitOfMeasurementID=" + unitOfMeasurementID + ", name=" + name + "]";
	}
}