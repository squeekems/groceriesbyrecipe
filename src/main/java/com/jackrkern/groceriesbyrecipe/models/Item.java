package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/* @author "Jack Kern" */

@Data
@Entity
@Table(name = "items")
public class Item
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemID;

	@Column
	private String description;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "aisleID", referencedColumnName = "aisleID")
	private Aisle aisle;

	@ManyToOne
	@JoinColumn(name = "userID", nullable = false)
	private User user;

	public Item()
	{}

	public Item(String description, Aisle aisle, User user)
	{
		this.description = description;
		this.aisle = aisle;
		this.user = user;
	}
}