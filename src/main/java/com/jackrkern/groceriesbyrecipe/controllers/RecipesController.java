package com.jackrkern.groceriesbyrecipe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.RecipeService;
import com.jackrkern.groceriesbyrecipe.models.Aisle;
import com.jackrkern.groceriesbyrecipe.models.Amount;
import com.jackrkern.groceriesbyrecipe.models.Recipe;
import com.jackrkern.groceriesbyrecipe.models.UnitOfMeasurement;

/* @author "Jack Kern" */

@Controller
public class RecipesController
{

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private ItemService itemService;

	@GetMapping("/recipes")
	public String getRecipes(Model model)
	{
		model.addAttribute("activePage", "recipes");
		List<Recipe> recipes = recipeService.getRecipes();
		model.addAttribute("recipes", recipes);
		List<Aisle> aisles = itemService.getAisles();
		model.addAttribute("aisles", aisles);
		List<UnitOfMeasurement> unitsOfMeasurement = recipeService.getUnitsOfMeasurement();
		model.addAttribute("unitsOfMeasurement", unitsOfMeasurement);
		List<Amount> amounts = recipeService.getAmounts();
		model.addAttribute("amounts", amounts);
		return "recipes";
	}
}