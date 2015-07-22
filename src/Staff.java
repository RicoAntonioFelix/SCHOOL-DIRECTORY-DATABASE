/*
 * Copyright (C) 2014-2015 Rico Antonio Felix
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

package com.rico.felix.models;

/**
 * This class is used to model a staff member within the system.
 *
 * The fundamental information contained within an object of this type
 * includes a staff member's title, first and last names along with an
 * e-mail address and the name of the staff member's office.
 */
public final class Staff extends Person
{
    // Field used for the serialization mechanism
    private static final long serialVersionUID = -3968916956475875017L;

    // Fields for storing fundamental information for an object of this type
    private String title;
    private String office;

    /**
     * Parameterized constructor for an object of this type
     *
     * @param title     - Staff member's title
     * @param firstName - Staff member's first name
     * @param lastName  - Staff member's last name
     * @param email     - Staff member's email address
     * @param office    - Name of staff member's office
     */
    public Staff(final String title, final String firstName, final String lastName,
          final String email, final String office)
    {
        super(firstName, lastName, email);
        this.title  = title;
        this.office = office;
    }

    /**
     * Retrieve name of office associated with an object of this type
     *
     * @return String - Name of office associated with the object
     */
    public String getOffice()
    {
        return office;
    }

    /**
     * Change the name of office associated with an object of this type
     *
     * @param newOffice - New name of office to associate with the object
     *
     * @return void
     */
    public void setOffice(final String newOffice)
    {
        office = newOffice;
    }

    /**
     * Retrieve the string representation of an object of this type
     *
     * @return String - String representation for this object
     */
    @Override
    public String toString()
    {
        return String.format(
            "Staff Member Name: %s%nE-mail Address: %s%nOffice: %s%n%n",
            (title + " " + getFirstName() + " " + getLastName()),
            getEmail(),
            office
        );
    }

}
