package com.jackrkern.groceriesbyrecipe.business;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;
import static java.lang.System.out;
import static org.springframework.util.StringUtils.capitalize;

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

	public List<Amount> getAmounts(User userID)
	{
		Iterable<Amount> amounts = amountRepository.findAllByUser(userID);
		List<Amount> amountList = new ArrayList<>();
		amounts.forEach(amount -> amountList.add(amount));
		return amountList;
	}

	public List<UnitOfMeasurement> getUnitsOfMeasurement(User userID)
	{
		Iterable<UnitOfMeasurement> unitsOfMeasurement = unitOfMeasurementRepository.findAllByUser(userID);
		List<UnitOfMeasurement> unitOfMeasurementList = new ArrayList<>();
		unitsOfMeasurement.forEach(UnitOfMeasurement -> unitOfMeasurementList.add(UnitOfMeasurement));
		return unitOfMeasurementList;
	}

	public Recipe saveRecipe(Recipe recipe)
	{
		recipe = recipeRepository.save(recipe);
		out.printf(STRINGsSTRINGnl, recipe.toDetailedString(), capitalize(strPast(strMapping(SAVE))));
		return recipe;
	}

	public void saveRecipe(Recipe recipe, Ingredient ingredient)
	{
		Set<Ingredient> ingredients = recipe.getIngredients();
		if (!ingredients.contains(ingredient))
		{
			ingredientRepository.save(ingredient);
			out.printf(STRINGsSTRINGnl, ingredient.toDetailedString(), capitalize(strPast(strMapping(SAVE))));
			saveRecipe(recipe);
			ingredients.add(ingredient);
			recipe.setIngredients(ingredients);
		}
		ingredientRepository.save(ingredient);
		out.printf(STRINGsSTRINGnl, ingredient.toDetailedString(), capitalize(strPast(strMapping(SAVE))));
		saveRecipe(recipe);
	}

	public void deleteIngredient(Long ingredientID, Long recipeID)
	{
		Recipe recipe = getRecipeByID(recipeID);
		Ingredient ingredient = getIngredientByID(ingredientID);
		recipe.getIngredients().remove(ingredient);
		out.printf(STRINGsSTRINGnl, ingredient.toDetailedString(), capitalize(strPast(strMapping(DELETE))));
		ingredientRepository.deleteById(ingredientID);
		saveRecipe(recipe);
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
				out.printf(STRINGsSTRINGnl, ingredient.toDetailedString(), capitalize(strPast(strMapping(DELETE))));
				ingredientRepository.deleteById(ingredient.getIngredientID());
			}
			out.printf(STRINGsSTRINGnl, recipe.toDetailedString(), capitalize(strPast(strMapping(DELETE))));
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

	public void saveUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement)
	{
		unitOfMeasurementRepository.save(unitOfMeasurement);
		out.printf(STRINGsSTRINGnl, unitOfMeasurement.toDetailedString(), capitalize(strPast(strMapping(SAVE))));
	}

	public void saveAmount(Amount amount)
	{
		amountRepository.save(amount);
		out.printf(STRINGsSTRINGnl, amount.toDetailedString(), capitalize(strPast(strMapping(SAVE))));
	}

	public void deleteUnitOfMeasurement(Long unitOfMeasurementID)
	{
		try
		{
			out.printf(	STRINGsSTRINGnl, getUnitOfMeasurementByID(unitOfMeasurementID).toDetailedString(),
						capitalize(strPast(strMapping(DELETE))));
			unitOfMeasurementRepository.deleteById(unitOfMeasurementID);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void deleteAmount(Long amountID)
	{
		try
		{
			out.printf(	STRINGsSTRINGnl, getAmountByID(amountID).toDetailedString(),
						capitalize(strPast(strMapping(DELETE))));
			amountRepository.deleteById(amountID);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}