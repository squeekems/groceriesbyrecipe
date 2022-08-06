package com.jackrkern.groceriesbyrecipe.repositories;

import com.jackrkern.groceriesbyrecipe.models.Aisle;
import com.jackrkern.groceriesbyrecipe.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/* @author "Jack Kern" */

@Repository
public interface AisleRepository extends CrudRepository<Aisle, Long> {
	List<Aisle> findAllByUser(User userID);
}