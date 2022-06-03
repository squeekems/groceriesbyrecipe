package com.jackrkern.groceriesbyrecipe.business;

import org.springframework.stereotype.Service;

import com.jackrkern.groceriesbyrecipe.repositories.RecipeRepository;

/* @author "Jack Kern" */

@Service
public class RecipesService
{
	private final RecipeRepository recipeRepository;

	public RecipesService(RecipeRepository recipeRepository)
	{
		this.recipeRepository = recipeRepository;
	}
}
