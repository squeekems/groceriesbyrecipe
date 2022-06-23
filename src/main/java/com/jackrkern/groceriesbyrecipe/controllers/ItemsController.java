package com.jackrkern.groceriesbyrecipe.controllers;

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
import org.springframework.web.servlet.view.RedirectView;

import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.RecipeService;
import com.jackrkern.groceriesbyrecipe.business.ShoppingListItemService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.Item;
import com.jackrkern.groceriesbyrecipe.models.ShoppingListItem;

/* @author "Jack Kern" */

@Controller
@RequestMapping("/items")
public class ItemsController
{
	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	@Autowired
	private ShoppingListItemService shoppingListItemService;

	/**/// Should be in my Settings Controller
	/**/@Autowired
	/**/private RecipeService recipeService;
	/**/// Should be in my Settings Controller

	@GetMapping
	public String getItems(Model model)
	{
		model.addAttribute("items", itemService.getItems(userService.getPrincipal()));
		model.addAttribute("item", new Item());
		model.addAttribute("activePage", "items");
		model.addAttribute("aisles", itemService.getAisles());
		model.addAttribute("shoppingList", new ShoppingListItem());
		/**/// Should be in my Settings Controller
		/**/model.addAttribute("aisles", itemService.getAisles());
		/**/model.addAttribute("unitsOfMeasurement", recipeService.getUnitsOfMeasurement());
		/**/model.addAttribute("amounts", recipeService.getAmounts());
		/**/// Should be in my Settings Controller
		return "items";
	}

	// Gets Item to be Editted or Added to Shopping List
	@RequestMapping("/getItemByID/{itemID}")
	@ResponseBody
	public Item getItemByID(@PathVariable(value = "itemID")
	Long itemID)
	{
		return itemService.getItemByID(itemID);
	}

	// Add
	@PostMapping("/add")
	public RedirectView addItem(Item item, @RequestParam(value = "cmbAddAisle")
	Long aisleID, RedirectAttributes redirectAttributes)
	{
		item.setAisle(itemService.getAisleByID(aisleID));
		item.setUser(userService.getPrincipal());
		itemService.saveItem(item);
		System.out.println(item.getDescription() + " Added");
		redirectAttributes.addFlashAttribute("success", item.getDescription() + " Added");
		return new RedirectView("/items");
	}

	// Edit
	@RequestMapping(value = "/edit", method = { RequestMethod.PUT, RequestMethod.GET })
	public RedirectView editItem(Item item, @RequestParam(value = "cmbEditAisle")
	Long aisleID, RedirectAttributes redirectAttributes)
	{
		item.setAisle(itemService.getAisleByID(aisleID));
		item.setUser(userService.getPrincipal());
		itemService.saveItem(item);
		System.out.println(item + " Edited");
		redirectAttributes.addFlashAttribute("success", item.getDescription() + " Edited");
		return new RedirectView("/items");
	}

	// Delete
	@GetMapping("/remove/{itemID}")
	public RedirectView deleteItem(@PathVariable(value = "itemID")
	Long itemID, RedirectAttributes redirectAttributes)
	{
		// create item object to use in feedback
		Item item = itemService.getItemByID(itemID);
		itemService.deleteItem(itemID);
		System.out.println(item.getDescription() + " Removed");
		redirectAttributes.addFlashAttribute("success", item.getDescription() + " Removed");
		return new RedirectView("/items");
	}

	// AddToShoppingList
	@PostMapping("/addToShoppingList")
	public RedirectView addItemToShoppingList(ShoppingListItem shoppingListItem, @RequestParam(value = "txtID")
	Long itemID, @RequestParam(value = "numCount")
	int count, RedirectAttributes redirectAttributes)
	{
		shoppingListItem.setItem(itemService.getItemByID(itemID));
		shoppingListItem.setUser(userService.getPrincipal());
		shoppingListItem.setCount(count);
		shoppingListItemService.saveShoppingListItem(shoppingListItem);
		System.out.println(shoppingListItem.getItem().getDescription() + " Added to Shopping List");
		redirectAttributes.addFlashAttribute("success", shoppingListItem.getItem().getDescription()
														+ " Added to Shopping List");
		return new RedirectView("/items");
	}
}