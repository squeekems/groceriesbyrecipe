package com.jackrkern.groceriesbyrecipe.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.jackrkern.groceriesbyrecipe.models.Item;
import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.ItemRepository;
import com.jackrkern.groceriesbyrecipe.repositories.UserRepository;

/* @author "Jack Kern" */

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent>
{
	private final UserRepository userRepository;
	private final ItemRepository itemRepository;

	public AppStartupEvent(UserRepository userRepository, ItemRepository itemRepository)
	{
		this.userRepository = userRepository;
		this.itemRepository = itemRepository;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event)
	{
		Iterable<User> users = this.userRepository.findAll();
		users.forEach(System.out::println);
		Iterable<Item> items = this.itemRepository.findAll();
		items.forEach(System.out::println);
	}
}
