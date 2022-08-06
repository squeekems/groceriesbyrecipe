package com.jackrkern.groceriesbyrecipe.controllers;

import static java.time.LocalDateTime.now;
import static org.springframework.util.StringUtils.capitalize;
import static java.lang.System.out;

import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.RecipeService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.Ingredient;
import com.jackrkern.groceriesbyrecipe.models.Recipe;
import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/* @author "Jack Kern" */

@Controller
public class EditRecipeController
{
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATEPATTERN);

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	// Read
	@GetMapping(EDIT_RECIPE)
	public Object getRecipe(HttpServletRequest request, Model model)
	{
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null)
		{
			model.addAttribute(strMapping(RECIPE), (Recipe) inputFlashMap.get(strMapping(RECIPE)));
			model.addAttribute(strPlural(strMapping(AMOUNT)), recipeService.getAmounts(userService.getPrincipal()));
			model.addAttribute(	strMapping(UNITSOFMEASUREMENT),
								recipeService.getUnitsOfMeasurement(userService.getPrincipal()));
			model.addAttribute(strMapping(ITEMS), itemService.getItemsSortedByDescription(userService.getPrincipal()));
			out.printf(	PERSONsLOADEDsTHEsNOUNsPAGEnl, now().format(dateTimeFormatter),
						userService.getPrincipal() != null ? userService.getPrincipal() : SOMEONE,
						strMapping(EDIT_RECIPE));
			return strMapping(EDIT_RECIPE);
		} else
			return new RedirectView(RECIPES);
	}

	// Update
	@PostMapping(EDIT_RECIPE)
	public RedirectView saveRecipe(@RequestParam(value = TXTSAVERECIPERECIPEID)
	Long recipeID, @RequestParam(value = TXTNAME)
	String name, @RequestParam(value = TXTINSTRUCTIONS)
	String instructions, RedirectAttributes redirectAttributes)
	{
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		recipe.setName(name);
		recipe.setInstructions(instructions);
		recipe.setUser(userService.getPrincipal());
		recipeService.saveRecipe(recipe);
		redirectAttributes.addFlashAttribute(strMapping(RECIPE), recipe);
		redirectAttributes.addFlashAttribute(	ACTIVEPAGE,
												String.format(STRINGsSTRING, capitalize(strMapping(EDIT)), recipe));
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, recipe,
																	capitalize(strPast(strMapping(SAVE)))));
		out.printf(	PERSONsVERBEDsTHEIRsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strPast(strMapping(UPDATE)), RECIPE, recipe);
		return new RedirectView(EDIT_RECIPE);
	}

	// Create
	@PostMapping(EDIT_RECIPE + ADD + INGREDIENT)
	public RedirectView addIngredient(Ingredient ingredient, @RequestParam(value = TXTADDINGREDIENTRECIPEID)
	Long recipeID, @RequestParam(value = TXTADDAMOUNT)
	Long amountID, @RequestParam(value = CMBADDUNITOFMEASUREMENT)
	Long unitOfMeasurementID, @RequestParam(value = CMBADDITEM)
	Long itemID, RedirectAttributes redirectAttributes)
	{
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		ingredient.setAmount(recipeService.getAmountByID(amountID));
		ingredient.setUnitOfMeasurement(recipeService.getUnitOfMeasurementByID(unitOfMeasurementID));
		ingredient.setItem(itemService.getItemByID(itemID));
		recipeService.saveRecipe(recipe, ingredient);
		redirectAttributes.addFlashAttribute(strMapping(RECIPE), recipe);
		redirectAttributes.addFlashAttribute(	ACTIVEPAGE,
												String.format(STRINGsSTRING, capitalize(strMapping(EDIT)), recipe));
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, ingredient,
																	capitalize(strPast(strMapping(ADD)))));
		out.printf(	PERSONsVERBEDsNOUNsPREPOSITIONsTHEIRsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strQuote(strPast(strMapping(ADD))), ingredient, TO, RECIPE, recipe);
		return new RedirectView(EDIT_RECIPE);
	}

	// Gets Ingredient to be Editted
	@RequestMapping(EDIT_RECIPE + GET + INGREDIENT + PVMINGREDIENTID)
	@ResponseBody
	public Ingredient getIngredient(@PathVariable(value = PVVINGREDIENTID)
	Long ingredientID)
	{
		return recipeService.getIngredientByID(ingredientID);
	}

	// Update
	@RequestMapping(value = EDIT_RECIPE + EDIT + INGREDIENT, method = { RequestMethod.PUT, RequestMethod.GET })
	public RedirectView updateIngredient(Ingredient ingredient, @RequestParam(value = TXTEDITINGREDIENTRECIPEID)
	Long recipeID, @RequestParam(value = TXTEDITID)
	Long ingredientID, @RequestParam(value = CMBEDITAMOUNT)
	Long amountID, @RequestParam(value = CMBEDITUNITOFMEASUREMENT)
	Long unitOfMeasurementID, @RequestParam(value = CMBEDITITEM)
	Long itemID, RedirectAttributes redirectAttributes)
	{
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		ingredient = recipeService.getIngredientByID(ingredientID);
		ingredient.setAmount(recipeService.getAmountByID(amountID));
		ingredient.setUnitOfMeasurement(recipeService.getUnitOfMeasurementByID(unitOfMeasurementID));
		ingredient.setItem(itemService.getItemByID(itemID));
		recipeService.saveRecipe(recipe, ingredient);
		redirectAttributes.addFlashAttribute(strMapping(RECIPE), recipe);
		redirectAttributes.addFlashAttribute(	ACTIVEPAGE,
												String.format(STRINGsSTRING, capitalize(strMapping(EDIT)), recipe));
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, ingredient,
																	capitalize(strPast(strMapping(EDIT)))));
		out.printf(	PERSONsVERBEDsNOUNsPREPOSITIONsTHEIRsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strQuote(strPast(strMapping(EDIT))), ingredient, ON, RECIPE, recipe);
		return new RedirectView(EDIT_RECIPE);
	}

	// Delete
	@GetMapping(EDIT_RECIPE + REMOVE + INGREDIENT)
	public RedirectView deleteItem(@RequestParam(value = TXTID)
	Long ingredientID, @RequestParam(value = TXTREMOVEINGREDIENTRECIPEID)
	Long recipeID, RedirectAttributes redirectAttributes)
	{
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		Ingredient ingredient = recipeService.getIngredientByID(ingredientID);
		recipeService.deleteIngredient(ingredientID, recipeID);
		redirectAttributes.addFlashAttribute(strMapping(RECIPE), recipe);
		redirectAttributes.addFlashAttribute(	ACTIVEPAGE,
												String.format(STRINGsSTRING, capitalize(strMapping(EDIT)), recipe));
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, ingredient,
																	capitalize(strPast(strMapping(REMOVE)))));
		out.printf(	PERSONsVERBEDsNOUNsPREPOSITIONsTHEIRsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strQuote(strPast(strMapping(REMOVE))), ingredient, FROM, RECIPE, recipe);
		return new RedirectView(EDIT_RECIPE);
	}
}