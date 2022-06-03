package com.jackrkern.groceriesbyrecipe.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	private int recipeID;
	private String name;
	private String instructions;
	private Set<Ingredient> ingredients;
	private User user;

	public Recipe()
	{
		ingredients = new HashSet<>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipeID")
	public int getRecipeID()
	{ return recipeID; }

	// @param recipeID the recipeID to set
	public void setRecipeID(int recipeID)
	{ this.recipeID = recipeID; }

	@Column(name = "name")
	public String getName()
	{ return name; }

	// @param name the name to set
	public void setName(String name)
	{ this.name = name; }

	@Column(name = "instructions")
	public String getInstructions()
	{ return instructions; }

	// @param instructions the instructions to set
	public void setInstructions(String instructions)
	{ this.instructions = instructions; }

	@ManyToMany(targetEntity = Ingredient.class)
	@JoinTable(	name = "recipes_ingredients", joinColumns = { @JoinColumn(name = "recipeID") },
				inverseJoinColumns = { @JoinColumn(name = "ingredientID") })
	public Set<Ingredient> getIngredients()
	{ return ingredients; }

	// @param ingredients the ingredients to set
	public void setIngredients(Set<Ingredient> ingredients)
	{ this.ingredients = ingredients; }

	@ManyToOne
	@JoinColumn(name = "userID", nullable = false)
	public User getUser()
	{ return user; }

	// @param user the user to set
	public void setUser(User user)
	{ this.user = user; }

	@Override
	public String toString()
	{
		return "Recipe [recipeID="	+ recipeID + ", name=" + name + ", instructions=" + instructions + ", ingredients="
				+ ingredients + ", user=" + user + "]";
	}
}
