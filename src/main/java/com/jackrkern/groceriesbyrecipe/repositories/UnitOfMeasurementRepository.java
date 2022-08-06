/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.repositories;

/**
 * @imports
 */
import com.jackrkern.groceriesbyrecipe.models.UnitOfMeasurement;
import com.jackrkern.groceriesbyrecipe.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  @author Jack Kern <jackrkern@gmail.com>
 */
@Repository
public interface UnitOfMeasurementRepository extends CrudRepository<UnitOfMeasurement, Long> {
	List<UnitOfMeasurement> findAllByUser(User userID);
}
