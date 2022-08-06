/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.models;

/**
 * @imports
 */
import javax.persistence.*;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.CN_USER_ID;
import static com.jackrkern.groceriesbyrecipe.util.AppConstants.TN_AMOUNTS;

/**
 *  @author Jack Kern <jackrkern@gmail.com>
 */
@Entity
@Table(name = TN_AMOUNTS)
public class Amount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long amountID;

	@Column
	private String value;

	@ManyToOne
	@JoinColumn(name = CN_USER_ID, nullable = false)
	private User user;

	/**
	 * Returns amountID of the Amount.
	 *
	 * @return the {@link Long} amountID
	 */
	public Long getAmountID() { return amountID; }

	/**
	 * Sets the amountID of the Amount.
	 *
	 * @param amountID of type {@link Long}
	 */
	public void setAmountID(Long amountID) { this.amountID = amountID; }

	/**
	 * Returns the value of the Amount.
	 *
	 * @return the {@link String} value
	 */
	public String getValue() { return value; }

	/**
	 * Sets the value of the Amount.
	 *
	 * @param value of type {@link String}
	 */
	public void setValue(String value) { this.value = value.trim(); }

	/**
	 * Returns the user of the Amount.
	 *
	 * @return the {@link User} user
	 */
	public User getUser() { return user; }

	/**
	 * Sets the user of the Amount.
	 *
	 * @param user of type {@link User}
	 */
	public void setUser(User user) { this.user = user; }

	/**
	 * Returns all the values represented in the Amount.
	 *
	 * @return a detailed String representation of the Amount
	 */
	public String toDetailedString() {
		return user + "'s Amount [amountID=" + amountID +
				", value=" + value + "]";
	}

	/**
	 * Returns the value of the Amount.
	 *
	 * @return a String representation of the Amount
	 */
	@Override
	public String toString() { return value; }
}