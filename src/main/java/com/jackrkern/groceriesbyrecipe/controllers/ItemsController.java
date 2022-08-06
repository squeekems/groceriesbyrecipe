/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.controllers;

/**
 * @imports
 */
import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.ShoppingListItemService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.Item;
import com.jackrkern.groceriesbyrecipe.models.ShoppingListItem;
import com.jackrkern.groceriesbyrecipe.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/**
 *  @author Jack Kern <jackrkern@gmail.com>
 */
@Controller
@RequestMapping(ITEMS)
public class ItemsController {

	Logger logger = LoggerFactory.getLogger(ItemsController.class);

	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	@Autowired
	private ShoppingListItemService shoppingListItemService;

	// Read
	@GetMapping
	public String getItems(Model model) {
		model.addAttribute(
				demap(ITEMS), itemService.getItemsSortedByAisle((User) userService.getPrincipal())
		);
		model.addAttribute(demap(ITEM), new Item());
		model.addAttribute(ACTIVE_PAGE, STORE_ITEMS);
		model.addAttribute(
				pluralOf(demap(AISLE)), itemService.getAisles((User) userService.getPrincipal())
		);
		model.addAttribute(demap(SHOPPING_LIST_ITEM), new ShoppingListItem());
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(GET)),
				demap(ITEMS)
		}) + PERIOD);
		return demap(ITEMS);
	}

	// Create
	@PostMapping(ADD)
	public RedirectView addItem(
			Item item,
			@RequestParam(value = CMB_ADD_AISLE) Long aisleID,
			@RequestParam(value = CHK_ADD_TO_SHOPPING_LIST, required = false) String blnAddToShoppingList,
			RedirectAttributes redirectAttributes) {
		item.setAisle(itemService.getAisleByID(aisleID));
		item.setUser((User) userService.getPrincipal());
		item = itemService.saveItem(item);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(ADD)),
				demap(ITEM),
				pastOf(CALL),
				item.toString()
		}) + PERIOD);
		redirectAttributes.addFlashAttribute(
				SUCCESS, String.format(S_S, item, capitalize(pastOf(demap(ADD))))
		);
		if (blnAddToShoppingList != null) {
			ShoppingListItem shoppingListItem = new ShoppingListItem();
			shoppingListItem.setItem(item);
			shoppingListItem.setUser((User) userService.getPrincipal());
			shoppingListItem.setCount(1);
			shoppingListItemService.saveShoppingListItem(shoppingListItem);
			logger.info(space(new String[]{
					userService.getPrincipal().toString(),
					pastOf(demap(ADD)),
					shoppingListItem.toString(),
					TO,
					THEIR,
					space(demap(SHOPPING_LIST))
			}) + PERIOD);
		}
		return new RedirectView(ITEMS);
	}

	// Update
	@RequestMapping(value = EDIT, method = { RequestMethod.PUT, RequestMethod.GET })
	public RedirectView updateItem(
			Item item,
			@RequestParam(value = CMB_EDIT_AISLE) Long aisleID,
			RedirectAttributes redirectAttributes) {
		item.setAisle(itemService.getAisleByID(aisleID));
		item.setUser((User) userService.getPrincipal());
		itemService.saveItem(item);
		redirectAttributes.addFlashAttribute(
				SUCCESS, String.format(S_S, item, capitalize(pastOf(demap(EDIT))))
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(UPDATE)),
				demap(ITEM),
				pastOf(CALL),
				item.toString()
		}) + PERIOD);
		return new RedirectView(ITEMS);
	}

	// Delete
	@GetMapping(REMOVE + PVM_ITEM_ID)
	public RedirectView deleteItem(
			@PathVariable(value = PVV_ITEM_ID) Long itemID,
			RedirectAttributes redirectAttributes) {
		// create item object to use in feedback
		Item item = itemService.getItemByID(itemID);
		itemService.deleteItem(itemID);
		redirectAttributes.addFlashAttribute(
				SUCCESS, String.format(S_S, item, capitalize(pastOf(demap(REMOVE))))
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(DELETE)),
				demap(ITEM),
				pastOf(CALL),
				item.toString()
		}) + PERIOD);
		return new RedirectView(ITEMS);
	}

	// Gets Item to be Edited or Added to Shopping List
	@RequestMapping(GET + ITEM + PVM_ITEM_ID)
	@ResponseBody
	public Item getItemByID(@PathVariable(value = PVV_ITEM_ID) Long itemID) {
		return itemService.getItemByID(itemID);
	}

	// AddToShoppingList
	@PostMapping(ADD_TO_SHOPPING_LIST)
	public RedirectView addItemToShoppingList(
			ShoppingListItem shoppingListItem,
			@RequestParam(value = TXT_ID) Long itemID,
			@RequestParam(value = NUM_COUNT) int count,
			@RequestParam(value = TXT_ADD_TO_SHOPPING_LIST_SCROLL_VALUE) double intScrollValue,
			RedirectAttributes redirectAttributes) {
		shoppingListItem.setItem(itemService.getItemByID(itemID));
		shoppingListItem.setUser((User) userService.getPrincipal());
		shoppingListItem.setCount(count);
		shoppingListItemService.saveShoppingListItem(shoppingListItem);
		redirectAttributes.addFlashAttribute(SCROLL_VALUE, intScrollValue);
		redirectAttributes.addFlashAttribute(
				SUCCESS,
				String.format(NOUN_VERB_PREPOSITION_NOUN,
						shoppingListItem,
						capitalize(pastOf(demap(ADD))),
						TO,
						capitalize(space(demap(SHOPPING_LIST)))
				)
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(ADD)),
				shoppingListItem.toString(),
				TO,
				THEIR,
				space(demap(SHOPPING_LIST))
		}) + PERIOD);
		return new RedirectView(ITEMS);
	}
}