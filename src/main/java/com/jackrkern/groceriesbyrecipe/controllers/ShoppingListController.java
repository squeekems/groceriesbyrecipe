package com.jackrkern.groceriesbyrecipe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.RecipeService;
import com.jackrkern.groceriesbyrecipe.business.ShoppingListService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.Aisle;
import com.jackrkern.groceriesbyrecipe.models.Amount;
import com.jackrkern.groceriesbyrecipe.models.UnitOfMeasurement;

/* @author "Jack Kern" */

@Controller
public class ShoppingListController
{
	@Autowired
	ShoppingListService shoppingListService;

	@Autowired
	private UserService userService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private RecipeService recipeService;

	@GetMapping("/list")
	public String getList(Model model)
	{
		model.addAttribute("shoppingLists", shoppingListService.getShoppingLists(userService.getPrincipal()));
		model.addAttribute("activePage", "list");
		List<Aisle> aisles = itemService.getAisles();
		model.addAttribute("aisles", aisles);
		List<UnitOfMeasurement> unitsOfMeasurement = recipeService.getUnitsOfMeasurement();
		model.addAttribute("unitsOfMeasurement", unitsOfMeasurement);
		List<Amount> amounts = recipeService.getAmounts();
		model.addAttribute("amounts", amounts);
		return "list";
	}
}