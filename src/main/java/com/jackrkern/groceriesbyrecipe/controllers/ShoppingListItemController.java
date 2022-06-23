package com.jackrkern.groceriesbyrecipe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.RecipeService;
import com.jackrkern.groceriesbyrecipe.business.ShoppingListItemService;
import com.jackrkern.groceriesbyrecipe.business.UserService;

/* @author "Jack Kern" */

@Controller
@RequestMapping("/list")
public class ShoppingListItemController
{
	@Autowired
	ShoppingListItemService shoppingListItemService;

	@Autowired
	private UserService userService;

	/**/// Should be in my Settings Controller
	/**/@Autowired
	/**/private ItemService itemService;
	/**/
	/**/@Autowired
	/**/private RecipeService recipeService;
	/**/// Should be in my Settings Controller

	@GetMapping
	public String getShoppingList(Model model)
	{
		model.addAttribute("shoppingList", shoppingListItemService.getShoppingList(userService.getPrincipal()));
		model.addAttribute("activePage", "list");
		/**/// Should be in my Settings Controller
		/**/model.addAttribute("aisles", itemService.getAisles());
		/**/model.addAttribute("unitsOfMeasurement", recipeService.getUnitsOfMeasurement());
		/**/model.addAttribute("amounts", recipeService.getAmounts());
		/**/// Should be in my Settings Controller
		return "list";
	}
}