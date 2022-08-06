/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.business;

import com.jackrkern.groceriesbyrecipe.models.Item;
import com.jackrkern.groceriesbyrecipe.models.ShoppingListItem;
import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.ItemRepository;
import com.jackrkern.groceriesbyrecipe.repositories.ShoppingListItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/* @author "Jack Kern" */

@Service
public class ShoppingListItemService {

	Logger logger = LoggerFactory.getLogger(ShoppingListItemService.class);

	@Autowired
	ShoppingListItemRepository shoppingListItemRepository;

	@Autowired
	private ItemRepository itemRepository;

	public List<ShoppingListItem> getShoppingList(User userID) {
		Iterable<ShoppingListItem> items = shoppingListItemRepository.findAllByUser(userID);
		List<ShoppingListItem> itemList = new ArrayList<>();
		items.forEach(itemList::add);
		itemList.sort((o1, o2) -> {
			if (o1.getItem().getAisle().equals(o2.getItem().getAisle())) {
				return o1.getItem().getDescription().compareTo(o2.getItem().getDescription());
			}
			return o1.getItem().getAisle().compareTo(o2.getItem().getAisle());
		});
		return itemList;
	}

	public void saveShoppingListItem(ShoppingListItem shoppingListItem) {
		ShoppingListItem tempShoppingListItem = shoppingListItemRepository.findByItemAndUser(
				shoppingListItem.getItem(),
				shoppingListItem.getUser()
		);
		if (tempShoppingListItem != null) {
			tempShoppingListItem.setCount(tempShoppingListItem.getCount() + shoppingListItem.getCount());
			if (tempShoppingListItem.getCount() < 1) {
				deleteByID(tempShoppingListItem.getShoppingListID());
			} else {
				tempShoppingListItem = shoppingListItemRepository.save(tempShoppingListItem);
				logger.info(space(new String[]{
						tempShoppingListItem.toDetailedString(),
						capitalize(pastOf(demap(SAVE)))
				}) + PERIOD);
			}
		} else {
			if (shoppingListItem.getCount() < 1) {
				doNothing(); // Do nothing
			} else {
				shoppingListItem = shoppingListItemRepository.save(shoppingListItem);
				logger.info(space(new String[]{
						shoppingListItem.toDetailedString(),
						capitalize(pastOf(demap(SAVE)))
				}) + PERIOD);
			}
		}
	}

	public ShoppingListItem saveShoppingListItem(Item item) {
		ShoppingListItem shoppingListItem = shoppingListItemRepository.findByItemAndUser(
				item,
				item.getUser()
		);
		if (shoppingListItem != null) {
			shoppingListItem.setCount(shoppingListItem.getCount() + 1);
			shoppingListItem = shoppingListItemRepository.save(shoppingListItem);
			logger.info(space(new String[]{
					shoppingListItem.toDetailedString(),
					capitalize(pastOf(demap(SAVE)))
			}) + PERIOD);
		} else {
			shoppingListItem = new ShoppingListItem();
			shoppingListItem.setItem(item);
			shoppingListItem.setUser(item.getUser());
			shoppingListItem.setCount(1);
			shoppingListItem = shoppingListItemRepository.save(shoppingListItem);
			logger.info(space(new String[]{
					shoppingListItem.toDetailedString(),
					capitalize(pastOf(demap(SAVE)))
			}) + PERIOD);
		}
		return shoppingListItem;
	}

	public void removeShoppingListItem(Long shoppingListItemID, int count) {
		ShoppingListItem shoppingListItem = getByID(shoppingListItemID);
		shoppingListItem.setCount(shoppingListItem.getCount() - count);
		if (shoppingListItem.getCount() < 1) {
			deleteByID(shoppingListItemID);
		} else {
			shoppingListItem = shoppingListItemRepository.save(shoppingListItem);
			logger.info(space(new String[]{
					shoppingListItem.toDetailedString(),
					capitalize(pastOf(demap(SAVE)))
			}) + PERIOD);
		}
	}

	public void deleteByID(Long shoppingListItemID) {
		try {
			logger.info(space(new String[]{
					getByID(shoppingListItemID).toDetailedString(),
					capitalize(pastOf(demap(DELETE)))
			}) + PERIOD);
			shoppingListItemRepository.deleteById(shoppingListItemID);
		} catch (IllegalArgumentException e) {
			logger.warn(space( new String[]{
					(demap(DELETE) + capitalize(BY) + ID),
					pastOf(CATCH),
					e.getClass().getName()
			}), e);
		}
	}

	public ShoppingListItem getByID(Long shoppingListItemID) {
		return shoppingListItemRepository.findById(shoppingListItemID).orElseThrow();
	}

	public void clearShoppingList(User userID) {
		Iterable<ShoppingListItem> shoppingList = shoppingListItemRepository.findAllByUser(userID);
		shoppingList.forEach(shoppingListItem -> deleteByID(shoppingListItem.getShoppingListID()));
	}

	public void addRecipeToShoppingList(HttpServletRequest request) {
		List<String> requestParameterNames = Collections.list(request.getParameterNames());
		List<ShoppingListItem> recipeItems = new ArrayList<>();
		for (String parameterName : requestParameterNames) {
			String attributeValue = request.getParameter(parameterName);
			if (parameterName.startsWith(TXT_ID)) {
				recipeItems.add(new ShoppingListItem(itemRepository.findById(Long.parseLong(attributeValue)).orElseThrow()));
			}
			if (parameterName.startsWith(NUM_CO)) {
				for (ShoppingListItem recipeItem : recipeItems) {
					if (Long.parseLong(parameterName.substring(8)) == recipeItem.getItem().getItemID()) {
						recipeItem.setCount(Integer.parseInt(attributeValue));
						saveShoppingListItem(recipeItem);
					}
				}
			}
		}
	}
}