package com.jackrkern.groceriesbyrecipe.controllers;

import static java.time.LocalDateTime.now;
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
	@GetMapping(sEDIT_RECIPE)
	public String getRecipe(HttpServletRequest request, Model model)
	{
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null)
			model.addAttribute(RECIPE, (Recipe) inputFlashMap.get(RECIPE));
		model.addAttribute(AMOUNTS, recipeService.getAmounts(userService.getPrincipal()));
		model.addAttribute(UNITSOFMEASUREMENT, recipeService.getUnitsOfMeasurement(userService.getPrincipal()));
		model.addAttribute(ITEMS, itemService.getItemsSortedByDescription(userService.getPrincipal()));
		out.printf(	PERSONsLOADEDsTHEsNOUNsPAGEnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, EDIT_RECIPE);
		return EDIT_RECIPE;
	}

	// Update
	@PostMapping(sEDIT_RECIPE)
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
		redirectAttributes.addFlashAttribute(RECIPE, recipe);
		redirectAttributes.addFlashAttribute(ACTIVEPAGE, String.format(STRINGsSTRING, cEDIT, recipe));
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, recipe, cSAVED));
		out.printf(	PERSONsVERBEDsTHEIRsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, UPDATED, RECIPE,
					recipe);
		return new RedirectView(sEDIT_RECIPE);
	}

	// Create
	@PostMapping(sEDIT_RECIPE + sADD + cINGREDIENT)
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
		redirectAttributes.addFlashAttribute(RECIPE, recipe);
		redirectAttributes.addFlashAttribute(ACTIVEPAGE, String.format(STRINGsSTRING, cEDIT, recipe));
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, ingredient, cADDED));
		out.printf(	PERSONsVERBEDsNOUNsPREPOSITIONsTHEIRsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, qADDEDq, ingredient, TO,
					RECIPE, recipe);
		return new RedirectView(sEDIT_RECIPE);
	}

	// Gets Ingredient to be Editted
	@RequestMapping(sEDIT_RECIPE + sGET + cINGREDIENT + cBYID + "/{ingredientID}")
	@ResponseBody
	public Ingredient getItemByID(@PathVariable(value = "ingredientID")
	Long ingredientID)
	{
		return recipeService.getIngredientByID(ingredientID);
	}

	// Update
	@RequestMapping(value = sEDIT_RECIPE + sEDIT + cINGREDIENT, method = { RequestMethod.PUT, RequestMethod.GET })
	public RedirectView updateItem(Ingredient ingredient, @RequestParam(value = "txtEditIngredientRecipeID")
	Long recipeID, @RequestParam(value = "txtEditID")
	Long ingredientID, @RequestParam(value = "cmbEditAmount")
	Long amountID, @RequestParam(value = "cmbEditUnitOfMeasurement")
	Long unitOfMeasurementID, @RequestParam(value = "cmbEditItem")
	Long itemID, RedirectAttributes redirectAttributes)
	{
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		ingredient = recipeService.getIngredientByID(ingredientID);
		ingredient.setAmount(recipeService.getAmountByID(amountID));
		ingredient.setUnitOfMeasurement(recipeService.getUnitOfMeasurementByID(unitOfMeasurementID));
		ingredient.setItem(itemService.getItemByID(itemID));
		recipeService.saveRecipe(recipe, ingredient);
		redirectAttributes.addFlashAttribute(RECIPE, recipe);
		redirectAttributes.addFlashAttribute(ACTIVEPAGE, String.format(STRINGsSTRING, cEDIT, recipe));
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, ingredient, cEDITED));
		out.printf(	PERSONsVERBEDsNOUNsPREPOSITIONsTHEIRsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, qEDITEDq, ingredient,
					ON, RECIPE, recipe);
		return new RedirectView(sEDIT_RECIPE);
	}

	// Delete
	@GetMapping(sEDIT_RECIPE + sREMOVE + cINGREDIENT)
	public RedirectView deleteItem(@RequestParam(value = "txtID")
	Long ingredientID, @RequestParam(value = "txtRemoveIngredientRecipeID")
	Long recipeID, RedirectAttributes redirectAttributes)
	{
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		Ingredient ingredient = recipeService.getIngredientByID(ingredientID);
		recipeService.deleteIngredient(ingredientID, recipeID);
		redirectAttributes.addFlashAttribute(RECIPE, recipe);
		redirectAttributes.addFlashAttribute(ACTIVEPAGE, String.format(STRINGsSTRING, cEDIT, recipe));
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, ingredient, cREMOVED));
		out.printf(	PERSONsVERBEDsNOUNsPREPOSITIONsTHEIRsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, qREMOVEDq, ingredient,
					FROM, RECIPE, recipe);
		return new RedirectView(sEDIT_RECIPE);
	}
}