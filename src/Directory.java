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

package com.rico.felix.data;

/*
 * Platform Dependencies
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * Local Dependencies
 */
import com.rico.felix.models.Person;
import com.rico.felix.models.Student;

/*
 * Local Dependency
 */
import com.rico.felix.auxiliary.Predicate;

/**
 * This class is used as an in-memory database for managing the application's
 * data.
 */
public class Directory
{
	/**
	 * Object used for managing the application's data
	 */
	private static Map<String, Person> filingSystem = new TreeMap<>();

	/*
	 * Prevent instantiation
	 */
	private Directory()
	{}

	/**
	 * Method used to add a record to the database
	 *
	 *@param person Record consisting of information pertaining to a person object
	 *              to add to the database
	 */
	public static void addPerson(final Person person)
	{
		if (person != null)
			filingSystem.put(person.getKey(), person);
	}

	/**
	 * Method used to delete a record from the database
	 *
	 * @param key Object's identifier which equates to the object's name
	 *
	 * @return    Person object if located within database or null
	 */
	public static Person removePerson(final String key)
	{
		return filingSystem.remove(key);
	}

	/**
	 * Method used to retrieve a record from the database
	 *
	 * @param key Object's identifier which equates to the object's name
	 *
	 * @return    Person object if located within database or null
	 */
	public static Person retrievePerson(final String key)
	{
		return filingSystem.get(key);
	}

	/**
	 * Method used to retrieve a specified sequence of records from the database
	 *
	 * @param filter Object used to filter the records for a specific member type
	 *
	 * @return       A specified sequence of records from the database if located
	 *               or null
	 */
	public static Iterator<Person> getMemberInformation(Predicate filter)
	{
		List<Person> members = new ArrayList<Person>();
		if (!filingSystem.isEmpty())
		{
			for (Person person : filingSystem.values())
			{
				if (filter.test(person))
					members.add(person);
			}
			return members.iterator();
		}
		return null;
	}

	/**
	 * Method used to retrieve a copy of the database
	 *
	 * @return A copy of the database
	 */
	public static TreeMap<String, Person> getFilingSystem()
	{
		return new TreeMap<>(filingSystem);
	}

	/**
	 * Method used to provide data for the database
	 *
	 * @param data A map data structure containing database information
	 *
	 * @return     Whether the initialization was successful for not
	 */
	public static boolean setFilingSystem(Map<String, Person> data)
	{
		if ((filingSystem = data) != null)
			return true;
		else
			return false;
	}

	/**
	 * Method used to initialize the database for the application on startup
	 */
	public static void loadDirectory()
	{
		if (!Database.restore())
		{
			Directory.addPerson(new Student("Rico", "Felix",
					"ricoantoniofelix@yahoo.com", "Freshman"));
		}
	}

}
