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

package com.rico.felix.data;

/*
 * Platform Dependencies
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * Platform Dependency
 */
import java.util.TreeMap;

/*
 * Local Dependency
 */
import com.rico.felix.models.Person;

/**
 * This class is used to persist and restore runtime data.
 */
public class Database
{
    /*
     * Prevent instantiation
     */
    private Database()
    {}

    /**
     * Persist runtime data in a file on disk
     *
     * @return void
     */
    public static void save()
    {
        try (BufferedOutputStream bos = new BufferedOutputStream(
                                            new FileOutputStream("directory.dat"));
             ObjectOutputStream oos = new ObjectOutputStream(bos);
            )
        {
            oos.writeObject(Directory.getFilingSystem());
        }
        catch (IOException ioe)
        {
            ioErrorMessage();
        }
    }

    /**
     * Restore persisted data if any
     *
     * @return boolean - true if restoration succeeded, false otherwise
     */
    @SuppressWarnings("unchecked")
    public static boolean restore()
    {
        try (BufferedInputStream bis = new BufferedInputStream(
                                            new FileInputStream("directory.dat"));
             ObjectInputStream ois = new ObjectInputStream(bis);
            )
        {
            return Directory.setFilingSystem((TreeMap<String, Person>)ois.readObject());
        }
        catch (IOException ioe)
        {
            if (Directory.getFilingSystem().isEmpty())
                ; // Application has no records in the database which is okay
            else
                ioErrorMessage();
        }
        catch (ClassNotFoundException cnfe)
        {
            System.err.println("Internal Error: ");
            cnfe.printStackTrace();
        }
        return false;
    }

    /**
     * Stream an error message upon i/o error and terminate the application
     *
     * @return void
     */
    private static void ioErrorMessage()
    {
        System.err.println("I/O Error Occurred Unexpectedly");
        System.err.println("Application will now terminate");
        System.exit(-0x1);
    }

}
