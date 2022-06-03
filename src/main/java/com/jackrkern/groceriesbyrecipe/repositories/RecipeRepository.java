package com.jackrkern.groceriesbyrecipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jackrkern.groceriesbyrecipe.models.Recipe;

/* @author "Jack Kern" */

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long>
{

}
