package com.jackrkern.groceriesbyrecipe.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jackrkern.groceriesbyrecipe.models.Aisle;
import com.jackrkern.groceriesbyrecipe.models.Item;
import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.AisleRepository;
import com.jackrkern.groceriesbyrecipe.repositories.ItemRepository;

/* @author "Jack Kern" */

@Service
public class ItemService
{
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private AisleRepository aisleRepository;

	public List<Aisle> getAisles()
	{
		Iterable<Aisle> aisles = aisleRepository.findAll();
		List<Aisle> aisleList = new ArrayList<>();
		aisles.forEach(aisle -> aisleList.add(aisle));
		aisleList.sort(new Comparator<Aisle>()
		{
			@Override
			public int compare(Aisle o1, Aisle o2)
			{
				return o1.getName().compareTo(o2.getName());
			}
		});
		return aisleList;
	}

	public Aisle getAisleByID(Long aisleID)
	{
		return aisleRepository.findById(aisleID).get();
	}

	public Aisle getAisleByName(String name)
	{
		return aisleRepository.findByName(name);
	}

	public Item getItemByID(Long itemID)
	{
		return itemRepository.findById(itemID).get();
	}

	public List<Item> getItems(User userID)
	{
		Iterable<Item> items = itemRepository.findAllByUser(userID);
		List<Item> itemList = new ArrayList<>();
		items.forEach(item ->
		{
			itemList.add(item);
		});
		itemList.sort(new Comparator<Item>()
		{
			@Override
			public int compare(Item o1, Item o2)
			{
				if (o1.getAisle().equals(o2.getAisle()))
				{
					return o1.getDescription().compareTo(o2.getDescription());
				}
				return o1.getAisle().compareTo(o2.getAisle());
			}
		});
		return itemList;
	}

	public void saveItem(Item item)
	{
		if (item != null)
		{
			itemRepository.save(item);
		} else
		{
			throw new RuntimeException("Item cannot be null");
		}
	}

	public void deleteItem(Long itemID)
	{
		try
		{
			itemRepository.deleteById(itemID);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
