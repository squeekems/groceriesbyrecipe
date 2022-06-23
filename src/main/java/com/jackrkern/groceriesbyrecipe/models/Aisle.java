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
@Table(name = "aisles")
public class Aisle
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long aisleID;

	@Column
	private String name;

	@ManyToOne
	@JoinColumn(name = "userID", nullable = false)
	private User user;

	public int compareTo(Aisle aisle)
	{
		return name.compareTo(aisle.getName());
	}

	public String toString()
	{
		return name;
	}
}