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
@Table(name = "amounts")
public class Amount
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long amountID;

	@Column
	private String value;

	@ManyToOne
	@JoinColumn(name = "userID", nullable = false)
	private User user;

	// @return the amountID
	public Long getAmountID()
	{ return amountID; }

	// @param amountID the amountID to set
	public void setAmountID(Long amountID)
	{ this.amountID = amountID; }

	// @return the value
	public String getValue()
	{ return value; }

	// @param value the value to set
	public void setValue(String value)
	{ this.value = value; }

	// @return the user
	public User getUser()
	{ return user; }

	// @param user the user to set
	public void setUser(User user)
	{ this.user = user; }

	public String toString()
	{
		return value;
	}
}