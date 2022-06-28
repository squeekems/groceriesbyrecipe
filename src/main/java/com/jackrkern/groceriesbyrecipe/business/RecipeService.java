package com.jackrkern.groceriesbyrecipe.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jackrkern.groceriesbyrecipe.models.Amount;
import com.jackrkern.groceriesbyrecipe.models.Ingredient;
import com.jackrkern.groceriesbyrecipe.models.Recipe;
import com.jackrkern.groceriesbyrecipe.models.UnitOfMeasurement;
import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.AmountRepository;
import com.jackrkern.groceriesbyrecipe.repositories.IngredientRepository;
import com.jackrkern.groceriesbyrecipe.repositories.RecipeRepository;
import com.jackrkern.groceriesbyrecipe.repositories.UnitOfMeasurementRepository;

/* @author "Jack Kern" */

@Service
public class RecipeService
{
	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private AmountRepository amountRepository;

	@Autowired
	private UnitOfMeasurementRepository unitOfMeasurementRepository;

	@Autowired
	private IngredientRepository ingredientRepository;

	public List<Recipe> getRecipes(User userID)
	{
		Iterable<Recipe> recipes = recipeRepository.findAllByUser(userID);
		List<Recipe> recipeList = new ArrayList<>();
		recipes.forEach(recipe -> recipeList.add(recipe));
		return recipeList;
	}

	public List<Amount> getAmounts()
	{
		Iterable<Amount> amounts = amountRepository.findAll();
		List<Amount> amountList = new ArrayList<>();
		amounts.forEach(amount -> amountList.add(amount));
		return amountList;
	}

	public List<UnitOfMeasurement> getUnitsOfMeasurement()
	{
		Iterable<UnitOfMeasurement> unitsOfMeasurement = unitOfMeasurementRepository.findAll();
		List<UnitOfMeasurement> unitOfMeasurementList = new ArrayList<>();
		unitsOfMeasurement.forEach(UnitOfMeasurement -> unitOfMeasurementList.add(UnitOfMeasurement));
		return unitOfMeasurementList;
	}

	public void saveRecipe(Recipe recipe)
	{
		if (recipe != null)
		{
			recipeRepository.save(recipe);
		} else
		{
			throw new RuntimeException("Recipe cannot be null");
		}
	}

	public void saveRecipe(Recipe recipe, Ingredient ingredient)
	{
		Set<Ingredient> ingredients = recipe.getIngredients();
		if (!ingredients.contains(ingredient))
		{
			ingredientRepository.save(ingredient);
			saveRecipe(recipe);
			ingredients.add(ingredient);
			recipe.setIngredients(ingredients);
		}
		ingredientRepository.save(ingredient);
		saveRecipe(recipe);
	}

	public void deleteIngredient(Long ingredientID, Long recipeID)
	{
		Recipe recipe = getRecipeByID(recipeID);
		recipe.getIngredients().remove(getIngredientByID(ingredientID));
		ingredientRepository.deleteById(ingredientID);
		recipeRepository.save(recipe);
	}

	public void deleteRecipe(Long recipeID)
	{
		try
		{
			Recipe recipe = getRecipeByID(recipeID);
			for (Iterator<Ingredient> iterator = recipe.getIngredients().iterator(); iterator.hasNext();)
			{
				Ingredient ingredient = iterator.next();
				recipe.getIngredients().remove(ingredient);
				ingredientRepository.deleteById(ingredient.getIngredientID());
			}
			recipeRepository.deleteById(recipeID);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public Recipe getRecipeByID(Long recipeID)
	{
		return recipeRepository.findById(recipeID).get();
	}

	public Amount getAmountByID(Long amountID)
	{
		return amountRepository.findById(amountID).get();
	}

	public UnitOfMeasurement getUnitOfMeasurementByID(Long unitOfMeasurementID)
	{
		return unitOfMeasurementRepository.findById(unitOfMeasurementID).get();
	}

	public Ingredient getIngredientByID(Long ingredientID)
	{
		return ingredientRepository.findById(ingredientID).get();
	}
}