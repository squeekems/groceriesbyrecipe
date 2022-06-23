package com.jackrkern.groceriesbyrecipe;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.jackrkern.groceriesbyrecipe.models.Recipe;
import com.jackrkern.groceriesbyrecipe.repositories.RecipeRepository;

/* @author "Jack Kern" */

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RecipeRepositoryTests
{
	@Autowired
	private RecipeRepository recipeRepository;

	@Test
	public void testFindByIngredients_IngredientID()
	{
		Long ingredientID = (long) 1;

		List<Recipe> ingredientRecipes = recipeRepository.findByIngredients_IngredientID(ingredientID);

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		ingredientRecipes.forEach(System.out::println);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		assertThat(ingredientRecipes).isNotNull();
	}

	@Test
	public void testFindIngredientsByRecipeId()
	{
		Long recipeID = (long) 1;

		Set<Long> ingredientIDs = recipeRepository.findIngredientsByRecipeID(recipeID);

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		ingredientIDs.forEach(System.out::println);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		assertThat(ingredientIDs).isNotNull();
	}
}