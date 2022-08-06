/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.business;

import com.jackrkern.groceriesbyrecipe.models.Aisle;
import com.jackrkern.groceriesbyrecipe.models.Item;
import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.AisleRepository;
import com.jackrkern.groceriesbyrecipe.repositories.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/* @author "Jack Kern" */

@Service
public class ItemService {

	Logger logger = LoggerFactory.getLogger(ItemService.class);

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private AisleRepository aisleRepository;

	public List<Aisle> getAisles(User userID) {
		Iterable<Aisle> aisles = aisleRepository.findAllByUser(userID);
		List<Aisle> aisleList = new ArrayList<>();
		aisles.forEach(aisleList::add);
		aisleList.sort(Comparator.comparing(Aisle::getName));
		return aisleList;
	}

	public Aisle getAisleByID(Long aisleID) {
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
			if (o1.getAisle().equals(o2.getAisle())) {
				return o1.getDescription().compareTo(o2.getDescription());
			}
			return o1.getAisle().compareTo(o2.getAisle());
		});
		return itemList;
	}

	public List<Item> getItemsSortedByDescription(User userID) {
		Iterable<Item> items = itemRepository.findAllByUser(userID);
		List<Item> itemList = new ArrayList<>();
		items.forEach(itemList::add);
		itemList.sort((o1, o2) -> {
			if (o1.getDescription().equals(o2.getDescription())) {
				return o1.getAisle().compareTo(o2.getAisle());
			}
			return o1.getDescription().compareTo(o2.getDescription());
		});
		return itemList;
	}

	public Item saveItem(Item item) {
		item = itemRepository.save(item);
		logger.info(space(new String[]{
				item.toDetailedString(),
				capitalize(pastOf(demap(SAVE)))
		}) + PERIOD);
		return item;
	}

	public void deleteItem(Long itemID) {
		try {
			logger.info(space(new String[]{
					getItemByID(itemID).toDetailedString(),
					capitalize(pastOf(demap(DELETE)))
			}) + PERIOD);
			itemRepository.deleteById(itemID);
		} catch (IllegalArgumentException e) {
			logger.warn(space( new String[]{
					(demap(DELETE) + capitalize(demap(ITEM))),
					pastOf(CATCH),
					e.getClass().getName()
			}), e);
		}
	}

	public Aisle saveAisle(Aisle aisle) {
		aisle = aisleRepository.save(aisle);
		logger.info(space(new String[]{
				aisle.toDetailedString(),
				capitalize(pastOf(demap(SAVE)))
		}) + PERIOD);
		return aisle;
	}

	public void deleteAisle(Long aisleID) {
		try {
			logger.info(space(new String[]{
					getAisleByID(aisleID).toDetailedString(),
					capitalize(pastOf(demap(DELETE)))
			}) + PERIOD);
			aisleRepository.deleteById(aisleID);
		} catch (IllegalArgumentException e) {
			logger.warn(space( new String[]{
					(demap(DELETE) + capitalize(demap(AISLE))),
					pastOf(CATCH),
					e.getClass().getName()
			}), e);
		}
	}
}
