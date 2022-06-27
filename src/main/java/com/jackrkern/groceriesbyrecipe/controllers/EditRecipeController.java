package com.jackrkern.groceriesbyrecipe.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.RecipeService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.Ingredient;
import com.jackrkern.groceriesbyrecipe.models.Recipe;

/* @author "Jack Kern" */

@Controller
@RequestMapping("/edit_recipe")
public class EditRecipeController
{
	@Autowired
	private RecipeService recipeService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	// Read
	@GetMapping
	public String getRecipe(HttpServletRequest request, Model model)
	{
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null)
			model.addAttribute("recipe", (Recipe) inputFlashMap.get("recipe"));
		model.addAttribute("amounts", recipeService.getAmounts());
		model.addAttribute("unitsOfMeasurement", recipeService.getUnitsOfMeasurement());
		model.addAttribute("items", itemService.getItemsSortedByDescription(userService.getPrincipal()));
		return "edit_recipe";
	}

	// Update
	@PostMapping
	public RedirectView saveRecipe(@RequestParam(value = "txtSaveRecipeRecipeID")
	Long recipeID, @RequestParam(value = "txtName")
	String name, @RequestParam(value = "txtInstructions")
	String instructions, RedirectAttributes redirectAttributes)
	{
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		recipe.setName(name);
		recipe.setInstructions(instructions);
		recipe.setUser(userService.getPrincipal());
		recipeService.saveRecipe(recipe);
		System.out.println(recipe.getName() + " Saved");
		redirectAttributes.addFlashAttribute("success", recipe.getName() + " Saved");
		redirectAttributes.addFlashAttribute("recipe", recipe);
		return new RedirectView("/edit_recipe");
	}

	// Post
	@PostMapping("/addIngredient")
	public RedirectView addIngredient(Ingredient ingredient, @RequestParam(value = "txtAddIngredientRecipeID")
	Long recipeID, @RequestParam(value = "cmbAddAmount")
	Long amountID, @RequestParam(value = "cmbAddUnitOfMeasurement")
	Long unitOfMeasurementID, @RequestParam(value = "cmbAddItem")
	Long itemID, RedirectAttributes redirectAttributes)
	{
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		ingredient.setAmount(recipeService.getAmountByID(amountID));
		ingredient.setUnitOfMeasurement(recipeService.getUnitOfMeasurementByID(unitOfMeasurementID));
		ingredient.setItem(itemService.getItemByID(itemID));
		recipeService.saveRecipe(recipe, ingredient);
		redirectAttributes.addFlashAttribute("recipe", recipe);
		return new RedirectView("/edit_recipe");
	}
}