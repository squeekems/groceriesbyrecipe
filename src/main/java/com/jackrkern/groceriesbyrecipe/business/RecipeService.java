/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.business;

/**
 * @imports
 */
import com.jackrkern.groceriesbyrecipe.models.*;
import com.jackrkern.groceriesbyrecipe.repositories.AmountRepository;
import com.jackrkern.groceriesbyrecipe.repositories.IngredientRepository;
import com.jackrkern.groceriesbyrecipe.repositories.RecipeRepository;
import com.jackrkern.groceriesbyrecipe.repositories.UnitOfMeasurementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/**
 *  @author Jack Kern <jackrkern@gmail.com>
 */
@Service
public class RecipeService {

	Logger logger = LoggerFactory.getLogger(RecipeService.class);

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
		Iterable<UnitOfMeasurement> unitsOfMeasurement =
				unitOfMeasurementRepository.findAllByUser(userID);
		List<UnitOfMeasurement> unitOfMeasurementList = new ArrayList<>();
		unitsOfMeasurement.forEach(unitOfMeasurementList::add);
		return unitOfMeasurementList;
	}

	public Recipe saveRecipe(Recipe recipe) {
		recipe = recipeRepository.save(recipe);
		logger.info(space(new String[]{
				recipe.toDetailedString(),
				capitalize(pastOf(demap(SAVE)))
		}) + PERIOD);
		return recipe;
	}

	public Recipe saveRecipe(Recipe recipe, Ingredient ingredient) {
		Set<Ingredient> ingredients = recipe.getIngredients();
		if (!ingredients.contains(ingredient)) {
			ingredient = ingredientRepository.save(ingredient);
			logger.info(space(new String[]{
					ingredient.toDetailedString(),
					capitalize(pastOf(demap(SAVE)))
			}) + PERIOD);
			recipe = saveRecipe(recipe);
			ingredients.add(ingredient);
			recipe.setIngredients(ingredients);
		}
		ingredient = ingredientRepository.save(ingredient);
		logger.info(space(new String[]{
				ingredient.toDetailedString(),
				capitalize(pastOf(demap(SAVE)))
		}) + PERIOD);
		recipe = saveRecipe(recipe);
		return recipe;
	}

	public void deleteIngredient(Long ingredientID, Long recipeID) {
		Recipe recipe = getRecipeByID(recipeID);
		Ingredient ingredient = getIngredientByID(ingredientID);
		recipe.getIngredients().remove(ingredient);
		try {
			logger.info(space(new String[]{
					ingredient.toDetailedString(),
					capitalize(pastOf(demap(DELETE)))
			}) + PERIOD);
			ingredientRepository.deleteById(ingredientID);
		} catch (IllegalArgumentException e) {
			logger.warn(space( new String[]{
					(demap(DELETE) + capitalize(demap(INGREDIENT))),
					pastOf(CATCH),
					e.getClass().getName()
			}), e);
		}
		saveRecipe(recipe);
	}

	public void deleteRecipe(Long recipeID) {
		try {
			Recipe recipe = getRecipeByID(recipeID);
			Iterable<Ingredient> ingredients = recipe.getIngredients();
			recipe.setIngredients(new HashSet<>());
			saveRecipe(recipe);
			for (Ingredient ingredient: ingredients) {
				try {
					logger.info(space(new String[]{
							ingredient.toDetailedString(),
							capitalize(pastOf(demap(DELETE)))
					}) + PERIOD);
					ingredientRepository.deleteById(ingredient.getIngredientID());
				} catch (IllegalArgumentException e) {
					logger.warn(space( new String[]{
							(demap(DELETE) + capitalize(demap(RECIPE))),
							pastOf(CATCH),
							e.getClass().getName()
					}), e);
				}
			}
			logger.info(space(new String[]{
					recipe.toDetailedString(),
					capitalize(pastOf(demap(DELETE)))
			}) + PERIOD);
			recipeRepository.deleteById(recipeID);
		} catch (IllegalArgumentException e) {
			logger.warn(space( new String[]{
					(demap(DELETE) + capitalize(demap(RECIPE))),
					pastOf(CATCH),
					e.getClass().getName()
			}), e);
		}
	}

	public Recipe getRecipeByID(Long recipeID) {
		return recipeRepository.findById(recipeID).orElseThrow();
	}

	public Amount getAmountByID(Long amountID) {
		return amountRepository.findById(amountID).orElseThrow();
	}

	public UnitOfMeasurement getUnitOfMeasurementByID(Long unitOfMeasurementID) {
		return unitOfMeasurementRepository.findById(unitOfMeasurementID).orElseThrow();
	}

	public Ingredient getIngredientByID(Long ingredientID) {
		return ingredientRepository.findById(ingredientID).orElseThrow();
	}

	public UnitOfMeasurement saveUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		unitOfMeasurement = unitOfMeasurementRepository.save(unitOfMeasurement);
		logger.info(space(new String[]{
				unitOfMeasurement.toDetailedString(),
				capitalize(pastOf(demap(SAVE)))
		}) + PERIOD);
		return unitOfMeasurement;
	}

	public Amount saveAmount(Amount amount) {
		amount = amountRepository.save(amount);
		logger.info(space(new String[]{
				amount.toDetailedString(),
				capitalize(pastOf(demap(SAVE)))
		}) + PERIOD);
		return amount;
	}

	public void deleteUnitOfMeasurement(Long unitOfMeasurementID) {
		try {
			logger.info(space(new String[]{
					getUnitOfMeasurementByID(unitOfMeasurementID).toDetailedString(),
					capitalize(pastOf(demap(DELETE)))
			}) + PERIOD);
			unitOfMeasurementRepository.deleteById(unitOfMeasurementID);
		} catch (IllegalArgumentException e) {
			logger.warn(space( new String[]{
					(demap(DELETE) + capitalize(demap(UNIT_OF_MEASUREMENT))),
					pastOf(CATCH),
					e.getClass().getName()
			}), e);
		}
	}

	public void deleteAmount(Long amountID) {
		try {
			logger.info(space(new String[]{
					getAmountByID(amountID).toDetailedString(),
					capitalize(pastOf(demap(DELETE)))
			}) + PERIOD);
			amountRepository.deleteById(amountID);
		} catch (IllegalArgumentException e) {
			logger.warn(space( new String[]{
					(demap(DELETE) + capitalize(demap(AMOUNT))),
					pastOf(CATCH),
					e.getClass().getName()
			}), e);
		}
	}
}