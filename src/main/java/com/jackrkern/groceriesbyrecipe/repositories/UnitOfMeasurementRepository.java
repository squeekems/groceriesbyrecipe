package com.jackrkern.groceriesbyrecipe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jackrkern.groceriesbyrecipe.models.UnitOfMeasurement;
import com.jackrkern.groceriesbyrecipe.models.User;

/* @author "Jack Kern" */

@Repository
public interface UnitOfMeasurementRepository extends CrudRepository<UnitOfMeasurement, Long>
{
	List<UnitOfMeasurement> findAllByUser(User userID);
}
