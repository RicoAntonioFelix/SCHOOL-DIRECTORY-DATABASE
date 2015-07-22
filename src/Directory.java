/*
 * Copyright 2014-2015 Rico Antonio Felix
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

/**
 * @author Rico Antonio Felix <ricoantoniofelix@yahoo.com>
 */

package com.rico.felix.data;

/*
 * Platform Dependencies
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * Platform Dependency
 */
import java.util.function.Predicate;

/*
 * Platform Dependency
 */
import java.util.stream.Collectors;

/*
 * Local Dependencies
 */
import com.rico.felix.models.Person;
import com.rico.felix.models.Student;

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
     * Prevent object instantiation
     */
    private Directory()
    {}

    /**
     * Add a record to the database
     *
     * @param person - Record consisting of information pertaining to a person
     *
     * @return void
     */
    public static void addPerson(final Person person)
    {
        if (person != null)
            filingSystem.put(person.getKey(), person);
    }

    /**
     * Delete a record from the database
     *
     * @param key - Identifier which equates to the Person's name
     *
     * @return Person - Person object if located within database or null
     */
    public static Person removePerson(final String key)
    {
        return filingSystem.remove(key);
    }

    /**
     * Retrieve a record from the database
     *
     * @param key - Identifier which equates to the Person's name
     *
     * @return Person - Person object if located within database or null
     */
    public static Person retrievePerson(final String key)
    {
        return filingSystem.get(key);
    }

    /**
     * Retrieve a specified sequence of records from the database
     *
     * @param filter - Object used to filter the records for a specific member type
     *
     * @return List<Person> - A specified sequence of records from the database if located
     *                        or null
     */
    public static List<Person> getMemberInformation(Predicate<Person> filter)
    {
        if (!filingSystem.isEmpty())
        {
            return filingSystem.values()
                               .stream()
                               .filter(filter)
                               .collect(Collectors.toList());
        }

        return null;
    }

    /**
     * Retrieve a copy of the database
     *
     * @return TreeMap<String, Person> - A copy of the database
     */
    public static TreeMap<String, Person> getFilingSystem()
    {
        return new TreeMap<>(filingSystem);
    }

    /**
     * Provide data for the database
     *
     * @param data - A map data structure containing database information
     *
     * @return boolean - true if initialization was successful, false otherwise
     */
    public static boolean setFilingSystem(Map<String, Person> data)
    {
        return ((filingSystem = data) != null) ? true : false;
    }

    /**
     * Initialize the database for the application on startup
     *
     * @return void
     */
    public static void loadDirectory()
    {
        if (!Database.restore())
            Directory.addPerson(new Student("Rico", "Felix",
                    "ricoantoniofelix@yahoo.com", "Freshman"));
    }

}
