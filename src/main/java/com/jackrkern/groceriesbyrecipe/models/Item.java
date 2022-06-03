package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private int itemID;

	@Column
	private String description;

	@Column
	private String aisle;

	@ManyToOne
	@JoinColumn(name = "userID", nullable = false)
	private User user;
}