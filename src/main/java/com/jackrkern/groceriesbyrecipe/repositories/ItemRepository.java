package com.jackrkern.groceriesbyrecipe.repositories;

import com.jackrkern.groceriesbyrecipe.models.Item;
import com.jackrkern.groceriesbyrecipe.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/* @author "Jack Kern" */

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
	List<Item> findAllByUser(User userID);
}