/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.controllers;

/**
 * @imports
 */
import com.jackrkern.groceriesbyrecipe.business.RecipeService;
import com.jackrkern.groceriesbyrecipe.business.ShoppingListItemService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.Recipe;
import com.jackrkern.groceriesbyrecipe.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/**
 *  @author Jack Kern <jackrkern@gmail.com>
 */
@Controller
@RequestMapping(RECIPES)
public class RecipesController {

	Logger logger = LoggerFactory.getLogger(RecipesController.class);

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private UserService userService;

	@Autowired
	private ShoppingListItemService shoppingListItemService;

	// Read
	@GetMapping
	public String getRecipes(Model model) {
		model.addAttribute(ACTIVE_PAGE, capitalize(demap(RECIPES)));
		model.addAttribute(demap(RECIPES), recipeService.getRecipes((User) userService.getPrincipal()));
		model.addAttribute(demap(RECIPE), new Recipe());
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(GET)),
				demap(RECIPES)
		}) + PERIOD);
		return demap(RECIPES);
	}

	// Create
	@PostMapping(ADD)
	public RedirectView addRecipe(
			@ModelAttribute Recipe recipe,
			RedirectAttributes redirectAttributes) {
		recipe.setUser((User) userService.getPrincipal());
		recipe = recipeService.saveRecipe(recipe);
		redirectAttributes.addFlashAttribute(demap(RECIPE), recipe);
		redirectAttributes.addFlashAttribute(
				ACTIVE_PAGE, String.format(S_S, capitalize(demap(EDIT)), recipe)
		);
		redirectAttributes.addFlashAttribute(
				SUCCESS, String.format(S_S, recipe, capitalize(pastOf(demap(ADD))))
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(ADD)),
				demap(RECIPE),
				pastOf(CALL),
				recipe.toString()
		}) + PERIOD);
		return new RedirectView(EDIT_RECIPE);
	}

	// Gets Recipe to be Edited
	@GetMapping(GET + RECIPE + PVM_RECIPE_ID)
	@ResponseBody
	public Recipe getRecipeByID(@PathVariable(value = PVV_RECIPE_ID) Long recipeID) {
		return recipeService.getRecipeByID(recipeID);
	}

	// Update
	@GetMapping(EDIT)
	public RedirectView updateRecipe(
			@RequestParam(value = CN_RECIPE_ID) Long recipeID,
			RedirectAttributes redirectAttributes) {
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		redirectAttributes.addFlashAttribute(demap(RECIPE), recipe);
		redirectAttributes.addFlashAttribute(
				ACTIVE_PAGE, String.format(S_S, capitalize(demap(EDIT)), recipe.getName())
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(SELECT),
				demap(RECIPE),
				pastOf(CALL),
				recipe.toString(),
				TO,
				BE,
				pastOf(demap(EDIT))
		}) + PERIOD);
		return new RedirectView(EDIT_RECIPE);
	}

	// AddToShoppingList
	@PostMapping(ADD_TO_SHOPPING_LIST)
	public RedirectView addRecipeToShoppingList(
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		shoppingListItemService.addRecipeToShoppingList(request);
		redirectAttributes.addFlashAttribute(
				SUCCESS,
				String.format(NOUN_VERB_PREPOSITION_NOUN,
						capitalize(pluralOf(demap(ITEM))),
						capitalize(pastOf(demap(ADD))),
						TO,
						capitalize(space(demap(SHOPPING_LIST)))
				)
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(ADD)),
				pluralOf(demap(ITEM)),
				OF,
				demap(RECIPE),
				TO,
				THEIR,
				space(demap(SHOPPING_LIST))
		}) + PERIOD);
		return new RedirectView(RECIPES);
	}

	// Delete
	@GetMapping(REMOVE + PVM_RECIPE_ID)
	public RedirectView deleteRecipe(
			@PathVariable(value = PVV_RECIPE_ID) Long recipeID,
			RedirectAttributes redirectAttributes) {
		// create recipe object to use in feedback
		Recipe recipe = recipeService.getRecipeByID(recipeID);
		recipeService.deleteRecipe(recipeID);
		redirectAttributes.addFlashAttribute(
				SUCCESS, String.format(S_S, recipe, capitalize(pastOf(demap(REMOVE))))
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(DELETE)),
				demap(RECIPE),
				pastOf(CALL),
				recipe.toString()
		}) + PERIOD);
		return new RedirectView(RECIPES);
	}
}