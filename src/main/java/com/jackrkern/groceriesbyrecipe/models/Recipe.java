package com.jackrkern.groceriesbyrecipe.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/* @author "Jack Kern" */

@Data
@Entity
@Table(name = "recipes")
public class Recipe
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recipeID;
	@Column
	private String name;
	@Column(length = 2047)
	private String instructions;
	@ManyToMany(targetEntity = Ingredient.class, fetch = FetchType.EAGER)
	@JoinTable(	name = "recipes_ingredients", joinColumns = { @JoinColumn(name = "recipeID") },
				inverseJoinColumns = { @JoinColumn(name = "ingredientID") })
	private Set<Ingredient> ingredients;
	@ManyToOne
	@JoinColumn(name = "userID", nullable = false)
	private User user;

	public String toString()
	{
		return name;
	}
}