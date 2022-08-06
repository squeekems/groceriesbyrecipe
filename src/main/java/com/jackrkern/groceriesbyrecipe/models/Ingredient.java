package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.*;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/* @author "Jack Kern" */

@Entity
@Table(name = TN_INGREDIENTS)
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ingredientID;

	@OneToOne
	@JoinColumn(name = CN_AMOUNT_ID, referencedColumnName = CN_AMOUNT_ID)
	private Amount amount;

	@OneToOne
	@JoinColumn(name = CN_UNIT_OF_MEASUREMENT_ID, referencedColumnName = CN_UNIT_OF_MEASUREMENT_ID)
	private UnitOfMeasurement unitOfMeasurement;

	@OneToOne
	@JoinColumn(name = CN_ITEM_ID, referencedColumnName = CN_ITEM_ID)
	private Item item;

//	@ManyToMany(mappedBy = INGREDIENTS, fetch = FetchType.LAZY)
//	private Set<Recipe> recipes;

	// @return the ingredientID
	public Long getIngredientID() { return ingredientID; }

	// @param ingredientID the ingredientID to set
	public void setIngredientID(Long ingredientID) { this.ingredientID = ingredientID; }

	// @return the amount
	public Amount getAmount() { return amount; }

	// @param amount the amount to set
	public void setAmount(Amount amount) { this.amount = amount; }

	// @return the unitOfMeasurement
	public UnitOfMeasurement getUnitOfMeasurement() { return unitOfMeasurement; }

	// @param unitOfMeasurement the unitOfMeasurement to set
	public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) { this.unitOfMeasurement = unitOfMeasurement; }

	// @return the item
	public Item getItem() { return item; }

	// @param item the item to set
	public void setItem(Item item) { this.item = item; }

//	// @return recipes
//	public Set<Recipe> getRecipes() {
//		return recipes;
//	}
//
//	// @param recipes the recipes to set
//	public void setRecipes(Set<Recipe> recipes) {
//		this.recipes = recipes;
//	}
//
//	public void addRecipe(Recipe recipe) {
//		recipes.add(recipe);
//		recipe.getIngredients().add(this);
//	}
//
//	public void removeRecipe(Recipe recipe) {
//		recipes.remove(recipe);
//		recipe.getIngredients().remove(this);
//	}

	public String toDetailedString() {
		return "Ingredient [ingredientID="	+ ingredientID +
				", amount=" + amount +
				", unitOfMeasurement=" + unitOfMeasurement +
				", item=" + item + "]";
	}

	@Override
	public String toString()
	{
		return amount + " " + unitOfMeasurement + " " + item;
	}
}