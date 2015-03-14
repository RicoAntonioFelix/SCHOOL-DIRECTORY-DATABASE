/*
 * Copyright 2014 Rico Antonio Felix
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
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

package com.rico.felix.view;

/*
 * Platform Dependencies
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;

/*
 * Platform Dependencies
 */
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

/*
 * Local Dependencies
 */
import com.rico.felix.models.Person;
import com.rico.felix.models.Student;
import com.rico.felix.models.Staff;
import com.rico.felix.models.Faculty;

/*
 * Local Dependencies
 */
import com.rico.felix.data.Database;
import com.rico.felix.data.Directory;

/*
 * Local Dependencies
 */
import com.rico.felix.auxiliary.Predicate;
import com.rico.felix.auxiliary.MemberTypes;

/**
 * This class is used as an interface between the user and the application
 * for managing the database
 */
public final class View
{
	// Application's interface view-port window frame and menu-bar
	private static JFrame frame;
	private static JMenuBar menuBar;

	// File menu components
	private static JMenu fileMenu;
	private static JMenuItem save;
	private static JMenuItem exit;

	// Edit menu components
	private static JMenu editMenu;
	private static JMenuItem  addStudent;
	private static JMenuItem  addStaffMember;
	private static JMenuItem  addFacultyMember;
	private static JSeparator seperator1;
	private static JMenuItem  removeMember;
	private static JSeparator seperator2;
	private static JMenuItem  clearDisplay;

	// Find menu components
	private static JMenu findMenu;
	private static JMenuItem showAll;
	private static JMenuItem findMember;
	private static JMenuItem findStudents;
	private static JMenuItem findStaffMembers;
	private static JMenuItem findFacultyMembers;

	// Help menu components
	private static JMenu helpMenu;
	private static JMenuItem manual;
	private static JMenuItem about;

	// Application interface's view-port and scrollbar
	private static JTextArea display;
	private static JScrollPane displayScrollPane;

	/*
	 * Prevent instantiation using conventional construction semantics
	 */
	private View()
	{}

	/*
	 * Statically configure interface's layout and bind action listeners
	 * to interface components
	 */
	static
	{
		initializeInterfaceComponents();
		configureViewPort();
		configureMenuBar();
		configureFrame();
	}

	private static void initializeInterfaceComponents()
	{
		// Interface window frame
		frame = new JFrame("School Directory Database");

		// Interface menu bar
		menuBar = new JMenuBar();

		// File menu components
		fileMenu = new JMenu("File");
		save     = new JMenuItem("Save");
		exit     = new JMenuItem("Exit");

		// Edit menu components
		editMenu         = new JMenu("Edit");
		addStudent       = new JMenuItem("Add Student");
		addStaffMember   = new JMenuItem("Add Staff Member");
		addFacultyMember = new JMenuItem("Add Faculty Member");
		seperator1       = new JSeparator();
		removeMember     = new JMenuItem("Remove Member");
		seperator2       = new JSeparator();
		clearDisplay     = new JMenuItem("Clear Display");

		// Find menu components
		findMenu           = new JMenu("Find");
		showAll            = new JMenuItem("Show All");
		findMember         = new JMenuItem("Find Member");
		findStudents       = new JMenuItem("Find Students");
		findStaffMembers   = new JMenuItem("Find Staff Members");
		findFacultyMembers = new JMenuItem("Find Faculty Members");

		// Help menu components
		helpMenu = new JMenu("Help");
		manual   = new JMenuItem("Manual");
		about    = new JMenuItem("About");

		// View-port
		display = new JTextArea();
	}

	private static void configureViewPort()
	{
		display.setBackground(Color.black);
		display.setForeground(Color.white);
        display.setText("Welcome to the School Directory Database\n\n"
                         +"Please use the application's menu");
		display.setEditable(false);
		displayScrollPane = new JScrollPane(display);
	}

	private static void configureMenuBar()
	{
		configureMenuBarLayout();
		assembleMenuBar();
		configureMenuBarActionListeners();
	}

	private static void configureMenuBarLayout()
	{
		configureFileMenuLayout();
		configureEditMenuLayout();
		configureFindMenuLayout();
		configureHelpMenuLayout();
	}

