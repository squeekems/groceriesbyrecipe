package com.jackrkern.groceriesbyrecipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jackrkern.groceriesbyrecipe.models.Unit;

/* @author "Jack Kern" */

@Repository
public interface UnitRepository extends CrudRepository<Unit, Long>
{

}
