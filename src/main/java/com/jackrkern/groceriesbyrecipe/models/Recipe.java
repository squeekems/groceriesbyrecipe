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

/* @author "Jack Kern" */

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

	// @return the recipeID
	public Long getRecipeID()
	{ return recipeID; }

	// @param recipeID the recipeID to set
	public void setRecipeID(Long recipeID)
	{ this.recipeID = recipeID; }

	// @return the name
	public String getName()
	{ return name; }

	// @param name the name to set
	public void setName(String name)
	{ this.name = name; }

	// @return the instructions
	public String getInstructions()
	{ return instructions; }

	// @param instructions the instructions to set
	public void setInstructions(String instructions)
	{ this.instructions = instructions; }

	// @return the ingredients
	public Set<Ingredient> getIngredients()
	{ return ingredients; }

	// @param ingredients the ingredients to set
	public void setIngredients(Set<Ingredient> ingredients)
	{ this.ingredients = ingredients; }

	// @return the user
	public User getUser()
	{ return user; }

	// @param user the user to set
	public void setUser(User user)
	{ this.user = user; }

	public String toString()
	{
		return name;
	}
}