	private static void configureFileMenuLayout()
	{
		fileMenu.add(save);
		fileMenu.add(exit);
	}

	private static void configureEditMenuLayout()
	{
		editMenu.add(addStudent);
		editMenu.add(addStaffMember);
		editMenu.add(addFacultyMember);
		editMenu.add(seperator1);
		editMenu.add(removeMember);
		editMenu.add(seperator2);
		editMenu.add(clearDisplay);
	}

	private static void configureFindMenuLayout()
	{
		findMenu.add(showAll);
		findMenu.add(findMember);
		findMenu.add(findStudents);
		findMenu.add(findStaffMembers);
		findMenu.add(findFacultyMembers);
	}

	private static void configureHelpMenuLayout()
	{
		helpMenu.add(manual);
		helpMenu.add(about);
	}

	private static void assembleMenuBar()
	{
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(findMenu);
		menuBar.add(helpMenu);
	}

	private static void configureMenuBarActionListeners()
	{
		configureFileMenuActionListeners();
		configureEditMenuActionListeners();
        configureFindMenuActionListeners();
        configureHelpMenuActionListeners();
	}

	private static void configureFileMenuActionListeners()
	{
		configureSaveMenuActionListener();
		configureExitMenuActionListener();
	}

	private static void configureSaveMenuActionListener()
	{
		save.addActionListener((event) -> {
			Database.save();
			display.setEditable(true);
			display.replaceRange("Changes was saved successfully", 0,
					                     display.getText().length());
			display.setEditable(false);
		});
	}

	private static void configureExitMenuActionListener()
	{
		exit.addActionListener((event) -> {
			exitCloseOperation();
		});
	}

    private static void configureEditMenuActionListeners()
    {
        configureAddStudentActionListener();
        configureAddStaffMemberActionListener();
        configureAddFacultyMemberActionListener();
        configureRemoveMemberActionListener();
        configureClearDisplayActionListener();
    }

    private static void configureAddStudentActionListener()
    {
        addStudent.addActionListener((event) -> {
            Dialogs.getAddMemberDialog(MemberTypes.STUDENT).setVisible(true);
        });
    }

    private static void configureAddStaffMemberActionListener()
    {
        addStaffMember.addActionListener((event) -> {
            Dialogs.getAddMemberDialog(MemberTypes.STAFF).setVisible(true);
        });
    }

    private static void configureAddFacultyMemberActionListener()
    {
        addFacultyMember.addActionListener((event) -> {
            Dialogs.getAddMemberDialog(MemberTypes.FACULTY).setVisible(true);
        });
    }

    private static void configureRemoveMemberActionListener()
    {
        removeMember.addActionListener((event) -> {
            Dialogs.getRemoveMemberDialog().setVisible(true);
        });
    }

    private static void configureClearDisplayActionListener()
    {
        clearDisplay.addActionListener((event) -> {
            display.setEditable(true);
            display.replaceRange("", 0, display.getText().length());
            display.setEditable(false);
        });
    }

    private static void configureFindMenuActionListeners()
    {
        configureShowAllActionListener();
        configureFindMemberActionListener();
        configureFindStudentActionListener();
        configureFindStaffMemberActionListener();
        configureFindFacultyMemberActionListener();
    }

    private static void configureShowAllActionListener()
    {
        showAll.addActionListener((event) -> {

            Iterator<Person> allMembers =
                    Directory.getMemberInformation((person) -> {
                        return person instanceof Person;
                    });

            StringBuilder information = new StringBuilder("");

            if (allMembers != null)
            {
                while (allMembers.hasNext())
                {
                    information.append(allMembers.next().toString());
                }
            }
            else
            {
                emptyDirectoryNotification();
                return;
            }

            display.setEditable(true);
            display.replaceRange("Directory Listing:\n\n" + information.toString(), 0,
                                 display.getText().length());
            display.setEditable(false);

        });
    }

    private static void configureFindMemberActionListener()
    {
        findMember.addActionListener((event) -> {
            Dialogs.getFindMemberDialog().setVisible(true);
        });
    }

