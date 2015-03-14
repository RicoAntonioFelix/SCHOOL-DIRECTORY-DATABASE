/*
 * Copyright 2014 Rico Antonio Felix
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rico.felix.models;

/*
 * Dependency
 */
import java.io.Serializable;

/**
 * This class is used to model a generic person within the system.
 *
 * The fundamental information contained within an object of this type
 * includes a person's first and last names along with an e-mail address.
 */
public abstract class Person implements Serializable
{
	/**
	 * Object used for the serialization mechanism
	 */
	private static final long serialVersionUID = 4875771514949460706L;

	// Fields for storing fundamental information for an object of this type
	private String firstName;
	private String lastName;
	private String email;

	/**
	 * Parameterized constructor for an object of this type
	 *
	 * @param firstName Person's first name
	 * @param lastName  Person's last name
	 * @param email     Person's email address
	 */
	public Person(final String firstName, final String lastName, final String email)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	/**
	 * Retrieve first name associated with an object of this type
	 *
	 * @return First name associated with the object
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Change the first name associated with an object of this type
	 *
	 * @param firstName New first name to associate with the object
	 */
	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * Retrieve last name associated with an object of this type
	 *
	 * @return Last name associated with the object
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * Change the last name associated with an object of this type
	 *
	 * @param lastName New last name to associate with the object
	 */
	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * Retrieve email address associated with an object of this type
	 *
	 * @return Email address associated with the object
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Change the email address associated with an object of this type
	 *
	 * @param email New email address to associate with the object
	 */
	public void setEmail(final String email)
	{
		this.email = email;
	}

	/**
	 * Retrieve a custom built key from this object which is designed
	 * to be usable within mapped data structures.
	 */
	public String getKey()
	{
		return lastName + " " + firstName;
	}

	/**
	 * Retrieve the string representation of an object of this type
	 *
	 * @return String representation for this object
	 */
	@Override
	public String toString()
	{
		return String.format("Name: %s%nE-mail Address: %s%n",
				             (firstName + " " + lastName), email);
	}

}
