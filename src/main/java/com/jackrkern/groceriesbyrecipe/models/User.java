/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.models;

/**
 * @imports
 */
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.TN_USERS;

/**
 *  @author Jack Kern <jackrkern@gmail.com>
 */
@Entity
@Table(name = TN_USERS)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userID;

	@Column
	private String username;

	@Column(nullable = false, unique = true, length = 45)
	private String email;

	@Column(nullable = false, length = 64)
	private String password;

	@Column
	private String lastName;

	@Column
	private String firstName;

	/**
	 * Returns the userID of the User.
	 *
	 * @return the {@link Long} userID
	 */
	public Long getUserID() { return userID; }

	/**
	 * Sets the userID of the User.
	 *
	 * @param userID of type {@link Long}
	 */
	public void setUserID(Long userID) { this.userID = userID; }

	/**
	 * Returns the username of the User.
	 *
	 * @return the {@link String} username
	 */
	public String getUsername() { return username; }

	/**
	 * Sets the username of the User.
	 *
	 * @param username of type {@link String}
	 */
	public void setUsername(String username) { this.username = username.trim(); }

	/**
	 * Return the email of the User.
	 *
	 * @return the {@link String} email
	 */
	public String getEmail() { return email; }

	/**
	 * Sets the email of the User.
	 *
	 * @param email of type {@link String}
	 */
	public void setEmail(String email) { this.email = email.trim(); }

	/**
	 * Returns the password of the User.
	 *
	 * @return the {@link String} password
	 */
	public String getPassword() { return password; }

	/**
	 * Sets the password of the User.
	 *
	 * @param password of type {@link String}
	 */
	public void setPassword(String password) { this.password = new BCryptPasswordEncoder().encode(password.trim()); }

	/**
	 * Returns the lastName of the User.
	 *
	 * @return the {@link String} lastName
	 */
	public String getLastName() { return lastName; }

	/**
	 * Sets the lastName of the User.
	 *
	 * @param lastName of type {@link String}
	 */
	public void setLastName(String lastName) { this.lastName = lastName.trim(); }

	/**
	 * Returns the firstName of the User.
	 *
	 * @return the {@link String} firstName
	 */
	public String getFirstName() { return firstName; }

	/**
	 * Sets the firstName of the User.
	 *
	 * @param firstName of type {@link String}
	 */
	public void setFirstName(String firstName) { this.firstName = firstName.trim(); }

	/**
	 * Return all the values represented in the User.
	 *
	 * @return a detailed String representation of the User
	 */
	public String toDetailedString() {
		return "User [userID="	+ userID +
				", username=" + username +
				", email=" + email +
				", password=" + password +
				", lastName=" + lastName +
				", firstName=" + firstName + "]";
	}

	/**
	 * Checks which String representations are available and returns the most intimate option.
	 *
	 * @return a String representation of the User
	 */
	@Override
	public String toString() {
		String fullName;
		if (firstName != null && !firstName.isEmpty()) {
			fullName = firstName;
			if (lastName != null && !lastName.isEmpty())
				fullName += " " + lastName;
		} else if (lastName != null && !lastName.isEmpty())
			fullName = lastName;
		else if (username != null && !username.isEmpty())
			fullName = username;
		else
			fullName = email;
		return fullName;
	}
}