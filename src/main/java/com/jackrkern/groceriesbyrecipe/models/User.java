package com.jackrkern.groceriesbyrecipe.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.TN_USERS;

/* @author "Jack Kern" */

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

	// @return the userID
	public Long getUserID() { return userID; }

	// @param userID the userID to set
	public void setUserID(Long userID) { this.userID = userID; }

	// @return the username
	public String getUsername() { return username; }

	// @param username the username to set
	public void setUsername(String username) { this.username = username.trim(); }

	// @return the email
	public String getEmail() { return email; }

	// @param email the email to set
	public void setEmail(String email) { this.email = email.trim(); }

	// @return the password
	public String getPassword() { return password; }

	// @param password the password to set
	public void setPassword(String password) { this.password = new BCryptPasswordEncoder().encode(password.trim()); }

	// @return the lastName
	public String getLastName() { return lastName; }

	// @param lastName the lastName to set
	public void setLastName(String lastName) { this.lastName = lastName.trim(); }

	// @return the firstName
	public String getFirstName() { return firstName; }

	// @param firstName the firstName to set
	public void setFirstName(String firstName) { this.firstName = firstName.trim(); }

	public String toDetailedString() {
		return "User [userID="	+ userID +
				", username=" + username +
				", email=" + email +
				", password=" + password +
				", lastName=" + lastName +
				", firstName=" + firstName + "]";
	}

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