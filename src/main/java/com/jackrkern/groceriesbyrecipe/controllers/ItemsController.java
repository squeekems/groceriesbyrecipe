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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
public class ItemsController
{
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATEPATTERN);

	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	@Autowired
	private ShoppingListItemService shoppingListItemService;

	// Read
	@GetMapping(sITEMS)
	public String getItems(Model model)
	{
		model.addAttribute(ITEMS, itemService.getItemsSortedByAisle(userService.getPrincipal()));
		model.addAttribute(ITEM, new Item());
		model.addAttribute(ACTIVEPAGE, "Store Items");
		model.addAttribute(AISLES, itemService.getAisles(userService.getPrincipal()));
		model.addAttribute(SHOPPINGLIST, new ShoppingListItem());
		out.printf(	PERSONsLOADEDsTHEsNOUNsPAGEnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, ITEMS);
		return ITEMS;
	}

	// Create
	@PostMapping(sITEMS + sADD)
	public RedirectView addItem(Item item, @RequestParam(value = "cmbAddAisle")
	Long aisleID, @RequestParam(value = "chkAddToShoppingList", required = false)
	String blnAddToShoppingList, RedirectAttributes redirectAttributes)
	{
		item.setAisle(itemService.getAisleByID(aisleID));
		item.setUser(userService.getPrincipal());
		item = itemService.saveItem(item);
		out.printf(	PERSONsCREATEDsAsNEWsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, ITEM, item);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, item, cADDED));
		if (blnAddToShoppingList != null)
		{
			ShoppingListItem shoppingListItem = new ShoppingListItem();
			shoppingListItem.setItem(item);
			shoppingListItem.setUser(userService.getPrincipal());
			shoppingListItem.setCount(1);
			shoppingListItemService.saveShoppingListItem(shoppingListItem);
			out.printf(	PERSONsVERBEDsNOUNsTOsTHEIRsNOUN, now().format(dateTimeFormatter),
						userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, ADDED,
						shoppingListItem, SHOPPINGsLIST);
		}
		return new RedirectView(sITEMS);
	}

	// Update
	@RequestMapping(value = sITEMS + sEDIT, method = { RequestMethod.PUT, RequestMethod.GET })
	public RedirectView updateItem(Item item, @RequestParam(value = "cmbEditAisle")
	Long aisleID, RedirectAttributes redirectAttributes)
	{
		item.setAisle(itemService.getAisleByID(aisleID));
		item.setUser(userService.getPrincipal());
		itemService.saveItem(item);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, item, cEDITED));
		out.printf(	PERSONsVERBEDsANsOBJECTsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, UPDATED, ITEM, item);
		return new RedirectView(sITEMS);
	}

	// Delete
	@GetMapping(sITEMS + sREMOVE + "/{itemID}")
	public RedirectView deleteItem(@PathVariable(value = "itemID")
	Long itemID, RedirectAttributes redirectAttributes)
	{
		// create item object to use in feedback
		Item item = itemService.getItemByID(itemID);
		itemService.deleteItem(itemID);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, item, cREMOVED));
		out.printf(	PERSONsVERBEDsANsOBJECTsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, DELETED, ITEM, item);
		return new RedirectView(sITEMS);
	}

	// Gets Item to be Editted or Added to Shopping List
	@RequestMapping(sITEMS + sGET + cITEM + cBYID + "/{itemID}")
	@ResponseBody
	public Item getItemByID(@PathVariable(value = "itemID")
	Long itemID)
	{
		return itemService.getItemByID(itemID);
	}

	// AddToShoppingList
	@PostMapping(sITEMS + sADDTOSHOPPINGLIST)
	public RedirectView addItemToShoppingList(ShoppingListItem shoppingListItem, @RequestParam(value = "txtID")
	Long itemID, @RequestParam(value = "numCount")
	int count, @RequestParam(value = "txtAddToShoppingListScrollValue")
	double intScrollValue, RedirectAttributes redirectAttributes)
	{
		shoppingListItem.setItem(itemService.getItemByID(itemID));
		shoppingListItem.setUser(userService.getPrincipal());
		shoppingListItem.setCount(count);
		shoppingListItemService.saveShoppingListItem(shoppingListItem);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, shoppingListItem,
																	" Added to Shopping List"));
		redirectAttributes.addFlashAttribute(SCROLLVALUE, intScrollValue);
		out.printf(	PERSONsVERBEDsNOUNsTOsTHEIRsNOUN, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, ADDED, shoppingListItem,
					SHOPPINGsLIST);
		return new RedirectView(sITEMS);
	}
}