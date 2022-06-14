package com.jackrkern.groceriesbyrecipe.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jackrkern.groceriesbyrecipe.models.Aisle;

/* @author "Jack Kern" */

@Repository
public interface AisleRepository extends CrudRepository<Aisle, Long>
{
	@Query("SELECT a FROM Aisle a WHERE a.name = ?1")
	Aisle findByName(String name);
}