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
@Table(name = TN_SHOPPING_LIST)
public class ShoppingListItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shoppingListID;

	@Column
	private int count;

	@OneToOne
	@JoinColumn(name = CN_ITEM_ID, referencedColumnName = CN_ITEM_ID)
	private Item item;

	@ManyToOne
	@JoinColumn(name = CN_USER_ID, nullable = false)
	private User user;

	/**
	 * No param Constructor
	 */
	public ShoppingListItem() {}

	/**
	 * Constructor
	 *
	 * @param user of type {@link User}
	 */
	public ShoppingListItem(User user) {
		this();
		this.user = user;
	}

	/**
	 * Constructor
	 * <p>
	 * Instantiates the count of the ShoppingListItem to {@code 1}.
	 *
	 * @param item of type {@link Item}
	 * @param user of type {@link User}
	 */
	public ShoppingListItem(Item item, User user) {
		this(user);
		this.item = item;
		this.count = 1;
	}

	/**
	 * Constructor
	 * <p>
	 * Instantiates the count of the ShoppingListItem to {@code 1}.
	 *
	 * @param item of type {@link Item}
	 */
	public ShoppingListItem(Item item) {
		this(item.getUser());
		this.item = item;
		this.count = 1;
	}

	/**
	 * Constructor
	 *
	 * @param count of type int
	 * @param item of type {@link Item}
	 * @param user of type {@link User}
	 */
	public ShoppingListItem(int count, Item item, User user) {
		this(item, user);
		this.count = count;
	}

	/**
	 * Returns the shoppingListID of the ShoppingListItem.
	 *
	 * @return the {@link Long} shoppingListID
	 */
	public Long getShoppingListID() { return shoppingListID; }

	/**
	 * Sets the shoppingListID of the ShoppingListItem.
	 *
	 * @param shoppingListID of type {@link Long}
	 */
	public void setShoppingListID(Long shoppingListID) { this.shoppingListID = shoppingListID; }

	/**
	 * Returns the count of the ShoppingListItem.
	 *
	 * @return the int count
	 */
	public int getCount() { return count; }

	/**
	 * Sets the count of the ShoppingListItem.
	 *
	 * @param count of type int
	 */
	public void setCount(int count) { this.count = count; }

	/**
	 * Returns the item of the ShoppingListItem.
	 *
	 * @return the {@link Item} item
	 */
	public Item getItem() { return item; }

	/**
	 * Sets the item of the ShoppingListItem.
	 *
	 * @param item of type {@link Item}
	 */
	public void setItem(Item item) { this.item = item; }

	/**
	 * Returns the user of the ShoppingListItem.
	 *
	 * @return the {@link User} user
	 */
	public User getUser() { return user; }

	/**
	 * Sets the user of the ShoppingListItem.
	 *
	 * @param user of type {@link User}
	 */
	public void setUser(User user) { this.user = user; }

	/**
	 * Return all the values represented in the ShoppingListItem.
	 *
	 * @return a detailed String representation of the ShoppingListItem
	 */
	public String toDetailedString() {
		if (count > 1)
			return user + "'s ShoppingListItem [shoppingListID=" + shoppingListID +
					", count=" + count +
					", item=" + item + "]";
		else
			return user + "'s ShoppingListItem [shoppingListID=" + shoppingListID +
					", item=" + item + "]";
	}

	/**
	 * Returns the item and count of the ShoppingListItem.
	 *
	 * @return a String representation of the ShoppingListItem
	 */
	@Override
	public String toString() {
		if (count > 1)
			return item + " (x" + count + ")";
		else
			return item.toString();
	}
}