/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.models;

/**
 * @imports
 */
import javax.persistence.*;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.CN_USER_ID;
import static com.jackrkern.groceriesbyrecipe.util.AppConstants.TN_AISLES;

/**
 *  @author Jack Kern <jackrkern@gmail.com>
 */
@Entity
@Table(name = TN_AISLES)
public class Aisle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long aisleID;

	@Column
	private String name;

	@ManyToOne
	@JoinColumn(name = CN_USER_ID, nullable = false)
	private User user;

	/**
	 * Returns the aisleID of the Aisle.
	 *
	 * @return the {@link Long} aisleID
	 */
	public Long getAisleID() { return aisleID; }

	/**
	 * Sets the aisleID of the Aisle.
	 *
	 * @param aisleID of type {@link Long}
	 */
	public void setAisleID(Long aisleID) { this.aisleID = aisleID; }

	/**
	 * Returns the name of the Aisle.
	 *
	 * @return the {@link String} name
	 */
	public String getName() { return name; }

	/**
	 * Sets the name of the Aisle.
	 *
	 * @param name of type {@link String}
	 */
	public void setName(String name) { this.name = name.trim(); }

	/**
	 * Returns the user of the Aisle.
	 *
	 * @return the {@link User} user
	 */
	public User getUser() { return user; }

	/**
	 * Sets the user of the Aisle.
	 *
	 * @param user of type {@link User}
	 */
	public void setUser(User user) { this.user = user; }

	/**
	 * Compares two Aisles by their name, of type {@link String}, lexicographically.
	 * <p>
	 * This is the definition of lexicographic ordering. If two strings are
	 * different, then either they have different characters at some index
	 * that is a valid index for both strings, or their lengths are different,
	 * or both. If they have different characters at one or more index
	 * positions, let <i>k</i> be the smallest such index; then the string
	 * whose character at position <i>k</i> has the smaller value, as
	 * determined by using the {@code <} operator, lexicographically precedes the
	 * other string. In this case, {@code compareTo} returns the
	 * difference of the two character values at position {@code k} in
	 * the two string -- that is, the value:</p>
	 * <blockquote><pre>
	 * this.charAt(k)-anotherString.charAt(k)
	 * </pre></blockquote>
	 * If there is no index position at which they differ, then the shorter
	 * string lexicographically precedes the longer string. In this case,
	 * {@code compareTo} returns the difference of the lengths of the
	 * strings -- that is, the value:
	 * <blockquote><pre>
	 * this.length()-anotherString.length()
	 * </pre></blockquote>
	 *
	 * <p>For finer-grained String comparison, refer to
	 * {@link java.text.Collator}.
	 *
	 * @param   aisle   the {@code String} to be compared.
	 * @return  the value {@code 0} if the argument aisle name is equal to
	 *          this aisle name; a value less than {@code 0} if this aisle name
	 *          is lexicographically less than the argument aisle name; and a
	 *          value greater than {@code 0} if this aisle name is
	 *          lexicographically greater than the argument aisle name.
	 */
	public int compareTo(Aisle aisle) { return name.compareTo(aisle.getName()); }

	/**
	 * Returns all the values represented in the Aisle.
	 *
	 * @return a detailed String representation of the Aisle
	 */
	public String toDetailedString() {
		return user + "'s Aisle [aisleID=" + aisleID +
				", name=" + name + "]";
	}

	/**
	 * Returns the name of the Aisle.
	 *
	 * @return a String representation of the Aisle
	 */
	@Override
	public String toString()
	{
		return name;
	}
}