package com.jackrkern.groceriesbyrecipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jackrkern.groceriesbyrecipe.models.Ingredient;

/* @author "Jack Kern" */

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long>
{

}