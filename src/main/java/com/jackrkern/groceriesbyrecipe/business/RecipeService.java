package com.jackrkern.groceriesbyrecipe.business;

import com.jackrkern.groceriesbyrecipe.models.*;
import com.jackrkern.groceriesbyrecipe.repositories.AmountRepository;
import com.jackrkern.groceriesbyrecipe.repositories.IngredientRepository;
import com.jackrkern.groceriesbyrecipe.repositories.RecipeRepository;
import com.jackrkern.groceriesbyrecipe.repositories.UnitOfMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;
import static java.lang.System.out;

/* @author "Jack Kern" */

@Service
public class RecipeService {
	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private AmountRepository amountRepository;

	@Autowired
	private UnitOfMeasurementRepository unitOfMeasurementRepository;

	@Autowired
	private IngredientRepository ingredientRepository;

	public List<Recipe> getRecipes(User userID) {
		Iterable<Recipe> recipes = recipeRepository.findAllByUser(userID);
		List<Recipe> recipeList = new ArrayList<>();
		recipes.forEach(recipeList::add);
		return recipeList;
	}

	public List<Amount> getAmounts(User userID) {
		Iterable<Amount> amounts = amountRepository.findAllByUser(userID);
		List<Amount> amountList = new ArrayList<>();
		amounts.forEach(amountList::add);
		return amountList;
	}

	public List<UnitOfMeasurement> getUnitsOfMeasurement(User userID) {
		Iterable<UnitOfMeasurement> unitsOfMeasurement = unitOfMeasurementRepository.findAllByUser(userID);
		List<UnitOfMeasurement> unitOfMeasurementList = new ArrayList<>();
		unitsOfMeasurement.forEach(unitOfMeasurementList::add);
		return unitOfMeasurementList;
	}

	public Recipe saveRecipe(Recipe recipe) {
		recipe = recipeRepository.save(recipe);
		out.printf(S_S_NL, recipe.toDetailedString(), capitalize(pastOf(demap(SAVE))));
		return recipe;
	}

	public void saveRecipe(Recipe recipe, Ingredient ingredient) {
		Set<Ingredient> ingredients = recipe.getIngredients();
		if (!ingredients.contains(ingredient)) {
			ingredientRepository.save(ingredient);
			out.printf(S_S_NL, ingredient.toDetailedString(), capitalize(pastOf(demap(SAVE))));
			saveRecipe(recipe);
			ingredients.add(ingredient);
			recipe.setIngredients(ingredients);
		}
		ingredientRepository.save(ingredient);
		out.printf(S_S_NL, ingredient.toDetailedString(), capitalize(pastOf(demap(SAVE))));
		saveRecipe(recipe);
	}

	public void deleteIngredient(Long ingredientID, Long recipeID) {
		Recipe recipe = getRecipeByID(recipeID);
		Ingredient ingredient = getIngredientByID(ingredientID);
		recipe.getIngredients().remove(ingredient);
		out.printf(S_S_NL, ingredient.toDetailedString(), capitalize(pastOf(demap(DELETE))));
		ingredientRepository.deleteById(ingredientID);
		saveRecipe(recipe);
	}

	public void deleteRecipe(Long recipeID) {
		try {
			Recipe recipe = getRecipeByID(recipeID);
			Iterable<Ingredient> ingredients = recipe.getIngredients();
			recipe.setIngredients(new HashSet<>());
			saveRecipe(recipe);
			for (Ingredient ingredient: ingredients) {
				out.printf(S_S_NL, ingredient.toDetailedString(), capitalize(pastOf(demap(DELETE))));
				ingredientRepository.deleteById(ingredient.getIngredientID());
			}
			out.printf(S_S_NL, recipe.toDetailedString(), capitalize(pastOf(demap(DELETE))));
			recipeRepository.deleteById(recipeID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Recipe getRecipeByID(Long recipeID)
	{
		return recipeRepository.findById(recipeID).orElseThrow();
	}

	public Amount getAmountByID(Long amountID)
	{
		return amountRepository.findById(amountID).orElseThrow();
	}

	public UnitOfMeasurement getUnitOfMeasurementByID(Long unitOfMeasurementID) {
		return unitOfMeasurementRepository.findById(unitOfMeasurementID).orElseThrow();
	}

	public Ingredient getIngredientByID(Long ingredientID)
	{
		return ingredientRepository.findById(ingredientID).orElseThrow();
	}

	public void saveUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		unitOfMeasurementRepository.save(unitOfMeasurement);
		out.printf(S_S_NL, unitOfMeasurement.toDetailedString(), capitalize(pastOf(demap(SAVE))));
	}

	public void saveAmount(Amount amount) {
		amountRepository.save(amount);
		out.printf(S_S_NL, amount.toDetailedString(), capitalize(pastOf(demap(SAVE))));
	}

	public void deleteUnitOfMeasurement(Long unitOfMeasurementID) {
		try {
			out.printf(S_S_NL, getUnitOfMeasurementByID(unitOfMeasurementID).toDetailedString(),
					capitalize(pastOf(demap(DELETE))));
			unitOfMeasurementRepository.deleteById(unitOfMeasurementID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteAmount(Long amountID) {
		try {
			out.printf(S_S_NL, getAmountByID(amountID).toDetailedString(),
					capitalize(pastOf(demap(DELETE))));
			amountRepository.deleteById(amountID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}