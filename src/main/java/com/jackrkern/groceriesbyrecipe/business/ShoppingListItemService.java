package com.jackrkern.groceriesbyrecipe.business;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;
import static java.lang.System.out;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.jackrkern.groceriesbyrecipe.models.Item;
import com.jackrkern.groceriesbyrecipe.models.ShoppingListItem;
import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.ItemRepository;
import com.jackrkern.groceriesbyrecipe.repositories.ShoppingListItemRepository;

/* @author "Jack Kern" */

@Service
public class ShoppingListItemService
{
	@Autowired
	ShoppingListItemRepository shoppingListItemRepository;

	@Autowired
	private ItemRepository itemRepository;

	private String strDetailedStoreItem(ShoppingListItem shoppingListItem)
	{
		return String.format(	SHOPPINGLISTITEMDETAIL, shoppingListItem, shoppingListItem.getCount(),
								shoppingListItem.getUser());
	}

	private String strDetailedStoreItem(Long shoppingListItemID)
	{
		return strDetailedStoreItem(getByID(shoppingListItemID));
	}

	public List<ShoppingListItem> getShoppingList(User userID)
	{
		Iterable<ShoppingListItem> items = shoppingListItemRepository.findAllByUser(userID);
		List<ShoppingListItem> itemList = new ArrayList<>();
		items.forEach(item ->
		{
			itemList.add(item);
		});
		itemList.sort(new Comparator<ShoppingListItem>()
		{
			@Override
			public int compare(ShoppingListItem o1, ShoppingListItem o2)
			{
				if (o1.getItem().getAisle().equals(o2.getItem().getAisle()))
				{
					return o1.getItem().getDescription().compareTo(o2.getItem().getDescription());
				}
				return o1.getItem().getAisle().compareTo(o2.getItem().getAisle());
			}
		});
		return itemList;
	}

	public ShoppingListItem findByItemAndUser(Item itemID, User userID)
	{
		return shoppingListItemRepository.findByItemAndUser(itemID, userID);
	}

	public void saveShoppingListItem(ShoppingListItem shoppingListItem)
	{
		if (shoppingListItem != null)
		{
			ShoppingListItem tempShoppingListItem = shoppingListItemRepository.findByItemAndUser(	shoppingListItem.getItem(),
																									shoppingListItem.getUser());
			if (tempShoppingListItem != null)
			{
				tempShoppingListItem.setCount(tempShoppingListItem.getCount() + shoppingListItem.getCount());
				if (tempShoppingListItem.getCount() < 1)
				{
					deleteByID(tempShoppingListItem.getShoppingListID());
				} else
				{
					shoppingListItemRepository.save(tempShoppingListItem);
					out.printf(STRINGsSTRINGnl, strDetailedStoreItem(tempShoppingListItem), cSAVED);
				}
			} else
			{
				if (shoppingListItem.getCount() < 1)
					out.printf("", ""); // Do nothing
				else
				{
					shoppingListItemRepository.save(shoppingListItem);
					out.printf(STRINGsSTRINGnl, strDetailedStoreItem(shoppingListItem), cSAVED);
				}
			}
		} else
		{
			throw new RuntimeException("ShoppingListItem cannot be null");
		}
	}

	public void saveShoppingListItem(Item item)
	{
		ShoppingListItem shoppingListItem = shoppingListItemRepository.findByItemAndUser(item, item.getUser());

		if (shoppingListItem != null)
		{
			shoppingListItem.setCount(shoppingListItem.getCount() + 1);
			shoppingListItemRepository.save(shoppingListItem);
			out.printf(STRINGsSTRINGnl, shoppingListItem, cSAVED);
		} else
		{
			shoppingListItem = new ShoppingListItem();
			shoppingListItem.setItem(item);
			shoppingListItem.setUser(item.getUser());
			shoppingListItem.setCount(1);
			shoppingListItemRepository.save(shoppingListItem);
			out.printf(STRINGsSTRINGnl, strDetailedStoreItem(shoppingListItem), cSAVED);
		}
	}

	public void saveShoppingListItemsFromRecipeJSON(JsonObject jsonObject)
	{
		JsonArray ingredients = (JsonArray) jsonObject.get("ingredients");
		ingredients.forEach(ingredient ->
		{
			JsonObject jIngredient = (JsonObject) ingredient;
			JsonObject jItem = (JsonObject) jIngredient.get("item");
			BigDecimal bdItemID = (BigDecimal) jItem.get("itemID");
			Long itemID = bdItemID.longValue();
			ShoppingListItem shoppingListItem = new ShoppingListItem();
			shoppingListItem.setItem(itemRepository.findById(itemID).get());
			shoppingListItem.setUser(shoppingListItem.getItem().getUser());
			shoppingListItem.setCount(1);
			shoppingListItemRepository.save(shoppingListItem);
			out.printf(STRINGsSTRINGnl, strDetailedStoreItem(shoppingListItem), cSAVED);
		});
	}

	public void removeShoppingListItem(Long shoppingListItemID, int count)
	{
		ShoppingListItem shoppingListItem = getByID(shoppingListItemID);
		shoppingListItem.setCount(shoppingListItem.getCount() - count);
		if (shoppingListItem.getCount() < 1)
		{
			deleteByID(shoppingListItemID);
		} else
		{
			shoppingListItemRepository.save(shoppingListItem);
			out.printf(STRINGsSTRINGnl, strDetailedStoreItem(shoppingListItem), cSAVED);
		}
	}

	public void deleteByID(Long shoppingListItemID)
	{
		try
		{
			out.printf(STRINGsSTRINGnl, strDetailedStoreItem(shoppingListItemID), cDELETED);
			shoppingListItemRepository.deleteById(shoppingListItemID);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public ShoppingListItem getByID(Long shoppingListItemID)
	{
		return shoppingListItemRepository.findById(shoppingListItemID).get();
	}

	public void clearShoppingList(User userID)
	{
		Iterable<ShoppingListItem> shoppingList = shoppingListItemRepository.findAllByUser(userID);
		shoppingList.forEach(shoppingListItem ->
		{
			deleteByID(shoppingListItem.getShoppingListID());
		});
	}

	public void addRecipeToShoppingList(HttpServletRequest request)
	{
		List<String> requestParameterNames = Collections.list((Enumeration<String>) request.getParameterNames());
		List<ShoppingListItem> recipeItems = new ArrayList<>();
		for (String parameterName : requestParameterNames)
		{
			String attributeName = parameterName;
			String attributeValue = request.getParameter(parameterName);
			if (attributeName.substring(0, 5).equals("txtID"))
			{
				recipeItems.add(new ShoppingListItem(itemRepository.findById(Long.parseLong(attributeValue)).get()));
			}
			if (attributeName.substring(0, 5).equals("numCo"))
			{
				for (ShoppingListItem recipeItem : recipeItems)
				{
					if (Long.parseLong(attributeName.substring(8)) == recipeItem.getItem().getItemID())
					{
						recipeItem.setCount(Integer.parseInt(attributeValue));
						saveShoppingListItem(recipeItem);
					}
				}
			}
		}
	}
}