package com.jackrkern.groceriesbyrecipe.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jackrkern.groceriesbyrecipe.models.Item;
import com.jackrkern.groceriesbyrecipe.models.ShoppingListItem;
import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.ShoppingListItemRepository;

/* @author "Jack Kern" */

@Service
public class ShoppingListItemService
{
	@Autowired
	ShoppingListItemRepository shoppingListItemRepository;

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
				shoppingListItemRepository.save(tempShoppingListItem);
			} else
			{
				shoppingListItemRepository.save(shoppingListItem);
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
		} else
		{
			shoppingListItem = new ShoppingListItem();
			shoppingListItem.setItem(item);
			shoppingListItem.setUser(item.getUser());
			shoppingListItem.setCount(1);
			shoppingListItemRepository.save(shoppingListItem);
		}
	}

	public void removeShoppingListItem(Long shoppingListItemID, int count)
	{
		ShoppingListItem shoppingListItem = shoppingListItemRepository.findById(shoppingListItemID).get();
		shoppingListItem.setCount(shoppingListItem.getCount() - count);
		if (shoppingListItem.getCount() < 1)
		{
			try
			{
				shoppingListItemRepository.deleteById(shoppingListItemID);
			} catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("");
			}
		} else
		{
			shoppingListItemRepository.save(shoppingListItem);
		}
	}

	public ShoppingListItem getByID(Long shoppingListItemID)
	{
		return shoppingListItemRepository.findById(shoppingListItemID).get();
	}

	public void deleteShoppingListItem(Long shoppingListItemID)
	{
		try
		{
			shoppingListItemRepository.deleteById(shoppingListItemID);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void clearShoppingList(User userID)
	{
		Iterable<ShoppingListItem> items = shoppingListItemRepository.findAllByUser(userID);
		items.forEach(item ->
		{
			shoppingListItemRepository.deleteById(item.getShoppingListID());
		});
	}
}