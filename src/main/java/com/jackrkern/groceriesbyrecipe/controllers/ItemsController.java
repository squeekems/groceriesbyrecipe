package com.jackrkern.groceriesbyrecipe.controllers;

import static java.time.LocalDateTime.now;
import static org.springframework.util.StringUtils.capitalize;
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
	@GetMapping(ITEMS)
	public String getItems(Model model)
	{
		model.addAttribute(strMapping(ITEMS), itemService.getItemsSortedByAisle(userService.getPrincipal()));
		model.addAttribute(strMapping(ITEM), new Item());
		model.addAttribute(ACTIVEPAGE, cSTOREscITEMS);
		model.addAttribute(strPlural(strMapping(AISLE)), itemService.getAisles(userService.getPrincipal()));
		model.addAttribute(strMapping(SHOPPINGLISTITEM), new ShoppingListItem());
		out.printf(	PERSONsLOADEDsTHEsNOUNsPAGEnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strMapping(ITEMS));
		return strMapping(ITEMS);
	}

	// Create
	@PostMapping(ITEMS + ADD)
	public RedirectView addItem(Item item, @RequestParam(value = CMBADDAISLE)
	Long aisleID, @RequestParam(value = CHKADDTOSHOPPINGLIST, required = false)
	String blnAddToShoppingList, RedirectAttributes redirectAttributes)
	{
		item.setAisle(itemService.getAisleByID(aisleID));
		item.setUser(userService.getPrincipal());
		item = itemService.saveItem(item);
		out.printf(	PERSONsCREATEDsAsNEWsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strMapping(ITEM), item);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, item,
																	capitalize(strPast(strMapping(ADD)))));
		if (blnAddToShoppingList != null)
		{
			ShoppingListItem shoppingListItem = new ShoppingListItem();
			shoppingListItem.setItem(item);
			shoppingListItem.setUser(userService.getPrincipal());
			shoppingListItem.setCount(1);
			shoppingListItemService.saveShoppingListItem(shoppingListItem);
			out.printf(	PERSONsVERBEDsNOUNsPREPOSITIONsTHEIRsNOUN, now().format(dateTimeFormatter),
						userService.getPrincipal() != null ? userService.getPrincipal() : SOMEONE,
						strPast(strMapping(ADD)), shoppingListItem, TO, strSpace(strMapping(SHOPPINGLIST)));
		}
		return new RedirectView(ITEMS);
	}

	// Update
	@RequestMapping(value = ITEMS + EDIT, method = { RequestMethod.PUT, RequestMethod.GET })
	public RedirectView updateItem(Item item, @RequestParam(value = CMBEDITAISLE)
	Long aisleID, RedirectAttributes redirectAttributes)
	{
		item.setAisle(itemService.getAisleByID(aisleID));
		item.setUser(userService.getPrincipal());
		itemService.saveItem(item);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, item,
																	capitalize(strPast(strMapping(EDIT)))));
		out.printf(	PERSONsVERBEDsANsOBJECTsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strPast(strMapping(UPDATE)), strMapping(ITEM), item);
		return new RedirectView(ITEMS);
	}

	// Delete
	@GetMapping(ITEMS + REMOVE + PVMITEMID)
	public RedirectView deleteItem(@PathVariable(value = PVVITEMID)
	Long itemID, RedirectAttributes redirectAttributes)
	{
		// create item object to use in feedback
		Item item = itemService.getItemByID(itemID);
		itemService.deleteItem(itemID);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, item,
																	capitalize(strPast(strMapping(REMOVE)))));
		out.printf(	PERSONsVERBEDsANsOBJECTsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strPast(strMapping(DELETE)), strMapping(ITEM), item);
		return new RedirectView(ITEMS);
	}

	// Gets Item to be Editted or Added to Shopping List
	@RequestMapping(ITEMS + GET + ITEM + PVMITEMID)
	@ResponseBody
	public Item getItemByID(@PathVariable(value = PVVITEMID)
	Long itemID)
	{
		return itemService.getItemByID(itemID);
	}

	// AddToShoppingList
	@PostMapping(ITEMS + ADDTOSHOPPINGLIST)
	public RedirectView addItemToShoppingList(ShoppingListItem shoppingListItem, @RequestParam(value = TXTID)
	Long itemID, @RequestParam(value = NUMCOUNT)
	int count, @RequestParam(value = TXTADDTOSHOPPINGLISTSCROLLVALUE)
	double intScrollValue, RedirectAttributes redirectAttributes)
	{
		shoppingListItem.setItem(itemService.getItemByID(itemID));
		shoppingListItem.setUser(userService.getPrincipal());
		shoppingListItem.setCount(count);
		shoppingListItemService.saveShoppingListItem(shoppingListItem);
		redirectAttributes.addFlashAttribute(	SUCCESS,
												String.format(	NOUNsVERBEDsPREPOSITIONsNOUN, shoppingListItem,
																capitalize(strPast(strMapping(ADD))), TO,
																capitalize(strSpace(strMapping(SHOPPINGLIST)))));
		redirectAttributes.addFlashAttribute(SCROLLVALUE, intScrollValue);
		out.printf(	PERSONsVERBEDsNOUNsPREPOSITIONsTHEIRsNOUN, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strPast(strMapping(ADD)), shoppingListItem, TO, strSpace(strMapping(SHOPPINGLIST)));
		return new RedirectView(ITEMS);
	}
}