package com.jackrkern.groceriesbyrecipe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jackrkern.groceriesbyrecipe.models.Item;
import com.jackrkern.groceriesbyrecipe.models.ShoppingListItem;
import com.jackrkern.groceriesbyrecipe.models.User;

/* @author "Jack Kern" */

@Repository
public interface ShoppingListItemRepository extends CrudRepository<ShoppingListItem, Long>
{
	List<ShoppingListItem> findAllByUser(User userID);

	ShoppingListItem findByItemAndUser(Item itemID, User userID);
}