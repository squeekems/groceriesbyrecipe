package com.jackrkern.groceriesbyrecipe.repositories;

import com.jackrkern.groceriesbyrecipe.models.Item;
import com.jackrkern.groceriesbyrecipe.models.ShoppingListItem;
import com.jackrkern.groceriesbyrecipe.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/* @author "Jack Kern" */

@Repository
public interface ShoppingListItemRepository extends CrudRepository<ShoppingListItem, Long> {
	List<ShoppingListItem> findAllByUser(User userID);
	ShoppingListItem findByItemAndUser(Item itemID, User userID);
}