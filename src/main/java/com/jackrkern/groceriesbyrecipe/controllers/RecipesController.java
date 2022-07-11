package com.jackrkern.groceriesbyrecipe.controllers;

import static java.time.LocalDateTime.now;
import static java.lang.System.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.jackrkern.groceriesbyrecipe.business.RecipeService;
import com.jackrkern.groceriesbyrecipe.business.ShoppingListItemService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.Recipe;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

/* @author "Jack Kern" */

@Controller
public class RecipesController
{
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATEPATTERN);

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private UserService userService;

	@Autowired
	private ShoppingListItemService shoppingListItemService;

	// Read
	@GetMapping(sRECIPES)
	public String getRecipes(Model model)
	{
		model.addAttribute(ACTIVEPAGE, cRECIPES);
		model.addAttribute(RECIPES, recipeService.getRecipes(userService.getPrincipal()));
		model.addAttribute(RECIPE, new Recipe());
		model.addAttribute(jRECIPE, new Recipe());
		out.printf(	PERSONsLOADEDsTHEsNOUNsPAGEnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, RECIPES);
		return RECIPES;
	}

	// Create
	@PostMapping(sRECIPES + sADD)
	public RedirectView addRecipe(@ModelAttribute
	Recipe recipe, RedirectAttributes redirectAttributes)
	{
		recipe.setUser(userService.getPrincipal());
		recipeService.saveRecipe(recipe);
		redirectAttributes.addFlashAttribute(RECIPE, recipe);
		redirectAttributes.addFlashAttribute(ACTIVEPAGE, "Edit " + recipe);
		redirectAttributes.addFlashAttribute(SUCCESS, recipe + " Added");
		out.printf(	PERSONsCREATEDsAsNEWsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, RECIPE, recipe);
		return new RedirectView(sEDIT_RECIPE);
	}

	// Gets Recipe to be Editted
	@GetMapping(sRECIPES + sGET + cRECIPE + cBYID + "/{recipeID}")
	@ResponseBody
	public Recipe getRecipeByID(@PathVariable(value = "recipeID")
	Long recipeID)
	{
		return recipeService.getRecipeByID(recipeID);
	}

	// Update
	@GetMapping(sRECIPES + sEDIT)
	public RedirectView updateRecipe(@RequestParam(value = "recipeID")
	Long recipeID, RedirectAttributes redirectAttributes)
	{
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		redirectAttributes.addFlashAttribute(RECIPE, recipe);
		redirectAttributes.addFlashAttribute(ACTIVEPAGE, "Edit " + recipe.getName());
		out.printf(	PERSONsSELECTEDsAsNOUNsCALLEDsNOUNsTOsBEsVERBEDnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, RECIPE, recipe, EDITED);
		return new RedirectView(sEDIT_RECIPE);
	}

//	// AddToShoppingList
//	@PostMapping(sRECIPES + sADDTOSHOPPINGLIST)
//	public RedirectView addRecipeToShoppingList(Recipe jRecipe,
//												RedirectAttributes redirectAttributes) throws JsonException
//	{
//		//
//		shoppingListItemService.saveShoppingListItemsFromRecipeJSON((JsonObject) Jsoner.deserialize(jRecipe.getInstructions()));
//		redirectAttributes.addFlashAttribute(SUCCESS, "Items Added to Shopping List");
//		out.printf(	PERSONsVERBEDsALLsTHEsNOUNSsLISTEDsFORsAsNOUNsTOsTHEIRsNOUNnl, now().format(dateTimeFormatter),
//					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, ADDED, ITEM, RECIPE,
//					SHOPPINGsLIST);
//		return new RedirectView(sRECIPES);
//	}

	// AddToShoppingList
	@PostMapping(sRECIPES + sADDTOSHOPPINGLIST)
	public RedirectView addRecipeToShoppingList(Recipe jRecipe, HttpServletRequest request,
												RedirectAttributes redirectAttributes)
	{
		shoppingListItemService.addRecipeToShoppingList(request);

		redirectAttributes.addFlashAttribute(SUCCESS, "Items Added to Shopping List");
		out.printf(	PERSONsVERBEDsALLsTHEsNOUNSsLISTEDsFORsAsNOUNsTOsTHEIRsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, ADDED, ITEM, RECIPE,
					SHOPPINGsLIST);
		return new RedirectView(sRECIPES);
	}

	// Delete
	@GetMapping(sRECIPES + sREMOVE + "/{recipeID}")
	public RedirectView deleteRecipe(@PathVariable(value = "recipeID")
	Long recipeID, RedirectAttributes redirectAttributes)
	{
		// create recipe object to use in feedback
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		recipeService.deleteRecipe(recipeID);
		redirectAttributes.addFlashAttribute(SUCCESS, recipe + " Removed");
		out.printf(	PERSONsVERBEDsAsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, DELETED, RECIPE,
					recipe);
		return new RedirectView(sRECIPES);
	}
}