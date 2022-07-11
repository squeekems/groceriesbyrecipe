package com.jackrkern.groceriesbyrecipe.controllers;

import static java.time.LocalDateTime.now;
import static java.lang.System.out;

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
import com.jackrkern.groceriesbyrecipe.business.ShoppingListItemService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.Item;
import com.jackrkern.groceriesbyrecipe.models.ShoppingListItem;
import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

import java.time.format.DateTimeFormatter;

/* @author "Jack Kern" */

@Controller
@RequestMapping
public class ShoppingListItemController
{
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATEPATTERN);

	@Autowired
	private ShoppingListItemService shoppingListItemService;

	@Autowired
	private UserService userService;

	@Autowired
	private ItemService itemService;

	// Read
	@GetMapping(sLIST)
	public String getShoppingList(Model model)
	{
		model.addAttribute(SHOPPINGLIST, shoppingListItemService.getShoppingList(userService.getPrincipal()));
		model.addAttribute(ACTIVEPAGE, "Shopping List");
		model.addAttribute(ITEMS, itemService.getItemsSortedByDescription(userService.getPrincipal()));
		model.addAttribute(ITEM, new Item());
		model.addAttribute(SHOPPINGLISTITEM, new ShoppingListItem());
		model.addAttribute(AISLES, itemService.getAisles(userService.getPrincipal()));
		out.printf(	PERSONsLOADEDsTHEsNOUNsPAGEnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, LIST);
		return LIST;
	}

	// Post
	@PostMapping(sLIST)
	public RedirectView addExistingToShoppingList(ShoppingListItem shoppingListItem, @RequestParam(value = "cmbAddItem")
	Long itemID, @RequestParam(value = "numCount")
	int count, RedirectAttributes redirectAttributes)
	{
		shoppingListItem.setItem(itemService.getItemByID(itemID));
		shoppingListItem.setUser(userService.getPrincipal());
		shoppingListItem.setCount(count);
		shoppingListItemService.saveShoppingListItem(shoppingListItem);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, shoppingListItem, cADDED));
		out.printf(	PERSONsVERBEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, qADDEDq,
					shoppingListItem);
		return new RedirectView(sLIST);
	}

	// Post
	@PostMapping(sLIST + sADD)
	public RedirectView addToShoppingList(Item item, @RequestParam(value = "cmbAddAisle")
	Long aisleID, RedirectAttributes redirectAttributes)
	{
		item.setAisle(itemService.getAisleByID(aisleID));
		item.setUser(userService.getPrincipal());
		itemService.saveItem(item);
		shoppingListItemService.saveShoppingListItem(item);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, item, cADDED));
		out.printf(	PERSONsVERBEDsANsOBJECTsCALLEDsNOUNsANDsVERBEDsITsTOsTHEIRsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, CREATED, ITEM, item,
					ADDED, SHOPPINGsLIST);
		return new RedirectView(sLIST);
	}

	// Delete
	@GetMapping(sLIST + sREMOVE + "/{shoppingListItemID}")
	public RedirectView deleteShoppingListItem(@PathVariable(value = "shoppingListItemID")
	Long shoppingListItemID, RedirectAttributes redirectAttributes)
	{
		ShoppingListItem shoppingListItem = shoppingListItemService.getByID(shoppingListItemID);
		shoppingListItemService.removeShoppingListItem(shoppingListItemID, 1);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, "1 " + shoppingListItem.getItem(),
																	cREMOVED));
		out.printf(	PERSONsVERBEDsAsNOUNsFROMsTHEIRsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, qREMOVEDq,
					shoppingListItem.getItem(), SHOPPINGsLIST);
		return new RedirectView(sLIST);
	}

	// Clear
	@GetMapping(sLIST + sCLEAR)
	public RedirectView clearShoppingList(RedirectAttributes redirectAttributes)
	{
		shoppingListItemService.clearShoppingList(userService.getPrincipal());
		out.println("Shopping List Cleared");
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, cSHOPPINGscLIST, cCLEARED));
		out.println((userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE)
					+ " cleared their shopping list.");
		out.printf(	PERSONsVERBEDsTHEIRsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, CLEARED, SHOPPINGsLIST);
		return new RedirectView(sLIST);
	}
}