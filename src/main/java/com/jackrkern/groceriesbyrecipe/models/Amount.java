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
@Table(name = "amounts")
public class Amount
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int amountID;
	@Column
	private String value;
	@ManyToOne
	@JoinColumn(name = "userID", nullable = false)
	private User user;
}