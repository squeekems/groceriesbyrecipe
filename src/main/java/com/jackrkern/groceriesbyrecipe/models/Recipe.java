package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.*;
import java.util.Set;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/* @author "Jack Kern" */

@Entity
@Table(name = TN_RECIPES)
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recipeID;
	@Column
	private String name;
	@Column(length = 2047)
	private String instructions;
	@ManyToMany(targetEntity = Ingredient.class, fetch = FetchType.EAGER)
	@JoinTable(	name = TN_RECIPES_INGREDIENTS, joinColumns = { @JoinColumn(name = CN_RECIPE_ID) },
				inverseJoinColumns = { @JoinColumn(name = CN_INGREDIENT_ID) })
	private Set<Ingredient> ingredients;
	@ManyToOne
	@JoinColumn(name = CN_USER_ID, nullable = false)
	private User user;

	// @return the recipeID
	public Long getRecipeID() { return recipeID; }

	// @param recipeID the recipeID to set
	public void setRecipeID(Long recipeID) { this.recipeID = recipeID; }

	// @return the name
	public String getName() { return name; }

	// @param name the name to set
	public void setName(String name) { this.name = name.trim(); }

	// @return the instructions
	public String getInstructions() { return instructions; }

	// @param instructions the instructions to set
	public void setInstructions(String instructions) { this.instructions = instructions.trim(); }

	// @return the ingredients
	public Set<Ingredient> getIngredients() { return ingredients; }

	// @param ingredients the ingredients to set
	public void setIngredients(Set<Ingredient> ingredients) { this.ingredients = ingredients; }

//	public void addIngredient(Ingredient ingredient) {
//		ingredients.add(ingredient);
//		ingredient.getRecipes().add(this);
//	}
//
//	public void removeIngredient(Ingredient ingredient) {
//		ingredients.remove(ingredient);
//		ingredient.getRecipes().remove(this);
//	}

	// @return the user
	public User getUser() { return user; }

	// @param user the user to set
	public void setUser(User user) { this.user = user; }

	public String toDetailedString() {
		String recipe = user + "'s Recipe [recipeID=" + recipeID +
				", name=" + name;
		if (instructions != null && !instructions.isEmpty())
			recipe += ", instructions=\"" + instructions.replaceAll("[\\t\\n\\r]+", " ") + "\"";
		if (ingredients != null && ingredients.size() != 0)
			recipe += ", ingredients=" + ingredients;
		recipe += "]";
		return recipe;
	}

	@Override
	public String toString()
	{
		return name;
	}
}