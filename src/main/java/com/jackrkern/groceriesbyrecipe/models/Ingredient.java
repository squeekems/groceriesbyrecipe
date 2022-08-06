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
@Table(name = TN_INGREDIENTS)
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ingredientID;

	@OneToOne
	@JoinColumn(name = CN_AMOUNT_ID, referencedColumnName = CN_AMOUNT_ID)
	private Amount amount;

	@OneToOne
	@JoinColumn(name = CN_UNIT_OF_MEASUREMENT_ID, referencedColumnName = CN_UNIT_OF_MEASUREMENT_ID)
	private UnitOfMeasurement unitOfMeasurement;

	@OneToOne
	@JoinColumn(name = CN_ITEM_ID, referencedColumnName = CN_ITEM_ID)
	private Item item;

	/**
	 * Returns the ingredientID of the Ingredient.
	 *
	 * @return the {@link Long} ingredientID
	 */
	public Long getIngredientID() { return ingredientID; }

	/**
	 * Sets the ingredientID of the Ingredient.
	 *
	 * @param ingredientID of type {@link Long}
	 */
	public void setIngredientID(Long ingredientID) { this.ingredientID = ingredientID; }

	/**
	 * Returns the amount of the Ingredient.
	 *
	 * @return the {@link Amount} amount
	 */
	public Amount getAmount() { return amount; }

	/**
	 * Sets the amount of the Ingredient.
	 *
	 * @param amount of type {@link Amount}
	 */
	public void setAmount(Amount amount) { this.amount = amount; }

	/**
	 * Returns the unitOfMeasurement of the Ingredient.
	 *
	 * @return the {@link UnitOfMeasurement} unitOfMeasurement
	 */
	public UnitOfMeasurement getUnitOfMeasurement() { return unitOfMeasurement; }

	/**
	 * Sets the unitOfMeasurement of the Ingredient.
	 *
	 * @param unitOfMeasurement of type {@link UnitOfMeasurement}
	 */
	public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	/**
	 * Returns the item of the Ingredient.
	 *
	 * @return the {@link Item} item
	 */
	public Item getItem() { return item; }

	/**
	 * Sets the item of the Ingredient.
	 *
	 * @param item of type {@link Item}
	 */
	public void setItem(Item item) { this.item = item; }

	/**
	 * Return all the values represented in the Ingredient.
	 *
	 * @return a detailed String representation of the Ingredient
	 */
	public String toDetailedString() {
		return "Ingredient [ingredientID="	+ ingredientID +
				", amount=" + amount +
				", unitOfMeasurement=" + unitOfMeasurement +
				", item=" + item + "]";
	}

	/**
	 * Returns the amount unitOfMeasurement and item of the Ingredient.
	 *
	 * @return a String representation of the Ingredient
	 */
	@Override
	public String toString() { return amount + " " + unitOfMeasurement + " " + item; }
}