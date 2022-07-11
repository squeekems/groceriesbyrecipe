package com.jackrkern.groceriesbyrecipe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jackrkern.groceriesbyrecipe.models.Aisle;
import com.jackrkern.groceriesbyrecipe.models.User;

/* @author "Jack Kern" */

@Repository
public interface AisleRepository extends CrudRepository<Aisle, Long>
{
	List<Aisle> findAllByUser(User userID);
}