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
@RequestMapping(LIST)
public class ShoppingListItemController {

	Logger logger = LoggerFactory.getLogger(ShoppingListItemController.class);

	@Autowired
	private ShoppingListItemService shoppingListItemService;

	@Autowired
	private UserService userService;

	@Autowired
	private ItemService itemService;

	// Read
	@GetMapping
	public String getShoppingList(Model model) {
		model.addAttribute(
				demap(SHOPPING_LIST),
				shoppingListItemService.getShoppingList((User) userService.getPrincipal())
		);
		model.addAttribute(ACTIVE_PAGE, capitalize(space(demap(SHOPPING_LIST))));
		model.addAttribute(
				demap(ITEMS), itemService.getItemsSortedByDescription((User) userService.getPrincipal())
		);
		model.addAttribute(demap(ITEM), new Item());
		model.addAttribute(demap(SHOPPING_LIST_ITEM), new ShoppingListItem());
		model.addAttribute(
				pluralOf(demap(AISLE)), itemService.getAisles((User) userService.getPrincipal())
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(GET)),
				demap(LIST)
		}) + PERIOD);
		return demap(LIST);
	}

	// Post
	@PostMapping
	public RedirectView addExistingToShoppingList(
			ShoppingListItem shoppingListItem,
			@RequestParam(value = CMB_ADD_ITEM) Long itemID,
			@RequestParam(value = NUM_COUNT) int count,
			RedirectAttributes redirectAttributes) {
		shoppingListItem.setItem(itemService.getItemByID(itemID));
		shoppingListItem.setUser((User) userService.getPrincipal());
		shoppingListItem.setCount(count);
		shoppingListItemService.saveShoppingListItem(shoppingListItem);
		redirectAttributes.addFlashAttribute(
				SUCCESS, String.format(S_S, shoppingListItem, capitalize(pastOf(demap(ADD))))
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(ADD)),
				shoppingListItem.toString()
		}) + PERIOD);
		return new RedirectView(LIST);
	}

	// Post
	@PostMapping(ADD)
	public RedirectView addToShoppingList(
			Item item,
			@RequestParam(value = CMB_ADD_AISLE) Long aisleID,
			RedirectAttributes redirectAttributes) {
		item.setAisle(itemService.getAisleByID(aisleID));
		item.setUser((User) userService.getPrincipal());
		itemService.saveItem(item);
		shoppingListItemService.saveShoppingListItem(item);
		redirectAttributes.addFlashAttribute(
				SUCCESS, String.format(S_S, item, capitalize(pastOf(demap(ADD))))
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(CREATE)),
				demap(ITEM),
				pastOf(CALL),
				item.toString(),
				AND,
				pastOf(demap(ADD)),
				IT,
				TO,
				THEIR,
				space(demap(SHOPPING_LIST))
		}) + PERIOD);
		return new RedirectView(LIST);
	}

	// Delete
	@GetMapping(REMOVE + PVM_SHOPPING_LIST_ITEM_ID)
	public RedirectView deleteShoppingListItem(
			@PathVariable(value = PVV_SHOPPING_LIST_ITEM_ID) Long shoppingListItemID,
			RedirectAttributes redirectAttributes) {
		ShoppingListItem shoppingListItem = shoppingListItemService.getByID(shoppingListItemID);
		shoppingListItemService.removeShoppingListItem(shoppingListItemID, 1);
		redirectAttributes.addFlashAttribute(
				SUCCESS, String.format(S_S, shoppingListItem.getItem(), capitalize(pastOf(demap(REMOVE))))
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(REMOVE)),
				shoppingListItem.getItem().toString(),
				FROM,
				THEIR,
				space(demap(SHOPPING_LIST))
		}) + PERIOD);
		return new RedirectView(LIST);
	}

	// Clear
	@GetMapping(CLEAR)
	public RedirectView clearShoppingList(RedirectAttributes redirectAttributes) {
		shoppingListItemService.clearShoppingList((User) userService.getPrincipal());
		redirectAttributes.addFlashAttribute(SUCCESS,
				String.format(
						S_S,
						capitalize(space(demap(SHOPPING_LIST))),
						capitalize(pastOf(demap(CLEAR)))
				)
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(CLEAR)),
				THEIR,
				space(demap(SHOPPING_LIST))
		}) + PERIOD);
		return new RedirectView(LIST);
	}
}