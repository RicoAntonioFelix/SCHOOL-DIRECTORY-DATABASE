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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	 * This method is used to persist runtime data in a file on disk
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
	 * This method is used to restore persisted data if any
	 *
	 * @return Whether restoration succeeded or not
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
	 * This method is used to stream an error message upon i/o error
	 * and terminate the application
	 */
	private static void ioErrorMessage()
	{
		System.err.println("I/O Error Occurred Unexpectedly");
		System.err.println("Application will now terminate");
		System.exit(-0x1);
	}

}
