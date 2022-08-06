/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.repositories;

/**
 * @imports
 */
import com.jackrkern.groceriesbyrecipe.models.Recipe;
import com.jackrkern.groceriesbyrecipe.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.FIND_INGREDIENTS_BY_RECIPE_ID;

/**
 *  @author Jack Kern <jackrkern@gmail.com>
 */
@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	List<Recipe> findByIngredients_IngredientID(Long ingredientID);

	// Get all the ingredientIDs of a Recipe by recipeID
	@Query(value = FIND_INGREDIENTS_BY_RECIPE_ID, nativeQuery = true)
	Set<Long> findIngredientsByRecipeID(Long recipeID);
	List<Recipe> findAllByUser(User userID);
}