    private static void configureFindStudentActionListener()
    {
        findStudents.addActionListener((event) -> {

            Iterator<Person> students =
                    Directory.getMemberInformation((person) -> {
                        return person instanceof Student;
                    });

            StringBuilder information = new StringBuilder("");

            if (students != null)
            {
                while (students.hasNext())
                {
                    information.append(students.next().toString());
                }
            }
            else
            {
                emptyDirectoryNotification();
                return;
            }

            display.setEditable(true);
            display.replaceRange("Students Information:\n\n" + information.toString(),
                                 0, display.getText().length());
            display.setEditable(false);

        });
    }

    private static void configureFindStaffMemberActionListener()
    {
        findStaffMembers.addActionListener((event) -> {

            Iterator<Person> staffMembers =
                    Directory.getMemberInformation((person) -> {
                        return person instanceof Staff;
                    });

            StringBuilder information = new StringBuilder("");

            if (staffMembers != null)
            {
                while (staffMembers.hasNext())
                {
                    information.append(staffMembers.next().toString());
                }
            }
            else
            {
                emptyDirectoryNotification();
                return;
            }

            display.setEditable(true);
            display.replaceRange("Staff Members Information:\n\n" + information.toString(),
                                 0, display.getText().length());
            display.setEditable(false);

        });
    }

    private static void configureFindFacultyMemberActionListener()
    {
        findFacultyMembers.addActionListener((event) -> {

            Iterator<Person> facultyMembers =
                    Directory.getMemberInformation((person) -> {
                        return person instanceof Faculty;
                    });

            StringBuilder information = new StringBuilder("");

            if (facultyMembers != null)
            {
                while (facultyMembers.hasNext())
                {
                    information.append(facultyMembers.next().toString());
                }
            }
            else
            {
                emptyDirectoryNotification();
                return;
            }

            display.setEditable(true);
            display.replaceRange("Faculty Members Information:\n\n" + information.toString(),
                                 0, display.getText().length());
            display.setEditable(false);

        });
    }

    private static void configureHelpMenuActionListeners()
    {
        configureManualActionListener();
        configureAboutActionListener();
    }

    private static void configureManualActionListener()
    {
        manual.addActionListener((event) -> {
            JOptionPane.showMessageDialog(frame, "To be written at a later date", "Manual",
                                          JOptionPane.PLAIN_MESSAGE, null);
        });
    }

    private static void configureAboutActionListener()
    {
        about.addActionListener((event) -> {
            JOptionPane.showMessageDialog(frame, "<html><p>School Directory Database v1.0a"
                                   + "</p><br/><p>Created by Rico Antonio Felix</p></html>",
                                     "About", JOptionPane.PLAIN_MESSAGE, null);
        });
    }

	private static void emptyDirectoryNotification()
	{
		display.setEditable(true);
		display.replaceRange("Directory is currently empty:\n\n", 0,
			                 display.getText().length());
		display.setEditable(false);
	}

	private static void configureFrame()
	{
		frame.setLayout(new BorderLayout());

		configureFrameWindowActionListener();		

		frame.setSize(600, 400);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(displayScrollPane);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private static void configureFrameWindowActionListener()
	{
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent event)
			{
				exitCloseOperation();
			}

			@Override
			public void windowClosed(WindowEvent event)
			{
				System.exit(0x0);
			}
		});
	}

	/**
	 * Method used to present an option when terminating the application
	 * which can either be closing and saving changes or closing and discarding
	 * changes
	 */
	private static void exitCloseOperation()
	{
		Object[] options = { "Save", "Quit" };
		
		int choice = JOptionPane.showOptionDialog(frame, "Save changes?",
				               "Quit Dialog", JOptionPane.DEFAULT_OPTION,
				   JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (choice == 0)
		{
			Database.save();
			System.exit(0x0);
		}
		else
		{
			System.exit(0x0);
		}
	}

	/**
	 * Method used to respond to the application's launcher when requested to
	 * load the interface
	 */
	public static void loadView()
	{
		new View();
	}

	/**
	 * Method used for communication between the view and dialog box
	 */
	static JFrame getFrame()
	{
		return frame;
	}

	/**
	 * Method used for communication between the view's view-port and dialog box
	 */
	static JTextArea getDisplay()
	{
		return display;
	}

}
