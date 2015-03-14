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

/**
 * This class is used to model a faculty member within the system.
 *
 * The fundamental information contained within an object of this type
 * includes a faculty member's first and last names along with an
 * e-mail address, whether the faculty member is tenured and the name
 * of the faculty member's office.
 */
public final class Faculty extends Person
{
	/**
	 * Object used for the serialization mechanism
	 */
	private static final long serialVersionUID = 4958318738235137176L;

	// Fields for storing fundamental information for an object of this type
	private boolean tenured;
	private String office;

	/**
	 * Parameterized constructor for an object of this type
	 *
	 * @param firstName Faculty member's first name
	 * @param lastName  Faculty member's last name
	 * @param email     Faculty member's email address
	 * @param tenured   Whether the faculty member is tenured or not
	 * @param office    Name of faculty member's office
	 */
	public Faculty(final String firstName, final String lastName, final String email,
			final boolean tenured, final String office)
	{
		super(firstName, lastName, email);
		this.tenured = tenured;
		this.office = office;
	}

	/**
	 * Retrieve tenured status for an object of this type
	 *
	 * @return Status whether the faculty member is tenured or not
	 */
	public boolean isTenured()
	{
		return tenured;
	}

	/**
	 * Change whether an object of this type is tenured or not
	 *
	 * @param tenured Whether the faculty member is tenured or not
	 */
	public void setTenured(final boolean tenured)
	{
		this.tenured = tenured;
	}

	/**
	 * Retrieve name of office associated with an object of this type
	 *
	 * @return Name of office associated with the object
	 */
	public String getOffice()
	{
		return office;
	}

	/**
	 * Change the name of office associated with an object of this type
	 *
	 * @param newOffice New name of office to associate with the object
	 */
	public void setOffice(final String newOffice)
	{
		office = newOffice;
	}

	/**
	 * Retrieve the string representation of an object of this type
	 *
	 * @return String representation for this object
	 */
	@Override
	public String toString()
	{
		return String.format("Faculty Member " + super.toString() + "Tenured: %s%n"
						+ "Office: %s%n%n", (tenured ? "Yes" : "No"), office);
	}

}
