package com.jackrkern.groceriesbyrecipe.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

//import com.jackrkern.groceriesbyrecipe.models.Aisle;
//import com.jackrkern.groceriesbyrecipe.models.Item;
//import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.AisleRepository;
import com.jackrkern.groceriesbyrecipe.repositories.ItemRepository;
import com.jackrkern.groceriesbyrecipe.repositories.UserRepository;

/* @author "Jack Kern" */

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent>
{
//	private final UserRepository userRepository;
//	private final ItemRepository itemRepository;
//	private final AisleRepository aisleRepository;

	public AppStartupEvent(	UserRepository userRepository, ItemRepository itemRepository,
							AisleRepository aisleRepository)
	{
//		this.userRepository = userRepository;
//		this.itemRepository = itemRepository;
//		this.aisleRepository = aisleRepository;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event)
	{
//		Iterable<User> users = userRepository.findAll();
//		Iterable<Item> items = itemRepository.findAll();
//		Iterable<Aisle> aisles = aisleRepository.findAll();
//		items.forEach(System.out::println);
//		aisles.forEach(System.out::println);
//		users.forEach(System.out::println);
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		System.out.println(encoder.encode("password"));
	}
}
