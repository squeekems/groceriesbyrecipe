package com.jackrkern.groceriesbyrecipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jackrkern.groceriesbyrecipe.models.UnitOfMeasurement;

/* @author "Jack Kern" */

@Repository
public interface UnitOfMeasurementRepository extends CrudRepository<UnitOfMeasurement, Long>
{

}
