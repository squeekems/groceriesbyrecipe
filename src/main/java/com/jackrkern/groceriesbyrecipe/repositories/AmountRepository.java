package com.jackrkern.groceriesbyrecipe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jackrkern.groceriesbyrecipe.models.Amount;
import com.jackrkern.groceriesbyrecipe.models.User;

/* @author "Jack Kern" */

@Repository
public interface AmountRepository extends CrudRepository<Amount, Long>
{
	List<Amount> findAllByUser(User userID);
}