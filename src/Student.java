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
 * This class is used to model a student within the system.
 *
 * The fundamental information contained within an object of this type
 * includes a student's first and last names along with an e-mail address
 * and the student's seniority which are classified as either freshman,
 * sophmore, junior or senior.
 */
public final class Student extends Person
{
	/**
	 * Object used for the serialization mechanism
	 */
	private static final long serialVersionUID = -8342165606041819467L;

	// Field for storing student's seniority
	private String classTypeStatus;

	/**
	 * Parameterized constructor for an object of this type
	 *
	 * @param firstName       Student's first name
	 * @param lastName        Student's last name
	 * @param email           Student's email address
	 * @param classTypeStatus Student's seniority status
	 */
	public Student(final String firstName, final String lastName, final String email,
			final String classTypeStatus)
	{
		super(firstName, lastName, email);
		this.classTypeStatus = classTypeStatus;
	}

	/**
	 * Retrieve the string representation of an object of this type
	 *
	 * @return String representation for this object
	 */
	@Override
	public String toString()
	{
		return String.format("Student " + super.toString() + "Class Type: %s%n%n",
			                 classTypeStatus);
	}
}
