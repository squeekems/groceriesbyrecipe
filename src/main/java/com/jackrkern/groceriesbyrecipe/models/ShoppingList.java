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
@Table(name = "list")
public class ShoppingList
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int listID;

	@Column
	private int count;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itemID", referencedColumnName = "itemID")
	private Item item;

	@ManyToOne
	@JoinColumn(name = "userID", nullable = false)
	private User user;
}