package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.*;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/* @author "Jack Kern" */

@Entity
@Table(name = AMOUNTS)
public class Amount
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long amountID;

	@Column
	private String value;

	@ManyToOne
	@JoinColumn(name = USERID, nullable = false)
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
	{ this.value = value.trim(); }

	// @return the user
	public User getUser()
	{ return user; }

	// @param user the user to set
	public void setUser(User user)
	{ this.user = user; }

	public String toString()
	{
		return value;
//		return String.format(ENTITYTOSTRING, cAMOUNT, value);
	}
}