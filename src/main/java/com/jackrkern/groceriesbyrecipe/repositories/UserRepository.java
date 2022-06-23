package com.jackrkern.groceriesbyrecipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jackrkern.groceriesbyrecipe.models.User;

/* @author "Jack Kern" */

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{
	User findByEmail(String email);
}