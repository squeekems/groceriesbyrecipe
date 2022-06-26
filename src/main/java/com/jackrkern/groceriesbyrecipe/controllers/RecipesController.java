package com.jackrkern.groceriesbyrecipe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.RecipeService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.Recipe;

/* @author "Jack Kern" */

@Controller
@RequestMapping("/recipes")
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

	// Read
	@GetMapping
	public String getRecipes(Model model)
	{
		model.addAttribute("activePage", "recipes");
		model.addAttribute("recipes", recipeService.getRecipes(userService.getPrincipal()));
		model.addAttribute("recipe", new Recipe());
		/**/// Should be in my Settings Controller
		/**/model.addAttribute("aisles", itemService.getAisles());
		/**/model.addAttribute("unitsOfMeasurement", recipeService.getUnitsOfMeasurement());
		/**/model.addAttribute("amounts", recipeService.getAmounts());
		/**/// Should be in my Settings Controller
		return "recipes";
	}

	// Create
	@PostMapping("/add")
	public RedirectView addRecipe(@ModelAttribute
	Recipe recipe, RedirectAttributes redirectAttributes)
	{
		recipe.setUser(userService.getPrincipal());
		recipeService.saveRecipe(recipe);
		System.out.println(recipe.getName() + " Added");
		redirectAttributes.addFlashAttribute("recipe", recipe);
		return new RedirectView("/edit_recipe");
	}

	// Update
	@GetMapping(value = "/edit/{recipeID}")
	public String updateRecipe(@PathVariable(value = "recipeID")
	Long recipeID, Model model)
	{
		model.addAttribute("recipe", recipeService.getRecipeByID(recipeID));
		System.out.println(recipeService.getRecipeByID(recipeID));
		return "edit_recipe";
	}
}