package com.jackrkern.groceriesbyrecipe.repositories;

import com.jackrkern.groceriesbyrecipe.models.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/* @author "Jack Kern" */

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {}