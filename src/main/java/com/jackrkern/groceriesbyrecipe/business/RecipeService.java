package com.jackrkern.groceriesbyrecipe.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jackrkern.groceriesbyrecipe.models.Amount;
import com.jackrkern.groceriesbyrecipe.models.Recipe;
import com.jackrkern.groceriesbyrecipe.models.UnitOfMeasurement;
import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.AmountRepository;
import com.jackrkern.groceriesbyrecipe.repositories.RecipeRepository;
import com.jackrkern.groceriesbyrecipe.repositories.UnitOfMeasurementRepository;

/* @author "Jack Kern" */

@Service
public class RecipeService
{
//	@Autowired
//	private IngredientRepository ingredientRepository;

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private AmountRepository amountRepository;

	@Autowired
	private UnitOfMeasurementRepository unitOfMeasurementRepository;

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
}