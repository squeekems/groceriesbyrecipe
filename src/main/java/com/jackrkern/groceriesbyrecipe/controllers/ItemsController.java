package com.jackrkern.groceriesbyrecipe.controllers;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.RecipeService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.Item;

/* @author "Jack Kern" */

@Controller
public class ItemsController
{
	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	@Autowired
	private RecipeService recipeService;

	@GetMapping("/items")
	public String getItems(HttpServletRequest request, String username, String password, Model model)
	{
		model.addAttribute("items", itemService.getItems(userService.getPrincipal()));

		model.addAttribute("item", new Item());

		model.addAttribute("activePage", "items");

		model.addAttribute("aisles", itemService.getAisles());
		model.addAttribute("unitsOfMeasurement", recipeService.getUnitsOfMeasurement());
		model.addAttribute("amounts", recipeService.getAmounts());
		return "items";
	}

	// Add
	@PostMapping("/items")
	public RedirectView addItem(Principal principal, Item item, @RequestParam(value = "cmbAisle")
	Long aisleID)
	{
		item.setAisle(itemService.getAisleByID(aisleID));
		item.setUser(userService.getPrincipal());
		itemService.addItem(item);
		System.out.println(item + " Added");
		return new RedirectView("/items");
	}

	// Delete
	@GetMapping("/items/{itemID}")
	public String deleteItem(@PathVariable(value = "itemID")
	Long itemID)
	{
		itemService.deleteItem(itemID);
		System.out.println(itemID + " Deleted");
		return "items";
	}

//	@GetMapping("/items/selected")
//	public String passItemID(Model model)
//	{
//		model.att
//		return "items";
//	}

//	@DeleteMapping("/items")
//	public String deleteItem(Long itemID, Model model)
//	{
//		itemService.deleteItem(itemID);
//		addItemAttributes(model);
//		return "items";
//	}
}