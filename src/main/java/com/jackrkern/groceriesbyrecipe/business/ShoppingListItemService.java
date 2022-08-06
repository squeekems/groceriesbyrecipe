package com.jackrkern.groceriesbyrecipe.business;

import com.jackrkern.groceriesbyrecipe.models.Item;
import com.jackrkern.groceriesbyrecipe.models.ShoppingListItem;
import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.ItemRepository;
import com.jackrkern.groceriesbyrecipe.repositories.ShoppingListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;
import static java.lang.System.out;

/* @author "Jack Kern" */

@Service
public class ShoppingListItemService {
	@Autowired
	ShoppingListItemRepository shoppingListItemRepository;

	@Autowired
	private ItemRepository itemRepository;

	public List<ShoppingListItem> getShoppingList(User userID) {
		Iterable<ShoppingListItem> items = shoppingListItemRepository.findAllByUser(userID);
		List<ShoppingListItem> itemList = new ArrayList<>();
		items.forEach(itemList::add);
		itemList.sort((o1, o2) -> {
			if (o1.getItem().getAisle().equals(o2.getItem().getAisle()))
				return o1.getItem().getDescription().compareTo(o2.getItem().getDescription());
			return o1.getItem().getAisle().compareTo(o2.getItem().getAisle());
		});
		return itemList;
	}

	public void saveShoppingListItem(ShoppingListItem shoppingListItem) {
		ShoppingListItem tempShoppingListItem = shoppingListItemRepository.findByItemAndUser(shoppingListItem.getItem(), shoppingListItem.getUser());
		if (tempShoppingListItem != null) {
			tempShoppingListItem.setCount(tempShoppingListItem.getCount() + shoppingListItem.getCount());
			if (tempShoppingListItem.getCount() < 1) {
				deleteByID(tempShoppingListItem.getShoppingListID());
			} else {
				tempShoppingListItem = shoppingListItemRepository.save(tempShoppingListItem);
				out.printf(S_S_NL, tempShoppingListItem.toDetailedString(),
							capitalize(pastOf(demap(SAVE))));
			}
		} else {
			if (shoppingListItem.getCount() < 1)
				doNothing(); // Do nothing
			else {
				shoppingListItem = shoppingListItemRepository.save(shoppingListItem);
				out.printf(S_S_NL, shoppingListItem.toDetailedString(), capitalize(pastOf(demap(SAVE))));
			}
		}
	}

	public void saveShoppingListItem(Item item) {
		ShoppingListItem shoppingListItem = shoppingListItemRepository.findByItemAndUser(item, item.getUser());

		if (shoppingListItem != null) {
			shoppingListItem.setCount(shoppingListItem.getCount() + 1);
			shoppingListItemRepository.save(shoppingListItem);
			out.printf(S_S_NL, shoppingListItem.toDetailedString(), capitalize(pastOf(demap(SAVE))));
		} else {
			shoppingListItem = new ShoppingListItem();
			shoppingListItem.setItem(item);
			shoppingListItem.setUser(item.getUser());
			shoppingListItem.setCount(1);
			shoppingListItem = shoppingListItemRepository.save(shoppingListItem);
			out.printf(S_S_NL, shoppingListItem.toDetailedString(), capitalize(pastOf(demap(SAVE))));
		}
	}

	public void removeShoppingListItem(Long shoppingListItemID, int count) {
		ShoppingListItem shoppingListItem = getByID(shoppingListItemID);
		shoppingListItem.setCount(shoppingListItem.getCount() - count);
		if (shoppingListItem.getCount() < 1)
			deleteByID(shoppingListItemID);
		else {
			shoppingListItem = shoppingListItemRepository.save(shoppingListItem);
			out.printf(S_S_NL, shoppingListItem.toDetailedString(), capitalize(pastOf(demap(SAVE))));
		}
	}

	public void deleteByID(Long shoppingListItemID) {
		try {
			out.printf(S_S_NL, getByID(shoppingListItemID).toDetailedString(),
						capitalize(pastOf(demap(DELETE))));
			shoppingListItemRepository.deleteById(shoppingListItemID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ShoppingListItem getByID(Long shoppingListItemID) {
		return shoppingListItemRepository.findById(shoppingListItemID).orElseThrow();
	}

	public void clearShoppingList(User userID) {
		Iterable<ShoppingListItem> shoppingList = shoppingListItemRepository.findAllByUser(userID);
		shoppingList.forEach(shoppingListItem ->
				deleteByID(shoppingListItem.getShoppingListID()));
	}

	public void addRecipeToShoppingList(HttpServletRequest request) {
		List<String> requestParameterNames = Collections.list(request.getParameterNames());
		List<ShoppingListItem> recipeItems = new ArrayList<>();
		for (String parameterName : requestParameterNames) {
			String attributeValue = request.getParameter(parameterName);
			if (parameterName.startsWith(TXT_ID))
				recipeItems.add(new ShoppingListItem(itemRepository.findById(Long.parseLong(attributeValue)).orElseThrow()));
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