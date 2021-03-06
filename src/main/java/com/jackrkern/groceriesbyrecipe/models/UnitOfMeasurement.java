package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.*;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/* @author "Jack Kern" */

@Entity
@Table(name = UNITSOFMEASUREMENT)
public class UnitOfMeasurement
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long unitOfMeasurementID;

	@Column
	private String name;

	@ManyToOne
	@JoinColumn(name = USERID, nullable = false)
	private User user;

	// @return the unitOfMeasurementID
	public Long getUnitOfMeasurementID()
	{ return unitOfMeasurementID; }

	// @param unitOfMeasurementID the unitOfMeasurementID to set
	public void setUnitOfMeasurementID(Long unitOfMeasurementID)
	{ this.unitOfMeasurementID = unitOfMeasurementID; }

	// @return the name
	public String getName()
	{ return name; }

	// @param name the name to set
	public void setName(String name)
	{ this.name = name.trim(); }

	// @return the user
	public User getUser()
	{ return user; }

	// @param user the user to set
	public void setUser(User user)
	{ this.user = user; }

	public String toString()
	{
		return name;
//		return String.format(ENTITYTOSTRING, cUNITOFMEASUREMENT, name);
	}
}