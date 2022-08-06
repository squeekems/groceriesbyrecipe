/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.models;

/**
 * @imports
 */
import javax.persistence.*;
import java.util.Set;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/**
 *  @author Jack Kern <jackrkern@gmail.com>
 */
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

	/**
	 * Returns the recipeID of the Recipe.
	 *
	 * @return the {@link Long} recipeID
	 */
	public Long getRecipeID() { return recipeID; }

	/**
	 * Sets the recipeID of the Recipe.
	 *
	 * @param recipeID of type {@link Long}
	 */
	public void setRecipeID(Long recipeID) { this.recipeID = recipeID; }

	/**
	 * Returns the name of the Recipe.
	 *
	 * @return the {@link String} name
	 */
	public String getName() { return name; }

	/**
	 * Sets the name of the Recipe.
	 *
	 * @param name of type {@link String}
	 */
	public void setName(String name) { this.name = name.trim(); }

	/**
	 * Returns the instructions of the Recipe.
	 *
	 * @return the {@link String} instructions
	 */
	public String getInstructions() { return instructions; }

	/**
	 * Sets the instructions of the Recipe.
	 *
	 * @param instructions of type {@link String}
	 */
	public void setInstructions(String instructions) { this.instructions = instructions.trim(); }

	/**
	 * Returns the ingredients of the Recipe.
	 *
	 * @return the {@link Set<Ingredient>} ingredients
	 */
	public Set<Ingredient> getIngredients() { return ingredients; }

	/**
	 * Sets the ingredients of the Recipe.
	 *
	 * @param ingredients of type {@link Set<Ingredient>}
	 */
	public void setIngredients(Set<Ingredient> ingredients) { this.ingredients = ingredients; }

	/**
	 * Returns the user of the Recipe.
	 *
	 * @return the {@link User} user
	 */
	public User getUser() { return user; }

	/**
	 * Sets the user of the Recipe.
	 *
	 * @param user of type {@link User}
	 */
	public void setUser(User user) { this.user = user; }

	/**
	 * Return all the values represented in the Recipe.
	 *
	 * @return a detailed String representation of the Recipe
	 */
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

	/**
	 * Returns the name of the Recipe.
	 *
	 * @return a String representation of the Recipe
	 */
	@Override
	public String toString()
	{
		return name;
	}
}