/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.models;

/**
 * @imports
 */
import javax.persistence.*;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/**
 *  @author Jack Kern <jackrkern@gmail.com>
 */
@Entity
@Table(name = TN_ITEMS)
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemID;

	@Column
	private String description;

	@OneToOne
	@JoinColumn(name = CN_AISLE_ID, referencedColumnName = CN_AISLE_ID)
	private Aisle aisle;

	@ManyToOne
	@JoinColumn(name = CN_USER_ID, nullable = false)
	private User user;

	public Item() {}

	public Item(String description, Aisle aisle, User user) {
		this();
		this.description = description;
		this.aisle = aisle;
		this.user = user;
	}

	/**
	 * Returns the itemID of the Item.
	 *
	 * @return the {@link Long} itemID
	 */
	public Long getItemID() { return itemID; }

	/**
	 * Sets the itemID of the Item.
	 *
	 * @param itemID of type {@link Long}
	 */
	public void setItemID(Long itemID) { this.itemID = itemID; }

	/**
	 * Returns the description of the Item.
	 *
	 * @return the {@link String} description
	 */
	public String getDescription() { return description; }

	/**
	 * Sets the description of the Item
	 *
	 * @param description of type {@link String}
	 */
	public void setDescription(String description) { this.description = description.trim(); }

	/**
	 * Returns the aisle of the Item.
	 *
	 * @return the {@link Aisle} aisle
	 */
	public Aisle getAisle() { return aisle; }

	/**
	 * Sets the aisle of the Item.
	 *
	 * @param aisle of type {@link Aisle}
	 */
	public void setAisle(Aisle aisle) { this.aisle = aisle; }

	/**
	 * Returns the user of the Item.
	 *
	 * @return the {@link User} user
	 */
	public User getUser() { return user; }

	/**
	 * Sets the user of the Item.
	 *
	 * @param user of type {@link User}
	 */
	public void setUser(User user) { this.user = user; }

	/**
	 * Return all the values represented in the Item.
	 *
	 * @return a detailed String representation of the Item
	 */
	public String toDetailedString() {
		return user + "'s Item [itemID=" + itemID +
				", description=" + description +
				", aisle=" + aisle + "]";
	}

	/**
	 * Returns the description of the Item.
	 *
	 * @return a String representation of the Item
	 */
	@Override
	public String toString()
	{
		return description;
	}
}