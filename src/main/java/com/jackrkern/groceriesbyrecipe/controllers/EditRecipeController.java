package com.jackrkern.groceriesbyrecipe.controllers;

import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.RecipeService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.Ingredient;
import com.jackrkern.groceriesbyrecipe.models.Recipe;
import com.jackrkern.groceriesbyrecipe.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/* @author "Jack Kern" */

@Controller
@RequestMapping(EDIT_RECIPE)
public class EditRecipeController {

	Logger logger = LoggerFactory.getLogger(EditRecipeController.class);

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	// Read
	@GetMapping
	public Object getRecipe(HttpServletRequest request, Model model) {
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null) {
			model.addAttribute(demap(RECIPE), inputFlashMap.get(demap(RECIPE)));
			model.addAttribute(
					pluralOf(demap(AMOUNT)), recipeService.getAmounts((User) userService.getPrincipal())
			);
			model.addAttribute(
					demap(UNITS_OF_MEASUREMENT),
					recipeService.getUnitsOfMeasurement((User) userService.getPrincipal())
			);
			model.addAttribute(
					demap(ITEMS), itemService.getItemsSortedByDescription((User) userService.getPrincipal())
			);
			logger.info(space(new String[]{
					userService.getPrincipal().toString(),
					pastOf(demap(GET)),
					demap(EDIT_RECIPE)
			}) + PERIOD);
			return demap(EDIT_RECIPE);
		} else
			return new RedirectView(RECIPES);
	}

	// Update
	@PostMapping
	public RedirectView saveRecipe(@RequestParam(value = TXT_SAVE_RECIPE_RECIPE_ID)
	Long recipeID, @RequestParam(value = TXT_NAME)
	String name, @RequestParam(value = TXT_INSTRUCTIONS)
	String instructions, RedirectAttributes redirectAttributes) {
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		recipe.setName(name);
		recipe.setInstructions(instructions);
		recipe.setUser((User) userService.getPrincipal());
		recipeService.saveRecipe(recipe);
		redirectAttributes.addFlashAttribute(demap(RECIPE), recipe);
		redirectAttributes.addFlashAttribute(
				ACTIVE_PAGE, String.format(S_S, capitalize(demap(EDIT)), recipe)
		);
		redirectAttributes.addFlashAttribute(
				SUCCESS, String.format(S_S, recipe, capitalize(pastOf(demap(SAVE))))
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(UPDATE)),
				THEIR,
				demap(RECIPE),
				pastOf(CALL),
				recipe.toString()
		}) + PERIOD);
		return new RedirectView(EDIT_RECIPE);
	}

	// Create
	@PostMapping(ADD + INGREDIENT)
	public RedirectView addIngredient(
			Ingredient ingredient,
			@RequestParam(value = TXT_ADD_INGREDIENT_RECIPE_ID) Long recipeID,
			@RequestParam(value = CMB_ADD_AMOUNT) Long amountID,
			@RequestParam(value = CMB_ADD_UNIT_OF_MEASUREMENT) Long unitOfMeasurementID,
			@RequestParam(value = CMB_ADD_ITEM) Long itemID,
			RedirectAttributes redirectAttributes) {
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		ingredient.setAmount(recipeService.getAmountByID(amountID));
		ingredient.setUnitOfMeasurement(recipeService.getUnitOfMeasurementByID(unitOfMeasurementID));
		ingredient.setItem(itemService.getItemByID(itemID));
		recipeService.saveRecipe(recipe, ingredient);
		redirectAttributes.addFlashAttribute(demap(RECIPE), recipe);
		redirectAttributes.addFlashAttribute(
				ACTIVE_PAGE, String.format(S_S, capitalize(demap(EDIT)), recipe)
		);
		redirectAttributes.addFlashAttribute(
				SUCCESS, String.format(S_S, ingredient, capitalize(pastOf(demap(ADD))))
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(ADD)),
				ingredient.toString(),
				TO,
				THEIR,
				demap(RECIPE),
				pastOf(CALL),
				recipe.toString()
		}) + PERIOD);
		return new RedirectView(EDIT_RECIPE);
	}

	// Gets Ingredient to be Edited
	@RequestMapping(GET + INGREDIENT + PVM_INGREDIENT_ID)
	@ResponseBody
	public Ingredient getIngredient(@PathVariable(value = PVV_INGREDIENT_ID) Long ingredientID) {
		return recipeService.getIngredientByID(ingredientID);
	}

	// Update
	@RequestMapping(value = EDIT + INGREDIENT, method = { RequestMethod.PUT, RequestMethod.GET })
	public RedirectView updateIngredient(
			@RequestParam(value = TXT_EDIT_INGREDIENT_RECIPE_ID) Long recipeID,
			@RequestParam(value = TXT_EDIT_ID) Long ingredientID,
			@RequestParam(value = CMB_EDIT_AMOUNT) Long amountID,
			@RequestParam(value = CMB_EDIT_UNIT_OF_MEASUREMENT) Long unitOfMeasurementID,
			@RequestParam(value = CMB_EDIT_ITEM) Long itemID,
			RedirectAttributes redirectAttributes) {
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		Ingredient ingredient = recipeService.getIngredientByID(ingredientID);
		ingredient.setAmount(recipeService.getAmountByID(amountID));
		ingredient.setUnitOfMeasurement(recipeService.getUnitOfMeasurementByID(unitOfMeasurementID));
		ingredient.setItem(itemService.getItemByID(itemID));
		recipeService.saveRecipe(recipe, ingredient);
		redirectAttributes.addFlashAttribute(demap(RECIPE), recipe);
		redirectAttributes.addFlashAttribute(
				ACTIVE_PAGE, String.format(S_S, capitalize(demap(EDIT)), recipe)
		);
		redirectAttributes.addFlashAttribute(
				SUCCESS, String.format(S_S, ingredient, capitalize(pastOf(demap(EDIT))))
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(EDIT)),
				ingredient.toString(),
				ON,
				THEIR,
				demap(RECIPE),
				pastOf(CALL),
				recipe.toString()
		}) + PERIOD);
		return new RedirectView(EDIT_RECIPE);
	}

	// Delete
	@GetMapping(REMOVE + INGREDIENT)
	public RedirectView deleteItem(
			@RequestParam(value = TXT_ID) Long ingredientID,
			@RequestParam(value = TXT_REMOVE_INGREDIENT_RECIPE_ID) Long recipeID,
			RedirectAttributes redirectAttributes) {
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		Ingredient ingredient = recipeService.getIngredientByID(ingredientID);
		recipeService.deleteIngredient(ingredientID, recipeID);
		redirectAttributes.addFlashAttribute(demap(RECIPE), recipe);
		redirectAttributes.addFlashAttribute(
				ACTIVE_PAGE, String.format(S_S, capitalize(demap(EDIT)), recipe)
		);
		redirectAttributes.addFlashAttribute(
				SUCCESS, String.format(S_S, ingredient, capitalize(pastOf(demap(REMOVE))))
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(REMOVE)),
				ingredient.toString(),
				FROM,
				THEIR,
				demap(RECIPE),
				pastOf(CALL),
				recipe.toString()
		}) + PERIOD);
		return new RedirectView(EDIT_RECIPE);
	}
}