package com.jackrkern.groceriesbyrecipe.controllers;

import static java.time.LocalDateTime.now;
import static org.springframework.util.StringUtils.capitalize;
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
	@GetMapping(RECIPES)
	public String getRecipes(Model model)
	{
		model.addAttribute(ACTIVEPAGE, capitalize(strMapping(RECIPES)));
		model.addAttribute(strMapping(RECIPES), recipeService.getRecipes(userService.getPrincipal()));
		model.addAttribute(strMapping(RECIPE), new Recipe());
		out.printf(	PERSONsLOADEDsTHEsNOUNsPAGEnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strMapping(RECIPES));
		return strMapping(RECIPES);
	}

	// Create
	@PostMapping(RECIPES + ADD)
	public RedirectView addRecipe(@ModelAttribute
	Recipe recipe, RedirectAttributes redirectAttributes)
	{
		recipe.setUser(userService.getPrincipal());
		recipe = recipeService.saveRecipe(recipe);
		redirectAttributes.addFlashAttribute(strMapping(RECIPE), recipe);
		redirectAttributes.addFlashAttribute(	ACTIVEPAGE,
												String.format(STRINGsSTRING, capitalize(strMapping(EDIT)), recipe));
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, recipe,
																	capitalize(strPast(strMapping(ADD)))));
		out.printf(	PERSONsCREATEDsAsNEWsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strMapping(RECIPE), recipe);
		return new RedirectView(EDIT_RECIPE);
	}

	// Gets Recipe to be Editted
	@GetMapping(RECIPES + GET + RECIPE + PVMRECIPEID)
	@ResponseBody
	public Recipe getRecipeByID(@PathVariable(value = PVVRECIPEID)
	Long recipeID)
	{
		return recipeService.getRecipeByID(recipeID);
	}

	// Update
	@GetMapping(RECIPES + EDIT)
	public RedirectView updateRecipe(@RequestParam(value = RECIPEID)
	Long recipeID, RedirectAttributes redirectAttributes)
	{
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		redirectAttributes.addFlashAttribute(strMapping(RECIPE), recipe);
		redirectAttributes.addFlashAttribute(ACTIVEPAGE, String.format(	STRINGsSTRING, capitalize(strMapping(EDIT)),
																		recipe.getName()));
		out.printf(	PERSONsSELECTEDsAsNOUNsCALLEDsNOUNsTOsBEsVERBEDnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strMapping(RECIPE), recipe, strPast(strMapping(EDIT)));
		return new RedirectView(EDIT_RECIPE);
	}

//	// AddToShoppingList
//	@PostMapping(RECIPES + ADDTOSHOPPINGLIST)
//	public RedirectView addRecipeToShoppingList(Recipe jRecipe,
//												RedirectAttributes redirectAttributes) throws JsonException
//	{
//		//
//		shoppingListItemService.saveShoppingListItemsFromRecipeJSON((JsonObject) Jsoner.deserialize(jRecipe.getInstructions()));
//		redirectAttributes.addFlashAttribute(SUCCESS, "Items Added to Shopping List");
//		out.printf(	PERSONsVERBEDsALLsTHEsNOUNSsLISTEDsFORsAsNOUNsTOsTHEIRsNOUNnl, now().format(dateTimeFormatter),
//					userService.getPrincipal() != null ? userService.getPrincipal() : SOMEONE, ADDED, ITEM, RECIPE,
//					SHOPPINGsLIST);
//		return new RedirectView(RECIPES);
//	}

	// AddToShoppingList
	@PostMapping(RECIPES + ADDTOSHOPPINGLIST)
	public RedirectView addRecipeToShoppingList(HttpServletRequest request, RedirectAttributes redirectAttributes)
	{
		shoppingListItemService.addRecipeToShoppingList(request);

		redirectAttributes.addFlashAttribute(	SUCCESS,
												String.format(	NOUNsVERBEDsPREPOSITIONsNOUN,
																capitalize(strPlural(strMapping(ITEM))),
																capitalize(strPast(strMapping(ADD))), TO,
																capitalize(strSpace(strMapping(SHOPPINGLIST)))));
		out.printf(	PERSONsVERBEDsALLsTHEsNOUNSsLISTEDsFORsAsNOUNsTOsTHEIRsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strPast(strMapping(ADD)), strMapping(ITEM), strMapping(RECIPE), strSpace(strMapping(SHOPPINGLIST)));
		return new RedirectView(RECIPES);
	}

	// Delete
	@GetMapping(RECIPES + REMOVE + PVMRECIPEID)
	public RedirectView deleteRecipe(@PathVariable(value = PVVRECIPEID)
	Long recipeID, RedirectAttributes redirectAttributes)
	{
		// create recipe object to use in feedback
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		recipeService.deleteRecipe(recipeID);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, recipe,
																	capitalize(strPast(strMapping(REMOVE)))));
		out.printf(	PERSONsVERBEDsAsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strPast(strMapping(DELETE)), strMapping(RECIPE), recipe);
		return new RedirectView(RECIPES);
	}
}