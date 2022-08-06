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
	@GetMapping(LIST)
	public String getShoppingList(Model model)
	{
		model.addAttribute(	strMapping(SHOPPINGLIST),
							shoppingListItemService.getShoppingList(userService.getPrincipal()));
		model.addAttribute(ACTIVEPAGE, capitalize(strSpace(strMapping(SHOPPINGLIST))));
		model.addAttribute(strMapping(ITEMS), itemService.getItemsSortedByDescription(userService.getPrincipal()));
		model.addAttribute(strMapping(ITEM), new Item());
		model.addAttribute(strMapping(SHOPPINGLISTITEM), new ShoppingListItem());
		model.addAttribute(strPlural(strMapping(AISLE)), itemService.getAisles(userService.getPrincipal()));
		out.printf(	PERSONsLOADEDsTHEsNOUNsPAGEnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strMapping(LIST));
		return strMapping(LIST);
	}

	// Post
	@PostMapping(LIST)
	public RedirectView addExistingToShoppingList(ShoppingListItem shoppingListItem, @RequestParam(value = CMBADDITEM)
	Long itemID, @RequestParam(value = NUMCOUNT)
	int count, RedirectAttributes redirectAttributes)
	{
		shoppingListItem.setItem(itemService.getItemByID(itemID));
		shoppingListItem.setUser(userService.getPrincipal());
		shoppingListItem.setCount(count);
		shoppingListItemService.saveShoppingListItem(shoppingListItem);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, shoppingListItem,
																	capitalize(strPast(strMapping(ADD)))));
		out.printf(	PERSONsVERBEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strQuote(strPast(strMapping(ADD))), shoppingListItem);
		return new RedirectView(LIST);
	}

	// Post
	@PostMapping(LIST + ADD)
	public RedirectView addToShoppingList(Item item, @RequestParam(value = CMBADDAISLE)
	Long aisleID, RedirectAttributes redirectAttributes)
	{
		item.setAisle(itemService.getAisleByID(aisleID));
		item.setUser(userService.getPrincipal());
		itemService.saveItem(item);
		shoppingListItemService.saveShoppingListItem(item);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, item,
																	capitalize(strPast(strMapping(ADD)))));
		out.printf(	PERSONsVERBEDsANsOBJECTsCALLEDsNOUNsANDsVERBEDsITsPREPOSITIONsTHEIRsNOUNnl,
					now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strPast(strMapping(CREATE)), strMapping(ITEM), item, strPast(strMapping(ADD)), TO,
					strSpace(strMapping(SHOPPINGLIST)));
		return new RedirectView(LIST);
	}

	// Delete
	@GetMapping(LIST + REMOVE + PVMSHOPPINGLISTITEMID)
	public RedirectView deleteShoppingListItem(@PathVariable(value = PVVSHOPPINGLISTITEMID)
	Long shoppingListItemID, RedirectAttributes redirectAttributes)
	{
		ShoppingListItem shoppingListItem = shoppingListItemService.getByID(shoppingListItemID);
		shoppingListItemService.removeShoppingListItem(shoppingListItemID, 1);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, shoppingListItem.getItem(),
																	capitalize(strPast(strMapping(REMOVE)))));
		out.printf(	PERSONsVERBEDsAsNOUNsPREPOSITIONsTHEIRsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strQuote(strPast(strMapping(REMOVE))), shoppingListItem.getItem(), FROM,
					strSpace(strMapping(SHOPPINGLIST)));
		return new RedirectView(LIST);
	}

	// Clear
	@GetMapping(LIST + CLEAR)
	public RedirectView clearShoppingList(RedirectAttributes redirectAttributes)
	{
		shoppingListItemService.clearShoppingList(userService.getPrincipal());
		redirectAttributes.addFlashAttribute(	SUCCESS,
												String.format(	STRINGsSTRING,
																capitalize(strSpace(strMapping(SHOPPINGLIST))),
																capitalize(strPast(strMapping(CLEAR)))));
		out.printf(	PERSONsVERBEDsTHEIRsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strPast(strMapping(CLEAR)), strSpace(strMapping(SHOPPINGLIST)));
		return new RedirectView(LIST);
	}
}