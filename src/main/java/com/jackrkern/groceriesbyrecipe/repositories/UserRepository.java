/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.repositories;

/**
 * @imports
 */
import com.jackrkern.groceriesbyrecipe.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *  @author Jack Kern <jackrkern@gmail.com>
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
}