package com.jackrkern.groceriesbyrecipe.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jackrkern.groceriesbyrecipe.models.ShoppingList;
import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.ShoppingListRepository;

/* @author "Jack Kern" */

@Service
public class ShoppingListService
{
	@Autowired
	ShoppingListRepository shoppingListRepository;

	public List<ShoppingList> getShoppingLists(User userID)
	{
		Iterable<ShoppingList> items = shoppingListRepository.findAllByUser(userID);
		List<ShoppingList> itemList = new ArrayList<>();
		items.forEach(item ->
		{
			itemList.add(item);
		});
		itemList.sort(new Comparator<ShoppingList>()
		{
			@Override
			public int compare(ShoppingList o1, ShoppingList o2)
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
}