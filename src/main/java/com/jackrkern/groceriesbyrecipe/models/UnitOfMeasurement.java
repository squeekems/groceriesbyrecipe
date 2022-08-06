/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.models;

/**
 * @imports
 */
import javax.persistence.*;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.CN_USER_ID;
import static com.jackrkern.groceriesbyrecipe.util.AppConstants.TN_UNITS_OF_MEASUREMENT;

/**
 *  @author Jack Kern <jackrkern@gmail.com>
 */
@Entity
@Table(name = TN_UNITS_OF_MEASUREMENT)
public class UnitOfMeasurement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long unitOfMeasurementID;

	@Column
	private String name;

	@ManyToOne
	@JoinColumn(name = CN_USER_ID, nullable = false)
	private User user;

	/**
	 * Returns the unitOfMeasurementID of the UnitOfMeasurement.
	 *
	 * @return the {@link Long} unitOfMeasurementID
	 */
	public Long getUnitOfMeasurementID() { return unitOfMeasurementID; }

	/**
	 * Sets the unitOfMeasurementID of the UnitOfMeasurement.
	 *
	 * @param unitOfMeasurementID of type {@link Long}
	 */
	public void setUnitOfMeasurementID(Long unitOfMeasurementID) { this.unitOfMeasurementID = unitOfMeasurementID; }

	/**
	 * Returns the name of the UnitOfMeasurement.
	 *
	 * @return the {@link String} name
	 */
	public String getName() { return name; }

	/**
	 * Sets the name of the UnitOfMeasurement.
	 *
	 * @param name of type {@link String}
	 */
	public void setName(String name) { this.name = name.trim(); }

	/**
	 * Returns the user of the UnitOfMeasurement.
	 *
	 * @return the {@link User} user
	 */
	public User getUser() { return user; }

	/**
	 * Sets the user of the UnitOfMeasurement.
	 *
	 * @param user of type {@link User}
	 */
	public void setUser(User user) { this.user = user; }

	/**
	 * Return all the values represented in the UnitOfMeasurement.
	 *
	 * @return a detailed String representation of the UnitOfMeasurement
	 */
	public String toDetailedString() {
		return user + "'s UnitOfMeasurement [unitOfMeasurementID=" + unitOfMeasurementID +
				", name=" + name + "]";
	}

	/**
	 * Returns the name of the UnitOfMeasurement.
	 *
	 * @return a String representation of the UnitOfMeasurement
	 */
	@Override
	public String toString() {
		return name;
	}
}