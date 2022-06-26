package com.jackrkern.groceriesbyrecipe.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.RecipeService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
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
		{
			model.addAttribute("recipe", (Recipe) inputFlashMap.get("recipe"));
			System.out.println((Recipe) inputFlashMap.get("recipe"));
		}
		model.addAttribute("amounts", recipeService.getAmounts());
		model.addAttribute("unitsOfMeasurement", recipeService.getUnitsOfMeasurement());
		model.addAttribute("items", itemService.getItems(userService.getPrincipal()));
		return "edit_recipe";
	}

	// Update
	@RequestMapping(value = "/save", method = { RequestMethod.PUT, RequestMethod.GET })
	public RedirectView saveRecipe(Recipe recipe, @RequestParam(value = "txtInstructions")
	String instructions, @RequestParam(value = "txtName")
	String name, RedirectAttributes redirectAttributes)
	{
		recipe.setName(name);
		recipe.setInstructions(instructions);
		recipe.setUser(userService.getPrincipal());
		recipeService.saveRecipe(recipe);
		System.out.println(recipe.getName() + " Edited");
		redirectAttributes.addFlashAttribute("success", recipe.getName() + " Saved");
		return new RedirectView("/recipes");
	}
}