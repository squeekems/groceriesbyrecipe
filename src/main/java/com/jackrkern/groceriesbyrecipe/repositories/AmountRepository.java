package com.jackrkern.groceriesbyrecipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jackrkern.groceriesbyrecipe.models.Amount;

/* @author "Jack Kern" */

@Repository
public interface AmountRepository extends CrudRepository<Amount, Long>
{

}