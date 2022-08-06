package com.jackrkern.groceriesbyrecipe.business;

import com.jackrkern.groceriesbyrecipe.models.Aisle;
import com.jackrkern.groceriesbyrecipe.models.Item;
import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.AisleRepository;
import com.jackrkern.groceriesbyrecipe.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;
import static java.lang.System.out;

/* @author "Jack Kern" */

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private AisleRepository aisleRepository;

	public List<Aisle> getAisles(User userID)
	{
		Iterable<Aisle> aisles = aisleRepository.findAllByUser(userID);
		List<Aisle> aisleList = new ArrayList<>();
		aisles.forEach(aisleList::add);
		aisleList.sort(Comparator.comparing(Aisle::getName));
		return aisleList;
	}

	public Aisle getAisleByID(Long aisleID)
	{
		return aisleRepository.findById(aisleID).orElseThrow();
	}

	public Item getItemByID(Long itemID)
	{
		return itemRepository.findById(itemID).orElseThrow();
	}

	public List<Item> getItemsSortedByAisle(User userID) {
		Iterable<Item> items = itemRepository.findAllByUser(userID);
		List<Item> itemList = new ArrayList<>();
		items.forEach(itemList::add);
		itemList.sort((o1, o2) -> {
			if (o1.getAisle().equals(o2.getAisle()))
				return o1.getDescription().compareTo(o2.getDescription());
			return o1.getAisle().compareTo(o2.getAisle());
		});
		return itemList;
	}

	public List<Item> getItemsSortedByDescription(User userID) {
		Iterable<Item> items = itemRepository.findAllByUser(userID);
		List<Item> itemList = new ArrayList<>();
		items.forEach(itemList::add);
		itemList.sort((o1, o2) -> {
			if (o1.getDescription().equals(o2.getDescription()))
				return o1.getAisle().compareTo(o2.getAisle());
			return o1.getDescription().compareTo(o2.getDescription());
		});
		return itemList;
	}

	public Item saveItem(Item item) {
		out.printf(S_S_NL, item.toDetailedString(), capitalize(pastOf(demap(SAVE))));
		return itemRepository.save(item);
	}

	public void deleteItem(Long itemID) {
		try {
			out.printf(S_S_NL, getItemByID(itemID).toDetailedString(),
						capitalize(pastOf(demap(DELETE))));
			itemRepository.deleteById(itemID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveAisle(Aisle aisle) {
		aisleRepository.save(aisle);
		out.printf(S_S_NL, aisle, capitalize(pastOf(demap(SAVE))));
	}

	public void deleteAisle(Long aisleID) {
		try {
			out.printf(S_S_NL, getAisleByID(aisleID).toDetailedString(),
						capitalize(pastOf(demap(DELETE))));
			aisleRepository.deleteById(aisleID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
