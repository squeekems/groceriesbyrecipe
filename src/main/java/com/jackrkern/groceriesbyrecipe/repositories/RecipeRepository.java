package com.jackrkern.groceriesbyrecipe.repositories;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jackrkern.groceriesbyrecipe.models.Recipe;
import com.jackrkern.groceriesbyrecipe.models.User;

/* @author "Jack Kern" */

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long>
{
	List<Recipe> findByIngredients_IngredientID(Long ingredientID);

	// Get all the ingredientIDs of a Recipe by recipeID
	@Query(value = FINDINGREDIENTSBYRECIPEID, nativeQuery = true)
	Set<Long> findIngredientsByRecipeID(Long recipeID);

	List<Recipe> findAllByUser(User userID);
}