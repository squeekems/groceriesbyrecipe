package com.jackrkern.groceriesbyrecipe;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.jackrkern.groceriesbyrecipe.models.Item;
import com.jackrkern.groceriesbyrecipe.models.ShoppingListItem;
import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.ItemRepository;
import com.jackrkern.groceriesbyrecipe.repositories.ShoppingListItemRepository;
import com.jackrkern.groceriesbyrecipe.repositories.UserRepository;

/* @author "Jack Kern" */

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ShoppingListRepositoryTests
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	ShoppingListItemRepository shoppingListItemRepository;

	@Test
	public void testFindAllByUser()
	{
		Long userID = (long) 1;

		User user = userRepository.findById(userID).get();

		List<ShoppingListItem> shoppingList = shoppingListItemRepository.findAllByUser(user);

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		shoppingList.forEach(System.out::println);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		assertThat(shoppingList).isNotNull();
	}

	@Test
	public void testFindByItemAndUser()
	{
		Item item = itemRepository.findById((long) 5).get();

		User user = userRepository.findById((long) 1).get();

		ShoppingListItem shoppingListItem = shoppingListItemRepository.findByItemAndUser(item, user);

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(shoppingListItem);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		assertThat(shoppingListItem).isNotNull();
	}
}