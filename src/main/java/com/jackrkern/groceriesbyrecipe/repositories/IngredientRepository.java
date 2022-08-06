/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.repositories;

/**
 * @imports
 */
import com.jackrkern.groceriesbyrecipe.models.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *  @author Jack Kern <jackrkern@gmail.com>
 */
@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {}