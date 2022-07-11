package com.jackrkern.groceriesbyrecipe.business;

import static java.lang.System.out;

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
import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/* @author "Jack Kern" */

@Service
public class ItemService
{
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private AisleRepository aisleRepository;

	private String strDetailedItem(Item item)
	{
		return String.format(ITEMDETAIL, item.getAisle(), item.getDescription(), item.getUser());
	}

	private String strDetailedItem(Long itemID)
	{
		return strDetailedItem(getItemByID(itemID));
	}

	public List<Aisle> getAisles(User userID)
	{
		Iterable<Aisle> aisles = aisleRepository.findAllByUser(userID);
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

	public Item getItemByID(Long itemID)
	{
		return itemRepository.findById(itemID).get();
	}

	public List<Item> getItemsSortedByAisle(User userID)
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

	public List<Item> getItemsSortedByDescription(User userID)
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
				if (o1.getDescription().equals(o2.getDescription()))
				{
					return o1.getAisle().compareTo(o2.getAisle());
				}
				return o1.getDescription().compareTo(o2.getDescription());
			}
		});
		return itemList;
	}

	public Item saveItem(Item item)
	{
		out.printf(STRINGsSTRINGnl, strDetailedItem(item), cSAVED);
		return itemRepository.save(item);
	}

	public void deleteItem(Long itemID)
	{
		try
		{
			out.printf(STRINGsSTRINGnl, strDetailedItem(itemID), cDELETED);
			itemRepository.deleteById(itemID);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void saveAisle(Aisle aisle)
	{
		if (aisle != null)
		{
			aisleRepository.save(aisle);
			out.printf(STRINGsSTRINGnl, aisle, cSAVED);
		} else
		{
			throw new RuntimeException("Aisle cannot be null");
		}
	}

	public void deleteAisle(Long aisleID)
	{
		try
		{
			out.printf(STRINGsSTRINGnl, getAisleByID(aisleID), cDELETED);
			aisleRepository.deleteById(aisleID);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
