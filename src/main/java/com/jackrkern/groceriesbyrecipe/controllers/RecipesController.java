package com.jackrkern.groceriesbyrecipe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.RecipeService;
import com.jackrkern.groceriesbyrecipe.business.ShoppingListItemService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.Recipe;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

/* @author "Jack Kern" */

@Controller
@RequestMapping("/recipes")
public class RecipesController
{

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private UserService userService;

	@Autowired
	private ShoppingListItemService shoppingListItemService;

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
		model.addAttribute("jRecipe", new Recipe());

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
		redirectAttributes.addFlashAttribute("recipe", recipe);
		return new RedirectView("/edit_recipe");
	}

	// Gets Recipe to be Editted
	@GetMapping("/getRecipeByID/{recipeID}")
	@ResponseBody
	public Recipe getRecipeByID(@PathVariable(value = "recipeID")
	Long recipeID)
	{
		return recipeService.getRecipeByID(recipeID);
	}

	// Update
	@GetMapping("/edit")
	public RedirectView updateRecipe(@RequestParam(value = "recipeID")
	Long recipeID, RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute("recipe", recipeService.getRecipeByID(recipeID));
		return new RedirectView("/edit_recipe");
	}

	// AddToShoppingList
	@PostMapping("/addToShoppingList")
	public RedirectView addItemToShoppingList(	Recipe jRecipe,
												RedirectAttributes redirectAttributes) throws JsonException
	{
		//
		shoppingListItemService.saveShoppingListItemsFromRecipeJSON((JsonObject) Jsoner.deserialize(jRecipe.getInstructions()));
		redirectAttributes.addFlashAttribute("success", "Items Added to Shopping List");
		return new RedirectView("/recipes");
	}

	// Delete
	@GetMapping("/remove/{recipeID}")
	public RedirectView deleteItem(@PathVariable(value = "recipeID")
	Long recipeID, RedirectAttributes redirectAttributes)
	{
		// create recipe object to use in feedback
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		recipeService.deleteRecipe(recipeID);
		redirectAttributes.addFlashAttribute("success", recipe.getName() + " Removed");
		return new RedirectView("/recipes");
	}
}