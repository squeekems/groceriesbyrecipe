package com.jackrkern.groceriesbyrecipe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	// Read
	@GetMapping
	public String getShoppingList(Model model)
	{
		model.addAttribute("shoppingList", shoppingListItemService.getShoppingList(userService.getPrincipal()));
		model.addAttribute("activePage", "list");
		model.addAttribute("items", itemService.getItemsSortedByDescription(userService.getPrincipal()));
		model.addAttribute("item", new Item());
		model.addAttribute("shoppingListItem", new ShoppingListItem());
		/**/// Should be in my Settings Controller
		/**/model.addAttribute("aisles", itemService.getAisles());
		/**/model.addAttribute("unitsOfMeasurement", recipeService.getUnitsOfMeasurement());
		/**/model.addAttribute("amounts", recipeService.getAmounts());
		/**/// Should be in my Settings Controller
		return "list";
	}

	// Post
	@PostMapping
	public RedirectView addExistingToShoppingList(ShoppingListItem shoppingListItem, @RequestParam(value = "cmbAddItem")
	Long itemID, @RequestParam(value = "numCount")
	int count, RedirectAttributes redirectAttributes)
	{
		shoppingListItem.setItem(itemService.getItemByID(itemID));
		shoppingListItem.setUser(userService.getPrincipal());
		shoppingListItem.setCount(count);
		shoppingListItemService.saveShoppingListItem(shoppingListItem);
		redirectAttributes.addFlashAttribute("success", shoppingListItem.getCount() + " "
														+ shoppingListItem.getItem().getDescription() + " Added");
		return new RedirectView("/list");
	}

	// Post
	@PostMapping("/add")
	public RedirectView addToShoppingList(Item item, @RequestParam(value = "cmbAddAisle")
	Long aisleID, RedirectAttributes redirectAttributes)
	{
		item.setAisle(itemService.getAisleByID(aisleID));
		item.setUser(userService.getPrincipal());
		itemService.saveItem(item);
		shoppingListItemService.saveShoppingListItem(item);
		redirectAttributes.addFlashAttribute("success", item.getDescription() + " Added");
		return new RedirectView("/list");
	}

	// Delete
	@GetMapping("/remove/{shoppingListItemID}")
	public RedirectView deleteShoppingListItem(@PathVariable(value = "shoppingListItemID")
	Long shoppingListItemID, RedirectAttributes redirectAttributes)
	{
		ShoppingListItem shoppingListItem = shoppingListItemService.getByID(shoppingListItemID);
		shoppingListItemService.removeShoppingListItem(shoppingListItemID, 1);
		System.out.println("1 " + shoppingListItem.getItem().getDescription() + " Removed");
		redirectAttributes.addFlashAttribute(	"success",
												"1 " + shoppingListItem.getItem().getDescription() + " Removed");
		return new RedirectView("/list");
	}

	// Clear
	@GetMapping("/clear")
	public RedirectView clearShoppingList(RedirectAttributes redirectAttributes)
	{
		shoppingListItemService.clearShoppingList(userService.getPrincipal());
		System.out.println("Shopping List Cleared");
		redirectAttributes.addFlashAttribute("success", "Shopping List Cleared");
		return new RedirectView("/list");
	}
}