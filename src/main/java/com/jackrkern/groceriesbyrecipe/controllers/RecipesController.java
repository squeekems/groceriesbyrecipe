package com.jackrkern.groceriesbyrecipe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.RecipeService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.Recipe;

/* @author "Jack Kern" */

@Controller
public class RecipesController
{

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private UserService userService;

	/**/// Should be in my Settings Controller
	/**/@Autowired
	/**/private ItemService itemService;
	/**/// Should be in my Settings Controller

	@GetMapping("/recipes")
	public String getRecipes(Model model)
	{
		model.addAttribute("activePage", "recipes");
		List<Recipe> recipes = recipeService.getRecipes(userService.getPrincipal());
		model.addAttribute("recipes", recipes);
		/**/// Should be in my Settings Controller
		/**/model.addAttribute("aisles", itemService.getAisles());
		/**/model.addAttribute("unitsOfMeasurement", recipeService.getUnitsOfMeasurement());
		/**/model.addAttribute("amounts", recipeService.getAmounts());
		/**/// Should be in my Settings Controller
		return "recipes";
	}
}